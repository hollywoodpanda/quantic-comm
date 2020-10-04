package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.DirectionComponent;
import br.net.uc.quantic.component.OnOffComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.SpriteComponent;
import br.net.uc.quantic.component.VelocityComponent;

public class MessageEntity extends Entity {

    public MessageEntity () {

        Sprite raySprite = new Sprite(new Texture("badlogic.jpg"));

        add(new PositionComponent());
        add(new DimensionComponent(20, 20));
        add(new DirectionComponent());
        add(new VelocityComponent());
        add(new SpriteComponent(raySprite));
        // TODO: Ideia é só disparar outra msg
        // TODO: ... se a msg num tá mais true
        add(new OnOffComponent(false));

    }

}
