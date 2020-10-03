package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelFieldComponent implements Component {

    public Label label;

    public LabelFieldComponent (Label label) {
        this.label = label;
    }

}
