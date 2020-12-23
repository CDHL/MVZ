package util;


public final class Data {

	public static final int STAGE_WIDTH = 1024;
	public static final int STAGE_HEIGHT = 680;
	
	public final static int[] whimsunEXPValue = {500, 1125, 1875, 99999};
	public final static int[] whimsunSkill0Range = new int[]{100, 100, 100, 100};
	public final static float[] whimsunSkill0CD = new float[]{14, 14, 14, 14};
	public final static float[] whimsunBullet1Damage = {1.8f, 4.5f, 8f, 99f};
	public final static float whimsunBullet1AngleSpeed = 1;
	public final static int whimsunBullet1Radius = 55;
	public final static float whimsunBullet1LastTime = 8;

	public final static int[] froggitEXPValue = {100, 225, 375, 99999};
	public final static int[] froggitSkill0Range = new int[]{200, 200, 200, 200};
	public final static float[] froggitSkill0CD = new float[]{0.25f, 0.25f, 0.25f, 0.25f};
	public final static float[] froggitBullet1Damage = {28, 65, 111, 999};
	public final static float froggitBullet1Speed = 10;

	public final static int[] moldsmalEXPValue = {200, 450, 750, 99999};
	public final static int[] moldsmalSkill0Range = new int[]{200, 200, 200, 200};
	public final static float[] moldsmalSkill0CD = new float[]{0.3f, 0.3f, 0.3f, 0.3f};
	public final static double[] moldsmalBullet1Args = new double[]{10};
	public final static float[] moldsmalBullet1Damage = {6, 15, 27, 999};
	public final static float moldsmalBullet1Speed = 30;
	public final static float[] moldsmalBullet2Damage = {1.75f, 4.5f, 8f, 99f};
	public final static float moldsmalBullet2LastTime = 0.35f;
	public final static float moldsmalBullet2Speed = 15;

	public final static int rockEXPValue = 250;
	public final static int rockOriginalHP = 4000;
	
	public final static int[] bucketOriginalHP = new int[]{1400, 1680, 2016, 2419, 2903, 3483, 4180, 5016, 6019, 7223};
	public final static int bucketZombieEXPValue = 58;
	public final static int[] coneOriginalHP = new int[]{560, 672, 806, 966, 1160, 1392, 1672, 2006, 2406, 2888};
	public final static int coneZombieEXPValue = 28;
	public final static int[] zombieOriginalHP = new int[]{280, 336, 403, 483, 580, 696, 836, 1003, 1203, 1444};
	public final static int zombieEXPValue = 8;
	
	public final static float[] zombieSpeed = new float[]{2.5f, 2.6f, 2.7f, 2.8f, 2.9f, 3.0f, 3.1f, 3.2f, 3.3f, 3.4f};
	public final static float[] zombieAttack = new float[]{50f, 60f, 72f, 86f, 103f, 125f, 149f, 179f, 215f, 257f};//经过1秒的帧数 就是50伤害
	
	public final static int maxNumber = 99999;

	public final static int[] friskOriginalHP = new int[] {2000};
	public final static float[] friskSpeed = new float[] {10};
	public final static int[] friskSkill0RangeArgs = new int[]{200};
	public final static float[] friskSkill0CDArgs = new float[]{0.25f};
	public final static float friskBullet1Damage = 28;
	public final static float friskBullet1Speed = 10;
	public final static int friskReviveGoalTime = 40;

	public final static int[] migospEXPValue = {100, 220, 364, 99999};
	public final static int[] migospSkill0RangeArgs = new int[]{50, 50, 50, 50};
	public final static float[] migospATBuffArgs = new float[]{0.2f, 0.4f, 0.6f, 99f};
	public final static float[] migospRGBuffArgs = new float[]{0.2f, 0.25f, 0.3f, 99f};
	

	public final static int[] looxEXPValue = {300, 495, 730, 99999};
	//*1.1^n*debuffargs
	public final static int[] looxSkill0RangeArgs = new int[]{200, 200, 200, 200};
	public final static float[] looxSkill0CDArgs = new float[]{0.25f, 0.25f, 0.25f, 0.25f};
	public final static float looxSkill1and2CD = 0.03f;
	public final static float looxBulletSpeed = 7;	
	public final static float[] looxDMGDebuffArgs = new float[]{1f, 1.5f, 2f, 99f};
	public final static float[] looxSkill0LastTime = new float[]{1f, 1.1f, 1.2f, 99f};

	//type2Args
	//bullet: {damage, HP, lastingTime, LV}
	//enemy: {HP, armorHP, giveEXP, LV, assignedtoenemy, trailID}
	//tower: {EXPValue, LV, free/upgradestatus(0=normal,1=free,2=upgrade)}
	//blocker: {EXPValue, HP, free/upgradestatus(0=normal,1=free,2=upgrade)}
	//hero: {HP, LV, position, assignedtoenemy, trailID}
	
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
