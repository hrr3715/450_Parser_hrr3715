package edu.louisiana.cacs.csce450GProject;
import Toyscanner.scan;

	import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

	import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

	public class ParseTable {
		
		static LinkedList<String> list = new LinkedList<String>();
	  public static String createParseTable(String ind1, int ind2) {
	    JFrame f = new JFrame("JTable Sample");
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container content = f.getContentPane();
	    ArrayList action_1 = new ArrayList(Arrays.asList( "id", "+", "*", "(", ")", "$", "E", "T", "F"));
	    
	    //{ "id", "+", "*", "(", ")", "$", "E", "T", "F" }; 
	   //int a= action_1.indexOf("id");
	    Object values[][] = 
	    	{ 
	    		{ "S5", null, null, "S4", null, null, "1", "2", "3" },
	    		{ null, "S6", null, null, null, "accept", null, null, null },
	    		{ null, "R2", "S7", null, "R2", "R2", null, null, null },
	    		{ null, "R4", "R4", null, "R4", "R4", null, null, null },
	    		{ "S5", null, null, "S4", null, null, "8", "2", "3" },
	    		{ null, "R6", "R6", null, "R6", "R6", null, null, null },
	    		{ "S5", null, null, "S4", null, null, null, "9", "3" },
	    		{ "S5", null, null, "S4", null, null, null, null, "10" },
	    		{ null, "S6", null, null, "S11", null, null, null, null },
	    		{ null, "R1", "S7", null, "R1", "R1", null, null, null },
	    		{ null, "R3", "R3", null, "R3", "R3", null, null, null },
	    		{ null, "R5", "R5", null, "R5", "R5", null, null, null },
	    	 };
	  
	    Object columns[] = { "id", "+", "*", "(", ")", "$", "E", "T", "F" };
	    JTable table = new JTable(values, columns);
	    JScrollPane scrollPane = new JScrollPane(table);
	    content.add(scrollPane, BorderLayout.CENTER);
	    f.setSize(300, 200);
	    //f.setVisible(true);
	    return (String) (values[ind2][action_1.indexOf(ind1)]);
	  }
	}