// The "Poly" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 10/13/2015
// Purpose: To create a polynomial class that can perform certain tasks
//***************************************************************

public class Poly
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	Polynomial poly;
	double inputX;
	char again = 'Y';
	
	c.println("Welcome to Polynomials! Below you can experiment with polynomial functions!");
	c.println("===========================================================================");
	c.println();
	
	while (again == 'Y' || again == 'y')
	{
	    poly = new Polynomial(7);
	    poly.get(c);
	    c.println("f(x) = " + poly.toString());
	    c.println();
	    c.print("Please enter an x value to evaluate this polynomial: ");
	    inputX = c.readDouble();
	    c.println("f(" + inputX + ") = " + poly.value(inputX));
	    c.println();
	    c.println("The first derivative of the polynomial is");
	    c.println("f'(x) = " + poly.derivative().toString());
	    c.println();
	    c.print("It will now attempt to find a root nearby... \nplease guess a starting x value: ");
	    inputX = c.readDouble();
	    c.println("Nearest Root: x = " + poly.root(inputX));
	    c.println();
	    c.println("Press 'Y' to test another polynomial, or any other key to exit...");
	    again = c.getChar();
	    c.clear();
	}
	c.close();

    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 10/12/2015
// Purpose: To create a object that represents a polynomial and capable
//      of perform a variety of tasks
// Fields:
//      coeff (array of a doubles to represent the coefficients)
//      degree (integer to represent the highest degree of the polynomial)
// Methods:
/*      arrayToString - method returns array as a string for debug purposes 
	toString - converts polynomial to a printable string in simplest form
	get - gets the properties of the polynomial from user
	value - evaluates the polynomial with given x value
	derivative - returns the first derivative of the function
	newton - returns the closest root from a given x value(if any) in the function using Newton's method
	root - returns the closest root of the polynomial given a starting guess value and precision value 
	    it calls newton

*/
//***************************************************************

class Polynomial
{
    protected double [] coeff;
    protected int degree;
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/13/2015
    // Purpose: Constructor for creating the object
    // Parameters: degree (integer)
    // Return/Output: -
    //***************************************************************
    
