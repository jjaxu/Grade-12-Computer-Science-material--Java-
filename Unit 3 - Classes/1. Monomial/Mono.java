// The "Mono" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 9/28/2015
// Purpose: To write an OOP with classes and objects
// Methods: main
//***************************************************************

public class Mono
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	Monomial monoOne;
	Monomial monoTwo;
	double x = 0;
	int power = 0;
	char again = 'Y';

	c.println ("Welcome to the Monomial Class Tester!");
	c.println ("=====================================");
	c.println ();

	while (again == 'Y' || again == 'y')
	{
	    monoOne = new Monomial ();
	    monoTwo = new Monomial ();
	    c.println ("First monomial---");
	    monoOne.get (c);
	    c.println ("Second monomial---");
	    monoTwo.get (c);
	    monoOne.simplify ();
	    monoTwo.simplify ();
	    c.println ("Your first monomial is: " + monoOne.toString ());
	    c.println ("Your second monomial is: " + monoTwo.toString ());
	    c.println ();

	    c.print ("Can they be added and simplifed?");
	    if (monoOne.canAdd (monoTwo))
	    {
		c.println (" Yes");
		c.println ("The sum of the monomials is: " + monoOne.add (monoTwo));
		c.println ("The difference of the monomials is: " + monoOne.subtract (monoTwo));
	    }
	    else
		c.println (" No");
		
	    c.println ();
	    c.print ("Can they be divided and simplifed?");
	    
	    if (monoOne.canDivide (monoTwo))
	    {
		c.println (" Yes");
		c.println ("The quotient of the monomials is: " + monoOne.divide (monoTwo));
	    }
	    else
		c.println (" No");
		
	    c.println ("The product of the monomials is: " + monoOne.multiply (monoTwo));
	    c.println ();

	    c.print ("Are the two monomials equal?");
	    
	    if (monoOne.equal (monoTwo))
		c.println (" Yes");
	    else
		c.println (" No");
		
	    c.println ();
	    c.print ("Enter an integer exponent for the monomials to raise to: ");
	    power = c.readInt ();

	    if (power < 0 && monoOne.coE == 0)
		c.println ("Cannot raise 0 to negative exponent");
	    else
		c.println (monoOne + " raised to the exponent " + power + " is " + monoOne.pow (power));

	    if (power < 0 && monoTwo.coE == 0)
		c.println ("Cannot raise 0 to negative exponent");
	    else
		c.println (monoTwo + " raised to the exponent " + power + " is " + monoTwo.pow (power));

	    c.println ();
	    c.print ("Please enter a value for x to evaluate the monomials: ");
	    x = c.readDouble ();

	    if (x == 0 && monoOne.exp < 0)
		c.println ("Cannot evaluate " + monoOne.toString () + " when x = 0");
	    else
		c.println ("When x = " + x + ": " + monoOne + " = " + monoOne.value (x));

	    if (x == 0 && monoTwo.exp < 0)
		c.println ("Cannot evaluate " + monoTwo.toString () + " when x = 0");
	    else
		c.println ("When x = " + x + ": " + monoTwo + " = " + monoTwo.value (x));

	    c.println ();
	    c.println ("Press 'Y' to start again, or any other key to exit...");
	    again = c.getChar ();
	    c.clear ();

	}
	c.close ();
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 9/28/2015
// Purpose: An object that represents a monomial
// Fields:
//   coefficient (double), exponent (int)
// Methods:
/*  simplify - Simplifies the fraction
    get - gets the coefficient and exponent from user
    toString - converts objects to a printable string
    canAdd - checks if another monomial can be added to this one
    canDivide - checks if this monomial can be divided by another monomial
    add - if adding is possible, it returns a new monomial that is the sum
    subtract - if adding is possible, it returns a new monomial that is the difference
    multiply - Returns a new monomial that is the product
    divide - if division is possible, it returns a new monomial that is the quotient
    value - returns the value of the expression given the value of x if possible
    pow - returns a monomial that is the result of the current one raised to a given power
    equal - checks whether if a given monomial is equal to the current one (simplified)
*/
//***************************************************************

class Monomial
{
    protected double coE;
    protected int exp;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Explicit constructor, to create a monomial object with
    //      given the coefficient and exponent for the fields
    // Parameters: - coefficient (double), exponent (integer)
    // Return/Output: -
    //***************************************************************

