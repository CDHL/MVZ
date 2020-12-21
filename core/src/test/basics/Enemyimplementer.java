package test.basics;

import java.util.Iterator;

import beings.MoveableBeing;

public abstract class Enemyimplementer extends Describableimplementer {

	public abstract void moveBeings(MoveableBeing b, float x, float y);
	
	public abstract void attack(MoveableBeing b1, Being b2);
	
	public abstract void update(Being z, Iterator<Being> it);
}
