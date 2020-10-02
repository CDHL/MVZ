package test.basics;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import basics.Skill;
import basics.Skill.CDType;
import basics.Skill.Rangetype;
import util.Utils1;

public class Being{

	public static enum Type1 {
		DEFAULTED,
		FROGGIT,
		FROGGIT_BULLET,
		ZOMBIE,
		CONE_ZOMBIE,
		BUCKET_ZOMBIE,
		WHIMSUN, 
		WHIMSUN_BULLET, 
		MOLDSMAL, 
		MOLDSMAL_BULLET1, 
		MOLDSMAL_BULLET2, 
		FRISK, 
		ROCK, 
		MIGOSP, 
		LOOX, 
		LOOX_BULLET1,
		LOOX_BULLET2,
		LOOX_BULLET3,
	}
	protected Type1 type1 = Type1.DEFAULTED;
	public void setType1 (Type1 type1) {
		this.type1 = type1;
	}
	public Type1 getType1 () {
		return type1;
	}
	

	public static enum Type2 {
		DEFAULTED,
		TOWER,
		MONSTERSET,
		BLOCKER,
		ENEMY,
		HERO, 
		BULLET,	
		ATTACHMENT,
	}
	protected Type2 type2 = Type2.DEFAULTED;
	public void setType2 (Type2 type2) {
		this.type2 = type2;
	}
	public Type2 getType2 () {
		return type2;
	}
	public float[] type2Args = new float[10];

	public float[] buffArgs = new float[10];

	public static enum Status {
		DEFAULTED,
		ATTACKING,
		MOVING,
	}
	protected Status status = Status.DEFAULTED;
	public void setStatus (Status status) {
		this.status = status;
	}
	public Status getStatus () {
		return status;
	}

	public static enum Quality {
		FRAGILE,
		NORMAL,
		LASTING,
	}
	protected Quality quality = Quality.NORMAL;
	public void setQuality (Quality quality) {
		this.quality = quality;
	}
	public Quality getQuality () {
		return quality;
	}
	
	public static enum Drawbase_name {
		NONE,
		FROGGIT, 
		FROGGIT_BULLET, 
		ZOMBIE_EATING_UP, 
		ZOMBIE_EATING_DOWN, 
		ZOMBIE_EATING_LEFT, 
		ZOMBIE_EATING_RIGHT, 
		ZOMBIE_WALKING_UP, 
		ZOMBIE_WALKING_DOWN, 
		ZOMBIE_WALKING_LEFT, 
		ZOMBIE_WALKING_RIGHT, 
		ZOMBIE_CONE_WALKING_UP, 
		ZOMBIE_CONE_WALKING_DOWN, 
		ZOMBIE_CONE_WALKING_LEFT, 
		ZOMBIE_CONE_WALKING_RIGHT, 
		ZOMBIE_BUCKET_WALKING_UP, 
		ZOMBIE_BUCKET_WALKING_DOWN, 
		ZOMBIE_BUCKET_WALKING_LEFT, 
		ZOMBIE_BUCKET_WALKING_RIGHT, 
		FRISK_WALKING_UP, 
		FRISK_WALKING_DOWN, 
		FRISK_WALKING_LEFT, 
		FRISK_WALKING_RIGHT, 
		ROCK,
		WHIMSUN, 
		WHIMSUN_BULLET, 
		MOLDSMAL, 
		MOLDSMAL_BULLET, 
		MIGOSP, 
		LOOX,
		LOOX_BULLET1,
		LOOX_BULLET2,
		LOOX_BULLET3,
	}
	protected Drawbase_name drawbase_name = Drawbase_name.NONE;
	public void setDrawbaseName (Drawbase_name drawbase_name) {
		this.drawbase_name = drawbase_name;
	}
	public Drawbase_name getDrawbaseName () {
		return drawbase_name;
	}

	float x, y, width, height;
	public float r = 1, g = 1, b = 1, alpha = 1;
	protected Rectangle relativeRectangle;
	public Rectangle entityRectangle;
	public float stateTime = 0;
	public Being assigned = null;
	
	protected class _color
	{
		protected float r = 1, g = 1, b = 1;
		protected _color(float r, float g, float b)
		{
			this.r = r;
			this.g = g;
			this.b = b;
		}
	}
	ArrayList<_color> colorList = new ArrayList<_color>();
	public void mixInColor(float r, float g, float b)
	{
		_color c = new _color(r, g, b);
		for(_color _c:colorList)
		{
			if(_c.r == r && _c.g == g && _c.b == b)
				return;
		}
		colorList.add(c);
	}
	public Color getMixedColor()
	{
		if(colorList.isEmpty())
			return new Color(r, g, b, alpha);
		float r=this.r, g=this.g, b=this.b;
		for(_color c:colorList)
		{
			r += c.r;
			g += c.g;
			b += c.b;
		}
		r /= colorList.size()+1;
		g /= colorList.size()+1;
		b /= colorList.size()+1;
		return new Color(r, g, b, alpha);
	}
	public void removeMixedColor(float r, float g, float b, Utils1 utils1)
	{
    	Iterator<_color> _it = colorList.iterator();
    	while(_it.hasNext())
    	{
    		_color _c = _it.next();
    		if(_c.r == r && _c.g == g && _c.b == b)
    		{
    			_it.remove();
    			break;
    		}
    	}
	}
	
