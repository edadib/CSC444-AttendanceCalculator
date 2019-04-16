package trygui;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.*;

public class tryProject extends JPanel implements ActionListener{
	private JLabel staffId, in, out, total, empty;
	private JTextField staffIdinput, inInput, outInput ,totalout;
	private JButton enter, reset, calculate;
	private JRadioButton mon,tue,wed,thu,fri,sat,sun;
	private ButtonGroup g = new ButtonGroup();

	public tryProject()
	{
		setLayout(new GridLayout(9,2));
		empty = new JLabel("");
		staffId = new JLabel("Enter Staff Id : ");
		in = new JLabel("Enter punch in time (24h): ");
		out = new JLabel("Enter punch out time (24h): ");
		total = new JLabel("Comments : ");
		
		staffIdinput = new JTextField();
		staffIdinput.setText("");
		inInput = new JTextField();
		inInput.setText("");
		outInput = new JTextField();
		outInput.setText("");
		totalout = new JTextField();
		totalout.setEditable(false);

		mon = new JRadioButton("Monday");
		tue = new JRadioButton("Tuesday");
		wed = new JRadioButton("Wednesday");
		thu = new JRadioButton("Thursday");
		fri = new JRadioButton("Friday");
		sat = new JRadioButton("Saturday");
		sun = new JRadioButton("Sunday");
		

		g.add(mon);
		g.add(tue);
		g.add(wed);
		g.add(thu);
		g.add(fri);
		g.add(sat);
		g.add(sun);

		enter = new JButton("Enter");
		enter.setToolTipText("To enter information");
		reset = new JButton("Reset"); 
		reset.setToolTipText("Reset to default value");
		calculate = new JButton("Calculate");
		calculate.setToolTipText("To calculate working hours");

		add(staffId);
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

		mon.addActionListener(this);
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
		double inTime, outTime, deduct, remain, sum = 0.0;
		String sId, day="";
		DecimalFormat df = new DecimalFormat("0.00");
		Scanner s = null;
		sId = staffIdinput.getText();
		inTime = Double.parseDouble(inInput.getText());
		outTime = Double.parseDouble(outInput.getText());
		if(e.getSource() == enter)
		{
			try
			{	
				FileOutputStream fo = new FileOutputStream("staffId.dat");
				ObjectOutputStream ou = new ObjectOutputStream(fo);

				FileInputStream fi = new FileInputStream("staffId.dat");
				ObjectInputStream oi = new ObjectInputStream(fi);

				FileWriter fw1 = new FileWriter(sId+".txt",true);
				BufferedWriter bW1 = new BufferedWriter(fw1);
				PrintWriter pW1 = new PrintWriter(bW1);

				FileWriter fw2 = new FileWriter(sId+" remain.txt",true);
				BufferedWriter bW2 = new BufferedWriter(fw2);
				PrintWriter pW2 = new PrintWriter(bW2);


				
				deduct = outTime-inTime;
				
				totalout.setText("Done :D");

				if(mon.isSelected()==true)
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

				pW1.println("\n"+day+"\t"+inTime+"\t"+outTime);
				pW2.println(""+deduct);
				
				ou.close();
				pW1.close();
				pW2.close();
			}
			catch(FileNotFoundException fn)
			{
				JOptionPane.showMessageDialog(null,fn.getMessage());
				fn.printStackTrace();
			}
			catch(IOException io)
			{
				JOptionPane.showMessageDialog(null,io.getMessage());
				io.printStackTrace();
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
		}
		if(e.getSource()== reset) 
		{
			staffIdinput.setText(" ");
			inInput.setText(" ");
			outInput.setText(" ");
			g.clearSelection();
			
		}
		if(e.getSource() == calculate)
		{
			try{
				s = new Scanner(new BufferedReader(new FileReader(sId+" remain.txt")));
				while (s.hasNext()) 
				{
					if (s.hasNextDouble()) {
						sum += s.nextDouble();
					}
					else {
						s.next();
					}
				}
			}
			catch(FileNotFoundException fn)
			{
				JOptionPane.showMessageDialog(null,fn.getMessage());
				fn.printStackTrace();
			}
			catch(IOException io)
			{
				JOptionPane.showMessageDialog(null,io.getMessage());
				io.printStackTrace();
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
				s.close();
			}
			if(sum<40)
			{
				totalout.setText("You have not complete your 40 hour for this week, you only did "+sum);
			}
			else
			{
				totalout.setText("Congratulations. You have completed your 40 hour this week, you did "+sum);
			}
		}
	}

	public static void main(String[] args) 
	{
		JFrame f = new JFrame("Attendance Calculator System");
		f.setPreferredSize(new Dimension (800,600));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.getContentPane().add(new tryProject());
		f.pack();
		f.setVisible(true);

	}
}