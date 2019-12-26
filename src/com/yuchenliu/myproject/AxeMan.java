package com.yuchenliu.myproject;

import java.awt.Image;

import javax.swing.ImageIcon;

public class AxeMan extends Human{
	
	public AxeMan(int x, int y, Boolean direction) { //Direction: if true, face left; if false, face right
		super(x,y,direction);
	}
	
	public boolean checkCollision(int playerX, int playerY) {
		int xMax = playerX + 37;
		int yMax = playerY + 25;
		if((super.x >= playerX && x <= xMax) && (y >= playerY && y <= yMax))
			return true;
		return false;
	}

	@Override
	Image getImage() {
		Image img;
		if(direction)
			img = new ImageIcon("src/com/yuchenliu/myproject/player2/player2_left.png").getImage();
		else
			img = new ImageIcon("src/com/yuchenliu/myproject/player2/player2_right.png").getImage();
		return img;
	}

}
