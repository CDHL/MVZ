package test.basics;

import com.badlogic.gdx.Gdx;

import test.basics.Being.Type1;
import test.framework.Level;
import util.Utils2;

public class Wave
{
	public int zombieNum = 0;
	float[] enemyGapTime;
	short[] enemyLV;
	//指示enemy使用的数组ID，从0开始
	short[] enemyRouteID;
	Type1[] enemyType;
	float waveStateTime;
	int index = 0;
	int number;
	boolean waveOver = false;
	String enemyTextInfo;
	Level level;
	boolean isFinalWave;
	public Wave(int number, String enemyTextInfo, ZIIW[] ziiws)//float[] enemyGapTime, Type1[] enemyType, short[] enemyLV, short[] enemyRouteID)
	{
		int ziiwsLength = ziiws.length;
		this.enemyGapTime = new float[ziiwsLength];
		this.enemyType = new Type1[ziiwsLength];
		this.enemyLV = new short[ziiwsLength];
		this.enemyRouteID = new short[ziiwsLength];
		for(int i = 0; i < ziiwsLength; i ++)
		{
			if(ziiws[i] == null)
			enemyGapTime[i] = -1;
			else
			{
				enemyGapTime[i] = ziiws[i].gapTime;
				enemyType[i] = ziiws[i].type;
				enemyLV[i] = ziiws[i].LV;
				enemyRouteID[i] = ziiws[i].routeID;
			}
		}
		//this.enemyGapTime = enemyGapTime;
		//this.enemyType = enemyType;
		//this.enemyLV = enemyLV;
		//this.enemyRouteID = enemyRouteID;
		this.number = number;
		this.enemyTextInfo = enemyTextInfo;
		zombieNum = enemyLV.length - 1;
		/*if(enemyGapTime.length != enemyType.length || enemyType.length != enemyLV.length
		|| enemyLV.length != enemyRouteID.length || enemyRouteID.length != enemyGapTime.length)
		{
			System.out.println("Wave Error: error in wave "+Integer.toString(number));
		}*/
	}
	public void assignLevel(Level level)
	{
		this.level = level;
		isFinalWave = (number == level.totalWaves);
	}
	public String getWaveTextInfo()
	{
		if(number == level.totalWaves)
		return
			  " * Final wave in "+(enemyGapTime[0]-(int)waveStateTime)+" seconds:"
		  + "\n * "+enemyTextInfo
		  + "\n * Press 'C' to quick-start the next wave!";
			
		return 
			  " * A huge wave of zombies is approaching!"
		  + "\n * Wave "+number+" of "+level.totalWaves+" in "+(enemyGapTime[0]-(int)waveStateTime)+" seconds:"
		  + "\n * "+enemyTextInfo
		  + "\n * Press 'C' to quick-start the next wave!";
	}
	public boolean isComing()
	{
		return index == 0;
	}
	public boolean isOver()
	{
		return waveOver;
	}
	public void quickStart()
	{
		enemyGapTime[0] = 0;
	}
	public void updateWave()
	{
		if(enemyGapTime[index] < 0)
		{
			waveOver = true;
			return;
		}
		waveStateTime += Gdx.graphics.getDeltaTime();
		if(waveStateTime >= enemyGapTime[index])
		{
			waveStateTime = 0;
			level.addNewZombieToStage(Utils2.getEnemy(enemyType[index], level.trailes[enemyRouteID[index]], level.assets, enemyLV[index], enemyRouteID[index]), level.trailes[enemyRouteID[index]]);
			index ++;
			if(index == 1 && isFinalWave)
			{
				System.out.println("FinalWave!");
				level.counteringFinalWave = true;
			}
		}
	}
}