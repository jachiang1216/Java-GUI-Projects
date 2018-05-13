/*
 * Simple Calculator Program with GUI
 * Created by Jian An Chiang
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class SimpleCalculator{
	public static void main(String[] args) { //Main
		new CalcMachine(); //Starts Calculator
	}
}

class CalcMachine extends JFrame{
	private 
	//Calculator Frame
	JFrame calcWindow; //Window
	JPanel InformationPanel; //Holds Title and Description.
	JPanel DisplayPanel; //Display Panel is Black
	JPanel DisplayPanel2; //Display Panel 2 is White
	JPanel CalcPanel; //Panel holds the Buttons
	JPanel thePanel; //Details Panel
	JButton button1,button2,button3,button4,button5,button6,button7,button8,button9,button0; //Number Buttons
	JButton buttonDiv,buttonMul,buttonSub,buttonAdd,buttonDec,buttonEql; //Operation Buttons
	JButton buttonInfo,buttonEmpty,buttonCE,buttonBackSpace; //Other Buttons
	JLabel message,description,displaytext; //Title,Description,Display Window
	//Details Frame
	JButton goBack; 
	//Button Action Object
	ListenForButton buttonListener;
	String displayString=""; //String in the Display Window
	boolean overwrite=false; //Overwrite. We will need this if user wants to clear the window if user clicks a number on an existing number 
	boolean addedListens=false; //Only add the listens once
	boolean oneOperation=false; //Only one operation allowed on the display window
	boolean oneDecimal=false; //Only one decimal per number
	public
	CalcMachine(){ //Constructor
		//Initialize All JButton Objects
		button1 = new JButton();
		button2 = new JButton();
		button3 = new JButton();
		button4 = new JButton();
		button5 = new JButton();
		button6 = new JButton();
		button7 = new JButton();
		button8 = new JButton();
		button9 = new JButton();
		button0 = new JButton();
		buttonDiv = new JButton();
		buttonMul = new JButton();
		buttonSub = new JButton();
		buttonAdd = new JButton();
		buttonDec = new JButton();
		buttonEql = new JButton();
		buttonInfo = new JButton();
		buttonEmpty = new JButton();
		buttonCE = new JButton();
		buttonBackSpace = new JButton();
		calcWindow(); //Start Calculator Window
	}
	//Calculator Window
	void calcWindow(){
		Toolkit tk = Toolkit.getDefaultToolkit(); //Used to get external image 
		
		//Window Properties
		calcWindow = new JFrame("Simple Calculator");
		calcWindow.setResizable(false);
		calcWindow.setSize(1000,800); 
		calcWindow.setLocationRelativeTo(null); //Centers window
		calcWindow.setIconImage(tk.getImage("Calculator.png")); //Set Window Icon Image
		calcWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes Window in the Background.
		
		//Panel Properties
		//Top Panel - Information
		InformationPanel = new JPanel();
        InformationPanel.setLayout(new GridLayout(0,1)); //0 row, 1 column
		InformationPanel.setBackground(Color.WHITE); 
		//Center Panel - Display
		DisplayPanel = new JPanel(new GridBagLayout()); 
		DisplayPanel.setBackground(Color.BLACK);
		DisplayPanel2 = new JPanel(new GridBagLayout()); //Centers DisplayPanel2 on DisplayPanel
	    DisplayPanel2.setBackground(Color.WHITE);
	    DisplayPanel2.setPreferredSize(new Dimension(800,150));
	    DisplayPanel.add(DisplayPanel2); //DisplayPanel2 will be on DisplayPanel1
		//Bottom Panel - Buttons
		CalcPanel = new JPanel();
        CalcPanel.setLayout(new GridLayout(5,4,5,5)); //5x4 with 5(hgap) and 5(vgap)
        CalcPanel.setBackground(Color.BLACK);
        
        //Label Properties
		message = new JLabel("<html><font size=50>Simple Calculator</font></html>",SwingConstants.CENTER);
		description = new JLabel("Performs basic mathematical operations. Enjoy!",SwingConstants.CENTER);
		description.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
		displaytext = new JLabel();
		displaytext.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 100));
		
		//Button Properties
		buttonProperties(); //Set Properties of Buttons
		addListeners(); //Add Actions to Buttons
	    addToPanel(); //Add Components to Panel
	    
		//Add Panels to the Frame
		calcWindow.add(InformationPanel,BorderLayout.NORTH);
		calcWindow.add(DisplayPanel,BorderLayout.CENTER);
		calcWindow.add(CalcPanel,BorderLayout.SOUTH);
		calcWindow.setVisible(true); //Set the window to visible
	}
	//Details Panel
	void detailPanel(){
		//Hide Previous Panels
		InformationPanel.setVisible(false);
		DisplayPanel.setVisible(false);
		CalcPanel.setVisible(false);
		
		//Panel Properties
	    thePanel = new JPanel(new GridBagLayout()); //Grids
		GridBagConstraints gbc = new GridBagConstraints(); //Create Object to Set Location of Labels 
		thePanel.setBackground(Color.BLACK);
		//Text Properties
		JLabel details = new JLabel();
		details.setText("<html><br></br><h1><b>Calculator Functional Details</b></h1>"
				+ "<font size=5><br></br><br></br>"
				+ "Hello, this is my first attempt at creating a simple calculator program. Therefore, "
				+ "this calculator is far from ideal.<br></br>~Jian An Chiang<br></br><br></br>"
				+ "Function: Basic Mathematical Operations on Integers and Decimals<br></br><br></br>"
				+ "Discovered Bugs:<br></br>"
				+ "		-Cannot divide or multiply by negative numbers <br></br>"
				+ "     -Cannot start with a Decimal <br></br>"
				+ "     -Full erase with backspace may allow operations to be clicked </font></html>");
		details.setForeground(Color.WHITE);
		//Go Back Button Properties
		goBack = new JButton();
		goBack.addActionListener(buttonListener);
		goBack.setText("<html><font size=5>Go Back to Calculator</font></html>");
		goBack.setForeground(Color.WHITE);
		goBack.setBorderPainted(false);
		goBack.setFocusPainted(false);
		goBack.setContentAreaFilled(false);
		//Formatting
		gbc.insets = new Insets(0,0,180,0); //Set Spacing. (Top,Left,Bottom,Right)
		gbc.gridx=0;
		gbc.gridy=0;
		thePanel.add(details,gbc);
		gbc.gridx=0;
		gbc.gridy=1;
		thePanel.add(goBack,gbc);
		thePanel.setVisible(true); //Set the Panel to visible
		calcWindow.add(thePanel); //Add the Panel
	}
	
	void buttonProperties(){ //For Calc Window
		Font buttonfont=(new Font(Font.DIALOG, Font.PLAIN, 50));
		buttonInfo.setText("Functional Details");
		button1.setText("1");
		button2.setText("2");
		button3.setText("3");
		button4.setText("4");
		button5.setText("5");
		button6.setText("6");
		button7.setText("7");
		button8.setText("8");
		button9.setText("9");
		button0.setText("0");
		buttonDiv.setText("%");
		buttonMul.setText("x");
		buttonSub.setText("-");
		buttonAdd.setText("+");
		buttonDec.setText(".");
		buttonEql.setText("=");
		buttonCE.setText("CE");
		buttonBackSpace.setText("BackSpace");
		buttonInfo.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,25));
		button1.setFont(buttonfont);
		button2.setFont(buttonfont);
		button3.setFont(buttonfont);
		button4.setFont(buttonfont);
		button5.setFont(buttonfont);
		button6.setFont(buttonfont);
		button7.setFont(buttonfont);
		button8.setFont(buttonfont);
		button9.setFont(buttonfont);
		button0.setFont(buttonfont);
		buttonDiv.setFont(buttonfont);
		buttonMul.setFont(buttonfont);
		buttonSub.setFont(buttonfont);
		buttonAdd.setFont(buttonfont);
		buttonDec.setFont(buttonfont);
		buttonEql.setFont(buttonfont);
		buttonCE.setFont(buttonfont);
		buttonBackSpace.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
	}
	
	void addToPanel(){ //For Calc Window
		//Add Components to the Panel
				InformationPanel.add(new JLabel(""));
				InformationPanel.add(message);
				InformationPanel.add(description);
				DisplayPanel2.add(displaytext);
				CalcPanel.add(buttonInfo);
				CalcPanel.add(buttonEmpty);
				CalcPanel.add(buttonCE);
				CalcPanel.add(buttonBackSpace);
				CalcPanel.add(button7);
				CalcPanel.add(button8);
				CalcPanel.add(button9);
				CalcPanel.add(buttonDiv);
				CalcPanel.add(button4);
				CalcPanel.add(button5);
				CalcPanel.add(button6);
				CalcPanel.add(buttonMul);
				CalcPanel.add(button1);
				CalcPanel.add(button2);
				CalcPanel.add(button3);
				CalcPanel.add(buttonSub);
				CalcPanel.add(button0);
				CalcPanel.add(buttonDec);
				CalcPanel.add(buttonEql);
				CalcPanel.add(buttonAdd);
	}
	
	void addListeners(){
		if (addedListens==false){ //If we hadn't initialized actions for buttons. Do so now.
			buttonListener = new ListenForButton(); //Allow button to listen for an action.
			buttonInfo.addActionListener(buttonListener);
			button1.addActionListener(buttonListener);
			button2.addActionListener(buttonListener);
			button3.addActionListener(buttonListener);
			button4.addActionListener(buttonListener);
			button5.addActionListener(buttonListener);
			button6.addActionListener(buttonListener);
			button7.addActionListener(buttonListener);
			button8.addActionListener(buttonListener);
			button9.addActionListener(buttonListener);
			button0.addActionListener(buttonListener);
			buttonDiv.addActionListener(buttonListener);
			buttonMul.addActionListener(buttonListener);
			buttonSub.addActionListener(buttonListener);
			buttonAdd.addActionListener(buttonListener);
			buttonDec.addActionListener(buttonListener);
			buttonEql.addActionListener(buttonListener);
			buttonCE.addActionListener(buttonListener);
			buttonBackSpace.addActionListener(buttonListener);
			addedListens=true;
		}
	}
	
	//Implement Listeners
	private class ListenForButton implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getSource()==buttonInfo){
				detailPanel(); //Call the Detail Panel
			}
			else if (e.getSource()==goBack){ //Go back to Calculator Panels
				displayString="";
				thePanel.setVisible(false);
				InformationPanel.setVisible(true);
				DisplayPanel.setVisible(true);
				CalcPanel.setVisible(true);
			}else if (e.getSource()==button1){ 
				if (overwrite==true){
					displayString="";
					overwrite=false;
				}
				displayString+="1";
				displaytext.setText(displayString);
			}else if (e.getSource()==button2){
				if (overwrite==true){
					displayString="";
					overwrite=false;
				}
				displayString+="2";
				displaytext.setText(displayString);
			}else if (e.getSource()==button3){
				if (overwrite==true){
					displayString="";
					overwrite=false;
				}
				displayString+="3";
				displaytext.setText(displayString);
			}else if (e.getSource()==button4){
				if (overwrite==true){
					displayString="";
					overwrite=false;
				}
				displayString+="4";
				displaytext.setText(displayString);
			}else if (e.getSource()==button5){
				if (overwrite==true){
					displayString="";
					overwrite=false;
				}
				displayString+="5";
				displaytext.setText(displayString);
			}else if (e.getSource()==button6){
				if (overwrite==true){
					displayString="";
					overwrite=false;
				}
				displayString+="6";
				displaytext.setText(displayString);
			}else if (e.getSource()==button7){
				if (overwrite==true){
					displayString="";
					overwrite=false;
				}
				displayString+="7";
				displaytext.setText(displayString);
			}else if (e.getSource()==button8){
				if (overwrite==true){
					displayString="";
					overwrite=false;
				}
				displayString+="8";
				displaytext.setText(displayString);
			}else if (e.getSource()==button9){
				if (overwrite==true){
					displayString="";
					overwrite=false;
				}
				displayString+="9";
				displaytext.setText(displayString);
			}else if (e.getSource()==button0){
				if (overwrite==true){
					displayString="";
					overwrite=false;
				}
				displayString+="0";
				displaytext.setText(displayString);
			}else if (e.getSource()==buttonDec){
				if (displayString==""){}
				else if (oneDecimal==true){}
				else{
				oneDecimal=true;
				displayString+=".";
				displaytext.setText(displayString);}
			}else if (e.getSource()==buttonDiv){
				overwrite=false;
				if (displayString==""){}
				else if (oneOperation==true){}
				else{
					oneDecimal=false;
					oneOperation=true;
					displayString+="/";
					displaytext.setText(displayString);
				}
			}else if (e.getSource()==buttonMul){
				overwrite=false;
				if (displayString==""){}
				else if (oneOperation==true){}
				else{
					oneDecimal=false;
					oneOperation=true;
					displayString+="x";
					displaytext.setText(displayString);
				}
			}else if (e.getSource()==buttonSub){
				overwrite=false;
				if (oneOperation==true){}
				else{
					oneDecimal=false;
					if (displayString==""){}
					else{oneOperation=true;}
					displayString+="-";
					displaytext.setText(displayString);
				}
			}else if (e.getSource()==buttonAdd){
				overwrite=false;
				if (displayString==""){}
				else if (oneOperation==true){}
				else{
					oneDecimal=false;
					oneOperation=true;
				displayString+="+";
				displaytext.setText(displayString);
				}
			}else if (e.getSource()==buttonCE){
				oneDecimal=false;
				oneOperation=false;
				displayString="";
				displaytext.setText(displayString);
			}else if (e.getSource()==buttonBackSpace){
				try{ //If there is nothing in the String, throw exception.
				if (displayString.substring(displayString.length()-1,displayString.length()).equals("x") || 
					displayString.substring(displayString.length()-1,displayString.length()).equals("/") ||
					displayString.substring(displayString.length()-1,displayString.length()).equals("-") ||
					displayString.substring(displayString.length()-1,displayString.length()).equals("+")){
					oneOperation=false;
				}else if (displayString.substring(displayString.length()-1,displayString.length()).equals(".")){
					oneDecimal=false;
				}
				displayString=displayString.substring(0,displayString.length()-1);
				}catch (IndexOutOfBoundsException c){c.getMessage();}
				displaytext.setText(displayString);
			}
			else if (e.getSource()==buttonEql){
				oneDecimal=false;
				oneOperation=false;
				double result = calcOperation(displayString);
				if (result==(int)result){ //Do not display as a double number if the result is an integer.
					int resultInt=(int)result;
					displayString = Integer.toString(resultInt);
				}else{
					oneDecimal=true;
					displayString = Double.toString(result);
				}
				displaytext.setText(displayString);
				overwrite=true;
			}
		}
	}
	
	//All Calculations
	public double calcOperation(String c){
		int pos_neg=1;
		int start=0;
		if (c.substring(0,1).equals("-")){ //If first character is minus sign, we conclude that it's negative
			pos_neg=-1;
			start=1;
		}
		String operator="";
		String number1="";
		String number2="";
		try{
		for (int i=start;i<c.length();++i){ //Find First Number
			if (c.substring(i,i+1).equals("+") || 
			    c.substring(i,i+1).equals("-") ||
				c.substring(i,i+1).equals("x") ||
				c.substring(i,i+1).equals("/")){
				start=i+1;
				operator=c.substring(i,i+1); //Store Operator
				break;
			}
			number1+=c.substring(i,i+1);
		}
		for (int j=start;j<c.length();++j){ //Find Second Number
			number2+=c.substring(j,j+1);
		}
		}catch(IndexOutOfBoundsException e){e.getMessage();}
		double result=0;
		switch (operator){
		case "+": result=pos_neg*Double.parseDouble(number1)+Double.parseDouble(number2); break;
		case "-": result=pos_neg*Double.parseDouble(number1)-Double.parseDouble(number2); break;
		case "/": result=pos_neg*Double.parseDouble(number1)/Double.parseDouble(number2); break;
		case "x": result=pos_neg*Double.parseDouble(number1)*Double.parseDouble(number2); break;
		}
		return result;
	}
	
}

