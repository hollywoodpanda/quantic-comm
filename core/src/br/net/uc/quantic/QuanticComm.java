package br.net.uc.quantic;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import br.net.uc.quantic.entity.EarthEntity;
import br.net.uc.quantic.entity.MarsEntity;
import br.net.uc.quantic.entity.WorldEntity;
import br.net.uc.quantic.system.InputProcessingSystem;
import br.net.uc.quantic.system.LabelProcessingSystem;
import br.net.uc.quantic.system.RenderSystem;

public class QuanticComm extends ApplicationAdapter {

	Engine engine;

	EarthEntity earth;
	MarsEntity mars;

	WorldEntity world;

	RenderSystem renderSystem;

	InputProcessingSystem inputProcessingSystem;

	LabelProcessingSystem labelProcessingSystem;
	
	@Override
	public void create () {

		engine = new Engine();

		earth = new EarthEntity();
		engine.addEntity(earth);

		mars = new MarsEntity();
		engine.addEntity(mars);

		world = new WorldEntity();
		engine.addEntity(world);

		renderSystem = new RenderSystem();
		engine.addSystem(renderSystem);
		renderSystem.setProcessing(true);

		inputProcessingSystem = new InputProcessingSystem();
		engine.addSystem(inputProcessingSystem);
		inputProcessingSystem.setProcessing(true);

		labelProcessingSystem = new LabelProcessingSystem();
		engine.addSystem(labelProcessingSystem);
		labelProcessingSystem.setProcessing(true);

	}

	@Override
	public void render () {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		engine.update(Gdx.graphics.getDeltaTime());


	}
	
	@Override
	public void dispose () {}
}
