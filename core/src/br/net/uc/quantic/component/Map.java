package br.net.uc.quantic.component;

import com.badlogic.ashley.core.ComponentMapper;

public class Map {

    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<DimensionComponent> dimension = ComponentMapper.getFor(DimensionComponent.class);
    public static final ComponentMapper<SpriteBatchComponent> spriteBatch = ComponentMapper.getFor(SpriteBatchComponent.class);
    public static final ComponentMapper<CameraComponent> camera = ComponentMapper.getFor(CameraComponent.class);
    public static final ComponentMapper<SpriteComponent> sprite = ComponentMapper.getFor(SpriteComponent.class);
    public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);
    public static final ComponentMapper<RotationComponent> rotation = ComponentMapper.getFor(RotationComponent.class);
    public static final ComponentMapper<DirectionComponent> direction = ComponentMapper.getFor(DirectionComponent.class);
    public static final ComponentMapper<ButtonComponent> button = ComponentMapper.getFor(ButtonComponent.class);
    public static final ComponentMapper<OnOffComponent> onOff = ComponentMapper.getFor(OnOffComponent.class);
    public static final ComponentMapper<GameStateComponent> gameState = ComponentMapper.getFor(GameStateComponent.class);
    public static final ComponentMapper<WinningComponent> winning = ComponentMapper.getFor(WinningComponent.class);

}
