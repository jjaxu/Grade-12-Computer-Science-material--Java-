// The "Dimonds" class.
import java.awt.*;
import hsa.Console;

//*****************************************
// Author: Jackie Xu
// Date: 9/11/2015
// Purpose: To familiarize Java's syntax as well as commands
// Inputs: Positive integer between 1 and 25 from the user
// Output: Dimond shaped stars based on the size the user entered
//*****************************************

public class Dimonds
{
    static Console c;       // The output console
    
    public static void main (String[] args)
    {
	c = new Console ();
	int size = 0;
	int count = 0;
	int lineNum = 0;
	int MAX = 0;
	int spaceCount = -1;
	char again = 'Y';
	
	c.println("Welcome to the program of Shapes!");
	c.println("=================================");
	c.println();
	
	while (again == 'Y' || again == 'y')
	{   
	    spaceCount = -1;
	    again = 'N';
	    
	    c.println ("Please enter an odd integer between 1 and 25: ");
	    size = c.readByte();
		
	    while (size <= 0 || size >= 26 || (size % 2 == 0))
	    {
		c.println();
		c.println ("Invalid! Please re-enter an ODD integer between 1 and 25: ");
		size = c.readInt();
	    }
	    
	    c.println();
	    c.print(" ");
	    MAX = size + 2;
	    
	    for (count = 0; count < size; count++)
		c.print("*");
	    c.println();
	    
	    
	    lineNum = (int)(0.5 * (float) size + 1.5);
	    count = 0;
	    for (lineNum = lineNum; lineNum >= 1; lineNum--)
	    
	    {   
		spaceCount = spaceCount + 1;
		for (count = 0; count < spaceCount; count++)
		    c.print(" ");
		    
		for (count = 0; count < size + 2;count++)
		    c.print("*");
		size = size - 2;
		c.println();
	    }   
	    c.println("Press 'Y' to start again, or any other key to exit");
	    again = c.getChar();
	    c.println();
	    c.println("*********************************");
	}
	c.close(); 
	
	// Place your program here.  'c' is the output console
    } // main method
} // Dimonds class
