// The "BigIntTest" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 10/18/2015
// Purpose: To find a way to store very big integers using arrays
//***************************************************************

public class BigIntTest
{
    static Console c;
    public static void main (String[] args) throws CloneNotSupportedException
    {
	c = new Console ();
	BigPositiveInt bigInt = new BigPositiveInt (0);
	BigPositiveInt bigInt2 = new BigPositiveInt (0);
	char again = 'Y';
	c.println("Welcome to Big Positive Integer!=====");
	c.println();
	
	while (again == 'Y' || again == 'y')
	{
	    c.print("Please enter your first positive integer: ");
	    bigInt.get (c);
	    c.println ("The integer is: " + bigInt.toString ());
	    c.println ("Size: " + bigInt.size);
	    c.println ();
	   
	    c.print("Please enter your second positive integer: ");
	    bigInt2.get (c);
	    c.println ("The integer is: " + bigInt2.toString ());
	    c.println ("Size: " + bigInt2.size);
	    
	    c.println ();
	    c.println ("The sum of the integers is: " + bigInt.add (bigInt2).toString ());
	    c.println ("The difference of the integers is: " + bigInt.subtract (bigInt2).toString ());
	    c.println ("Their Product is: " + bigInt.multiply (bigInt2).toString ());
	    c.println();
	    c.println("Press 'Y' to start again, or any other key to exit...");
	    again = c.getChar();
	    c.clear();

	}
	c.close();
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 10/18/2015
// Purpose: To store very large integers using arrays that would otherwise overflow
//      the default primitive variables
// Fields:
//      digits (integer array)
//      size (integer)
//      sign (boolean - true is + and false is -, it's always + for BigPositiveInt)
// Methods:
/*      get - gets the digits from the user
	set - sets digits of BigPositiveInt from a given integer
	removeLeadingZeros - removes excess zeros at the front of the array
	addDigitFront - adds a digit to the front of array (position 0)
	addDigitBack - adds a digit to the back of the array (position size)
	toString - Converts object to a printable string
	arrayToString - method that returns array as a string for debug purposes
	add (overloaded) - adds another BigPositiveInteger to the current one
	add - adds a given integer to the current object
	subtract(overloaded) - Subtracts another BigPositiveInteger from the current
	    one, the given BigPositiveInt must be smaller or equal to the current one
	subtract - Subtracts the given integer from the current object, the integer's
	    value must be less or equal to the current object
	multiply (overloaded) - calculates the product of the current BigPositiveInt
	    and other BigPositiveInt
	multiply - calculates the product of the current BigPositiveInt and a given integer
	clone - creates a deep copy of the current object, essential in the subtract method
*/
//***************************************************************

class BigPositiveInt implements Cloneable
{
    protected int[] digits;
    protected int size;
    protected boolean sign;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/18/2015
    // Purpose: Constructor that fills the array with the given size and value
    // Parameters: size, value (integers)
    // Return/Output: -
    //***************************************************************

    public BigPositiveInt (int size, int value)
    {
	this.size = size;
	this.digits = new int [size];
	this.sign = true;
	
	for (int count = 0; count < size; count++)
	    this.digits[count] = value;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/18/2015
    // Purpose: Constructor that fills the array with the given size and value of 0
    // Parameters: size (integer)
    // Return/Output: -
    //***************************************************************

    public BigPositiveInt (int size)
    {
	this (size, 0);
    }


    //***************************************************************
    // Author: Don Smith
    // Date: Unknown
    // Purpose: Gets a bigInt from the user
    // Parameters: Console object
    // Return/Output: -
    //***************************************************************

    public void get (Console c)
    {
	String a;
	a = c.readString ();
	this.size = 0;

	for (int i = 0 ; i < a.length () ; i++)
	    this.addDigitFront (a.charAt (i) - '0');

	this.removeLeadingZeros ();
    }


    //***************************************************************
    // Author: Don Smith
    // Date: Unknown
    // Purpose: Gets a bigInt from the user
    // Parameters: Console object
    // Return/Output: -
    //***************************************************************

    public void set (int inputInt)
    {
	String a;
	a = inputInt + "";
	this.size = 0;

	for (int i = 0 ; i < a.length () ; i++)
	    this.addDigitFront (a.charAt (i) - '0');

	this.removeLeadingZeros ();
    }



    //***************************************************************
    // Author: Don Smith
    // Date: Unknown
    // Purpose: Removes any excess zeros at the front of the array
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public void removeLeadingZeros ()
    {
	int[] old;
	while ((this.digits [this.size - 1] == 0) && (size > 1))
	    this.size--;

	old = this.digits;
	this.digits = new int [this.size];

	for (int a = 0 ; a < this.size ; a++)
	    this.digits [a] = old [a];
    }


    //***************************************************************
    // Author: Don Smith
    // Date: Unknown
    // Purpose: Adds a given integer to the front of the current BigInt
    // Parameters: Given integer
    // Return/Output: -
    //***************************************************************

    public void addDigitFront (int added)
    {
	int[] oldDigit;
	oldDigit = this.digits;

	this.size++;
	this.digits = new int [this.size];

	for (int i = 1 ; i < this.size ; i++)
	    this.digits [i] = oldDigit [i - 1];
	this.digits [0] = added;
    }


    //***************************************************************
    // Author: Don Smith
    // Date: Unknown
    // Purpose: Adds a given integer to the back of the current BigInt
    // Parameters: Given integer
    // Return/Output: -
    //***************************************************************

    public void addDigitBack (int added)
    {
	int[] oldDigit;
	oldDigit = this.digits;

	this.size++;
	this.digits = new int [this.size];

	for (int i = 0 ; i < this.size - 1 ; i++)
	    this.digits [i] = oldDigit [i];
	this.digits [this.size - 1] = added;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/18/2015
    // Purpose: Turns the object in a printable string
    // Parameters: -
    // Return/Output: String object
    //***************************************************************

    public String toString ()
    {
	String result = "";
	for (int count = this.size - 1 ; count >= 0 ; count--)
	    result = result + this.digits [count];
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

    public String arrayToString ()
    {
	String result = "";
	int length = this.digits.length;

	for (int count = 0 ; count < length ; count++)
	    if (count != 0)
		result = result + "," + this.digits [count];
	    else
		result = result + this.digits [count];
	return "[" + result + "]" + " (" + this.size + ")";
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/19/2015
    // Purpose: returns the sum of the current BigInt object with another BigPositiveInt object
    // Parameters: BigPositiveInt object
    // Return/Output: BigPositiveInt object
    //***************************************************************

    public BigPositiveInt add (BigPositiveInt secondBigInt) throws CloneNotSupportedException
    {
	BigPositiveInt largerInt;
	BigPositiveInt smallerInt;

	if (this.size < secondBigInt.size)
	{
	    largerInt = (BigPositiveInt) secondBigInt.clone ();
	    smallerInt = (BigPositiveInt) this.clone ();
	}
	else
	{
	    largerInt = (BigPositiveInt) this.clone ();
	    smallerInt = (BigPositiveInt) secondBigInt.clone ();

	}

	largerInt.addDigitBack (0);
	for (int count = smallerInt.size ; count < largerInt.size ; count++)
	    smallerInt.addDigitBack (0);

	BigPositiveInt sum = new BigPositiveInt (0);

	for (int count = 0 ; count < largerInt.size ; count++)
	{
	    sum.addDigitBack (smallerInt.digits [count] + largerInt.digits [count]);
	    if (sum.digits [count] > 9)
	    {
		sum.digits [count] = sum.digits [count] % 10;
		smallerInt.digits [count + 1]++;
	    }
	}
	sum.removeLeadingZeros ();
	return sum;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/21/2015
    // Purpose: returns the sum of the current BigInt object with a given integer
    // Parameters: integer input
    // Return/Output: BigPositiveInt object
    //***************************************************************

    public BigPositiveInt add (int inputInt) throws CloneNotSupportedException
    {

	BigPositiveInt newBigInt = new BigPositiveInt (0);
	BigPositiveInt result = new BigPositiveInt (0);

	newBigInt.set (inputInt);
	result = this.add (newBigInt);

	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/21/2015
    // Purpose: returns the difference of the current BigPositiveInt and another BigPositiveInt
    //      the given BigPositiveInt must be less or equal to the current one. Cloning is essential
    //      in this method
    // Parameters: BigPositiveInt object
    // Return/Output: BigPositiveInt object
    //***************************************************************

    public BigPositiveInt subtract (BigPositiveInt secondBigInt) throws CloneNotSupportedException
    {
	BigPositiveInt largerInt;
	BigPositiveInt smallerInt;

	if (this.size < secondBigInt.size)
	{
	    largerInt = (BigPositiveInt) secondBigInt.clone ();
	    smallerInt = (BigPositiveInt) this.clone ();

	}
	else
	{
	    largerInt = (BigPositiveInt) this.clone ();
	    smallerInt = (BigPositiveInt) secondBigInt.clone ();

	}

	largerInt.addDigitBack (0);
	for (int count = smallerInt.size ; count < largerInt.size ; count++)
	    smallerInt.addDigitBack (0);

	BigPositiveInt diff = new BigPositiveInt (0);

	for (int count = 0 ; count < smallerInt.size ; count++)
	{
	    if (count == 0)
	    {
		smallerInt.digits [0] = 10 - smallerInt.digits [0];
	    }
	    else
	    {
		smallerInt.digits [count] = 9 - smallerInt.digits [count];
	    }
	}

	diff = largerInt.add (smallerInt);
	diff.removeLeadingZeros ();

	BigPositiveInt realDiff = new BigPositiveInt (0);

	for (int count = 0 ; count < diff.size - 1 ; count++)
	    realDiff.addDigitBack (diff.digits [count]);
	realDiff.removeLeadingZeros ();
	return realDiff;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/21/2015
    // Purpose: returns the difference of the current BigPositiveInt and an integer
    //      the given integer must be less or equal to the value of the current object
    // Parameters: integer input
    // Return/Output: BigPositiveInt object
    //***************************************************************

    public BigPositiveInt subtract (int inputInt) throws CloneNotSupportedException
    {

	BigPositiveInt newBigInt = new BigPositiveInt (0);
	BigPositiveInt result = new BigPositiveInt (0);

	newBigInt.set (inputInt);
	result = this.subtract (newBigInt);

	return result;
    }

    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/22/2015
    // Purpose: returns the product of the current BigPositiveInt and another BigPositiveInt
    // Parameters: BigPositiveInt object
    // Return/Output: BigPositiveInt object
    //***************************************************************

    public BigPositiveInt multiply (BigPositiveInt secondBigInt) throws CloneNotSupportedException
    {
	BigPositiveInt result = new BigPositiveInt(0);
	BigPositiveInt current = new BigPositiveInt(0);
	int carry = 0;
	
	for (int count = 0; count < this.size; count++)
	{
	    for (int countTwo = 0; countTwo < secondBigInt.size; countTwo++)
	    {
		current.addDigitBack((this.digits[count] * secondBigInt.digits[countTwo] + carry) % 10);
		carry = ((this.digits[count] * secondBigInt.digits[countTwo]) + carry) / 10;
	      
	    }
	    
	    if (carry != 0)
		current.addDigitBack(carry);
		    
	    
	    for (int countThree = 0; countThree < count; countThree++)
		current.addDigitFront(0);

	    result = result.add(current);
	    carry = 0;
	    current = new BigPositiveInt(0);
	}
	
	return result;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/22/2015
    // Purpose: returns the product of the current BigPositiveInt and a given integer
    // Parameters: integer input
    // Return/Output: BigpositiveInt object
    //***************************************************************

    public BigPositiveInt multiply (int inputInt) throws CloneNotSupportedException
    {
	BigPositiveInt newBigInt = new BigPositiveInt (0);
	BigPositiveInt result = new BigPositiveInt (0);

	newBigInt.set (inputInt);
	result = this.multiply (newBigInt);

	return result;
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/19/2015
    // Purpose: Creates a deep clone of the current object
    // Parameters: -
    // Return/Output: full clone of object
    //***************************************************************


    public Object clone () throws CloneNotSupportedException
    {
	BigPositiveInt newBigInt = (BigPositiveInt) super.clone ();
	newBigInt.digits = (int[]) digits.clone ();
	return newBigInt;
    }
}
