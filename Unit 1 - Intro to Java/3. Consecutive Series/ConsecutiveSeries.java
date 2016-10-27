// The "ConsecutiveSeries" class.
import java.awt.*;
import hsa.Console;

//**********************************************************************
// Author: Jackie Xu
// Date: 9/12/2015
// Purpose: To familiarize Java's syntax, mainly loops for this one
// Inputs: Two positive integers from user, 'Y' key to replay, any other key to quit
// Output: All the possible consecutive integer sums for the given range
//**********************************************************************

public class ConsecutiveSeries
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ("Consecutive Series Calculator");
	int startSum = 0;
	int endSum = 0;

	double startCount = 0;
	double endCount = 0;

	int total = 0;
	char again = 'Y';

	c.println ("Welcome to the Consecutive Series calculator!");
	c.println ("=============================================");
	c.println ();

	while (again == 'Y' || again == 'y')
	{
	    c.println ("Please enter your first number: ");
	    startSum = c.readInt ();

	    while (startSum < 0)
	    {
		c.println ();
		c.println ("Invalid! Please re-enter a POSITIVE integer: ");
		startSum = c.readInt ();
	    }

	    c.println ();
	    c.println ("Please enter youe second number: ");
	    endSum = c.readInt ();

	    while (endSum < 0)
	    {
		c.println ("Invalid! Please re-enter a POSITIVE integer: ");
		endSum = c.readInt ();
	    }

	    if (startSum > endSum)
	    {
		int temp = startSum;
		startSum = endSum;
		endSum = temp;
	    }

	    c.println ();
	    for (startSum = startSum ; startSum <= endSum ; startSum++)
	    {
		c.print (startSum + ": ");
		for (startCount = 1 ; endCount < startSum - 1 ; startCount++)
		{
		    endCount = (int) (Math.sqrt (2 * startSum + startCount * startCount - startCount + 0.25) - 0.5);
		    if (((startCount + endCount) * (endCount - startCount + 1) / 2) == startSum)
		    {
			total = total + 1;
			c.print ((int) startCount + "-" + (int) endCount + " ");
		    }
		}
		c.print ("(" + total + ")");
		c.println ();
		total = 0;
	    }
	    c.println ();

	    startCount = 0;
	    endCount = 0;
	    total = 0;

	    c.println ("Press 'Y' to start again, or any other key to exit");
	    again = c.getChar ();
	    c.println ("**************************************************");
	    c.println ();
	}
	c.close ();
    }
}
