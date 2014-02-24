package com.jtsay.flappybird;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class World {
	public interface WorldListener {
		public void jump();

		public void crash();

		public void score();
	}
	
	public static final Vector2 GRAVITY = new Vector2(0, -950);
	public static final int WIDTH = 320;
	public static final int HEIGHT = 480;
	public static final float PIPE_SPACING = 128+Pipe.WIDTH;
	public static final int PIPE_VELOCITY = 100;
	public static final float MIN_PIPE_POS_Y = HEIGHT/4;
	public static final float MAX_PIPE_POS_Y = 3*HEIGHT/4;
	public final Bird bird;
	public final List<PipeSet> pipeSets;
	public final Rectangle ground = new Rectangle(0,0,WIDTH,83);
	public WorldState state;
	private static Random rand = new Random();
	
	public World(WorldListener listener) {
		bird = new Bird(WIDTH/4, HEIGHT/2);
		bird.velocity.y = Bird.JUMP_VELOCITY;
		pipeSets = new ArrayList<PipeSet>(3);
		pipeSets.add(new PipeSet(WIDTH*3/4 + 100, getRandomPipePosY()));
		pipeSets.add(new PipeSet(WIDTH*3/4 + 100 + PIPE_SPACING, getRandomPipePosY()));
		pipeSets.add(new PipeSet(WIDTH*3/4 + 100 + 2*PIPE_SPACING, getRandomPipePosY()));
		state = WorldState.RUNNING;
	}
	
	public void reset() {
		state = WorldState.RUNNING;
		bird.reset(WIDTH/4, HEIGHT/2);
		pipeSets.get(0).reset(WIDTH*3/4 + 100, getRandomPipePosY());
		pipeSets.get(1).reset(WIDTH*3/4 + 100 + PIPE_SPACING, getRandomPipePosY());
		pipeSets.get(2).reset(WIDTH*3/4 + 100 + 2*PIPE_SPACING, getRandomPipePosY());
	}
	
	public void update(float deltaTime, boolean isTouched) {
		updateBird(deltaTime, isTouched);
		updatePipes(deltaTime);
		checkCollision();
	}
	
	private void updateBird(float deltaTime, boolean justTouched) {
		bird.velocity.add(GRAVITY.x*deltaTime, GRAVITY.y*deltaTime);
		if (justTouched) {
			bird.velocity.y = Bird.JUMP_VELOCITY;
		}
		bird.move(bird.velocity.x * deltaTime, bird.velocity.y * deltaTime);
	}
	
	private void updatePipes(float deltaTime) {
		for (PipeSet pipeSet : pipeSets) {
			pipeSet.moveLeft(deltaTime * PIPE_VELOCITY);
			if (pipeSet.getX() <= -32) {
				pipeSet.reset(3*PIPE_SPACING-Pipe.WIDTH/2, getRandomPipePosY());
			}
		}
	}
	
	private void checkCollision() {
		for (PipeSet pipeSet : pipeSets) {
			if (bird.bounds.overlaps(pipeSet.topPipe.bounds) ||
					bird.bounds.overlaps(pipeSet.bottomPipe.bounds) ||
					bird.bounds.overlaps(ground)) {
				bird.isHit();
				state = WorldState.PAUSED;
			}
		}
	}
	
	private float getRandomPipePosY() {
		return rand.nextFloat() * (MAX_PIPE_POS_Y - MIN_PIPE_POS_Y) + MIN_PIPE_POS_Y;
	}
	
}
