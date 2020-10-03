package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

import br.net.uc.quantic.component.CameraComponent;
import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.SpriteBatchComponent;
import br.net.uc.quantic.component.SpriteComponent;
import br.net.uc.quantic.component.VelocityComponent;

public class RenderSystem extends EntitySystem {

    private ImmutableArray<Entity> sprites;
    private ImmutableArray<Entity> worlds;
    private ImmutableArray<Entity> cameras;

    public RenderSystem() {}

    @Override
    public void addedToEngine(Engine engine) {

        // Carregamos tudo que precisamos para as sprites (posição, imagem e dimensão)
        this.sprites = engine.getEntitiesFor(Family.all(PositionComponent.class, SpriteComponent.class, DimensionComponent.class).get());

        // Carregamos o que precisamos para os mundos (algo onde desenhar - batch - e um tamanho para o "quadro")
        this.worlds = engine.getEntitiesFor(Family.all(SpriteBatchComponent.class, DimensionComponent.class).get());

        // Só temos a terra com camera, mas recupera tudo que tem componente camera aqui
        this.cameras = engine.getEntitiesFor(Family.all(CameraComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {

        // Armazenará os componentes de câmera do sistema
        CameraComponent cameraComponent;

    }
}
