package com.jtsay.flappybird;

public class PipeSet {
	public final Pipe topPipe;
	public final Pipe bottomPipe;
	public final SpaceBetweenPipes space;
	
	public PipeSet(Pipe topPipe, Pipe bottomPipe, SpaceBetweenPipes space) {
		this.topPipe = topPipe;
		this.bottomPipe = bottomPipe;
		this.space = space;
	}
	
	public PipeSet(float x, float y) {
		this.topPipe = new Pipe(x, y + SpaceBetweenPipes.HEIGHT/2 + Pipe.HEIGHT/2);
		this.space = new SpaceBetweenPipes(x, y);
		this.bottomPipe = new Pipe(x, y - SpaceBetweenPipes.HEIGHT/2 - Pipe.HEIGHT/2);
	}
}
