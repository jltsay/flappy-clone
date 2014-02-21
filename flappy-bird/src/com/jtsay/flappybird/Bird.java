package com.jtsay.flappybird;

public class Bird extends GameObject {
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 21;
	private boolean isHit;

	public Bird(float posX, float posY) {
		super(posX, posY, WIDTH, HEIGHT);
		isHit = false;
	}
	
	public boolean isHit() {
		return isHit;
	}
	
	public void hitObstacle() {
		isHit = true;
	}
	
}
