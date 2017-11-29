package navair.frc.east.brigman;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;


public class InputWindow extends JApplet implements ActionListener, FocusListener{
	String newLine = System.lineSeparator();
	String sourceDir = "/Users/marybiggs/SVGnest/SVGnest/";
	final String dir = System.getProperty("user.dir");
	BigDecimal DPI = BigDecimal.valueOf(1); 
	private final String hint = "Offset";
	private boolean showingHint;
	
	JFrame       	inputWindow     = new JFrame();
	JPanel			topPanel		= new JPanel();
	JPanel			inputPanel		= new JPanel();
	ButtonGroup		shapeButtonGroup = new ButtonGroup();
	JRadioButton	circleButton	= new JRadioButton("Circle");
	JRadioButton	rectangleButton	= new JRadioButton("Rectangle");
	JRadioButton	polygonButton	= new JRadioButton("Polygon");
	JRadioButton	ellipseButton	= new JRadioButton("Ellipse");
	JTextField		offset	        = new JTextField(hint);
	JTextField		input1			= new JTextField();
	JTextField		input2			= new JTextField();
	JTextField		input3			= new JTextField();
	JTextField		input4			= new JTextField();
	JLabel       errorLabelField  = new JLabel("");

	public static void main(String[] args) {
		InputWindow iw = new InputWindow();
	}
	
	public InputWindow(){
		// Build GUI
		inputWindow.getContentPane().add(topPanel, "North");
		inputWindow.getContentPane().add(inputPanel, "Center");
		topPanel.add(circleButton);
		topPanel.add(rectangleButton);
		topPanel.add(polygonButton);
		topPanel.add(ellipseButton);
		topPanel.add(offset);
		input1.setPreferredSize(new Dimension(100, 24));
		input2.setPreferredSize(new Dimension(100, 24));
		input3.setPreferredSize(new Dimension(100, 24));
		offset.setPreferredSize(new Dimension(100, 24));
		input4.setPreferredSize(new Dimension(100,24));
		
		inputPanel.add(input1, "Center");
		inputPanel.add(input2, "Center");
		inputPanel.add(input3, "Center");
		inputPanel.add(input4, "Center");
		offset.setForeground(Color.LIGHT_GRAY);
		input1.addActionListener(this);
		input2.addActionListener(this);
		input3.addActionListener(this);
		input4.addActionListener(this);
		offset.addFocusListener(this);
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
		  //inputWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Causes error when used as JApplet
		  inputWindow.setVisible(true);
		  inputWindow.setResizable(true);
		  input1.setVisible(true);
		  input2.setVisible(true);
		  input3.setVisible(true);
		  input4.setVisible(false);
		  circleButton.setSelected(true);
		  
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
			input1.setVisible(true); // x
			input2.setVisible(true); // y
			input3.setVisible(true);// # of sides
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
				errorLabelField.setText("ERROR: All input fields must have a valid numerical value.");
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
		    	
		    	boolean offsetFlag = offsetNumber.contains("Offset");
				
				if(!offsetFlag){
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
		    	errorLabelField.setText("ERROR: Please enter a valid number for rectangle coordinates and offset.");
		    }
		    
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
				
				boolean offsetFlag = offsetNumber.contains("Offset");
				
				if(!offsetFlag){
					BigDecimal bigOffset = new BigDecimal(offsetNumber);
		    		rNum = rNum.multiply(bigOffset.add(DPI));
				}
				
				x = xNum.toString();
				y = yNum.toString();
				r = rNum.toString();
			}
			catch(Exception e){
				System.out.println("Circle BigDecimal Error is: " + e.toString());
				errorLabelField.setText("ERROR: Please enter a valid number for circle coordinates and offset.");
				return;
			}

