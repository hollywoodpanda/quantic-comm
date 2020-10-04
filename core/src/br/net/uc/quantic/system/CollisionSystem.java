package br.net.uc.quantic.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.GameStateComponent;
import br.net.uc.quantic.component.Map;
import br.net.uc.quantic.component.OnOffComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.RotationComponent;
import br.net.uc.quantic.component.SpriteComponent;
import br.net.uc.quantic.component.WinningComponent;
import br.net.uc.quantic.utils.Values;

/**
 * Sistema para controlar a colisão da mensagem
 * com outros objetos na cena do jogo.
 *
 * Os objetos possíveis seriam:
 *
 * 1. Mensagem colide com Marte
 * 2. Mensagem colide com algum astro ou no cordão de asteróides
 *
 */
public class CollisionSystem extends EntitySystem {

    private ImmutableArray<Entity> messages;
    // Is this the right way to spell multiple mars instances?
    private ImmutableArray<Entity> marses;

    private ImmutableArray<Entity> gameStates;

    @Override
    public void addedToEngine(Engine engine) {

        // TODO: Evoluir como pegamos exclusivamente a mensagem (não deveria ser por OnOffComponent)
        // Na verdade bastaria eu buscar os que tem OnOffComponent (visto que só mensagem possui esse componente).
        // Inclui os outros para ilustrar melhor o que vamos precisar da mensagem
        this.messages = engine.getEntitiesFor(Family.all(PositionComponent.class, SpriteComponent.class, DimensionComponent.class, OnOffComponent.class).get());

        // TODO: Evoluir a forma como identificamos marte exclusivamente - estamos usando o WinningComponent vazio!
        this.marses = engine.getEntitiesFor(Family.all(PositionComponent.class, SpriteComponent.class, DimensionComponent.class, RotationComponent.class, WinningComponent.class).get());

        this.gameStates = engine.getEntitiesFor(Family.one(GameStateComponent.class).get());

    }

    @Override
    public void update(float deltaTime) {

        Entity message = this.messages.first();

        Entity mars = this.marses.first();

        OnOffComponent onOff = Map.onOff.get(message);

        if (! onOff.isOn) {
            return;
        }

        // Vamos pegar os quatro vértices da mensagem
        SpriteComponent msgSprite = Map.sprite.get(message);

        Vector2 msgBottomLeft = new Vector2(msgSprite.value.getVertices()[SpriteBatch.X1], msgSprite.value.getVertices()[SpriteBatch.Y1]);
        Vector2 msgTopLeft = new Vector2(msgSprite.value.getVertices()[SpriteBatch.X2], msgSprite.value.getVertices()[SpriteBatch.Y2]);
        Vector2 msgTopRight = new Vector2(msgSprite.value.getVertices()[SpriteBatch.X3], msgSprite.value.getVertices()[SpriteBatch.Y3]);
        Vector2 msgBottomRight = new Vector2(msgSprite.value.getVertices()[SpriteBatch.X4], msgSprite.value.getVertices()[SpriteBatch.Y4]);

        // Pegando quatro vértices de marte
        SpriteComponent marsSprite = Map.sprite.get(mars);

        Vector2 marsBottomLeft = new Vector2(marsSprite.value.getVertices()[SpriteBatch.X1],marsSprite.value.getVertices()[SpriteBatch.Y1]);
        Vector2 marsTopLeft = new Vector2(marsSprite.value.getVertices()[SpriteBatch.X2],marsSprite.value.getVertices()[SpriteBatch.Y2]);
        Vector2 marsTopRight = new Vector2(marsSprite.value.getVertices()[SpriteBatch.X3],marsSprite.value.getVertices()[SpriteBatch.Y3]);
        Vector2 marsBottomRight = new Vector2(marsSprite.value.getVertices()[SpriteBatch.X4],marsSprite.value.getVertices()[SpriteBatch.Y4]);

        // Colisão de marte com a mensagem!!! Vitória :clap: :clap: :clap:
        if (msgSprite.value.getBoundingRectangle().overlaps(marsSprite.value.getBoundingRectangle())) {

            // TODO: Exibir um popup com mensagem de vitória e nota na fase
            Values.log("Vitóriaaaaa!!!");

            // Destruir mensagem

            onOff.isOn = false;

            // Informar vitória
            for (Entity gameStateEntity : gameStates) {

                GameStateComponent gameState = Map.gameState.get(gameStateEntity);
                gameState.state = GameStateComponent.GameState.WIN;

            }

        }

    }
}
