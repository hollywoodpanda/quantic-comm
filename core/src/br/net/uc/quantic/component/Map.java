package br.net.uc.quantic.component;

import com.badlogic.ashley.core.ComponentMapper;

import javax.swing.text.Position;

public class Map {

    public static final ComponentMapper<PositionComponent> position = ComponentMapper.getFor(PositionComponent.class);
    public static final ComponentMapper<DimensionComponent> dimension = ComponentMapper.getFor(DimensionComponent.class);
    public static final ComponentMapper<SpriteBatchComponent> spriteBatch = ComponentMapper.getFor(SpriteBatchComponent.class);
    public static final ComponentMapper<CameraComponent> camera = ComponentMapper.getFor(CameraComponent.class);
    public static final ComponentMapper<SpriteComponent> sprite = ComponentMapper.getFor(SpriteComponent.class);
    public static final ComponentMapper<VelocityComponent> velocity = ComponentMapper.getFor(VelocityComponent.class);

}
