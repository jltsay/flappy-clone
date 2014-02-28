package com.jtsay.flappybird.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jtsay.flappybird.World;
import com.jtsay.flappybird.gameobjects.Bird;
import com.jtsay.flappybird.gameobjects.Pipe;
import com.jtsay.flappybird.gameobjects.PipeSet;
import com.jtsay.flappybird.gameobjects.Ground;

public class WorldRenderer {

	World world;
	OrthographicCamera cam;
	public static final float CAM_WIDTH = 320;
	public static final float CAM_HEIGHT = 480;
	SpriteBatch batch;
	TextureRegion background;
	
	public WorldRenderer(SpriteBatch batch, World world) {
		this.batch = batch;
		this.world = world;
		this.cam = new OrthographicCamera(CAM_WIDTH, CAM_HEIGHT);
		this.cam.position.set(CAM_WIDTH/2, CAM_HEIGHT/2, 0);
		this.cam.update();
	}
	
	public void render() {
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderObjects();
	}
	
	private void renderBackground() {
		batch.disableBlending();
		batch.begin();
		batch.draw(Assets.backgroundRegion, cam.position.x - CAM_WIDTH/2, cam.position.y - CAM_HEIGHT/2, CAM_WIDTH,
				CAM_HEIGHT);
		batch.end();
	}
	
	private void renderObjects() {
		batch.enableBlending();
		batch.begin();
		renderBird();
		renderPipes();
		renderTopSoil();
		batch.end();
	}
	
	private void renderBird() {
		batch.draw(Assets.bird, world.bird.position.x - Bird.WIDTH/2, world.bird.position.y - Bird.HEIGHT/2, Bird.WIDTH, Bird.HEIGHT);
		
	}
	
	private void renderPipes() {
		for (PipeSet pipeSet : world.pipeSets) {
			batch.draw(Assets.pipe, pipeSet.getX() - Pipe.WIDTH/2, pipeSet.topPipe.position.y-Pipe.HEIGHT/2);
			batch.draw(Assets.pipe, pipeSet.getX() - Pipe.WIDTH/2, pipeSet.bottomPipe.position.y - Pipe.HEIGHT/2);
		}
	}
	
	private void renderTopSoil() {
		for (Ground ground : world.grounds) {
			batch.draw(Assets.ground, ground.position.x - Ground.WIDTH/2, ground.position.y-Ground.HEIGHT/2);
		}
	}
	
	
	
}
