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
import test.basics.ZIIW;
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
					rRotateALevel();
					return true;
				}
				case Keys.LEFT:
				{
					lRotateALevel();
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
	
	String menuMessage()
	{
		return        " * Thank you for playing MVZ(20201222version)!"
				  + "\n * Game rule:"
	              + "\n * Press right button to cancel."
	              + "\n * Press left button to check a monster, check a "
	              + "\n   zombie, or place a monster."
	              + "\n * Press 'S' to slow down the game speed."
	              + "\n * Press 'Z' to upgrade the monsters."
	              + "\n * Press 'X' to free a monster or to control "
	              + "\n   the hero."
	              + "\n * Press 'C' to quick-start the next wave."
	              + "\n * Press ESC to pause the game."
	              + "\n"
	              + "\n * Current level:"+currentLevelName
	              + "\n * Press 'right' to choose another level."
	              + "\n * Press 'Z' to start the level!";
	}
	//zi: -> ZIIW
	//创造新的僵尸信息
	ZIIW zi(float gapTime, Type1 type, int LV, int routeID)
	{
		return new ZIIW(gapTime, type, (short)LV, (short)routeID);
	}
	@Override
	public void create () {
		assets = new Assets();
		batch = new SpriteBatch();
		stage = new Stage();
		frame = new Frame(assets.unit_Texture, Data.STAGE_WIDTH - 200 - 12, Data.STAGE_HEIGHT - 200 - 12, 1);
		frame.setX(100);
		frame.setY(100);

		textinfos[0] = frame.add(menuMessage(), assets.font_DTM_Mono, 10, Data.STAGE_HEIGHT - 200 - 12 - 10, 1, 1, 1, 1);
		
		levelName.add("ruins1");
		levelName.add("ruins2");
		levelName.add("ruins3");
		levelName.add("twoswitchpuzzle");
		levelName.add("debuglevel");
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
	
	public void rRotateALevel()
	{
		currentLevelIndex = (currentLevelIndex + 1)%levelName.size();
	}
	public void lRotateALevel()
	{
		currentLevelIndex -= 1; 
		if(currentLevelIndex == -1)
			currentLevelIndex = levelName.size()-1;
	}
	
	public void beginAGame()
	{
		switch(currentLevelName)
		{
			case "ruins1":
			{
				currentlevel = new Level(this, assets, assets.ruins1_Texture, new String[] {"texts/ruins1.1.txt", "texts/ruins1.2.txt"}, 300, Type1.FRISK, 
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
				new Rectangle(240, 400, 160, 40),
				new Rectangle(200, 440, 240, 40),
				new Rectangle(160, 480, 120, 40),
				new Rectangle(120, 520, 80, 40),
				new Rectangle(200, 520, 40, 80),
				new Rectangle(80, 560, 80, 80),
				new Rectangle(80, 640, 80, 80),
				new Rectangle(160, 560, 40, 120),
				new Rectangle(160, 680, 200, 40),
				new Rectangle(360, 480, 120, 40),
				new Rectangle(440, 520, 80, 40),
				new Rectangle(400, 520, 40, 80),
				new Rectangle(480, 560, 80, 80),
				new Rectangle(480, 640, 80, 80),
				new Rectangle(440, 560, 40, 120),
				new Rectangle(280, 680, 200, 40),
				},
				new Wave[]{
				new Wave(1, "Zombie(LV1) * 4, Zombie(LV2) * 1",
				new ZIIW[]{zi(99999, Type1.ZOMBIE, 2, 0), zi(0.8f, Type1.ZOMBIE, 1, 1), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 1),
						   zi(0.8f, Type1.ZOMBIE, 1, 0), null}),
				new Wave(2, "Zombie(LV2) * 14",
				new ZIIW[]{zi(25, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), zi(0.7f, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), 
						   zi(0.7f, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), zi(0.7f, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), 
						   zi(0.7f, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), zi(0.7f, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), 
						   zi(0.7f, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), null}),
				new Wave(3, "Cone Zombie(LV3) * 2",
				new ZIIW[]{zi(25, Type1.CONE_ZOMBIE, 3, 0), zi(0.02f, Type1.CONE_ZOMBIE, 3, 1), null}),
				new Wave(4, "Zombie(LV1) * 35",
				new ZIIW[]{zi(25, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), null}),
				new Wave(5, "Bucket Zombie(LV5) * 1",
				new ZIIW[]{zi(25, Type1.BUCKET_ZOMBIE, 5, 0), null}),
				new Wave(6, "Bucket Zombie(LV5) * 4",
				new ZIIW[]{zi(35, Type1.BUCKET_ZOMBIE, 5, 0), zi(1.3f, Type1.BUCKET_ZOMBIE, 5, 1), zi(1.3f, Type1.BUCKET_ZOMBIE, 5, 0), zi(1.3f, Type1.BUCKET_ZOMBIE, 5, 1),
						   null}),
				new Wave(7, "Cone Zombie(LV1) * 10",
				new ZIIW[]{zi(35, Type1.CONE_ZOMBIE, 1, 0), zi(0.6f, Type1.CONE_ZOMBIE, 1, 1), zi(0.4f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 1), 
						   zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 1), zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 1), 
						   zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 1), null}),
				new Wave(8, "Bucket Zombie(LV6) * 4",
				new ZIIW[]{zi(40, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 6, 1), zi(4f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 6, 1), 
						   null}),
				new Wave(9, "Cone Zombie(LV2) * 10, Bucket Zombie(LV2) * 10",
				new ZIIW[]{zi(40, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 1), zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 1), 
						   zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 1), zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 1), 
						   zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 1), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 1), 
						   zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 1), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 1), 
						   zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 1), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 1), 
						   null}),
				new Wave(10, "Zombie(LV9) * 7",
				new ZIIW[]{zi(60, Type1.BUCKET_ZOMBIE, 9, 0), zi(1f, Type1.BUCKET_ZOMBIE, 9, 1), zi(1f, Type1.BUCKET_ZOMBIE, 9, 0), zi(1f, Type1.BUCKET_ZOMBIE, 9, 1), 
						   zi(1f, Type1.BUCKET_ZOMBIE, 9, 0), zi(1f, Type1.BUCKET_ZOMBIE, 9, 1), zi(1f, Type1.BUCKET_ZOMBIE, 9, 0), null})
				});
				
				break;
			}
			case "debuglevel":
			{
				currentlevel = new Level(this, assets, assets.ruins1_Texture, new String[] {"texts/ruins1.1.txt", "texts/ruins1.2.txt"}, 9990, Type1.FRISK, 
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
				
				//new Rectangle(280, 0, 80, 400),
				new Rectangle(280, 400, 120, 40),
				new Rectangle(320, 440, 120, 40),
				new Rectangle(360, 480, 120, 40),
				new Rectangle(440, 520, 80, 40),
				new Rectangle(400, 520, 40, 80),
				new Rectangle(480, 560, 80, 80),
				new Rectangle(480, 640, 80, 80),
				new Rectangle(440, 560, 40, 120),
				new Rectangle(280, 680, 200, 40),
				},
				new Wave[]{
				new Wave(1, "Zombie(LV1) * 4, Zombie(LV2) * 1",
				new ZIIW[]{zi(99999, Type1.ZOMBIE, 2, 0), zi(0.8f, Type1.ZOMBIE, 1, 1), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 1),
						   zi(0.8f, Type1.ZOMBIE, 1, 0), null}),
				new Wave(2, "Zombie(LV2) * 14",
				new ZIIW[]{zi(25, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), zi(0.7f, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), 
						   zi(0.7f, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), zi(0.7f, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), 
						   zi(0.7f, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), zi(0.7f, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), 
						   zi(0.7f, Type1.ZOMBIE, 2, 0), zi(0.7f, Type1.ZOMBIE, 2, 1), null}),
				new Wave(3, "Cone Zombie(LV3) * 2",
				new ZIIW[]{zi(25, Type1.CONE_ZOMBIE, 3, 0), zi(0.02f, Type1.CONE_ZOMBIE, 3, 1), null}),
				new Wave(4, "Zombie(LV1) * 35",
				new ZIIW[]{zi(25, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), 
						zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 1), zi(0.3f, Type1.ZOMBIE, 1, 0), null}),
				new Wave(5, "Bucket Zombie(LV5) * 1",
				new ZIIW[]{zi(25, Type1.BUCKET_ZOMBIE, 5, 0), null}),
				new Wave(6, "Bucket Zombie(LV5) * 4",
				new ZIIW[]{zi(35, Type1.BUCKET_ZOMBIE, 5, 0), zi(1.3f, Type1.BUCKET_ZOMBIE, 5, 1), zi(1.3f, Type1.BUCKET_ZOMBIE, 5, 0), zi(1.3f, Type1.BUCKET_ZOMBIE, 5, 1),
						   null}),
				new Wave(7, "Cone Zombie(LV1) * 10",
				new ZIIW[]{zi(35, Type1.CONE_ZOMBIE, 1, 0), zi(0.6f, Type1.CONE_ZOMBIE, 1, 1), zi(0.4f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 1), 
						   zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 1), zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 1), 
						   zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 1), null}),
				new Wave(8, "Bucket Zombie(LV6) * 4",
				new ZIIW[]{zi(40, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 6, 1), zi(4f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 6, 1), 
						   null}),
				new Wave(9, "Cone Zombie(LV2) * 10, Bucket Zombie(LV2) * 10",
				new ZIIW[]{zi(40, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 1), zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 1), 
						   zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 1), zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 1), 
						   zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 1), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 1), 
						   zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 1), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 1), 
						   zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 1), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 1), 
						   null}),
				new Wave(10, "Zombie(LV9) * 7",
				new ZIIW[]{zi(60, Type1.BUCKET_ZOMBIE, 9, 0), zi(1f, Type1.BUCKET_ZOMBIE, 9, 1), zi(1f, Type1.BUCKET_ZOMBIE, 9, 0), zi(1f, Type1.BUCKET_ZOMBIE, 9, 1), 
						   zi(1f, Type1.BUCKET_ZOMBIE, 9, 0), zi(1f, Type1.BUCKET_ZOMBIE, 9, 1), zi(1f, Type1.BUCKET_ZOMBIE, 9, 0), null})
				});
				
				break;
			}
			case "twoswitchpuzzle":
			{
				currentlevel = new Level(this, assets, assets.Twoswitchpuzzle_Texture, new String[] {"texts/Twoswitchpuzzle.txt"}, 300, Type1.FRISK, 
				new Rectangle[] {new Rectangle(80, 320, 1320, 120), new Rectangle(0, 0, 80, 480), new Rectangle(80, 440, 1400, 40), new Rectangle(320, 0, 1160, 40), new Rectangle(80, 0, 160, 40)},
				new Rectangle[] {new Rectangle(485, 200, 67, 120), new Rectangle(968, 200, 67, 120), new Rectangle(485, 40, 67, 80), new Rectangle(968, 40, 67, 80)},
				new Rectangle[] {new Rectangle(240, 0, 80, 200), new Rectangle(240, 120, 1240, 80)},
				new Wave[]{
				new Wave(1, "Zombie(LV1) * 4, Zombie(LV2) * 1",
				new ZIIW[]{zi(99999, Type1.ZOMBIE, 2, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0),
						   zi(0.8f, Type1.ZOMBIE, 1, 0), null}),
				new Wave(2, "Zombie(LV1) * 14",
				new ZIIW[]{zi(25, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), 
						   zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), 
						   zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.5f, Type1.ZOMBIE, 1, 0), zi(0.5f, Type1.ZOMBIE, 1, 0), 
						   zi(0.5f, Type1.ZOMBIE, 1, 0), zi(0.5f, Type1.ZOMBIE, 1, 0), null}),
				new Wave(3, "Zombie(LV1) * 15",
				new ZIIW[]{zi(25, Type1.ZOMBIE, 1, 0), zi(0.25f, Type1.ZOMBIE, 1, 0), zi(0.25f, Type1.ZOMBIE, 1, 0), zi(0.25f, Type1.ZOMBIE, 1, 0),
				           zi(0.25f, Type1.ZOMBIE, 1, 0), zi(0.25f, Type1.ZOMBIE, 1, 0), zi(0.25f, Type1.ZOMBIE, 1, 0), zi(0.25f, Type1.ZOMBIE, 1, 0), 
				           zi(0.25f, Type1.ZOMBIE, 1, 0), zi(0.25f, Type1.ZOMBIE, 1, 0), zi(0.25f, Type1.ZOMBIE, 1, 0), zi(0.25f, Type1.ZOMBIE, 1, 0), 
				           zi(0.25f, Type1.ZOMBIE, 1, 0), zi(0.25f, Type1.ZOMBIE, 1, 0), zi(0.25f, Type1.ZOMBIE, 1, 0), null}),
				new Wave(4, "Zombie(LV1) * 20", 
				new ZIIW[]{zi(25, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), 
				           zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), 
				           zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), 
				           zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), 
				           zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), 
				           null}),
				new Wave(5, "Zombie(LV1) * 40", 
				new ZIIW[]{zi(25, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), 
				           zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), 
				           zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), 
				           zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), 
				           zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), 
				           zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), 
				           zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), 
				           zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), 
				           zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), 
				           zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), 
				           null}),
				new Wave(6, "Bucket Zombie(LV1) * 3", 
				new ZIIW[]{zi(35, Type1.BUCKET_ZOMBIE, 1, 0), zi(1f, Type1.BUCKET_ZOMBIE, 1, 0), zi(1f, Type1.BUCKET_ZOMBIE, 1, 0), null}),
				new Wave(7, "Cone Zombie(LV1) * 10", 
				new ZIIW[]{zi(35, Type1.CONE_ZOMBIE, 1, 0), zi(0.6f, Type1.CONE_ZOMBIE, 1, 0), zi(0.4f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), 
						   zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), 
						   zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), zi(0.3f, Type1.CONE_ZOMBIE, 1, 0), null}),
				new Wave(8, "Bucket Zombie(LV1) * 4, Bucket Zombie(LV2) * 4", 
				new ZIIW[]{zi(40, Type1.BUCKET_ZOMBIE, 1, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 1, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 1, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 1, 0), 
				           zi(0.2f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 2, 0), 
				           null}),
				new Wave(9, "Cone Zombie(LV2) * 10, Bucket Zombie(LV2) * 10", 
				new ZIIW[]{zi(40, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), 
				           zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), 
				           zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.3f, Type1.CONE_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), 
				           zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), 
				           zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 2, 0), 
				           null}),
				new Wave(10, "Zombie(LV4) * 30, Cone Zombie(LV4) * 30, \n * Bucket Zombie(LV4) * 30", 
				new ZIIW[]{zi(60, Type1.ZOMBIE, 4, 0), zi(2f, Type1.ZOMBIE, 4, 0), zi(2f, Type1.ZOMBIE, 4, 0), zi(2f, Type1.ZOMBIE, 4, 0), 
				           zi(2f, Type1.ZOMBIE, 4, 0), zi(2f, Type1.ZOMBIE, 4, 0), zi(2f, Type1.ZOMBIE, 4, 0), zi(2f, Type1.ZOMBIE, 4, 0), 
				           zi(2f, Type1.ZOMBIE, 4, 0), zi(2f, Type1.ZOMBIE, 4, 0), zi(1f, Type1.ZOMBIE, 4, 0), zi(1f, Type1.ZOMBIE, 4, 0), 
				           zi(1f, Type1.ZOMBIE, 4, 0), zi(1f, Type1.ZOMBIE, 4, 0), zi(1f, Type1.ZOMBIE, 4, 0), zi(1f, Type1.ZOMBIE, 4, 0), 
				           zi(1f, Type1.ZOMBIE, 4, 0), zi(1f, Type1.ZOMBIE, 4, 0), zi(1f, Type1.ZOMBIE, 4, 0), zi(1f, Type1.ZOMBIE, 4, 0), 
				           zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), 
				           zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), 
				           zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(5f, Type1.CONE_ZOMBIE, 4, 0), zi(4f, Type1.CONE_ZOMBIE, 4, 0), 
				           zi(3f, Type1.CONE_ZOMBIE, 4, 0), zi(2f, Type1.CONE_ZOMBIE, 4, 0), zi(1f, Type1.CONE_ZOMBIE, 4, 0), zi(0.4f, Type1.CONE_ZOMBIE, 4, 0), 
				           zi(0.4f, Type1.CONE_ZOMBIE, 4, 0), zi(0.4f, Type1.CONE_ZOMBIE, 4, 0), zi(0.4f, Type1.CONE_ZOMBIE, 4, 0), zi(0.4f, Type1.CONE_ZOMBIE, 4, 0), 
				           zi(0.2f, Type1.CONE_ZOMBIE, 4, 0), zi(0.2f, Type1.CONE_ZOMBIE, 4, 0), zi(0.2f, Type1.CONE_ZOMBIE, 4, 0), zi(0.2f, Type1.CONE_ZOMBIE, 4, 0), 
				           zi(0.2f, Type1.CONE_ZOMBIE, 4, 0), zi(0.2f, Type1.CONE_ZOMBIE, 4, 0), zi(0.2f, Type1.CONE_ZOMBIE, 4, 0), zi(0.2f, Type1.CONE_ZOMBIE, 4, 0), 
				           zi(0.2f, Type1.CONE_ZOMBIE, 4, 0), zi(0.2f, Type1.CONE_ZOMBIE, 4, 0), zi(0.1f, Type1.CONE_ZOMBIE, 4, 0), zi(0.1f, Type1.CONE_ZOMBIE, 4, 0), 
				           zi(0.1f, Type1.CONE_ZOMBIE, 4, 0), zi(0.1f, Type1.CONE_ZOMBIE, 4, 0), zi(0.1f, Type1.CONE_ZOMBIE, 4, 0), zi(0.1f, Type1.CONE_ZOMBIE, 4, 0), 
				           zi(0.1f, Type1.CONE_ZOMBIE, 4, 0), zi(0.1f, Type1.CONE_ZOMBIE, 4, 0), zi(0.1f, Type1.CONE_ZOMBIE, 4, 0), zi(0.1f, Type1.CONE_ZOMBIE, 4, 0), 
				           zi(0.3f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 4, 0), 
				           zi(0.3f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 4, 0), 
				           zi(0.3f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 4, 0), 
				           zi(0.2f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 4, 0), 
				           zi(0.2f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 4, 0), 
				           zi(0.1f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 4, 0), 
				           zi(0.1f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 4, 0), 
				           zi(0.1f, Type1.BUCKET_ZOMBIE, 4, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 4, 0), null}),
				new Wave(11, "Bucket Zombie(LV10) * 5", 
				new ZIIW[]{zi(40, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), null})});
				break;
			}
			case "ruins2":
			{
				currentlevel = new Level(this, assets, assets.ruins2_Texture, new String[] {"texts/ruins2.txt"}, 300, Type1.FRISK, 
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
				new ZIIW[]{zi(99999, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), 
				           zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), 
				           zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), 
				           zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), 
				           zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), 
				           zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), 
				           zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), 
				           zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.6f, Type1.ZOMBIE, 1, 0), zi(0.5f, Type1.ZOMBIE, 1, 0), zi(0.5f, Type1.ZOMBIE, 1, 0), 
				           zi(0.5f, Type1.ZOMBIE, 1, 0), zi(0.5f, Type1.ZOMBIE, 1, 0), zi(0.5f, Type1.ZOMBIE, 1, 0), zi(0.5f, Type1.ZOMBIE, 1, 0), 
				           zi(0.5f, Type1.ZOMBIE, 1, 0), zi(0.5f, Type1.ZOMBIE, 1, 0), zi(0.5f, Type1.ZOMBIE, 1, 0), zi(0.5f, Type1.ZOMBIE, 1, 0), 
				           zi(0.4f, Type1.ZOMBIE, 1, 0), zi(0.4f, Type1.ZOMBIE, 1, 0), zi(0.4f, Type1.ZOMBIE, 1, 0), zi(0.4f, Type1.ZOMBIE, 1, 0), 
				           zi(0.4f, Type1.ZOMBIE, 1, 0), zi(0.4f, Type1.ZOMBIE, 1, 0), zi(0.4f, Type1.ZOMBIE, 1, 0), zi(0.4f, Type1.ZOMBIE, 1, 0), 
				           zi(0.4f, Type1.ZOMBIE, 1, 0), zi(0.4f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), 
				           zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), 
				           zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), zi(0.3f, Type1.ZOMBIE, 1, 0), 
				           zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), 
				           zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), 
				           zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.2f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), 
				           zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), 
				           zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), zi(0.1f, Type1.ZOMBIE, 1, 0), 
				           null}),
				new Wave(2, "Zombie(LV5) * 80",
				new ZIIW[]{zi(20, Type1.ZOMBIE, 5, 0), zi(0.8f, Type1.ZOMBIE, 5, 0), zi(0.8f, Type1.ZOMBIE, 5, 0), zi(0.8f, Type1.ZOMBIE, 5, 0), 
				           zi(0.8f, Type1.ZOMBIE, 5, 0), zi(0.8f, Type1.ZOMBIE, 5, 0), zi(0.8f, Type1.ZOMBIE, 5, 0), zi(0.8f, Type1.ZOMBIE, 5, 0), 
				           zi(0.8f, Type1.ZOMBIE, 5, 0), zi(0.8f, Type1.ZOMBIE, 5, 0), zi(0.7f, Type1.ZOMBIE, 5, 0), zi(0.7f, Type1.ZOMBIE, 5, 0), 
				           zi(0.7f, Type1.ZOMBIE, 5, 0), zi(0.7f, Type1.ZOMBIE, 5, 0), zi(0.7f, Type1.ZOMBIE, 5, 0), zi(0.7f, Type1.ZOMBIE, 5, 0), 
				           zi(0.7f, Type1.ZOMBIE, 5, 0), zi(0.7f, Type1.ZOMBIE, 5, 0), zi(0.7f, Type1.ZOMBIE, 5, 0), zi(0.7f, Type1.ZOMBIE, 5, 0), 
				           zi(0.6f, Type1.ZOMBIE, 5, 0), zi(0.6f, Type1.ZOMBIE, 5, 0), zi(0.6f, Type1.ZOMBIE, 5, 0), zi(0.6f, Type1.ZOMBIE, 5, 0), 
				           zi(0.6f, Type1.ZOMBIE, 5, 0), zi(0.6f, Type1.ZOMBIE, 5, 0), zi(0.6f, Type1.ZOMBIE, 5, 0), zi(0.6f, Type1.ZOMBIE, 5, 0), 
				           zi(0.6f, Type1.ZOMBIE, 5, 0), zi(0.6f, Type1.ZOMBIE, 5, 0), zi(0.5f, Type1.ZOMBIE, 5, 0), zi(0.5f, Type1.ZOMBIE, 5, 0), 
				           zi(0.5f, Type1.ZOMBIE, 5, 0), zi(0.5f, Type1.ZOMBIE, 5, 0), zi(0.5f, Type1.ZOMBIE, 5, 0), zi(0.5f, Type1.ZOMBIE, 5, 0), 
				           zi(0.5f, Type1.ZOMBIE, 5, 0), zi(0.5f, Type1.ZOMBIE, 5, 0), zi(0.5f, Type1.ZOMBIE, 5, 0), zi(0.5f, Type1.ZOMBIE, 5, 0), 
				           zi(0.4f, Type1.ZOMBIE, 5, 0), zi(0.4f, Type1.ZOMBIE, 5, 0), zi(0.4f, Type1.ZOMBIE, 5, 0), zi(0.4f, Type1.ZOMBIE, 5, 0), 
				           zi(0.4f, Type1.ZOMBIE, 5, 0), zi(0.4f, Type1.ZOMBIE, 5, 0), zi(0.4f, Type1.ZOMBIE, 5, 0), zi(0.4f, Type1.ZOMBIE, 5, 0), 
				           zi(0.4f, Type1.ZOMBIE, 5, 0), zi(0.4f, Type1.ZOMBIE, 5, 0), zi(0.3f, Type1.ZOMBIE, 5, 0), zi(0.3f, Type1.ZOMBIE, 5, 0), 
				           zi(0.3f, Type1.ZOMBIE, 5, 0), zi(0.3f, Type1.ZOMBIE, 5, 0), zi(0.3f, Type1.ZOMBIE, 5, 0), zi(0.3f, Type1.ZOMBIE, 5, 0), 
				           zi(0.3f, Type1.ZOMBIE, 5, 0), zi(0.3f, Type1.ZOMBIE, 5, 0), zi(0.3f, Type1.ZOMBIE, 5, 0), zi(0.3f, Type1.ZOMBIE, 5, 0), 
				           zi(0.2f, Type1.ZOMBIE, 5, 0), zi(0.2f, Type1.ZOMBIE, 5, 0), zi(0.2f, Type1.ZOMBIE, 5, 0), zi(0.2f, Type1.ZOMBIE, 5, 0), 
				           zi(0.2f, Type1.ZOMBIE, 5, 0), zi(0.2f, Type1.ZOMBIE, 5, 0), zi(0.2f, Type1.ZOMBIE, 5, 0), zi(0.2f, Type1.ZOMBIE, 5, 0), 
				           zi(0.2f, Type1.ZOMBIE, 5, 0), zi(0.2f, Type1.ZOMBIE, 5, 0), zi(0.1f, Type1.ZOMBIE, 5, 0), zi(0.1f, Type1.ZOMBIE, 5, 0), 
				           zi(0.1f, Type1.ZOMBIE, 5, 0), zi(0.1f, Type1.ZOMBIE, 5, 0), zi(0.1f, Type1.ZOMBIE, 5, 0), zi(0.1f, Type1.ZOMBIE, 5, 0), 
				           zi(0.1f, Type1.ZOMBIE, 5, 0), zi(0.1f, Type1.ZOMBIE, 5, 0), zi(0.1f, Type1.ZOMBIE, 5, 0), zi(0.1f, Type1.ZOMBIE, 5, 0), 
				           null}),
				new Wave(3, "Cone Zombie(LV5) * 80",
				new ZIIW[]{zi(20, Type1.CONE_ZOMBIE, 5, 0), zi(0.8f, Type1.CONE_ZOMBIE, 5, 0), zi(0.8f, Type1.CONE_ZOMBIE, 5, 0), zi(0.8f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.8f, Type1.CONE_ZOMBIE, 5, 0), zi(0.8f, Type1.CONE_ZOMBIE, 5, 0), zi(0.8f, Type1.CONE_ZOMBIE, 5, 0), zi(0.8f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.8f, Type1.CONE_ZOMBIE, 5, 0), zi(0.8f, Type1.CONE_ZOMBIE, 5, 0), zi(0.7f, Type1.CONE_ZOMBIE, 5, 0), zi(0.7f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.7f, Type1.CONE_ZOMBIE, 5, 0), zi(0.7f, Type1.CONE_ZOMBIE, 5, 0), zi(0.7f, Type1.CONE_ZOMBIE, 5, 0), zi(0.7f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.7f, Type1.CONE_ZOMBIE, 5, 0), zi(0.7f, Type1.CONE_ZOMBIE, 5, 0), zi(0.7f, Type1.CONE_ZOMBIE, 5, 0), zi(0.7f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.6f, Type1.CONE_ZOMBIE, 5, 0), zi(0.6f, Type1.CONE_ZOMBIE, 5, 0), zi(0.6f, Type1.CONE_ZOMBIE, 5, 0), zi(0.6f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.6f, Type1.CONE_ZOMBIE, 5, 0), zi(0.6f, Type1.CONE_ZOMBIE, 5, 0), zi(0.6f, Type1.CONE_ZOMBIE, 5, 0), zi(0.6f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.6f, Type1.CONE_ZOMBIE, 5, 0), zi(0.6f, Type1.CONE_ZOMBIE, 5, 0), zi(0.5f, Type1.CONE_ZOMBIE, 5, 0), zi(0.5f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.5f, Type1.CONE_ZOMBIE, 5, 0), zi(0.5f, Type1.CONE_ZOMBIE, 5, 0), zi(0.5f, Type1.CONE_ZOMBIE, 5, 0), zi(0.5f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.5f, Type1.CONE_ZOMBIE, 5, 0), zi(0.5f, Type1.CONE_ZOMBIE, 5, 0), zi(0.5f, Type1.CONE_ZOMBIE, 5, 0), zi(0.5f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.4f, Type1.CONE_ZOMBIE, 5, 0), zi(0.4f, Type1.CONE_ZOMBIE, 5, 0), zi(0.4f, Type1.CONE_ZOMBIE, 5, 0), zi(0.4f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.4f, Type1.CONE_ZOMBIE, 5, 0), zi(0.4f, Type1.CONE_ZOMBIE, 5, 0), zi(0.4f, Type1.CONE_ZOMBIE, 5, 0), zi(0.4f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.4f, Type1.CONE_ZOMBIE, 5, 0), zi(0.4f, Type1.CONE_ZOMBIE, 5, 0), zi(0.3f, Type1.CONE_ZOMBIE, 5, 0), zi(0.3f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.3f, Type1.CONE_ZOMBIE, 5, 0), zi(0.3f, Type1.CONE_ZOMBIE, 5, 0), zi(0.3f, Type1.CONE_ZOMBIE, 5, 0), zi(0.3f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.3f, Type1.CONE_ZOMBIE, 5, 0), zi(0.3f, Type1.CONE_ZOMBIE, 5, 0), zi(0.3f, Type1.CONE_ZOMBIE, 5, 0), zi(0.3f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.2f, Type1.CONE_ZOMBIE, 5, 0), zi(0.2f, Type1.CONE_ZOMBIE, 5, 0), zi(0.2f, Type1.CONE_ZOMBIE, 5, 0), zi(0.2f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.2f, Type1.CONE_ZOMBIE, 5, 0), zi(0.2f, Type1.CONE_ZOMBIE, 5, 0), zi(0.2f, Type1.CONE_ZOMBIE, 5, 0), zi(0.2f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.2f, Type1.CONE_ZOMBIE, 5, 0), zi(0.2f, Type1.CONE_ZOMBIE, 5, 0), zi(0.1f, Type1.CONE_ZOMBIE, 5, 0), zi(0.1f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.1f, Type1.CONE_ZOMBIE, 5, 0), zi(0.1f, Type1.CONE_ZOMBIE, 5, 0), zi(0.1f, Type1.CONE_ZOMBIE, 5, 0), zi(0.1f, Type1.CONE_ZOMBIE, 5, 0), 
				           zi(0.1f, Type1.CONE_ZOMBIE, 5, 0), zi(0.1f, Type1.CONE_ZOMBIE, 5, 0), zi(0.1f, Type1.CONE_ZOMBIE, 5, 0), zi(0.1f, Type1.CONE_ZOMBIE, 5, 0), 
				           null}),
				new Wave(4, "Bucket Zombie(LV5) * 80",
				new ZIIW[]{zi(20, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.8f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.8f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.8f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.8f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.8f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.8f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.8f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.8f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.8f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.6f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.6f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.6f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.5f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.5f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.4f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.4f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.4f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.3f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.3f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.2f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.2f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.2f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           null}),
				new Wave(5, "Bucket Zombie(LV10) * 80",
				new ZIIW[]{zi(40, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.6f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.5f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.5f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.4f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.4f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.4f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.3f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.3f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.2f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.2f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.2f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 10, 0), 
				           null})});
				break;
			}
			case "ruins3":
			{
				currentlevel = new Level(this, assets, assets.ruins3_Texture, new String[] {"texts/ruins3.txt"}, 100, Type1.FRISK, 
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
				new ZIIW[]{zi(99999, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), 
				           zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), 
				           zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.8f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), 
				           zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), 
				           zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), 
				           zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), zi(0.7f, Type1.ZOMBIE, 1, 0), 
				           zi(0.7f, Type1.ZOMBIE, 1, 0), null}),
				new Wave(2, "Cone Zombie(LV4) * 1, Cone Zombie(LV8) * 1",
				new ZIIW[]{zi(30, Type1.CONE_ZOMBIE, 4, 0), zi(8, Type1.CONE_ZOMBIE, 8, 0), null}),
				new Wave(3, "Zombie(LV1) * 13, Cone Zombie(LV1) * 1",
				new ZIIW[]{zi(50, Type1.ZOMBIE, 1, 0), zi(0.05f, Type1.ZOMBIE, 1, 0), zi(0.05f, Type1.ZOMBIE, 1, 0), zi(0.05f, Type1.ZOMBIE, 1, 0), 
						   zi(0.05f, Type1.ZOMBIE, 1, 0), zi(0.05f, Type1.ZOMBIE, 1, 0), zi(0.05f, Type1.ZOMBIE, 1, 0), zi(0.05f, Type1.ZOMBIE, 1, 0), 
						   zi(0.05f, Type1.ZOMBIE, 1, 0), zi(0.05f, Type1.ZOMBIE, 1, 0), zi(0.05f, Type1.ZOMBIE, 1, 0), zi(0.05f, Type1.ZOMBIE, 1, 0), 
						   zi(0.05f, Type1.ZOMBIE, 1, 0), zi(0.05f, Type1.CONE_ZOMBIE, 1, 0), null}),
				new Wave(4, "Zombie(LV3) * 12",
				new ZIIW[]{zi(50, Type1.ZOMBIE, 3, 0), zi(0.05f, Type1.ZOMBIE, 3, 0), zi(0.05f, Type1.ZOMBIE, 3, 0), zi(0.05f, Type1.ZOMBIE, 3, 0), 
						   zi(0.05f, Type1.ZOMBIE, 3, 0), zi(0.05f, Type1.ZOMBIE, 3, 0), zi(0.05f, Type1.ZOMBIE, 3, 0), zi(0.05f, Type1.ZOMBIE, 3, 0), 
						   zi(0.05f, Type1.ZOMBIE, 3, 0), zi(0.05f, Type1.ZOMBIE, 3, 0), zi(0.05f, Type1.ZOMBIE, 3, 0), zi(0.05f, Type1.ZOMBIE, 3, 0), 
                           null}),
				new Wave(5, "Zombie(LV4) * 12",
				new ZIIW[]{zi(50, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), 
						   zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), 
						   zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), 
                           null}),
				new Wave(6, "Bucket Zombie(LV7) * 1",
				new ZIIW[]{zi(30, Type1.BUCKET_ZOMBIE, 7, 0), null}),
				new Wave(7, "Cone Zombie(LV1) * 9",
				new ZIIW[]{zi(50, Type1.CONE_ZOMBIE, 1, 0), zi(1f, Type1.CONE_ZOMBIE, 1, 0), zi(1f, Type1.CONE_ZOMBIE, 1, 0), zi(1f, Type1.CONE_ZOMBIE, 1, 0), 
				           zi(1f, Type1.CONE_ZOMBIE, 1, 0), zi(1f, Type1.CONE_ZOMBIE, 1, 0), zi(1f, Type1.CONE_ZOMBIE, 1, 0), zi(1f, Type1.CONE_ZOMBIE, 1, 0), 
				           zi(1f, Type1.CONE_ZOMBIE, 1, 0), null}),
				new Wave(8, "Zombie(LV4) * 24, Cone Zombie(LV5) * 1",
				new ZIIW[]{zi(50, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), 
						   zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), 
						   zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), 
						   zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), 
						   zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), 
						   zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), zi(0.05f, Type1.ZOMBIE, 4, 0), 
						   zi(1f, Type1.CONE_ZOMBIE, 5, 0), null}),
				new Wave(9, "Bucket Zombie(LV2) * 6",
				new ZIIW[]{zi(50, Type1.BUCKET_ZOMBIE, 2, 0), zi(2f, Type1.BUCKET_ZOMBIE, 2, 0), zi(2f, Type1.BUCKET_ZOMBIE, 2, 0), zi(2f, Type1.BUCKET_ZOMBIE, 2, 0), 
				           zi(2f, Type1.BUCKET_ZOMBIE, 2, 0), zi(2f, Type1.BUCKET_ZOMBIE, 2, 0), null}),
				new Wave(10, "Cone Zombie(LV6) * 12",
				new ZIIW[]{zi(50, Type1.CONE_ZOMBIE, 6, 0), zi(1f, Type1.CONE_ZOMBIE, 6, 0), zi(1f, Type1.CONE_ZOMBIE, 6, 0), zi(1f, Type1.CONE_ZOMBIE, 6, 0), 
				           zi(1f, Type1.CONE_ZOMBIE, 6, 0), zi(1f, Type1.CONE_ZOMBIE, 6, 0), zi(1f, Type1.CONE_ZOMBIE, 6, 0), zi(1f, Type1.CONE_ZOMBIE, 6, 0), 
				           zi(1f, Type1.CONE_ZOMBIE, 6, 0), zi(1f, Type1.CONE_ZOMBIE, 6, 0), zi(1f, Type1.CONE_ZOMBIE, 6, 0), zi(1f, Type1.CONE_ZOMBIE, 6, 0), 
				           null}),
				new Wave(11, "Cone Zombie(LV2) * 12",
				new ZIIW[]{zi(50, Type1.CONE_ZOMBIE, 2, 0), zi(0.05f, Type1.CONE_ZOMBIE, 2, 0), zi(0.05f, Type1.CONE_ZOMBIE, 2, 0), zi(0.05f, Type1.CONE_ZOMBIE, 2, 0), 
				           zi(0.05f, Type1.CONE_ZOMBIE, 2, 0), zi(0.05f, Type1.CONE_ZOMBIE, 2, 0), zi(0.05f, Type1.CONE_ZOMBIE, 2, 0), zi(0.05f, Type1.CONE_ZOMBIE, 2, 0), 
				           zi(0.05f, Type1.CONE_ZOMBIE, 2, 0), zi(0.05f, Type1.CONE_ZOMBIE, 2, 0), zi(0.05f, Type1.CONE_ZOMBIE, 2, 0), zi(0.05f, Type1.CONE_ZOMBIE, 2, 0), 
				           null}),
				new Wave(12, "Bucket Zombie(LV2) * 18",
				new ZIIW[]{zi(50, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), 
				           zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), 
				           zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), 
				           zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), 
				           zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), zi(0.05f, Type1.BUCKET_ZOMBIE, 2, 0), null}),
				new Wave(13, "Bucket Zombie(LV5) * 20, Bucket Zombie(LV6) * 20, Bucket Zombie(LV7) * 20, Bucket Zombie(LV8) * 20",
				new ZIIW[]{zi(50, Type1.BUCKET_ZOMBIE, 5, 0), zi(1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(1f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(1f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(1f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), zi(0.7f, Type1.BUCKET_ZOMBIE, 5, 0), 
				           zi(0.6f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 6, 0), 
				           zi(0.6f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 6, 0), 
				           zi(0.6f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.6f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 6, 0), 
				           zi(0.5f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 6, 0), 
				           zi(0.5f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 6, 0), zi(0.5f, Type1.BUCKET_ZOMBIE, 6, 0), 
				           zi(0.4f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 7, 0), 
				           zi(0.4f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 7, 0), 
				           zi(0.4f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.4f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 7, 0), 
				           zi(0.3f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 7, 0), 
				           zi(0.3f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 7, 0), zi(0.3f, Type1.BUCKET_ZOMBIE, 7, 0), 
				           zi(0.2f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 8, 0), 
				           zi(0.2f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 8, 0), 
				           zi(0.2f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.2f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 8, 0), 
				           zi(0.1f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 8, 0), 
				           zi(0.1f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 8, 0), zi(0.1f, Type1.BUCKET_ZOMBIE, 8, 0), 
				           null})});
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
		frame.update(textinfos[0], menuMessage());
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