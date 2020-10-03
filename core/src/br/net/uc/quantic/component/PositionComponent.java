package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;

import javax.swing.text.Position;

/**
 * Entidades com esse componente terão
 * posição no mundo
 */
public class PositionComponent implements Component {

    /**
     * X
     */
    public float x;

    /**
     * Y
     */
    public float y;

    /**
     * Inicia na posição (0, 0)
     */
    public PositionComponent() {

        this.x = 0f;

        this.y = 0f;

    }

    /**
     * Inicia na posição informada.
     * @param x posição em x
     * @param y posição em y
     */
    public PositionComponent (float x, float y) {

        this.x = x;
        this.y = y;

    }

}
