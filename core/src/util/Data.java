package util;


public final class Data {

	public static final int STAGE_WIDTH = 1024;
	public static final int STAGE_HEIGHT = 680;
	
	public final static int[] whimsunEXPValue = {500, 500};
	public final static int[] whimsunSkill0RangeArgs = new int[]{100};
	public final static float[] whimsunSkill0CDArgs = new float[]{12};
	public final static float whimsunBullet1Damage = 1.8f;
	public final static float whimsunBullet1AngleSpeed = 1;
	public final static int whimsunBullet1Radius = 55;
	public final static float whimsunBullet1LastTime = 8;

	public final static int[] froggitEXPValue = {100, 200};
	public final static int[] froggitSkill0RangeArgs = new int[]{200};
	public final static float[] froggitSkill0CDArgs = new float[]{0.25f};
	public final static float froggitBullet1Damage = 28;
	public final static float froggitBullet1Speed = 10;

	public final static int[] moldsmalEXPValue = {200, 200};
	public final static int[] moldsmalSkill0RangeArgs = new int[]{200};
	public final static float[] moldsmalSkill0CDArgs = new float[]{0.3f};
	public final static double[] moldsmalBullet1Args = new double[]{10};
	public final static float moldsmalBullet1Damage = 6;
	public final static float moldsmalBullet1Speed = 30;
	public final static float moldsmalBullet2Damage = 1.75f;
	public final static float moldsmalBullet2LastTime = 0.35f;
	public final static float moldsmalBullet2Speed = 15;

	public final static int rockEXPValue = 250;
	public final static int rockOriginalHP = 4000;
	
	public final static int[] bucketOriginalHP = new int[]{1000, 1200, 1440, 1730, 2070, 2500, 2980, 3580, 4290, 5150};
	public final static int bucketZombieEXPValue = 58;
	public final static int[] coneOriginalHP = new int[]{400, 480, 576, 691, 829, 996, 1194, 1433, 1720, 2064};
	public final static int coneZombieEXPValue = 28;
	public final static int[] zombieOriginalHP = new int[]{200, 240, 288, 346, 415, 498, 597, 717, 860, 1032};
	public final static int zombieEXPValue = 8;
	
	public final static float[] zombieSpeed = new float[]{5, 5.1f, 5.2f, 5.3f, 5.4f, 5.5f, 5.6f, 5.7f, 5.8f, 5.9f};
	public final static float[] zombieAttack = new float[]{100f, 120f, 144f, 173f, 207f, 250f, 298f, 358f, 429f, 515f};//经过1秒的帧数 就是100伤害
	
	public final static int maxNumber = 99999;

	public final static int[] friskOriginalHP = new int[] {2000};
	public final static float[] friskSpeed = new float[] {10};
	public final static int[] friskSkill0RangeArgs = new int[]{200};
	public final static float[] friskSkill0CDArgs = new float[]{0.25f};
	public final static float friskBullet1Damage = 28;
	public final static float friskBullet1Speed = 10;
	public final static int friskReviveGoalTime = 40;

	public final static int[] migospEXPValue = {100, 200};
	public final static int[] migospSkill0RangeArgs = new int[]{50};
	public final static float[] migospATBuffArgs = new float[]{0.2f};
	public final static float[] migospRGBuffArgs = new float[]{0.2f};
	

	public final static int[] looxEXPValue = {300, 500};
	public final static int[] looxSkill0RangeArgs = new int[]{200};
	public final static float[] looxSkill0CDArgs = new float[]{0.25f};
	public final static float looxSkill1and2CD = 0.03f;
	public final static float looxBulletSpeed = 7;	
	public final static float[] looxDMGDebuffArgs = new float[]{1f};
	public final static float[] looxSkill0LastTime = new float[]{1f};

	//type2Args
	//bullet: {damage, HP, lastingTime, LV}
	//enemy: {HP, armorHP, giveEXP, LV, assignedtoenemy}
	//tower: {EXPValue, LV, free/upgradestatus(0=normal,1=free,2=upgrade)}
	//blocker: {EXPValue, HP, free/upgradestatus(0=normal,1=free,2=upgrade)}
	//hero: {HP, LV, position, assignedtoenemy}
	
	//MoveArgs
	//normal: {velocityx, velocityy}
	//alongtrail: {leftenergy, speed, position}
	//rotating: {rotationOriginX, rotationOriginY, rotation}
	
	//buffArgs
	//{MIGOSP AT, MIGOSP RG, MIGOSP buffed(0 = no, 1 = yes), LOOX +dmg, LOOX debuffTimeLeft}
	
	//CDArgs
	//normal:{cdTime}
	//always:{}
	
	//RangeArgs
	//normal:{range}
	
}
