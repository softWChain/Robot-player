package com.Veys.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.Veys.framework.GameObject;
import com.Veys.framework.Handler;
import com.Veys.framework.ID;
import com.Veys.window.Game;

public class BasicEnemy extends GameObject{

	private Handler handler;


	
	public BasicEnemy(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX=5;
		velY = 5;

		
	
	}

	@Override
	public void tick() {
		
		x += velX;
		y += velY;
		
		if( y <= 0 || y >= Game.HEIGHT -32) velY  *= -1;
		if( x <= 0 || x >= Game.WIDTH - 32) velX  *= -1;

		handler.addObject(new Trail((int) x, (int) y, ID.Trail, Color.CYAN, 16, 16, 0.1f, handler));

	}

	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.CYAN);
		g.fillRect((int) x, (int) y, 16, 16);

		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int )x,(int) y,16,16);
	}

	
}
