package test.basics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Describableimplementer extends Implementer {

	public abstract void render(Being r, SpriteBatch batch, float deltaX, float deltaY);

	public abstract String information(Being infoGiver);
	
}
