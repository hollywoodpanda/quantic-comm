package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;

import br.net.uc.quantic.component.CameraComponent;
import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.DirectionComponent;
import br.net.uc.quantic.component.Map;
import br.net.uc.quantic.component.OnOffComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.VelocityComponent;


public class RayMessageSystem extends EntitySystem implements Listener<Boolean> {

    private ImmutableArray<Entity> messages;
    private ImmutableArray<Entity> earth;

    private Signal<Boolean> signal;

    public RayMessageSystem (Signal<Boolean> signal) {
        this.signal = signal;
        this.signal.add(this);
    }

    @Override
    public void addedToEngine(Engine engine) {

        this.messages = engine.getEntitiesFor(Family.all(OnOffComponent.class).get());
        // Terra é a única que tem câmera
        this.earth = engine.getEntitiesFor(Family.one(CameraComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void receive(Signal<Boolean> signal, Boolean object) {

        Entity messageEntity = messages.first();

        OnOffComponent messageOnOff = Map.onOff.get(messageEntity);

        if (! messageOnOff.isOn) {

            PositionComponent messagePosition = Map.position.get(messageEntity);
            DirectionComponent messageDirection = Map.direction.get(messageEntity);
            VelocityComponent messageVelocity = Map.velocity.get(messageEntity);

            Entity earthEntity = earth.first();
            PositionComponent earthsPosition = Map.position.get(earthEntity);
            DimensionComponent earthsDimension = Map.dimension.get(earthEntity);

            messagePosition.x = (earthsPosition.x + (earthsDimension.width / 2));
            messagePosition.y = earthsPosition.y + (earthsDimension.height / 2);

            messageDirection.x = 1200;
            messageDirection.y = 1200;

            messageVelocity.x = 100;
            messageVelocity.y = 100;

            messageOnOff.isOn = true;
        }

    }
}