    public Monomial (double coEfficient, int exponent)
    {
	this.coE = coEfficient;
	this.exp = exponent;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Simple constructor that gives the monomial a coefficient
    //      and exponent of 0
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public Monomial ()
    {
	this (0, 0);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Simplifies the value of monomial, if the coefficient is 0,
    // then the value of the monomial simplifies to 0
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public void simplify ()
    {
	if (coE == 0 || coE == -0)
	{
	    this.exp = 0;
	    this.coE = 0;
	}
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: gets the coefficient and exponent values from user
    // Parameters: console that is getting the input
    // Return/Output: -
    //***************************************************************

    public void get (Console c)
    {
	c.print ("Please enter the coefficient of the monomial: ");
	this.coE = c.readDouble ();
	c.print ("Please enter the exponent of the monomial: ");
	this.exp = c.readInt ();
	c.println ();
	this.simplify ();
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Converts the objects to a printable string in the form ax^b
    //      The string is always in simplest form
    // Parameters:
    // Return/Output: output (String object)
    //***************************************************************

    public String toString ()
    {
	String output = "";
	String first = "";
	String second = "";

	if (this.coE == 0)
	    first = "0";
	else if (this.coE == 1)
	    first = "";
	else if (this.coE == -1)
	    first = "-";
	else
	    first = this.coE + "";

	if (this.exp == 0)
	{
	    second = "";
	    first = this.coE + "";
	}
	else if (this.exp == 1)
	    second = "x";
	else
	    second = "x^" + this.exp;

	output = first + second;
	return output;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Checks whether if another monomial can be added to the
    //      current one and be simplified. Returns true if the given monomial
    // has the same exponent as the current monomial
    // Parameters: another Monomial object
    // Return/Output: true or false
    //***************************************************************

    public boolean canAdd (Monomial monoTwo)
    {
	boolean blnCanAdd = false;
	if (monoTwo.exp == this.exp || this.coE == 0 || monoTwo.coE == 0)
	    blnCanAdd = true;
	return blnCanAdd;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Checks whether if another monomial can be divided to the
    //      current one and be simplified. Returns true as long the given
    //      monomial does not have a coefficient of 0
    // Parameters: another Monomial object
    // Return/Output: true or false
    //***************************************************************

    public boolean canDivide (Monomial monoTwo)
    {
	boolean blnCanDivide = false;
	if (monoTwo.coE != 0)
	    blnCanDivide = true;
	return blnCanDivide;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Finds the sum of the current monomial and another given monomial
    //      if they can be added
    // Parameters: another Monomial object
    // Return/Output: sum (Monomial object)
    //***************************************************************

    public Monomial add (Monomial monoTwo)
    {
	Monomial sum = new Monomial ();
	if (this.canAdd (monoTwo))
	{
	    sum.coE = this.coE + monoTwo.coE;
	    sum.exp = this.exp;
	}
	sum.simplify ();
	return sum;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Finds the difference of the current monomial and another given monomial
    //      if they can be subtracted (same as added)
    // Parameters: another Monomial object
    // Return/Output: difference (Monomial object)
    //***************************************************************

    public Monomial subtract (Monomial monoTwo)
    {
	Monomial temp = new Monomial ();
	temp.coE = -1 * monoTwo.coE;
	temp.exp = monoTwo.exp;
	return add (temp);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Finds the product of the current monomial and another given monomial
    // Parameters: another Monomial object
    // Return/Output: product (Monomial object)
    //***************************************************************

    public Monomial multiply (Monomial monoTwo)
    {
	Monomial product = new Monomial ();
	product.coE = this.coE * monoTwo.coE;
	product.exp = this.exp + monoTwo.exp;
	product.simplify ();
	return product;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Finds the quotient of the current monomial and another given monomial
    //      if they can be divided
    // Parameters: another Monomial object
    // Return/Output: quotient (Monomial object)
    //***************************************************************

    public Monomial divide (Monomial monoTwo)
    {
	Monomial quotient = new Monomial ();
	if (canDivide (monoTwo))
	{
	    quotient.coE = this.coE / monoTwo.coE;
	    quotient.exp = this.exp - monoTwo.exp;
	}
	quotient.simplify ();
	return quotient;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Evaluates the expression given a value for x if possible
    //  also returns 0 if evaluation is not possible
    // Parameters: value of x (double)
    // Return/Output: resulting value
    //***************************************************************

    public double value (double x)
    {
	double result = 0;
	result = this.coE * Math.pow (x, this.exp);
	if (result == -0)
	    result = 0;
	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Finds the result of the current monomial raised to a
    //      given exponent if it can be done. Returns monomial with zeros
    //      for its fields if raising 0 to a negative exponent occurs
    // Parameters: exponent (integer)
    // Return/Output: resulting monomial (Monomial object)
    //***************************************************************

    public Monomial pow (int exponent)
    {
	Monomial result = new Monomial ();
	if (this.coE == 0 && exponent < 0)
	{
	    result.coE = 0;
	    result.exp = 0;
	}
	else
	{
	    result.coE = Math.pow (this.coE, exponent);
	    result.exp = this.exp * exponent;

	}
	result.simplify ();
	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/29/2015
    // Purpose: Checks whether if a given monomial's value is identical
    //  to the current one's
    // Parameters: another monomial object
    // Return/Output: true/false
    //***************************************************************

    public boolean equal (Monomial monoTwo)
    {
	boolean isEqual = false;
	if (this.exp == monoTwo.exp && this.coE == monoTwo.coE)
	    isEqual = true;
	return isEqual;

    }
}
