package com.Veys.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.Veys.framework.GameObject;
import com.Veys.framework.Handler;
import com.Veys.framework.ID;
import com.Veys.window.Game;

public class MenuParticle extends GameObject{

	private Handler handler;
	private Color col;
	Random r = new Random();

	
	public MenuParticle(float x, float y, ID id,Handler handler) {
		super(x, y, id);
		this.handler = handler;
		velX=5;
		velY = 5;

		col = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255));
	
	}

	@Override
	public void tick() {
		
		x += velX;
		y += velY;
		
		if( y <= 0 || y >= Game.HEIGHT -32) velY  *= -1;
		if( x <= 0 || x >= Game.WIDTH - 32) velX  *= -1;

		handler.addObject(new Trail((int) x, (int) y, ID.Trail, col, 16, 16, 0.1f, handler));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(col);
		g.fillRect((int) x, (int) y, 16, 16);
		
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int )x,(int) y,16,16);
	}

	
}
