package test.basics;

import java.util.ArrayList;
import java.util.Iterator;

import beings.MoveableBeing;

public abstract class Bulletimplementer extends Implementer {

	public abstract boolean MoveBullet(MoveableBeing bullet);
	
	public abstract void bulletAnalysis(MoveableBeing bullet, ArrayList<Being> renderList, Iterator<MoveableBeing> it, ArrayList<Being> enemies);
}
