package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.RotationComponent;
import br.net.uc.quantic.component.SpriteComponent;

public class SpaceBackgroundEntity extends Entity {

    public SpaceBackgroundEntity () {

        Sprite sprite = new Sprite(new Texture("space.png"));

        add(new PositionComponent(-300, -300));
        add(new SpriteComponent(sprite));
        add(new DimensionComponent(1200, 1200));
        add(new RotationComponent(600, 600, -0.005f));

    }

}
