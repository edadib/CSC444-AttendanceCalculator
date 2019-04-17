package trygui;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
								//import java.text.DecimalFormat;
import java.util.*;

import javax.swing.*;

public class tryProject2 extends JPanel implements ActionListener{
	private JLabel staffId, in, out, total, empty1, empty2, empty3, empty4, empty5, empty6;																		//declaration of global variables
	private JTextField staffIdinput, inInput, outInput ,totalout;
	private JButton enter, reset, calculate;
	private JRadioButton mon,tue,wed,thu,fri,sat,sun;
	private ButtonGroup g = new ButtonGroup();
	JFrame f = new JFrame("Attendance Calculator System");



	/*method tryProject2, all the declaration,initialized value, adding of components*/
	public tryProject2()
	{

		staffId = new JLabel("Enter Staff Id : ");																//declaration of all label
		in = new JLabel("Enter punch in time (24h): ");			
		out = new JLabel("Enter punch out time (24h): ");		
		total = new JLabel("Comments : ");
		empty1 = new JLabel(" ");
		empty2 = new JLabel(" ");
		empty3 = new JLabel(" ");
		empty4 = new JLabel(" ");
		empty5 = new JLabel(" ");
		empty6 = new JLabel(" ");
		
		staffIdinput = new JTextField();																		// assignation of all text field and it`s initialization
		staffIdinput.setText("");
		inInput = new JTextField();
		inInput.setText("0");
		outInput = new JTextField();
		outInput.setText("0");
		totalout = new JTextField();
		totalout.setEditable(false);

		mon = new JRadioButton("Monday");																		// radio button assignation
		tue = new JRadioButton("Tuesday");
		wed = new JRadioButton("Wednesday");
		thu = new JRadioButton("Thursday");
		fri = new JRadioButton("Friday");
		sat = new JRadioButton("Saturday");
		sun = new JRadioButton("Sunday");
		

		g.add(mon);																								// adding of button to its group
		g.add(tue);
		g.add(wed);
		g.add(thu);
		g.add(fri);
		g.add(sat);
		g.add(sun);

		enter = new JButton("Enter");																			// button assignation
		enter.setToolTipText("To enter information");															//on hover, it will display a popup, to give information
		reset = new JButton("Reset"); 
		reset.setToolTipText("Reset to default value");
		calculate = new JButton("Calculate");
		calculate.setToolTipText("To calculate working hours");

		f.add(staffId);																							// add all the component to the user interface
		f.add(staffIdinput);
		f.add(empty1);
		f.add(in);
		f.add(inInput);
		f.add(empty2);
		f.add(out);
		f.add(outInput);
		f.add(empty3);
		f.add(total);
		f.add(totalout);
		f.add(empty4);
		f.add(mon);
		f.add(tue);
		f.add(wed);
		f.add(thu);
		f.add(fri);
		f.add(sat);
		f.add(sun);
		f.add(empty5);
		f.add(empty6);
		f.add(calculate);
		f.add(enter);
		f.add(reset);

		mon.addActionListener(this);																			//adding action listener to all that requires
		tue.addActionListener(this);
		wed.addActionListener(this);
		thu.addActionListener(this);
		fri.addActionListener(this);
		sat.addActionListener(this);
		sun.addActionListener(this);
		enter.addActionListener(this);
		reset.addActionListener(this);
		calculate.addActionListener(this);
		
      f.setLayout(new GridLayout(8,3));
      f.setExtendedState(Frame.MAXIMIZED_BOTH);
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.setVisible(true);

	}

