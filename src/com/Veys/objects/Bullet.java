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

public class Bullet extends GameObject{
	
	private BufferedImage[] img = new BufferedImage[6];
	private Animation animation;
	private float speedGravity = 0.1f;
	private Handler handler;
	public Bullet(float x, float y, ID id,Handler handler ,SpriteSheet ss) {
		super(x, y, id);
		this.handler= handler;
		
		img[0] = ss.grabImage(4,63, 17, 19);
		img[1] = ss.grabImage(72,61, 17, 20);
		img[2] = ss.grabImage(94,61, 19, 20);
		img[3] = ss.grabImage(116,62, 18, 19);
		//img[4] = ss.grabImage(136,67, 20, 12);
		//img[5] = ss.grabImage(158,67, 22, 13);
		img[4] = ss.grabImage(181,68, 18, 13);
		img[5] = ss.grabImage(203,68,18, 12);
		animation = new Animation(20,img[0],img[1],img[2],img[3],img[4],img[5]);
	}


	@Override
	public void tick() {
		
		x += velX;
		animation.runAnimation();
		
		velX += speedGravity;
		
		if(velX >= 10){
			velX = 10;
		}
		
		
	}
	

	@Override
	public void render(Graphics g) {
		
		Graphics2D g2d= (Graphics2D ) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(velX > 0){
			animation.drawAnimation(g, (int) x, (int) y, 0, 25, 25);
		}

		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x,(int) y,width,height);
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
