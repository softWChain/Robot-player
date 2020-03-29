package com.Veys.window;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window {
	
	JFrame frame = new JFrame();
	
	public Window(int width,int height,String title ,Game game){
		

		game.setPreferredSize(new Dimension(width,height));
		game.setMaximumSize(new Dimension(width,height));
		game.setMinimumSize(new Dimension(width,height));
		
	
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle(title);
		frame.setVisible(true);
		game.start();
	}
	
	public JFrame getFrame(){
		return frame;
	}

}
