package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteComponent implements Component {

    public Sprite value;

    public SpriteComponent (Sprite value) {

        this.value = value;

    }

}
