package src;



import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;




public class IntroCheckersCaller {

	private static JFrame frame = new JFrame(); // New JFrame
/**
 * Main method Opens the frame and GUI.
 * @param args
 */
	public static void main(String[] args) {
		int panelWidth=691, panelHeight=432;
		// TODO Auto-generated method stub
		frame.setSize(panelWidth, panelHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new IntroCheckers());
		frame.setVisible(true);
		
	}
/**
 * Disposes the first Frame which is the intro.
 */
	public void disposeIntro(){
		frame.dispose();
	}


}
