package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import br.net.uc.quantic.component.Map;
import br.net.uc.quantic.component.TextComponent;
import br.net.uc.quantic.component.TextFieldComponent;

public class InputProcessingSystem extends EntitySystem {

    ImmutableArray<Entity> typingEntities;

    @Override
    public void addedToEngine(Engine engine) {

        this.typingEntities = engine.getEntitiesFor(Family.all(TextFieldComponent.class, TextComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {

        for (Entity typingEntity : this.typingEntities) {

            TextFieldComponent textFieldComponent = Map.textField.get(typingEntity);
            TextComponent textComponent = Map.text.get(typingEntity);

            textComponent.text = textFieldComponent.textField.getText();

        }

    }
}
