import java.awt.Color;

public interface MazeConstants
{
   public final int SOLID = 0;  // code numbers for cells' status 
   public final int HOLLOW = 1;

   public final int START_MODE = 0;  
   public final int END_MODE = 1;    
   
   public final int CELL_SIZE = 10;  // how many pixels wide and tall is each cell
   public final int NUM_ROWS = 15;   // how many cells in the grid
   public final int NUM_COLS = 25;
   
   public final Color SOLID_COLOR = Color.DARK_GRAY;           // appearance (colors)
   public final Color HOLLOW_COLOR = new Color(192,192,168);
   public final Color START_COLOR = Color.ORANGE;
   public final Color END_COLOR = Color.GREEN;
   public final Color OUTLINE_COLOR = Color.BLACK;
   public final Color POPPED_COLOR = Color.BLUE;
   public final Color PUSHED_COLOR = Color.YELLOW;
   
   public final int OPTIMAL_PATH_CIRCLE_RADIUS = 3;
}
