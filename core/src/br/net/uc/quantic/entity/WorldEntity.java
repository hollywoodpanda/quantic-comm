package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;

import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.SpriteBatchComponent;

public class WorldEntity extends Entity {

    public WorldEntity () {

        add(new SpriteBatchComponent());
        add(new DimensionComponent(10000, 10000));

    }

}
