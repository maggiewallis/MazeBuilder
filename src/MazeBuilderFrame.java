import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MazeBuilderFrame extends JFrame implements MazeConstants, ActionListener
{
	private MazeBuilderPanel mainPane;
	private JRadioButton startSelectorButton;
	private JRadioButton endSelectorButton;
	private JButton rebuildButton;
	private JButton solveButton;
	private JLabel status;
	
	public MazeBuilderFrame()
	{
		super("MazeBuilder");
		
		setSize(CELL_SIZE*NUM_COLS,CELL_SIZE*NUM_ROWS+100);
		getContentPane().setLayout(new BorderLayout());
		mainPane = new MazeBuilderPanel();
		getContentPane().add(mainPane, BorderLayout.CENTER);
		
		addBottomPanel();
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void addBottomPanel()
	{
		JPanel bottomArea = new JPanel();
		getContentPane().add(bottomArea, BorderLayout.SOUTH);
		startSelectorButton = new JRadioButton("Start");
		endSelectorButton = new JRadioButton("End");
		ButtonGroup bg = new ButtonGroup(); // a button group is what lets radio buttons be mutually exclusive. 
		bg.add(startSelectorButton);
		bg.add(endSelectorButton);
		
		Box radiosBox = Box.createVerticalBox(); // this verticalBox is what lets us stack the radios vertically.
		bottomArea.add(radiosBox);
		radiosBox.add(startSelectorButton);
		radiosBox.add(endSelectorButton);
		endSelectorButton.setSelected(true);
		
		rebuildButton = new JButton("Rebuild Maze");
		solveButton = new JButton("Solve Maze");
		
		Box buttonsBox = Box.createVerticalBox();  // this verticalBox is what lets us stack these buttons vertically.
		buttonsBox.add(rebuildButton);
		buttonsBox.add(solveButton);
		
		bottomArea.add(buttonsBox);
		
		status = new JLabel(""); // a place where text can go in the GUI - at first, this will be empty/invisible.
		bottomArea.add(status);
		
		
		startSelectorButton.addActionListener(this);
		endSelectorButton.addActionListener(this);
		rebuildButton.addActionListener(this);
		solveButton.addActionListener(this);
		
		mainPane.attachStatusLabel(status);  // tell the panel about the label, so it can change what it says.
	}
	
	public void actionPerformed(ActionEvent aEvt)
	{
		System.out.println("MBFrame --> actionPerformed");
		if (aEvt.getSource() == startSelectorButton)
			mainPane.setSelectionMode(START_MODE);
		if (aEvt.getSource() == endSelectorButton)
			mainPane.setSelectionMode(END_MODE);
		if (aEvt.getSource() == rebuildButton)
			mainPane.doRebuild();
		if (aEvt.getSource() == solveButton)
			mainPane.doSolve();
		repaint();
	
	}

}
