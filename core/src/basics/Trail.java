package basics;

import java.io.Serializable;
import java.util.ArrayList;

import com.badlogic.gdx.math.Vector2;

import util.Data;

@SuppressWarnings("serial")
public class Trail extends ArrayList<Vector2> implements Serializable {

	public Vector2 initialPosition;
	public Vector2 finalPosition;
	public float dstmin;
	public int size;
	public void addNewPosition(float x, float y)
	{
		Vector2 position = new Vector2(x, y);
		add(position);
	}
	// -> Vector2
	// - x坐标返回 int position
	// - y坐标返回 dstmin
	public Vector2 searchPosition(float x, float y)
	{
		int position = -1;
		double dstmin = Data.maxNumber;
		for(int i = 0; i < size; i ++)
		{
			if(get(i).dst(x, y) < dstmin)
			{
				position = i;
				dstmin = get(i).dst(x, y);
			}
		}
		return new Vector2((float)position, (float)dstmin);
	}
	public void update()
	{
		dstmin = this.get(0).dst(this.get(1));
		this.size = this.size();
		initialPosition = this.get(0);
		finalPosition = this.get(size - 1);
	}
}