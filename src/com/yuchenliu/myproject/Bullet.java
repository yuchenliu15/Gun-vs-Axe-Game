package com.yuchenliu.myproject;

public class Bullet {
	
	private int x, y;
	private boolean direction;
	
	public Bullet(int x, int y, boolean direction) { //Bullet fly direction; True: left; False: right
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public void fireBullet() {
		if(direction)
			x -= 20;
		else
			x+= 20;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public boolean checkCollision(int enemyX, int enemyY) {
		int xMax = enemyX + 37;
		int yMax = enemyY + 25;
		if((x >= enemyX && x <= xMax) && (y >= enemyY && y <= yMax))
			return true;
		return false;
	}
	
}
