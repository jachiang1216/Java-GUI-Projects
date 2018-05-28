/* Create your own background screen saver with this program!
 * Created by Jian An Chiang
 */
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//------------------------------------MAIN METHOD----------------------------------------//
public class Background_Creator{
	public static void main(String[] args){
	mainLayout obj = new mainLayout(); //Sets the main layout for the Program.
	}
}
//----------------------------------MAIN LAYOUT CLASS------------------------------------//
class mainLayout extends JFrame{
	protected 
	JFrame window;
	JPanel mainPanel;
	aboutUs obj;
	mainMenu obj2;
	musicMenu obj3;
	public
	mainLayout(){ //Constructor
		Toolkit tk = Toolkit.getDefaultToolkit();
		window=new JFrame("Background Creator Tool");
		window.setSize(1200,800);
		window.setResizable(false);
		window.setLocationRelativeTo(null);
		window.setIconImage(tk.getImage("Paintbrush.png"));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel=new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.GRAY);
		window.add(mainPanel);
		obj2=new mainMenu();
	}	
	//-------------------------------------MAIN MENU CLASS------------------------------------//
	class mainMenu{
		private
		JPanel Info,Options;
		JLabel title,author;
		JButton begin,music,credit,exit;
		GridBagConstraints gbc;
		boolean addedListens=false;
		boolean Abouton=false;
		boolean Musicon=false;
		public
		mainMenu(){
			begin=new JButton();
			music=new JButton();
			credit=new JButton();
			exit=new JButton();
			Info=new JPanel(new GridLayout(0,1));
			Options=new JPanel(new GridBagLayout());
			gbc = new GridBagConstraints();
			Options.setBackground(Color.GRAY);
			display();
		}
		//--------------------------------DISPLAY METHOD-------------------------------------//
		void display(){
			title=new JLabel("<html><br></br><br></br>Background Creator Tool</html>",SwingConstants.CENTER);
			author=new JLabel("<html>Create your own custom screensaver</html>",SwingConstants.CENTER);
			title.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
			author.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			Info.add(title);
			Info.add(author);
			Info.setOpaque(false); //Make JPanel Transparent
			gbc.insets = new Insets(10,10,10,10); //Padding
			//Begin Button
			begin.setBackground(Color.BLACK);
			begin.setForeground(Color.WHITE);
			begin.setBorderPainted(false);
			begin.setFocusPainted(false);
			gbc.gridx=0;
			gbc.gridy=0;
			begin.setPreferredSize(new Dimension(250,40));
			begin.setText("<html><font size=5>Get Started</font></html>");
			Options.add(begin,gbc);
			//Select Music Button
			music.setBackground(Color.BLACK);
			music.setForeground(Color.WHITE);
			music.setBorderPainted(false);
			music.setFocusPainted(false);
			gbc.gridx=0;
			gbc.gridy=1;
			music.setPreferredSize(new Dimension(250,40));
			music.setText("<html><font size=5>Select Music</font></html>");
			Options.add(music,gbc);
			//About Me Button
			credit.setBackground(Color.BLACK);
			credit.setForeground(Color.WHITE);
			credit.setBorderPainted(false);
			credit.setFocusPainted(false);
			gbc.gridx=0;
			gbc.gridy=2;
			credit.setPreferredSize(new Dimension(250,40));
			credit.setText("<html><font size=5>About Tool</font></html>");
			Options.add(credit,gbc);
			//Exit Button
			exit.setBackground(Color.BLACK);
			exit.setForeground(Color.WHITE);
			exit.setBorderPainted(false);
			exit.setFocusPainted(false);
			gbc.gridx=0;
			gbc.gridy=3;
			exit.setPreferredSize(new Dimension(250,40));
			exit.setText("<html><font size=5>Exit</font></html>");
			Options.add(exit,gbc);
			
			//Add JPanels to mainPanel.
			mainPanel.add(Info,BorderLayout.NORTH);
			mainPanel.add(Options);
			window.setVisible(true);
			
			//Add Actions to Buttons
			if (addedListens==false){ //If we hadn't initialized actions for buttons. Do so now.
				ListenForButton buttonListener = new ListenForButton(); //Allow button to listen for an action.
				begin.addActionListener(buttonListener);
				music.addActionListener(buttonListener);
				credit.addActionListener(buttonListener);
				exit.addActionListener(buttonListener);
				addedListens=true;
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
			public void actionPerformed(ActionEvent c) {
				if (c.getSource()==exit){
					window.dispose();
				}
				else if(c.getSource()==credit){
					turnOffVisible();
					if (Abouton==false){ //Create the About Panel if it hasn't been created yet
						obj=new aboutUs();
						Abouton=true;
					}else{
						obj.turnVisible();
					}
				}
				else if(c.getSource()==music){
					turnOffVisible();
					if (Musicon==false){ //Create the Music Panel if it hasn't been created yet
						obj3=new musicMenu();
						Musicon=true;
					}else{
						obj3.turnVisible();
					}
				}
				else if(c.getSource()==begin){ //Get Started.
					
				}
			}
		}
	}
	//------------------------------------ABOUT US CLASS-----------------------------------//
	class aboutUs{
		private
		JPanel aboutPanel;
		JButton goBack;
		JLabel details,creator;
		GridBagConstraints gbc = new GridBagConstraints();
		boolean addedListens=false;
		public
		aboutUs(){
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
			
			goBack=new JButton();
			goBack.setBackground(Color.BLACK);
			goBack.setForeground(Color.WHITE);
			goBack.setBorderPainted(false);
			goBack.setFocusPainted(false);
			goBack.setPreferredSize(new Dimension(250,40));
			goBack.setText("<html><font size=5>Go Back</font></html>");
			if(addedListens==false){
				ListenForButton buttonListener = new ListenForButton();
				goBack.addActionListener(buttonListener);
				addedListens=true;
			}
			
			gbc.gridx=1;
			gbc.gridy=0;
			creator=new JLabel("<html><h1 style='font-family:Courier New;'><font size=4>Developed by Jian An Chiang</font></h1></html>");
			aboutPanel.add(details,gbc);
			gbc.gridx=1;
			gbc.gridy=1;
			aboutPanel.add(creator,gbc);
			
			gbc.insets = new Insets(80,0,20,0);
			gbc.gridx=1;
			gbc.gridy=2;
			aboutPanel.add(goBack,gbc);
			mainPanel.add(aboutPanel);
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
					obj2.turnVisible();
				}
			}
		}
	}
	//------------------------------------MUSIC MENU CLASS-----------------------------------//
		class musicMenu{
			private
			JPanel musicPanel;
			JLabel SelectGenre,SelectSong;
			JButton playIt,goBack,stopIt;
			GridBagConstraints gbc = new GridBagConstraints();
			boolean addedListens=false;
			JComboBox genreList;
			JList songList;
			JScrollPane songScroller;
			String[] genres = {"Select Genre","Hip Hop","Rock","Korean Pop","Orchestra","Game"};
			String[] hipHopSongs = {"God's Plan","Litty","Look Alive","No Heart","Savage Mode","Wavy"};
			String[] rockSongs = {"New Morning","Like a Rolling Stone","Purple Haze","If Not For You","Surrender"};
			String[] kPopSongs = {"DNA","Lady","Likey","Love Song","Me Like Yuh","Something Special"};
			String[] orchestralSongs = {"Hornpipe"};
			String[] gameSongs = {"Fortnite","Silver Crown","Urf"};
			public
			musicMenu(){
				musicPanel=new JPanel(new GridBagLayout());
				musicPanel.setBackground(Color.GRAY);
				musicPanel.setPreferredSize(new Dimension(800,500));
				
				SelectGenre=new JLabel("Select a Genre");
				SelectGenre.setFont(new Font(Font.MONOSPACED,Font.BOLD,65));
				SelectSong=new JLabel("Select a Song");
				SelectSong.setFont(new Font(Font.MONOSPACED,Font.BOLD,65));
				SelectSong.setVisible(false);
				
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
				playIt=new JButton();
				playIt.setBackground(Color.BLACK);
				playIt.setForeground(Color.WHITE);
				playIt.setBorderPainted(false);
				playIt.setFocusPainted(false);
				playIt.setPreferredSize(new Dimension(250,40));
				playIt.setText("<html><font size=5>Play Song</font></html>");
				playIt.setVisible(false);
				
				goBack=new JButton();
				goBack.setBackground(Color.BLACK);
				goBack.setForeground(Color.WHITE);
				goBack.setBorderPainted(false);
				goBack.setFocusPainted(false);
				goBack.setPreferredSize(new Dimension(250,40));
				goBack.setText("<html><font size=5>Go Back</font></html>");
				
				stopIt=new JButton();
				stopIt.setBackground(Color.BLACK);
				stopIt.setForeground(Color.WHITE);
				stopIt.setBorderPainted(false);
				stopIt.setFocusPainted(false);
				stopIt.setPreferredSize(new Dimension(250,40));
				stopIt.setText("<html><font size=5>Stop Music</font></html>");
				stopIt.setVisible(false);
				
				if(addedListens==false){
					ListenForButton buttonListener = new ListenForButton();
					goBack.addActionListener(buttonListener);
					playIt.addActionListener(buttonListener);
					addedListens=true;
				}
				
				genreList=new JComboBox(genres);
				genreList.setSelectedIndex(0);
				genreList.setPreferredSize(new Dimension(250,40));
				genreList.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
				genreList.addItemListener(new ItemListener(){ //Set Actions for ComboBox
					public void itemStateChanged(ItemEvent e){
						if (e.getStateChange()==ItemEvent.SELECTED){//Execute if one thing is selected.
							SelectSong.setVisible(true);
							playIt.setVisible(true);
							if(genreList.getSelectedIndex()==0){
								playIt.setVisible(false);
								SelectSong.setVisible(false);
								songList.setVisible(false);
								songScroller.setVisible(false);
						    }else if(genreList.getSelectedIndex()==1){
						    	songList.setListData(hipHopSongs);
						    	songList.setSelectedIndex(0);
								songList.setVisible(true);
								songScroller.setVisible(true);
							}else if(genreList.getSelectedIndex()==2){
								songList.setListData(rockSongs);
								songList.setSelectedIndex(0);
								songList.setVisible(true);
								songScroller.setVisible(true);
							}else if(genreList.getSelectedIndex()==3){
						    	songList.setListData(kPopSongs);
						    	songList.setSelectedIndex(0);
								songList.setVisible(true);
								songScroller.setVisible(true);
							}else if(genreList.getSelectedIndex()==4){
								songList.setListData(orchestralSongs);
								songList.setSelectedIndex(0);
								songList.setVisible(true);
								songScroller.setVisible(true);
							}else if(genreList.getSelectedIndex()==5){
						    	songList.setListData(gameSongs);
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
				gbc.gridx=0;
				gbc.gridy=1;
				musicPanel.add(genreList,gbc);
				
				gbc.insets = new Insets(40,0,0,0);
				gbc.gridx=0;
				gbc.gridy=2;
				musicPanel.add(SelectSong,gbc);
				
				gbc.insets = new Insets(40,0,0,0);
				gbc.gridx=0;
				gbc.gridy=3;
				musicPanel.add(songScroller,gbc);
				
				gbc.insets = new Insets(40,0,0,0);
				gbc.gridx=0;
				gbc.gridy=4;
				musicPanel.add(playIt,gbc);
				
				gbc.gridx=0;
				gbc.gridy=5;
				musicPanel.add(stopIt,gbc);
				mainPanel.add(musicPanel);
				
				gbc.gridx=0;
				gbc.gridy=6;
				musicPanel.add(goBack,gbc);
				mainPanel.add(musicPanel);
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
					    playIt.setVisible(false);
						songList.setVisible(false);
						turnOffVisible();
						obj2.turnVisible();
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
					}
				}
			}
		}
}


//--------------------------------------MUSIC CLASS----------------------------------//
class Music{
	private
	Clip clip;
	public void playMusic(String songName){ //Plays Audio
		try {
	        File file = new File(songName);
	        if (file.exists()) {
	            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
	            clip = AudioSystem.getClip();
	            clip.open(sound);
	            clip.start();
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
		clip.stop();
	}
	public void loopMusic(){ //Loops Audio
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
}