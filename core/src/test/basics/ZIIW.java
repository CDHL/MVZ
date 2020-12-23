package test.basics;

import test.basics.Being.Type1;

// Zombie info in wavesµÄËõÐ´
public class ZIIW {
	
	public float gapTime;
	public Type1 type;
	public short LV;
	public short routeID;
	public ZIIW(float gapTime, Type1 type, short LV, short routeID)
	{
		this.gapTime = gapTime;
		this.type = type;
		this.LV = LV;
		this.routeID = routeID;
	}
}
