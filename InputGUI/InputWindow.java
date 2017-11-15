import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.util.Scanner;


public class InputWindow implements ActionListener, FocusListener{
	String newLine = System.lineSeparator();
	String sourceDir = "/Users/marybiggs/SVGnest/SVGnest/";
	BigDecimal DPI = BigDecimal.valueOf(12); 
	private final String hint = "Offset";
	private boolean showingHint;
	
	JFrame       	inputWindow     = new JFrame();
	//JButton 		submitButton	= new JButton("Submit");
	JPanel			topPanel		= new JPanel();
	JPanel			inputPanel		= new JPanel();
	ButtonGroup		shapeButtonGroup = new ButtonGroup();
	JRadioButton	circleButton	= new JRadioButton("Circle");
	JRadioButton	rectangleButton	= new JRadioButton("Rectangle");
	JRadioButton	polygonButton	= new JRadioButton("Polygon");
	JRadioButton	ellipseButton	= new JRadioButton("Ellipse");
	JTextField		offset	        = new JTextField("Offest");
	JTextField		input1			= new JTextField();
	JTextField		input2			= new JTextField();
	JTextField		input3			= new JTextField();
	JTextField		input4			= new JTextField();
	/*JTextField		y			= new JTextField();
	JTextField		width			= new JTextField();
	JTextField		height			= new JTextField();
	JTextField		center		= new JTextField();
	JTextField		sideLength	= new JTextField();
	JTextField		ellipseX 	= new JTextField();
	JTextField		ellipseY 	= new JTextField();
	JTextField		ellipseRX 	= new JTextField();
	JTextField		ellipseRY 	= new JTextField();*/

	JLabel       errorLabelField  = new JLabel("error messages here =>");

	public static void main(String[] args) {
		InputWindow iw = new InputWindow();
	}
	
