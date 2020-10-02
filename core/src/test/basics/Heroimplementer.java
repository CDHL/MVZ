package test.basics;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import basics.Trail;
import beings.MoveableBeing;

public abstract class Heroimplementer extends Implementer {

	public abstract void heroAnalysis(Hero h, ArrayList<Being> enemies,
			ArrayList<MoveableBeing> bullets, ArrayList<Being> renderList, Trail trail);
	
	public abstract void beginGOTO(Hero h, Vector2 destination, int position);
}
