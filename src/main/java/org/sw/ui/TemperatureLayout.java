package org.sw.ui;


import org.sw.App;
import org.sw.R;

import trikita.anvil.DSL;

import static trikita.anvil.BaseDSL.CENTER;
import static trikita.anvil.BaseDSL.CENTER_VERTICAL;
import static trikita.anvil.BaseDSL.FILL;
import static trikita.anvil.BaseDSL.WRAP;
import static trikita.anvil.BaseDSL.dip;
import static trikita.anvil.BaseDSL.padding;
import static trikita.anvil.BaseDSL.size;
import static trikita.anvil.BaseDSL.text;
import static trikita.anvil.BaseDSL.textSize;
import static trikita.anvil.BaseDSL.typeface;
import static trikita.anvil.BaseDSL.weight;
import static trikita.anvil.DSL.gravity;
import static trikita.anvil.DSL.linearLayout;
import static trikita.anvil.DSL.textColor;
import static trikita.anvil.DSL.textView;


public class TemperatureLayout {
    public static void view(String temperature) {
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
                DSL.text(R.string.current_temperature);
            });
        });

        textView(() -> {
            size(FILL, FILL);
            gravity(CENTER);
            weight(1f);
            typeface("fonts/Roboto-Light.ttf");
            textSize(dip(128));
            textColor(Theme.get(App.getState().settings().theme()).primaryTextColor);
            DSL.text(temperature);
        });
    }
}
