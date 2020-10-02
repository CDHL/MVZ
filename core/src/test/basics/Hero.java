package test.basics;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import beings.MoveableBeing;

public class Hero extends MoveableBeing {

	public Vector2 destination = null;
	public boolean died = false;
	public final int reviveGoalTime;
	public float reviveTime = 0;
	public final int originalHP;

	public Hero(Rectangle relativeRectangle, int reviveGoalTime, int originalHP)
	{
		super(relativeRectangle);
		this.reviveGoalTime = reviveGoalTime;
		this.originalHP = originalHP;
	}
	
}
