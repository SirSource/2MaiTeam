package src;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import java.awt.event.ActionEvent;

import sun.audio.*;

import java.io.*;
//hello my friend
public class IntroCheckers extends JPanel implements ActionListener {
	private JTextField textName;
	private AudioPlayer bjMusic;
	Icon musiconicon = new ImageIcon(getClass().getResource("sound.png"));
	Icon musicofficon = new ImageIcon(getClass().getResource("speaker.png"));
	JButton btnMusic = new JButton(musicofficon);


	/**
	 * Create the panel.
	 */
	public IntroCheckers() {
		bjMusic =  new AudioPlayer ("Ocarina song of time.mp3");
		bjMusic.play();

		setBackground(new Color(153, 102, 0));
		setForeground(new Color(139, 69, 19));

		//icon images 
		Icon backgroundicon = new ImageIcon(getClass().getResource("background2.png"));
		Icon iconchess = new ImageIcon(getClass().getResource("iconchess.png")); 
		Icon iconok = new ImageIcon(getClass().getResource("ok2.png")); 
		Icon iconquestion = new ImageIcon(getClass().getResource("questionshield.png"));


		; 
		setPreferredSize(new Dimension(backgroundicon.getIconWidth(), backgroundicon.getIconHeight()));
		JLayeredPane layeredPane = new JLayeredPane();  //in case you get ideas, yeas you need the pane 
		layeredPane.setBorder(null);
		add(layeredPane);
		layeredPane.setPreferredSize(new Dimension(backgroundicon.getIconWidth(), backgroundicon.getIconHeight()));



		JLabel checkersiconlabel = new JLabel(iconchess);
		checkersiconlabel.setBounds(191, 49, iconchess.getIconWidth(), iconchess.getIconHeight());
		layeredPane.add(checkersiconlabel, new Integer(300));

		JLabel checkersLabel = new JLabel("Checkers");
		checkersLabel.setFont(new Font("Lithos Pro", Font.BOLD | Font.ITALIC, 32));
		checkersLabel.setBounds(270, 56, 175, 40);
		layeredPane.add(checkersLabel, new Integer(300));

		JButton buttonok = new JButton("I am ready!", iconok);
		buttonok.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 16));
		buttonok.setBounds(270, 324, 157, 40);
		buttonok.addActionListener(this);
		layeredPane.add(buttonok);

		JLabel labelAreYouReady = new JLabel("Are you ready?");
		labelAreYouReady.setFont(new Font("Lucida Blackletter", Font.ITALIC, 18));
		labelAreYouReady.setBounds(280, 294, 134, 29);
		layeredPane.add(labelAreYouReady);

		JButton buttonInstructions = new JButton("Instructions", iconquestion);
		buttonInstructions.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 15));
		buttonInstructions.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				int panelWidth=1075, panelHeight=672;

				JFrame instruction = new JFrame();
				instruction.setSize(panelWidth, panelHeight);
				instruction.setResizable(false);
				instruction.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				instruction.getContentPane().add(new Instructions());
				instruction.setVisible(true);
			}
		}

				);
		buttonInstructions.setBounds(360, 229, 147, 40);
		layeredPane.add(buttonInstructions);

		JLabel labelWelcome = new JLabel("Welcome!");
		labelWelcome.setFont(new Font("Lithos Pro", Font.PLAIN, 16));
		labelWelcome.setBounds(304, 102, 89, 16);
		layeredPane.add(labelWelcome);

		textName = new JTextField();
		textName.setText("NAME");
		textName.setBounds(283, 174, 134, 28);
		layeredPane.add(textName);
		textName.setColumns(10);

		JLabel labelName = new JLabel("Name?");
		labelName.setFont(new Font("Lithos Pro", Font.PLAIN, 16));
		labelName.setBounds(315, 142, 72, 20);
		layeredPane.add(labelName);


		btnMusic.setBounds(227, 239, 58, 29);


		btnMusic.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				if (AudioPlayer.clip.isRunning()){
					btnMusic.setIcon(musiconicon);
					bjMusic.stop();
				}
				else {
					btnMusic.setIcon(musicofficon);
					bjMusic.play();
				}
			}
		});

		layeredPane.add(btnMusic);


		JLabel background = new JLabel(backgroundicon);
		background.setBounds(0, 0, backgroundicon.getIconWidth(),backgroundicon.getIconHeight());
		layeredPane.add(background);



	}

	public void actionPerformed(ActionEvent e) 
	{
		int panelWidth=1075, panelHeight=700;

		JFrame chessboard = new JFrame();
		bjMusic.stop(); 
		chessboard.setSize(panelWidth, panelHeight);
		chessboard.setResizable(false);
		chessboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chessboard.getContentPane().add(new CheckersBoard());
		chessboard.setVisible(true);
		new IntroCheckersCaller().disposeIntro();
	}
}
