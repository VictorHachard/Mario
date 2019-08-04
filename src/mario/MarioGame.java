package mario;

import gameengine.app.GameApp;
import gameengine.app.GameSetting;
import gameengine.entities.GameObject;
import gameengine.entities.builder.BuilderGameObject;
import gameengine.entities.builder.BuilderGameObjectFactory;
import gameengine.entities.builder.WrapperGameObject;
import gameengine.entities.texture.Texture;
import gameengine.input.UserEvent;
import gameengine.physic.Dimension3D;
import gameengine.physic.collision.aabb.AABB;
import gameengine.render.Camera;
import gameengine.world.Level;
import gameengine.world.builder.BuilderLevel;
import javafx.geometry.Point3D;
import javafx.scene.input.KeyCode;

public class MarioGame extends GameApp {

	GameObject gc;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void initCamera(Camera camera) {
		camera.setPosition(gc.getPosition());
		camera.setGameObjectBinded(gc);
		
	}

	@Override
	public void initParticle() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void initLevel() {
		Level l = BuilderLevel.buildLevel("test", "lvl1.txt", 1);		
		getGameWorld().setLevel(l);
		
	}

	@Override
	public void initInput() {
		getInput().addEvent(new UserEvent("ZoomOut") {
			@Override
			public void onUpdate() {
				getManager().getCamera().setZoom(getManager().getCamera().getZoom()-0.1);
			}
			@Override
			public void onEnd() {
			}
			@Override
			public void onBegin() {
				getManager().getCamera().setZoom(getManager().getCamera().getZoom()-0.1);
			}
		}, KeyCode.PAGE_DOWN);
		getInput().addEvent(new UserEvent("ZoomIn") {
			@Override
			public void onUpdate() {
				getManager().getCamera().setZoom(getManager().getCamera().getZoom()+0.1);
			}
			@Override
			public void onEnd() {
			}
			@Override
			public void onBegin() {
				getManager().getCamera().setZoom(getManager().getCamera().getZoom()+0.1);
			}
		}, KeyCode.PAGE_UP);
		getInput().addEvent(new UserEvent("Left") {
			@Override
			public void onUpdate() {
			}
			@Override
			public void onEnd() {
				gc.getVelocity().subtract(-0.05,0.0);
			}
			@Override
			public void onBegin() {
				gc.getVelocity().add(-0.05,0.0);
			}
		}, KeyCode.Q);
		getInput().addEvent(new UserEvent("Down") {
			@Override
			public void onUpdate() {
			}
			@Override
			public void onEnd() {
				gc.getVelocity().subtract(0.0,0.05);
			}
			@Override
			public void onBegin() {
				gc.getVelocity().add(0.0,0.05);
			}
		}, KeyCode.S);
		getInput().addEvent(new UserEvent("Right") {
			@Override
			public void onUpdate() {
			}
			@Override
			public void onEnd() {
				gc.getVelocity().subtract(0.05,0.0);
			}
			@Override
			public void onBegin() {
				gc.getVelocity().add(0.05,0.0);
			}
		}, KeyCode.D);
		getInput().addEvent(new UserEvent("Up") {
			@Override
			public void onUpdate() {
			}
			@Override
			public void onEnd() {
				gc.getVelocity().subtract(0.0,-0.05);
			}
			@Override
			public void onBegin() {
				gc.getVelocity().add(0.0,-0.05);
			}
		}, KeyCode.Z);
		
	}

	@Override
	public void initCollision() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initUI() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void initGameObject() {
		Dimension3D d = new Dimension3D(32.0, 32.0,32.0);
		gc = BuilderGameObject.createGameObject()
				.with(d)
				.with(new Texture("bonjour"))
				.is("personnage")
				.with(4.0)
				.with(new AABB(new Point3D(0.0, 0.0, 0.0),d));
		BuilderLevel.addWrapperGameObject(new WrapperGameObject("3",new BuilderGameObjectFactory() {
			@Override
			public GameObject createGameObject() {
				return gc;
			}
		}));
	}

	@Override
	public void initSetting(GameSetting setting) {
		setting.setWidth(1720);
		setting.setHeight(880);
		setting.setTitle("Hello World!");
		setting.setDebugWindows();
	}
	
}