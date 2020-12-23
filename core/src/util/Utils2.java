package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import basics.Assets;
import basics.Skill;
import basics.Trail;
import beings.MoveableBeing;
import beings.MoveableBeing.MoveType;
import beings.TrailBeing;
import test.basics.Being;
import test.basics.Being.Drawbase_name;
import test.basics.Being.Quality;
import test.basics.Being.Status;
import test.basics.Being.Type1;
import test.basics.Being.Type2;
import test.basics.Hero;

public final class Utils2 {

	
	public static Array<TextureRegion> getFrames(Texture animationTexture, int frameRows, int frameCols)
	{
		int perCellWidth = animationTexture.getWidth() / frameCols;		// 计算每一个小人单元格的宽度
		int perCellHeight = animationTexture.getHeight() / frameRows;	// 计算每一个小人单元格的高度
		
		// 按照指定的宽高作为一个单元格分割大图纹理, 分割后的结果为一个 纹理区域二维数组, 数组中的元素是分割出来的小人单元格
		TextureRegion[][] cellRegions = TextureRegion.split(animationTexture, perCellWidth, perCellHeight);
		
		// 把二维数组变为一维数组, 因为 Animation 只能接收一维数组作为关键帧序列, 数组中的一个元素（小人单元格的纹理区域）表示一个关键帧
		//TextureRegion[] frames = new TextureRegion[frameRows * frameCols];
		Array<TextureRegion> frames = new Array<TextureRegion>(frameRows*frameCols);
		for (int row = 0; row < frameRows; row++) {
			for (int col = 0; col < frameCols; col++) {
				//frames[index++] = cellRegions[row][col];
				frames.add(cellRegions[row][col]);
			}
		}
		return frames;
	}

	public static Trail gettrail(final String path)
	{
		Trail trail = null;
	          
		try{
			//FileReader fileReader = new FileReader(file);
			//BufferedReader reader = new BufferedReader(fileReader);
			trail = new Trail ();
			System.out.println(path);
			System.out.println("用户的当前工作目录:/n"+System.getProperty("user.dir")); 
			InputStream in = Utils2.class.getClassLoader().getResourceAsStream(path); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
			
			String result = null;
			while ((result = reader.readLine()) != null)//读一行就列出一行，直到没有东西可以读为止
			{
				String[] args = result.split(",");
				trail.addNewPosition(Float.parseFloat(args[0]), Float.parseFloat(args[1]));
			}
			reader.close();
			
			trail.update();
			
		}catch(IOException ex){ex.printStackTrace();}

		return trail;
	}
	
