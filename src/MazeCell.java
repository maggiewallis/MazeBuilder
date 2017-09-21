import java.awt.Graphics;

public class MazeCell implements MazeConstants
{
	private Place loc;
	private int status;
	private boolean isStart;
	private boolean isEnd;

	private boolean isPushed; // these will be of use when 
	private boolean isPopped; //  solve the maze.
	
	public MazeCell(int inRow, int inCol)
	{
		loc = new Place(inRow, inCol);
		status = SOLID;
		isStart = false;
		isEnd = false;
		isPushed = false;
		isPopped = false;
	}

	public int getStatus()
	{
		return status;
	}

	public boolean isPushed()
	{
		return isPushed;
	}

	public void setPushed(boolean isPushed)
	{
		this.isPushed = isPushed;
	}

	public boolean isPopped()
	{
		return isPopped;
	}

	public void setPopped(boolean isPopped)
	{
		this.isPopped = isPopped;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public boolean isStart()
	{
		return isStart;
	}

	public void setStart(boolean isStart)
	{
		this.isStart = isStart;
	}

	public boolean isEnd()
	{
		return isEnd;
	}

	public void setEnd(boolean isEnd)
	{
		this.isEnd = isEnd;
	}

	public Place getLoc()
	{
		return loc;
	}

	
	public void drawSelf(Graphics g)
	{
		if (status == SOLID)
			g.setColor(SOLID_COLOR);
		else
			g.setColor(HOLLOW_COLOR);
		g.fillRect(loc.column()*CELL_SIZE, loc.row()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
		g.setColor(OUTLINE_COLOR);
		g.drawRect(loc.column()*CELL_SIZE, loc.row()*CELL_SIZE, CELL_SIZE, CELL_SIZE);
		if (isStart)
		{
			g.setColor(START_COLOR);
			g.fillOval(loc.column()*CELL_SIZE+2, loc.row()*CELL_SIZE+2, CELL_SIZE-4, CELL_SIZE-4);
			
		}
		if (isEnd)
		{
			g.setColor(END_COLOR);
			g.fillOval(loc.column()*CELL_SIZE+2, loc.row()*CELL_SIZE+2, CELL_SIZE-4, CELL_SIZE-4);
			
		}
		
		if (isPopped)
		{
			g.setColor(POPPED_COLOR);
			g.drawLine(loc.column()*CELL_SIZE,           loc.row()*CELL_SIZE, 
					   loc.column()*CELL_SIZE+CELL_SIZE, loc.row()*CELL_SIZE+CELL_SIZE);
			g.drawLine(loc.column()*CELL_SIZE+CELL_SIZE, loc.row()*CELL_SIZE,
					   loc.column()*CELL_SIZE          , loc.row()*CELL_SIZE+CELL_SIZE);
		}
		else if (isPushed)
		{
			g.setColor(PUSHED_COLOR);
			g.drawLine(loc.column()*CELL_SIZE,           loc.row()*CELL_SIZE+CELL_SIZE/2, 
					   loc.column()*CELL_SIZE+CELL_SIZE, loc.row()*CELL_SIZE+CELL_SIZE/2);
			g.drawLine(loc.column()*CELL_SIZE+CELL_SIZE/2, loc.row()*CELL_SIZE,
					   loc.column()*CELL_SIZE+CELL_SIZE/2, loc.row()*CELL_SIZE+CELL_SIZE);
		}
		
	}
	
}
