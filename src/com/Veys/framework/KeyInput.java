package com.Veys.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.Veys.objects.Bullet;
import com.Veys.window.Game;

public class KeyInput extends KeyAdapter{
	
	private Handler handler;

	public KeyInput(Handler handler){
		this.handler=handler;
	}

	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_D){
					handler.setRight(true);
	
				}
				if(key == KeyEvent.VK_A){
					handler.setLeft(true);

				}
				if(key==KeyEvent.VK_F){
					handler.addObject(new Bullet(tempObject.getX()   ,tempObject.getY() , ID.Bullet,handler,Game.bullet_sheet));
				}

				if(key == KeyEvent.VK_SPACE && !tempObject.isJumping()){
					tempObject.setJumping(true);
					tempObject.setVelY(-20);
					
				}
			}
			if(key == KeyEvent.VK_ESCAPE){
				System.exit(0);
			}
		}


		
	}
	
	public void keyReleased(KeyEvent e){
		
		int key = e.getKeyCode();
		
		for(int i=0;i<handler.object.size();i++){
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player){
				if(key == KeyEvent.VK_D){
					handler.setRight(false);
				}
				if(key == KeyEvent.VK_A){
					handler.setLeft(false);
				}
			}
		}
		
	}

}