	public InputWindow(){
		// Build GUI
		inputWindow.getContentPane().add(topPanel, "North");
		inputWindow.getContentPane().add(inputPanel, "Center");
		//topPanel.add(submitButton);
		topPanel.add(circleButton);
		topPanel.add(rectangleButton);
		//topPanel.add(triangleButton);
		topPanel.add(ellipseButton);
		topPanel.add(offset);
		input1.setPreferredSize(new Dimension(100, 24));
		input2.setPreferredSize(new Dimension(100, 24));
		input3.setPreferredSize(new Dimension(100, 24));
		offset.setPreferredSize(new Dimension(100, 24));
		input4.setPreferredSize(new Dimension(100,24));
		
		/*y.setPreferredSize(new Dimension(100,24));
		width.setPreferredSize(new Dimension(100,24));
		height.setPreferredSize(new Dimension(100,24));
		ellipseX.setPreferredSize(new Dimension(100,24));
		ellipseY.setPreferredSize(new Dimension(100,24));
		ellipseRX.setPreferredSize(new Dimension(100,24));
		ellipseRY.setPreferredSize(new Dimension(100,24));
		center.setPreferredSize(new Dimension(100,24));
		sideLength.setPreferredSize(new Dimension(100,24));*/
		inputPanel.add(input1, "Center");
		inputPanel.add(input2, "Center");
		inputPanel.add(input3, "Center");
		inputPanel.add(input4, "Center");
		offset.setForeground(Color.LIGHT_GRAY);
		/*inputPanel.add(y, "Center");
		inputPanel.add(width, "Center");
		inputPanel.add(height, "Center");
		inputPanel.add(center, "Center");
		inputPanel.add(sideLength, "Center");
		inputPanel.add(ellipseX, "Center");
		inputPanel.add(ellipseY, "Center");
		inputPanel.add(ellipseRX, "Center");
		inputPanel.add(ellipseRY, "Center");
		center.addActionListener(this);
		sideLength.addActionListener(this);
		ellipseX.addActionListener(this);
		ellipseY.addActionListener(this);
		ellipseRX.addActionListener(this);
		ellipseRY.addActionListener(this);*/
		input1.addActionListener(this);
		input2.addActionListener(this);
		input3.addActionListener(this);
		input4.addActionListener(this);
		offset.addFocusListener(this);
		/*y.addActionListener(this);
		width.addActionListener(this);
		height.addActionListener(this);*/
		
		inputWindow.getContentPane().add(errorLabelField,"South");

		  // Set attributes of GUI objects
		  inputWindow.setTitle("SVGnest Input File Generator"); 
		  shapeButtonGroup.add(circleButton);
		  shapeButtonGroup.add(rectangleButton);
		  shapeButtonGroup.add(ellipseButton);
		  shapeButtonGroup.add(polygonButton);
		  errorLabelField.setForeground(Color.red);
		  inputWindow.setFont(new Font("default", Font.BOLD, 20));
		  
		  //Sign up for event notification with the
		  //GUI objects we want to hear from.
		  //submitButton.addActionListener(this);
		  circleButton.addActionListener(this);
		  rectangleButton.addActionListener(this);
		  ellipseButton.addActionListener(this);
		  polygonButton.addActionListener(this);

		  //Show the windows
		  inputWindow.setSize(600,400);
		  inputWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  inputWindow.setVisible(true);
		  inputWindow.setResizable(true);
		  input1.setVisible(true);
		  input2.setVisible(true);
		  input3.setVisible(true);
		  input4.setVisible(false);
		  circleButton.setSelected(true);
		  /*y.setVisible(false);
		  width.setVisible(false);
		  height.setVisible(false);
		  ellipseX.setVisible(false);
		  ellipseY.setVisible(false);
		  ellipseRX.setVisible(false);
		  ellipseRY.setVisible(false);
		  center.setVisible(false);
		  sideLength.setVisible(false);*/
		  
		  try {
				initiateFile("SVG_GUI.svg");
			} catch (IOException ioe) {
				System.out.println("initiateFile error is: " + ioe);
			}
		  
	}

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		offset.setText("");
		showingHint = false;
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		if(offset.getText().isEmpty()){
			offset.setText(hint);
			showingHint = true;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == circleButton){
			//System.out.println("circle button was pressed");
			input1.setVisible(true); // X
			input2.setVisible(true); // Y
			input3.setVisible(true); // r
			input4.setVisible(false);
			input1.setText("");
			input2.setText("");
			input3.setText("");
		}
		if(ae.getSource() == rectangleButton){
			input1.setVisible(true); // X
			input2.setVisible(true); // Y
			input3.setVisible(true);// width
			input4.setVisible(true); // height
			input1.setText("");
			input2.setText("");
			input3.setText("");
			input4.setText("");
		}
		if(ae.getSource() == ellipseButton){
			input1.setVisible(true); // X
			input2.setVisible(true); // Y
			input3.setVisible(true);// width
			input4.setVisible(true); // height
			input1.setText("");
			input2.setText("");
			input3.setText("");
			input4.setText("");
		}
		if(ae.getSource() == polygonButton){
			input1.setVisible(true); // # of sides
			input2.setVisible(true); // x
			input3.setVisible(true);// y
			input4.setVisible(true); // side length
			input1.setText("");
			input2.setText("");
			input3.setText("");
			input4.setText("");
		}
		if(((ae.getSource() == input1) || (ae.getSource() == input2) || (ae.getSource() == input3) || (ae.getSource() == input4))
				&& rectangleButton.isSelected()){ 
			// Accept condition if input is provided, rectangle button is selected
		    System.out.println("rectangle coordinate entered");
		    String rectX = input1.getText().trim();
		    String rectY = input2.getText().trim();
		    String rectWidth = input3.getText().trim();
		    String rectHeight = input4.getText().trim();
		    String offsetNumber = offset.getText().trim();
		    if((rectX == null) || (rectY == null) || (rectWidth == null) || (rectHeight == null)){
				errorLabelField.setText("All input fields must have a valid numerical value.");
				return;
			}
		    
		    try{
		    	BigDecimal xNum = new BigDecimal(rectX);
		    	xNum = xNum.multiply(DPI);
		    	BigDecimal yNum = new BigDecimal(rectY);
		    	yNum = yNum.multiply(DPI);
		    	BigDecimal widthNum = new BigDecimal(rectWidth);
		    	widthNum = widthNum.multiply(DPI);
		    	BigDecimal heightNum = new BigDecimal(rectHeight);
		    	heightNum = heightNum.multiply(DPI);
		    	if(offsetNumber != null){
		    		BigDecimal bigOffset = new BigDecimal(offsetNumber);
		    		widthNum = widthNum.multiply(bigOffset.add(DPI));
		    		heightNum = heightNum.multiply(bigOffset.add(DPI));
		    	}
		    	rectX = xNum.toString();
		    	rectY = yNum.toString();
		    	rectWidth = widthNum.toString();
		    	rectHeight = heightNum.toString();
		    }
		    catch(Exception e){
		    	System.out.println("Rectangle BigDecimal Error is: " + e.toString());
		    	errorLabelField.setText("Please enter a valid number for rectangle coordinates and offset.");
		    }
		    
		    //writeRectangle(rectX, rectY, rectWidth, rectHeight);
		    writeSVG(rectX, rectY, rectWidth, rectHeight, 1);
		}
		if(((ae.getSource() == input1) || (ae.getSource() == input2) || (ae.getSource() == input3))
				&& circleButton.isSelected()){
			// Start event if enter is pressed at any field
			System.out.println("x co-ordinate was entered");
			String x = input1.getText().trim();
			String y = input2.getText().trim();
			String r = input3.getText().trim();
			String offsetNumber = offset.getText().trim();
			if((x == null) || (y == null) || (r == null)){
				errorLabelField.setText("All input fields must have a valid numerical value.");
				return;
			}
			
			try{
				BigDecimal xNum = new BigDecimal(x);
				BigDecimal yNum = new BigDecimal(y);
				BigDecimal rNum = new BigDecimal(r);
				xNum = xNum.multiply(DPI);
				yNum = yNum.multiply(DPI);
				rNum = rNum.multiply(DPI);
				if(offsetNumber != null){
		    		BigDecimal bigOffset = new BigDecimal(offsetNumber);
		    		rNum = rNum.multiply(bigOffset.add(DPI));
		    	}
				x = xNum.toString();
				y = yNum.toString();
				r = rNum.toString();
			}
			catch(Exception e){
				System.out.println("Circle BigDecimal Error is: " + e.toString());
				errorLabelField.setText("Please enter a valid number for circle coordinates and offset.");
				return;
			}

			//writeCircle(x, y, r);
			writeSVG(x, y, r, 3);
		}
		if(((ae.getSource() == input1) || (ae.getSource() == input2) || (ae.getSource() == input3) 
				||(ae.getSource() == input4)) && polygonButton.isSelected())
		if(((ae.getSource() == input1) || (ae.getSource() == input2) || (ae.getSource() == input3) 
				||(ae.getSource() == input4)) && ellipseButton.isSelected()){
			System.out.println("Ellipse co-ordinate was entered");
			String Xellipse = input1.getText().trim();
			String Yellipse = input2.getText().trim();
			String RXellipse = input3.getText().trim();
			String RYellipse = input4.getText().trim();
			String offsetNumber = offset.getText().trim();
			if((Xellipse == null) || (Yellipse == null) || (RXellipse == null) ||  (RYellipse == null)){
				errorLabelField.setText("All input fields must have a valid numerical value.");
				return;
			}
			
			try{
				BigDecimal xNum = new BigDecimal(Xellipse);
				BigDecimal yNum = new BigDecimal(Yellipse);
				BigDecimal rxNum = new BigDecimal(RXellipse);
				BigDecimal ryNum = new BigDecimal(RYellipse);
				xNum = xNum.multiply(DPI);
				yNum = yNum.multiply(DPI);
				rxNum = rxNum.multiply(DPI);
				ryNum = ryNum.multiply(DPI);
				if(offsetNumber != null){
		    		BigDecimal bigOffset = new BigDecimal(offsetNumber);
		    		rxNum = rxNum.multiply(bigOffset.add(DPI));
		    		ryNum = ryNum.multiply(bigOffset.add(DPI));
		    	}
				Xellipse = xNum.toString();
				Yellipse = yNum.toString();
				RXellipse = rxNum.toString();
				RYellipse = ryNum.toString();
			}
			catch(Exception e){
				System.out.println("Ellipse BigDecimal Error is: " + e.toString());
				errorLabelField.setText("Please enter a valid number for ellipse coordinates and offset.");
				return;
			}

			//writeEllipse(Xellipse, Yellipse, RXellipse, RYellipse);
			writeSVG(Xellipse, Yellipse, RXellipse, RYellipse, 2);
		}
	}
	
	private void initiateFile(String file) throws IOException{ 
		// Function that creates .svg file with initial code
		File filePath = new File(sourceDir + file);
		if (filePath.createNewFile()){
          System.out.println("File is created!");
        //Write Content
          FileWriter fw = new FileWriter(filePath);
          //Buffered reader will skip lines if newLines are taken out
          fw.write("<svg version=\"1.1\" id=\"svg2\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\" width=\"400px\" height=\"300px\" viewBox=\"0 0 400 300\" enable-background=\"new 0 0 400 300\" xml:space=\"preserve\" fill=\"none\" stroke=\"#010101\">"
          		+ newLine);
          /*fw.write("<svg version=\"1.1\" id=\"svg2\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\" width=\"400px\" height=\"300px\" viewBox=\"0 0 400 300\" enable-background=\"new 0 0 400 300\" xml:space=\"preserve\">"
            		+ newLine);*/
          fw.close();
          return;
        }else{
          System.out.println("File already exists.");
          return;
        }
   	}
	
	private void writeSVG(String var1, String var2, String var3, String var4, int shape){
		/*
		 * Function: writeSVG
		 * Purpose: General function used to take input from GUI and write it to SVG file used for SVGnest input.
		 * Inputs: var1-var4 are the shape's coordinates and shape is a variable used to decide which shapes drawing
		 * 		   code should be executed. 
		 */
		String inputString = null;
		
		switch (shape){
			case 1: inputString = "<rect x=\"" + var1 + "\" y=\"" + var2 + "\" width=\"" + var3 + "\" height=\"" + var4 + "\" fill=\"none\" stroke=\"#010101\"/>" + newLine;
					break;
			
			case 2: inputString = "<ellipse cx=\"" + var1 + "\" cy=\"" + var2 + "\" rx=\"" + var3 + "\" ry=\"" + var4 + "\" fill=\"none\" stroke=\"#010101\"/>" + newLine;
					break;
			
			default: System.out.println("Invalid shape code was sent to writeSVG!"); break;
		}
		
		String oldFileName = sourceDir + "SVG_GUI.svg";
	    String tmpFileName = sourceDir + "tmp.svg";
		BufferedReader br = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		FileWriter fw = null;
		Scanner scanner = null;
		FileWriter fw2 = null;
		BufferedWriter bw2 = null;
		    
	    try{
	    	fr = new FileReader(oldFileName);
		    fw = new FileWriter(oldFileName, true);// opens file in append mode
	    	br = new BufferedReader(fr);
	        bw = new BufferedWriter(fw);
	        fw2 = new FileWriter(tmpFileName);
	        bw2 = new BufferedWriter(fw2);
	        scanner = new Scanner(oldFileName);
	        String line;
	        while((line = br.readLine()) != null){
	        	if(scanner.hasNext()){
	        		if((!line.contains("</svg>")) || !(line.contains(""))){
	        			bw2.write(line + newLine); 
	        		}
	        		else{ 
	        			break;
        			}
	        	}
	        	else break;
	        }
	        bw2.write(inputString);
		}
		catch(Exception ex){
			System.out.println("writeSVG error for shape code " + shape + "is: " + ex);
			return;
		}
	    finally {
	         try {
	            if(br != null)
	               br.close();
	         } catch (IOException ioe) {
	            System.out.println("Exception in I/O Exception in writeSVG for shape code " + shape + "is:" + ioe);
	            return;
	         }
	         try {
	            if((bw != null) || (bw2 != null))
	               bw2.write("</svg>"+newLine);
	               bw.close();
	               bw2.close();
	               scanner.close();
	         } catch (IOException ioe) {
	        	 System.out.println("Exception in I/O Exception in writeSVG for shape code " + shape + "is:" + ioe);
	        	 return;
	         }
	      }
		  // Once everything is complete, delete old file..
		  File oldFile = new File(oldFileName);
		  oldFile.delete();
		
		  // And rename tmp file's name to old file name
		  File newFile = new File(tmpFileName);
		  newFile.renameTo(oldFile);
	}
	private void writeSVG(String var1, String var2, String var3, int shape){
		/*
		 * Function: writeSVG
		 * Purpose: General function used to take input from GUI and write it to SVG file used for SVGnest input.
		 * Inputs: var1-var4 are the shape's coordinates and shape is a variable used to decide which shapes drawing
		 * 		   code should be executed.
		 */
		String inputString = null;
		
		switch (shape){
			case 3: inputString = "<circle cx=\"" + var1 + "\" cy=\"" + var2 + "\" r=\"" + var3 + "\" fill=\"none\" stroke=\"#010101\"/>"+newLine;
					break;
			
			default: System.out.println("Invalid shape code was sent to writeSVG!"); break;
		}
		
		String oldFileName = sourceDir + "SVG_GUI.svg";
	    String tmpFileName = sourceDir + "tmp.svg";
		BufferedReader br = null;
		BufferedWriter bw = null;
		FileReader fr = null;
		FileWriter fw = null;
		Scanner scanner = null;
		FileWriter fw2 = null;
		BufferedWriter bw2 = null;
	    try{
	    	fr = new FileReader(oldFileName);
		    fw = new FileWriter(oldFileName, true);// opens file in append mode
	    	br = new BufferedReader(fr);
	        bw = new BufferedWriter(fw);
	        fw2 = new FileWriter(tmpFileName);
	        bw2 = new BufferedWriter(fw2);
	        scanner = new Scanner(oldFileName);
	        String line;
	        while((line = br.readLine()) != null){
	        	if(scanner.hasNext()){
	        		if((!line.contains("</svg>")) || !(line.contains(""))){
	        			bw2.write(line + newLine); 
	        		}
	        		else{ 
	        			break;
        			}
	        	}
	        	else break;
	        }
	        bw2.write(inputString);
		}
		catch(Exception ex){
			System.out.println("writeSVG error for shape code " + shape + "is: " + ex);
			return;
		}
	    finally {
	         try {
	            if(br != null)
	               br.close();
	         } catch (IOException ioe) {
	            System.out.println("Exception in I/O Exception in writeSVG for shape code " + shape + "is:" + ioe);
	            return;
	         }
	         try {
	            if((bw != null) || (bw2 != null))
	               bw2.write("</svg>"+newLine);
	               bw.close();
	               bw2.close();
	               scanner.close();
	         } catch (IOException ioe) {
	        	 System.out.println("Exception in I/O Exception in writeSVG for shape code " + shape + "is:" + ioe);
	        	 return;
	         }
	      }
		  // Once everything is complete, delete old file..
		  File oldFile = new File(oldFileName);
		  oldFile.delete();
		
		  // And rename tmp file's name to old file name
		  File newFile = new File(tmpFileName);
		  newFile.renameTo(oldFile);
	}
}