	public static Being getMonster(Type1 type, Rectangle relativeRectangle)
	{
		switch(type)
		{
			case FROGGIT:
			{
				return getTower(type, relativeRectangle);
			}
			case WHIMSUN:
			{
				return getTower(type, relativeRectangle);
			}
			case ROCK:
			{
				return getBlocker(type, relativeRectangle);
			}
			case MOLDSMAL:
			{
				return getTower(type, relativeRectangle);
			}
			case MIGOSP:
			{
				return getTower(type, relativeRectangle);
			}
			case LOOX:
			{
				return getTower(type, relativeRectangle);
			}
			default:{}
		}
		return null;
	}
	static Being getTower(Type1 type, Rectangle relativeRectangle)
	{
		Being t = null;
		switch(type)
		{
			case FROGGIT:
			{
				Being _t = new Being(relativeRectangle);
				_t.type2Args[0] = Data.froggitEXPValue[0];//EXPValue
				_t.addNewSkill(Skill.Rangetype.NORMAL, Skill.CDType.NORMAL);//, Data.froggitSkill0RangeArgs, Data.froggitSkill0CDArgs);
				_t.skills.get(0).cdTime = Data.maxNumber;
				_t.setDrawbaseName(Being.Drawbase_name.FROGGIT);
				_t.upgradeAble = true;
				t = _t;
				break;
			}
			case WHIMSUN:
			{
				Being _t = new Being(relativeRectangle);
				_t.type2Args[0] = Data.whimsunEXPValue[0];//EXPValue
				_t.addNewSkill(Skill.Rangetype.NORMAL, Skill.CDType.NORMAL);//, Data.whimsunSkill0RangeArgs, Data.whimsunSkill0CDArgs);
				_t.skills.get(0).cdTime = Data.maxNumber;
				_t.setDrawbaseName(Being.Drawbase_name.WHIMSUN);
				_t.upgradeAble = true;
				t = _t;
				break;
			}
			case MOLDSMAL:
			{
				Being _t = new Being(relativeRectangle);
				_t.type2Args[0] = Data.moldsmalEXPValue[0];//EXPValue
				_t.addNewSkill(Skill.Rangetype.NORMAL, Skill.CDType.NORMAL);//, Data.moldsmalSkill0RangeArgs, Data.moldsmalSkill0CDArgs);
				_t.skills.get(0).cdTime = Data.maxNumber;
				_t.setDrawbaseName(Being.Drawbase_name.MOLDSMAL);
				_t.upgradeAble = true;
				t = _t;
				break;
			}
			case MIGOSP:
			{
				Being _t = new Being(relativeRectangle);
				_t.type2Args[0] = Data.migospEXPValue[0];//EXPValue
				_t.addNewSkill(Skill.Rangetype.NORMAL, Skill.CDType.ALWAYS);//, Data.migospSkill0RangeArgs, null);
				_t.setDrawbaseName(Being.Drawbase_name.MIGOSP);
				_t.upgradeAble = true;
				t = _t;
				break;
			}
			case LOOX:
			{
				Being _t = new Being(relativeRectangle);
				_t.type2Args[0] = Data.looxEXPValue[0];//EXPValue
				_t.addNewSkill(Skill.Rangetype.NORMAL, Skill.CDType.NORMAL);
				_t.skills.get(0).cdTime = Data.maxNumber;
				_t.addNewSkill(Skill.Rangetype.NORMAL, Skill.CDType.NORMAL);
				_t.skills.get(1).cdTime = 0;
				_t.addNewSkill(Skill.Rangetype.NORMAL, Skill.CDType.NORMAL);
				_t.skills.get(2).cdTime = 0;
				_t.setDrawbaseName(Being.Drawbase_name.LOOX);
				_t.upgradeAble = true;
				t = _t;
				break;
			}
			default:{break;}
		}
		t.type2Args[1] = 1;//LV
		t.type2Args[2] = 0;//free/upgradestatus(0=normal,1=free,2=upgrade)
		t.setType1(type);
		t.setType2(Type2.TOWER);
		return t;
	}
	static Being getBlocker(Type1 type, Rectangle relativeRectangle)
	{
		Being b = null;
		switch(type)
		{
			case ROCK:
			{
				Being _b = new Being(relativeRectangle);
				_b.type2Args[0] = Data.rockEXPValue;//EXPValue
				_b.type2Args[1] = Data.rockOriginalHP;//HP
				_b.type2Args[2] = 0;//free/upgradestatus(0=normal,1=free,2=upgrade)
				_b.setDrawbaseName(Being.Drawbase_name.ROCK);
				b = _b;
				break;
			}
			default:{break;}
		}
		b.setType1(type);
		b.setType2(Type2.BLOCKER);
		return b;
	}
	public static Being getEnemy(Type1 type, Trail trail, Assets assets, int LV, int routeID)
	{
		Being e = null;
		switch(type)
		{
			case ZOMBIE:
			{
				TrailBeing _e = new TrailBeing(assets.zombie_walking_RRectangle);
				_e.moveArgs[0] = 0;
				_e.moveArgs[1] = Data.zombieSpeed[LV - 1];
				_e.moveArgs[2] = 0;
				_e.type2Args[3] = LV;
				_e.type2Args[0] = Data.zombieOriginalHP[LV - 1];
				_e.type2Args[1] = 0;
				_e.type2Args[2] = Data.zombieEXPValue;
				_e.trail = trail;
				e = _e;
				break;
			}
			case CONE_ZOMBIE:
			{
				TrailBeing _e = new TrailBeing(assets.zombie_walking_RRectangle);
				_e.moveArgs[0] = 0;
				_e.moveArgs[1] = Data.zombieSpeed[LV - 1];
				_e.moveArgs[2] = 0;
				_e.type2Args[3] = LV;
				_e.type2Args[0] = Data.zombieOriginalHP[LV - 1];
				_e.type2Args[1] = Data.coneOriginalHP[LV - 1];
				_e.type2Args[2] = Data.coneZombieEXPValue;
				_e.trail = trail;
				Being cone = new Being(assets.zombie_cone_walking_RRectangle);
				cone.setType2(Type2.ATTACHMENT);
				cone.setPosition(0, 46);
				_e.attachments.add(cone);
				e = _e;
				break;
			}
			case BUCKET_ZOMBIE:
			{
				TrailBeing _e = new TrailBeing(assets.zombie_walking_RRectangle);
				_e.moveArgs[0] = 0;
				_e.moveArgs[1] = Data.zombieSpeed[LV - 1];
				_e.moveArgs[2] = 0;
				_e.type2Args[3] = LV;
				_e.type2Args[0] = Data.zombieOriginalHP[LV - 1];
				_e.type2Args[1] = Data.bucketOriginalHP[LV - 1];
				_e.type2Args[2] = Data.bucketZombieEXPValue;
				_e.trail = trail;
				Being bucket = new Being(assets.zombie_bucket_walking_RRectangle);
				bucket.setType2(Type2.ATTACHMENT);
				bucket.setPosition(0, 38);
				_e.attachments.add(bucket);
				e = _e;
				break;
			}
			default:{break;}
		}
		e.setType1(type);
		e.setType2(Type2.ENEMY);
		e.setStatus(Status.MOVING);
		//设置僵尸的route ID
		e.type2Args[5] = routeID;
		return e;
	}
	public static Hero getHero(Type1 type, Assets assets)
	{
		Hero e = null;
		switch(type)
		{
			case FRISK:
			{
				e = new Hero(assets.frisk_layer_right_walking_RRectangle, Data.friskReviveGoalTime, Data.friskOriginalHP[0]);
				e.type2Args[1] = 1;
				e.type2Args[2] = 500;
				e.addNewSkill(Skill.Rangetype.NORMAL, Skill.CDType.NORMAL);//, Data.friskSkill0RangeArgs, Data.friskSkill0CDArgs);
				e.skills.get(0).cdTime = Data.maxNumber;
				e.setDrawbaseName(Drawbase_name.FRISK_WALKING_DOWN);
				break;
			}
			default:{break;}
		}
		e.type2Args[0] = e.originalHP;
		e.setType1(type);
		e.setType2(Type2.HERO);
		e.setStatus(Status.DEFAULTED);
		e.setMoveType(MoveType.NORMAL);
		return e;
	}
	public static MoveableBeing getBullet(MoveType type, double[] bulletArgs, Rectangle relativeRectangle)//Vector2 startPosition, Vector2 endPosition, Rectangle relativeRectangle)
	{
		double startPositionX = bulletArgs[0];
		double startPositionY = bulletArgs[1];
		switch(type)
		{
			case NORMAL:
			{
		    	//double distanceX = endPosition.x-startPosition.x;
		    	//double distanceY = endPosition.y-startPosition.y;
				double endPositionX = bulletArgs[2];
				double endPositionY = bulletArgs[3];
		    	double distanceX = endPositionX-startPositionX;
		    	double distanceY = endPositionY-startPositionY;
		    	float distance = (float)Math.sqrt(distanceX*distanceX + distanceY*distanceY);
		    	double v = bulletArgs[4];
		    	double vx = v*(distanceX)/distance;
		    	double vy = v*(distanceY)/distance;

		    	MoveableBeing bullet = new MoveableBeing(relativeRectangle);
		    	bullet.moveArgs[0] = vx;
		    	bullet.moveArgs[1] = vy;
				bullet.setPosition((float)startPositionX, (float)startPositionY);
				bullet.setMoveType(type);
				bullet.setQuality(Quality.FRAGILE);
				return bullet;
			}
			case ROTATING:
			{
		    	MoveableBeing bullet = new MoveableBeing(relativeRectangle);
		    	bullet.moveArgs[0] = startPositionX;
		    	bullet.moveArgs[1] = startPositionY;
				float radius = (float)bulletArgs[2];
		    	bullet.moveArgs[2] = radius;//rotation

				bullet.setMoveType(type);
				bullet.setQuality(Quality.LASTING);
				return bullet;
			}
			default:{}
		}
		return null;
	}
	
