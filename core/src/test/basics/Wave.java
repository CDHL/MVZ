package test.basics;

import com.badlogic.gdx.Gdx;

import test.basics.Being.Type1;
import test.framework.Level;
import util.Utils2;

public class Wave
{
	public int zombieNum = 0;
	float[] enemyGapTime;
	int[] enemyLV;
	Type1[] enemyType;
	float waveStateTime;
	int index = 0;
	int number;
	boolean waveOver = false;
	String enemyTextInfo;
	Level level;
	boolean isFinalWave;
	public Wave(int number, String enemyTextInfo, float[] enemyGapTime, Type1[] enemyType, int[] enemyLV)
	{
		this.enemyGapTime = enemyGapTime;
		this.enemyType = enemyType;
		this.number = number;
		this.enemyTextInfo = enemyTextInfo;
		this.enemyLV = enemyLV;
		zombieNum = enemyLV.length - 1;
		if(enemyGapTime.length != enemyType.length || enemyType.length != enemyLV.length || enemyLV.length != enemyGapTime.length)
		{
			System.out.println("Wave Error: error in wave "+Integer.toString(number));
		}
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
			level.addNewZombieToStage(Utils2.getEnemy(enemyType[index], level.trail, level.assets, enemyLV[index]), level.trail);
			index ++;
			if(index == 1 && isFinalWave)
			{
				System.out.println("FinalWave!");
				level.counteringFinalWave = true;
			}
		}
	}
}