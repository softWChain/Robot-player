package com.Veys.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.Veys.framework.Handler;
import com.Veys.framework.ID;
import com.Veys.framework.KeyInput;
import com.Veys.framework.STATE;
import com.Veys.objects.BasicEnemy;
import com.Veys.objects.MenuParticle;

public class Game extends Canvas implements Runnable{
	
	
	private static final long serialVersionUID = -3007732524220650739L;
	
	public static int WIDTH = 1080;
	public static int HEIGHT = 720;
	public static String TITLE = "Game pre-Alpha 0.0.1";
	
	

	private boolean running = false;

	private BufferStrategy bs;
	private Graphics g;
	
	private Handler handler;
	private Camera camera;
	private Thread thread;
	private Window window;
	private MapLoading maploading;
	private BasicEnemy basicEnemy;
	public static STATE gameState = STATE.Game;
	Random r = new Random();
	
	private BufferedImage level,level2;
	public static BufferedImage block_images;
	public static SpriteSheet block_sheet;
	public static BufferedImage player_images;
	public static SpriteSheet player_sheet;
	public static BufferedImage enemy_images;
	public static SpriteSheet enemy_sheet;
	public static BufferedImage bullet_images;
	public static SpriteSheet bullet_sheet;

	

	public Game(){
		
		window = new Window(WIDTH, HEIGHT, TITLE, this);
		setFocusable(true);
		

		
	}
	
	public void init(){
		BufferedImageLoader loader = new BufferedImageLoader();
		
		handler = new Handler();
		camera = new Camera(0,0);
		level = loader.loadImage("/levell.png");
		level2 = loader.loadImage("/level3.png");
		block_images = loader.loadImage("/sheet.png");
		block_sheet = new SpriteSheet(block_images);
		player_images = loader.loadImage("/pic_golem.png");
		player_sheet = new SpriteSheet(player_images);
		enemy_images = loader.loadImage("/pic_golem.png");
		enemy_sheet = new SpriteSheet(enemy_images);
		bullet_images = loader.loadImage("/bullet.png");
		bullet_sheet = new SpriteSheet(bullet_images);



		
		addKeyListener(new KeyInput(handler));
		
		
		
		if(gameState == STATE.Game){
			maploading = new MapLoading(level, handler);
			handler.addObject(new BasicEnemy(250, 250, ID.BasicEnemy, handler));
			

		}
		else {
			
			for(int i=0;i<10;i++){
				handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
				}
			
			handler.addObject(new BasicEnemy(Game.WIDTH/2, Game.HEIGHT/2, ID.BasicEnemy, handler));
			}
		
	}
	
	public void tick(){
		handler.tick();
		
		for(int i=0;i<handler.object.size();i++){
			if(handler.object.get(i).getId()==ID.Player){
				camera.tick(handler.object.get(i));
			}
		}


	}
	
	public void render(){
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g2d.translate(camera.getX(), camera.getY());
		handler.render(g);
		g2d.translate(-camera.getX(), -camera.getY());

		
		
		bs.show();
		g.dispose();	
	}
	
	public static int clamp(int var ,int min,int max){
		if(var >= max)
			
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}

	
	@SuppressWarnings("static-access")
	public void run(){
		
		init();
		
		double amountOFTicks = 60.0;
		double timePerSecond = 1000000000 / amountOFTicks;
		double delta = 0;
		long lastTime = System.nanoTime();
		long now;
		long timer = System.currentTimeMillis();
		int frames = 0;
		int updates = 0;
		
		while(running){
			try {
				thread.sleep(12);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			now = System.nanoTime();
			delta += (now - lastTime) / timePerSecond;
			lastTime = now;
			
			if(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer >= 1000){
				
				timer += 1000;
				
				window.getFrame().setTitle(((TITLE + "   FPS : " + frames +" , UPDATES : " + updates)));
				System.err.println((TITLE + "   FPS : " + frames +" , UPDATES : " + updates));
				frames  = 0;
				updates = 0;
				
			}
			
		}
		
		
		stop();
	}
	
	public synchronized void start(){
		
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
		
	}
	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args){
		
		new Game();
		
	}

	
	

}