	public static MoveableBeing getBullet(Type1 type, double[] bulletArgs, Rectangle relativeRectangle, int LV, float[] buffArgs)
	{
		MoveableBeing b = null;
		switch(type)
		{
			case FROGGIT_BULLET:
			{
				b = getBullet(MoveType.NORMAL, bulletArgs, relativeRectangle);
		    	b.type2Args[1] = 0;
		    	b.type2Args[2] = Data.maxNumber;
				break;
			}
			case WHIMSUN_BULLET:
			{
				b = getBullet(MoveType.ROTATING, bulletArgs, relativeRectangle);
		    	b.type2Args[1] = Data.maxNumber;
		    	b.type2Args[2] = Data.whimsunBullet1LastTime;
		    	b.alpha = 0;
				break;
			}
			case MOLDSMAL_BULLET1:
			{
				b = getBullet(MoveType.NORMAL, bulletArgs, relativeRectangle);
		    	b.type2Args[1] = 0;
		    	b.type2Args[2] = Data.maxNumber;
				break;
			}
			case MOLDSMAL_BULLET2:
			{
				b = getBullet(MoveType.NORMAL, bulletArgs, relativeRectangle);
		    	b.type2Args[1] = Data.maxNumber;
		    	b.type2Args[2] = Data.moldsmalBullet2LastTime;
				break;
			}
			case LOOX_BULLET1:
			{
				b = getBullet(MoveType.NORMAL, bulletArgs, relativeRectangle);
		    	b.type2Args[1] = 0;
		    	b.type2Args[2] = Data.maxNumber;
				break;
			}
			case LOOX_BULLET2:
			{
				b = getBullet(MoveType.NORMAL, bulletArgs, relativeRectangle);
		    	b.type2Args[1] = 0;
		    	b.type2Args[2] = Data.maxNumber;
				break;
			}
			case LOOX_BULLET3:
			{
				b = getBullet(MoveType.NORMAL, bulletArgs, relativeRectangle);
		    	b.type2Args[1] = 0;
		    	b.type2Args[2] = Data.maxNumber;
				break;
			}
			default:{break;}
		}
		b.setType1(type);
		b.setType2(Type2.BULLET);
		b.buffArgs = buffArgs;
    	b.type2Args[3] = LV;
    	b.type2Args[0] = getAT(b);
		return b;
	}
	
