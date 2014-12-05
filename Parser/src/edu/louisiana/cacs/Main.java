package edu.louisiana.cacs;

import java.io.FileNotFoundException;
import java.io.IOException;

import edu.louisiana.cacs.csce450GProject.Parser;

public class Main{
    public static void main(String[] args) throws FileNotFoundException, IOException{
        System.out.println("Hello World from Main");
        Parser myParser = new Parser("C:\\Users\\hrushith\\450 Java\\data\\sample.txt");
        myParser.parse();
    }
}