package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Launcher {
	
	public Sprite mlsprite;
	public Texture mltexture; 
	public float xposition; 
	public float yposition;
	public float width;
	public float height;
	public float angle;
	public int firecounter;
	public int firerate = 20;
	public int aimx;
	public int aimy;
	
	public int framecol;
	public int framerow;
	public Animation shootanimation;
	public TextureRegion[] shootframes;
	public TextureRegion currentframe;
	public float statetime;
	
	public Launcher(float x, float y)
	{
		mltexture = new Texture(Gdx.files.internal("1launcher.png"));
		mlsprite = new Sprite(mltexture);
		xposition = x;
		yposition = y;
		width = mltexture.getWidth();
		height = mltexture.getHeight();
		
		mlsprite.setPosition(xposition - width/2, yposition - height/2);
		
		grid();
	}
	
	public void frames()
	{
		framecol = 7;
		framerow = 2;
		TextureRegion[][] temp = TextureRegion.split(mltexture, 1008/framecol - 17, mltexture.getHeight()/framerow);
		
		shootframes = new TextureRegion[framecol*framerow];
		int index = 0;
		for(int i = 0; i < framerow; i++)
		{
			for(int j = 0; j < framecol; j++)
			{
				shootframes[index++] = temp[i][j];
			}
		}
		shootanimation = new Animation(0.2f, shootframes);

	}
	
	public void draw(SpriteBatch batch)
	{
		mlsprite.draw(batch);
	}
	
	public void update()
	{
		//getangle();
		mlsprite.setRotation(angle);
	}
	
	public void getangle(int x, int y)
	{
			float xC, yC, xZ, yZ; 
			
			xC = xposition;
			yC = yposition;
			xZ = x;
		 	yZ = y;
			
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
		
		mlsprite.setPosition(xposition - width/2, yposition - height/2);
	}
	
	public void fire()
	{
		ZombieTD.mislist.add(new Missile(xposition, yposition));
	}
}
