package com.Veys.window;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {

	
	private int speed,frames,index=0,count=0;
	private BufferedImage images[];
	private BufferedImage currentImage;
	
	public Animation(int speed , BufferedImage ...args){
		this.speed = speed;
		images = new BufferedImage[args.length];
		for(int i=0;i<args.length;i++){
			images[i] = args[i];
		}
		frames = args.length;
		
	}
	
	public void runAnimation(){
		index++;
		if(index > speed){
			index = 0;
			nextFrame();
		}
	}
	
	
	public void nextFrame(){
		for(int i=0;i<frames;i++){
			if(count  == i)
				currentImage = images[i];
		}
		count++;
		if(count > frames){
			count = 0;
		}
	}
	
	public void drawAnimation(Graphics g,float x ,float y ,int offset,int width,int height){
		g.drawImage(currentImage, (int) x - offset, (int) y, width,height,null);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}

