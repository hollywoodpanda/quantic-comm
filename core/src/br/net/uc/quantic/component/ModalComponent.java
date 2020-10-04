package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;

public class ModalComponent implements Component {

    public Stage stage;
    public Dialog dialog;

    public ModalComponent (Stage stage, Dialog dialog) {
        this.stage = stage;
        this.dialog = dialog;
    }

}
