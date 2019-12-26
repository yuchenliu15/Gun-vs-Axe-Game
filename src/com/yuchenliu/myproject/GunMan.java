package com.yuchenliu.myproject;

import java.awt.Image;

import javax.swing.ImageIcon;

public class GunMan extends Human {

	public GunMan(int x, int y, boolean direction) { // Direction: if true, face left; if false, face right
		super(x, y, direction);
	}

	@Override
	Image getImage() {
		Image img;
		if (direction)
			img = new ImageIcon("src/com/yuchenliu/myproject/player1/player_left.png").getImage();
		else
			img = new ImageIcon("src/com/yuchenliu/myproject/player1/player_right.png").getImage();
		return img;
	}
}
