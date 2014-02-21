package com.jtsay.flappybird;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class GameObject {

	public final Vector2 position;
	public final Rectangle bounds;
	public final Vector2 velocity;
	public final Vector2 acceleration;
	
	public GameObject(float posX, float posY, float width, float height) {
		position = new Vector2(posX, posY);
		bounds = new Rectangle(posX-width/2, posY-height/2, width, height);
		velocity = new Vector2();
		acceleration = new Vector2();
	}
	
}
