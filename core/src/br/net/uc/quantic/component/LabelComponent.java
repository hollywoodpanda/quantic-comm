package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class LabelComponent implements Component {

    public Stage stage;
    public Label label;

    public LabelComponent (Stage stage, Label label) {
        this.stage = stage;
        this.label = label;
    }

}
