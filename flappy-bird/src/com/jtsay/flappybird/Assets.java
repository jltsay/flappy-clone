package com.jtsay.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static Texture background;
	public static TextureRegion backgroundRegion;
	private static Texture spritesheet;
	public static TextureRegion bird;
	public static TextureRegion pipe;
	
	public static void load() {
		background = loadTexture("data/background.png");
		backgroundRegion = new TextureRegion(background, 320, 480);
		spritesheet = loadTexture("data/spritesheet.png");
		bird = new TextureRegion(spritesheet, 2, 2, 32, 21);
		pipe = new TextureRegion(spritesheet, 2, 25, 64, 320);
	}
	
	public static Texture loadTexture (String file) {
		return new Texture(Gdx.files.internal(file));
	}
	
	
}
