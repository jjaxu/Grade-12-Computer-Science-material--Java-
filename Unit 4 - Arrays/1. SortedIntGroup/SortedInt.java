// The "SortedInt" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 10/8/2015
// Purpose: To familiarize with arrays
// Methods: main
//***************************************************************

public class SortedInt
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	int size = 0;
	c.println("SortedIntGroup Array exercise------");
	c.println();
	char again = 'Y';
	
	while (again == 'Y' || again == 'y')
	{
	    c.print("Please enter the size of your first array: ");
	    size = c.readInt();
	    
	    while (size < 0)
	    {
		c.println("Invalid size! It cannot be negative!");
		c.print("Please re-enter the size of your first array: ");
		size = c.readInt();
	    }
	    c.println();
	    if (size != 0)
		c.println("You may now enter its values in ascending order");
		
	    int[] arrayOne = new int [size];
	    for (int count = 0; count < size; count++)
	    {
		c.print("Please enter a value for position " + (count + 1) + ": ");
		arrayOne[count] = c.readInt();
		if (count != 0)
		{
		    while (arrayOne [count] < arrayOne [count - 1])
		    {
			c.println("The input must be greater or equal to the previous input!");
			c.print("Please enter a value for position " + (count + 1) + ": ");
			arrayOne[count] = c.readInt();
		    }
		}
	    }
	    
	    c.println();
	    c.print("Please enter the size of your second array: ");
	    size = c.readInt();
	    
	    while (size < 0)
	    {
		c.println("Invalid size! It cannot be negative!");
		c.print("Please re-enter the size of your second array: ");
		size = c.readInt();
	    }
	    c.println();
	    
	    if (size != 0)
		c.println("You may now enter its values in ascending order");

	    int[] arrayTwo = new int [size];
	    for (int count = 0; count < size; count++)
	    {
		c.print("Please enter a value for position " + (count + 1) + ": ");
		arrayTwo[count] = c.readInt();
		if (count != 0)
		{
		    while (arrayTwo [count] < arrayTwo [count - 1])
		    {
			c.println("The input must be greater or equal to the previous input!");
			c.print("Please enter a value for position " + (count + 1) + ": ");
			arrayTwo[count] = c.readInt();
		    }
		}
	    }


	    SortedIntGroup testIntGroup = new SortedIntGroup (arrayOne);
	    SortedIntGroup testIntGroup2 = new SortedIntGroup (arrayTwo);
	    c.println();

	    c.println ("First array: " + testIntGroup.toString ());
	    c.println ("Second array: " + testIntGroup2.toString ());
	    c.println();
	    
	    SortedIntGroup mergedIntGroup = new SortedIntGroup (testIntGroup.merge (testIntGroup2).intArray);
	    c.println ("Merged array: " + mergedIntGroup);
	    c.println ("Start of longest run: " + mergedIntGroup.longestRun ().start);
	    c.println ("Length of longest run: " + mergedIntGroup.longestRun ().length);
	    mergedIntGroup.dropDups();
	    c.println ("Merged array with duplicates dropped: " + mergedIntGroup.toString());
	    c.println();
	    c.println("Press 'Y' to start again, any other key to exit...");
	    again = c.getChar();
	    c.clear();
	}
	c.close();
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 10/8/2015
// Purpose: An object that represents an array of sorted integers
// Fields:
//      integer array (Represents the array of the object)
//      size of array (Represents the size of array of the object)
// Methods:
/*      toString - converts array into a printable string
	sorted - checks if the array is sorted in ascending order
	dropDups - removes duplicates in the array
	merge - merges the current array with another from the same class
	longestRun - finds the longest sequence of consecutive elements
*/
//***************************************************************

class SortedIntGroup
{
    protected int[] intArray;
    protected int size;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/8/2015
    // Purpose: Constructor for object with given size, it creates
    //      consecutive series from 1 - size
    // Parameters: size
    // Return/Output: -
    //***************************************************************

