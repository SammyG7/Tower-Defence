package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;

public class Zombie {
	
	public Texture zombietexture;
	public float xposition;
	public float yposition;
	public float width;
	public float height;
	public float speed;
	public boolean active;
	public boolean infront;
	public int hp;
	
	public int framecol;
	public int framerow;
	public Animation walkanimation;
	public TextureRegion[] walkframes;
	public TextureRegion currentframe;
	public float statetime;
	
	
	public Zombie(float x, float y)
	{
		this.active = true;
		zombietexture = new Texture(Gdx.files.internal("Zombies.png"));
		xposition = x;
		yposition = y;
		width = zombietexture.getWidth()/4;
		height = zombietexture.getHeight();
		speed = 1;
		hp = 10;
		
		framecol = 4;
		framerow = 1;
		TextureRegion[][] temp = TextureRegion.split(zombietexture, zombietexture.getWidth()/framecol, zombietexture.getHeight()/framerow);
		
		walkframes = new TextureRegion[framecol*framerow];
		int index = 0;
		for(int i = 0; i < framerow; i++)
		{
			for(int j = 0; j < framecol; j++)
			{
				walkframes[index++] = temp[i][j];
			}
		}
		walkanimation = new Animation(0.2f, walkframes);
	}
	
	public void draw(SpriteBatch batch)
	{
		statetime += Gdx.graphics.getDeltaTime();
		currentframe = walkanimation.getKeyFrame(statetime, true);
		batch.draw(currentframe, xposition -width/2, yposition - height/2);
	}
	
	public void update()
	{
		xposition -= speed;

		if(xposition < -20)
		{
			active = false;
		}
	}
	
	public Rectangle getRectangle()
	{
		return new Rectangle(xposition - width/2, yposition - height/2, width, height);
	}
	
	public void takeDamage()
	{
		hp--;
		if(hp < 0)
		{
			active = false;
		}
	}
}
