/* Create your own background screen saver with this program!
 * Created by Jian An Chiang
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//------------------------------------MAIN METHOD----------------------------------------//
public class Background_Creator{
	public static void main(String[] args){
		mainLayout start = new mainLayout(); //Sets the main layout for the Program.
	}
}
//----------------------------------MAIN LAYOUT CLASS------------------------------------//
class mainLayout extends JFrame{
	protected 
	Toolkit tk;
	JFrame window,tempWindow;
	aboutUs aboutUs_obj;
	mainMenu mainMenu_obj;
	musicMenu musicMenu_obj;
	backgroundEdit background_obj;
	shapeAnimation shapeAnimation_obj;
	GridBagConstraints gbc = new GridBagConstraints();
	public
	mainLayout(){ //Constructor
		tempWindow = new JFrame();
		window=windowDesign(window);
		mainMenu_obj=new mainMenu();
	}
	JFrame windowDesign(JFrame win){
		tk = Toolkit.getDefaultToolkit();
		win=new JFrame("Background Creator Tool");
		win.setSize(1200,800);
		win.setResizable(false);
		win.setLocationRelativeTo(null);
		win.setIconImage(tk.getImage("Paintbrush.png"));
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		win.getContentPane().setBackground(Color.GRAY);
		return win;
	}
	void buttonDesign(JButton button){ //Button Design
		button.setBackground(Color.BLACK);
		button.setForeground(Color.WHITE);
		button.setBorderPainted(false);
		button.setFocusPainted(false);
		button.setPreferredSize(new Dimension(250,40));
	}
	
	//-------------------------------------MAIN MENU CLASS------------------------------------//
	class mainMenu{
		private
		JPanel Info,Options;
		JLabel title,author;
		JButton begin,music,credit,exit;
		boolean initialize=false;
		public
		mainMenu(){ //Constructor
			if (initialize==true){//Do not recreate if created already
			}else{
				begin=new JButton("<html><font size=5>Get Started</font></html>");
				music=new JButton("<html><font size=5>Select Music</font></html>");
				credit=new JButton("<html><font size=5>About Tool</font></html>");
				exit=new JButton("<html><font size=5>Exit</font></html>");
				Info=new JPanel(new GridLayout(0,1));
				Info.setBackground(Color.GRAY);
				Options=new JPanel(new GridBagLayout());
				Options.setBackground(Color.GRAY);
				
				title=new JLabel("<html><br></br><br></br>Background Creator Tool</html>",SwingConstants.CENTER);
				author=new JLabel("<html>Create your own custom screensaver</html>",SwingConstants.CENTER);
				title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
				author.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
				Info.add(title);
				Info.add(author);
				
				//Begin Button
				buttonDesign(begin);
				gbc.insets = new Insets(10,0,10,0); //Top,Left,Down,Right
				gbc.gridx=0;
				gbc.gridy=0;
				Options.add(begin,gbc);
				
				//Select Music Button
				buttonDesign(music);
				gbc.gridy=1;
				Options.add(music,gbc);
				
				//About Me Button
				buttonDesign(credit);
				gbc.gridy=2;
				Options.add(credit,gbc);
				
				//Exit Button
				buttonDesign(exit);
				gbc.gridy=3;
				Options.add(exit,gbc);
				
				//Add JPanels to mainPanel.
				window.add(Info,BorderLayout.NORTH);
				window.add(Options);
				window.setVisible(true);
				
				//Add Actions to Buttons
				ListenForButton buttonListener = new ListenForButton(); 
				begin.addActionListener(buttonListener);
				music.addActionListener(buttonListener);
				credit.addActionListener(buttonListener);
				exit.addActionListener(buttonListener);
				
				initialize=true;
			}
		}
		void turnVisible(){
			Info.setVisible(true);
			Options.setVisible(true);
		}
		void turnOffVisible(){
			Info.setVisible(false);
			Options.setVisible(false);
		}
		
		//----------------------------LISTENFORBUTTON MAIN MENU CLASS----------------------------//
		private class ListenForButton implements ActionListener{
			boolean about_on=false;
			boolean music_on=false;
			boolean background_on=false;
			public void actionPerformed(ActionEvent e){
				if(e.getSource()==begin){ //Get Started.
					turnOffVisible();
					if (background_on==true){ 
						background_obj.turnVisible();
					}else{
						background_obj=new backgroundEdit();
						background_on=true;
					}
				}else if(e.getSource()==music){ //Select Music
					turnOffVisible();
					if (music_on==true){
						musicMenu_obj.turnVisible();
					}else{
						musicMenu_obj=new musicMenu();
						music_on=true;
					}
				}else if(e.getSource()==credit){ //About Us
					turnOffVisible();
					if (about_on==true){ 
						aboutUs_obj.turnVisible();
					}else{
						aboutUs_obj=new aboutUs();
						about_on=true;
					}
				}else{ //Exit
					window.dispose();
				}
			}
		}
	}
	//--------------------------------BACKGROUND EDIT CLASS---------------------------------//	
	class backgroundEdit{
		private
		JPanel EditPanel,PreviewPanel,BottomPanel;
		JLabel screenTitle,colorHeader,shapeHeader,shapeColorHeader,markHeader,watermark;
		JButton goBack,apply;
		JCheckBox shapeToggle,markToggle;
		JList colorList;
		JScrollPane colorScroller;
		JComboBox shapeList,shapeColorList;
		String[] colorname = {"No Colour","Black","White","Blue","Green","Magenta","Red"};
		Color[] colors = {Color.GRAY,Color.BLACK,Color.WHITE,Color.BLUE,Color.GREEN,Color.MAGENTA,Color.RED};
		String[] shapename = {"Select Shape","Circle","Square","Rectangle"};
		String[] shapecolor = {"Black","White","Blue","Green","Magenta","Red"};
		int shapeindex=0;
		int colorindex=0;
		JTextField markField;
		boolean initialize=false;
		boolean shapeon=false;
		backgroundEdit(){
			if (initialize==true) {
			}else{
				EditPanel = new JPanel(new GridBagLayout());
				EditPanel.setBackground(Color.GRAY);
				PreviewPanel = new JPanel();
				PreviewPanel.setBackground(Color.GRAY);
				BottomPanel = new JPanel();
				BottomPanel.setBackground(Color.GRAY);
				
				screenTitle=new JLabel("<html>Background Editing Area</html>",SwingConstants.CENTER);
				screenTitle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
				
				//Apply and Confirm Button
				apply=new JButton("<html><font size=5>Apply and Confirm</font></html>");
				buttonDesign(apply);
				
				//Go Back Button
				goBack=new JButton("<html><font size=5>Go Back</font></html>");
				buttonDesign(goBack);
				
				//Color List Configuration
				colorHeader = new JLabel("Select Color");
				colorHeader.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
				colorList=new JList();
				colorList.setVisibleRowCount(4);
				colorList.setFont(new Font("Arial",Font.BOLD,19));
				colorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				colorList.setListData(colorname);
		    	colorList.setSelectedIndex(0);
				colorScroller=new JScrollPane(colorList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
													JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				colorScroller.setPreferredSize(new Dimension(150,100));
				
				//Shape Configuration
				shapeToggle = new JCheckBox();
				shapeToggle.setBackground(Color.GRAY);
				shapeToggle.setText("Toggle Shape Animation");
				shapeToggle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
				shapeToggle.setFocusPainted(false);
				shapeHeader = new JLabel("Select Shape Type");
				shapeHeader.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
				shapeHeader.setVisible(false);
				shapeList=new JComboBox(shapename);
				shapeList.setSelectedIndex(0);
				shapeList.setPreferredSize(new Dimension(200,30));
				shapeList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
				shapeList.setVisible(false);
				shapeColorHeader = new JLabel("Select Shape Color");
				shapeColorHeader.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
				shapeColorHeader.setVisible(false);
				shapeColorList=new JComboBox(shapecolor);
				shapeColorList.setSelectedIndex(0);
				shapeColorList.setPreferredSize(new Dimension(200,30));
				shapeColorList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
				shapeColorList.setVisible(false);
				
				//Name Configuration
				markToggle = new JCheckBox();
				markToggle.setBackground(Color.GRAY);
				markToggle.setText("Toggle WaterMark Option");
				markToggle.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
				markToggle.setFocusPainted(false);
				markHeader = new JLabel("Type your WaterMark");
				markHeader.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
				markHeader.setVisible(false);
				markField = new JTextField();
				markField.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
				markField.setPreferredSize(new Dimension(200,30));
				markField.setVisible(false);
				
				//WaterMark
				watermark = new JLabel("");
				watermark.setFont(new Font(Font.MONOSPACED, Font.BOLD, 80));
				
				//Add Actions to Buttons
				ListenForButton buttonListener = new ListenForButton(); //Allow button to listen for an action.
				goBack.addActionListener(buttonListener);
				apply.addActionListener(buttonListener);
				shapeToggle.addActionListener(buttonListener);
				markToggle.addActionListener(buttonListener);
				ListenForList ListListener = new ListenForList();
				colorList.addListSelectionListener(ListListener);
				ListenForShape ItemListener = new ListenForShape();
				shapeList.addItemListener(ItemListener);
				ListenForColor ItemListener2 = new ListenForColor();
				shapeColorList.addItemListener(ItemListener2);
				ListenForKey KeyListener = new ListenForKey();
				markField.addKeyListener(KeyListener);
				
				//Title
				gbc.gridx=0;
				gbc.gridy=0;
				gbc.gridwidth=4;
				EditPanel.add(screenTitle,gbc);
				
				//Background Color
				gbc.insets = new Insets(30,0,0,0);
				gbc.gridy=1;
				gbc.gridwidth=1;
				EditPanel.add(colorHeader,gbc);
				
				gbc.insets = new Insets(20,20,20,20);
				gbc.gridy=2;
				gbc.gridheight=2;
				EditPanel.add(colorScroller,gbc);
				
				//Shape Animation
				gbc.insets = new Insets(30,30,0,0);
				gbc.gridx=1;
				gbc.gridy=1;
				gbc.gridwidth=2;
				gbc.gridheight=1;
				EditPanel.add(shapeToggle,gbc);
				
				gbc.insets = new Insets(40,30,0,0);
				gbc.gridy=2;
				gbc.gridwidth=1;
				EditPanel.add(shapeHeader,gbc);
			
				gbc.insets = new Insets(0,30,0,0);
				gbc.gridy=3;
				EditPanel.add(shapeList,gbc);
				
				gbc.insets = new Insets(40,30,0,0);
				gbc.gridx=2;
				gbc.gridy=2;
				EditPanel.add(shapeColorHeader,gbc);
				
				gbc.insets = new Insets(0,30,0,0);
				gbc.gridy=3;
				EditPanel.add(shapeColorList,gbc);
				
				//WaterMark
				gbc.insets = new Insets(30,30,0,0);
				gbc.gridx=3;
				gbc.gridy=1;
				EditPanel.add(markToggle,gbc);
				
				gbc.insets = new Insets(40,30,0,0);
				gbc.gridy=2;
				EditPanel.add(markHeader,gbc);
				
				gbc.insets = new Insets(0,30,0,0);
				gbc.gridy=3;
				EditPanel.add(markField,gbc);
				
				PreviewPanel.add(watermark);
				BottomPanel.add(goBack,gbc);
				BottomPanel.add(apply,gbc);
				window.add(EditPanel,BorderLayout.NORTH);
				window.add(PreviewPanel,BorderLayout.CENTER);
				window.add(BottomPanel,BorderLayout.SOUTH);
				
				initialize=true;
			}
		}
		void turnVisible(){
			EditPanel.setVisible(true);
			PreviewPanel.setVisible(true);
			BottomPanel.setVisible(true);
		}
		void turnOffVisible(){
			EditPanel.setVisible(false);
			PreviewPanel.setVisible(false);
			BottomPanel.setVisible(false);
		}
		private class ListenForButton implements ActionListener{
			public void actionPerformed(ActionEvent c) {
				if (c.getSource()==goBack){
					try {	
						shapeAnimation_obj.setVisible(false);
					}catch (NullPointerException e1){e1.getMessage();}
					turnOffVisible();
					mainMenu_obj.turnVisible();
				}else if (c.getSource()==shapeToggle){
					if (shapeToggle.isSelected()==true){
						shapeHeader.setVisible(true);
						shapeList.setVisible(true);
						shapeColorHeader.setVisible(true);
						shapeColorList.setVisible(true);
					}else{
						try {	
							shapeAnimation_obj.setVisible(false);
						}catch (NullPointerException e1){e1.getMessage();}
						shapeHeader.setVisible(false);
						shapeList.setVisible(false);
						shapeColorHeader.setVisible(false);
						shapeColorList.setVisible(false);
					}
				}else if (c.getSource()==markToggle){
					if (markToggle.isSelected()==true){
						markHeader.setVisible(true);
						markField.setVisible(true);
					}else{
						markHeader.setVisible(false);
						markField.setVisible(false);
					}
				}else if (c.getSource()==apply){
					EditPanel.setVisible(false);
					BottomPanel.setVisible(false);
					try{
						if (shapeAnimation_obj.isVisible()==true){
							PreviewPanel.setVisible(false);
							window.remove(shapeAnimation_obj);
							
							Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
							shapeAnimation_obj = new shapeAnimation(shapename[shapeindex],shapecolor[colorindex],screenSize.getWidth(),screenSize.getHeight());
							shapeAnimation_obj.add(watermark);
							window.add(shapeAnimation_obj);
							shapeAnimation_obj.setVisible(false);
							shapeAnimation_obj.setBackground(colors[colorList.getSelectedIndex()]);
							shapeAnimation_obj.setVisible(true);
						}
					}catch(NullPointerException e){e.getMessage();}
					windowDesign(tempWindow);
					tempWindow.setUndecorated(true);
					tempWindow.setAlwaysOnTop(true);
					int xsize=(int) tk.getScreenSize().getWidth();
					int ysize=(int) tk.getScreenSize().getHeight();
					tempWindow.setSize(xsize,ysize);
					tempWindow.setLocationRelativeTo(null);
					tempWindow.add(shapeAnimation_obj);
					ListenForKey KeyListener = new ListenForKey();
					tempWindow.addKeyListener(KeyListener);
					tempWindow.setVisible(true);
				}
			}
		}
		private class ListenForList implements ListSelectionListener{ //Background Color
			public void valueChanged(ListSelectionEvent e) {
				PreviewPanel.setBackground(colors[colorList.getSelectedIndex()]);
				try {	
					shapeAnimation_obj.setBackground(PreviewPanel.getBackground());
				}catch (NullPointerException e1){e1.getMessage();}
			}
		}
		private class ListenForShape implements ItemListener{ //Shape Type
			public void itemStateChanged(ItemEvent e){ 
				if (e.getStateChange()==ItemEvent.SELECTED){
					if (shapeList.getSelectedIndex()==0){
						shapeAnimation_obj.setVisible(false);
					}else {
						shapeindex=shapeList.getSelectedIndex();
						if (shapeon==true) {
							window.remove(shapeAnimation_obj);
						}
						shapeAnimation_obj = new shapeAnimation(shapename[shapeindex],shapecolor[colorindex],PreviewPanel.getWidth(),PreviewPanel.getHeight());
						shapeAnimation_obj.add(watermark);
						window.add(shapeAnimation_obj);
						shapeAnimation_obj.setVisible(false);
						shapeAnimation_obj.setBackground(colors[colorList.getSelectedIndex()]);
						shapeAnimation_obj.setVisible(true);
						shapeon=true;
					}
				}
			}	
		}
		private class ListenForColor implements ItemListener{ //Shape Color
			public void itemStateChanged(ItemEvent e){ 
				if (e.getStateChange()==ItemEvent.SELECTED){
					colorindex=shapeColorList.getSelectedIndex();
					if (shapeon==true) {
						window.remove(shapeAnimation_obj);
					}
					shapeAnimation_obj = new shapeAnimation(shapename[shapeindex],shapecolor[colorindex],PreviewPanel.getWidth(),PreviewPanel.getHeight());
					shapeAnimation_obj.add(watermark);
					window.add(shapeAnimation_obj);
					shapeAnimation_obj.setVisible(false);
					shapeAnimation_obj.setBackground(colors[colorList.getSelectedIndex()]);
					shapeAnimation_obj.setVisible(true);
					shapeon=true;
				}
			}
		}
		private class ListenForKey implements KeyListener{ //WaterMark
			public void keyTyped(KeyEvent e){}
			public void keyPressed(KeyEvent e){
				if (e.getSource()==markField){
					if (e.getKeyCode()==16 || e.getKeyCode()==20 || e.getKeyCode()==127){ //Shift, Caps Lock, Delete 
					}else if (e.getKeyCode()==8){
						try{
							watermark.setText(watermark.getText().substring(0,watermark.getText().length()-1));
						}catch (StringIndexOutOfBoundsException e2){e2.getMessage();}
					}else{
						watermark.setText(watermark.getText()+e.getKeyChar());
						try {	
							shapeAnimation_obj.add(watermark);
							window.add(shapeAnimation_obj);
						}catch (NullPointerException e1){e1.getMessage();}
					}
				}
				if (e.getSource()==tempWindow){
					tempWindow.remove(shapeAnimation_obj);
					shapeAnimation_obj = new shapeAnimation(shapename[shapeindex],shapecolor[colorindex],PreviewPanel.getWidth(),PreviewPanel.getHeight());
					shapeAnimation_obj.setBackground(colors[colorList.getSelectedIndex()]);
					window.add(shapeAnimation_obj);
					turnVisible();
					tempWindow.dispose();
				}
			}
			public void keyReleased(KeyEvent e){}
				
		}
	}	
	
	class shapeAnimation extends JPanel implements ActionListener{
		String shape,color;
		Timer time = new Timer(5, this); //GUI version of Sleep Method
		int x=0,y=0; //Start at Edge of Window
        int velX=2,velY=2;
        double width,height;
        double wall=40;
		shapeAnimation(String x,String y,double w,double h){
			shape=x;
			color=y;
			width=w;
			height=h;
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			switch (color) {
			case "Black": g.setColor(Color.BLACK); break;
			case "White": g.setColor(Color.WHITE); break;
			case "Blue": g.setColor(Color.BLUE); break;
			case "Green": g.setColor(Color.GREEN); break;
			case "Magenta": g.setColor(Color.MAGENTA); break;
			case "Red": g.setColor(Color.RED); break;
				}
			switch (shape){
			case "Circle": g.fillOval(x,y,80,80); break; 
			case "Square": g.fillRect(x,y,80,80); break;
			case "Rectangle": g.fillRect(x,y,160,80); break;
			}
			time.start();
		}
		
		public void actionPerformed(ActionEvent e) {
			if (shape=="Rectangle") {
				wall=160;
			}else {wall=80;}
			if (x<0 || x>width-wall){
				velX=-velX;
			}
			if (y<0 || y>height-80){ 
				velY=-velY;
			}
			x+=velX;
			y+=velY;
			repaint();

		}
	}
//----------------------------------------ABOUT US CLASS-----------------------------------//
	class aboutUs{
		private
		JPanel aboutPanel;
		JButton goBack;
		JLabel details,creator;
		boolean initializer=false;
		public
		aboutUs(){
			if (initializer==true){
			}else{
				aboutPanel=new JPanel(new GridBagLayout());
				aboutPanel.setBackground(Color.GRAY);
				
				details = new JLabel("<html><h1 style='font-family:Courier New;'><font size=7>Background Creator Tool:</font></h1><br></br><br></br><br></br>"
						+ "<font size=6>Functional Specifications:</font><br></br><br></br>"
						+ "<font size=5>-Create a custom background with tools provided. <br></br>"
						+ "-Background music feature add-on</font><br></br><br></br>"
						+ "<font size=6>Selected Tools include the following:</font><br></br><br></br> "
						+ "<font size=5>-Background Colour<br></br>"
						+ "-Shape Type<br></br>"
						+ "-Toggle Shape Animation<br></br>"
						+ "-Choice adding your name on the background<br></br>"
						+ "-Full Screen Capabilities</font></html>",SwingConstants.CENTER);
				details.setBackground(Color.BLACK);
				details.setForeground(Color.WHITE);
				details.setPreferredSize(new Dimension(800,500));
				details.setOpaque(true);
				
				goBack=new JButton("<html><font size=5>Go Back</font></html>");
				buttonDesign(goBack);
				
				//Add Actions to Buttons
				ListenForButton buttonListener = new ListenForButton();
				goBack.addActionListener(buttonListener);
				
				creator=new JLabel("<html><h1 style='font-family:Courier New;'><font size=4>Developed by Jian An Chiang</font></h1></html>");
				gbc.gridx=1;
				gbc.gridy=0;
				aboutPanel.add(details,gbc);
				
				gbc.gridy=1;
				aboutPanel.add(creator,gbc);
				
				gbc.insets = new Insets(80,0,0,0);
				gbc.gridy=2;
				aboutPanel.add(goBack,gbc);
				window.add(aboutPanel);
				
				initializer=true;
			}
		}
		void turnVisible(){
			aboutPanel.setVisible(true);
		}
		void turnOffVisible(){
			aboutPanel.setVisible(false);
		}
		private class ListenForButton implements ActionListener{
			public void actionPerformed(ActionEvent c) {
				if (c.getSource()==goBack){
					turnOffVisible();
					mainMenu_obj.turnVisible();
				}
			}
		}
	}
//------------------------------------MUSIC MENU CLASS-----------------------------------//
	class musicMenu{
		private
		JPanel musicPanel;
		JLabel SelectGenre,SelectSong,LoopStatement;
		JButton playIt,goBack,stopIt;
		JRadioButton loopIt;
		boolean initializer=false;
		JComboBox genreList;
		JList songList;
		JScrollPane songScroller;
		String[] genres = {"Select Genre","Hip Hop","Rock","Korean Pop","Orchestra","Game"};
		String[] hipHopSongs = {"God's Plan","Litty","Look Alive","No Heart","Savage Mode","Wavy"};
		String[] rockSongs = {"New Morning","Like a Rolling Stone","Purple Haze","If Not For You","Surrender"};
		String[] kPopSongs = {"DNA","Lady","Likey","Love Song","Me Like Yuh","Something Special"};
		String[] orchestralSongs = {"Hornpipe"};
		String[] gameSongs = {"Fortnite","Silver Crown","Urf"};
		String[][] genreindex = {hipHopSongs,rockSongs,kPopSongs,orchestralSongs,gameSongs};
		public
		musicMenu(){
			if (initializer==true){
			}else{
				musicPanel=new JPanel(new GridBagLayout());
				musicPanel.setBackground(Color.GRAY);
				musicPanel.setPreferredSize(new Dimension(800,500));
				
				SelectGenre=new JLabel("Select a Genre");
				SelectGenre.setFont(new Font(Font.MONOSPACED,Font.BOLD,65));
				SelectSong=new JLabel("Select a Song");
				SelectSong.setFont(new Font(Font.MONOSPACED,Font.BOLD,65));
				SelectSong.setVisible(false);
				LoopStatement=new JLabel("Repeat On");
				LoopStatement.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
				LoopStatement.setVisible(false);
				
				//List holding songs
				songList=new JList();
				songList.setVisibleRowCount(5);
				songList.setFont(new Font("Arial",Font.BOLD,25));
				songList.setVisible(false);
				songList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				songScroller=new JScrollPane(songList,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
													JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				songScroller.setPreferredSize(new Dimension(400,160));
				songScroller.setVisible(false);
				
				//Buttons
				playIt=new JButton("<html><font size=5>Play Song</font></html>");
				buttonDesign(playIt);
				playIt.setVisible(false);
				
				goBack=new JButton("<html><font size=5>Go Back</font></html>");
				buttonDesign(goBack);
				
				stopIt=new JButton("<html><font size=5>Stop Music</font></html>");
				buttonDesign(stopIt);
				stopIt.setVisible(false);
				
				loopIt = new JRadioButton();
				loopIt.setContentAreaFilled(false);
				loopIt.setVisible(false);
				
				ListenForButton buttonListener = new ListenForButton();
				goBack.addActionListener(buttonListener);
				playIt.addActionListener(buttonListener);
				stopIt.addActionListener(buttonListener);
				loopIt.addActionListener(buttonListener);
				
				genreList=new JComboBox(genres);
				genreList.setSelectedIndex(0);
				genreList.setPreferredSize(new Dimension(250,40));
				genreList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
				genreList.addItemListener(new ItemListener(){ //Set Actions for ComboBox
					public void itemStateChanged(ItemEvent e){
						if (e.getStateChange()==ItemEvent.SELECTED){//Execute if one thing is selected.
							SelectSong.setVisible(true);
							playIt.setVisible(true);
							loopIt.setVisible(true);
							LoopStatement.setVisible(true);
							if(genreList.getSelectedIndex()==0){
								playIt.setVisible(false);
								SelectSong.setVisible(false);
								songScroller.setVisible(false);
								loopIt.setVisible(false);
								LoopStatement.setVisible(false);
						    }else{
						    	songList.setListData(genreindex[genreList.getSelectedIndex()-1]);
						    	songList.setSelectedIndex(0);
								songList.setVisible(true);
								songScroller.setVisible(true);
							}
						}
					}
				});
				
				gbc.gridx=0;
				gbc.gridy=0;
				musicPanel.add(SelectGenre,gbc);
				
				gbc.insets = new Insets(40,0,0,0);
				gbc.gridy=1;
				musicPanel.add(genreList,gbc);
				
				gbc.gridy=2;
				musicPanel.add(SelectSong,gbc);
				
				gbc.gridy=3;
				musicPanel.add(songScroller,gbc);
				
				gbc.gridy=4;
				musicPanel.add(playIt,gbc);
		
				gbc.gridy=5;
				musicPanel.add(stopIt,gbc);
				
				gbc.gridy=6;
				musicPanel.add(goBack,gbc);
				
				gbc.gridx=1;
				gbc.gridy=4;
				musicPanel.add(loopIt,gbc);
	
				gbc.gridx=2;
				musicPanel.add(LoopStatement,gbc);
				window.add(musicPanel);
			}
		}
		void turnVisible(){
			musicPanel.setVisible(true);
		}
		void turnOffVisible(){
			musicPanel.setVisible(false);
		}
		private class ListenForButton implements ActionListener{
			Music mixer=new Music();
			public void actionPerformed(ActionEvent c) {
				if (c.getSource()==goBack){
					genreList.setSelectedIndex(0);
					turnOffVisible();
					mainMenu_obj.turnVisible();
				}else if (c.getSource()==stopIt){
					try{
						mixer.stopMusic();
					}catch(NullPointerException e){e.getMessage();}
				}else if (c.getSource()==playIt){
					stopIt.setVisible(true);
					String file="";
					switch (genreList.getSelectedIndex()){
					case 1: switch (songList.getSelectedIndex()){
							case 0: file="God's Plan.wav"; break;
							case 1: file="Litty.wav"; break;
							case 2: file="Look Alive.wav"; break;
							case 3: file="No Heart.wav"; break;
							case 4: file="Savage Mode.wav"; break;
							case 5: file="Wavy.wav"; break;
							}mixer.playMusic(file); break;
					case 2: switch (songList.getSelectedIndex()){
							case 0: file="New Morning.wav"; break;
							case 1: file="Like a Rolling Stone.wav"; break;
							case 2: file="Purple Haze.wav"; break;
							case 3: file="If Not For You.wav"; break;
							case 4: file="Surrender.wav"; break;
							}mixer.playMusic(file); break;
					case 3: switch (songList.getSelectedIndex()){
							case 0: file="DNA.wav"; break;
							case 1: file="Lady.wav"; break;
							case 2: file="Likey.wav"; break;
							case 3: file="Love Song.wav"; break;
							case 4: file="Me Like Yuh.wav"; break;
							case 5: file="Something Special.wav"; break;
							}mixer.playMusic(file); break;
					case 4: switch (songList.getSelectedIndex()){
							case 0: file="Water Music - Hornpipe.wav"; break;
							case 1: file=""; break;
							case 2: file=""; break;
							case 3: file=""; break;
							case 4: file=""; break;
							}mixer.playMusic(file); break;
					case 5: switch (songList.getSelectedIndex()){
							case 0: file="Fortnite.wav"; break;
							case 1: file="Silver Crown.wav"; break;
							case 2: file="Urf.wav"; break;
							case 3: file=""; break;
							case 4: file=""; break;
							}mixer.playMusic(file);
					}
				}else if (loopIt.isSelected()==true) {
					mixer.loopMusic();
				}else if (loopIt.isSelected()==false) {
					mixer.loopStop();
				}
			}
		}
	}
}


//--------------------------------------MUSIC CLASS----------------------------------//
class Music{
	private
	Clip clip;
	boolean playing=false; //Prevent additional songs playing at the same time
	public void playMusic(String songName){ //Plays Audio
		if (playing==true) {
			clip.stop();
			playing=false;
		}
		try {
	        File file = new File(songName);
	        if (file.exists()) {
	            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
	            clip = AudioSystem.getClip();
	            clip.open(sound);
	            clip.start();
	            playing=true;
	        }else{throw new RuntimeException("Sound: file not found");}
	    }catch (MalformedURLException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Sound: Malformed URL: " + e);
	    }catch (UnsupportedAudioFileException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Sound: Unsupported Audio File: " + e);
	    }catch (IOException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Sound: Input/Output Error: " + e);
	    }catch (LineUnavailableException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);}
	}
	public void stopMusic(){ //Stops Audio
		clip.close();
	}
	public void loopMusic(){ //Loops Audio
		if (playing==true){
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		}
	}
	public void loopStop() { //Stops Loop
		clip.loop(0);
	}
}
