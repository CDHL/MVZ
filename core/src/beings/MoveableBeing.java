package beings;

import com.badlogic.gdx.math.Rectangle;

import test.basics.Being;

public class MoveableBeing extends Being{
	
	public static enum MoveType {
		NONE,
		UNMOVEABLE, 
		NORMAL,
		ALONGTRAIL, 
		ABOUTTRAIL, 
		ROTATING,
	}
	protected MoveType moveType = MoveType.NONE;
	public void setMoveType (MoveType moveType) {
		this.moveType = moveType;
	}
	public MoveType getMoveType () {
		return moveType;
	}
	
	public double[] moveArgs = new double[5];
	public double[] setmoveArgs (double[] moveArgs) {
		this.moveArgs = moveArgs;
		return moveArgs;
	}
	
	public MoveableBeing(Rectangle relativeRectangle)
	{
		super(relativeRectangle);
	}
}
