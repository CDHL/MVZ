package test.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.MVZ_20020804;

import basics.Assets;
import basics.Frame;
import basics.Frame.Spriteimg;
import basics.Frame.Textinfo;
import beings.MoveableBeing;
import basics.Trail;
import test.basics.Being;
import test.basics.SpecialTiles;
import test.basics.Wave;
import test.basics.Being.Drawbase_name;
import test.basics.Being.Type1;
import test.basics.Being.Type2;
import test.basics.Hero;
import util.Utils2;
import util.Data;
import util.Utils1;

public class Level implements Screen{

	//Assets&Utils
	public Assets assets;
	Utils1 utils1;
	//Window size
	public static int BATTLEFIELD_WIDTH;
	public static int BATTLEFIELD_HEIGHT;
	//ObjectCatching
	public Being selectedBeing = null;
	
	//If mouse catches something or something gets sticked to the mouse
	enum MouseCatching
	{
		NOTHING,
		SCREEN,
		MONSTERSET,
	}
	MouseCatching mouseCatching = MouseCatching.NOTHING;
	float mouseX, mouseY;
	float x, y;
	void getmouseXY(float mouseX, float mouseY)
	{
		this.mouseX = mouseX;
		this.mouseY = mouseY;
	}
	//Monstersets
	public class Monsterset
	{
		public float towerXOnScreen, towerYOnScreen;
		public final int EXP;
		Type1 type1;
		Type2 type2;
		Texture range, texture;
		float rangeWidth, rangeHeight;
		Rectangle relativeRectangle;
		boolean disabled = false;

		boolean wouldCounteractWater;
		boolean wouldCounteractWall;
		boolean wouldCounteractBeing;
		boolean wouldCounteractRoad;
		public Monsterset(Texture range, Texture tower_texture, float rangeWidth, float rangeHeight, Rectangle relativeRectangle, Type1 type1, Type2 type2, int EXP, 
				boolean wouldCounteractWater,
				boolean wouldCounteractWall,
				boolean wouldCounteractBeing,
				boolean wouldCounteractRoad)
		{

			this.range = range;
			this.texture = tower_texture;
			this.rangeWidth = rangeWidth;
			this.rangeHeight = rangeHeight;
			this.relativeRectangle = relativeRectangle;
			this.type1 = type1;
			this.type2 = type2;
			this.wouldCounteractWater = wouldCounteractWater;
			this.wouldCounteractWall = wouldCounteractWall;
			this.wouldCounteractBeing = wouldCounteractBeing;
			this.wouldCounteractRoad = wouldCounteractRoad;
			this.EXP = EXP;
		}
		public Monsterset(Texture soldier_texture, Rectangle relativeRectangle, Type1 type1, Type2 type2, int EXP)
		{
			this.texture = soldier_texture;
			this.relativeRectangle = relativeRectangle;
			this.type1 = type1;
			this.type2 = type2;
			this.EXP = EXP;
		}
		public void disable()
		{
			disabled = true;
		}
		public void enable()
		{
			disabled = false;
		}
		public boolean isdisabled()
		{
			return disabled;
		}
		void drawMonster()
		{
			batch.draw(texture, towerXOnScreen, towerYOnScreen, relativeRectangle.getWidth(), relativeRectangle.getHeight());
		}
		// -> Vector2
		// 返回最合适的trail位置(非index)
		// - InputX是这个点的absolute distance X
		// - InputY是这个点的absolute distance Y
		Vector2 getTrailPositionWithSmallestDst(float inputX, float inputY)
		{
			int routeID = -1;
			Vector2 prePositionIndexandDstmin = new Vector2(-1, 99999);
			for(int i = 0; i < trailes.length; i ++)
			{
				// compare challenger with prePositionIndexandDstmin with the value of Dstmin, and choose the smaller one
				Vector2 challenger = trailes[i].searchPosition(inputX, inputY);
				if(prePositionIndexandDstmin.y > challenger.y)
				{
					routeID = i;
					prePositionIndexandDstmin = challenger;
				}
			}
			return trailes[routeID].get((int)prePositionIndexandDstmin.x);
		}
		public void draw()
		{
			//鼠标没有锁定monsterset 画个锤子
			if(mouseCatching != MouseCatching.MONSTERSET)return;
			if(type2 == Type2.TOWER)
			{
				towerXOnScreen = mouseX - relativeRectangle.getWidth()/2 + relativeRectangle.getX()/2;
				towerYOnScreen = Data.STAGE_HEIGHT - mouseY - relativeRectangle.getHeight()/2 + relativeRectangle.getY()/2;
				if(mouseCatching != MouseCatching.MONSTERSET)return;
				if(disabled) batch.setColor(Color.RED);
				else batch.setColor(Color.BLACK);
				batch.draw(range, mouseX - rangeWidth/2, Data.STAGE_HEIGHT - mouseY - rangeHeight/2, rangeWidth, rangeHeight);
				batch.setColor(Color.WHITE);
				drawMonster();
			}
			if(type2 == Type2.BLOCKER)
			{
				towerXOnScreen = mouseX - relativeRectangle.getWidth()/2 + relativeRectangle.getX()/2;
				towerYOnScreen = Data.STAGE_HEIGHT - mouseY - relativeRectangle.getHeight()/2 + relativeRectangle.getY()/2;
				if(disabled) batch.setColor(1, 0, 0, 0.8f);
				else batch.setColor(1, 1, 1, 0.8f);
				/*//未完成
				Vector2 prePositionIndexandDstmin = new Vector2(-1, 99999);
				for(int i = 0; i < trailes.length; i ++)
				{
					// compare challenger with prePositionIndexandDstmin with the value of Dstmin, and choose the smaller one
					Vector2 challenger = trailes[0].searchPosition(-deltaX + towerXOnScreen, -deltaY + towerYOnScreen);
					if(prePositionIndexandDstmin.y > challenger.y)
					{
						prePositionIndexandDstmin = challenger;
					}
				}*/
				Vector2 prePosition = getTrailPositionWithSmallestDst(-deltaX + towerXOnScreen, -deltaY + towerYOnScreen);
				//trailes[0].get((int)prePositionIndexandDstmin.x);
				//Vector2 prePosition = trailes[0].get(trailes[0].searchPosition(
				//-deltaX + towerXOnScreen
				//, -deltaY + towerYOnScreen));

				towerXOnScreen = prePosition.x + deltaX;
				towerYOnScreen = prePosition.y + deltaY;
				
				drawMonster();
				batch.setColor(1, 1, 1, 1);
			}
		}
		public boolean overlaps(SpecialTiles specialtiles)
		{
			Rectangle r = new Rectangle().set(relativeRectangle);
			r.setPosition(towerXOnScreen - deltaX, towerYOnScreen - deltaY);
			return specialtiles.overlaps(r);
		}
		public boolean counteractsWater()
		{
			return wouldCounteractWater && this.overlaps(water);
		}
		public boolean counteractsWall()
		{
			return wouldCounteractWall && this.overlaps(wall);
		}
		public boolean counteractsBeing()
		{
			return wouldCounteractBeing && this.overlaps(towertile);
		}
		public boolean counteractsRoad()
		{
			return wouldCounteractRoad && this.overlaps(road);
		}
		public boolean counteractsOnroadmonster()
		{
			return (type2 == Type2.BLOCKER) && this.overlaps(fightertile);
		}
	}
	Monsterset[] monstersets = new Monsterset[12];
	Monsterset monsterset;
	//Waves
	public int totalWaves;
	Wave[] waves;
	int currentWaveNum = 0;
	//Slow down & pause
	boolean slowed = false;
	int speedNum = 0;
	public boolean paused = false;
	boolean died = false;
	boolean won = false;
	//画面偏移
	float deltaX = 0, deltaY = 200;
	InputMultiplexer inputMultiplexer = new InputMultiplexer();
	InputProcessor stageInputProcessor = new InputProcessor() {
		float mouseXWhenTouchDown;
		float deltaXWhenTouchDown;
		public float limitdeltaX(float deltaX)
		{
			if(deltaX > 0)deltaX = 0;
			if(deltaX < Data.STAGE_WIDTH - background.getWidth())deltaX = Data.STAGE_WIDTH - background.getWidth();
			if(Data.STAGE_WIDTH > background.getWidth())
			deltaX = Data.STAGE_WIDTH/2 - 100 - background.getWidth()/2;
			return deltaX;
		}
		public float getdeltaX(float mouseXWhenTouchDragged)
		{
			float deltaX = 0;
			deltaX = deltaXWhenTouchDown + mouseXWhenTouchDragged - mouseXWhenTouchDown;
			return limitdeltaX(deltaX);
		}
		float mouseYWhenTouchDown;
		float deltaYWhenTouchDown;
		public float getdeltaY(float mouseYWhenTouchDragged)
		{
			float deltaY = 0;
			deltaY = deltaYWhenTouchDown - mouseYWhenTouchDragged + mouseYWhenTouchDown;
			return limitdeltaY(deltaY);
		}
		public float limitdeltaY(float deltaY)
		{
			if(deltaY > 200)deltaY = 200;
			if(deltaY < Data.STAGE_HEIGHT - background.getHeight())deltaY = Data.STAGE_HEIGHT - background.getHeight();
			return deltaY;
		}
		@Override
		public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
			getmouseXY(arg0, arg1);

			switch (mouseCatching)
			{
				case SCREEN:
				{
					deltaX = getdeltaX(arg0);
					deltaY = getdeltaY(arg1);
					mouseCatching = MouseCatching.NOTHING;
					return true;
				}
				default:{}
			}
			return true;
		}
		
