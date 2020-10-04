package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

import br.net.uc.quantic.component.CameraComponent;
import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.DirectionComponent;
import br.net.uc.quantic.component.Map;
import br.net.uc.quantic.component.OnOffComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.RotationComponent;
import br.net.uc.quantic.component.SpriteBatchComponent;
import br.net.uc.quantic.component.SpriteComponent;
import br.net.uc.quantic.component.VelocityComponent;
import br.net.uc.quantic.utils.Values;


public class RayMessageSystem extends EntitySystem implements Listener<Boolean> {

    private ImmutableArray<Entity> messages;
    private ImmutableArray<Entity> earth;
    private ImmutableArray<Entity> world;

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

        this.world = engine.getEntitiesFor(Family.all(SpriteBatchComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void receive(Signal<Boolean> signal, Boolean object) {

        Entity messageEntity = messages.first();

        OnOffComponent messageOnOff = Map.onOff.get(messageEntity);

        // Se mensagem não está viva....
        if (! messageOnOff.isOn) {

            PositionComponent messagePosition = Map.position.get(messageEntity);
            VelocityComponent messageVelocity = Map.velocity.get(messageEntity);

            Entity earthEntity = earth.first();
            this.positionMessageOnTopOfEarthV2(earthEntity, messageEntity);

            messageOnOff.isOn = true;

        }

    }

    private void positionMessageOnTopOfEarthV2 (Entity earth, Entity message) {

        SpriteComponent earthSprite = Map.sprite.get(earth);

        PositionComponent messagePosition = Map.position.get(message);

        float x = earthSprite.value.getVertices()[SpriteBatch.X2];
        float y = earthSprite.value.getVertices()[SpriteBatch.Y2];

        messagePosition.x = x;
        messagePosition.y = y;

    }

}
