package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;

import basics.Assets;
import basics.Frame;
import basics.Frame.Textinfo;
import test.basics.Wave;
import test.basics.Being.Type1;
import test.framework.Level;
import util.Data;

public class MVZ_20020804 extends Game {

	public Stage stage;
	public Assets assets;
	SpriteBatch batch;
	Frame frame;
	Textinfo[] textinfos = new Textinfo[2];
	Level currentlevel;
	ArrayList<String> levelName = new ArrayList<String>();
	int currentLevelIndex = 0;
	String currentLevelName;
	boolean playingGame = false;

	InputProcessor stageInputProcessor = new InputProcessor() {

		@Override
		public boolean keyDown(int keycode) {

			switch(keycode)
			{
				case Keys.Z:
				{
					beginAGame();
					return true;
				}
				case Keys.RIGHT:
				{
					chooseALevel();
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean keyUp(int keycode) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean keyTyped(char character) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDown(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchUp(int screenX, int screenY, int pointer, int button) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean touchDragged(int screenX, int screenY, int pointer) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean scrolled(int amount) {
			// TODO Auto-generated method stub
			return false;
		}
		
	};
	
	@Override
	public void create () {

		assets = new Assets();
		batch = new SpriteBatch();
		stage = new Stage();
		frame = new Frame(assets.unit_Texture, Data.STAGE_WIDTH - 200 - 12, Data.STAGE_HEIGHT - 200 - 12, 1);
		frame.setX(100);
		frame.setY(100);

		textinfos[0] = frame.add(" * Thank you for playing MVZ(20200830version)!"
							  + "\n * Game rule:"
				              + "\n * Press right button to cancel."
				              + "\n * Press left button to check a monster, check a "
				              + "\n   zombie, or place a monster."
				              + "\n * Press 's' to slow down the game speed."
				              + "\n * Press 'z' to quick-start the next wave."
				              + "\n * Press 'x' to free a monster or to control "
				              + "\n   the hero."
				              + "\n * Press ESC to pause the game."
				              + "\n"
				              + "\n * Current level:"+currentLevelName
				              + "\n * Press 'right' to choose another level."
				              + "\n * Press 'z' to start the level!", assets.font_DTM_Mono, 10, Data.STAGE_HEIGHT - 200 - 12 - 10, 1, 1, 1, 1);
		
		levelName.add("ruins1");
		levelName.add("ruins2");
		levelName.add("ruins3");
		levelName.add("twoswitchpuzzle");
		goToMenu();
	}
	
	public void goToMenu()
	{
		playingGame = false;
		if(currentlevel != null)
		{
			currentlevel.dispose();
			currentlevel = null;
		}
		
		this.currentLevelName = levelName.get(currentLevelIndex);
        Gdx.input.setInputProcessor(stageInputProcessor);
	}
	
	public void backToMenuByWinningAGame()
	{
		goToMenu();
	}
	
	public void chooseALevel()
	{
		currentLevelIndex = (currentLevelIndex + 1)%levelName.size();
	}
	
	public void beginAGame()
	{
		switch(currentLevelName)
		{
			case "ruins1":
			{
				currentlevel = new Level(this, assets, assets.ruins1_Texture, "texts/ruins1.txt", 300, Type1.FRISK, 
				new Rectangle[]{
				new Rectangle(0, 0, 80, 960),
				new Rectangle(80, 0, 200, 120),
				new Rectangle(90, 120, 70, 85),
				new Rectangle(80, 200, 40, 360),
				new Rectangle(70, 725, 516, 240),
				new Rectangle(360, 0, 200, 120),
				new Rectangle(560, 0, 80, 960),
				new Rectangle(490, 120, 70, 85),
				new Rectangle(520, 200, 40, 360),
				new Rectangle(200, 600, 40, 80),
				new Rectangle(400, 600, 40, 80),
				new Rectangle(240, 640, 170, 35),
				}, 
				new Rectangle[] {},
				new Rectangle[]{
				new Rectangle(280, 0, 80, 400),
				new Rectangle(240, 400, 120, 40),
				new Rectangle(200, 440, 120, 40),
				new Rectangle(160, 480, 120, 40),
				new Rectangle(120, 520, 80, 40),
				new Rectangle(200, 520, 40, 80),
				new Rectangle(80, 560, 80, 80),
				new Rectangle(80, 640, 80, 80),
				new Rectangle(160, 560, 40, 120),
				new Rectangle(160, 680, 200, 40),
				},
				new Wave[]{
				new Wave(1, "Zombie(LV1) * 4, Zombie(LV2) * 1",
		        new float[]{
		        99999, 0.8f, 0.8f, 0.8f, 0.8f,
		        -1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        2, 1, 1, 1, 1,
		        -1}),
		        
		        new Wave(2, "Zombie(LV2) * 14", 
		        new float[]{
		        25, 0.7f, 0.7f, 0.7f, 0.7f, 
		        0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 
		        0.7f, 0.7f, 0.7f, 0.7f, -1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.DEFAULTED},
		        new int[] {
		        2, 2, 2, 2, 2,
		        2, 2, 2, 2, 2, 
		        2, 2, 2, 2, -1}),
		        
		        new Wave(3, "Cone Zombie(LV3) * 2", 
		        new float[]{
		        25, 0.2f, -1}, 
		        new Type1[]{
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.DEFAULTED}, 
		        new int[] {
		        3, 3, -1}),
		       
		        new Wave(4, "Zombie(LV1) * 35", 
		        new float[]{
		        25, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, -1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.DEFAULTED},
		        new int[] {
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1, -1
		        }),
		        
		        new Wave(5, "Bucket Zombie(LV5) * 1", 
		        new float[]{
		        25, -1}, 
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, Type1.DEFAULTED},
		        new int[] {
		        5, -1}),
		        
		        new Wave(6, "Bucket Zombie(LV5) * 4", 
		        new float[]{
		        35, 1.3f, 1.3f, 1f, -1}, 
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.DEFAULTED},
		        new int[] {5, 5, 5, 5, -1}),
		        
		        new Wave(7, "Cone Zombie(LV1) * 10", 
		        new float[]{
		        35, 0.6f, 0.4f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, -1}, 
		        new Type1[]{
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.DEFAULTED},
		        new int[] {
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1, -1}),
		        
		        new Wave(8, "Bucket Zombie(LV6) * 4", 
		        new float[]{
		        40, 0.2f, 4f, 0.2f, -1}, 
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.DEFAULTED},
		        new int[] {
		        6, 6, 6, 6, -1}),
		        
		        new Wave(9, "Cone Zombie(LV2) * 10, Bucket Zombie(LV2) * 10", 
		        new float[]{
		        40, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.5f, 0.5f, 0.5f, 0.5f, 
		        0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -1}, 
		        new Type1[]{
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.DEFAULTED},
		        new int[] {
		        2, 2, 2, 2, 2,
		        2, 2, 2, 2, 2,
		        2, 2, 2, 2, 2,
		        2, 2, 2, 2, 2, -1}),
		        
		        new Wave(10, "Zombie(LV9) * 7", 
		        new float[]{
		        60, 1f, 1f, 1f, 1f, 1f, 1f, -1},
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.DEFAULTED},
		        new int[]{
				9, 9, 9, 9, 9, 9, 9, -1})
				});
				
				break;
			}
			case "twoswitchpuzzle":
			{
				currentlevel = new Level(this, assets, assets.Twoswitchpuzzle_Texture, "texts/Twoswitchpuzzle.txt", 300, Type1.FRISK, 
				new Rectangle[] {new Rectangle(80, 320, 1320, 120), new Rectangle(0, 0, 80, 480), new Rectangle(80, 440, 1400, 40), new Rectangle(320, 0, 1160, 40), new Rectangle(80, 0, 160, 40)},
				new Rectangle[] {new Rectangle(485, 200, 67, 120), new Rectangle(968, 200, 67, 120), new Rectangle(485, 40, 67, 80), new Rectangle(968, 40, 67, 80)},
				new Rectangle[] {new Rectangle(240, 0, 80, 200), new Rectangle(240, 120, 1240, 80)},
				new Wave[]{
				new Wave(1, "Zombie(LV1) * 4, Zombie(LV2) * 1",
		        new float[]{
		        99999, 0.8f, 0.8f, 0.8f, 0.8f,
		        -1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        2, 1, 1, 1, 1,
		        -1}),
		        
		        new Wave(2, "Zombie(LV1) * 14", 
		        new float[]{
		        25, 0.6f, 0.6f, 0.6f, 0.6f, 
		        0.6f, 0.6f, 0.6f, 0.6f, 0.6f, 
		        0.5f, 0.5f, 0.5f, 0.5f, -1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.DEFAULTED},
		        new int[] {
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1, 
		        1, 1, 1, 1, -1}),
		        
		        new Wave(3, "Zombie(LV1) * 15", 
		        new float[]{
		        25, 0.25f, 0.25f, 0.25f, 0.25f, 
		        0.25f, 0.25f, 0.25f, 0.25f, 0.25f, 
		        0.25f, 0.25f, 0.25f, 0.25f, 0.25f, -1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1, -1}),
		       
		        new Wave(4, "Zombie(LV1) * 20", 
		        new float[]{
		        25, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, -1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.DEFAULTED},
		        new int[] {
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1, -1
		        }),
		        
		        new Wave(5, "Zombie(LV1) * 40", 
		        new float[]{
		        25, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.2f, 
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 
		        0.2f, 0.2f, 0.1f, 0.1f, 0.1f, 
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f, -1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.DEFAULTED},
		        new int[] {
		        1, 1, 1, 1, 1, 
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1, 
		        1, 1, 1, 1, 1, 
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1, -1}),
		        
		        new Wave(6, "Bucket Zombie(LV1) * 3", 
		        new float[]{
		        35, 1f, 1f, -1}, 
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.DEFAULTED},
		        new int[] {1, 1, 1, -1}),
		        
		        new Wave(7, "Cone Zombie(LV1) * 10", 
		        new float[]{
		        35, 0.6f, 0.4f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, -1}, 
		        new Type1[]{
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.DEFAULTED},
		        new int[] {
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1, -1}),
		        
		        new Wave(8, "Bucket Zombie(LV1) * 4, Bucket Zombie(LV2) * 4", 
		        new float[]{
		        40, 0.2f, 0.2f, 0.2f, 0.2f, 
		        0.2f, 0.2f, 0.2f, -1}, 
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.DEFAULTED},
		        new int[] {
		        1, 1, 1, 1, 2,
		        2, 2, 2, -1}),
		        
		        new Wave(9, "Cone Zombie(LV2) * 10, Bucket Zombie(LV2) * 10", 
		        new float[]{
		        40, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.5f, 0.5f, 0.5f, 0.5f, 
		        0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -1}, 
		        new Type1[]{
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.DEFAULTED},
		        new int[] {
		        2, 2, 2, 2, 2,
		        2, 2, 2, 2, 2,
		        2, 2, 2, 2, 2,
		        2, 2, 2, 2, 2, -1}),
		        
		        new Wave(10, "Zombie(LV3) * 30, Cone Zombie(LV3) * 30, \n * Bucket Zombie(LV3) * 30", 
		        new float[]{
		        60, 2f, 2f, 2f, 2f, 
		        2f, 2f, 2f, 2f, 2f, 
		        1f, 1f, 1f, 1f, 1f, 
		        1f, 1f, 1f, 1f, 1f, 
		        0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 
		        0.05f, 0.05f, 0.05f, 0.05f, 0.05f, 
		        5f, 4f, 3f, 2f, 1f, 
		        0.4f, 0.4f, 0.4f, 0.4f, 0.4f, 
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f, 
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f, 
		        0.05f, 0.05f, 0.05f, 0.05f, 0.05f, -1},
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE,
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE,
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.DEFAULTED},
		        new int[]{
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3,
				3, 3, 3, 3, 3, -1}),
		        
		        new Wave(11, "Bucket Zombie(LV10) * 3", 
		        new float[]{
		        40, 0.05f, 0.05f, -1}, 
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.DEFAULTED},
		        new int[] {
		        10, 10, 10, -1})
				});
				break;
			}
			case "ruins2":
			{
				currentlevel = new Level(this, assets, assets.ruins2_Texture, "texts/ruins2.txt", 300, Type1.FRISK, 
				new Rectangle[]{
				new Rectangle(0, 0, 120, 680),
				new Rectangle(120, 0, 520, 120),
				new Rectangle(522, 120, 120, 80),
				new Rectangle(0, 760, 120, 200),
				new Rectangle(120, 800, 520, 160),
				new Rectangle(522, 280, 120, 520),
				}, 
				new Rectangle[] {},
				new Rectangle[]{
				new Rectangle(0, 680, 360, 80),
				new Rectangle(280, 320, 80, 360),
				new Rectangle(360, 200, 80, 200),
				new Rectangle(440, 200, 200, 80),
				},
				new Wave[]{
				new Wave(1, "Zombie(LV1) * 80",
		        new float[]{
		        99999, 0.8f, 0.8f, 0.8f, 0.8f,
		        0.8f, 0.8f, 0.8f, 0.8f, 0.8f,
		        0.7f, 0.7f, 0.7f, 0.7f, 0.7f,
		        0.7f, 0.7f, 0.7f, 0.7f, 0.7f,
		        0.6f, 0.6f, 0.6f, 0.6f, 0.6f,
		        0.6f, 0.6f, 0.6f, 0.6f, 0.6f,
		        0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
		        0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
		        0.4f, 0.4f, 0.4f, 0.4f, 0.4f,
		        0.4f, 0.4f, 0.4f, 0.4f, 0.4f,
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f,
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f,
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
		        -1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        -1}),
				
				new Wave(2, "Zombie(LV5) * 80",
		        new float[]{
		        10, 0.8f, 0.8f, 0.8f, 0.8f,
		        0.8f, 0.8f, 0.8f, 0.8f, 0.8f,
		        0.7f, 0.7f, 0.7f, 0.7f, 0.7f,
		        0.7f, 0.7f, 0.7f, 0.7f, 0.7f,
		        0.6f, 0.6f, 0.6f, 0.6f, 0.6f,
		        0.6f, 0.6f, 0.6f, 0.6f, 0.6f,
		        0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
		        0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
		        0.4f, 0.4f, 0.4f, 0.4f, 0.4f,
		        0.4f, 0.4f, 0.4f, 0.4f, 0.4f,
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f,
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f,
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
		        -1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        -1}),
				
				new Wave(3, "Cone Zombie(LV5) * 80",
		        new float[]{
		        10, 0.8f, 0.8f, 0.8f, 0.8f,
		        0.8f, 0.8f, 0.8f, 0.8f, 0.8f,
		        0.7f, 0.7f, 0.7f, 0.7f, 0.7f,
		        0.7f, 0.7f, 0.7f, 0.7f, 0.7f,
		        0.6f, 0.6f, 0.6f, 0.6f, 0.6f,
		        0.6f, 0.6f, 0.6f, 0.6f, 0.6f,
		        0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
		        0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
		        0.4f, 0.4f, 0.4f, 0.4f, 0.4f,
		        0.4f, 0.4f, 0.4f, 0.4f, 0.4f,
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f,
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f,
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
		        -1}, 
		        new Type1[]{
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        -1}),
				
				new Wave(4, "Bucket Zombie(LV5) * 80",
		        new float[]{
		        10, 0.8f, 0.8f, 0.8f, 0.8f,
		        0.8f, 0.8f, 0.8f, 0.8f, 0.8f,
		        0.7f, 0.7f, 0.7f, 0.7f, 0.7f,
		        0.7f, 0.7f, 0.7f, 0.7f, 0.7f,
		        0.6f, 0.6f, 0.6f, 0.6f, 0.6f,
		        0.6f, 0.6f, 0.6f, 0.6f, 0.6f,
		        0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
		        0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
		        0.4f, 0.4f, 0.4f, 0.4f, 0.4f,
		        0.4f, 0.4f, 0.4f, 0.4f, 0.4f,
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f,
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f,
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
		        -1}, 
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        -1}),
				
				new Wave(5, "Bucket Zombie(LV10) * 80",
		        new float[]{
		        30, 0.6f, 0.6f, 0.6f, 0.6f,
		        0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
		        0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
		        0.4f, 0.4f, 0.4f, 0.4f, 0.4f,
		        0.4f, 0.4f, 0.4f, 0.4f, 0.4f,
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
		        0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f,
		        0.2f, 0.2f, 0.2f, 0.2f, 0.2f,
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
		        0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
		        0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
		        0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
		        0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
		        0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
		        -1}, 
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        10, 10, 10, 10, 10,
		        -1})}
				);
				break;
			}
			case "ruins3":
			{
				currentlevel = new Level(this, assets, assets.ruins3_Texture, "texts/ruins3.txt", 100, Type1.FRISK, 
				new Rectangle[]{
				new Rectangle(0, 0, 480, 440),
				new Rectangle(0, 440, 80, 280),
				new Rectangle(80, 520, 1080, 200),
				new Rectangle(720, 360, 430, 160),
				new Rectangle(480, 0, 680, 120),
				new Rectangle(1080, 120, 80, 120),
				new Rectangle(1080, 270, 80, 90),
				}, 
				new Rectangle[] {},
				new Rectangle[]{
				new Rectangle(80, 440, 640, 80),
				new Rectangle(640, 120, 80, 320),
				new Rectangle(720, 120, 120, 40),
				new Rectangle(760, 160, 80, 200),
				new Rectangle(840, 240, 120, 120),
				new Rectangle(880, 120, 80, 120),
				new Rectangle(960, 120, 120, 40),
				new Rectangle(1040, 160, 40, 120),
				new Rectangle(1080, 230, 80, 40),
				},
				new Wave[]{
				new Wave(1, "Zombie(LV1) * 25",
		        new float[]{
		        99999, 0.8f, 0.8f, 0.8f, 0.8f,
		        0.8f, 0.8f, 0.8f, 0.8f, 0.8f,
		        0.7f, 0.7f, 0.7f, 0.7f, 0.7f,
		        0.7f, 0.7f, 0.7f, 0.7f, 0.7f,
		        0.7f, 0.7f, 0.7f, 0.7f, 0.7f, 
		        -1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE,  
		        Type1.DEFAULTED}, 
		        new int[] {
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        -1}),
				
				new Wave(2, "Cone Zombie(LV4) * 1, Cone Zombie(LV8) * 1",
		        new float[]{
		        30, 8, -1}, 
		        new Type1[]{
		        Type1.CONE_ZOMBIE,  Type1.CONE_ZOMBIE, Type1.DEFAULTED}, 
		        new int[] {
		        4, 7, 
		        -1}),
				
				new Wave(3, "Zombie(LV1) * 13, Cone Zombie(LV1) * 1",
		        new float[]{
		        50, 0.05f, 0.05f, 0.05f, 0.05f,
		        0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
		        0.05f, 0.05f, 0.05f, 0.05f,
		        -1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.CONE_ZOMBIE,
		        Type1.DEFAULTED}, 
		        new int[] {
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 
		        -1}),
				
				new Wave(4, "Zombie(LV3) * 12",
		        new float[]{
				50, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f,
				-1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        3, 3, 3, 3, 3,
		        3, 3, 3, 3, 3,
		        3, 3, 
		        -1}),
				
				new Wave(5, "Zombie(LV4) * 12",
		        new float[]{
				50, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f,
				-1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        4, 4, 4, 4, 4,
		        4, 4, 4, 4, 4,
		        4, 4, 
		        -1}),
				
				new Wave(6, "Bucket Zombie(LV7) * 1",
		        new float[]{
		        30, 
		        -1}, 
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        7, 
		        -1}),
				
				new Wave(7, "Cone Zombie(LV1) * 9",
		        new float[]{
				50, 1f, 1f, 1f, 1f,
				1f, 1f, 1f, 1f,
				-1}, 
		        new Type1[]{
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE,
		        Type1.DEFAULTED}, 
		        new int[] {
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 
		        -1}),
				
				new Wave(8, "Zombie(LV4) * 24, Cone Zombie(LV5) * 1",
		        new float[]{
				50, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f, 0.05f, 0.05f, 1f,
				-1}, 
		        new Type1[]{
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, 
		        Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        4, 4, 4, 4, 4,
		        4, 4, 4, 4, 4,
		        4, 4, 4, 4, 4,
		        4, 4, 4, 4, 4,
		        4, 4, 4, 4, 5,
		        -1}),
				
				new Wave(9, "Bucket Zombie(LV2) * 6",
		        new float[]{
				50, 2f, 2f, 2f, 2f,
				2f, 
				-1}, 
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        2, 2, 2, 2, 2,
		        2, 2, 
		        -1}),
				
				new Wave(10, "Cone Zombie(LV6) * 12",
		        new float[]{
				50, 1f, 1f, 1f, 1f,
				1f, 1f, 1f, 1f, 1f,
				1f, 1f,
				-1}, 
		        new Type1[]{
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        6, 6, 6, 6, 6,
		        6, 6, 6, 6, 6,
		        6, 6, 
		        -1}),
				
				new Wave(11, "Cone Zombie(LV2) * 12",
		        new float[]{
				50, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f,
				-1}, 
		        new Type1[]{
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.CONE_ZOMBIE, Type1.CONE_ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        2, 2, 2, 2, 2,
		        2, 2, 2, 2, 2,
		        2, 2, 
		        -1}),
				
				new Wave(12, "Bucket Zombie(LV2) * 18",
		        new float[]{
				50, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f, 0.05f, 0.05f, 0.05f,
				0.05f, 0.05f, 0.05f, 
				-1}, 
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE,
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE,
		        Type1.DEFAULTED}, 
		        new int[] {
		        2, 2, 2, 2, 2,
		        2, 2, 2, 2, 2,
		        2, 2, 2, 2, 2,
		        2, 2, 2, 
		        -1}),
				
				new Wave(13, "Bucket Zombie(LV5) * 20, Bucket Zombie(LV6) * 20, Bucket Zombie(LV7) * 20, Bucket Zombie(LV8) * 20",
		        new float[]{
				50, 1f, 1f, 1f, 1f,
				1f, 1f, 1f, 1f, 1f,
				0.7f, 0.7f, 0.7f, 0.7f, 0.7f,
				0.7f, 0.7f, 0.7f, 0.7f, 0.7f,
				0.6f, 0.6f, 0.6f, 0.6f, 0.6f,
				0.6f, 0.6f, 0.6f, 0.6f, 0.6f,
				0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
				0.5f, 0.5f, 0.5f, 0.5f, 0.5f,
				0.4f, 0.4f, 0.4f, 0.4f, 0.4f,
				0.4f, 0.4f, 0.4f, 0.4f, 0.4f,
				0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
				0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
				0.2f, 0.2f, 0.2f, 0.2f, 0.2f,
				0.2f, 0.2f, 0.2f, 0.2f, 0.2f,
				0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
				0.1f, 0.1f, 0.1f, 0.1f, 0.1f,
				-1}, 
		        new Type1[]{
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, Type1.BUCKET_ZOMBIE, 
		        Type1.DEFAULTED}, 
		        new int[] {
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        5, 5, 5, 5, 5,
		        6, 6, 6, 6, 6,
		        6, 6, 6, 6, 6,
		        6, 6, 6, 6, 6,
		        6, 6, 6, 6, 6,
		        7, 7, 7, 7, 7,
		        7, 7, 7, 7, 7,
		        7, 7, 7, 7, 7,
		        7, 7, 7, 7, 7,
		        8, 8, 8, 8, 8,
		        8, 8, 8, 8, 8,
		        8, 8, 8, 8, 8,
		        8, 8, 8, 8, 8,
		        -1})}
				);
				break;
			}
		}
		playingGame = true;
		this.setScreen(currentlevel);
	}

	@Override
	public void render () {

		if(playingGame)
		{
			super.render();
			return;
		}
		batch.begin();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		this.currentLevelName = levelName.get(currentLevelIndex);
		frame.update(textinfos[0], 
				" * Thank you for playing MVZ(20200830version)!"
			  + "\n * Game rule:"
              + "\n * Press right button to cancel."
              + "\n * Press left button to check a monster, check a "
              + "\n   zombie, or place a monster."
              + "\n * Press 's' to slow down the game speed."
              + "\n * Press 'z' to quick-start the next wave."
              + "\n * Press 'x' to free a monster or to control "
              + "\n   the hero."
              + "\n * Press ESC to pause the game."
              + "\n"
              + "\n * Current level:"+currentLevelName
              + "\n * Press 'right' to choose another level."
              + "\n * Press 'z' to start the level!");
		frame.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		currentlevel.dispose();
		assets.dispose();
	}

	
	@Override
	public void resize (int width, int height) {
	}
}