package org.sw.alarm;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import org.sw.App;
import org.sw.ui.TemperatureLayout;
import org.sw.ui.Theme;

import trikita.anvil.RenderableView;

import static trikita.anvil.DSL.backgroundColor;
import static trikita.anvil.DSL.onClick;

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
                TemperatureLayout.view("53 c");
            }
        });
    }


}
