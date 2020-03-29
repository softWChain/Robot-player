package com.Veys.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import com.Veys.framework.GameObject;
import com.Veys.framework.Handler;
import com.Veys.framework.ID;
import com.Veys.window.Animation;
import com.Veys.window.SpriteSheet;

public class Enemy extends GameObject{

	private BufferedImage[] enemy_image = new BufferedImage[13];
	private Handler handler;
	private float  distance,diffX,diffY;
	
	private Animation animLeft,animRight,animBack,animFront;
	public Enemy(float x, float y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		width =32;
		height = 32;
		enemy_image[0] = ss.grabImage(192, 4, 14, 20);
		
		//BACK
		enemy_image[1] = ss.grabImage(192, 36, 14, 20);
		enemy_image[2] = ss.grabImage(208, 35, 14, 20);
		enemy_image[3] = ss.grabImage(224, 36, 14, 21);
		//RIGHT
		enemy_image[4] = ss.grabImage(192, 66, 15, 20);
		enemy_image[5] = ss.grabImage(207, 65, 16, 19);
		enemy_image[6] = ss.grabImage(225, 69, 14, 20);
		//LEFT
		enemy_image[7] = ss.grabImage(192, 98, 13, 20);
		enemy_image[8] = ss.grabImage(208, 97, 18, 19);
		enemy_image[9] = ss.grabImage(224, 101, 15, 20);
		//FRONT
		enemy_image[10] = ss.grabImage(192, 4, 14, 20);
		enemy_image[11] = ss.grabImage(208, 3, 14, 20);
		enemy_image[12] = ss.grabImage(224, 5, 14, 20);
		
		
		animBack = new Animation(8, enemy_image[1],enemy_image[2],enemy_image[3]);
		animRight = new Animation(8, enemy_image[3],enemy_image[5],enemy_image[6]);
		animLeft = new Animation(8, enemy_image[7],enemy_image[8],enemy_image[9]);
		animFront = new Animation(8, enemy_image[10],enemy_image[11],enemy_image[12]);

		
	}


	@Override
	public void tick() {
		
		
		x += velX;
		y += velY;
		collision();
		

		for(int i=0;i<handler.object.size();i++){
			
			GameObject temObject = handler.object.get(i);
			if(temObject.getId() == ID.Player){
				diffX = x - temObject.getX() + width;

				diffY = y - temObject.getY() - height;
				distance = (float) Math.sqrt((x-temObject.getX())* (x-temObject.getX()) + (y - temObject.getY()) * (y - temObject.getY()));
			}
		}	
		
		if(distance < 200){
			velX = ((-1/distance) * diffX);
			velY = ((-1/distance) * diffY);
		}

		else{
			velX = 0;
			velY = 0;
		}
		
		
		
		animBack.runAnimation();
		animRight.runAnimation();
		animLeft.runAnimation();
		animFront.runAnimation();
		
	
		
	}
	
	public void collision(){
		for(int i=0;i<handler.object.size();i++){
			GameObject temObject = handler.object.get(i);
			if(temObject.getId() == ID.Block){
				if(getBoundsTop().intersects(temObject.getBounds())){
					y = temObject.getY()  + height;
					velY = 0;
				}
				if(getBoundsBottom().intersects(temObject.getBounds())){
					y = temObject.getY()  - height;
					velY = 0;
				}
				if(getBoundsLeft().intersects(temObject.getBounds())){
					x = temObject.getX()  + width;
					velX = 0;
				}
				if(getBoundsRight().intersects(temObject.getBounds())){
					x = temObject.getX()  - width;
					velX = 0;
				}
			}
			if(temObject.getId() == ID.Bullet){
				if(getBounds().intersects(temObject.getBounds())){
					handler.removeObject(temObject);
					
				}
			}
		}
	}

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		for(int i = 0;i<handler.object.size();i++){
			GameObject temObject = handler.object.get(i);
			if(temObject.getId() == ID.Player){
				if(velX==0 && velY==0){
					g.drawImage(enemy_image[0], (int)x,(int) y,width,height,null);
				}else if(Math.abs(temObject.getX()-x) > Math.abs(temObject.getY() - y)){
					if(velX > 0){
						animRight.drawAnimation(g, x, y, 0,width,height);
					}else if(velX < 0){
						animLeft.drawAnimation(g, x, y, 0,width,height);
					}
				}else if(Math.abs(temObject.getY()-y) > Math.abs(temObject.getX() - x)){
					if(velY>0){
						animFront.drawAnimation(g, x, y, 0, width, height);
					}
					
				else if(velY < 0){
						animBack.drawAnimation(g, x, y, 0,width,height);
					}
				}
			}
		}

		
	}

	@Override
	public Rectangle getBounds() {
		
		return new Rectangle((int) x,(int) y,width,height);
	}
	public Rectangle getBoundsTop(){
		return new Rectangle((int) x + width/2 -(width/2)/2 , (int) y , width/2,height/2);
	}
	public Rectangle getBoundsBottom(){
		return new Rectangle((int) x + (width/2) - (width/2)/2 , (int) y + height/2,width/2,height/2);
	}
	public Rectangle getBoundsLeft(){
		return new Rectangle((int) x , (int) y + (height/2) - (height/2) / 2,width/4,height/2);
	}
	public Rectangle getBoundsRight(){
		return new Rectangle((int) x + width - (width/4) , (int) y + (height/2) - (height/2)/2,width/4,height/2);
	}

}
