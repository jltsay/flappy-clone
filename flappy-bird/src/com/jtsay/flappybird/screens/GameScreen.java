package com.jtsay.flappybird.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jtsay.flappybird.World;
import com.jtsay.flappybird.WorldState;
import com.jtsay.flappybird.World.WorldListener;
import com.jtsay.flappybird.util.Assets;
import com.jtsay.flappybird.util.WorldRenderer;

public class GameScreen implements Screen {

	Game game;
	World world;
	WorldListener listener;
	WorldRenderer renderer;
	SpriteBatch batcher;
	String scoreString;
	
	public GameScreen(Game game) {
		this.game = game;
		this.batcher = new SpriteBatch();
		this.listener = new WorldListener() {
			
			@Override
			public void score() {
				
			}
			
			@Override
			public void jump() {
				
			}
			
			@Override
			public void crash() {
				
			}
		};
		this.world = new World(listener);
		this.renderer = new WorldRenderer(batcher, world);
		scoreString = "SCORE: ";
	}
	
	@Override
	public void render(float delta) {
		update(delta);
		draw();
	}
	
	private void update(float delta) {
		// TODO: check if touched
		// TODO: update world
		boolean justTouched = Gdx.input.justTouched();
		if (justTouched) {
			// TODO: call cam.unproject on the touchpoint, to check the coordinates, call 
			// appropriate listener calls.
		}
		if (world.state == WorldState.RUNNING) {
			world.update(delta, justTouched);			
		} else {
			if (justTouched) {
				world.reset();
				world.update(delta, false);				
			}
		}
	}
	
	private void draw() {
		renderer.render();
		batcher.begin();
		Assets.font.draw(batcher, scoreString + world.score, 16, world.HEIGHT - 20);
		batcher.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
