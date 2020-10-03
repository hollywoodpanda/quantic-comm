package br.net.uc.quantic.component;

import com.badlogic.ashley.core.Component;

/**
 * Dá dimensão (tamanho a algo)
 */
public class DimensionComponent implements Component {

    /**
     * Largura
     */
    public float width;

    /**
     * Altura
     */
    public float height;

    /**
     * Criamos dimensão (1, 1)
     */
    public DimensionComponent () {

        this.height = 1f;
        this.width = 1f;

    }

    /**
     * Constrói dimensão com largura / altura informada
     *
     * @param width A largura
     * @param height A altura
     */
    public DimensionComponent (float width, float height) {

        this.width = width;
        this.height = height;

    }

}