	public void actionPerformed(ActionEvent e)
	{
		double inTime, outTime, deduct, sum = 0.0;																//local variables declaration
		String sId, day="";
		Scanner s = null;
		sId = staffIdinput.getText();
		inTime = Double.parseDouble(inInput.getText());															//values input from text field are string, need to convert to double for arithmetic operation
		outTime = Double.parseDouble(outInput.getText());
		if(e.getSource() == enter)																				//if enter is clicked
		{
			try 																								//try-catch clause for exception handling
			{	
				FileOutputStream fo = new FileOutputStream("staffId.dat");										//binary file input output 
				ObjectOutputStream ou = new ObjectOutputStream(fo);

				FileInputStream fi = new FileInputStream("staffId.dat");
				ObjectInputStream oi = new ObjectInputStream(fi);

				FileWriter fw1 = new FileWriter(sId+" log.txt",true);												//text file input output
				BufferedWriter bW1 = new BufferedWriter(fw1);
				PrintWriter pW1 = new PrintWriter(bW1);

				FileWriter fw2 = new FileWriter(sId+" hours.txt",true);
				BufferedWriter bW2 = new BufferedWriter(fw2);
				PrintWriter pW2 = new PrintWriter(bW2);

				deduct = outTime-inTime;																		//working hours calculation

				if(mon.isSelected()==true)																		//determine which day user work
				{
					day = "Monday";
					JOptionPane.showMessageDialog(null,"Done :D");												//popup that tells user upon successful file creation
					pW1.println("\n"+day+"\t"+inTime+"\t"+outTime);												//writing to user record file, like a log file
					pW2.println(""+deduct);																		//writing to user working hour file, for the use of total working hour calculation
				}
				if(tue.isSelected()==true)
				{
					day = "Tuesday";
					JOptionPane.showMessageDialog(null,"Done :D");
					pW1.println("\n"+day+"\t"+inTime+"\t"+outTime);
					pW2.println(""+deduct);
				}
				if(wed.isSelected()==true)
				{
					day = "Wednesday";
					JOptionPane.showMessageDialog(null,"Done :D");
					pW1.println("\n"+day+"\t"+inTime+"\t"+outTime);
					pW2.println(""+deduct);
				}
				if(thu.isSelected()==true)
				{
					day = "Thursday";
					JOptionPane.showMessageDialog(null,"Done :D");
					pW1.println("\n"+day+"\t"+inTime+"\t"+outTime);
					pW2.println(""+deduct);
				}
				if(fri.isSelected()==true)
				{
					day = "Friday";
					JOptionPane.showMessageDialog(null,"Done :D");
					pW1.println("\n"+day+"\t"+inTime+"\t"+outTime);
					pW2.println(""+deduct);
				}
				if(sat.isSelected()==true)
				{
					day = "Saturday";
					JOptionPane.showMessageDialog(null,"Done :D");
					pW1.println("\n"+day+"\t"+inTime+"\t"+outTime);
					pW2.println(""+deduct);
				}
				if(sun.isSelected()==true)
				{
					day = "Sunday";
					JOptionPane.showMessageDialog(null,"Done :D");
					pW1.println("\n"+day+"\t"+inTime+"\t"+outTime);
					pW2.println(""+deduct);
				}
				if(mon.isSelected()==false && tue.isSelected()==false && wed.isSelected()==false && thu.isSelected()==false && fri.isSelected()==false && sat.isSelected()==false && sun.isSelected()==false) //If none is selected
				{
					JOptionPane.showMessageDialog(new JFrame(),"Please choose your working day first","Warning",JOptionPane.WARNING_MESSAGE);		//Warning Popup that tells the user need to choose its working day first
				}


				ou.close();																						//closing of all file
				oi.close();
				pW1.close();
				pW2.close();
			}
			catch(FileNotFoundException fn)
			{
				JOptionPane.showMessageDialog(null,fn.getMessage());
				fn.printStackTrace();
			}
			catch(NumberFormatException ne)
			{
				JOptionPane.showMessageDialog(null,ne.getMessage());
				ne.printStackTrace();
			}
			catch(NullPointerException np)
			{
				JOptionPane.showMessageDialog(null,np.getMessage());
				np.printStackTrace();
			}
			catch(IOException io)
			{
				JOptionPane.showMessageDialog(null,io.getMessage());
				io.printStackTrace();
			}
			catch(Exception de)
			{  
				JOptionPane.showMessageDialog(null,de.getMessage());
				de.printStackTrace();
			}
		}
		if(e.getSource() == reset) 																				//if user click reset button, it will reset all input
		{
			staffIdinput.setText(" ");
			inInput.setText("0");
			outInput.setText("0");
			g.clearSelection();

			
		}
		if(e.getSource() == calculate)																			//if user click calculate, it will search for file with staff id name and calculate it total working hour for the week
		{
			try
			{
				s = new Scanner(new BufferedReader(new FileReader(sId+" hours.txt")));							//read from file
				while (s.hasNext()) 																			//while it has more value in it, it will loop
				{
					if (s.hasNextDouble()) {																	//if it is double, it will add with sum value and store its value in variable sum
						sum += s.nextDouble();
					}
					else {
						s.next();																				//if not double it will skip
					}
				}
			}
			catch(FileNotFoundException fn)																		//exception handler
			{
				JOptionPane.showMessageDialog(null,fn.getMessage());
				fn.printStackTrace();
			}
			catch(NumberFormatException ne)
			{
				JOptionPane.showMessageDialog(null,ne.getMessage());
				ne.printStackTrace();
			}
			catch(NullPointerException np)
			{
				JOptionPane.showMessageDialog(null,np.getMessage());
				np.printStackTrace();
			}
			catch(Exception de)
			{  
				JOptionPane.showMessageDialog(null,de.getMessage());
				de.printStackTrace();
			}
			finally{
				s.close();																						//close file
			}
			if(sum<40)								
			{
				totalout.setText("You have not complete your 40 hour for this week, you only did "+sum+" hours");		//to display if user does not meet the requirement
			}
			else
			{
				totalout.setText("Congratulations. You have completed your 40 hour this week you did "+sum+" hours");	//if user meet the requirement
			}
		}
	}
/*
_____________________________________________________________________________________
|		Simple README																|
|																					|
|	1. First compile and run this source code. 										|
|	2. As the output has emerged, first key-in the staff id, the times, and day.	|
|	3. Click on enter to save the user input into file.								|
|	4. Click calculate to calculate either user has enough working time or not.		|
[___________________________________________________________________________________]

*/
	public static void main(String[] args) 
	{ 																		
			new tryProject2();														//set the size of window
	}
}