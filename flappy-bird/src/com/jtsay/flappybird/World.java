package com.jtsay.flappybird;

import java.util.ArrayList;
import java.util.List;

public class World {
	public interface WorldListener {
		public void jump();

		public void crash();

		public void score();
	}
	
	public static final int GRAVITY = -10;
	public static final int WIDTH = 320;
	public static final int HEIGHT = 480;
	public static final int PIPE_SPACING = 128;
	public final Bird bird;
	public final List<PipeSet> pipeSets;
	public WorldState state;
	
	public World(WorldListener listener) {
		bird = new Bird(WIDTH/4, HEIGHT/4);
		pipeSets = new ArrayList<PipeSet>(3);
		pipeSets.add(new PipeSet(WIDTH*3/4, HEIGHT/2));
		pipeSets.add(new PipeSet(WIDTH*3/4 + PIPE_SPACING, HEIGHT/2 + PIPE_SPACING));
		pipeSets.add(new PipeSet(WIDTH*3/4 + 2*PIPE_SPACING, HEIGHT/2 + 2*PIPE_SPACING));
		state = WorldState.RUNNING;
	}
	
	public void update(float deltaTime, boolean isTouched) {
		updateBird(deltaTime, isTouched);
		updatePipes(deltaTime);
		checkCollision();
	}
	
	private void updateBird(float deltaTime, boolean isTouched) {
		
	}
	
	private void updatePipes(float deltaTime) {
		
	}
	
	private void checkCollision() {
		
	}
	
}
