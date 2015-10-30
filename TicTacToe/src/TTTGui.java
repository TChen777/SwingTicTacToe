import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

//Tony Chen
//Oct 28 2015
//GUI logic for the tic tac toe game

public class TTTGui {
	
	private TicTacToeGame Game;
	private Random rand;
	private char curLetter;				//Holds the current letter for the game (X or O)
	private int turns;
	private JFrame TTTprogram;
	private JPanel gameGrid;
	private JLabel status;
	private JPanel bottomPanel;
	
	//Constructor
	//Initialize game logic and then GUI elements
	public TTTGui() {
		
		turns = 0;
		Game = new TicTacToeGame();
		
		//Get starting letter
		rand = new Random();
		int  n = rand.nextInt(2);		//Returns 0 or 1
		if (n == 0)
			curLetter = 'X';
		else 
			curLetter = 'O';
		
		initGUI();
	}
	
	//Resets GUI elements and game logic
	private void resetGame() {
		
		Game.clearBoard();
		Component[] cells = gameGrid.getComponents();
		for (int i = 0; i < cells.length; i++) {
			
			if (cells[i] instanceof JButton) {
				
    		((JButton)cells[i]).setText("");
    		((JButton)cells[i]).setEnabled(true);
    		
    	}
    }
		
		//Obtain new starting player
		int  n = rand.nextInt(2);		//Returns 0 or 1
		if (n == 0)
			curLetter = 'X';
		else 
			curLetter = 'O';
		
		status.setForeground(Color.BLACK);
    status.setText("Player " + curLetter + " Turn ");
    turns = 0;
		
	}
	
	private void initGUI() {
		
		//Set the main window consisting of 3 main UI elements
		TTTprogram = new JFrame("Tic Tac Toe");
		TTTprogram.setSize(400, 400);
		TTTprogram.setLayout(new BorderLayout());
		TTTprogram.setLocationRelativeTo( null );
		TTTprogram.setResizable(false);
		TTTprogram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TTTprogram.setVisible(true);
		
		//Set the status msg
		status = new JLabel("Player " + curLetter + " Turn " , SwingConstants.CENTER);
		
		//Create the tic tac toe grid and init jbutton to each cell
		gameGrid = new JPanel();
		gameGrid.setLayout(new GridLayout(3,3));
		
	//Creates 9 buttons each with a actionlistener to handle the clicks
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				
				JButton button = new JButton();
				button.setName(i + " " + j);
				button.addActionListener(new ActionListener() {
					
					//Button updates game logic and UI elements, checks if a possible win is met
					@Override
					public void actionPerformed(ActionEvent e) {
						turns++;
						
						//Update the game board and button
						String[] coordinates = ((JButton)e.getSource()).getName().split(" ");
	          Game.insertLetter(curLetter, Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
	          ((JButton)e.getSource()).setText(curLetter + "");
	          ((JButton)e.getSource()).setFont(new Font("Serif", Font.BOLD, 40));
	          ((JButton)e.getSource()).setEnabled(false);
	                    
	          //Check if game is over, (little optimization, ignore checking for a win if less than 4 turns)
	          if (turns > 4 && Game.calcWin()) {
	          	
	          	Component[] cells = gameGrid.getComponents();
	          	for (int i = 0; i < cells.length; i++) {
	          		if (cells[i] instanceof JButton) {
	          			cells[i].setEnabled(false);
	              }
	           	}
	            status.setForeground(Color.RED);
	            status.setText("Player " + curLetter + " Wins ");
	            
	          }
	          else {
	            //Check if cells are full, if so its a tie, else Swap letters and change turn
	            if (turns == 9) {
	              
	              status.setForeground(Color.RED);
	              status.setText("It's a tie");
	              
	            }
	            else {
	              
  		          if (curLetter == 'O')
  		          	curLetter = 'X';
  		          else 
  		          	curLetter = 'O';
  		          
  		          status.setText("Player " + curLetter + " Turn ");
	            }
	          }
					}
					
				});
				
				gameGrid.add(button);
			}
		}
		
		//Reset game button init
		bottomPanel = new JPanel();
		
		JButton resetBtn = new JButton();
		resetBtn.setText("Reset Game");
		resetBtn.setSize(40, 20);
		resetBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				resetGame();
			}
		});
		
		bottomPanel.add(resetBtn);
		
		TTTprogram.add(status, BorderLayout.PAGE_START);		//Status msg
		TTTprogram.add(gameGrid, BorderLayout.CENTER);			//The 3x3 grid
		TTTprogram.add(bottomPanel, BorderLayout.PAGE_END);		//Reset button
	}
	
	//Main initializes the GUI components and logic is established as a result
	public static void main(String[] args) {
		TTTGui startGame = new TTTGui();
	}
	
}//TTTGui 
