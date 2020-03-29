package com.Veys.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.Veys.framework.GameObject;
import com.Veys.framework.Handler;
import com.Veys.framework.ID;
import com.Veys.framework.STATE;
import com.Veys.window.Animation;
import com.Veys.window.Game;
import com.Veys.window.SpriteSheet;

public class Player extends GameObject{
	
	private float gravity = 0.7f;
	private final float MAX_SPEED = 10f;
	private Handler handler;
	private BufferedImage[] player_images = new BufferedImage[7];
	private Animation leftAnimation,rightAnimation;

	public Player(float x, float y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		width=28;
		height = 42;
		//FRONT	
		player_images[0] = ss.grabImage(1, 64 , 29, 30);
		//RİGHT
		player_images[1] = ss.grabImage(5, 130 ,18, 28);
		player_images[2] = ss.grabImage(37, 130 ,19, 28);
		player_images[3] = ss.grabImage(70, 130 , 17, 28);
		//LEFT
		player_images[4] = ss.grabImage(8, 162 , 18, 28);
		player_images[5] = ss.grabImage(39, 162 , 19, 28);
		player_images[6] = ss.grabImage(72, 162 , 17, 28);
		
		rightAnimation = new Animation(8, player_images[1],player_images[2],player_images[3]);
		leftAnimation = new Animation(8, player_images[4],player_images[5],player_images[6]);
		
		
		
		
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(falling || jumping ){
			velY += gravity;
		}
		if(velY > MAX_SPEED){
			velY = MAX_SPEED;
		}
		collosion();
		rightAnimation.runAnimation();
		leftAnimation.runAnimation();
		
		if(handler.isRight()){
			velX = 5;
		}
		else if(!handler.isLeft()){
			velX = 0;
		}
		if(handler.isLeft()){
			velX = -5;
		}
		else if(!handler.isRight()){
			velX = 0;
		}

	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(velX > 0){
			rightAnimation.drawAnimation(g, x, y - 20, 0,40,60);
		}else if(velX < 0){
			leftAnimation.drawAnimation(g, x, y - 20, 0,40,60);
		}else{
			g.drawImage(player_images[0],(int) x,(int) y - 20 , 40,60, null);
		}
		

		
		if(x > 1472 && x < 1600 && y == 256){
			Game.gameState = STATE.Menu;
		}
	}
	
	public void collosion(){
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temObject = handler.object.get(i);
			if(temObject.getId() == ID.Block){
				
				if(getBoundsTop().intersects(temObject.getBounds())){
					y = temObject.getY() + temObject.getHeight();
					
					velY = 0;
				}
				if(getBoundsBottom().intersects(temObject.getBounds())){
					y = temObject.getY() - height;
					velY=0;
					falling= false;
					jumping = false;
				}else{
					falling = true;
				}
				
				if(getBoundsLeft().intersects(temObject.getBounds())){
					x = temObject.getX() + temObject.getWidth();
					
				}
				if(getBoundsRight().intersects(temObject.getBounds())){
					x = temObject.getX() - temObject.getWidth();
				}
			}


		}
		
	
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x,(int) y ,width,height);
	}
	public Rectangle getBoundsTop(){
		return new Rectangle((int) x + (width)/2 -(width/2)/2, (int) y, width/2,(int) height / 2 );
	}
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + (width)/2 -(width/2)/2,(int) y + height/2 ,width/2,(int)height/2);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x,(int) y + width/4,width/4,height - height/4);
	}
	public Rectangle getBoundsRight() {
		return new Rectangle((int) x + (width)/2 +(width/2)/2,(int) y + width/4 , width/4,height - height/4);
	}

}
