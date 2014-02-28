package com.jtsay.flappybird;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.bind.Marshaller.Listener;

import sun.util.logging.resources.logging;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.jtsay.flappybird.gameobjects.Bird;
import com.jtsay.flappybird.gameobjects.Ground;
import com.jtsay.flappybird.gameobjects.Pipe;
import com.jtsay.flappybird.gameobjects.PipeSet;
import com.jtsay.flappybird.gameobjects.SpaceBetweenPipes;

public class World {
	public interface WorldListener {
		public void jump();

		public void crash();

		public void score();
	}
	
	public static final Vector2 GRAVITY = new Vector2(0, -950);
	public static final float WIDTH = 320;
	public static final float HEIGHT = 480;
	public static final float PIPE_SPACING = 128+Pipe.WIDTH;
	public static final int PIPE_VELOCITY = 100;
	public static final float GROUND_HEIGHT = 80;
	public static final float MIN_PIPE_POS_Y = GROUND_HEIGHT + SpaceBetweenPipes.HEIGHT;
	public static final float MAX_PIPE_POS_Y = 3*HEIGHT/4;
	public static final float OVERLAP_OFFSET = 28;
	public final Rectangle ground = new Rectangle(0, 0, WIDTH, GROUND_HEIGHT);

	public final Bird bird;
	public final List<PipeSet> pipeSets;
	public final List<Ground> grounds;
	public WorldState state;
	public WorldListener listener;
	
	public int score;
	
	private static Random rand = new Random();
	
	public World(WorldListener listener) {
		bird = new Bird(0, 0);
		pipeSets = new ArrayList<PipeSet>(3);
		for (int i=0; i<3; i++) {
			pipeSets.add(new PipeSet(0, 0));
		}
		grounds = new ArrayList<Ground>(2);
		for (int i=0; i<2; i++) {
			grounds.add(new Ground(0,0,i));
		}
		this.listener = listener;
		reset();
	}
	
	public void reset() {
		state = WorldState.RUNNING;
		bird.reset(WIDTH/4, HEIGHT/2);
		pipeSets.get(0).reset(WIDTH, getRandomPipePosY());
		pipeSets.get(1).reset(WIDTH + PIPE_SPACING, getRandomPipePosY());
		pipeSets.get(2).reset(WIDTH + 2*PIPE_SPACING, getRandomPipePosY());
		grounds.get(0).reset(Ground.WIDTH/2, GROUND_HEIGHT - Ground.HEIGHT/2);
		grounds.get(1).reset(1.5f*Ground.WIDTH - OVERLAP_OFFSET, GROUND_HEIGHT - Ground.HEIGHT/2);
		score = 0;
	}
	
	public void update(float deltaTime, boolean isTouched) {
		updateBird(deltaTime, isTouched);
		updatePipes(deltaTime);
		updateGrounds(deltaTime);
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
			if (pipeSet.getX() <= -Pipe.WIDTH/2) {
				pipeSet.reset(3*PIPE_SPACING-Pipe.WIDTH/2, getRandomPipePosY());
			}
		}
	}
	
	private void updateGrounds(float deltaTime) {
		for (Ground ground : grounds) {
			ground.moveLeft(deltaTime * PIPE_VELOCITY);
			if (ground.position.x <= OVERLAP_OFFSET - Ground.WIDTH/2) {
				ground.reset(1.5f*Ground.WIDTH - OVERLAP_OFFSET, ground.position.y);
				Gdx.app.log("ground positions:", "index="+ground.index+" x="+ground.position.x);
				Gdx.app.log("ground positions:", "index="+(1-ground.index)+" x="+grounds.get(1-ground.index).position.x);
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
				return;
			}
			if (bird.bounds.overlaps(pipeSet.space.bounds) && !pipeSet.space.used){
				pipeSet.space.used = true;
				score += 1;
				listener.score();
			}
		}
	}
	
	private float getRandomPipePosY() {
		return rand.nextFloat() * (MAX_PIPE_POS_Y - MIN_PIPE_POS_Y) + MIN_PIPE_POS_Y;
	}
	
}
