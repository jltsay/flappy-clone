package com.jtsay.flappybird;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	}
	
	public void render() {
		batch.setProjectionMatrix(cam.combined);
		renderBackground();
		renderBird();
	}
	
	private void renderBackground() {
		batch.disableBlending();
		batch.begin();
		batch.draw(Assets.backgroundRegion, cam.position.x - CAM_WIDTH / 2, cam.position.y - CAM_HEIGHT / 2, CAM_WIDTH,
				CAM_HEIGHT);
		batch.end();
	}
	
	private void renderObjects() {
		batch.enableBlending();
		batch.begin();
		renderBird();
		renderPipes();
		batch.end();
	}
	
	private void renderBird() {
		batch.draw(Assets.bird, world.bird.position.x, world.bird.position.y, 32, 21);
		
	}
	
	private void renderPipes() {
		
	}
	
	
	
}