package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import br.net.uc.quantic.component.ButtonComponent;
import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.utils.Values;

public class SendEntity extends Entity {

    public SendEntity (final Stage stage, final Signal<Boolean> signal) {

        Skin skin = new Skin(Gdx.files.internal(Values.SKIN_PATH));

        TextButton button = new TextButton(Values.BUTTON_TITLE, skin);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();

        button.setColor(Color.GREEN);
        button.setSize(300, 50);
        button.setPosition(300, 10);

        button.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {

                Values.log("Clicou no botão. Mandando signal true");

                signal.dispatch(true);

            }

        });

        // TODO: Associar o evento de clique no botão com o disparo
        // TODO: ... de outra entidade :scream:

        stage.addActor(button);
        Gdx.input.setInputProcessor(stage);

        add(new PositionComponent(200, 10));
        add(new DimensionComponent(220, 50));
        add(new ButtonComponent(stage, button));

    }

}
