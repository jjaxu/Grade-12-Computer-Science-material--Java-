// The "UpsideDown" class.
import java.awt.*;
import hsa.Console;

//*****************************************
// Author: Jackie Xu
// Date: 9/13/2015
// Purpose: To take a challenging using Java after getting to know the syntax (Enrichment)
// Inputs: Two positive integers, Restart key
// Output: numbers within the given range that resembles themselves upside down
//*****************************************

public class UpsideDown
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ("Upside Down Numbers");
	int startNum = 0;
	int endNum = 0;
	int flip = 0;
	int temp = 0;
	int ok = 0;
	int digitOrg = 0;
	int digitFlip = 0;
	int totalDigit = 0;
	char again = 'Y';

	c.println ("Welcome to Upside down!");
	c.println ("=======================");
	c.println ();

	while (again == 'Y' || again == 'y')
	{
	    c.println ("Please enter your first positive integer: ");
	    startNum = c.readInt ();

	    while (startNum < 0)
	    {
		c.println ();
		c.println ("Invalid! Please re-enter a POSITIVE integer: ");
		startNum = c.readInt ();
	    }

	    c.println ();
	    c.println ("Please enter your second positive integer: ");
	    endNum = c.readInt ();

	    while (endNum < 0)
	    {
		c.println ();
		c.println ("Invalid! Please re-enter a POSITIVE integer: ");
		endNum = c.readInt ();
	    }

	    if (startNum > endNum)
	    {
		int tempVal = startNum;
		startNum = endNum;
		endNum = tempVal;
	    }
	    c.println ();
	    c.print ("List of numbers in the given range that resembles themselves upside down: ");
	    c.println ();

	    for (startNum = startNum ; startNum <= endNum ; startNum++)
	    {
		temp = startNum;
		flip = 0;
		ok = 0;
		totalDigit = 0;

		while (10 * temp / 10 != 0)
		{
		    flip = flip * 10 + temp % 10;
		    temp = temp / 10;
		    totalDigit++;
		}
		temp = startNum;

		while (10 * temp / 10 != 0)
		{
		    digitOrg = temp % 10;
		    digitFlip = flip % 10;
		    temp = temp / 10;
		    flip = flip / 10;

		    if (digitFlip == 0 || digitFlip == 1 || digitFlip == 8 || digitFlip == 6 || digitFlip == 9)
		    {
			if (digitFlip == 6)
			{
			    if (digitOrg == 9)
			    {
				ok++;
			    }

			}
			else if (digitFlip == 9)
			{
			    if (digitOrg == 6)
			    {
				ok++;
			    }
			}
			else
			{
			    if (digitFlip == digitOrg)
			    {
				ok++;
			    }
			}
		    }

		}

		if (ok == totalDigit)
		    c.println (startNum);
	    }
	    c.println ();
	    c.println ("Press 'Y' to start again, or any other key to exit");
	    again = c.getChar ();
	    c.println ("**************************************************");
	    c.println ();
	}
	c.close ();
    }
}
