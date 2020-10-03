package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.StageComponent;
import br.net.uc.quantic.component.TextComponent;
import br.net.uc.quantic.component.TextFieldComponent;
import br.net.uc.quantic.utils.Values;

public class TypingEntity extends Entity {

    public TypingEntity() {

        Skin skin = new Skin(Gdx.files.internal(Values.SKIN_PATH));

        Stage stage = new Stage();

        TextField textField = new TextField("", skin);

        // TODO: Deveria iniciar o stage aqui mesmo???
        stage.addActor(textField);
        Gdx.input.setInputProcessor(stage);

        add(new StageComponent(stage));
        add(new TextFieldComponent(textField));
        add(new TextComponent(""));
        add(new PositionComponent(100, 10));
        add(new DimensionComponent(200, 20));

    }

}
