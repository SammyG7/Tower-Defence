package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;

public class Bullet {

	public Texture bullettexture;
	public float xposition;
	public float yposition;
	public float width;
	public float height;
	public float angle;
	public boolean active;
	public int life;
	
	public Bullet(float x, float y)
	{
		bullettexture = new Texture(Gdx.files.internal("Bullet.png"));
		xposition = x;
		yposition = y;
		width = bullettexture.getWidth();
		height = bullettexture.getHeight();
		this.active = true;
		life = 100;
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(bullettexture, xposition-width/2, yposition - height/2);
	}
	
	public void update()
	{
		getangle();
		xposition += (float) (Math.cos(angle)*5);
		yposition += (float) (Math.sin(angle)*5);
		
		life --;
		if (life < 0)
		{
			active = false;
		}
	}
	
	public void getangle()
	{
		if(!ZombieTD.zombielist.isEmpty() || !ZombieTD.pzombielist.isEmpty())
		{
			float xC, yC, xZ, yZ; 
			
			xC = xposition;
			yC = yposition;
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
			else
			{
			 	xZ = 2;
			 	yZ = 2;
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
					
			this.angle = (float) Math.toRadians(angle);
		}
	}
	
	public Circle getCircle()
	{
		return new Circle(xposition, yposition, width/2);
	}
	
}
