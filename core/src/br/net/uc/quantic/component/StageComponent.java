package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class StageComponent implements Component {

    public Stage stage;

    public StageComponent (Stage stage) {

        this.stage = stage;

    }

}
