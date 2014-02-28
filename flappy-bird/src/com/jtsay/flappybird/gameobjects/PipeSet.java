package com.jtsay.flappybird.gameobjects;

public class PipeSet {
	public final Pipe topPipe;
	public final Pipe bottomPipe;
	public final SpaceBetweenPipes space;
	
	public PipeSet(float x, float y) {
		this.topPipe = new Pipe(x, y + SpaceBetweenPipes.HEIGHT/2 + Pipe.HEIGHT/2);
		this.space = new SpaceBetweenPipes(x, y);
		this.bottomPipe = new Pipe(x, y - SpaceBetweenPipes.HEIGHT/2 - Pipe.HEIGHT/2);
	}
	
	public void reset(float x, float y) {
		this.topPipe.reset(x, y + SpaceBetweenPipes.HEIGHT/2 + Pipe.HEIGHT/2);
		this.space.reset(x, y);
		this.bottomPipe.reset(x, y - SpaceBetweenPipes.HEIGHT/2 - Pipe.HEIGHT/2);
		
	}
	
	public void moveLeft(float offset) {
		topPipe.moveLeft(offset);
		space.moveLeft(offset);
		bottomPipe.moveLeft(offset);
	}
	
	public float getX() {
		return topPipe.position.x;
	}
	
}
