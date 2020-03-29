package com.Veys.window;

import java.awt.image.BufferedImage;

import com.Veys.framework.Handler;
import com.Veys.framework.ID;
import com.Veys.framework.STATE;
import com.Veys.objects.BasicEnemy;
import com.Veys.objects.Block;
import com.Veys.objects.Enemy;
import com.Veys.objects.Player;

public class MapLoading {
	
	private BufferedImage img;
	private Handler handler;
	
	
	public MapLoading(BufferedImage img,Handler handler){

		int width = img.getWidth();
		int height = img.getHeight();
		
		for(int yy=0;yy<height;yy++){
			for(int xx=0;xx<width;xx++){
				int pixel = img.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int blue = (pixel >> 8) & 0xff;
				int green = (pixel) & 0xff;
				
				if(red == 0 && blue == 38 && green == 255){
					handler.addObject(new Player(xx*32, yy*32, ID.Player, handler,Game.player_sheet));
				}
				if(red == 255 && blue == 255 && green == 255 ){
					handler.addObject(new Block(xx*32, yy*32, ID.Block,handler,Game.block_sheet));
				}
				if(red == 255 && blue == 0 && green == 0 ){
					handler.addObject(new Enemy(xx*32, yy*32, ID.Enemy,handler,Game.enemy_sheet));
				}

			}		
		}	
	}

}
