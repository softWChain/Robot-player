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
import com.Veys.window.SpriteSheet;

public class Block extends GameObject{
	
	private BufferedImage img;
	private Handler handler;
	public Block(float x, float y, ID id,Handler handler,SpriteSheet ss) {
		super(x, y, id);
		this.handler = handler;
		width=32;
		height=32;
		
		img = ss.grabImage(0, 32 , 32, 32);
		
	}

	@Override
	public void tick() {
		
		collosion();
		
	}
	
	public void collosion(){
		
		for(int i=0;i<handler.object.size();i++){
			GameObject temObject = handler.object.get(i);

			if(temObject.getId() == ID.Bullet){
				if(getBoundsLeft().intersects(temObject.getBounds())){
					velX = 0;
					handler.removeObject(temObject);
					
				}
				if(getBoundsRight().intersects(temObject.getBounds())){
					velX=0;
					handler.removeObject(temObject);
				}
			}
		}
		
	}

	@Override
	public void render(Graphics g) {

		Graphics2D g2d= (Graphics2D ) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.drawImage(img, (int) x,(int) y, null);
		
		g2d.setColor(Color.CYAN);
		g2d.draw(getBoundsBottom());
		g2d.draw(getBoundsTop());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		
		
		
	}

	@Override
	public Rectangle getBounds() { 
		
		return new Rectangle((int) x , (int) y, width,height);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) x + (width/2) - ((width/2)/2), (int) y, (int) width/2, (int) height / 2);
	}
	public Rectangle getBoundsBottom() {
		return new Rectangle((int) x + (width/2) - ((width/2)/2), (int) y + (height / 2),(int) width /2 ,(int) height/2);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) x, (int) y + 4 , 5, height - 8 );
	}
	public Rectangle getBoundsRight() { 
		
		return new Rectangle((int) x + width -5, (int) y + 4 ,(int) 5, (int) height - 8);
	}


}
