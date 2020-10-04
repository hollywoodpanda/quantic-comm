package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;

public class DirectionComponent implements Component {

    public float x;
    public float y;

    public DirectionComponent () {
        this.x = 0f;
        this.y = 0f;
    }

    public DirectionComponent (float x, float y) {

        this.x = x;
        this.y = y;

    }

}
