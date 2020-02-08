//Import necessary packages.
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//Author: Alex Damon
//Date: 12/16/19
//Project Name: Matrix Tic Tac Toe
//Summary: This project is a Java GUI application, made in Eclipse, that simulates a game of tic tac toe. 
//Neo (Player X) is the human player and Agent Smith (Player O) is the computer player. The game ends when either 
//player has won or there is a tie. The theme of the game, is the popular sci-fi action media franchise, 'The Matrix.' 
//All product names, logos, and brands are property of their respective owners. No copyright infringement intended.

//Main class
public class matrix_tictactoe {

	//Main method
	public static void main(String[] args) {

	//Posts event at end of Swings event list.
	EventQueue.invokeLater(ticTacToe::new);
	}
}

//Class inherits from JFrame class and implements ActionListener.
class ticTacToe extends JFrame implements ActionListener{
			
	//Initialize variables and components.
	private final int bSize = 100;
	private final int WINDOW_HEIGHT = 329;
	private final int WINDOW_WIDTH = 306;
	private ImageIcon neo = new ImageIcon(getClass().getClassLoader().getResource("neo.png"));
	private ImageIcon smith = new ImageIcon(getClass().getClassLoader().getResource("smith.png"));
	private int counter;
	private JButton[][] buttons = new JButton[3][3];
	private String player = "";
			
	//Constructor
	public ticTacToe(){
				
		//Set title
		setTitle("Matrix Tic Tac Toe");
				
		//Specify action for close button.
		setDefaultCloseOperation(EXIT_ON_CLOSE);
				
		//Setting width and height of window.
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
				
		//Prevent user from resizing window.
		setResizable(false);
				
		//Telling container there is no layout.
		setLayout(null);
				
		//Makes GUI component visible to user.
		setVisible(true);
				
		//Calling preset() method
		preset();	
	}
			
	//Sets up game and board for both players.
	private void preset(){
		//For loops signifying vertical and horizontal board spaces.
		for (int a = 0; a < 3; ++a){
			for (int b = 0; b < 3; ++b){
				//Assigning buttons to JButton.
				buttons[a][b] = new JButton();
				//Setting bounding rectangle and style elements.
				buttons[a][b].setBounds (a * bSize, b * bSize, bSize, bSize);
				buttons[a][b].setIcon(new ImageIcon(getClass().getClassLoader().getResource("matrix.jpg")));
				buttons[a][b].setBackground(Color.BLACK);
				buttons[a][b].setBorder(new LineBorder(Color.GREEN));
				buttons[a][b].setFocusPainted(false);
				buttons[a][b].setFont(new Font("Dialog", Font.PLAIN, 20));
				buttons[a][b].addActionListener(this);
				add(buttons[a][b]);
			}
		}
	}
			
	//Override method in sub class.
	@Override
			
	//Invoked when an action occurs.
	public void actionPerformed(ActionEvent e){
		//Handling player X and O.
		player = "X";
		//Returns object on which the event occurred.
		JButton button = (JButton) e.getSource();
		//As X button is clicked, the color changes.
		if(button.getText().equals("X"))
		button.setBackground(Color.BLACK);
		button.setBorder(new LineBorder(Color.GREEN));
		button.setFocusPainted(false);
		button.setForeground(Color.GREEN);
		button.setIcon(null);
		button.setOpaque(true);
		//If the button is empty...
		if(button.getText().equals(""))
			//set text of the box to X.
			button.setText("X");
		else
			return;
				
		//Increase amount by 1.
		++counter;
				
		//If winning conditions are met, game is over.
		if (winner()) {
			xGameOver();}
			
		//If all spaces are filled, and no winner, it is a tie and game is over.
		else if (counter == 9) {
			tieGameOver();}
				
		//If none of these, player O has won and game is over.
		else if (winner()) {
			oGameOver();}
				
		//Calls cpuTurn method.
			cpuTurn();
	}
			
