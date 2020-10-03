package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import java.awt.TextField;

import br.net.uc.quantic.component.Map;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.StageComponent;
import br.net.uc.quantic.component.TextComponent;
import br.net.uc.quantic.component.TextFieldComponent;
import br.net.uc.quantic.entity.TypingEntity;

public class InputProcessingSystem extends EntitySystem {

    ImmutableArray<Entity> typingEntities;

    @Override
    public void addedToEngine(Engine engine) {

        this.typingEntities = engine.getEntitiesFor(Family.all(StageComponent.class, TextComponent.class, TextFieldComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {

        for (Entity typingEntity : this.typingEntities) {

            StageComponent stageComponent = Map.stage.get(typingEntity);

            stageComponent.stage.draw();
            stageComponent.stage.act();

        }

    }
}
