package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;

/**
 * Dá velocidade (vetorial - x, y) para algo
 */
public class VelocityComponent implements Component {

    /**
     * X do vetor
     */
    public float x;

    /**
     * Y do vetor
     */
    public float y;

    /**
     * Inicia velocidade "parado" :man_shrugging:
     */
    public VelocityComponent () {

        this.x = 0f;

        this.y = 0f;

    }

    /**
     * Inicia velocidade com as informações informadas
     * @param x Velocidade de x
     * @param y Velocidade de y
     */
    public VelocityComponent (float x , float y) {

        this.x = x;

        this.y = y;

    }

}
