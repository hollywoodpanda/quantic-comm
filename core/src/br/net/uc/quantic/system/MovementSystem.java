package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import br.net.uc.quantic.component.Map;
import br.net.uc.quantic.component.OnOffComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.SpriteComponent;
import br.net.uc.quantic.component.VelocityComponent;

public class MovementSystem extends EntitySystem {

    private ImmutableArray<Entity> moveables;

    @Override
    public void addedToEngine(Engine engine) {

        this.moveables = engine.getEntitiesFor(Family.all(VelocityComponent.class, PositionComponent.class, SpriteComponent.class, OnOffComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {

        for (Entity movingEntity : moveables) {

            VelocityComponent velocity = Map.velocity.get(movingEntity);
            PositionComponent position = Map.position.get(movingEntity);

            position.x += (velocity.x * deltaTime) / 2;
            position.y += (velocity.y * deltaTime) / 2;

        }

    }
}
