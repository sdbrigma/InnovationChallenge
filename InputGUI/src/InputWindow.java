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
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;

public class InputWindow implements ActionListener{
	String newLine = System.lineSeparator();
	String sourceDir = "/Users/marybiggs/SVGnest/SVGnest/";
	
	JFrame       	inputWindow     = new JFrame();
	JButton 		submitButton	= new JButton("Submit");
	JPanel			topPanel		= new JPanel();
	JPanel			inputPanel		= new JPanel();
	ButtonGroup		shapeButtonGroup = new ButtonGroup();
	JRadioButton	circleButton	= new JRadioButton("Circle");
	JRadioButton	triangleButton	= new JRadioButton("Triangle");
	JRadioButton	rectangleButton	= new JRadioButton("Rectangle");
	JTextField		circleX			= new JTextField();
	JTextField		circleY			= new JTextField();
	JTextField		radius			= new JTextField();
	JLabel       errorLabelField  = new JLabel("error messages here =>");
	//JLabel       exitLabel        = new JLabel("Close window to LEAVE the Chat Room");
	//JButton      sendToAllButton  = new JButton("Send To All");     
	//JRadioButton horizontalRButton= new JRadioButton("Horizontal Split",true);//initially selected     
	//JRadioButton verticalRButton  = new JRadioButton("Vertical Split");     
	/*ButtonGroup  splitButtonGroup = new ButtonGroup();      
	JTextArea    inChatArea       = new JTextArea("(enter chat here)");
	JTextArea    outChatArea      = new JTextArea();
	JScrollPane  inChatScrollPane = new JScrollPane(inChatArea);
	JScrollPane  outChatScrollPane= new JScrollPane(outChatArea);
	JSplitPane   chatPane         = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
	                                    inChatScrollPane, outChatScrollPane);*/
	//JPanel       bottomPanel      = new JPanel();

	// Who Windows
	//JFrame whosInWindow        = new JFrame("Who's In");
	//JFrame whosNotInWindow     = new JFrame("Who's NOT In");
	//JList<String>whosInList    = new JList<String>();
	//JList<String>whosNotInList = new JList<String>();
	//JScrollPane inScrollPane   = new JScrollPane(whosInList);
	//JScrollPane notInScrollPane= new JScrollPane(whosNotInList);
	/*JButton sendPrivateButton  = new JButton("Send Private To");
	JButton saveMessageButton  = new JButton("Save Message For");
	JButton clearWhosInButton  = new JButton("CLEAR SELECTIONS");
	JButton clearWhosNotButton = new JButton("CLEAR SELECTIONS");*/

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InputWindow iw = new InputWindow();

	}
	
	public InputWindow(){
		// Build GUI
		inputWindow.getContentPane().add(topPanel, "North");
		inputWindow.getContentPane().add(inputPanel, "Center");
		inputWindow.setAlwaysOnTop(true);
		topPanel.add(submitButton);
		topPanel.add(circleButton);
		topPanel.add(rectangleButton);
		topPanel.add(triangleButton);
		circleX.setPreferredSize(new Dimension(100, 24));
		circleY.setPreferredSize(new Dimension(100, 24));
		radius.setPreferredSize(new Dimension(100, 24));
		inputPanel.add(circleX, "Center");
		inputPanel.add(circleY, "Center");
		inputPanel.add(radius, "Center");
		circleX.addActionListener(this);
		
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
		  shapeButtonGroup.add(triangleButton);
		  shapeButtonGroup.add(rectangleButton);
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
		  triangleButton.addActionListener(this);
		  rectangleButton.addActionListener(this);
		  
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
		  
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if(ae.getSource() == circleButton){
			//System.out.println("circle button was pressed");
			circleX.setVisible(true);
			circleY.setVisible(true);
			radius.setVisible(true);
		}
		if(ae.getSource() == rectangleButton){
			
		}
		if(ae.getSource() == triangleButton){
			
		}
		if(ae.getSource() == submitButton){
			//TODO: Format data for SVG file
			//Then clear all buttons and erase all data.
		}
		if(ae.getSource() == circleX){
			//System.out.println("x co-ordinate was entered");
			/*if((circleX.getText() == null) || (circleY.getText() == null) 
										   || (radius.getText() == null)){
				errorLabelField.setText("All input fields must have a valid numerical value.");
				return;
			}*/
			//BigDecimal xCoord = new BigDecimal(circleX.getText());
			try {
				createFile(circleX.getText(), (sourceDir + "testFile.svg"));
			} catch (IOException ioe) {
				System.out.println("createFile error is: " + ioe);
			}
		}
	}
	
	private static void createFile(String data, String file) throws IOException 
    {
        	FileOutputStream out = new FileOutputStream(file);
        	out.write(data.getBytes());
            out.close();
    }

}
