package test.basics;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.math.Rectangle;

public class SpecialTiles {
	
	ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
	public void add(Rectangle rectangle)
	{
		rectangles.add(rectangle);
	}
	public void add(float x, float y, float width, float height) {
		
		Rectangle rectangle = new Rectangle();
		rectangle.set(x, y, width, height);
		add(rectangle);
	}
	public void remove(Rectangle rectangle)
	{
    	Iterator<Rectangle> _it = rectangles.iterator();
    	while(_it.hasNext())
    	{
    		Rectangle _r = _it.next();
    		if(_r == rectangle)
    		{
    			_it.remove();
    			break;
    		}
    	}
	}
	public boolean overlaps (Rectangle r) {
		for(Rectangle rectangle:rectangles)
		{
			if(rectangle.overlaps(r))
				return true;
		}
		return false;
	}

	public boolean contains (float x, float y) {
		for(Rectangle rectangle:rectangles)
		{
			if(rectangle.contains(x, y))
				return true;
		}
		return false;
	}
	
	public SpecialTiles(Rectangle[] rectangle)
	{
		for(Rectangle r:rectangle)
		{
			add(r);
		}
	}

	public SpecialTiles()
	{
	}
}
