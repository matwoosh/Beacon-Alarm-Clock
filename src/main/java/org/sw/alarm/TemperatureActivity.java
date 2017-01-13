package org.sw.alarm;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import org.sw.App;
import org.sw.R;
import org.sw.ui.Theme;

import trikita.anvil.Anvil;
import trikita.anvil.DSL;
import trikita.anvil.RenderableView;

import static trikita.anvil.BaseDSL.CENTER;
import static trikita.anvil.BaseDSL.CENTER_HORIZONTAL;
import static trikita.anvil.BaseDSL.CENTER_VERTICAL;
import static trikita.anvil.BaseDSL.FILL;
import static trikita.anvil.BaseDSL.TOP;
import static trikita.anvil.BaseDSL.WRAP;
import static trikita.anvil.BaseDSL.dip;
import static trikita.anvil.BaseDSL.margin;
import static trikita.anvil.BaseDSL.padding;
import static trikita.anvil.BaseDSL.size;
import static trikita.anvil.BaseDSL.text;
import static trikita.anvil.BaseDSL.textSize;
import static trikita.anvil.BaseDSL.typeface;
import static trikita.anvil.BaseDSL.weight;
import static trikita.anvil.DSL.backgroundColor;
import static trikita.anvil.DSL.frameLayout;
import static trikita.anvil.DSL.gravity;
import static trikita.anvil.DSL.linearLayout;
import static trikita.anvil.DSL.onClick;
import static trikita.anvil.DSL.orientation;
import static trikita.anvil.DSL.textColor;
import static trikita.anvil.DSL.textView;

public class TemperatureActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // fill status bar with a theme dark color on post-Lollipop devices
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Theme.get(App.getState().settings().theme()).primaryDarkColor);
        }


        setContentView(new RenderableView(this) {
            public void view() {
                backgroundColor(Theme.get(App.getState().settings().theme()).backgroundColor);
                    onClick(v -> finish());

                    linearLayout(() -> {
                        size(FILL, WRAP);
                        gravity(CENTER_VERTICAL);
                        Theme.materialIcon(() -> {
                            textColor(Theme.get(App.getState().settings().theme()).secondaryTextColor);
                            textSize(dip(32));
                            padding(dip(15));
                            text("\ue430"); // "sun" icon
                        });
                        textView(() -> {
                            size(WRAP, WRAP);
                            weight(1f);
                            typeface("fonts/Roboto-Light.ttf");
                            textSize(dip(20));
                            textColor(Theme.get(App.getState().settings().theme()).primaryTextColor);
                            DSL.text(getString(R.string.current_temperature));
                        });
                    });

                    textView(() -> {
                        size(FILL, FILL);
                        gravity(CENTER);
                        weight(1f);
                        typeface("fonts/Roboto-Light.ttf");
                        textSize(dip(128));
                        textColor(Theme.get(App.getState().settings().theme()).primaryTextColor);
                        DSL.text("-53");
                    });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
