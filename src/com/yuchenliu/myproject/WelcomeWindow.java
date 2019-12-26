package com.yuchenliu.myproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Color;

public class WelcomeWindow extends JFrame {

	private JPanel contentPane;
	private JRadioButton radioGunMan1, radioAxeMan1, radioGunMan2, radioAxeMan2;
	private JLabel lblCannotSelectThe;
	private GunMan person = null;
	private AxeMan person2 = null;

	public WelcomeWindow() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Main.COORX, Main.COORY, Main.WIDTH, Main.HEIGHT);
		contentPane = new WelcomePanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnPlay = new JButton("PLAY");
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPlay.setBounds(203, 382, 139, 71);
		btnPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				var axe_1 = WelcomeWindow.this.radioAxeMan1;
				var axe_2 = WelcomeWindow.this.radioAxeMan2;
				var gun_1 = WelcomeWindow.this.radioGunMan1;
				var gun_2 = WelcomeWindow.this.radioGunMan2;

				if ((axe_1.isSelected() && axe_2.isSelected()) || (gun_1.isSelected() && gun_2.isSelected()))
					WelcomeWindow.this.lblCannotSelectThe.setVisible(true);
				else {
					WelcomeWindow.this.dispose();

					if (axe_1.isSelected()) {
						person2 = new AxeMan(40, 200, false);
						person = new GunMan(500, 200, true);

						EventQueue.invokeLater(() -> {
							GameWindow win = new GameWindow(person, person2, false);
							win.setVisible(true);
						});

					} else {
						person2 = new AxeMan(500, 200, true);
						person = new GunMan(40, 200, false);

						EventQueue.invokeLater(() -> {
							GameWindow win = new GameWindow(person, person2, true);
							win.setVisible(true);
						});

					}

				}
			}
		});
		contentPane.add(btnPlay);

		JLabel lblVs = new JLabel("VS");
		lblVs.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblVs.setBounds(251, 10, 108, 99);
		contentPane.add(lblVs);

		JLabel lblPlayer = new JLabel("Player 1");
		lblPlayer.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblPlayer.setBounds(44, 14, 173, 82);
		contentPane.add(lblPlayer);

		JLabel label = new JLabel("Player 2");
		label.setFont(new Font("Tahoma", Font.PLAIN, 45));
		label.setBounds(346, 14, 173, 82);
		contentPane.add(label);

		radioGunMan1 = new JRadioButton("Gun man", true);
		radioGunMan1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioGunMan1.setBounds(44, 158, 105, 21);
		contentPane.add(radioGunMan1);

		radioAxeMan1 = new JRadioButton("Axe man");
		radioAxeMan1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioAxeMan1.setBounds(44, 191, 105, 21);
		contentPane.add(radioAxeMan1);
		radioGunMan1.addActionListener(e -> {
			Image img = new ImageIcon("src/com/yuchenliu/myproject/player1/player_left.png").getImage();
			((WelcomePanel) contentPane).setPlayer(img);
			contentPane.repaint();
		});
		radioAxeMan1.addActionListener(e -> {
			Image img = new ImageIcon("src/com/yuchenliu/myproject/player2/player2_left.png").getImage();
			((WelcomePanel) contentPane).setPlayer(img);
			contentPane.repaint();
		});

		ButtonGroup buttonGroupPlayer1 = new ButtonGroup();
		buttonGroupPlayer1.add(radioGunMan1);
		buttonGroupPlayer1.add(radioAxeMan1);

		radioGunMan2 = new JRadioButton("Gun man");
		radioGunMan2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioGunMan2.setBounds(346, 160, 105, 21);
		contentPane.add(radioGunMan2);

		radioAxeMan2 = new JRadioButton("Axe man", true);
		radioAxeMan2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radioAxeMan2.setBounds(346, 193, 105, 21);
		contentPane.add(radioAxeMan2);

		radioGunMan2.addActionListener(e -> {
			Image img = new ImageIcon("src/com/yuchenliu/myproject/player1/player_left.png").getImage();
			((WelcomePanel) contentPane).setPlayer2(img);
			contentPane.repaint();
		});
		radioAxeMan2.addActionListener(e -> {
			Image img = new ImageIcon("src/com/yuchenliu/myproject/player2/player2_left.png").getImage();
			((WelcomePanel) contentPane).setPlayer2(img);
			contentPane.repaint();
		});

		ButtonGroup buttonGroupPlayer2 = new ButtonGroup();
		buttonGroupPlayer2.add(radioGunMan2);
		buttonGroupPlayer2.add(radioAxeMan2);

		lblCannotSelectThe = new JLabel("Cannot select the same players");
		lblCannotSelectThe.setForeground(Color.RED);
		lblCannotSelectThe.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblCannotSelectThe.setBounds(181, 358, 207, 14);
		lblCannotSelectThe.setVisible(false);
		contentPane.add(lblCannotSelectThe);
	}

}

class WelcomePanel extends JPanel {

	private Image player, player2;

	public WelcomePanel() {
		super();
		createImage();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(player, 60, 120, 37, 25, this);
		g.drawImage(player2, 362, 120, 37, 25, this);
	}

	public void setPlayer(Image player) {
		this.player = player;
	}

	public void setPlayer2(Image player2) {
		this.player2 = player2;
	}

	public void createImage() {
		ImageIcon img = new ImageIcon("src/com/yuchenliu/myproject/player1/player_left.png");
		player = img.getImage();

		ImageIcon img2 = new ImageIcon("src/com/yuchenliu/myproject/player2/player2_left.png");
		player2 = img2.getImage();

	}
}
