package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import br.net.uc.quantic.component.Map;
import br.net.uc.quantic.component.OnOffComponent;
import br.net.uc.quantic.component.RotationComponent;
import br.net.uc.quantic.component.SpriteComponent;

public class RotationSystem extends EntitySystem {

    private ImmutableArray<Entity> rotationable;

    @Override
    public void addedToEngine(Engine engine) {

        this.rotationable = engine.getEntitiesFor(Family.all(RotationComponent.class, SpriteComponent.class).exclude(OnOffComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {

        for (Entity rotationEntity : rotationable) {

            RotationComponent rotationComponent = Map.rotation.get(rotationEntity);
            SpriteComponent spriteComponent = Map.sprite.get(rotationEntity);

            spriteComponent.value.setOrigin(rotationComponent.x, rotationComponent.y);
            spriteComponent.value.rotate(rotationComponent.degrees);

        }

    }
}
