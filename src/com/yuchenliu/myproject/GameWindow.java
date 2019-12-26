package com.yuchenliu.myproject;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;


public class GameWindow extends JFrame{

	private static final long serialVersionUID = 1L;

	public GameWindow(GunMan first, AxeMan second, boolean playerPosition) {
		new JFrame();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(Main.COORX, Main.COORY, Main.WIDTH, Main.HEIGHT);
		add(new Panel(first, second, playerPosition));	
	}
	
}

class Panel extends JPanel{
	private int playerX, playerY, player2X, player2Y;
	private int bullet_width = 5, bullet_height = 5;
	private boolean gameover = false;
	
	private GunMan person;
	private AxeMan person2;
	
	private Queue<Bullet> bulletQueue = new LinkedList<Bullet>();
	private Timer timer = new Timer(50, e->{ //refresh every 50 milliseconds (0.5 seconds)
		updateScreen();
		repaint();
	});

	private Image personImage;
	private Image person2Image;
	
	Panel(GunMan firstPlayer, AxeMan secondPlayer, boolean playerPosition){ //playerPosition is orientation
		super();															// of the two physical players
		setFocusable(true);													//True: gunman left; False: gunman right
		
		person = firstPlayer;
		person2 = secondPlayer;
		playerX = person.getX();
		playerY = person.getY();
		player2X = person2.getX();
		player2Y = person2.getY();
		
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 81) { //Key Q
					bullet_width *= 7;
					bullet_height *= 7;
					sound();
				}
				if(e.getKeyCode() == 47) { //Key Question Mark
					person2.setX(playerX - 30);
					person2.setY(playerY);
				};
				
				if(playerPosition) {
					checkKeyPressPlayer1(e,person);
					checkKeyPressPlayer2(e,person2);
				}else {
					checkKeyPressPlayer1(e,person2);
					checkKeyPressPlayer2(e,person);
				}
				
				if(e.getKeyCode() == 32) { //	Space key to shoot
					Bullet bullet = new Bullet(playerX + 13, playerY + 7, person.direction);
					bulletQueue.add(bullet);
				}
			}

			@Override	
			public void keyReleased(KeyEvent e) {
				if(playerPosition) {
					checkKeyReleasePlayer1(e,person);
					checkKeyReleasePlayer2(e,person2);
				}else {
					checkKeyReleasePlayer1(e,person2);
					checkKeyReleasePlayer2(e,person);
				}
			}
				
		});
		timer.start(); //Begin the game
	}
	
	public void createImage() {
		personImage = person.getImage();
		person2Image = person2.getImage();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		createImage();
		drawBullet(g);
		
		g.drawImage(personImage, playerX, playerY, 37, 25, this);
		g.drawImage(person2Image, player2X, player2Y, 37, 25, this);
		if(gameover)
			g.drawString("Game Over", 250, 200);
		
		Toolkit.getDefaultToolkit().sync(); //sync all the graphics
	}
	
	public void updateScreen() {
		person.move();
		person2.move();
		
		playerX = person.getX();
		playerY = person.getY();
		player2X = person2.getX();
		player2Y = person2.getY();
		
		boolean kill = person2.checkCollision(playerX, playerY);
		if(kill) {
			playerX = 1000;
			playerY = 1000;
			gameOver();
		}
		
	}
	
	public void gameOver() {
		timer.stop();
		gameover = true;
		repaint();
		
		new EndWindow().setVisible(true);;
		((JFrame)SwingUtilities.getWindowAncestor(this)).dispose();
	}
	
	public void drawBullet(Graphics g) {
		Iterator<Bullet> iterator = bulletQueue.iterator();
		if(!bulletQueue.isEmpty()) {
			
			while(iterator.hasNext()) {
				Bullet bullet = iterator.next();
				boolean collide = bullet.checkCollision(person2.getX(), person2.getY());
				if(collide) {
					player2X = 1000;
					player2Y = 1000;
					gameOver();
				}
				if(bullet.getX() >= 600 || collide)
					iterator.remove();
				else {
					bullet.fireBullet();
					g.fillOval(bullet.getX(), bullet.getY(), bullet_width, bullet_height);
				}
			}
		}else {
			bullet_width = 5;
			bullet_height = 5;
		}
	}
	
	public void sound() {
		File ninjasound = new File("src/com/myproject/sound1.wav");
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(ninjasound));
			clip.start();
			
			Thread.sleep(clip.getMicrosecondLength() / 1000 / 3);
		} catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void checkKeyPressPlayer1(KeyEvent e, Human person) {
		if(e.getKeyCode()==68) {person.right = true; person.setFaceRight();}
		if(e.getKeyCode()==65) {person.left = true; person.setFaceLeft();}
		if(e.getKeyCode()==87) person.up = true;
		if(e.getKeyCode()==83) person.down = true;
	}
	
	public void checkKeyReleasePlayer1(KeyEvent e, Human person) {
		if(e.getKeyCode()==68) person.right = false;
		if(e.getKeyCode()==65) person.left = false;
		if(e.getKeyCode()==87) person.up = false;
		if(e.getKeyCode()==83) person.down = false;
	}
	
	public void checkKeyPressPlayer2(KeyEvent e, Human person) {
		if(e.getKeyCode()==39) {person.right = true; person.setFaceRight();}
		if(e.getKeyCode()==37) {person.left = true; person.setFaceLeft();}
		if(e.getKeyCode()==38) person.up = true;
		if(e.getKeyCode()==40) person.down = true;
	}
	
	public void checkKeyReleasePlayer2(KeyEvent e, Human person) {
		if(e.getKeyCode()==39) person.right = false;
		if(e.getKeyCode()==37) person.left = false;
		if(e.getKeyCode()==38) person.up = false;
		if(e.getKeyCode()==40) person.down = false;
	}
	
}
