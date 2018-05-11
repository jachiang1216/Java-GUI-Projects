package Practice;
/*Exposure to Graphical User Interfaces (GUI)
 * A very very random button clicking game with a counter. This gave me exposure to some of
 * Java awt/swing's capabilities.
 * Created by Jian An Chiang
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class ButtonClickerGame{

	public static void main(String[] args) {
		new GameGUI(); //Starts Game.
	}

}
class GameGUI extends JFrame{
	private 
	JFrame mainMenu;
	JPanel thePanel;
	JButton button;
	JLabel message;
	Font fontsize; //Sets the Font.
	int buttonClicked=0;
	int numberOfButtons;
	boolean waitOnButton=true;
	public
	GameGUI(){ //Constructor
		fontsize = new Font(Font.SANS_SERIF, Font.PLAIN, 100);
		numberOfButtons=(int) (Math.random()*20+1);
		mainMenu();
		win();
	}
	void win(){ //Win
		mainMenu.dispose();
		Toolkit tk = Toolkit.getDefaultToolkit();
		//Window Properties
		JFrame winWindow = new JFrame("Button Game");
		winWindow.setResizable(false);
		winWindow.setSize(1000,800); //1000 by 800 pixels window size
		winWindow.setLocationRelativeTo(null); //Centers window
		winWindow.setIconImage(tk.getImage("mainMenuIcon.png")); //
		winWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Deallocate Memory of Window.
		message = new JLabel("EZ Game. You did it.");
		fontsize = new Font(Font.SANS_SERIF, Font.PLAIN, 100);
		message.setFont(fontsize);
		thePanel = new JPanel();
        thePanel.setLayout(new BorderLayout());
		thePanel.add(message);
		winWindow.add(thePanel);			
		winWindow.setVisible(true); //Set the window to visible
	}
	void mainMenu(){
		Toolkit tk = Toolkit.getDefaultToolkit();
		//Window Properties
		
while ((buttonClicked<numberOfButtons+1) && (waitOnButton==true)){
	    waitOnButton=false;
		mainMenu = new JFrame("Button Game");
		mainMenu.setResizable(false);
		mainMenu.setSize(1000,800); //1000 by 800 pixels window size
		mainMenu.setLocationRelativeTo(null); //Centers window
		mainMenu.setIconImage(tk.getImage("mainMenuIcon.png")); //
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Deallocate Memory of Window.
		
		//Panel Properties
		
		thePanel = new JPanel();
        thePanel.setLayout(new BorderLayout());
        
		button = new JButton();
		if(buttonClicked==0){
		button.setText("Please Click Me");
		}else if (buttonClicked==1){
			button.setText("Click "+numberOfButtons+" Times");
		}else if (buttonClicked<numberOfButtons+1){
			fontsize = new Font(Font.SANS_SERIF, Font.PLAIN, 60);
			button.setText("Button Clicked: "+(buttonClicked-1)+" times");
		}
		button.setFont(fontsize);
		ListenForButton buttonListener = new ListenForButton(); //Allow button to listen for an action.
		button.addActionListener(buttonListener);
		
		
		//Add Components to the Panel
		thePanel.add(button); //Button will be at the bottom.
		
		//Add Panel to the Frame
		mainMenu.add(thePanel);			
		mainMenu.setVisible(true); //Set the window to visible
		
		while(waitOnButton == false){
		    try {
		       Thread.sleep(200);
		    } catch(InterruptedException e) {
		    }
		}
}
	}
	//Implement Listeners
	private class ListenForButton implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getSource()==button){
				buttonClicked++; 
				mainMenu.dispose();
		        //Label Properties
				waitOnButton=true;
			}
		}
	}
	
}

