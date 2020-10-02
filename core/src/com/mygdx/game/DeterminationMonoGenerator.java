package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeBitmapFontData;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class DeterminationMonoGenerator extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

   private BitmapFont Font;

   FreeTypeBitmapFontData fontData;
	
	@Override
	public void create () {
		
		boolean flip = false;
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		//Font = new BitmapFont(Gdx.files.internal("data/arial-15.fnt"), flip);
	    FileHandle fontFile = Gdx.files.internal("DTM-Mono.otf");

	    FreeTypeFontGenerator generator = new FreeTypeFontGenerator(fontFile);

	    FreeTypeFontParameter parameter = new FreeTypeFontParameter();
	    parameter.size = 15;
	    parameter.flip = flip;
	    parameter.genMipMaps = true;

	    Font = generator.generateFont(parameter);
	    generator.dispose();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		Font.draw(batch, "determination", 200, 200);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
