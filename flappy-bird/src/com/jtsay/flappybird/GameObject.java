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
	
	public void setPos(float x, float y) {
		this.position.x = x;
		this.position.y = y;
		this.bounds.x = x - this.bounds.width/2;
		this.bounds.y = y - this.bounds.height/2;
	}
	
	public void move(float x, float y) {
		this.position.x += x;
		this.position.y += y;
		this.bounds.x += x;
		this.bounds.y += y;
	}
	
	public void moveLeft(float offset) {
		this.position.x -= offset;
		this.bounds.x -= offset;
	}
}
