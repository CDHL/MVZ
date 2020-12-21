package test.basics;

import java.util.ArrayList;

import basics.Trail;
import beings.MoveableBeing;

public abstract class Towerimplementer extends Describableimplementer{

	public abstract void update(Being t, ArrayList<Being> enemies, ArrayList<Being> towers, ArrayList<MoveableBeing> bullets, ArrayList<Being> renderList, Trail trail);
	
	public abstract void towerLOVEup(Being t);
}
