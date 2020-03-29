package com.Veys.framework;

import java.awt.Graphics;
import java.util.ArrayList;

import com.Veys.objects.Block;
import com.Veys.window.Game;

public class Handler {

	public ArrayList<GameObject> object = new ArrayList<GameObject>();
	
	protected boolean right,left;
	
	public void tick(){
		
		for(int i=0;i<object.size();i++){
			GameObject temObject = object.get(i);
			temObject.tick();
		}
	}
	public void render(Graphics g){
		
		for(int i=0;i<object.size();i++){
			GameObject temObject = object.get(i);
			temObject.render(g);
		}
		
	}
	/*
	public void createLevel(){
		for(int i=0;i<Game.WIDTH;i+=32){
			object.add(new Block(i, Game.HEIGHT - 32, ID.Block));
		}
		for(int i=64;i<Game.WIDTH/2 - 32;i+=32){
			object.add(new Block(i, Game.HEIGHT - 128, ID.Block));
		}
		for(int i=Game.WIDTH/2 +32;i<Game.WIDTH - 64;i+=32){
			object.add(new Block(i, Game.HEIGHT - 222, ID.Block));
		}
	}
	*/
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

}
