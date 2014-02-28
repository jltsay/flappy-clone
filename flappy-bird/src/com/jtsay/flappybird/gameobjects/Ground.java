package com.jtsay.flappybird.gameobjects;

public class Ground extends GameObject {

	public static float WIDTH = 504;
	public static float HEIGHT = 80;
	public final int index;
	
	public Ground(float x, float y, int index) {
		super(x, y, WIDTH, HEIGHT);
		this.index = index;
	}
	
}
