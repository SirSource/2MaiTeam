package src;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

public class Winner extends JPanel {

	/**
	 * Create the panel.
	 */
	public Winner(String winner) {
		setForeground(Color.WHITE);
		setBackground(new Color(205, 133, 63));
		Icon backgroundicon = new ImageIcon(getClass().getResource("background5.png"));
	
		this.setBounds(0,0,backgroundicon.getIconWidth(), backgroundicon.getIconHeight() );
		
	//Este es el cambio
		
		JLabel background = new JLabel(backgroundicon);
	
		background.setBounds(0, 0, backgroundicon.getIconWidth(), backgroundicon.getIconHeight());
		setLayout(null);
		
		add(background);
		JTextPane textpaneInstructions = new JTextPane();
	
		textpaneInstructions.setBounds(232, 152, 439, 239);
		textpaneInstructions.setBackground(new Color( 0,0,0,0));
		textpaneInstructions.setFont(new Font("Lucida Blackletter", Font.PLAIN, 30));
		textpaneInstructions.setText("The winner is the " + winner + " player!\n"
		+ "Congratulation "+ winner+ " player!");

		add(textpaneInstructions);


	}


}
