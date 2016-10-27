// The "Test" class.
import java.awt.*;
import hsa.Console;

public class Test
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	double fact = 0;
	//  c.setColor (Color.blue);
	//  c.fillRect (0, 0, 100, 100);
	c.println ("LOOOL");
	print ("");
	fact = factorial (5);
    }


    public static void print (String x)
    {
	c.println (x);
    }


    public double factorial (int x)
    {
	if (x > 0)
	{
	    return x * factorial (x - 1);
	}
	else
	{
	    return 1;
	}

    }
}

