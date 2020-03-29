package com.Veys.window;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage img;
	
	SpriteSheet (BufferedImage img)
	{
		this.img =img;
	}
	
	public BufferedImage grabImage(int x,int y,int width,int height){
		return img.getSubimage(x, y, width, height);
	}
		


}
