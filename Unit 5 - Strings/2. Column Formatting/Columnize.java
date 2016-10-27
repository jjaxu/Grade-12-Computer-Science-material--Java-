// The "Columnize" class.
import java.awt.*;
import hsa.*;
import java.util.*;

//***************************************************************
// Author: Jackie Xu
// Date: 10/31/2015
// Purpose: To learn how to format text with String methods, as
//      well as learning how to read/write to files
//***************************************************************

public class Columnize
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	String fileName;
	String word, whole, line;

	int count = 0;
	int col = 2;
	int countTwo = 0;
	int width = 0;
	char type = 'f';
	StringTokenizer all;
	String[] lines;

	whole = "";
	line = "";

	c.print ("Please enter the input file name: ");
	fileName = c.readLine ();
	TextInputFile input;
	input = new TextInputFile (fileName);

	while (!input.eof ())
	{
	    whole = whole + input.readLine ();
	}
	whole = whole.trim ();
	count = 0;

	c.print ("Please enter the width of columns (20 - 50, bad inputs will be defaulted to 35): ");
	width = c.readInt ();
	if (width < 20 || width > 50)
	    width = 35;
	c.print ("Please enter justification type ('l' for left, 'c' for centre, " +
		"'r' for right, 'f' for full, \nbad inputs will be defaulted to left): ");
	type = c.getChar ();
	type = Character.toLowerCase (type);
	
	c.println();
	c.print("Please enter number of columns: ");
	col = c.readInt();
	while (col <= 0)
	{   
	    c.println();
	    c.println("Invalid input! number of columns cannot be 0 or negative!");
	    c.print("Please re-enter the number of columns: ");
	    col = c.readInt();
	}
	
	all = new StringTokenizer (whole);
	input.close ();

	while (all.hasMoreTokens ())
	{
	    word = all.nextToken ();

	    if (line.length () + word.length () <= width)
	    {
		line = line + word + " ";
	    }
	    else
	    {
		countTwo++;
		line = "";
		line = line + word + " ";
	    }
	}

	all = new StringTokenizer (whole);
	lines = new String [countTwo + 1];
	line = "";

	while (all.hasMoreTokens ())
	{
	    word = all.nextToken ();

	    if (line.length () + word.length () <= width)
	    {
		line = line + word + " ";
	    }
	    else
	    {
		lines [count] = line.trim ();
		line = "";
		line = line + word + " ";
		count++;
	    }
	}
	lines [count] = line;

	lines = justify (lines, width, type);
	columnize (lines, col);
	c.println ("File has been successfully written.");


    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/2/2015
    // Purpose: justify the given array of lines to the given type and width
    // Parameters: String array, width (integer), type (char)
    // Return/Output: string array (with the justified text)
    //***************************************************************

    public static String[] justify (String[] inputLines, int width, char type)
    {
	boolean stop = false;
	int countTwo = 0;
	int spaceIndex = 0;

	if (width < 20 || width > 50)
	    width = 35;

	if (type == 'r')
	{
	    for (int count = 0 ; count < inputLines.length ; count++)
	    {
		inputLines [count] = inputLines [count].trim ();
		while (inputLines [count].length () != width)
		{

		    inputLines [count] = " " + inputLines [count];
		}
	    }
	}

	else if (type == 'f')
	{
	    for (int count = 0 ; count < inputLines.length ; count++)
	    {

		while (inputLines [count].length () < width && stop == false)
		{
		    
		    inputLines [count] = inputLines [count].trim ();

		    spaceIndex = inputLines [count].indexOf (" ", countTwo);

		    if (inputLines [count].indexOf (" ") == -1)
		    {
			stop = true;
			while (inputLines [count].length () != width)
			{
			    inputLines [count] = inputLines [count] + " ";
			}
		    }
		    else
		    {
			inputLines [count] = inputLines [count].substring (0, spaceIndex + 1) + " " + inputLines [count].substring (spaceIndex + 1);
			countTwo = spaceIndex + 2;

			if (inputLines [count].indexOf (" ", countTwo) == -1)
			    countTwo = 0;
		    }

		}
		stop = false;

	    }

	}

	else if (type == 'c')
	{
	    for (int count = 0 ; count < inputLines.length ; count++)
	    {
		countTwo = 0;
		inputLines [count] = inputLines [count].trim ();
		while (inputLines [count].length () != width)
		{
		    if (countTwo % 2 == 0)
			inputLines [count] = " " + inputLines [count];
		    else
			inputLines [count] = inputLines [count] + " ";
		    countTwo++;
		}

	    }
	}
	else
	    for (int count = 0 ; count < inputLines.length ; count++)
	    {
		inputLines [count] = inputLines [count].trim ();
		while (inputLines [count].length () != width)
		{
		    inputLines [count] = inputLines [count] + " ";
		}
	    }

	return inputLines;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/4/2015
    // Purpose: Columnizes the justified text
    // Parameters: number of columns
    // Return/Output: string array (columnized text)
    //***************************************************************

    public static void columnize (String[] justifiedLines, int numOfCol)
    {
	int cutOff = justifiedLines.length;
	String fileName;
	if (numOfCol == 2)
	{
	    cutOff = (justifiedLines.length + 1) / 2;

	    for (int count = cutOff ; count < justifiedLines.length ; count++)
	    {
		justifiedLines [count - cutOff] = justifiedLines [count - cutOff] + "     " + justifiedLines [count];

	    }

	}
	else if (numOfCol != 1)
	{
	    String current = "";
	    String [] output = new String [1];
	    int position = 0;
	    int offset = justifiedLines.length / numOfCol;
	
	    if (justifiedLines.length % numOfCol != 0)
	    {
		offset = offset + 1;
	    }

	    for (int i = 0; i < offset; i++)
	    {
		current = justifiedLines[i];
		position = i + offset; 
		while (position < justifiedLines.length)
		{
		    current = current + "     " + justifiedLines[position];
		    position = position + offset;
		}
		output[i] = current ;
		output = increaseSize(output);
		current = "";
	    
	    }
	    justifiedLines = new String [output.length];
	    justifiedLines = output;
	    cutOff = justifiedLines.length - 1;
	}
	
	System.out.println ("Preview...");
	System.out.println ();
	
	for (int count = 0 ; count < cutOff ; count++)
	{
	    System.out.println (justifiedLines [count]);
	}
	c.println ();
	c.println ();
	c.print ("Preview is displayed, Please enter the output file name: ");
	fileName = c.readLine ();
	TextOutputFile output;
	output = new TextOutputFile (fileName);

	for (int count = 0 ; count < cutOff ; count++)
	{
	    output.println (justifiedLines [count]);
	}
	output.close ();

    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/5/2015
    // Purpose: increases array size by 1
    // Parameters: original string array
    // Return/Output: string array, larger by 1 partition
    //***************************************************************
    
    public static String [] increaseSize(String [] input)
    {       
	String [] output = new String [input.length + 1];
	for (int count = 0; count < input.length; count++)
	{
	    output[count] = input[count];
	}
	return output;
    }

}
