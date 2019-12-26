package com.yuchenliu.myproject;

import java.awt.EventQueue;

public class Main {
	
	public static final int WIDTH = 600, HEIGHT = 500;
	public static final int COORX = 100, COORY = 100;
	
	public static void main(String args[]) {
		
		EventQueue.invokeLater(()->{
			WelcomeWindow welcome = new WelcomeWindow();
			welcome.setVisible(true);
		});
	

	}
}
