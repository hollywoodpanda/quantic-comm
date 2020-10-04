package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import br.net.uc.quantic.component.ButtonComponent;
import br.net.uc.quantic.component.CameraComponent;
import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.DirectionComponent;
import br.net.uc.quantic.component.Map;
import br.net.uc.quantic.component.OnOffComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.SpriteBatchComponent;
import br.net.uc.quantic.component.SpriteComponent;
import br.net.uc.quantic.component.VelocityComponent;
import br.net.uc.quantic.utils.Values;

public class RenderSystem extends EntitySystem {

    private ImmutableArray<Entity> sprites;
    private ImmutableArray<Entity> worlds;
    private ImmutableArray<Entity> cameras;
    private ImmutableArray<Entity> buttons;
    private ImmutableArray<Entity> messages;

    public RenderSystem() {}

    @Override
    public void addedToEngine(Engine engine) {

        // Carregamos tudo que precisamos para as sprites (posição, imagem e dimensão)
        this.sprites = engine.getEntitiesFor(Family.all(PositionComponent.class, SpriteComponent.class, DimensionComponent.class).exclude(OnOffComponent.class).get());

        // Carregamos a mensagem!
        this.messages = engine.getEntitiesFor(Family.all(PositionComponent.class, SpriteComponent.class, DimensionComponent.class, OnOffComponent.class).get());

        // Carregamos o que precisamos para os mundos (algo onde desenhar - batch - e um tamanho para o "quadro")
        this.worlds = engine.getEntitiesFor(Family.all(SpriteBatchComponent.class, DimensionComponent.class).get());

        // Só temos a terra com camera, mas recupera tudo que tem componente camera aqui
        this.cameras = engine.getEntitiesFor(Family.all(CameraComponent.class).get());

        this.buttons = engine.getEntitiesFor(Family.all(ButtonComponent.class, DimensionComponent.class, PositionComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {

        // Armazenará os componentes de câmera do sistema
        CameraComponent cameraComponent;

        // O tamanho da viewport da câmera
        DimensionComponent cameraDimensionComponent;

        // O batch para desenharmos as sprites
        SpriteBatchComponent spriteBatchComponent;

        // A posição de cada sprite
        PositionComponent spritePositionComponent;
        // Cada sprite (imagem / textura)
        SpriteComponent spriteComponent;
        // A dimensão de cada sprite
        DimensionComponent spriteDimensionComponent;

        // Para cada entidade de câmera...
        for (Entity cameraEntity : cameras) {

            // Pegamos o componente de câmera da entidade...
            cameraComponent = Map.camera.get(cameraEntity);

            // Atualizamos a câmera
            cameraComponent.camera.update();

            // Para cada mundo...
            for (Entity worldEntity : worlds) {

                // Pegamos o spriteBatch para desenharmos...
                spriteBatchComponent = Map.spriteBatch.get(worldEntity);

                // Iniciamos o desenho!
                spriteBatchComponent.batch.begin();

                spriteBatchComponent.batch.enableBlending();

                // Projeção da câmera no batch (???)
                spriteBatchComponent.batch.setProjectionMatrix(cameraComponent.camera.combined);

                for (Entity spriteEntity : sprites) {

                    // Os componentes da sprite...
                    spriteComponent = Map.sprite.get(spriteEntity);
                    spritePositionComponent = Map.position.get(spriteEntity);
                    spriteDimensionComponent = Map.dimension.get(spriteEntity);

                    // Definindo posição / tamanho da sprite
                    spriteComponent.value.setBounds(
                            spritePositionComponent.x,
                            spritePositionComponent.y,
                            spriteDimensionComponent.width,
                            spriteDimensionComponent.height);

                    spriteComponent.value.draw(spriteBatchComponent.batch);

                }

                Entity messageEntity = messages.first();

                PositionComponent messagePosition = Map.position.get(messageEntity);
                DimensionComponent messageSize = Map.dimension.get(messageEntity);
                SpriteComponent messageSprite = Map.sprite.get(messageEntity);
                OnOffComponent messageIsOn = Map.onOff.get(messageEntity);

                if (messageIsOn.isOn) {

                    messageSprite.value.setSize(messageSize.width, messageSize.height);
                    messageSprite.value.setPosition(messagePosition.x, messagePosition.y);

                    messageSprite.value.draw(spriteBatchComponent.batch);

                }

                for (Entity button : buttons) {

                    PositionComponent buttonPosition = Map.position.get(button);
                    ButtonComponent buttonComponent = Map.button.get(button);
                    DimensionComponent buttonDimension = Map.dimension.get(button);

                    buttonComponent.button.setPosition(buttonPosition.x, buttonPosition.y);
                    buttonComponent.button.setSize(buttonDimension.width, buttonDimension.height);

                    buttonComponent.button.draw(spriteBatchComponent.batch, Values.PARENT_ALPHA);

                    buttonComponent.stage.act();
                    buttonComponent.stage.draw();

                }

                spriteBatchComponent.batch.end();

            }

        }

    }
}
