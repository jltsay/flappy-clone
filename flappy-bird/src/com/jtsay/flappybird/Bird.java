package com.jtsay.flappybird;

public class Bird extends GameObject {
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 21;

	public Bird(float posX, float posY) {
		super(posX, posY, WIDTH, HEIGHT);
	}
	
}