	//Handling cpu (player O)
	private void cpuTurn(){
		player = "O";
		//Increase amount by 1.
		++counter;
		//Calling preTurn method
		preTurn();
				
		//If winning conditions are met, game is over.
		if (winner())
			oGameOver();
				
		//Determining spaces cpu (player O) will move. Random.
		while (true){
			int a = (int) (Math.random() * 3);
			int b = (int) (Math.random() * 3);
			//If buttons are empty...
			if (buttons[a][b].getText().equals(""))
			{
				//apply various button style elements.
				buttons[a][b].setText("O");
				buttons[a][b].setBackground(Color.BLACK);
				buttons[a][b].setFocusPainted(false);
				buttons[a][b].setForeground(Color.GREEN);
				buttons[a][b].setBorder(new LineBorder(Color.GREEN));
				buttons[a][b].setIcon(null);
				break;
			}
		}
	}
			
	//Establishing next moves for cpu (player O).
	private void preTurn(){
		//For loops for vertical and horizontal board spaces.
		for (int a = 0; a < 3; ++a) {
			for (int b = 0; b < 3; ++b){
				if (buttons[a][b].getText().equals("")){
					//Various button style elements.
					buttons[a][b].setText("O");
				//Set what happens to last O button.
				if(winner()){
					buttons[a][b].setBackground(Color.BLACK);
					buttons[a][b].setFocusPainted(false);
					buttons[a][b].setForeground(Color.GREEN);
					buttons[a][b].setBorder(new LineBorder(Color.GREEN));
					buttons[a][b].setIcon(null);
					return;}
					else
					//Otherwise button remains empty.
					buttons[a][b].setText("");
				}
			}
		}
	}
			
	//Checks to see who wins.
	private boolean winner(){
		//Calculations for determining the winner and all possible ways a player can win.
		for (int a = 0; a < 3; ++a){
		if((buttons[a][0].getText().equals("X") && buttons[a][1].getText().equals("X") && buttons[a][2].getText().equals("X")	
			|| (buttons[a][0].getText().equals("O") && buttons[a][1].getText().equals("O") && buttons[a][2].getText().equals("O"))))
			return true;
		}
		for (int a = 0; a < 3; ++a){
		if((buttons[0][a].getText().equals("X") && buttons[1][a].getText().equals("X") && buttons[2][a].getText().equals("X")	
				|| (buttons[0][a].getText().equals("O") && buttons[1][a].getText().equals("O") && buttons[2][a].getText().equals("O"))))
				return true;
				
		else if ((buttons[0][0].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[2][2].getText().equals("X")
			  || (buttons[0][0].getText().equals("O") && buttons[1][1].getText().equals("O") && buttons[2][2].getText().equals("O"))))
			  return true;
			
		else if ((buttons[2][0].getText().equals("X") && buttons[1][1].getText().equals("X") && buttons[0][2].getText().equals("X")
				  || (buttons[2][0].getText().equals("O") && buttons[1][1].getText().equals("O") && buttons[0][2].getText().equals("O"))))
				  return true;
		}	
			return false;
	}
	//Displays if player X has won.
	private void xGameOver(){
		JOptionPane.showMessageDialog(null, "“My name … is Neo!”", "Player X Wins", JOptionPane.INFORMATION_MESSAGE, neo);
		System.exit(0);
	}
	//Displays if player O has won.
	private void oGameOver(){
		JOptionPane.showMessageDialog(null, "“Good bye, Mr Anderson.”", "Player O Wins", JOptionPane.INFORMATION_MESSAGE, smith);
		System.exit(0);
	}
	//Displays if both players have tied.
	private void tieGameOver(){
		JOptionPane.showMessageDialog(null, "“Good bye, Mr Anderson.”", "Both Players Tie", JOptionPane.INFORMATION_MESSAGE, smith);
		System.exit(0);
	}
}
