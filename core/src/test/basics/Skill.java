package test.basics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Skill {

	/**
	 * 
	 * @author https://www.cnblogs.com/MiniHouse/p/3844451.html
	 *
	 */
	public static boolean IsInside( float x, float y, Circle circle )
	{
		float disX = x - circle.x;
		float disY = y - circle.y;
		return disX*disX + disY*disY <= circle.radius*circle.radius;
	}
		        
	public static float Distance( float x1, float y1, float x2, float y2 )
	{
		return (float)Math.sqrt( (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) );
	}
	
    public static boolean IsOverlap( Rectangle rect0, Circle circle0 )
    {
	    float x1 = rect0.x, y1 = rect0.y, w = rect0.width, h = rect0.height;
	    float cx1 = x1+w/2, cy1 = y1+h/2;
	    float x2 = circle0.x, y2 = circle0.y, r = circle0.radius;
	    
	    /////overlap with the corners
	    if( IsInside(x1,y1,circle0) || IsInside(x1+w,y1,circle0) || IsInside(x1,y1+h,circle0) || IsInside(x1+w,y1+h,circle0) ){
	    return true;
	    }
	    ////overlap with the left or the right edge or s inside of the rect
	    if( Distance( cx1, cy1, x2, y2 )<w/2+r && Math.abs( cy1-y2 )<h/2 ){
	    return true;
	    }
	    ////overlap with the top or the bottom edge or s inside of the rect
	    if( Distance( cx1, cy1, x2, y2 )<h/2+r && Math.abs( cx1-x2 )<w/2 ){
	    return true;
	    }

	    return false;
	}
	
	public static enum Rangetype {
		NONE,
		NORMAL,
	}
	private Rangetype rangetype = Rangetype.NONE;
	public void setRangetype (Rangetype rangetype) {
		this.rangetype = rangetype;
	}
	public Rangetype getRangetype () {
		return rangetype;
	}
	//public int[] rangeArgs;
	public boolean rangeTest(Being detecter, Being detectee, int[] rangeArgs)
	{
		Vector2 detecterPosition = detecter.getCenter(new Vector2());
		//Vector2 detecteePosition = detectee.getPosition(new Vector2());
		switch(rangetype)
		{
			case NORMAL://Normal情况下是圆形射击半径
			{
				Circle range = new Circle(detecterPosition, (float)rangeArgs[0]);
				Rectangle rrectangle = detectee.getRelativeRectangle(new Rectangle());
				rrectangle.x += detectee.getX();
				rrectangle.y += detectee.getY();
				return IsOverlap(rrectangle, range);
			}
			default:{}
		}
		return false;
	}
	

	public static enum CDType {
		NONE,
		NORMAL,
		ALWAYS,
	}
	private CDType cdtype = CDType.NONE;
	public float cdTime = 0;
	public void setCDType (CDType cdtype) {
		this.cdtype = cdtype;
	}
	public CDType getCDType () {
		return cdtype;
	}
	//public double[] cdArgs;
	public boolean cdReady(float cdTime)
	{
		switch(cdtype)
		{
			case NORMAL:
			{
				return this.cdTime >= cdTime;//cdArgs[0];
			}
			case ALWAYS:
			{
				return true;
			}
			default:{}
		}
		return false;
	}
	public void updateCDTime()
	{
		cdTime += Gdx.graphics.getDeltaTime();
	}
	public void resetCDTime()
	{
		setCDTime(0);
	}
	public void setCDTime(float cdTime)
	{
		this.cdTime = cdTime;
	}

	public Skill(Rangetype rangetype, CDType cdtype)//, int[] rangeArgs, double[] cdArgs)
	{
		this.rangetype = rangetype;
		this.cdtype = cdtype;
		//this.rangeArgs = rangeArgs;
		//this.cdArgs = cdArgs;
	}
}
