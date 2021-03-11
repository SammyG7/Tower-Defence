package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Missile {

	public Texture missiletexture;
	public Sprite missilesprite;
	public float xposition;
	public float yposition;
	public float width;
	public float height;
	public float angle;
	public boolean active;
	public int aimx;
	public int aimy;
	
	public Missile(float x, float y)
	{
		missiletexture = new Texture(Gdx.files.internal("mis1.png"));
		missilesprite = new Sprite(missiletexture);
		xposition = x;
		yposition = y;
		width = missiletexture.getWidth();
		height = missiletexture.getHeight();
		this.active = true;
		aimx = 0;
		aimy = 0;
		
		missilesprite.setPosition(xposition - width/2, yposition - height/2);
	}
	
	public void draw(SpriteBatch batch)
	{
		batch.draw(missilesprite, xposition, yposition);
	}
	
	public void update()
	{
		getangle();
		missilesprite.setRotation(angle);
		xposition += (float) (Math.cos(angle)*5);
		yposition += (float) (Math.sin(angle)*5);
		
	}
	
	public void getangle()
	{
		if(!ZombieTD.zombielist.isEmpty() || !ZombieTD.pzombielist.isEmpty())
		{
			float xC, yC, xZ, yZ; 
			
			xC = xposition;
			yC = yposition;
			xZ = aimx;
		 	yZ = aimy;
		 	//ZombieTD.zombielist.get(0).infront && 
					
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
}
