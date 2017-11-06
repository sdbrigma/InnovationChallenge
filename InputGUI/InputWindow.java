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


public class InputWindow implements ActionListener{
	String newLine = System.lineSeparator();
	String sourceDir = "/Users/marybiggs/SVGnest/SVGnest/";
	BigDecimal DPI = BigDecimal.valueOf(12); 
	
	JFrame       	inputWindow     = new JFrame();
	JButton 		submitButton	= new JButton("Submit");
	JPanel			topPanel		= new JPanel();
	JPanel			inputPanel		= new JPanel();
	ButtonGroup		shapeButtonGroup = new ButtonGroup();
	JRadioButton	circleButton	= new JRadioButton("Circle");
	JRadioButton	rectangleButton	= new JRadioButton("Rectangle");
	JRadioButton	triangleButton	= new JRadioButton("Triangle");
	JRadioButton	ellipseButton	= new JRadioButton("Ellipse");
	JTextField		offset	        = new JTextField("Offest");
	JTextField		circleX			= new JTextField();
	JTextField		circleY			= new JTextField();
	JTextField		radius			= new JTextField();
	JTextField		x			= new JTextField();
	JTextField		y			= new JTextField();
	JTextField		width			= new JTextField();
	JTextField		height			= new JTextField();
	JTextField		center		= new JTextField();
	JTextField		sideLength	= new JTextField();
	JTextField		ellipseX 	= new JTextField();
	JTextField		ellipseY 	= new JTextField();
	JTextField		ellipseRX 	= new JTextField();
	JTextField		ellipseRY 	= new JTextField();

	JLabel       errorLabelField  = new JLabel("error messages here =>");

	public static void main(String[] args) {
		InputWindow iw = new InputWindow();
	}
	