		@Override
		public boolean touchDragged(int arg0, int arg1, int arg2) {
			getmouseXY(arg0, arg1);

			switch (mouseCatching)
			{
				case SCREEN:
				{
					deltaX = getdeltaX(arg0);
					deltaY = getdeltaY(arg1);
					return true;
				}
				default:{}
			}
			return true;
		}
		
		@Override
		public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {

			if(paused || died || won)
			return true;
			getmouseXY(arg0, arg1);

			
			switch (mouseCatching)
			{
				case NOTHING:
				{
					Being _selectedBeing = changeSelectedBeingTest();
					if(_selectedBeing != null && _selectedBeing != selectedBeing)
					{
						//切换了选中目标
						System.out.println("selectedBeing changed");
						resetFreeStatus(selectedBeing);
						selectedBeing = _selectedBeing;
						return true;
					}
					//移动屏幕
					frame.update(textinfos[1], "");
					mouseXWhenTouchDown = arg0;
					deltaXWhenTouchDown = deltaX;
					mouseYWhenTouchDown = arg1;
					deltaYWhenTouchDown = deltaY;
					if(Gdx.input.isButtonPressed(Buttons.RIGHT))
					{
						resetFreeStatus(selectedBeing);
						updateCatchingAndSelecting(MouseCatching.SCREEN, null);
						return true;
					}
					mouseCatching = MouseCatching.SCREEN;
					return true;
				}
				case MONSTERSET:
				{
					//右键取消放塔
					if(Gdx.input.isButtonPressed(Buttons.RIGHT))
					{
						clearCatchingAndSelecting();
						return true;
					}

					//放不了塔就是放不了
					if(monsterset.isdisabled())return true;
					
					clearCatchingAndSelecting();

					if(monsterset.type2 == Type2.HERO)
					{
						Vector2 destination = new Vector2(-deltaX + monsterset.towerXOnScreen, -deltaY + monsterset.towerYOnScreen);
						if(heroset.onroad)
						{
							if(onroadmonsters.indexOf(hero) == -1)
							{
								utils1.goodRemove(hero, towers);
								onroadmonsters.add(hero);
							}
							utils1.beginGOTO(hero, destination, heroset.position);
						}
						else
						{
							if(towers.indexOf(hero) == -1)
							{
								utils1.goodRemove(hero, onroadmonsters);
								towers.add(hero);
							}
							utils1.beginGOTO(hero, destination, -1);
						}
						return true;
					}

					Being m = Utils2.getMonster(monsterset.type1, monsterset.relativeRectangle);

					addEXP(-monsterset.EXP);

					m.setPosition(-deltaX + monsterset.towerXOnScreen, -deltaY + monsterset.towerYOnScreen);
					
					Rectangle r = new Rectangle().set(monsterset.relativeRectangle);
					r.setPosition(-deltaX + monsterset.towerXOnScreen, -deltaY + monsterset.towerYOnScreen);

					m.entityRectangle = r;
					
					if(m.getType2() == Type2.TOWER)
					{
						towers.add(m);
						towertile.add(m.entityRectangle);
					}
					if(m.getType2() == Type2.BLOCKER)
					{
						onroadmonsters.add(m);
						fightertile.add(m.entityRectangle);
					}
					renderList.add(m);
					
					return true;
				}
				default:{}
			}
			return true;
		}
		
