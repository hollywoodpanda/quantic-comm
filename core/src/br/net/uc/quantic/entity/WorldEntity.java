package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.GameStateComponent;
import br.net.uc.quantic.component.LabelComponent;
import br.net.uc.quantic.component.SpriteBatchComponent;
import br.net.uc.quantic.component.TimerComponent;
import br.net.uc.quantic.utils.Values;

public class WorldEntity extends Entity {

    public WorldEntity (Stage stage) {

        Skin skin = new Skin(Gdx.files.internal(Values.SKIN_PATH));

        Label label = new Label("" + Values.INITIAL_LEVEL_TIME, skin);

        label.setColor(Color.GREEN);

        add(new SpriteBatchComponent());
        add(new DimensionComponent(10000, 10000));
        add(new GameStateComponent());
        add(new TimerComponent(Values.DEFAULT_LEVEL_TIME));
        add(new LabelComponent(stage, label));

    }

}
