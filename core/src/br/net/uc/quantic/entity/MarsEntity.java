package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.SpriteComponent;
import br.net.uc.quantic.component.VelocityComponent;

public class MarsEntity extends Entity {

    public MarsEntity () {

        // TODO: incluir textura do planeta marte!
        Sprite sprite = new Sprite(new Texture("badlogic.jpg"));

        add(new PositionComponent(500, 500));
        add(new VelocityComponent());
        add(new SpriteComponent(sprite));
        // Marte tem que ser pequenininho para parecer estar longe
        add(new DimensionComponent(10, 10));

    }

}