	public InputWindow(){
		// Build GUI
		inputWindow.getContentPane().add(topPanel, "North");
		inputWindow.getContentPane().add(inputPanel, "Center");
		topPanel.add(submitButton);
		topPanel.add(circleButton);
		topPanel.add(rectangleButton);
		//topPanel.add(triangleButton);
		topPanel.add(ellipseButton);
		topPanel.add(offset);
		circleX.setPreferredSize(new Dimension(100, 24));
		circleY.setPreferredSize(new Dimension(100, 24));
		radius.setPreferredSize(new Dimension(100, 24));
		offset.setPreferredSize(new Dimension(100, 24));
		x.setPreferredSize(new Dimension(100,24));
		y.setPreferredSize(new Dimension(100,24));
		width.setPreferredSize(new Dimension(100,24));
		height.setPreferredSize(new Dimension(100,24));
		ellipseX.setPreferredSize(new Dimension(100,24));
		ellipseY.setPreferredSize(new Dimension(100,24));
		ellipseRX.setPreferredSize(new Dimension(100,24));
		ellipseRY.setPreferredSize(new Dimension(100,24));
		center.setPreferredSize(new Dimension(100,24));
		sideLength.setPreferredSize(new Dimension(100,24));
		inputPanel.add(circleX, "Center");
		inputPanel.add(circleY, "Center");
		inputPanel.add(radius, "Center");
		inputPanel.add(x, "Center");
		inputPanel.add(y, "Center");
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
		ellipseRY.addActionListener(this);
		circleX.addActionListener(this);
		circleY.addActionListener(this);
		radius.addActionListener(this);
		offset.addActionListener(this);
		x.addActionListener(this);
		y.addActionListener(this);
		width.addActionListener(this);
		height.addActionListener(this);
		
		inputWindow.getContentPane().add(errorLabelField,"South");
		  //inputWindow.getContentPane().add(chatPane,"Center");
		  //bottomPanel.add(sendToAllButton);  // Add GUI objects in
		  //bottomPanel.add(horizontalRButton);// left-to-right
		  //bottomPanel.add(verticalRButton);  // sequence.
		  //bottomPanel.add(exitLabel);
		  //inputWindow.getContentPane().add(bottomPanel,"South");

		  //whosInWindow.getContentPane().add(clearWhosInButton,"North");
		  //whosInWindow.getContentPane().add(inScrollPane,"Center");
		  //whosInWindow.getContentPane().add(sendPrivateButton,"South");
		  //whosNotInWindow.getContentPane().add(clearWhosNotButton,"North");
		 // whosNotInWindow.getContentPane().add(notInScrollPane,"Center");
		  //whosNotInWindow.getContentPane().add(saveMessageButton,"South");

		  // Set attributes of GUI objects
		  inputWindow.setTitle("SVGnest Input File Generator"); 
		  shapeButtonGroup.add(circleButton);
		  shapeButtonGroup.add(rectangleButton);
		  shapeButtonGroup.add(ellipseButton);
		  //chatPane.setDividerLocation(200); 
		  //splitButtonGroup.add(horizontalRButton);
		  //splitButtonGroup.add(verticalRButton);
		  //clearWhosInButton.setBackground(Color.YELLOW);
		  //clearWhosNotButton.setBackground(Color.YELLOW);
		  //sendPrivateButton.setBackground(Color.green);
		  //saveMessageButton.setBackground(Color.cyan);
		  //sendToAllButton.setBackground(Color.green);
		  errorLabelField.setForeground(Color.red);
		  //exitLabel.setForeground(Color.blue);
		  
		  //outChatArea.setEditable(false);
		  //inChatArea.setFont (new Font("default",Font.BOLD,20));
		  //outChatArea.setFont(new Font("default",Font.BOLD,20));
		  inputWindow.setFont(new Font("default", Font.BOLD, 20));
		  //inChatArea.setLineWrap(true);
		  //outChatArea.setLineWrap(true);
		  //inChatArea.setWrapStyleWord(true);
		  //outChatArea.setWrapStyleWord(true);
		  
		  //Sign up for event notification with the
		  //GUI objects we want to hear from.
		  submitButton.addActionListener(this);
		  circleButton.addActionListener(this);
		  rectangleButton.addActionListener(this);
		  ellipseButton.addActionListener(this);
		  
		  /*clearWhosInButton.addActionListener(this);
		  clearWhosNotButton.addActionListener(this);
		  sendPrivateButton.addActionListener(this);
		  saveMessageButton.addActionListener(this);
		  sendToAllButton.addActionListener(this);
		  horizontalRButton.addActionListener(this);
		  verticalRButton.addActionListener(this);*/

		  //Show the windows
		  inputWindow.setSize(600,400);
		  inputWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  inputWindow.setVisible(true);
		  inputWindow.setResizable(true);
		  circleX.setVisible(false);
		  circleY.setVisible(false);
		  radius.setVisible(false);
		  x.setVisible(false);
		  y.setVisible(false);
		  width.setVisible(false);
		  height.setVisible(false);
		  ellipseX.setVisible(false);
		  ellipseY.setVisible(false);
		  ellipseRX.setVisible(false);
		  ellipseRY.setVisible(false);
		  center.setVisible(false);
		  sideLength.setVisible(false);
		  
		  try {
				initiateFile("testFile.svg");
			} catch (IOException ioe) {
				System.out.println("initiateFile error is: " + ioe);
			}
		  
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == circleButton){
			//System.out.println("circle button was pressed");
			circleX.setText("");
			circleY.setText("");
			radius.setText("");
			circleX.setVisible(true);
			circleY.setVisible(true);
			radius.setVisible(true);
			x.setVisible(false);
			y.setVisible(false);
			width.setVisible(false);
			height.setVisible(false);
			ellipseX.setVisible(false);
			ellipseY.setVisible(false);
			ellipseRX.setVisible(false);
			ellipseRY.setVisible(false);
			center.setVisible(false);
			sideLength.setVisible(false);
		}
		if(ae.getSource() == rectangleButton){
			x.setText("");
			y.setText("");
			width.setText("");
			height.setText("");
			circleX.setVisible(false);
			circleY.setVisible(false);
			radius.setVisible(false);
			x.setVisible(true);
			y.setVisible(true);
			width.setVisible(true);
			height.setVisible(true);
			ellipseX.setVisible(false);
			ellipseY.setVisible(false);
			ellipseRX.setVisible(false);
			ellipseRY.setVisible(false);
			center.setVisible(false);
			sideLength.setVisible(false);
		}
		if(ae.getSource() == ellipseButton){
			ellipseX.setText("");
			ellipseX.setText("");
			ellipseRX.setText("");
			ellipseRY.setText("");
			circleX.setVisible(false);
			circleY.setVisible(false);
			radius.setVisible(false);
			x.setVisible(false);
			y.setVisible(false);
			width.setVisible(false);
			height.setVisible(false);
			ellipseX.setVisible(true);
			ellipseY.setVisible(true);
			ellipseRX.setVisible(true);
			ellipseRY.setVisible(true);
			center.setVisible(false);
			sideLength.setVisible(false);
		}
		if(ae.getSource() == submitButton){
			//TODO: Format data for SVG file
			//Then clear all buttons and erase all data.
		}
		if((ae.getSource() == x) || (ae.getSource() == y) || (ae.getSource() == width) || (ae.getSource() == height)){
		    System.out.println("rectangle coordinate entered");
		    String rectX = x.getText().trim();
		    String rectY = y.getText().trim();
		    String rectWidth = width.getText().trim();
		    String rectHeight = height.getText().trim();
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
		    	rectX = xNum.toString();
		    	rectY = yNum.toString();
		    	rectWidth = widthNum.toString();
		    	rectHeight = heightNum.toString();
		    }
		    catch(Exception e){
		    	System.out.println("Rectangle BigDecimal Error is: " + e.toString());
		    	errorLabelField.setText("Please enter a valid number for rectangle coordinates.");
		    }
		    
