package br.net.uc.quantic.entity;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import br.net.uc.quantic.component.CameraComponent;
import br.net.uc.quantic.component.DimensionComponent;
import br.net.uc.quantic.component.PositionComponent;
import br.net.uc.quantic.component.RotationComponent;
import br.net.uc.quantic.component.SpriteComponent;
import br.net.uc.quantic.component.VelocityComponent;
import br.net.uc.quantic.utils.Values;

/**
 * TODO: CameraComponent???
 */
public class EarthEntity extends Entity {

    public EarthEntity () {

        float x = 40;
        float y = 60;

        float width = 200;
        float height = 240;

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, Values.CAMERA_VIEWPORT_WIDTH, Values.CAMERA_VIEWPORT_HEIGHT);
        // TODO: Num sei se preciso disso aqui
        camera.update();

        // TODO: incluir textura do planeta terra!
        Sprite sprite = new Sprite(new Texture("earth.png"));

        add(new PositionComponent(x, y));
        add(new VelocityComponent());
        add(new SpriteComponent(sprite));
        // Terra deve ser bem maior que marte!
        add(new DimensionComponent(width, height));
        add(new CameraComponent(camera));
        add(new RotationComponent(width / 2, height / 2, 0.2f));

    }

}

