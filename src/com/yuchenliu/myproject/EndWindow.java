package com.yuchenliu.myproject;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;

public class EndWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EndWindow frame = new EndWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public EndWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Main.COORX, Main.COORY, Main.WIDTH, Main.HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGameOver = new JLabel("Game Over");
		lblGameOver.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 70));
		lblGameOver.setBounds(85, 10, 432, 124);
		contentPane.add(lblGameOver);
		
		JButton btnNewButton = new JButton("Play Again");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(204, 362, 137, 56);
		btnNewButton.addActionListener(e -> {
			EventQueue.invokeLater(()->{
				WelcomeWindow welcome = new WelcomeWindow();
				welcome.setVisible(true);
				this.dispose();
			});
		});
		contentPane.add(btnNewButton);
	}

}
