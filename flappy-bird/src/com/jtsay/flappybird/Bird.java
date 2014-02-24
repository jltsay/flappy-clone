package com.jtsay.flappybird;

import com.badlogic.gdx.math.Vector2;

public class Bird extends GameObject {
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 21;
	public static final int JUMP_VELOCITY = 320;
	private boolean isHit;

	public Bird(float posX, float posY) {
		super(posX, posY, WIDTH, HEIGHT);
		isHit = false;
	}
	
	public void reset(float x, float y) {
		setPos(x, y);
		velocity.y = JUMP_VELOCITY;
		this.isHit = false;
	}
	
	public boolean isHit() {
		return isHit;
	}
	
	public void hitObstacle() {
		isHit = true;
	}
	
}
