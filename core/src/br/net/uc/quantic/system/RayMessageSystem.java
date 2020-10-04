package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.Gdx;
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

        if (! messageOnOff.isOn) {

            PositionComponent messagePosition = Map.position.get(messageEntity);
            //DirectionComponent messageDirection = Map.direction.get(messageEntity);
            VelocityComponent messageVelocity = Map.velocity.get(messageEntity);

            Entity earthEntity = earth.first();
//
            this.positionMessageOnTopOfEarthV2(earthEntity, messageEntity);

            messageOnOff.isOn = true;
        }

    }

    private void positionMessageOnTopOfEarthV2 (Entity earth, Entity message) {

        PositionComponent earthPosition = Map.position.get(earth);
        DimensionComponent earthDimension = Map.dimension.get(earth);
        RotationComponent earthRotation = Map.rotation.get(earth);
        SpriteComponent earthSprite = Map.sprite.get(earth);

        SpriteComponent messageSprite = Map.sprite.get(message);
        PositionComponent messagePosition = Map.position.get(message);
        VelocityComponent messageVelocity = Map.velocity.get(message);
        DimensionComponent messageDimension = Map.dimension.get(message);

        float x1 = (earthPosition.x + earthRotation.x);
        float y1 = (earthPosition.y + earthRotation.y);

        float x2 = (earthPosition.x + earthDimension.width) * MathUtils.cos(earthSprite.value.getRotation());
        float y2 = (earthPosition.y + earthDimension.height) * MathUtils.sin(earthSprite.value.getRotation());



//        // 2. Vamos ver... num deu tb!
//        messagePosition.x = earthSprite.value.getX() + earthSprite.value.getWidth();
//        messagePosition.y = earthSprite.value.getY() + earthSprite.value.getHeight();

    }

    private void positionMessageOnTopOfEarth (Entity earth, Entity message) {

        PositionComponent earthPosition = Map.position.get(earth);
        DimensionComponent earthDimension = Map.dimension.get(earth);
        RotationComponent earthRotation = Map.rotation.get(earth);
        SpriteComponent earthSprite = Map.sprite.get(earth);

        SpriteComponent messageSprite = Map.sprite.get(message);
        PositionComponent messagePosition = Map.position.get(message);
        VelocityComponent messageVelocity = Map.velocity.get(message);
        DimensionComponent messageDimension = Map.dimension.get(message);

        float currentDegrees = earthSprite.value.getRotation();
        float originX = earthSprite.value.getOriginX();
        float originY = earthSprite.value.getOriginY();

        float earthX = earthPosition.x + (earthDimension.width / 2);
        float earthY = earthPosition.y + (earthDimension.height / 2);

        float x = (earthX - originX) * MathUtils.sin(currentDegrees) + (earthY - originY) * MathUtils.cos(currentDegrees) + originX;

        // Essa solução da deepweb num tá funcionando (https://answers.unity.com/questions/1255801/find-point-of-top-of-rotated-sprite.html)
        float y = (earthY - originY) * MathUtils.cos(currentDegrees) + (earthX - originX) * MathUtils.sin(currentDegrees) + originY;

        messagePosition.x = earthX ;//+ (earthDimension.width / 2);
        messagePosition.y = earthY ;//+ (earthDimension.height / 2);

        Values.log("Segue: " + earthSprite.value.getVertices()[0]);
        Values.log("Segue: " + earthSprite.value.getVertices()[1]);

        float v1 = earthSprite.value.getVertices()[0];
        float v2 = earthSprite.value.getVertices()[1];


        float newX = (v2 + v1) / 2;

        messagePosition.x = earthSprite.value.getScaleX();



//        messagePosition.x = x;
//        messagePosition.y = y;

//        messageVelocity.x = 70;
//        messageVelocity.y = 70;

        //Values.log("Passei aqui!");

//        messageSprite.value.setOrigin((messageDimension.width / 2), (messageDimension.height / 2));
//        messageSprite.value.setRotation(currentDegrees);



//        messagePosition.x = messageDimension.width;
//        messagePosition.y = messageDimension.height;

    }

}
