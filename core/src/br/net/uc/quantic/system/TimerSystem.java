package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import br.net.uc.quantic.component.GameStateComponent;
import br.net.uc.quantic.component.LabelComponent;
import br.net.uc.quantic.component.Map;
import br.net.uc.quantic.component.TimerComponent;
import br.net.uc.quantic.utils.Values;

/**
 * Responsável por atualizar o timer do jogo.
 */
public class TimerSystem extends EntitySystem {

    private ImmutableArray<Entity> timers;

    private ImmutableArray<Entity> labels;

    private ImmutableArray<Entity> gameStates;

    @Override
    public void addedToEngine(Engine engine) {

        this.timers = engine.getEntitiesFor(Family.one(TimerComponent.class).get());
        this.labels = engine.getEntitiesFor(Family.one(LabelComponent.class).get());
        this.gameStates = engine.getEntitiesFor(Family.one(GameStateComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {

        Entity timerEntity = this.timers.first();
        TimerComponent timerComponent = Map.timer.get(timerEntity);

        Entity labelEntity = this.labels.first();
        LabelComponent labelComponent = Map.label.get(labelEntity);

        timerComponent.time += Values.DEFAULT_LEVEL_TIME;
        labelComponent.label.setText("" + Math.abs(timerComponent.time / 1000f));

    }
}
