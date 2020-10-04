package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import br.net.uc.quantic.component.GameStateComponent;
import br.net.uc.quantic.component.LabelComponent;
import br.net.uc.quantic.utils.Values;

public class GameOverEntity extends Entity {

    public GameOverEntity (Stage stage) {

        Skin skin = new Skin(Gdx.files.internal(Values.SKIN_PATH));

        Label label = new Label(Values.GAME_OVER, skin);

        add(new LabelComponent(stage, label));
        add(new GameStateComponent());

    }

}
