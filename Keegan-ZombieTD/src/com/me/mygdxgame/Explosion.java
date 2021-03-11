package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explosion {

	public Texture explosiontexture;
	public float xposition;
	public float yposition;
	public float width;
	public float height;
	public boolean active;
	public int hp;
	
	public int framecol;
	public int framerow;
	public Animation explodeanimation;
	public TextureRegion[] explodeframes;
	public TextureRegion currentframe;
	public float statetime;
	
	public Explosion(float x, float y)
	{
		explosiontexture = new Texture(Gdx.files.internal("Explosion.png"));
		xposition= x;
		yposition = y;
		width = explosiontexture.getWidth()/6;
		height = explosiontexture.getHeight();
		active = true;
		hp = 10;
		
		framecol = 6;
		framerow = 1;
		TextureRegion[][] temp = TextureRegion.split(explosiontexture, explosiontexture.getWidth()/framecol, explosiontexture.getHeight()/framerow);
		explodeframes = new TextureRegion[framecol*framerow];
		int index = 0;
		for(int i = 0; i < framerow; i++)
		{
			for(int j = 0; j < framecol; j++)
			{
				explodeframes[index++] = temp[i][j];
			}
		}
		explodeanimation = new Animation(0.2f, explodeframes);
	}
	
	public void draw(SpriteBatch batch)
	{
		statetime += Gdx.graphics.getDeltaTime();
		currentframe = explodeanimation.getKeyFrame(statetime, true);
		batch.draw(currentframe, xposition - width/2, yposition -height/2);
	}
	
	public void update()
	{
		hp--;
		if(hp < 0)
		{
			active = false;
		}
	}
}
