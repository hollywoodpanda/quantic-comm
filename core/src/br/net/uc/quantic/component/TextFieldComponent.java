package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class TextFieldComponent implements Component {

    public TextField textField;

    public TextFieldComponent(TextField textField) {

        this.textField = textField;

    }

}
