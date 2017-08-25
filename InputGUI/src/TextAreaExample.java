import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class TextAreaExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    JFrame f = new JFrame("Text Area Examples");
	    JPanel upperPanel = new JPanel();
	    JPanel lowerPanel = new JPanel();
	    f.getContentPane().add(upperPanel, "North");
	    f.getContentPane().add(lowerPanel, "South");

	    upperPanel.add(new JTextArea(content));
	    upperPanel.add(new JTextArea(content, 6, 10));
	    upperPanel.add(new JTextArea(content, 3, 8));

	    lowerPanel.add(new JScrollPane(new JTextArea(content, 6, 8)));
	    JTextArea ta = new JTextArea(content, 6, 8);
	    ta.setLineWrap(true);
	    //lowerPanel.add(new JScrollPane(ta));

	    ta = new JTextArea(content, 6, 8);
	    //ta.setLineWrap(true);
	    //ta.setWrapStyleWord(true);
	    //lowerPanel.add(new JScrollPane(ta));

	    f.pack();
	    f.setVisible(true);
	  }

	  static String content = "Here men from the planet Earth\n"
	      + "first set foot upon the Moon,\n" + "July 1969, AD.\n"
	      + "We came in peace for all mankind.";

	}


