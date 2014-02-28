package com.jtsay.flappybird.gameobjects;

public class SpaceBetweenPipes extends GameObject {
	
	public static float WIDTH = 64;
	public static float HEIGHT = 110;
	public boolean used;
	
	public SpaceBetweenPipes(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		used = false;
	}
	
	public void reset(float x, float y) {
		super.reset(x, y);
		this.used = false;
	}

}