	public boolean fliped = false;
	
	public ArrayList<Skill> skills = new ArrayList<Skill>();
	public ArrayList<Being> attachments = new ArrayList<Being>();

	//Construct being
	public Being()
	{
		this(new Rectangle(0, 0, 0, 0));
	}
	public Being(Rectangle relativeRectangle)
	{
		super();
		this.relativeRectangle = relativeRectangle;
	}
	
	public void addNewSkill(Rangetype rangetype, CDType cdtype)//, int[] rangeArgs, double[] cdArgs)
	{
		skills.add(new Skill(rangetype, cdtype));//, rangeArgs, cdArgs));
	}
	
	//Below this text are methods which controls the size and the position of the being.
	public Vector2 getPosition(Vector2 vector)
	{
		vector.set(this.getX(), this.getY());
		return vector;
	}
	public Being translate(float x, float y)
	{
		this.x += x;
		this.y += y;
		return this;
	}
	public float getRelativeX()
	{
		return relativeRectangle.x;
	}
	public float getRelativeY()
	{
		return relativeRectangle.y;
	}
	public float getRelativeCenterX()
	{
		return relativeRectangle.x + getWidth()/2;
	}
	public float getRelativeCenterY()
	{
		return relativeRectangle.y + getHeight()/2;
	}
	public Vector2 getRelativeCenter()
	{
		return new Vector2(getRelativeCenterX(), getRelativeCenterY());
	}
	public Rectangle setRelativeRectangle(Rectangle relativeRectangle)
	{
		this.relativeRectangle = relativeRectangle;
		return this.relativeRectangle;
	}
	public Rectangle getRelativeRectangle(Rectangle relativeRectangle)
	{
		relativeRectangle = new Rectangle(this.relativeRectangle);
		return relativeRectangle;
	}
	public Being setPosition (float x, float y){
		this.x = x;
		this.y = y;
		return this;
	}
	public Being setPosition (Vector2 vector){
		return setPosition(vector.x, vector.y);
	}
	public Being setCenter (Vector2 position) {
		return setCenter(position.x, position.y);
	}
	public Being setCenter (float x, float y) {
		setPosition(x - getWidth() / 2, y - getHeight() / 2);
		return this;
	}
	public Vector2 getCenter (Vector2 vector) {
		vector.x = getX() + getWidth() / 2;
		vector.y = getY() + getHeight() / 2;
		return vector;
	}

	public float getX()
	{
		//if(relativeRectangle.width < 0)//flipped
		//return this.x;
		return this.x + relativeRectangle.x;
	}
	public float getY()
	{
		//if(relativeRectangle.height < 0)//flipped
		//return this.y;
		return this.y + relativeRectangle.y;
	}
	public float getWidth()
	{
		return Math.abs(relativeRectangle.width);
	}
	public float getHeight()
	{
		return Math.abs(relativeRectangle.height);
	}
	public Vector2 getSize (Vector2 size) {
		return size.set(this.getWidth(), this.getHeight());
	}
	public boolean contains (float x, float y) {
		return this.getX() <= x && this.getX() + this.getWidth() >= x && this.getY() <= y && this.getY() + this.getHeight() >= y;
	}
	public boolean contains (Circle circle) {
		return (circle.x - circle.radius >= getX()) && (circle.x + circle.radius <= getX() + getWidth())
			&& (circle.y - circle.radius >= getY()) && (circle.y + circle.radius <= getY() + getHeight());
	}
	public boolean contains (Rectangle rectangle) {
		float xmin = rectangle.x;
		float xmax = xmin + rectangle.width;

		float ymin = rectangle.y;
		float ymax = ymin + rectangle.height;

		return ((xmin > getX() && xmin < getX() + getWidth()) && (xmax > getX() && xmax < getX() + getWidth()))
			&& ((ymin > getY() && ymin < getY() + getHeight()) && (ymax > getY() && ymax < getY() + getHeight()));
	}
	public boolean overlaps (Being r) {
		return getX() < r.getX() + r.getHeight() && getX() + getWidth() > r.getX() && getY() < r.getY() + r.getHeight() && getY() + getHeight() > r.getY();
	}

	//Above this text are methods which controls the size and the position of the being.
}
