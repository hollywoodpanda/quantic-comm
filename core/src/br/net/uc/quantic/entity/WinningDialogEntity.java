package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.GameStateComponent;
import br.net.uc.quantic.component.ModalComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.utils.Values;

public class WinningDialogEntity extends Entity {

    public WinningDialogEntity (Stage stage) {

        Skin skin = new Skin(Gdx.files.internal(Values.SKIN_PATH));

        Dialog dialog = new  Dialog("VITORIA", skin, "dialog") {

            public void result (Object obj) {

                Values.log("RESULTADO DO DIALOGO");

            }

        };

        dialog.text("A MENSAGEM FOI ENVIADA");
        dialog.button("Ok", true);
        dialog.key(Input.Keys.ENTER, true);
        dialog.setModal(true);

        add(new ModalComponent(stage, dialog));
        add(new GameStateComponent());
        // TODO: Ajustar posição do diálogo
        add(new PositionComponent(0, 0));
        // TODO: Remover. Não estamos calculando...
        add(new DimensionComponent(500, 500));

    }

}
