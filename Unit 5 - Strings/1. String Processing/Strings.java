// The "Strings" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 10/23/2015
// Purpose: To familiarize the concepts and functions of strings
//***************************************************************

public class Strings
{
    static Console c;
    
    public static void main (String[] args)
    {
	c = new Console ();
	char again = 'Y';       
	String phrase;
	String oldText;
	String newText;
	
	c.println("Welcome to simple Strings! Here you can replace text and check for permutations.");
	c.println("================================================================================");
	c.println();
	while (again == 'Y' || again == 'y')
	{
	    c.print("Please enter a phrase for string testing: ");
	    phrase = c.readLine();
	    c.println("Your phrase is: " + phrase);
	    
	    c.println();
	    c.print("please enter keywords in the string to be replaced: ");
	    oldText = c.readLine();
	    c.print("please enter keywords to replace with: ");
	    newText = c.readLine();
	    
	    c.println();
	    phrase = replace(phrase,oldText,newText);
	    c.println("The new phrase is: " + phrase);
	    c.println("-----------------------------");
	    c.println();
	    c.println("Permutation checker-----");
	    c.println();
	    c.print("Please enter a phrase: ");
	    oldText = c.readLine();
	    c.print("Please enter another phrase to be compared: ");
	    newText = c.readLine();
	    c.println();
	    c.print("Are the two strings permutations of each other? ");
	    if (permuted(oldText,newText))
		c.println("Yes");
	    else
		c.println("No");
	    c.println();
	    c.println("Press 'Y' to start again, or any other key to exit...");
	    again = c.getChar();
	    c.clear();
	}
	c.close();
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/27/2015
    // Purpose: Replaces all occurrences of the target text to a new text
    // Parameters: phrase, target text, new text (String)
    // Return/Output: result (String)
    //***************************************************************
    
    public static String replace (String phrase, String oldText, String newText)
    {
	StringBuffer result = new StringBuffer();
	String real = phrase;
	int index = 0;
	
	if (!oldText.equals(""))
	{
	    while (phrase.indexOf(oldText) != -1)
	    {
		index = phrase.indexOf(oldText);
		result.append(phrase.substring(0,index));
		phrase = phrase.substring(index + oldText.length());
		result.append(newText);
	    }
	    result.append(phrase);       
	
	    real = result.toString();
	}
	return real;
    }
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/27/2015
    // Purpose: determines if two given strings are permutations of each other
    // Parameters: two string inputs
    // Return/Output: true/false
    //***************************************************************
    
    public static boolean permuted (String oldText, String newText)
    {
	boolean result = false;
	if (oldText.length() == newText.length())
	{
	    String [] first = oldText.split("");
	    java.util.Arrays.sort(first);
	    
	    
	    String [] second = newText.split("");
	    java.util.Arrays.sort(second);
	    
	    int ok = 0;
	    for (int count = 0; count <= oldText.length(); count++)
	    {
		if (first[count].equals(second[count]))
		    ok++;
	    }
	    
	    if (ok == oldText.length() + 1)
		result = true;

	}   
	return result;
    }
  
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/9/2015
    // Purpose: Converts the array into printable string along with its size
    //      Very useful method for debugging
    // Parameters: -
    // Return/Output: String output
    //***************************************************************  
    
    public static String arrayToString (String [] input)
    {
	String result = "";
	int length = input.length;

	for (int count = 0 ; count < length ; count++)
	    if (count != 0)
		result = result + "," + input [count];
	    else
		result = result + input [count];
	return "[" + result + "]" + " (" + input.length + ")";
    }

}
