// The "MyFirstJavaProgram" class.
import java.awt.*;
import hsa.Console;

public class MyFirstJavaProgram
{
    static Console c;           // The output console

    public static void main (String[] args)
    {
	// Place your program here.  'c' is the output console
	int a = 3;
	float b = 9;
	c = new Console ();

	c.println ("value of old a is: " + a);   // This is the "print" command
	c.println ("Value of old b is: " + b);
	c.println ("");
	
	c.println ("Please now enter a new value for a: ");
	a = c.readInt();             // This is the "input" command
	c.println("New value of a is: " + a);
	c.println("");
	
	c.println ("Please now enter a new value for b: ");
	b = c.readFloat();
	c.println("New value of b is: " + b);
	c.println("");
	
	if (a > b)
	    c.println ("A is larger than B.");
	else if (a < b)
	    c.println ("B is larger than A.");
	else
	    c.println ("A and B are equal.");

	
    } // main method
} // MyFirstJavaProgram class