			writeSVG(x, y, r, 3);
		}
		if(((ae.getSource() == input1) || (ae.getSource() == input2) || (ae.getSource() == input3) 
				||(ae.getSource() == input4)) && polygonButton.isSelected()){
			System.out.println("Polygon coordinates entered");
			String x = input1.getText().trim();
			String y = input2.getText().trim();
			String sideNum = input3.getText().trim();
			String sideLength = input4.getText().trim();
			String offsetNumber = offset.getText().trim();
			
			String rPoly = null;
			
			if((x == null) || (y == null) || (sideNum == null) || (sideLength == null)){
				errorLabelField.setText("ERROR: All input fields must have a valid numerical value.");
				return;
			}
			
			try{
				BigDecimal bigX = new BigDecimal(x).multiply(DPI);
				BigDecimal bigY = new BigDecimal(y).multiply(DPI);
				BigDecimal bigSideNum = new BigDecimal(sideNum);
				BigDecimal bigSideLength = new BigDecimal(sideLength);
				
				boolean offsetFlag = offsetNumber.contains("Offset");
				
				if(!offsetFlag){
					BigDecimal bigOffset = new BigDecimal(offsetNumber);
		    		bigSideLength = bigSideLength.multiply(bigOffset.add(DPI));
				}
				
				// Trig functions not available for BigDecimal
				double doubleSideLength = Double.parseDouble(sideLength);
				double doubleSideNum = Double.parseDouble(sideNum);
				
				if(doubleSideNum < 3){
					errorLabelField.setText("ERROR: Desired polygon must have at least three sides!");
					return;
				}
				double radius = doubleSideLength/(2*Math.sin(180/doubleSideNum));
				if(radius<0){
					radius = radius * -1;
				}
				
				rPoly = Double.toString(radius*DPI.doubleValue());
				x = bigX.toString();
				y = bigY.toString();
				sideNum = bigSideNum.toString();
				sideLength = bigSideLength.multiply(DPI).toString();
			}
			catch(Exception e){
				System.out.println("Polygon BigDecimal Error is: " + e.toString());
				errorLabelField.setText("ERROR: Please enter a valid number for polygon coordinates and offset.");
				return;
			}

			writeSVG(x, y, sideNum, sideLength, rPoly);
		}
		if(((ae.getSource() == input1) || (ae.getSource() == input2) || (ae.getSource() == input3) 
				||(ae.getSource() == input4)) && ellipseButton.isSelected()){
			System.out.println("Ellipse co-ordinate was entered");
			String Xellipse = input1.getText().trim();
			String Yellipse = input2.getText().trim();
			String RXellipse = input3.getText().trim();
			String RYellipse = input4.getText().trim();
			String offsetNumber = offset.getText().trim();
			if((Xellipse == null) || (Yellipse == null) || (RXellipse == null) ||  (RYellipse == null)){
				errorLabelField.setText("ERROR: All input fields must have a valid numerical value.");
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
				
				boolean offsetFlag = offsetNumber.contains("Offset");
				
				if(!offsetFlag){
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
				errorLabelField.setText("ERROR: Please enter a valid number for ellipse coordinates and offset.");
				return;
			}

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
          fw.close();
          return;
        }else{
          System.out.println("File already exists.");
          return;
        }
   	}
	
	private void writeSVG(String polyX, String polyY, String sideNum, String sideLength, String radius){
		/*
		 * Function writeSVG
		 * Purpose: General function used to write polygons to output SVG file
		 * Inputs: polyX and polyY are the center of the shape, sideNum is the number of sides, sideLength is the length of sides,
		 * 		   polyR is the radius, and shape is the predefined shape code used to choose which writeSVG function to execute. 
		 */
		int i = 0;
		double length = Double.parseDouble(sideLength);
		double doubleX = Double.parseDouble(polyX);
		double doubleY = Double.parseDouble(polyY);
		double doubleR = Double.parseDouble(radius);

		int sides = Integer.parseInt(sideNum);
		//int angle = (sides - 2) * 180; // All regular polygons are equiangular
		float angle = 360/sides; // splits unit circle into equal angles
		float tmpAngle = 0;
		//double[][] bigArray = new double[sides][2];
		double x,y;
		String coordinates = null;
		
		for(i=0; i<sides; i++){  // one full coordinate per side
			if(i==0){// First iterations doesn't matter since first point is will be at 0 degrees
				x = doubleR + doubleX;
				y = 0;
				coordinates = Double.toString(x) + ",0";
				//bigArray[0][0] = doubleR + doubleX; 
				//bigArray[0][1] = 0;
			}
			else{
				tmpAngle = angle*i; // iterates through angle by equal parts
				x = doubleR*Math.cos(tmpAngle) + doubleX;
				y = doubleR*Math.sin(tmpAngle) + doubleY;
				coordinates = coordinates + " " + Double.toString(x) + "," + Double.toString(y);
				//bigArray[i][0] = doubleR*Math.cos(tmpAngle) + doubleX;
				//bigArray[i][1] = doubleR*Math.sin(tmpAngle) + doubleY;
			}
		}
		String inputString = "<polygon points=\"" + coordinates + "\" " + "fill=\"none\" stroke=\"#010101\"/>" + newLine;
		
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
			System.out.println("writeSVG error for polygon is: " + ex);
			return;
		}
	    finally {
	         try {
	            if(br != null)
	               br.close();
	         } catch (IOException ioe) {
	            System.out.println("Exception in I/O Exception in writeSVG for polygon is: " + ioe);
	            return;
	         }
	         try {
	            if((bw != null) || (bw2 != null))
	               bw2.write("</svg>"+newLine);
	               bw.close();
	               bw2.close();
	               scanner.close();
	         } catch (IOException ioe) {
	        	 System.out.println("Exception in I/O Exception in writeSVG for polygon is: " + ioe);
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
	
	void writeToFile(){
		//TODO: Replace repetitive File Writer code with this function
	}
	
}
