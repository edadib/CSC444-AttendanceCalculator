package trygui;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
								//import java.text.DecimalFormat;
import java.util.*;

import javax.swing.*;

public class tryProject extends JPanel implements ActionListener{
	private JLabel staffId, in, out, total;																		//declaration of global variables
	private JTextField staffIdinput, inInput, outInput ,totalout;
	private JButton enter, reset, calculate;
	private JRadioButton mon,tue,wed,thu,fri,sat,sun;
	private ButtonGroup g = new ButtonGroup();

/*method tryProject, all the declaration,initialized value, adding of components*/
	public tryProject()
	{
		setLayout(new GridLayout(9,2)); 																		// to set the layout of the user interface
		staffId = new JLabel("Enter Staff Id : ");																//declaration of all label
		in = new JLabel("Enter punch in time (24h): ");			
		out = new JLabel("Enter punch out time (24h): ");		
		total = new JLabel("Comments : ");
		
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
		mon.setSelected(true);                									 								//default radio button selection

		enter = new JButton("Enter");																			// button assignation
		enter.setToolTipText("To enter information");															//on hover, it will display a popup, to give information
		reset = new JButton("Reset"); 
		reset.setToolTipText("Reset to default value");
		calculate = new JButton("Calculate");
		calculate.setToolTipText("To calculate working hours");

		add(staffId);																							// add all the component to the user interface
		add(staffIdinput);
		add(in);
		add(inInput);
		add(out);
		add(outInput);
		add(total);
		add(totalout);
		add(mon);
		add(tue);
		add(wed);
		add(thu);
		add(fri);
		add(sat);
		add(sun);
		add(calculate);
		add(enter);
		add(reset);

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

				FileWriter fw1 = new FileWriter(sId+".txt",true);												//text file input output
				BufferedWriter bW1 = new BufferedWriter(fw1);
				PrintWriter pW1 = new PrintWriter(bW1);

				FileWriter fw2 = new FileWriter(sId+" remain.txt",true);
				BufferedWriter bW2 = new BufferedWriter(fw2);
				PrintWriter pW2 = new PrintWriter(bW2);

				deduct = outTime-inTime;																		//working hours calculation
				
				totalout.setText("Done :D");																	//tell user upon successful file creation

				if(mon.isSelected()==true)																		//determine which day user work
				{
					day = "Monday";
				}
				if(tue.isSelected()==true)
				{
					day = "Tuesday";
				}
				if(wed.isSelected()==true)
				{
					day = "Wednesday";
				}
				if(thu.isSelected()==true)
				{
					day = "Thursday";
				}
				if(fri.isSelected()==true)
				{
					day = "Friday";
				}
				if(sat.isSelected()==true)
				{
					day = "Saturday";
				}
				if(sun.isSelected()==true)
				{
					day = "Sunday";
				}

				pW1.println("\n"+day+"\t"+inTime+"\t"+outTime);													//writing to user record file, like a log file
				pW2.println(""+deduct);																			//writing to user working hour file, for the use of total working hour calculation
				
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

			
		}
		if(e.getSource() == calculate)																			//if user click calculate, it will search for file with staff id name and calculate it total working hour for the week
		{
			try
			{
				s = new Scanner(new BufferedReader(new FileReader(sId+" remain.txt")));							//read from file
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
			finally{
				s.close();																						//close file
			}
			if(sum<40)								
			{
				totalout.setText("You have not complete your 40 hour for this week, you only did "+sum);		//to display if user does not meet the requirement
			}
			else
			{
				totalout.setText("Congratulations. You have completed your 40 hour this week, you did "+sum);	//if user meet the requirement
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
		JFrame f = new JFrame("Attendance Calculator System");													//Title of the app
		f.setPreferredSize(new Dimension (800,600));															//set the size of window
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);											
		f.getContentPane().add(new tryProject());																
		f.pack();		
		f.setVisible(true);

	}
}