		@Override
		public boolean scrolled(int arg0) {
			return true;
		}
		
		@Override
		public boolean mouseMoved(int arg0, int arg1) {
			getmouseXY(arg0, arg1);
			return true;
		}
		
		@Override
		public boolean keyUp(int arg0) {

			return true;
		}
		
		@Override
		public boolean keyTyped(char arg0) {
			return true;
		}
		
		@Override
		public boolean keyDown(int arg0) {
			
			switch(arg0)
			{
				case Keys.Z:
				{
					if(died)
					{
						game.goToMenu();
						return true;
					}
					if(won)
					{
						game.backToMenuByWinningAGame();
						return true;
					}
					if(paused)
					return true;
					if(selectedBeing != null)
					{
						if(selectedBeing.upgradeAble)
						{
							if(selectedBeing.type2Args[2] == 0)
							{
								selectedBeing.type2Args[2] = 2;
								return true;
							}
							if(selectedBeing.type2Args[2] == 2)
							{
								utils1.towerLOVEup(selectedBeing);
								resetFreeStatus(selectedBeing);
								selectedBeing = null;
								return true;
							}
						}
					}
					return true;
				}
				case Keys.C:
				{
					if(waves[currentWaveNum].isComing() && (selectedBeing == null || selectedBeing.getType2()==Type2.ENEMY))
					{
						selectedBeing = null;
				    	Iterator<Being> it1 = towers.iterator();
				        while(it1.hasNext())
				        {
				        	Being t = it1.next();
				        	switch(t.getType1())
				        	{
				        		case FROGGIT:
				        		{
				                	t.skills.get(0).setCDTime(Data.maxNumber);
				        	        break;
				        		}
				        		case WHIMSUN:
				        		{
				                	t.skills.get(0).setCDTime(Data.maxNumber);
				        	        break;
				        		}
				        		case MOLDSMAL:
				        		{
				                	t.skills.get(0).setCDTime(Data.maxNumber);
				        	        break;
				        		}
				        		case LOOX:
				        		{
				                	t.skills.get(0).setCDTime(Data.maxNumber);
				                	t.skills.get(1).setCDTime(0);
				                	t.skills.get(2).setCDTime(0);
				        	        break;
				        		}
				        		default:{}
				        	}
				        }
						waves[currentWaveNum].quickStart();
					}
					return true;
				}
				case Keys.S:
				{
					if(paused || died || won)
					return true;
					slowed = !slowed;
					return true;
				}
				case Keys.ESCAPE:
				{
					if(died || won)
					return true;
					clearCatchingAndSelecting();
					paused = !paused;
					return true;
				}
				case Keys.X:
				{
					if(died || won)
					return true;
					if(paused)
					game.goToMenu();
					if(selectedBeing != null)//选中了一个塔
					{
						if(selectedBeing.getType2() == Type2.TOWER || selectedBeing.getType2() == Type2.BLOCKER)
						{
							if(selectedBeing.type2Args[2] == 0)
							{
								selectedBeing.type2Args[2] = 1;
								return true;
							}
							if(selectedBeing.type2Args[2] == 1)
							{
								//卖掉怪物
								addEXP((int)(selectedBeing.type2Args[0]*0.8));
								
								utils1.goodRemove(selectedBeing,renderList);
								switch(selectedBeing.getType2())
								{
									case TOWER:
									{
										towertile.remove(selectedBeing.entityRectangle);
										utils1.goodRemove(selectedBeing,towers);
										break;
									}
									case BLOCKER:
									{
										fightertile.remove(selectedBeing.entityRectangle);
				                    	utils1.goodRemove(selectedBeing, onroadmonsters);
										break;
									}
									default:{}
								}

								selectedBeing = null;
								return true;
							}
						}
						else if(selectedBeing.getType2() == Type2.ENEMY || selectedBeing.getType2() == Type2.HERO)
						{
							monsterset = heroset;

							updateCatchingAndSelecting(MouseCatching.MONSTERSET, hero);
							return true;
						}
					}
					else
					{
						monsterset = heroset;
						updateCatchingAndSelecting(MouseCatching.MONSTERSET, hero);
						return true;
					}
					return true;
				}
			}
			return true;
		}
	};
	ClickListener[] clickListener = new ClickListener[10];
	
	void resetFreeStatus()
	{
		for(Being t: towers)
		{
			if(t.getType2() != Type2.HERO)
			{
				t.type2Args[2] = 0;
			}
		}
	}
	
	//重置原选中目标的买卖/升级Status
	//只对非英雄怪物生效
	void resetFreeStatus(Being t)
	{
		if(t == null) return;
		if(t.getType2() == Type2.TOWER || t.getType2() == Type2.BLOCKER)
		{
			t.type2Args[2] = 0;
			System.out.println("resetFreeStatus executed");
		}
	}
	
	public void onClick(int button, float x, float y)
	{
		System.out.println("button pressed");
		if(0 <= button && button < 6)
		{
			Type1 maintype = Type1.DEFAULTED;
			switch(button)
			{
				case 0:{maintype = Type1.FROGGIT; break;}
				case 1:{maintype = Type1.WHIMSUN; break;}
				case 2:{maintype = Type1.ROCK; break;}
				case 3:{maintype = Type1.MOLDSMAL; break;}
				case 4:{maintype = Type1.MIGOSP; break;}
				case 5:{maintype = Type1.LOOX; break;}
				default:{}
			}
			if(mouseCatching == MouseCatching.MONSTERSET && monsterset.type1 == maintype)
			{
				clearCatchingAndSelecting();
				return;
			}
			if(mouseCatching == MouseCatching.NOTHING || mouseCatching == MouseCatching.MONSTERSET)
			{
				updateCatchingAndSelecting(MouseCatching.MONSTERSET, null);
				monsterset = monstersets[button];
			}
		}
	}
	OrthographicCamera camera;
	public int HP = 20;
	public int EXP = 300;
	final MVZ_20020804 game;
	
	//Batch and stage
	SpriteBatch batch;
	Stage stage;
	//Animations&Textures
	Map<Drawbase_name, Animation<TextureRegion>> animations = new HashMap<Drawbase_name, Animation<TextureRegion>> ();
	//relativeProperties
	public final Map<Drawbase_name, Rectangle> relativeRectangles = new HashMap<Drawbase_name, Rectangle> ();
	//trail
	public Trail[] trailes;
	//Background
	private Sprite background;
	//各种ArrayList
	public ArrayList<Being> enemies = new ArrayList<Being>();
	public ArrayList<Being> towers = new ArrayList<Being>();
	public ArrayList<Being> onroadmonsters = new ArrayList<Being>();
	public ArrayList<MoveableBeing> bullets = new ArrayList<MoveableBeing>();
	public ArrayList<MoveableBeing> _bullets = new ArrayList<MoveableBeing>();
	public ArrayList<Being> renderList = new ArrayList<Being>();
	//Frame
	Frame frame;
	Frame frame2;
	Frame frame3;
	Spriteimg[] spriteimgs = new Spriteimg[13];
	private Button[] button = new Button[6];
	
	public SpecialTiles water;
	public SpecialTiles wall;
	public SpecialTiles towertile;
	public SpecialTiles fightertile;
	public SpecialTiles road;
	Textinfo[] textinfos = new Textinfo[3];
	
	public int finalWaveZombies;
	public boolean counteringFinalWave = false;
	//Hero
	public Hero hero;
	public class Heroset extends Monsterset
	{
		boolean onroad = false;
		int position = -1;
		public Heroset(Texture range, Texture tower_texture, float rangeWidth, float rangeHeight,
				Rectangle relativeRectangle, Type1 type1, boolean wouldCounteractWater,
				boolean wouldCounteractWall, boolean wouldCounteractBeing, boolean wouldCounteractRoad) {
			super(range, tower_texture, rangeWidth, rangeHeight, relativeRectangle, type1, Type2.HERO, 0, wouldCounteractWater,
					wouldCounteractWall, wouldCounteractBeing, wouldCounteractRoad);
			// TODO Auto-generated constructor stub
		}
		@Override
		public void draw()
		{
			//鼠标没有锁定monsterset 画个锤子
			if(mouseCatching != MouseCatching.MONSTERSET)return;
			if(hero.died)return;
			if(road.contains(mouseX-deltaX, Data.STAGE_HEIGHT-mouseY-deltaY))
			{
				onroad = true;
				towerXOnScreen = mouseX - relativeRectangle.getWidth()/2 + relativeRectangle.getX()/2;
				towerYOnScreen = Data.STAGE_HEIGHT - mouseY - relativeRectangle.getHeight()/2 + relativeRectangle.getY()/2;
				if(disabled) batch.setColor(1, 0, 0, 0.8f);
				else batch.setColor(1, 1, 1, 0.8f);
				//未完成
				//position = trailes[0].searchPosition( -deltaX + towerXOnScreen, -deltaY + towerYOnScreen);
				Vector2 prePosition = getTrailPositionWithSmallestDst(-deltaX + towerXOnScreen, -deltaY + towerYOnScreen);//trailes[0].get(position);

				towerXOnScreen = prePosition.x + deltaX;
				towerYOnScreen = prePosition.y + deltaY;

				//batch.draw(texture, towerXOnScreen, towerYOnScreen, relativeRectangle.getWidth(), relativeRectangle.getHeight());
				batch.draw(texture, towerXOnScreen, towerYOnScreen, relativeRectangle.getWidth(), relativeRectangle.getHeight(), 0, 0, texture.getWidth()/4, texture.getHeight(), false, false);
				batch.setColor(1, 1, 1, 1);
			}
			else
			{
				onroad = false;
				towerXOnScreen = mouseX - relativeRectangle.getWidth()/2 + relativeRectangle.getX()/2;
				towerYOnScreen = Data.STAGE_HEIGHT - mouseY - relativeRectangle.getHeight()/2 + relativeRectangle.getY()/2;
				if(mouseCatching != MouseCatching.MONSTERSET)return;
				if(disabled) batch.setColor(Color.RED);
				else batch.setColor(Color.BLACK);
				batch.draw(range, mouseX - rangeWidth/2, Data.STAGE_HEIGHT - mouseY - rangeHeight/2, rangeWidth, rangeHeight);
				batch.setColor(Color.WHITE);
				batch.draw(texture, towerXOnScreen, towerYOnScreen, relativeRectangle.getWidth(), relativeRectangle.getHeight(), 0, 0, texture.getWidth()/4, texture.getHeight(), false, false);
				//drawMonster();
			}
		}
		@Override
		public boolean counteractsWater()
		{
			return !onroad && wouldCounteractWater && this.overlaps(water);
		}
		@Override
		public boolean counteractsWall()
		{
			return !onroad && wouldCounteractWall && this.overlaps(wall);
		}
		@Override
		public boolean counteractsBeing()
		{
			return !onroad && wouldCounteractBeing && this.overlaps(towertile);
		}
		@Override
		public boolean counteractsRoad()
		{
			return !onroad && wouldCounteractRoad && this.overlaps(road);
		}
	}
	Heroset heroset;
	public String frame2Textinfo = "";
	
	public Level(final MVZ_20020804 gam, Assets assets, Texture bgTexture, String[] trailpathes, int EXP, Type1 herotype, Rectangle[] wall, Rectangle[] water, Rectangle[] road, Wave[] waves){
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1024, 480);
		this.game = gam;
		this.stage = gam.stage;
		this.assets = assets;
		this.waves = waves;
		this.EXP = EXP;
		trailes = new Trail[trailpathes.length];
		for(int i = 0; i < trailpathes.length; i ++)
		{
			trailes[i] = Utils2.gettrail(trailpathes[i]);
		}
		init(herotype, water, wall, road, bgTexture);
	}
	
	public void addNewZombieToStage(Being enemy, Trail trail)
	{
		enemies.add(enemy);
		renderList.add(enemy);
		enemy.setPosition(trail.initialPosition.x, trail.initialPosition.y);
	}
		
	//神奇的快速排序
	public void quickSortPositions(int low, int high) {// 传入low=0，high=array.length-1;
		
		//zombieRenderList的每个元素对应着List enemies上的一个位置，比较要比较的是getY
		int pivot, p_pos, i;// pivot->位索引;p_pos->轴值。
		Being t;
		if (low < high) {
			p_pos = low;
			pivot = (int)renderList.get(p_pos).getY();
			for (i = low + 1; i <= high; i++)
			{
				if ((int)renderList.get(i).getY() > pivot) {
					p_pos++;
					t = renderList.get(p_pos);
					renderList.set(p_pos, renderList.get(i));
					renderList.set(i, t);
				}
			}
				
			t = renderList.get(low);
			renderList.set(low, renderList.get(p_pos));
			renderList.set(p_pos, t);
			// 分而治之
			quickSortPositions(low, p_pos - 1);// 排序左半部分
			quickSortPositions(p_pos + 1, high);// 排序右半部分
		}
	}

	//判断是否让鼠标点中了新的selected Being
	//搜索所有Being:可选中查看目标
	Being changeSelectedBeingTest()
	{
		//判断是不是鼠标碰到了Beings
		for(Being t:towers)
		{
			Rectangle t_r = t.getRelativeRectangle(new Rectangle());//relativeRectangles.get(z.getDrawbaseName());
			Rectangle _t_r = new Rectangle(t.getX() + deltaX, t.getY() + deltaY, t_r.width, t_r.height);
			if(_t_r.contains(mouseX, Data.STAGE_HEIGHT-mouseY))
			{
				return t;
			}
		}
		for(Being s:onroadmonsters)
		{
			Rectangle s_r = s.getRelativeRectangle(new Rectangle());//relativeRectangles.get(z.getDrawbaseName());
			Rectangle _s_r = new Rectangle(s.getX() + deltaX, s.getY() + deltaY, s_r.width, s_r.height);
			if(_s_r.contains(mouseX, Data.STAGE_HEIGHT-mouseY))
			{
				return s;
			}
		}
		for(Being z:enemies)
		{
			Rectangle z_r = z.getRelativeRectangle(new Rectangle());
			Rectangle _z_r = new Rectangle(z.getX() + deltaX, z.getY() + deltaY, z_r.width, z_r.height);
			if(_z_r.contains(mouseX, Data.STAGE_HEIGHT-mouseY))
			{
				return z;
			}
		}
		Rectangle h_r = hero.getRelativeRectangle(new Rectangle());
		Rectangle _h_r = new Rectangle(hero.getX() + deltaX, hero.getY() + deltaY, h_r.width, h_r.height);
		if(_h_r.contains(mouseX, Data.STAGE_HEIGHT-mouseY))
		{
			return hero;
		}
		return null;
	}
	
	public void monstersetJudge()
	{
		if(monsterset.type2 == Type2.TOWER)
		{
			if(monsterset.counteractsWall() ||monsterset.counteractsWater() || monsterset.counteractsBeing() || monsterset.counteractsRoad())
			{
				monsterset.disable();
				return;
			}
		}
		if(monsterset.type2 == Type2.BLOCKER)
		{
			if(monsterset.counteractsOnroadmonster())
			{
				monsterset.disable();
				return;
			}
		}
		if(monsterset.type2 == Type2.HERO)
		{
			if(monsterset.counteractsWall() ||monsterset.counteractsWater() || monsterset.counteractsBeing() || monsterset.counteractsRoad() || hero.died)
			{
				monsterset.disable();
				return;
			}
		}
		if(EXP < monsterset.EXP)
		{
			monsterset.disable();
			return;
		}
		
		monsterset.enable();
	}
	
	public void init(Type1 herotype, Rectangle[] water, Rectangle[] wall, Rectangle[] road, Texture bgTexture)
	{
		animations.put(Drawbase_name.ZOMBIE_EATING_LEFT, assets.zombie_layer_left_eating_Animation);
		animations.put(Drawbase_name.ZOMBIE_EATING_UP, assets.zombie_back_eating_Animation);
		animations.put(Drawbase_name.ZOMBIE_EATING_DOWN, assets.zombie_eating_Animation);
		animations.put(Drawbase_name.ZOMBIE_EATING_RIGHT, assets.zombie_layer_right_eating_Animation);
		
		animations.put(Drawbase_name.ZOMBIE_WALKING_LEFT, assets.zombie_layer_left_walking_Animation);
		animations.put(Drawbase_name.ZOMBIE_WALKING_UP, assets.zombie_back_walking_Animation);
		animations.put(Drawbase_name.ZOMBIE_WALKING_DOWN, assets.zombie_walking_Animation);
		animations.put(Drawbase_name.ZOMBIE_WALKING_RIGHT, assets.zombie_layer_right_walking_Animation);

		animations.put(Drawbase_name.FRISK_WALKING_LEFT, assets.frisk_layer_left_walking_Animation);
		animations.put(Drawbase_name.FRISK_WALKING_UP, assets.frisk_back_walking_Animation);
		animations.put(Drawbase_name.FRISK_WALKING_DOWN, assets.frisk_walking_Animation);
		animations.put(Drawbase_name.FRISK_WALKING_RIGHT, assets.frisk_layer_right_walking_Animation);
		
		animations.put(Drawbase_name.ZOMBIE_CONE_WALKING_LEFT, assets.zombie_cone_layer_left_walking_Animation);
		animations.put(Drawbase_name.ZOMBIE_CONE_WALKING_RIGHT, assets.zombie_cone_layer_right_walking_Animation);
		
		animations.put(Drawbase_name.ZOMBIE_BUCKET_WALKING_LEFT, assets.zombie_bucket_layer_left_walking_Animation);
		animations.put(Drawbase_name.ZOMBIE_BUCKET_WALKING_RIGHT, assets.zombie_bucket_layer_right_walking_Animation);
		
		relativeRectangles.put(Drawbase_name.ZOMBIE_WALKING_LEFT, assets.zombie_layer_left_walking_RRectangle);
		relativeRectangles.put(Drawbase_name.ZOMBIE_WALKING_UP, assets.zombie_back_walking_RRectangle);
		relativeRectangles.put(Drawbase_name.ZOMBIE_WALKING_DOWN, assets.zombie_walking_RRectangle);
		relativeRectangles.put(Drawbase_name.ZOMBIE_WALKING_RIGHT, assets.zombie_layer_right_walking_RRectangle);
		
		relativeRectangles.put(Drawbase_name.ZOMBIE_EATING_LEFT, assets.zombie_layer_left_eating_RRectangle);
		relativeRectangles.put(Drawbase_name.ZOMBIE_EATING_UP, assets.zombie_back_eating_RRectangle);
		relativeRectangles.put(Drawbase_name.ZOMBIE_EATING_DOWN, assets.zombie_eating_RRectangle);
		relativeRectangles.put(Drawbase_name.ZOMBIE_EATING_RIGHT, assets.zombie_layer_right_eating_RRectangle);
		
		relativeRectangles.put(Drawbase_name.ZOMBIE_CONE_WALKING_LEFT, assets.zombie_cone_layer_left_walking_RRectangle);
		relativeRectangles.put(Drawbase_name.ZOMBIE_CONE_WALKING_UP, assets.zombie_cone_back_walking_RRectangle);
		relativeRectangles.put(Drawbase_name.ZOMBIE_CONE_WALKING_DOWN, assets.zombie_cone_walking_RRectangle);
		relativeRectangles.put(Drawbase_name.ZOMBIE_CONE_WALKING_RIGHT, assets.zombie_cone_layer_right_walking_RRectangle);
		
		relativeRectangles.put(Drawbase_name.ZOMBIE_BUCKET_WALKING_LEFT, assets.zombie_bucket_layer_left_walking_RRectangle);
		relativeRectangles.put(Drawbase_name.ZOMBIE_BUCKET_WALKING_UP, assets.zombie_bucket_back_walking_RRectangle);
		relativeRectangles.put(Drawbase_name.ZOMBIE_BUCKET_WALKING_DOWN, assets.zombie_bucket_walking_RRectangle);
		relativeRectangles.put(Drawbase_name.ZOMBIE_BUCKET_WALKING_RIGHT, assets.zombie_bucket_layer_right_walking_RRectangle);
		
		relativeRectangles.put(Drawbase_name.FRISK_WALKING_LEFT, assets.frisk_layer_left_walking_RRectangle);
		relativeRectangles.put(Drawbase_name.FRISK_WALKING_UP, assets.frisk_back_walking_RRectangle);
		relativeRectangles.put(Drawbase_name.FRISK_WALKING_DOWN, assets.frisk_walking_RRectangle);
		relativeRectangles.put(Drawbase_name.FRISK_WALKING_RIGHT, assets.frisk_layer_right_walking_RRectangle);
		
		relativeRectangles.put(Drawbase_name.FROGGIT, assets.froggit_RRectangle);

		relativeRectangles.put(Drawbase_name.FROGGIT_BULLET, assets.froggit_bullet_RRectangle);
		
		relativeRectangles.put(Drawbase_name.WHIMSUN, assets.whimsun_RRectangle);

		relativeRectangles.put(Drawbase_name.WHIMSUN_BULLET, assets.whimsun_bullet_RRectangle);
		
		relativeRectangles.put(Drawbase_name.ROCK, assets.rock_RRectangle);
		
		relativeRectangles.put(Drawbase_name.MOLDSMAL, assets.moldsmal_RRectangle);

		relativeRectangles.put(Drawbase_name.MOLDSMAL_BULLET, assets.moldsmal_bullet_RRectangle);

		relativeRectangles.put(Drawbase_name.LOOX, assets.loox_RRectangle);
		relativeRectangles.put(Drawbase_name.LOOX_BULLET1, assets.loox_bullet1_RRectangle);
		relativeRectangles.put(Drawbase_name.LOOX_BULLET2, assets.loox_bullet2_RRectangle);
		relativeRectangles.put(Drawbase_name.LOOX_BULLET3, assets.loox_bullet3_RRectangle);

		relativeRectangles.put(Drawbase_name.MIGOSP, assets.migosp_RRectangle);
		
		//得到背景的sprite图
		background = new Sprite(bgTexture);
		BATTLEFIELD_WIDTH = bgTexture.getWidth();
		BATTLEFIELD_HEIGHT = bgTexture.getHeight();
		
		// 创建 Batch
		batch = new SpriteBatch();
	    
	    //Frames, +86, -86
		frame = new Frame(assets.unit_Texture, 188, 668, 1);
		frame.setX(824);
		frame.setY(0);
		textinfos[0] = frame.add(propFrameInfo(), assets.font_DTM_Mono, 10, 656, 1, 1, 1, 1);
		spriteimgs[0] = frame.add(assets.unit_Texture, 16, 490, 70, 70);
		spriteimgs[0].setColor(0.64f, 0.64f, 0.64f, 1);
		spriteimgs[1] = frame.add(assets.unit_Texture, 102, 490, 70, 70);
		spriteimgs[1].setColor(0.64f, 0.64f, 0.64f, 1);
		spriteimgs[2] = frame.add(assets.unit_Texture, 16, 404, 70, 70);
		spriteimgs[2].setColor(0.64f, 0.64f, 0.64f, 1);
		spriteimgs[3] = frame.add(assets.unit_Texture, 102, 404, 70, 70);
		spriteimgs[3].setColor(0.64f, 0.64f, 0.64f, 1);
		spriteimgs[4] = frame.add(assets.unit_Texture, 16, 318, 70, 70);
		spriteimgs[4].setColor(0.64f, 0.64f, 0.64f, 1);
		spriteimgs[5] = frame.add(assets.unit_Texture, 102, 318, 70, 70);
		spriteimgs[5].setColor(0.64f, 0.64f, 0.64f, 1);
		spriteimgs[6] = frame.add(assets.froggit_Texture, 31, 505, 40, 40);
		spriteimgs[7] = frame.add(assets.whimsun_Texture, 117, 505, 40, 40);
		spriteimgs[8] = frame.add(assets.rock_Texture, 31, 421, 40, 36);
		spriteimgs[9] = frame.add(assets.moldsmal_Texture, 116, 424, 42, 30);
		spriteimgs[10] = frame.add(assets.migosp_Texture, 119, 330, 36, 46);
		spriteimgs[11] = frame.add(assets.loox_Texture, 31, 329.5f, 40, 47);
		//Frame2
		frame2 = new Frame(assets.unit_Texture, 1012, 188, 1);
		textinfos[1] = frame2.add("", assets.font_DTM_Mono, 10, 200 - 12 - 10, 1, 1, 1, 1);
		frame2.setX(0);
		frame2.setY(0);
		//Frame3
		frame3 = new Frame(assets.unit_Texture, Data.STAGE_WIDTH - 200 - 12, Data.STAGE_HEIGHT - 200 - 12, 0.5f);
		textinfos[2] = frame3.add(" * Game paused."
				  + "\n * Game rule:"
	              + "\n * Press right button to cancel."
	              + "\n * Press left button to check a monster, check a "
	              + "\n   zombie, or place a monster."
	              + "\n * Press 'S' to slow down the game speed."
	              + "\n * Press 'Z' to upgrade the monsters."
	              + "\n * Press 'X' to free a monster or to control "
	              + "\n   the hero."
	              + "\n * Press 'C' to quick-start the next wave."
	              + "\n"
	              + "\n * Press 'X' to go back to the menu..."
	              + "\n * Press ESC to resume the game...", assets.font_DTM_Mono, 10, Data.STAGE_HEIGHT - 200 - 12 - 10, 1, 1, 1, 0.9f);
		frame3.setX(100);
		frame3.setY(100);
		//inputMultiplexer
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(stageInputProcessor);

        Gdx.input.setInputProcessor(inputMultiplexer);
        clickListener[0] = new ClickListener() {
    		@Override
    		public void clicked(InputEvent event, float x, float y) {
    			if(!paused && !died && !won)
    			onClick(0, x, y);
    		}
    	};
        clickListener[1] = new ClickListener() {
    		@Override
    		public void clicked(InputEvent event, float x, float y) {
    			if(!paused && !died && !won)
    			onClick(1, x, y);
    		}
    	};
        clickListener[2] = new ClickListener() {
    		@Override
    		public void clicked(InputEvent event, float x, float y) {
    			if(!paused && !died && !won)
    			onClick(2, x, y);
    		}
    	};
        clickListener[3] = new ClickListener() {
    		@Override
    		public void clicked(InputEvent event, float x, float y) {
    			if(!paused && !died && !won)
    			onClick(3, x, y);
    		}
    	};
        clickListener[4] = new ClickListener() {
    		@Override
    		public void clicked(InputEvent event, float x, float y) {
    			if(!paused && !died && !won)
    			onClick(4, x, y);
    		}
    	};
        clickListener[5] = new ClickListener() {
    		@Override
    		public void clicked(InputEvent event, float x, float y) {
    			if(!paused && !died && !won)
    			onClick(5, x, y);
    		}
    	};
		//button
    	Button.ButtonStyle style = new Button.ButtonStyle();
		style.up = new TextureRegionDrawable(new TextureRegion(assets.unit_alpha2_Texture));
		style.down = new TextureRegionDrawable(new TextureRegion(assets.unit_alpha2_Texture));
		button[0] = new Button(style);
		button[0].setSize(70, 70);
		button[0].setPosition(846, 496);
		button[0].addListener(clickListener[0]);

        stage.addActor(button[0]);
		button[1] = new Button(style);
		button[1].setSize(70, 70);
		button[1].setPosition(931, 496);
		button[1].addListener(clickListener[1]);
        stage.addActor(button[1]);
		button[2] = new Button(style);
		button[2].setSize(70, 70);
		button[2].setPosition(846, 410);
		button[2].addListener(clickListener[2]);
        stage.addActor(button[2]);
		button[3] = new Button(style);
		button[3].setSize(70, 70);
		button[3].setPosition(931, 410);
		button[3].addListener(clickListener[3]);
        stage.addActor(button[3]);
		button[4] = new Button(style);
		button[4].setSize(70, 70);
		button[4].setPosition(931, 324);
		button[4].addListener(clickListener[4]);
        stage.addActor(button[4]);
		button[5] = new Button(style);
		button[5].setSize(70, 70);
		button[5].setPosition(846, 324);
		button[5].addListener(clickListener[5]);
        stage.addActor(button[5]);

        monstersets[0] = new Monsterset(assets.white_circle_100X100_Texture, assets.froggit_Texture, Data.froggitSkill0Range[0]*2, Data.froggitSkill0Range[0]*2, assets.froggit_RRectangle, Type1.FROGGIT, Type2.TOWER, Data.froggitEXPValue[0], true, true, true, true);
        monsterset = monstersets[0];
        
        monstersets[1] = new Monsterset(assets.white_circle_100X100_Texture, assets.whimsun_Texture, Data.whimsunSkill0Range[0]*2, Data.whimsunSkill0Range[0]*2, assets.whimsun_RRectangle, Type1.WHIMSUN, Type2.TOWER, Data.whimsunEXPValue[0], false, false, true, true);
        
        monstersets[2] = new Monsterset(assets.rock_Texture, assets.rock_RRectangle, Type1.ROCK, Type2.BLOCKER, Data.rockEXPValue);

        monstersets[3] = new Monsterset(assets.white_circle_100X100_Texture, assets.moldsmal_Texture, Data.moldsmalSkill0Range[0]*2, Data.moldsmalSkill0Range[0]*2, assets.moldsmal_RRectangle, Type1.MOLDSMAL, Type2.TOWER, Data.moldsmalEXPValue[0], true, true, true, true);

        monstersets[4] = new Monsterset(assets.white_circle_100X100_Texture, assets.migosp_Texture, Data.migospSkill0RangeArgs[0]*2, Data.migospSkill0RangeArgs[0]*2, assets.migosp_RRectangle, Type1.MIGOSP, Type2.TOWER, Data.migospEXPValue[0], true, true, true, true);

        monstersets[5] = new Monsterset(assets.white_circle_100X100_Texture, assets.loox_Texture, Data.looxSkill0RangeArgs[0]*2, Data.looxSkill0RangeArgs[0]*2, assets.loox_RRectangle, Type1.LOOX, Type2.TOWER, Data.looxEXPValue[0], true, true, true, true);
        
        heroset = new Heroset(assets.white_circle_100X100_Texture, assets.frisk_walking_Texture, Data.froggitSkill0Range[0]*2, Data.froggitSkill0Range[0]*2, assets.frisk_walking_RRectangle, Type1.FRISK, true, true, true, false);
        
        this.wall = new SpecialTiles(wall);
        this.water = new SpecialTiles(water);
        this.road = new SpecialTiles(road);
        this.towertile = new SpecialTiles();
        this.fightertile = new SpecialTiles();
        
        hero = Utils2.getHero(herotype, assets);
        //设置trailID
        hero.type2Args[4] = 0;
        renderList.add(hero);
        hero.setPosition(trailes[0].get(500).x, trailes[0].get(500).y);
		onroadmonsters.add(hero);
        
		deltaX = Data.STAGE_WIDTH/2 - 100 - background.getWidth()/2;
        
        //初始化utils1
		utils1 = new Utils1(assets, animations, relativeRectangles, this);
		
		//Waves
		totalWaves = waves.length;
		for(Wave w:waves)
		{
			w.assignLevel(this);
		}
		finalWaveZombies = waves[totalWaves - 1].zombieNum;
	}
	
	String propFrameInfo()
	{
		return "HP: "+HP+"/20\nEXP: "+EXP+"\nTOWERS:\n\n\n\n\n\n\n\n\n\nSD:"+slowed;
	}
	
	@Override
	public void render(float delta)
	{
		//开始绘画
        batch.begin();
		// 黑色清屏
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//render background
        background.setX(deltaX);
        background.setY(deltaY);
        background.draw(batch);
        //渲染顺序排序，按照纵坐标大小
        quickSortPositions(0, renderList.size()-1);
        //渲染
        for(int i = 0; i < renderList.size(); i ++)
        {
        	Being r = renderList.get(i);
			batch.setColor(r.r, r.g, r.b, r.alpha);
			updateStateTime(r);
			utils1.render(r, batch, deltaX, deltaY);
        }
        frame.draw(batch);
        frame2.draw(batch);
        monsterset.draw();

        if(paused)frame3.draw(batch);
        
        //结束绘制
        batch.end();
        stage.act();
        stage.draw();

        //如果按了暂停键 不更新
        if(paused || died || won)return;
        //如果按了慢速键 更新速度变慢
		speedNum ++;
		if(slowed && speedNum < 2)return;
		speedNum = 0;

		//更新部分
		//Update waves
		if(waves[currentWaveNum].isOver() && currentWaveNum + 1 != totalWaves)
		{
			currentWaveNum ++;
		}
		waves[currentWaveNum].updateWave();
		//Update the monsterset
		this.monstersetJudge();
		//Update frames
		frame.update(textinfos[0], propFrameInfo());
		if(frame2Textinfo == "" && waves[currentWaveNum].isComing())
		{
			frame2Textinfo = waves[currentWaveNum].getWaveTextInfo();
			if(currentWaveNum + 1 == totalWaves)
			{
				frame2.update(textinfos[1], 1, 0, 0, 1);
			}
		}
		else
		{
			frame2.update(textinfos[1], 1, 1, 1, 1);
		}
		frame2.update(textinfos[1], frame2Textinfo);
		frame2Textinfo = "";
		if(selectedBeing == null && mouseCatching == MouseCatching.MONSTERSET)
		frame2Textinfo = utils1.getFrame2Textinfo(monsterset.type1, null);
    	//塔的处理与分析
    	Iterator<Being> it1 = towers.iterator();
        while(it1.hasNext())
        {
        	Being t = it1.next();
        	utils1.towerAnalysis(t, enemies, towers, bullets, renderList);
        }
    	
    	//子弹的处理和分析，并且判断有没有打中僵尸
    	Iterator<MoveableBeing> it2 = bullets.iterator();
        while(it2.hasNext())
        {
        	//任何人都会被concurrentmodificationexception气死
        	MoveableBeing bullet = it2.next();
        	utils1.bulletAnalysis(bullet, renderList, it2, enemies);
        }
        for(MoveableBeing b: _bullets)
        {
        	bullets.add(b);
        }
        _bullets.clear();
		//僵尸的处理很分析
    	Iterator<Being> it3 = enemies.iterator();
        while(it3.hasNext())
        {
        	Being z = it3.next();
    		utils1.zombieAnalysis(z, it3);
        }


    	//路上怪物的处理与分析
    	Iterator<Being> it4 = onroadmonsters.iterator();
        while(it4.hasNext())
        {
        	Being m = it4.next();
        	utils1.onroadmonsterAnalysis(m, enemies, towers, _bullets, renderList);
        }
        //utils1.heroAnalysis(hero, enemies, bullets, renderList, trail);
        if(HP <= 0)
        {
        	die();
        	return;
        }
        if(finalWaveZombies <= 0)
        {
        	win();
        	return;
        }
	}
	
	void die()
	{
		died = true;
		clearCatchingAndSelecting();
		frame2.update(textinfos[1], " * GAMEOVER..."
				                + "\n * You cannot give up just yet..."
				                + "\n * Whoever you are! stay determined..."
				                + "\n * Press 'z' to go back to the menu."
				);
	}
	
	void win()
	{
		won = true;
		clearCatchingAndSelecting();
		frame2.update(textinfos[1], " * YOU WON!"
				                + "\n * Press 'z' to go back to the menu."
				);
	}
	
	public void updateStateTime(Being being)
	{
		if(paused || died || won)
		return;
		if(slowed)
		being.stateTime += Gdx.graphics.getDeltaTime()/2;
		else
		being.stateTime += Gdx.graphics.getDeltaTime();
		if(!being.attachments.isEmpty())
		{
			for(Being b:being.attachments)
			{
				if(slowed)
				b.stateTime += Gdx.graphics.getDeltaTime()/2;
				else
				b.stateTime += Gdx.graphics.getDeltaTime();
			}
		}
	}
	
	void clearCatchingAndSelecting()
	{
		updateCatchingAndSelecting(MouseCatching.NOTHING, null);
	}
	void updateCatchingAndSelecting(MouseCatching mouseCatching, Being selectedBeing)
	{
		this.mouseCatching = mouseCatching;
		if(this.selectedBeing != selectedBeing)
		{
			System.out.println("selectedBeing changed");
			resetFreeStatus(selectedBeing);
			this.selectedBeing = selectedBeing;
		}
	}
	public void addEXP(int EXP)
	{
		this.EXP += EXP;
	}
	
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}
	@Override
	public void show() {
	}
	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}
	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}
	@Override
	public void dispose() {
		batch.dispose();
		button[0].removeListener(clickListener[0]);
		button[1].removeListener(clickListener[1]);
		button[2].removeListener(clickListener[2]);
		button[3].removeListener(clickListener[3]);
		button[4].removeListener(clickListener[4]);
		button[5].removeListener(clickListener[5]);
		inputMultiplexer.clear();
	}
}
