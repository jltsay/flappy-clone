package com.jtsay.flappybird;

import java.util.ArrayList;
import java.util.List;

public class World {
	public interface WorldListener {
		public void jump();

		public void crash();

		public void score();
	}
	
	public static final int gravity = -10;
	public final Bird bird;
	public final List<PipeSet> pipeSets;
	
	public World(WorldListener listener) {
		bird = new Bird(320/4, 480/4);
		pipeSets = new ArrayList<PipeSet>(3);
	}
	
	
}
