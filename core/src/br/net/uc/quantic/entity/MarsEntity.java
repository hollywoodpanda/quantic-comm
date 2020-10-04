package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.RotationComponent;
import br.net.uc.quantic.component.SpriteComponent;
import br.net.uc.quantic.component.VelocityComponent;
import br.net.uc.quantic.component.WinningComponent;

public class MarsEntity extends Entity {

    public MarsEntity () {

        float x = 700;
        float y = 500;

        float width = 80;
        float height = 80;

        // TODO: incluir textura do planeta marte!
        Sprite sprite = new Sprite(new Texture("mars.png"));

        add(new PositionComponent(x, y));
        add(new VelocityComponent());
        add(new SpriteComponent(sprite));
        // Marte tem que ser pequenininho para parecer estar longe
        add(new DimensionComponent(width, height));
        add(new RotationComponent((width) / 2, (height) / 2, 0.2f));
        // TODO: Evoluir a forma como identificamos marte exclusivamente
        add(new WinningComponent());

    }

}
