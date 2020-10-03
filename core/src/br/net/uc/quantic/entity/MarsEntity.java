package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.LabelFieldComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.SpriteComponent;
import br.net.uc.quantic.component.VelocityComponent;
import br.net.uc.quantic.utils.Values;

public class MarsEntity extends Entity {

    public MarsEntity () {

        // TODO: incluir textura do planeta marte!
        Sprite sprite = new Sprite(new Texture("badlogic.jpg"));

        add(new PositionComponent(500, 500));
        add(new VelocityComponent());
        add(new SpriteComponent(sprite));
        // Marte tem que ser pequenininho para parecer estar longe
        add(new DimensionComponent(10, 10));

        this.initLabel();

    }

    private void initLabel() {

        Skin skin = new Skin(Gdx.files.internal(Values.SKIN_PATH));

        Label label = new Label("", skin);
        label.setSize(100, 20);

        add(new LabelFieldComponent(label));

    }

}
