package src;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

public class Instructions extends JPanel {

	/**
	 * Create the panel.
	 */
	public Instructions() {
		setForeground(Color.WHITE);
		setBackground(new Color(205, 133, 63));
		Icon backgroundicon = new ImageIcon(getClass().getResource("background4.png"));
		this.setBounds(0,0,backgroundicon.getIconWidth(), backgroundicon.getIconHeight() );
	//Este es el cambio
		
		JLabel background = new JLabel(backgroundicon);
	
		background.setBounds(0, 0, backgroundicon.getIconWidth(), backgroundicon.getIconHeight());
		setLayout(null);
		
		add(background);
		JTextPane textpaneInstructions = new JTextPane();
	
		textpaneInstructions.setBounds(210, 60, 690, 550);
		textpaneInstructions.setBackground(new Color( 0,0,0,0));
		textpaneInstructions.setFont(new Font("Lucida Blackletter", Font.PLAIN, 15));
		textpaneInstructions.setText("                                                                            Rules \n\n"
				+ "Checkers is played by two players. Each player begins the game with 12 colored pieces.\n"
				+ "The board consists of 64 squares, alternating between 32 dark and 32 light squares.\n"
				+ "Each player places his or her pieces on the 12 dark squares closest to him or her.\n"
				+ "Red moves first. Players then alternate moves.\n"
				+ "Moves are allowed only on the dark squares, so pieces always move diagonally. Simple pieces are always limited to forward moves (toward the opponent).\n"
				+ "A piece making a non-capturing move (not involving a jump) may move only one square.\n"
				+ "A piece making a capturing move (a jump) leaps over one of the opponent's pieces, landing in a straight diagonal line on the other side."
				+ "\nOnly one piece may be captured in a single jump; however,"
				+ " multiple jumps are allowed on a single turn.\n"
				+ "When a piece is captured, it is removed from the board.\n"
				+ "If more than one capture is available, the player is free to choose whichever he or she prefers.\n"
				+ "When a piece reaches the furthest row from the player who controls that piece, it is crowned and becomes a king.\n"
                + "Kings are limited to moving diagonally, but may move both forward and backward.\n"
				+ "Kings may combine jumps in several directions -- forward and backward -- on the same turn."
				+ " Single pieces may shift direction diagonally during a multiple capture turn, but must always jump forward (toward the opponent)\n"
				+ "A player wins the game when the opponent cannot make a move or if the opponent runs out of time."
				
				);
	
		
	
		add(textpaneInstructions);


	}


}