		    writeRectangle(rectX, rectY, rectWidth, rectHeight);
		}
		if((ae.getSource() == circleX) || (ae.getSource() == circleY) || (ae.getSource() == radius)){
			// Start event if enter is pressed at any field
			System.out.println("x co-ordinate was entered");
			String x = circleX.getText().trim();
			String y = circleY.getText().trim();
			String r = radius.getText().trim();
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
				x = xNum.toString();
				y = yNum.toString();
				r = rNum.toString();
			}
			catch(Exception e){
				System.out.println("Circle BigDecimal Error is: " + e.toString());
				errorLabelField.setText("Please enter a valid number for circle coordinates.");
				return;
			}

			writeCircle(x, y, r);
		}
		if((ae.getSource() == ellipseX) || (ae.getSource() == ellipseY) || (ae.getSource() == ellipseRX)
																		||(ae.getSource() == ellipseRY)){
			System.out.println("Ellipse co-ordinate was entered");
			String Xellipse = ellipseX.getText().trim();
			String Yellipse = ellipseY.getText().trim();
			String RXellipse = ellipseRX.getText().trim();
			String RYellipse = ellipseRY.getText().trim();
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
				Xellipse = xNum.toString();
				Yellipse = yNum.toString();
				RXellipse = rxNum.toString();
				RYellipse = ryNum.toString();
			}
			catch(Exception e){
				System.out.println("Ellipse BigDecimal Error is: " + e.toString());
				errorLabelField.setText("Please enter a valid number for ellipse coordinates.");
				return;
			}

			writeEllipse(Xellipse, Yellipse, RXellipse, RYellipse);
		
			
		}
		if(ae.getSource() == offset){
			System.out.println("offset value entered");
			// TODO: Make case for negative offset
			try{
				BigDecimal offsetNumber = new BigDecimal(offset.getText().trim());
				offsetNumber = offsetNumber.multiply(DPI);
				
				// Check which input fields are visible to choose which offset code to run. 
				if(circleX.isVisible()){
					BigDecimal xCircle = new BigDecimal(circleX.getText().trim());
					BigDecimal YCircle = new BigDecimal(circleY.getText().trim());
					xCircle = xCircle.add(offsetNumber);
					YCircle = YCircle.add(offsetNumber);
					String tmpX = xCircle.toString();
					String tmpY = YCircle.toString();
					String tmpR = radius.getText().trim();
					writeCircle(tmpX, tmpY, tmpR);
				}
				else if(x.isVisible()){				
					BigDecimal widthRect = new BigDecimal(width.getText().trim());
					BigDecimal heightRect = new BigDecimal(height.getText().trim());
					widthRect = widthRect.add(offsetNumber);
					heightRect = heightRect.add(offsetNumber);
					String tmpWidth = widthRect.toString();
					String tmpHeight = heightRect.toString();
					writeRectangle(x.getText().trim(), y.getText().trim(), tmpWidth, tmpHeight);
				}
				else if(ellipseX.isVisible()){
					BigDecimal xEllipse = new BigDecimal(ellipseX.getText().trim());
					BigDecimal yEllipse = new BigDecimal(ellipseY.getText().trim());
					xEllipse = xEllipse.add(offsetNumber);
					yEllipse = yEllipse.add(offsetNumber);
					String tmpX = xEllipse.toString();
					String tmpY = yEllipse.toString();
					writeEllipse(tmpX, tmpY, ellipseRX.getText().trim(), ellipseRY.getText().trim());
				}
				else{  }
			}
			catch(Exception e){
				errorLabelField.setText("Please enter a valid offset number.");
				return;
			}
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
          fw.write("<svg version=\"1.1\" id=\"svg2\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\" width=\"1147.592px\" height=\"1397.27px\" viewBox=\"0 0 1147.592 1397.27\" enable-background=\"new 0 0 1147.592 1397.27\" xml:space=\"preserve\">"
          		+ newLine);
          fw.close();
          return;
        }else{
          System.out.println("File already exists.");
          return;
        }
   	}
	private void writeRectangle(String x, String y, String width, String height){
		// TODO: finish writeRectangle function
		String rectangle = newLine + "<rect x=\"" + x + "\" y=\"" + y + "\" width=\"" + width + "\" height=\"" + height + "\" fill=\"none\" stroke=\"#010101\"/>" + newLine;
		String oldFileName = sourceDir + "testFile.svg";
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
	        bw2.write(rectangle);
		}
		catch(Exception ex){
			System.out.println("writeRectangle error is: " + ex);
			return;
		}
	    finally {
	         try {
	            if(br != null)
	               br.close();
	         } catch (IOException ioe) {
	            System.out.println("Exception in writeRectangle is:" + ioe);
	            return;
	         }
	         try {
	            if((bw != null) || (bw2 != null))
	               bw2.write("</svg>"+newLine);
	               bw.close();
	               bw2.close();
	               scanner.close();
	         } catch (IOException ioe) {
	        	 System.out.println("Exception in writeRectangle is:" + ioe);
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
	private void writeEllipse(String x, String y, String rx, String ry){
		// Function writes an ellipse with given radius and center at (x, y) to testFile.svg in root.
		String circle = "<ellipse cx=\"" + x + "\" cy=\"" + y + "\" rx=\"" + rx + "\" ry=\"" + ry + "\" fill=\"none\" stroke=\"#010101\"/>"+newLine;
		String oldFileName = sourceDir + "testFile.svg";
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
	        bw2.write(circle);
		}
		catch(Exception ex){
			System.out.println("writeEllipse error is: " + ex);
			return;
		}
	    finally {
	         try {
	            if(br != null)
	               br.close();
	         } catch (IOException ioe) {
	            System.out.println("Exception in writeEllipse is:" + ioe);
	            return;
	         }
	         try {
	            if((bw != null) || (bw2 != null))
	               bw2.write("</svg>"+newLine);
	               bw.close();
	               bw2.close();
	               scanner.close();
	         } catch (IOException ioe) {
	        	 System.out.println("Exception in writeEllipse is:" + ioe);
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
	
	private void writeCircle(String cX, String cY, String radius){
		// Function writes a circle with given radius and center at (cX, cY) to testFile.svg in root.
		String circle = "<circle cx=\"" + cX + "\" cy=\"" + cY + "\" r=\"" + radius + "\" fill=\"none\" stroke=\"#010101\"/>"+newLine;
		String oldFileName = sourceDir + "testFile.svg";
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
	        bw2.write(circle);
		}
		catch(Exception ex){
			System.out.println("writeCircle error is: " + ex);
			return;
		}
	    finally {
	         try {
	            if(br != null)
	               br.close();
	         } catch (IOException ioe) {
	            System.out.println("Exception in writeCircle is:" + ioe);
	            return;
	         }
	         try {
	            if((bw != null) || (bw2 != null))
	               bw2.write("</svg>"+newLine);
	               bw.close();
	               bw2.close();
	               scanner.close();
	         } catch (IOException ioe) {
	        	 System.out.println("Exception in writeCircle is:" + ioe);
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
