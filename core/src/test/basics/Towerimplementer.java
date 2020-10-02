package test.basics;

import java.util.ArrayList;

import basics.Trail;
import beings.MoveableBeing;

public abstract class Towerimplementer extends Implementer{

	public abstract void towerAnalysis(Being t, ArrayList<Being> enemies, ArrayList<Being> towers, ArrayList<MoveableBeing> bullets, ArrayList<Being> renderList, Trail trail);
	
}
