// The "Digitsum" class.
import java.awt.*;
import hsa.Console;

//*****************************************
// Author: Jackie Xu
// Date: 9/10/2015
// Purpose: To familiarize Java's syntax
// Inputs: Positive integer from user, 'Y' key to replay, any other key to quit
// Output: The Digital Sum of the user's integer
//*****************************************

public class Digitsum
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ("Digit Sum");
	int num = 0;
	char again = 'Y';

	c.println ("Welcome to the Digital Sum Calculator!");
	c.println ("======================================");
	c.println ();

	while (again == 'Y' || again == 'y')
	{
	    c.println ("Please enter a positive integer: ");
	    num = c.readInt ();

	    while (num < 0)
	    {
		c.println ();
		c.println ("Invalid! Please re-enter a POSITIVE integer: ");
		num = c.readInt ();
	    }

	    do
	    {
		num = num / 10 + num % 10;
	    }
	    while (num > 9);

	    c.println ("The digital sum is: " + num);
	    c.println ();

	    c.println ("Press 'Y' to start again, or any other key to exit: ");
	    again = c.getChar ();
	    c.println ("***************************************************");
	    c.println ();
	}
	c.close ();
    }
}

