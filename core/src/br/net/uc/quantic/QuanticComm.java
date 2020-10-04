package br.net.uc.quantic;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.signals.Signal;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;

import br.net.uc.quantic.entity.EarthEntity;
import br.net.uc.quantic.entity.GameOverEntity;
import br.net.uc.quantic.entity.MarsEntity;
import br.net.uc.quantic.entity.MessageEntity;
import br.net.uc.quantic.entity.SendEntity;
import br.net.uc.quantic.entity.SpaceBackgroundEntity;
import br.net.uc.quantic.entity.WinningDialogEntity;
import br.net.uc.quantic.entity.WorldEntity;
import br.net.uc.quantic.system.CollisionSystem;
import br.net.uc.quantic.system.LevelFinishSystem;
import br.net.uc.quantic.system.MovementSystem;
import br.net.uc.quantic.system.RayMessageSystem;
import br.net.uc.quantic.system.RenderSystem;
import br.net.uc.quantic.system.RotationSystem;
import br.net.uc.quantic.system.TimerSystem;

public class QuanticComm extends ApplicationAdapter {

	Signal<Boolean> sendMessageSignal;

	Engine engine;

	EarthEntity earth;
	MarsEntity mars;

	SendEntity buttonSend;

	MessageEntity message;

	WorldEntity world;

	SpaceBackgroundEntity spaceBackground;

	WinningDialogEntity winningDialog;

	GameOverEntity gameOver;

	RenderSystem renderSystem;

	RotationSystem rotationSystem;

	RayMessageSystem rayMessageSystem;

	MovementSystem movementSystem;

	CollisionSystem collisionSystem;

	LevelFinishSystem levelFinishSystem;

	TimerSystem timerSystem;

	@Override
	public void create () {

		Stage stage = new Stage();

		engine = new Engine();

		sendMessageSignal = new Signal<>();

		spaceBackground = new SpaceBackgroundEntity();
		engine.addEntity(spaceBackground);

		mars = new MarsEntity();
		engine.addEntity(mars);

		earth = new EarthEntity();
		engine.addEntity(earth);

		buttonSend = new SendEntity(stage, sendMessageSignal);
		engine.addEntity(buttonSend);

		message = new MessageEntity();
		engine.addEntity(message);

		world = new WorldEntity(stage);
		engine.addEntity(world);

		winningDialog = new WinningDialogEntity(stage);
		engine.addEntity(winningDialog);

		gameOver = new GameOverEntity(stage);
		engine.addEntity(gameOver);

		renderSystem = new RenderSystem();
		engine.addSystem(renderSystem);
		renderSystem.setProcessing(true);

		rotationSystem = new RotationSystem();
		engine.addSystem(rotationSystem);
		rotationSystem.setProcessing(true);

		rayMessageSystem = new RayMessageSystem(sendMessageSignal);
		engine.addSystem(rayMessageSystem);
		rayMessageSystem.setProcessing(true);

		movementSystem = new MovementSystem();
		engine.addSystem(movementSystem);
		movementSystem.setProcessing(true);

		collisionSystem = new CollisionSystem();
		engine.addSystem(collisionSystem);
		collisionSystem.setProcessing(true);

		levelFinishSystem = new LevelFinishSystem();
		engine.addSystem(levelFinishSystem);
		levelFinishSystem.setProcessing(true);

		timerSystem = new TimerSystem();
		engine.addSystem(timerSystem);
		timerSystem.setProcessing(true);

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
