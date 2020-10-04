package br.net.uc.quantic;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import br.net.uc.quantic.entity.EarthEntity;
import br.net.uc.quantic.entity.MarsEntity;
import br.net.uc.quantic.entity.MessageEntity;
import br.net.uc.quantic.entity.SendEntity;
import br.net.uc.quantic.entity.SpaceBackgroundEntity;
import br.net.uc.quantic.entity.WorldEntity;
import br.net.uc.quantic.system.RayMessageSystem;
import br.net.uc.quantic.system.RenderSystem;
import br.net.uc.quantic.system.RotationSystem;

public class QuanticComm extends ApplicationAdapter {

	Signal<Boolean> sendMessageSignal;

	Engine engine;

	EarthEntity earth;
	MarsEntity mars;

	SendEntity buttonSend;

	MessageEntity message;

	WorldEntity world;

	SpaceBackgroundEntity spaceBackground;

	RenderSystem renderSystem;

	RotationSystem rotationSystem;

	RayMessageSystem rayMessageSystem;
	
	@Override
	public void create () {

		engine = new Engine();

		sendMessageSignal = new Signal<>();

		spaceBackground = new SpaceBackgroundEntity();
		engine.addEntity(spaceBackground);

		mars = new MarsEntity();
		engine.addEntity(mars);

		earth = new EarthEntity();
		engine.addEntity(earth);

		buttonSend = new SendEntity(sendMessageSignal);
		engine.addEntity(buttonSend);

		message = new MessageEntity();
		engine.addEntity(message);

		world = new WorldEntity();
		engine.addEntity(world);

		renderSystem = new RenderSystem();
		engine.addSystem(renderSystem);
		renderSystem.setProcessing(true);

		rotationSystem = new RotationSystem();
		engine.addSystem(rotationSystem);
		rotationSystem.setProcessing(true);

		rayMessageSystem = new RayMessageSystem(sendMessageSignal);
		engine.addSystem(rayMessageSystem);
		rayMessageSystem.setProcessing(true);

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
