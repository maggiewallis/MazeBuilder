
public class Place
{
	private int r, c;
	
	public Place(int inR, int inC)
	{
		r = inR;
		c = inC;
	}
	
	public Place()
	{
		this(-1,-1);
	}
	
	public int row()
	{
		return r;
	}
	
	public int column()
	{
		return c;
	}

	public String toString()
	{
		return "["+r+","+c+"]";
	}
	
	public boolean equals(Object other)
	{
		if (! (other instanceof Place))
			return false;
		Place otherPlace = (Place)other;
		boolean match = false;
		//check whether the data in otherPlace matches the data in this Place.
		
			if (otherPlace.row() == r && otherPlace.column() == c)
			{
				match = true;
			}
		//--------------
		return match;
	}	
	
	
	public Place north()
	{
		Place result = null;
			result = new Place(r - 1, c);
		return result;
	}
	
	
	public Place south()
	{
		Place result = null;
		result = new Place(r + 1, c);
		return result;
	}
	
	public Place east()
	{
		Place result = null;
		result = new Place(r, c+1);
		return result;
	}
	
	public Place west()
	{
		Place result = null;
		result = new Place(r, c-1);
		return result;
	}
	
	
	public boolean isNeighbor(Place candidate)
	{
		boolean isNextDoor = false;
		if (candidate.equals(north()))
		{
			isNextDoor = true;
		}
		if (candidate.equals(south()))
		{
			isNextDoor = true;
		}
		if (candidate.equals(east()))
		{
			isNextDoor = true;
		}
		if (candidate.equals(west()))
		{
			isNextDoor = true;
		}
		return isNextDoor;
		
	}
	
}
