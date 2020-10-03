package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import br.net.uc.quantic.component.LabelFieldComponent;
import br.net.uc.quantic.component.Map;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.TextComponent;
import br.net.uc.quantic.component.TextFieldComponent;
import br.net.uc.quantic.entity.EarthEntity;
import br.net.uc.quantic.utils.Values;

public class LabelProcessingSystem extends EntitySystem {

    ImmutableArray<Entity> labels;
    ImmutableArray<Entity> inputTexts;

    @Override
    public void addedToEngine(Engine engine) {

        this.labels = engine.getEntitiesFor(Family.all(LabelFieldComponent.class, PositionComponent.class).get());
        this.inputTexts = engine.getEntitiesFor(Family.all(TextFieldComponent.class, TextComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {

        Entity earth = this.inputTexts.first();

        TextComponent earthText = Map.text.get(earth);

        Values.log("Texto no input: " + earthText.text);

        for (Entity labelEntity : this.labels) {

            LabelFieldComponent labelFieldComponent = Map.label.get(labelEntity);
            PositionComponent labelPositionComponent = Map.position.get(labelEntity);

            labelFieldComponent.label.setText(earthText.text);
            labelFieldComponent.label.setPosition(labelPositionComponent.x, labelPositionComponent.y);

            Values.log("Text in label: " + labelFieldComponent.label.getText());

        }

    }
}
