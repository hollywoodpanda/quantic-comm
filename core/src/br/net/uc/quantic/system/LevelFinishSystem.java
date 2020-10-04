package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import br.net.uc.quantic.component.GameStateComponent;
import br.net.uc.quantic.component.LabelComponent;
import br.net.uc.quantic.component.Map;
import br.net.uc.quantic.component.ModalComponent;
import br.net.uc.quantic.component.SpriteBatchComponent;
import br.net.uc.quantic.component.TimerComponent;
import br.net.uc.quantic.utils.Values;

public class LevelFinishSystem extends EntitySystem {

    private ImmutableArray<Entity> winningDialogs;

    private ImmutableArray<Entity> timerLabels;

    private ImmutableArray<Entity> gameStates;

    private ImmutableArray<Entity> gameOvers;

    private ImmutableArray<Entity> spriteBatches;

    public LevelFinishSystem () {}

    @Override
    public void addedToEngine(Engine engine) {

        this.winningDialogs = engine.getEntitiesFor(Family.all(GameStateComponent.class, ModalComponent.class).get());
        this.timerLabels = engine.getEntitiesFor(Family.all(TimerComponent.class, LabelComponent.class).get());
        this.gameStates = engine.getEntitiesFor(Family.one(GameStateComponent.class).get());
        this.gameOvers = engine.getEntitiesFor(Family.one(LabelComponent.class, GameStateComponent.class).get());
        this.spriteBatches = engine.getEntitiesFor(Family.one(SpriteBatchComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {

            Entity winningDialog = this.winningDialogs.first();

            GameStateComponent gameState = Map.gameState.get(winningDialog);

            ModalComponent modal = Map.modal.get(winningDialog);

            if (gameState.state == GameStateComponent.GameState.WIN) {

                modal.stage.addActor(modal.dialog);

                modal.dialog.show(modal.stage);

                modal.stage.act();
                modal.stage.draw();

            }

            // TODO: Validar se timer acabou
            Entity timerLabelEntity = timerLabels.first();

            TimerComponent timerComponent = Map.timer.get(timerLabelEntity);
            LabelComponent labelComponent = Map.label.get(timerLabelEntity);

            // Acabou o tempo!!!
            if (timerComponent.time > Values.DEFAULT_LEVEL_TIME) {

                Values.log("YOU LOOSE");

                for (Entity gameStateEntity : gameStates) {

                    GameStateComponent gameStateComponent = Map.gameState.get(gameStateEntity);

                    gameStateComponent.state = GameStateComponent.GameState.LOOSE;

                }

                for (Entity gameOverEntity : gameOvers) {

                    LabelComponent gameOverLabel = Map.label.get(gameOverEntity);

                    for (Entity spriteBatchEntity : spriteBatches) {



                    }

                }

            }



    }

}
