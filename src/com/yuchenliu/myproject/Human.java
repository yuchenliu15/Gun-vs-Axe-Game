package com.yuchenliu.myproject;

import java.awt.Image;

public abstract class Human {
	protected boolean up, down, left, right, direction; //Direction: if true, face left; if false, face right
	protected int x, y;
	
	public Human(int x, int y, Boolean direction) { 
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public void move() {
		
		if(right && x < 550) x += 10;
		if(left && x > 0) x -= 10;
		if(up && y > 0) y -= 10;
		if(down && y < 385) y += 10;

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setFaceLeft() {
		direction = true;
	}
	
	public void setFaceRight() {
		direction = false;
	}
	
	abstract Image getImage();
	
}