	public static int getDeltaTrailPosition(TrailBeing tb, int time)
	{
		//充满怪物的能量
		float energy = (float)tb.moveArgs[0] + (float)tb.moveArgs[1];
		Trail trail = tb.trail;

		float _energy = energy + (time-1)*(float)tb.moveArgs[1];
		float dst = trail.dstmin;
		return (int)Math.floor((float)_energy/dst);
	}
	
	public static Vector2 getNormalBulletDestination(Being enemy, float bulletStartX, float bulletStartY, double bulletSpeed, Trail trail)
	{
		if(enemy.getStatus() != Status.MOVING)
		{
			return enemy.getPosition(new Vector2());
		}
		//这个Vector2的结局：在return的时候被销毁
		Vector2 bulletStartPosition = new Vector2(bulletStartX, bulletStartY);
		//Rectangle enemyRectangle = getRelativeRectangle(enemy);
		TrailBeing e = (TrailBeing)enemy;
		int minTime = 0, maxTime = (e.trail.size - (int)e.moveArgs[2])/(int)e.moveArgs[1] - 1;
		int medianTime = (minTime + maxTime)/2;
		
		double minDistanceDifference = 10000;
		int bestTime = medianTime;
		
		//最后有用的输出：bestTime
		do
		{
			Vector2 medianPresumedDestination = new Vector2(0, 0);
			int medianPresumedDestinationPosition = (int)e.moveArgs[2] + getDeltaTrailPosition(e, medianTime);
			if(medianPresumedDestinationPosition > trail.size - 1)
			medianPresumedDestinationPosition = trail.size - 1;
			//利用求出来的position推测出假定僵尸的位置
			medianPresumedDestination.set
			(trail.get(medianPresumedDestinationPosition).x + e.getRelativeCenterX(),
			 trail.get(medianPresumedDestinationPosition).y + e.getRelativeCenterY());
			//在medianTime下子弹飞行的距离
			double medianBulletFlyDistance = (double)(medianTime*bulletSpeed);
			//在medianTime下僵尸的最终移动点距离子弹的距离
			double medianBullet2EnemyDistance = (double)(bulletStartPosition.dst(medianPresumedDestination));
			
			//在二分的时候进行强制筛选：判断子弹飞行的距离 和 子弹僵尸距离最低值是在哪个time
			if(minDistanceDifference > Math.abs(medianBulletFlyDistance - medianBullet2EnemyDistance))
			{
				minDistanceDifference = Math.abs(medianBulletFlyDistance - medianBullet2EnemyDistance);
				bestTime = medianTime;
			}
			
			if(medianBulletFlyDistance - medianBullet2EnemyDistance < 0)
			{
				minTime = medianTime;
				medianTime = (minTime + maxTime)/2;
			}
			else
			{
				maxTime = medianTime;
				medianTime = (minTime + maxTime)/2;
			}
		}
		while(Math.abs(minTime - maxTime) > 1);
		
		int destinationPosition = (int)e.moveArgs[2] + getDeltaTrailPosition(e, bestTime);
		Vector2 destination = new Vector2(0, 0);

		destination.set
		(trail.get(destinationPosition).x + e.getRelativeCenterX(),
		 trail.get(destinationPosition).y + e.getRelativeCenterY());
		
		return destination;
	}
	
