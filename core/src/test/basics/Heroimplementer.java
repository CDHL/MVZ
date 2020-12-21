package test.basics;

import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import basics.Trail;
import beings.MoveableBeing;

public abstract class Heroimplementer extends Describableimplementer {

	public abstract void update(Hero h, ArrayList<Being> enemies,
			ArrayList<MoveableBeing> bullets, ArrayList<Being> renderList, Trail trail);
	
	public abstract void beginGOTO(Hero h, Vector2 destination, int position);
}
