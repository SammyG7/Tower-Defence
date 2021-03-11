package com.me.mygdxgame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class UI {

	public static int gold = 100;
	
	public static void draw(SpriteBatch batch)
	{
		BitmapFont font;
		
		font = new BitmapFont();
		font.setColor(Color.YELLOW);
		font.draw(batch, "Money :" + gold, 300, 550);
	}
}