	static String freeMassage(Type1 type1, double EXP)
	{
		String monsterName = "ERROR";
		switch (type1)
		{
			case FROGGIT:
			{
				monsterName = "froggit";
				break;
			}
			case WHIMSUN:
			{
				monsterName = "whimsun";
				break;
			}
			case ROCK:
			{
				monsterName = "rock";
				break;
			}
			case MOLDSMAL:
			{
				monsterName = "moldsmal";
				break;
			}
			case MIGOSP:
			{
				monsterName = "migosp";
				break;
			}
			case LOOX:
			{
				monsterName = "loox";
				break;
			}
			default:{break;}
		}
		return 			   " * press [x] again to free "+monsterName+" for "+(int)EXP+" EXP.";
	}
	public static int getLV(Being r)
	{
		float LV = -1;
		switch (r.getType2())
		{
			case TOWER:
			{
				LV = r.type2Args[1];
				break;
			}
			case ENEMY:
			{
				LV = r.type2Args[3];
				break;
			}
			case BULLET:
			{
				LV = r.type2Args[3];
				break;
			}
			case HERO:
			{
				LV = r.type2Args[1];
				break;
			}
			default:
				break;
		}
		return (int)LV;
	}
	public static int getRG(Being r)
	{
		int LV = getLV(r);
		float range = getRG(r.getType1(), LV);
		if(r.buffArgs[1] != 0)
		{
			range *= (1f + r.buffArgs[1]);
		}
		return (int)range;
	}
	public static int getRG(Type1 type, int LV, float[] buffArgs)
	{
		float range = getRG(type, LV);
		if(buffArgs[1] != 0)
		{
			range *= (1f + buffArgs[1]);
		}
		return (int)range;
	}
	public static int getRG(Type1 type, int LV)
	{
		int range = -1;
		switch (type)
		{
			case FRISK:
			{
				range = Data.friskSkill0RangeArgs[LV - 1];
				break;
			}
			case FROGGIT:
			{
				range = Data.froggitSkill0Range[LV - 1];
				break;
			}
			case MIGOSP:
			{
				range = Data.migospSkill0RangeArgs[LV - 1];
				break;
			}
			case MOLDSMAL:
			{
				range = Data.moldsmalSkill0Range[LV - 1];
				break;
			}
			case WHIMSUN:
			{
				range = Data.whimsunSkill0Range[LV - 1];
				break;
			}
			case LOOX:
			{
				range = Data.looxSkill0RangeArgs[LV - 1];
				break;
			}
			default:
				break;
		}
		return range;
	}
	
	public static float getAT(Being r)
	{
		int LV = getLV(r);
		float damage = getAT(r.getType1(), LV);
		if(r.buffArgs[0] != 0)
		{
			damage *= (1f + r.buffArgs[0]);
		}
		return damage;
	}
	public static float getAT(Type1 type, int LV, float[] buffArgs)
	{
		float damage = getAT(type, LV);
		if(buffArgs[0] != 0)
		{
			damage *= (1f + buffArgs[0]);
		}
		return damage;
	}
	public static float getAT(Type1 type, int LV)
	{
		float damage = -1;
		switch (type)
		{
			case BUCKET_ZOMBIE:
			{
				damage = Data.zombieAttack[LV - 1];
				break;
			}
			case CONE_ZOMBIE:
			{
				damage = Data.zombieAttack[LV - 1];
				break;
			}
			case FRISK:
			{
				damage = Data.friskBullet1Damage;
				break;
			}
			case FROGGIT:
			{
				damage = Data.froggitBullet1Damage[LV - 1];
				break;
			}
			case FROGGIT_BULLET:
			{
				damage = Data.froggitBullet1Damage[LV - 1];
				break;
			}
			case MOLDSMAL_BULLET1:
			{
				damage = Data.moldsmalBullet1Damage[LV - 1];
				break;
			}
			case MOLDSMAL_BULLET2:
			{
				damage = Data.moldsmalBullet2Damage[LV - 1];
				break;
			}
			case WHIMSUN:
			{
				damage = Data.whimsunBullet1Damage[LV - 1];
				break;
			}
			case WHIMSUN_BULLET:
			{
				damage = Data.whimsunBullet1Damage[LV - 1];
				break;
			}
			case ZOMBIE:
			{
				damage = Data.zombieAttack[LV - 1];
				break;
			}
			default:
				break;
		}
		return damage;
	}
}