    public Polynomial(int inputDegree)
    {
	this.degree = inputDegree;
	this.coeff = new double[this.degree + 1];
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/9/2015
    // Purpose: Not a required method, but returns the array as a string
    //      for debugging purposes
    // Parameters: -
    // Return/Output: String object 
    //***************************************************************
    
    public String arrayToString ()
    {
	String result = "";
	int length = this.degree;

	for (int count = 0 ; count <= length ; count++)
	{
	    if (count != 0)
		result = result + ", " + this.coeff [count];
	    else
		result = result + this.coeff [count];
	}
	return "[" + result + "]";
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/15/2015
    // Purpose: turns polynomial into a printable string in simplest form
    // Parameters: -
    // Return/Output: String object
    //***************************************************************
    
    public String toString ()
    {
	String result = "";
	for (int count = degree; count > 0; count--)
	{
	    if (count == this.degree)
	    {   
		if (this.coeff[count] != 0)
		{
		    if (this.coeff[count] == 1)
		    {
			if (this.degree != 1)
			    result = result + "x^" + count;
			else
			    result = result + "x";
		    }
		    else if (this.coeff[count] == -1)
		    {
			if (this.degree != 1)
			    result = result + "-x^" + count;
			else
			    result = result + "-x";
		    }
		    else
		    {
			if (this.degree != 1)
			    result = result + this.coeff[count] + "x^" + count;
			else
			    result = result + this.coeff[count] + "x";
		    }
		}
	    }
	    else
	    {
		if (this.coeff[count] != 0)
		{
		    if (coeff[count] == 1)
		    {
			if (count != 1)
			    result = result + " + x^" + count;
			else
			    result = result + " + x";
		    }
		    else if (coeff[count] == -1)
		    {
			if (count != 1)
			    result = result + " - x^" + count;
			else
			    result = result + " - x";
		    }
		    else if (coeff[count] < 0)
		    {
			if (count != 1)
			    result = result + " - " + Math.abs(this.coeff[count]) + "x^" + count;
			else
			    result = result + " - " + Math.abs(this.coeff[count]) + "x";
		    }
		    else
		    {
			if (count != 1)
			    result = result + " + " + Math.abs(this.coeff[count]) + "x^" + count;
			else
			    result = result + " + " + Math.abs(this.coeff[count]) + "x";
		    }
		}
		
	    }
		
	}
	
	if (Math.abs(this.coeff[0]) != 0) 
	{
	    if (this.coeff[0] < 0)
		result = result + " - " + Math.abs(this.coeff[0]);
	    else
		result = result + " + " + this.coeff[0];
	}
	
	if (this.degree == 0)
	    result = this.coeff[0] + "";
	return result;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/15/2015
    // Purpose: To let user set the properties of the polynomial
    // Parameters: Input console
    // Return/Output: -
    //***************************************************************
    
    public void get(Console c)
    {
    
	c.print("Please enter the degree of the polynomial \n(must be a positive integer, 0 is fine): ");
	this.degree = c.readInt();
	while (this.degree < 0)
	{
	    c.println();
	    c.print("Invalid input! The degree must be a positive integer! \nPlease re-enter the degree: ");
	    this.degree = c.readInt();
	} 
	c.println();
	this.coeff = new double[this.degree + 1];
	
	for (int count = degree; count > 0; count--)
	{
	    c.print("Please enter the coefficient for degree " + count + ": ");
	    this.coeff[count] = c.readDouble();
	    while (count == degree && Math.abs(this.coeff[count]) == 0)
	    {
		c.print("Leading coefficient cannot be 0! Please re-enter the coefficient: ");
		this.coeff[count] = c.readDouble();
	    }
	}
	c.print("Please enter the constant (degree 0): ");
	this.coeff[0] = c.readDouble();

    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/17/2015
    // Purpose: Evaluate the polynomial using Horner's rule
    // Parameters: input x value
    // Return/Output: answer (double) 
    //***************************************************************
    
    public double value(double x)
    {
	double answer = 0;
	if (this.degree == 0)
	    answer = this.coeff[0];
	else
	{
	    for (int count = this.degree; count > 0; count--)
	    {
		if (count == this.degree)
		    answer = this.coeff[count] * x + this.coeff[count - 1];
		else
		    answer = answer * x + this.coeff[count - 1];
	    }
	}
	return answer;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/18/2015
    // Purpose: To find the derivative of the polynomial function
    // Parameters: -
    // Return/Output: derivative (Polynomial object)
    //***************************************************************
    
    public Polynomial derivative ()
    {
	Polynomial derive;
	if (this.degree != 0)
	{
	    derive = new Polynomial(this.degree - 1);
	    for (int count = derive.degree; count >= 0; count--)
	    {
		derive.coeff[count] = this.coeff[count + 1] * (count + 1);
	    }
	}
	else
	{
	    derive = new Polynomial(0);
	    derive.coeff[0] = 0;
	}
	return derive;
    }
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/18/2015
    /* Purpose: Calculate the closest root of the polynomial. Given the starting x value, 
	    and precision, a value is returned until the answer is less than the precision level
	    it will not return solution if starting x value is too far from a root ( > 100 units)
	    this restriction prevents possible infinite loops that might occur. 
    */
    // Parameters: starting x value (double), precision (double)
    // Return/Output: root (double)
    //***************************************************************
    
    protected double newton(double startX, double precision)
    {
	double endX;
	double boundary = 0;
	
	while (this.derivative().value(startX) == 0 && boundary < 100)
	{    
	    startX = startX + 0.001;
	    boundary++;
	}
	
	if (this.degree == 0)
	{
	    if (this.coeff[0] == 0)
		System.out.println("There are roots at all real x values");
	    else
		System.out.println("No real roots for a horizontal line");
	    startX = 0;
	    endX = 0;
	}
	else   
	    endX = startX - this.value(startX) / this.derivative().value(startX);
	
	boundary = 0;
	while (Math.abs(endX - startX) > precision)
	{
	    startX = endX;
	    endX = startX - this.value(startX) / this.derivative().value(startX);
	    boundary++;
	   
	    
	    if (boundary > 10000)
	    {
		startX = 0;
		endX = 0;
		System.out.println("No real roots nearby");
	    }

	}

	return endX;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/18/2015
    // Purpose: Call newton to calculate the closest root of the polynomial
    //      given a start x value
    // Parameters: starting x value
    // Return/Output: root (double)
    //***************************************************************
    
    public double root(double startX)
    {
	return this.newton(startX,0.0000000001);
    }

}
