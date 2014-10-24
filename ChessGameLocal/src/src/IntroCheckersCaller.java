package src;



import javax.swing.JFrame;




public class IntroCheckersCaller {
	
	private static JFrame frame = new JFrame();

	public static void main(String[] args) {
		int panelWidth=691, panelHeight=432;
		// TODO Auto-generated method stub

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
