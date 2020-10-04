package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class ButtonComponent implements Component {

    public Stage stage;
    public Button button;

    public ButtonComponent (Stage stage, Button button) {
        this.stage = stage;
        this.button = button;
    }

}