    public SortedIntGroup (int size)
    {
	this.size = size;
	this.intArray = new int [size];
	for (int count = 0 ; count < size ; count++)
	    this.intArray [count] = count + 1;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/8/2015
    // Purpose: Explicit constructor for the object with a given array
    //      If given array is not sorted, it defaults to an empty array
    // Parameters: integer array
    // Return/Output: -
    //***************************************************************

    public SortedIntGroup (int[] inputArray)
    {
	this.intArray = new int [inputArray.length];
	this.intArray = inputArray;
	this.size = inputArray.length;
	
	if (this.sorted() == false)
	{
	    this.intArray = new int[0];
	    this.size = 0;
	}
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/9/2015
    // Purpose: Simple constructor that creates an empty array
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public SortedIntGroup ()
    {
	this (0);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/9/2015
    // Purpose: Converts the array into printable string along with its size
    // Parameters: -
    // Return/Output: string output
    //***************************************************************

    public String toString ()
    {
	String result = "";
	int length = this.intArray.length;

	for (int count = 0 ; count < length ; count++)
	    if (count != 0)
		result = result + "," + this.intArray [count];
	    else
		result = result + this.intArray [count];
	return "[" + result + "]" + " (" + this.size + ")";
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/9/2015
    // Purpose: checks if the array is sorted in ascending order (private)
    // Parameters: -
    // Return/Output: true/false
    //***************************************************************

    private boolean sorted ()
    {
	boolean isSorted = false;
	int ok = 0;

	for (int count = 0 ; count < this.size - 1 ; count++)
	{
	    if (this.intArray [count] <= this.intArray [count + 1])
		ok++;
	}

	if (ok == this.size - 1)
	    isSorted = true;

	return isSorted;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/11/2015
    // Purpose: Removes all duplicate values from array, only keeping one
    //      Can only be done if array is sorted
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public void dropDups ()
    {
	if (this.sorted ())
	{
	    int different = 1;
	    for (int count = 0 ; count < this.size - 1 ; count++)
	    {
		if (this.intArray [count] != this.intArray [count + 1])
		{
		    different++;
		}
	    }

	    int[] newArray = new int [different];
	    int countTwo = 0;
	    for (int count = 0 ; count < this.size - 1 ; count++)
	    {
		if (count != 0)
		{
		    if (intArray [count] != intArray [count - 1])
		    {
			newArray [countTwo] = this.intArray [count];
			countTwo++;
		    }
		}
		else
		{
		    newArray [countTwo] = this.intArray [count];
		    countTwo++;
		}

	    }

	    if (this.intArray [this.size - 1] != this.intArray [this.size - 2])
		newArray [newArray.length - 1] = this.intArray [this.size - 1];

	    this.intArray = newArray;
	    this.size = newArray.length;
	}
	else
	    if (this.size != 0)
		System.out.println ("Cannot drop dups, array is not sorted.");
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/11/2015
    // Purpose: Merges with another SortedIntGroup, returns empty array if either
    //      given array is not sorted
    // Parameters: Another SortedIntGroup object
    // Return/Output: Merged SortedIntGroup object
    //***************************************************************

    public SortedIntGroup merge (SortedIntGroup intGroupTwo)
    {
	SortedIntGroup mergedGroup;
	if (this.sorted () && intGroupTwo.sorted ())
	{
	    int count = 0;
	    int countTwo = 0;
	    int countMerge = 0;
	    int[] mergeArray = new int [this.size + intGroupTwo.size];

	    while (count < this.size && countTwo < intGroupTwo.size)
	    {
		if (this.intArray [count] <= intGroupTwo.intArray [countTwo])
		{
		    mergeArray [countMerge] = this.intArray [count];
		    count++;
		}
		else
		{
		    mergeArray [countMerge] = intGroupTwo.intArray [countTwo];
		    countTwo++;
		}
		countMerge++;
	    }

	    if (this.intArray [this.size - 1] > intGroupTwo.intArray [intGroupTwo.size - 1])
	    {
		for (countMerge = countMerge ; countMerge < this.size + intGroupTwo.size ; countMerge++)
		{
		    mergeArray [countMerge] = this.intArray [count];
		    count++;
		}

	    }

	    else
	    {
		for (countMerge = countMerge ; countMerge < intGroupTwo.size + this.size ; countMerge++)
		{
		    mergeArray [countMerge] = intGroupTwo.intArray [countTwo];
		    countTwo++;
		}
	    }
	    ;
	    mergedGroup = new SortedIntGroup (mergeArray);
	}
	else
	{
	    mergedGroup = new SortedIntGroup ();
	}
	return mergedGroup;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/11/2015
    // Purpose: Finds the longest consecutive series in the array
    // Parameters: -
    // Return/Output: - Run container(start location and length of chain)
    //***************************************************************

    public Run longestRun ()
    {
	int consec = 1;
	int start = 0;
	Run longRun = new Run ();
	longRun.start = 0;
	longRun.length = 1;

	for (int count = 0 ; count < this.size - 1 ; count++)
	{

	    if (this.intArray [count] == this.intArray [count + 1] - 1)
	    {
   
		consec++;
		if (consec == 2)
		    start = count;

	    }
	    else
	    {

		if (consec > longRun.length)
		{
		    longRun.start = start;
		    longRun.length = consec;
		}
		consec = 1;
	    }

	}

	if (consec > longRun.length)
	{
	    longRun.start = start;
	    longRun.length = consec;
	}
	
	if (this.size == 0)
	{
	    longRun.start = 0;
	    longRun.length = 0;
	}

	return longRun;
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 10/8/2015
// Purpose: Container class Run, used for SortedIntGroup class
// Fields:
//      start (integer)
//      length (integer)
//***************************************************************

class Run
{
    public int start;
    public int length;
}
