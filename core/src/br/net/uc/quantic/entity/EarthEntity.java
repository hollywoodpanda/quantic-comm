package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import br.net.uc.quantic.component.CameraComponent;
import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.SpriteComponent;
import br.net.uc.quantic.component.StageComponent;
import br.net.uc.quantic.component.TextComponent;
import br.net.uc.quantic.component.TextFieldComponent;
import br.net.uc.quantic.component.VelocityComponent;
import br.net.uc.quantic.utils.Values;

/**
 * TODO: CameraComponent???
 */
public class EarthEntity extends Entity {

    public EarthEntity () {

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Values.CAMERA_VIEWPORT_WIDTH, Values.CAMERA_VIEWPORT_HEIGHT);
        // TODO: Num sei se preciso disso aqui
        camera.update();

        // TODO: incluir textura do planeta terra!
        Sprite sprite = new Sprite(new Texture("badlogic.jpg"));

        add(new PositionComponent(30, 10));
        add(new VelocityComponent());
        add(new SpriteComponent(sprite));
        // Terra deve ser bem maior que marte!
        add(new DimensionComponent(100, 100));
        add(new CameraComponent(camera));

        this.initInputText();

    }

    private void initInputText () {

        Skin skin = new Skin(Gdx.files.internal(Values.SKIN_PATH));

        Stage stage = new Stage();

        TextField textField = new TextField("", skin);
        textField.setSize(100, 20);

        // TODO: Deveria iniciar o stage aqui mesmo???
        stage.addActor(textField);
        Gdx.input.setInputProcessor(stage);

        add(new StageComponent(stage));
        add(new TextComponent(""));
        add(new TextFieldComponent(textField));

    }

}

