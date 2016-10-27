// The "DimondsEnriched" class.
import java.awt.*;
import hsa.Console;

//*****************************************
// Author: Jackie Xu
// Date: 9/11/2015
// Purpose: To familiarize Java's syntax as well as commands
// Inputs: Positive integer between 1 and 25 from the user
// Output: Dimond shaped stars based on the size the user entered
//*****************************************

public class DimondsEnriched
{
    static Console c; 
    
    public static void main (String[] args)
    {
	c = new Console ();
	int size = 0;
	int count = 0;
	int count2 = 0;
	int count3 = 0;
	int lineNum = 0;
	int lineNumPerm = 0;
	int spaceCount = -1;
	int sizePerm = 0;
	
	char again = 'Y';
	char type = 'F';
	char shape = 'D';
	
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
	    c.println("Please choose a shape: 'H' for hourglass, any other key for dimond: ");
	    shape = c.getChar();
	    
	    c.println();
	    c.println("Please choose the type, 'H' for hollow, any other key for filled: ");
	    type = c.getChar();
	    c.println();
	    
	    
	    if (shape == 'H' || shape == 'h')
	    {
		for (count = 0; count < size; count++)
		    c.print("*");
		size = size - 2;
		sizePerm = size;
	    }
	    else
	    {   
		c.print(" ");
		for (count = 0; count < size; count++)
		    c.print("*");
	    }
	    c.println();
	    
	    
	    lineNum = (int)(0.5 * (float) size + 1.5);
	    lineNumPerm = lineNum;
	    count = 0;


	    
	    for (lineNum = lineNum; lineNum >= 1; lineNum--)
	    
	    {   
		spaceCount = spaceCount + 1;
		for (count = 0; count < spaceCount; count++)
		    c.print(" ");
		
		
		
		if (type == 'H' || type == 'h')
		{    for (count = 0; count < size + 2;count++)
		    {   if (count == 0 || count > size)
			    c.print("*");
			 else
			    c.print(" ");
		    }  
		}
		else
		{
		    for (count = 0; count < size + 2;count++)
			c.print("*");
		}
		
		size = size - 2;
		c.println();
	    }   

	    if (shape == 'H' || shape == 'h')
	    {
		size = 1;
		for (count = 0; count < lineNumPerm; count++)
		{
				   
		    for (count2 = 0; count2 < spaceCount; count2++)
			c.print(" ");
			
		    for (count3 = 0; count3 < size; count3++)
			if (type == 'H' || type == 'h')
			{    
			    if (count3 == 0 || count3 > size - 2)
				c.print("*");
			    else
				c.print(" ");
			}
			else
			    c.print("*");
			    
		    c.println();
		    size = size + 2;
		    spaceCount = spaceCount - 1;
		}
	    for (count = 0; count < size - 2; count++)
		c.print("*");
	    }   
	    
	    c.println();   
	    c.println("Press 'Y' to start again, or any other key to exit");
	    again = c.getChar();
	    c.println();
	    c.println("*********************************************");
	    c.println();
	}
	c.close(); 
	
      
    } 
}
