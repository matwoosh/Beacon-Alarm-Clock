package org.sw.ui;

import org.sw.App;
import org.sw.R;

import trikita.anvil.DSL;

import static trikita.anvil.BaseDSL.CENTER_HORIZONTAL;
import static trikita.anvil.BaseDSL.FILL;
import static trikita.anvil.BaseDSL.WRAP;
import static trikita.anvil.BaseDSL.dip;
import static trikita.anvil.BaseDSL.margin;
import static trikita.anvil.BaseDSL.size;
import static trikita.anvil.BaseDSL.text;
import static trikita.anvil.BaseDSL.textSize;
import static trikita.anvil.BaseDSL.typeface;
import static trikita.anvil.BaseDSL.weight;
import static trikita.anvil.DSL.backgroundColor;
import static trikita.anvil.DSL.gravity;
import static trikita.anvil.DSL.textColor;
import static trikita.anvil.DSL.textView;

public class ActiveAlarmLayout {
    public static void view() {
        Theme.materialIcon(() -> {
            size(FILL, FILL);
            text("\ue857"); // "alarm off"
            textColor(Theme.get(App.getState().settings().theme()).accentColor);
            textSize(dip(128));
            backgroundColor(Theme.get(App.getState().settings().theme()).backgroundColor);
        });

        textView(() -> {
            size(FILL, WRAP);
            gravity(CENTER_HORIZONTAL);
            backgroundColor(Theme.get(App.getState().settings().theme()).backgroundTranslucentColor);
            weight(1f);
            margin(dip(10), dip(10));
            typeface("fonts/Roboto-Light.ttf");
            textSize(dip(24));
            textColor(Theme.get(App.getState().settings().theme()).primaryTextColor);
            DSL.text(R.string.phone_to_beacon);
        });
    }


}
