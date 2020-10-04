package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class CameraComponent implements Component {

    public OrthographicCamera camera;

    public CameraComponent (OrthographicCamera camera) {

        this.camera = camera;

    }

}
