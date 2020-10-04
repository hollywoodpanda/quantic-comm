package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.signals.Listener;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import br.net.uc.quantic.component.CameraComponent;
import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.Map;
import br.net.uc.quantic.component.OnOffComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.SpriteBatchComponent;
import br.net.uc.quantic.component.SpriteComponent;
import br.net.uc.quantic.component.VelocityComponent;
import br.net.uc.quantic.entity.EarthEntity;
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

        // TODO: Vamos avaliar se mensagem está na viewport ainda ou não. Se não
        // TODO: estiver, modificamos o isOn dela para false

        Entity messageEntity = messages.first();

        OnOffComponent onOffMessage = Map.onOff.get(messageEntity);



        if (onOffMessage.isOn) {

            Entity earthEntity = earth.first();
            CameraComponent cameraComponent = Map.camera.get(earthEntity);

            // 1. Pegar 4 pontos do retângulo da mensagem
            SpriteComponent messageSprite = Map.sprite.get(messageEntity);

            Vector2 bottomLeft = new Vector2(messageSprite.value.getVertices()[SpriteBatch.X1],messageSprite.value.getVertices()[SpriteBatch.Y1]);
            Vector2 topLeft = new Vector2(messageSprite.value.getVertices()[SpriteBatch.X2],messageSprite.value.getVertices()[SpriteBatch.Y2]);
            Vector2 topRight = new Vector2(messageSprite.value.getVertices()[SpriteBatch.X3],messageSprite.value.getVertices()[SpriteBatch.Y3]);
            Vector2 bottomRight = new Vector2(messageSprite.value.getVertices()[SpriteBatch.X4],messageSprite.value.getVertices()[SpriteBatch.Y4]);

            onOffMessage.isOn =
                    cameraComponent.camera.frustum.pointInFrustum(new Vector3(bottomLeft.x, bottomLeft.y, 0))
                            && cameraComponent.camera.frustum.pointInFrustum(new Vector3(topLeft.x, topLeft.y, 0))
                            && cameraComponent.camera.frustum.pointInFrustum(new Vector3(topRight.x, topRight.y, 0))
                            && cameraComponent.camera.frustum.pointInFrustum(new Vector3(bottomRight.x, bottomRight.y, 0));

        }

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

            // Posicionamos a mensagem...
            this.positionMessageOnTopOfEarth(earthEntity, messageEntity);

            // Calculamos a direção / velocidade
            this.calculateMessageDirection(earthEntity, messageEntity);

            messageOnOff.isOn = true;

        }

    }

    private void positionMessageOnTopOfEarth (Entity earth, Entity message) {

        SpriteComponent earthSprite = Map.sprite.get(earth);

        PositionComponent messagePosition = Map.position.get(message);

        // Pego o x do vértice no topo esquerdo do retângulo da Terra
        float x2 = earthSprite.value.getVertices()[SpriteBatch.X2];
        // Pego o y do vértice no topo esquerdo do retângulo da Terra
        float y2 = earthSprite.value.getVertices()[SpriteBatch.Y2];
        // Pego o x do vértice no topo direito do retângulo da Terra
        float x3 = earthSprite.value.getVertices()[SpriteBatch.X3];
        // Pego o y do vértice no topo direito do retângulo da Terra
        float y3 = earthSprite.value.getVertices()[SpriteBatch.Y3];

        // Calculo a média entre os dois vértices X e posiciono a mensagem inicialmente
        messagePosition.x = (x3 + x2) / 2;
        // Calculo a média entre os dois vértices Y e posiciono a mensagem inicialmente
        messagePosition.y = (y3 + y2) / 2;

    }

    private void calculateMessageDirection (Entity earth, Entity message) {

        VelocityComponent messageVelocity = Map.velocity.get(message);

        SpriteComponent earthSprite = Map.sprite.get(earth);

        //Diff entre 2 e 1
        float x1 = earthSprite.value.getVertices()[SpriteBatch.X2] - earthSprite.value.getVertices()[SpriteBatch.X1];
        float y1 = earthSprite.value.getVertices()[SpriteBatch.Y2] - earthSprite.value.getVertices()[SpriteBatch.Y1];

        messageVelocity.x = x1;
        messageVelocity.y = y1;

    }

}
