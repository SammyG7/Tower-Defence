package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;


public class Cannon {

	public Sprite cannonsprite;
	public Texture cannontexture; 
	public float xposition; 
	public float yposition;
	public float width;
	public float height;
	public float angle;
	public int firecounter;
	public int firerate = 20;
	
	Sound firefx;
	
	public Cannon(float x, float y) {


		cannontexture = new Texture(Gdx.files.internal("Cannon.png"));
		cannonsprite = new Sprite(cannontexture);
		xposition = x;
		yposition = y;
		width = cannontexture.getWidth();
		height = cannontexture.getHeight();
		
		firefx = Gdx.audio.newSound(Gdx.files.internal("Bullet.mp3"));
		
		cannonsprite.setPosition(xposition - width/2, yposition - height/2);

		grid();
	}
	
	public void draw(SpriteBatch batch)
	{
		cannonsprite.draw(batch);
	}
	
	public void update()
	{
		getangle();
		cannonsprite.setRotation(angle);
		fire();
		
	}
	
	public void fire()
	{
		if (!ZombieTD.zombielist.isEmpty())
		{
			firecounter ++;
			if(firecounter > firerate)
			{
				ZombieTD.bulletlist.add(new Bullet(xposition, yposition));
				firecounter = 0;
				
				firefx.play();
			}
			
		}
	}
	
	
	public void getangle()
	{
		if(!ZombieTD.zombielist.isEmpty() || !ZombieTD.pzombielist.isEmpty())
		{
			float xC, yC, xZ, yZ; 
			
			xC = xposition;
			yC = yposition;
			xZ = 0;
		 	yZ = 0;
		 	//ZombieTD.zombielist.get(0).infront && 
			if(!ZombieTD.pzombielist.isEmpty())
			{
				xZ = ZombieTD.pzombielist.get(0).xposition;
				yZ = ZombieTD.pzombielist.get(0).yposition;
			}
			else if(!ZombieTD.zombielist.isEmpty())
			{
				xZ = ZombieTD.zombielist.get(0).xposition;
				yZ = ZombieTD.zombielist.get(0).yposition;
			}
			
			float angle;
			
			angle = (float) Math.atan((yC - yZ) / (xC - xZ));
			angle = (float) Math.toDegrees(angle);
			
			if(xC >= xZ && yC <= yZ)
			{
				angle = (90 + angle) + 90;
			}
			else if(xC >= xZ && yC >= yZ)
			{
				angle = (angle - 90) - 90;
			}
					
			this.angle = angle;
		}
	}
	
	public void grid()
	{
		int Xfirstdigit = 0;
		int Xlasttwodigits = (int) (xposition % 100);
		
		int Yfirstdigit = 0;
		int Ylasttwodigits = (int) (yposition % 100);
		
		if(xposition >= 100);
			Xfirstdigit = Integer.parseInt(Integer.toString((int) xposition).substring(0, 1));
		if(Xlasttwodigits < 50)
		{
			xposition = 25 + Xfirstdigit*100;
		}
		else if(xposition >= 50)
		{
			xposition = 75 + Xfirstdigit*100;
		}
		
		if(yposition >= 100);
		Yfirstdigit = Integer.parseInt(Integer.toString((int) yposition).substring(0, 1));
		if(Ylasttwodigits < 50)
		{
			yposition = 25 + Yfirstdigit*100;
		}
		else if(yposition >= 50)
		{
			yposition = 75 + Yfirstdigit*100;
		}
		
		cannonsprite.setPosition(xposition - width/2, yposition - height/2);
	}
}


