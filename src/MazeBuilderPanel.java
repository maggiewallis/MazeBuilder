import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MazeBuilderPanel extends JPanel implements MazeConstants, MouseListener, ActionListener
{
	private MazeCell[][] theGrid;
	private int selectionMode;
	private JLabel statusLabel;
	private Place startLoc;
	private Place endLoc;
	private Stack<Place> optimal;
	private Stack<Place> maze;
	private Stack<Place> mazeSolver;
	private Place currentLoc;
	private Place currentLocation;
	private List<Place> neighbors;
	private List<Place> usefulNeighbors;
	
	public MazeBuilderPanel()
	{
		super();
		theGrid = new MazeCell[NUM_ROWS][NUM_COLS];
		for (int r=0; r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++)
				theGrid[r][c] = new MazeCell(r,c);
		startLoc = new Place(1,1);
		theGrid[1][1].setStart(true);
		endLoc = new Place(NUM_ROWS-2,NUM_COLS-2);
		theGrid[NUM_ROWS-2][NUM_COLS-2].setEnd(true);
		addMouseListener(this);
		optimal = new Stack<Place>();
		selectionMode = END_MODE;
	}
	
	public void actionPerformed(ActionEvent aEvt)
	{
		System.out.println("action.");
		repaint();
	}
	
	
	
	public boolean inBounds(Place p)
	{
		boolean isInBounds = false;
		if (0 < p.row() && p.row() < NUM_ROWS -1 && 0 < p.column() && p.column() < NUM_COLS-1)
		{
			isInBounds = true; 
		}
		//---------------------------
		return isInBounds;
		
	}
	
	
	public MazeCell cellAt(Place p)
	{
		MazeCell mc = null;
	    mc = theGrid[p.row()][p.column()];
		//----------------------------
	    return mc;
	}
	
	
	public Place pickPlaceOffList(List<Place> choices)
	{
		Place chosenItem = null;

		if (choices.isEmpty() == true)
		{
			chosenItem = null;
		}
		else
		{
			int i = (int)(Math.random()*choices.size());
			chosenItem = choices.remove(i);
		}
		//-----------------------------
		return chosenItem;
	}
	
	
	public List<Place> getNeighborsOfState(Place p, int state)
	{
		ArrayList<Place> result = new ArrayList<Place>();
		
		if(cellAt(p.north()).getStatus() == state && inBounds(p.north()) == true)
		{
			result.add(p.north());
		}
		if(cellAt(p.south()).getStatus() == state && inBounds(p.south()) == true)
		{
			result.add(p.south());
		}
		if(cellAt(p.east()).getStatus() == state && inBounds(p.east()) == true)
		{
			result.add(p.east());
		}
		if(cellAt(p.west()).getStatus() == state && inBounds(p.west()) == true)
		{
			result.add(p.west());
		}
		//------------------------------------------------
		return result;
	}
	

	
	public void resetMazeToSolid()
	{
		for (int r=0; r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++)
				theGrid[r][c].setStatus(SOLID);
		resetSolveStates();
		repaint();
		setStatus("Maze refilled.");
		
		
	}
	
	
	public void resetSolveStates()
	{
		for (int r=0;r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++)
			{
				theGrid[r][c].setPopped(false);
				theGrid[r][c].setPushed(false);
			}
		optimal.clear();
		repaint();
	}
	
	
	
	
	public void doRebuild()
	{
		resetMazeToSolid();
		
		//make a stack, peek,pop,push
		maze = new Stack<Place>();
		maze.push(startLoc);
		
		while (maze.size() > 0)
		{
			currentLoc = maze.pop();
			if (getNeighborsOfState(currentLoc, HOLLOW).size() < 2)
			{
				cellAt(currentLoc).setStatus(HOLLOW);
				neighbors = getNeighborsOfState(currentLoc, SOLID);
				while (neighbors.size() > 0)
				{
					maze.push(pickPlaceOffList(neighbors));
				}
			}
		}
		

		
		
		//-------------------------------
		setStatus("Maze rebuilt.");
	}
	


	public void doSolve()
	{
		resetSolveStates();
		setStatus("Searching maze");
		mazeSolver = new Stack<Place>();
		optimal = new Stack<Place>();
		mazeSolver.push(startLoc);
		optimal.push(startLoc);
		cellAt(startLoc).setPushed(true);
		
		if (currentLocation != endLoc)
		{
			while (mazeSolver.isEmpty() == false)
			{
				currentLocation = mazeSolver.pop();
				while (optimal.isEmpty() == false && optimal.peek().isNeighbor(currentLocation) == false)
				{
					optimal.pop();
				}
				optimal.push(currentLocation);
				if (cellAt(currentLocation) == cellAt(endLoc))
				{
					break;
				}
				cellAt(currentLocation).setPopped(true);
				usefulNeighbors = getNeighborsOfState(currentLocation, HOLLOW);
				
				
				while (usefulNeighbors.isEmpty() == false)
				{
					Place position = pickPlaceOffList(usefulNeighbors);
					if (cellAt(position).isPushed() == false)
					{
						mazeSolver.push(position);
						cellAt(position).setPushed(true);
						neighbors.remove(position);
					}
					else
					{
						neighbors.remove(position);
					}		
				}
				
			}
			
		}
		
		
		//--------------------------------------
		setStatus("No Path found."); 
		return;
	}
	
	
	public void paintComponent(Graphics g)
	{
		System.out.println("painting.");
		for (int r=0; r<NUM_ROWS; r++)
			for (int c=0; c<NUM_COLS; c++)
			{
				theGrid[r][c].drawSelf(g);
				
				// ---------------- Only used in part D ---------------------
				// draws a red circle in all "optimal" cells.
				if (optimal.contains(new Place(r,c)))
				{
					g.setColor(Color.RED);
					g.drawOval(c*CELL_SIZE+CELL_SIZE/2-OPTIMAL_PATH_CIRCLE_RADIUS, 
							   r*CELL_SIZE+CELL_SIZE/2-OPTIMAL_PATH_CIRCLE_RADIUS, 
							   2*OPTIMAL_PATH_CIRCLE_RADIUS,
							   2*OPTIMAL_PATH_CIRCLE_RADIUS);
				}
				// ----------------------------------------------------------
			}
	}

	

	public void attachStatusLabel(JLabel lab)
	{
		statusLabel = lab;
	}
	
	public void setStatus(String stat)
	{
		statusLabel.setText(stat);
	}
	
	
	public int getSelectionMode()
	{
		return selectionMode;
	}
	public void setSelectionMode(int selectionMode)
	{
		System.out.println("MBP: setting selection mode: "+selectionMode);
		this.selectionMode = selectionMode;
	}
	
	// ---------------------------- used MouseListener methods -------------------------
	@Override
	public void mouseReleased(MouseEvent e)
	{
		System.out.println("dealing with mouse click in panel.");
		int r = e.getY()/CELL_SIZE;
		int c = e.getX()/CELL_SIZE;
		Place clickedPlace = new Place(r,c);
		System.out.println(clickedPlace);
		if (! inBounds(clickedPlace))
		{
			setStatus("Invalid location");
			return; // this isn't eligible
		}
		if (selectionMode==START_MODE)
		{
			if (startLoc.equals(clickedPlace))
				return; // no change.
			theGrid[startLoc.row()][startLoc.column()].setStart(false);
			theGrid[clickedPlace.row()][clickedPlace.column()].setStart(true);
			startLoc = clickedPlace;
			setStatus("Start moved: "+startLoc);
			resetSolveStates();
			repaint();
		}
		else if (selectionMode==END_MODE)
		{
			if (endLoc.equals(clickedPlace))
				return; // no change.
			theGrid[endLoc.row()][endLoc.column()].setEnd(false);
			theGrid[clickedPlace.row()][clickedPlace.column()].setEnd(true);
			endLoc = clickedPlace;
			setStatus("End moved: "+endLoc);
			resetSolveStates();
			repaint();
		}
	}
	
	// ------------------------------------  unused MouseListener methods.-----------------------------
	@Override
	public void mouseClicked(MouseEvent e)
	{	}
	@Override
	public void mousePressed(MouseEvent e)
	{	}
	@Override
	public void mouseEntered(MouseEvent e)
	{ }
	@Override
	public void mouseExited(MouseEvent e)
	{ }
	
	

}
