package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;

public class RotationComponent implements Component {

    public float x;

    public float y;

    public float degrees;

    public RotationComponent () {

        this.x = 1f;
        this.y = 1f;
        this.degrees = 0f;

    }

    public RotationComponent (float x, float y, float degrees) {

        this.x = x;
        this.y = y;
        this.degrees = degrees;

    }

}
