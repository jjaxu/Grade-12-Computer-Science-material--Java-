// The "Methods" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 9/17/2015
// Purpose: To investigate Methods in Java
// Methods: main, isPrime, printPrimes, calcNumOfPrimes, findTwoPrimeGap
//findHeight, findArea, findHeightParabola, findAreaParabola, calcGCD
//DisplayCountdown, displaySierpinksiCarpet, recursiveSierpinskiCarpet
//***************************************************************

public class Methods
{
    static Console c;
    static Console s;

    public static void main (String[] args)
    {
	int size = 37;
	int level = 8;
	c = new Console ("Methods");

	int inputOne = 0;
	int inputTwo = 0;
	double radius = 0;
	double coA = 0;
	double coB = 0;
	double coC = 0;
	double startX = 0;
	double endX = 0;
	long numOne = 0;
	long numTwo = 0;

	c.println ("Method set #1 - Prime Numbers----------");
	c.print ("Please enter your first positive integer for the calculations: ");
	inputOne = c.readInt ();
	while (inputOne < 0)
	{
	    c.print ("Invalid input! Please enter a POSITIVE integer: ");
	    inputOne = c.readInt ();
	}

	if (isPrime (inputOne))
	{
	    c.println (inputOne + " is a prime number.");

	}
	else
	{
	    c.println (inputOne + " is not a prime number.");
	}

	c.println ();
	c.print ("Please enter your second positive integer for the calculations: ");
	inputTwo = c.readInt ();

	while (inputTwo < 0)
	{
	    c.print ("Invalid input! Please enter a POSITIVE integer: ");
	    inputTwo = c.readInt ();
	}

	if (isPrime (inputTwo))
	{
	    c.println (inputTwo + " is a prime number.");

	}
	else
	{
	    c.println (inputTwo + " is not a prime number.");
	}

	c.println ();
	c.println ("Here's a list of all primes in the range of the two given integers: ");
	printPrimes (inputOne, inputTwo);
	c.println ();
	c.println ("There are " + calcNumOfPrimes (inputOne, inputTwo) + " prime numbers in this range.");
	c.println ();

	c.print ("Please enter a positive, non zero even integer for the prime gap calculations (numbers greater than 60 takes a long time): ");
	inputOne = c.readInt ();
	
	while (inputOne < 1 || inputOne % 2 == 1)
	{
	    c.print ("Invalid input! Please enter a POSITIVE, NON ZERO EVEN integer: ");
	    inputOne = c.readInt ();
	}

	c.println ("Two consecutive primes with a gap of " + inputOne + " is " + findTwoPrimeGap (inputOne).first + " and " + findTwoPrimeGap (inputOne).second);
	c.println ("******************************************************");
	c.println ();
	c.println ("Method Set #2 - Area integration (Semicircle)----------");
	c.println ();

	c.print ("Please enter the radius of the semicircle: ");
	radius = c.readDouble ();
	c.println ("The area of the semicircle is: " + findArea (radius));

	c.println ();
	c.println ("Enrichment - Definite integral under a parabolic function----------");
	c.print ("Please enter the 'a' value for a parabola: ");
	coA = c.readDouble ();

	c.print ("Please enter the 'b' value for a parabola: ");
	coB = c.readDouble ();

	c.print ("Please enter the 'c' value for a parabola: ");
	coC = c.readDouble ();

	c.print ("Please enter the starting x value for the parabola: ");
	startX = c.readDouble ();

	c.print ("Please enter the finishing x value for the parabola: ");
	endX = c.readDouble ();
	c.println ("Area of under the parabola is: " + findAreaParabola (startX, endX, coA, coB, coC));
	c.println ("******************************************************");
	c.println ();

	c.println ("Method Set #3 - Recursion (Greatest common divisor)----------");
	c.print ("Please enter your first positive, non zero integer for GCD calculation: ");
	numOne = c.readLong ();

	while (numOne < 1)
	{
	    c.print ("Invalid input! Please enter a POSITIVE NON ZERO integer: ");
	    numOne = c.readLong ();
	}
	c.println ();

	c.print ("Please enter your second positive, non zero integer for GCD calculation: ");
	numTwo = c.readLong ();

	while (numTwo < 1)
	{
	    c.print ("Invalid input! Please enter a POSITIVE NON ZERO integer: ");
	    numTwo = c.readLong ();
	}
	c.println ();

	c.println ("The GCD between " + numOne + " and " + numTwo + " is " + calcGCD (numOne, numTwo));
	c.println ();
	c.println ("Countdown using recursion----------");
	c.print ("Please enter a countdown integer greater than 0: ");
	inputOne = c.readInt ();

	while (inputOne < 1)
	{
	    c.print ("Invalid input! Please enter a POSITIVE NON ZERO integer: ");
	    inputOne = c.readInt ();
	}
	
	c.println ();
	displayCountDown (inputOne);
	c.println ();
	c.println ("------------------------------------------------------");

	c.println ("Enrichment - Sierpinski's Carpet-----");
	c.println ();


	c.print ("Please enter the size you want for Sierpinski's Carpet (1 - 90, 37 is a good size): ");
	size = c.readInt ();

	while (size < 1 || size > 90)
	{
	    c.print ("Invalid input! Please enter a POSITIVE integer between 1 and 90: ");
	    size = c.readInt ();
	}

	c.println ();
	c.print ("Please enter the level (depth) you want for Sierpinski's Carpet (depths greater than 10 are slow: ");
	level = c.readInt ();

	while (level < 0)
	{
	    c.print ("Invalid input! Please enter a POSITIVE integer: ");
	    level = c.readInt ();
	}

	displaySierpinskiCarpet (size, level);
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/17/2015
    // Purpose: Calculates whether the given integer is prime
    // Inputs: integer number
    // Return: true / false
    //***************************************************************

    public static boolean isPrime (int num)
    {
	boolean prime = false;

	if (num == 2 || num == 3)
	    prime = true;
	else if (num < 2 || num % 2 == 0)
	    prime = false;
	else
	{
	    int count = 0;
	    int factorCount = 0;

	    for (count = 3 ; count <= num ; count = count + 2)
	    {
		if (num % count == 0)
		{
		    factorCount++;
		}
	    }

	    if (factorCount == 1)
	    {
		prime = true;
	    }
	}
	return prime;
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/18/2015
    // Purpose: Prints all the primes numbers within a given range
    // Inputs: start and end values
    // Return: -
    //***************************************************************

    public static void printPrimes (int start, int stop)
    {
	if (start > stop)
	{
	    int temp = start;
	    start = stop;
	    stop = temp;
	}

	for (start = start ; start <= stop ; start++)
	{
	    if (isPrime (start))
	    {
		c.print (start + " ");

	    }
	}
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/18/2015
    // Purpose: Calculates the number of prime numbers between a given range
    // Inputs: start and end values
    // Return: number of prime numbers in the given range
    //***************************************************************

    public static int calcNumOfPrimes (int start, int stop)
    {
	int primeCount = 0;
	if (start > stop)
	{
	    int temp = start;
	    start = stop;
	    stop = temp;
	}

	for (start = start ; start <= stop ; start++)
	{
	    if (isPrime (start))
	    {
		primeCount++;
	    }
	}
	return primeCount;
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/18/2015
    // Purpose: Calculates two consecutive integers that has a difference (gap) of the given value
    // Inputs: gap value
    // Return: TwoPrime class object
    // NOTE: GAPS ABOVE 60 TAKE A LONG TIME!!!
    //***************************************************************

    public static TwoPrimeGap findTwoPrimeGap (int gap)
    {
	TwoPrimeGap range;
	range = new TwoPrimeGap ();
	int boundOne = 1;
	int boundTwo = 0;
	boundTwo = boundOne + gap;

	if (gap % 2 == 0)
	{
	    while (isPrime (boundOne) == false || isPrime (boundTwo) == false || calcNumOfPrimes (boundOne, boundTwo) != 2)
	    {
		boundOne = boundOne + 2;
		boundTwo = boundTwo + 2;
	    }
	    range.first = boundOne;
	    range.second = boundTwo;

	}
	else if (gap == 1)
	{
	    range.first = 2;
	    range.second = 3;
	}        
	return range;
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/19/2015
    // Purpose: Calculates the height of the semi-circle at a specific x-coordinate given the radius
    // Inputs: x-coordinate, radius
    // Return: height
    //***************************************************************

    public static double findHeight (double xCoord, double radius)
    {
	return Math.sqrt (radius * radius - (xCoord - radius) * (xCoord - radius));
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/19/2015
    // Purpose: Calculates the height of the parabola at the give x-coordinate and coefficients a, b and c
    // Inputs: x-coordinate, coefficients a, b and c
    // Return: height
    //***************************************************************

    public static double findHeightParabola (double xCoord, double coA, double coB, double coC)
    {
	return coA * xCoord * xCoord + coB * xCoord + coC;
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/19/2015
    // Purpose: Calculates the area of a semicircle given radius using rectangle approximation
    // Inputs: radius of circle
    // Return: area of the semicircle
    //***************************************************************

    public static double findArea (double radius)
    {
	radius = Math.abs (radius);
	double area = 0;
	int n = 10000;
	double x = 0;
	double width = 2 * radius / n;
	int count = 0;

	for (count = 0 ; count < n ; count++)
	{
	    area = area + width * findHeight (x, radius);
	    x = x + width;
	}
	return area;
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/19/2015
    // Purpose: Calculates the area of a parabola using integration
    // Inputs: starting x value, finishing x value, coefficients a, b and c of a quadratic function
    // Return: area of parabola
    //***************************************************************

    public static double findAreaParabola (double start, double end, double coA, double coB, double coC)
    {
	double area = 0;
	int n = 100000;
	double x = start;
	double width = (end - start) / n;
	int count = 0;

	for (count = 0 ; count < n ; count++)
	{
	    area = area + width * findHeightParabola (x, coA, coB, coC);
	    x = x + width;
	}
	return area;
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/22/2015
    // Purpose: Calculates the GCD of two given integers using recursion
    // Inputs: two integers
    // Return: GCD of the two integers
    //***************************************************************

    public static long calcGCD (long m, long n)
    {
	long gcd = 0;
	if (m < 0)
	    m = Math.abs (m);

	if (n < 0)
	    n = Math.abs (n);

	if (n == 0)
	{
	    gcd = m;
	}
	else
	{
	    gcd = calcGCD (n, m % n);
	}
	return gcd;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/22/2015
    // Purpose: Prints a count down from the input integer to 1
    // Inputs: one integer
    // Return: -
    //***************************************************************

    public static void displayCountDown (int n)
    {
	if (n < 0)
	    n = Math.abs (n);

	if (n >= 1)
	{
	    if (n != 1)
		c.print (n + ",");
	    else
		c.print (n);

	    displayCountDown (n - 1);
	}
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/23/2015
    // Purpose: Call's Sierpinski's Carpet's recursive formula and creates a GUI for it
    // Inputs: size of carpet, deepness level
    // Return: -
    //***************************************************************

    public static void displaySierpinskiCarpet (int size, int level)
    {
	s = new Console (size, 5 * size / 2, "Sierpinski's Carpet");
	recursiveSierpinskiCarpet (0, 0, s.getHeight (), level);
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 9/23/2015
    // Purpose: Prints Sierpinski's Carpet using a recursive formula
    // Inputs: starting x,y coordinates, width of carpet(r), deepness level
    // Return: -
    //***************************************************************

    public static void recursiveSierpinskiCarpet (int x, int y, int r, int level)
    {
	if (level % 2 == 0)
	    s.setColor (Color.black);
	else
	    s.setColor (Color.red);

	if (level > 0)
	{
	    s.fillRect (x + r / 3, y + r / 3, r / 3, r / 3);

	    recursiveSierpinskiCarpet (x, y, r / 3, level - 1);
	    recursiveSierpinskiCarpet (x + r / 3, y, r / 3, level - 1);
	    recursiveSierpinskiCarpet (x + 2 * r / 3, y, r / 3, level - 1);

	    recursiveSierpinskiCarpet (x, y + r / 3, r / 3, level - 1);
	    recursiveSierpinskiCarpet (x + 2 * r / 3, y + r / 3, r / 3, level - 1);

	    recursiveSierpinskiCarpet (x, y + 2 * r / 3, r / 3, level - 1);
	    recursiveSierpinskiCarpet (x + r / 3, y + 2 * r / 3, r / 3, level - 1);
	    recursiveSierpinskiCarpet (x + 2 * r / 3, y + 2 * r / 3, r / 3, level - 1);
	}
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 9/18/2015
// Purpose: Container class that holds boundaries integers
// Fields: first, second
// Methods: -
//***************************************************************

class TwoPrimeGap
{
    public long first;
    public long second;
}
