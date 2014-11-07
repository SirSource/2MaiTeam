package src;



import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;




public class IntroCheckersCaller {

	private static JFrame frame = new JFrame();

	public static void main(String[] args) {
		int panelWidth=691, panelHeight=432;
		// TODO Auto-generated method stub

		
		try {
			new Client();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	
		
		
		
		
		frame.setSize(panelWidth, panelHeight);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new IntroCheckers());
		frame.setVisible(true);
		
		
		
		
		
	}

	public void disposeIntro(){
		frame.dispose();
	}


}
