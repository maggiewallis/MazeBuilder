import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlaceTest
{

	@Before
	public void setUp() throws Exception
	{
	}
	
	@Test
	public void testEqualsObject()
	{
		Place A = new Place(3,5);
		Place B = new Place(3,5);
		Place C = new Place(5,3);
		assertTrue(A.equals(A));
		assertTrue(A.equals(B));
		assertTrue(B.equals(A));
		assertFalse(A.equals(C));
	}

	@Test
	public void testNorth()
	{
		Place A = new Place(8,2);
		Place B = new Place(7,2);
		assertTrue(A.north().equals(B));
		assertFalse(B.north().equals(A));
	}

	@Test
	public void testSouth()
	{
		Place A = new Place(8,2);
		Place B = new Place(9,2);
		assertTrue(A.south().equals(B));
		assertFalse(B.south().equals(A));
	}

	@Test
	public void testEast()
	{
		Place A = new Place(8,2);
		Place B = new Place(8,3);
		assertTrue(A.east().equals(B));
		assertFalse(B.east().equals(A));
	}

	@Test
	public void testWest()
	{
		Place A = new Place(8,2);
		Place B = new Place(8,1);
		assertTrue(A.west().equals(B));
		assertFalse(B.west().equals(A));
	}
	
	@Test
	public void testIsNeighbor()
	{
		Place A = new Place(13,20);
		Place B = new Place(13,19);
		Place C = new Place(5,9);
		assertTrue(A.isNeighbor(B));
		assertTrue(B.isNeighbor(A));
		assertTrue(C.isNeighbor(C.north()));
		assertFalse(A.isNeighbor(A));
		assertFalse(A.isNeighbor(C));
		assertFalse(B.north().isNeighbor(B.south()));
		
	}

}
