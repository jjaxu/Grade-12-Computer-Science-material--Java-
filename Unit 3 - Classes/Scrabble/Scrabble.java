// The "Scrabble" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 10/1/2015
// Purpose: To familiarize with composition of classes
// Methods: main
//***************************************************************

public class Scrabble
{
    static Console c, t;

    public static void main (String[] args)
    {

	t = new Console ("Scrabble Tile Class");
	char next = 'Y';
	Tile testTile;
	ScrabbleHand testHand;

	t.println ("Welcome to the Scrabble Tile tester!");
	t.println ("====================================");

	while (next == 'Y' || next == 'y')
	{
	    t.println ("Press any letter on the keyboard to create a tile with that letter, \nor" +
		    " press any other key to create a random tile...");
	    next = t.getChar ();
	    int position = next - 'a' + 1;

	    if (position < 1 || position > 26)
		testTile = new Tile ();
	    else
	    {
		next = Character.toUpperCase (next);
		testTile = new Tile (next);
	    }

	    testTile.print (10, 90, 60, t);
	    t.setCursor (9, 1);
	    t.println ("The letter is: " + testTile.toString ());
	    t.println ("Its value is: " + testTile.value ());
	    t.println ();
	    t.println ("=======================================================");
	    t.println ("Now press any key to create a random hand of five tiles...");
	    next = t.getChar ();

	    testHand = new ScrabbleHand ();
	    testHand.print (10, 280, 60, t);
	    t.setCursor (19, 1);
	    t.println ("The letters are: " + testHand.toString ());
	    t.println ("The hand's total value is: " + testHand.value ());
	    t.println ();
	    t.println ("=======================================================");
	    t.println ("Now press any key to display the permutations of the current hand...");
	    next = t.getChar ();

	    c = new Console (20, 72, "Scrabble Hand Class");

	    testHand.print (10, 10, 60, c);
	    c.setCursor (6, 1);
	    c.println ("All possible permutations are:");
	    testHand.printPermutations (c);
	    c.println ();
	    c.println ();
	    c.println ("Press 'Y' key to start again, or any other key to exit...");
	    next = c.getChar ();
	    c.close ();
	    t.clear ();

	}
	t.close ();
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 10/1/2015
// Purpose: An object that represents a Scrabble tile
// Fields:
//   letter (char)
// Methods:
/*  toString - converts only the letter of the tile to a string
    value - Returns the value of the tile, depending of what letter it is
    print - Draws the tile with its letter and corresponding value on the console
*/
//***************************************************************

class Tile
{
    protected char letter = 'A';

    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/1/2015
    // Purpose: Constructor for explicit input
    // Input/Parameters: desired letter
    // Return/Output: -
    //***************************************************************

    public Tile (char inputLetter)
    {
	this.letter = inputLetter;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/1/2015
    // Purpose: Simple constructor that produces a random letter tile
    // Input/Parameters: -
    // Return/Output: -
    //***************************************************************

    public Tile ()
    {
	int num = (int) (Math.random () * 26) + 1;
	this.letter = (char) (num + 'A' - 1);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/1/2015
    // Purpose: converts the letter from a char to a string so it can be printed
    // Input/Parameters: -
    // Return/Output: letter as a string
    //***************************************************************

    public String toString ()
    {
	return this.letter + "";
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/2/2015
    // Purpose: determines the value of the tile based on its letter
    // different letters will have different values
    // Input/Parameters: -
    // Return/Output: value of the tile (integer)
    //***************************************************************

    public int value ()
    {
	int letterValue = 1;
	if (this.letter == 'F' || this.letter == 'H' || this.letter == 'V' || this.letter == 'W' || this.letter == 'Y')
	    letterValue = 4;
	else if (this.letter == 'B' || this.letter == 'C' || this.letter == 'M' || this.letter == 'P')
	    letterValue = 3;
	else if (this.letter == 'D' || this.letter == 'G')
	    letterValue = 2;
	else if (this.letter == 'J' || this.letter == 'X')
	    letterValue = 8;
	else if (this.letter == 'Q' || this.letter == 'Z')
	    letterValue = 10;
	else if (this.letter == 'K')
	    letterValue = 5;
	return letterValue;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/4/2015
    // Purpose: Draws the tile as visual on the given console.
    //          Both the letter and its value are visible
    // Input/Parameters: x-coordinate, y-coordinate, size of tile and console it's printing to
    // Return/Output: - Picture of tile on console
    //***************************************************************

    public void print (int xCoord, int yCoord, int size, Console c)
    {
	Font f = new Font ("Arial", Font.BOLD, (int) (size * 0.75));
	c.setFont (f);
	Color tileColor = new Color (238, 197, 145);
	c.setColor (tileColor);
	c.fill3DRect (xCoord, yCoord, size, size, true);
	c.setColor (Color.black);
	c.drawString (this.letter + "", (int) (xCoord + 0.15 * size), (int) (yCoord + size - 0.25 * size));

	f = new Font ("Arial", Font.BOLD, (int) (size * 0.2));
	c.setFont (f);
	c.drawString (value () + "", (int) (xCoord + 0.7 * size), (int) (yCoord + size - 0.05 * size));
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 10/4/2015
// Purpose: A hand of Scrabble tiles that is composed of Tile objects
// Fields:
//    Tile object (x5)
// Methods:
//    value - returns the total value of the tiles
//    toString - converts all the tile objects into a string (letters only)
//    print - prints the five tiles side by side, along with its total value
//    printPermutations - calls the recursive formula to print the permutations of the hand's letters
//    recursivePrintPermutations - prints all the permutations of the letters in the hand using recursion
//***************************************************************

class ScrabbleHand
{
    protected Tile tileOne, tileTwo, tileThree, tileFour, tileFive;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/4/2015
    // Purpose: Constructor of the hand for explicit tiles
    // Input/Parameters: five desired tile objects
    // Return/Output: -
    //***************************************************************

    public ScrabbleHand (Tile firstTile, Tile secondTile, Tile thirdTile, Tile fourthTile, Tile fifthTile)
    {
	this.tileOne = firstTile;
	this.tileTwo = secondTile;
	this.tileThree = thirdTile;
	this.tileFour = fourthTile;
	this.tileFive = fifthTile;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/4/2015
    // Purpose: Simple constructor for a hand with five random tile objects
    // Input/Parameters: -
    // Return/Output: -
    //***************************************************************

    public ScrabbleHand ()
    {
	this.tileOne = new Tile ();
	this.tileTwo = new Tile ();
	this.tileThree = new Tile ();
	this.tileFour = new Tile ();
	this.tileFive = new Tile ();
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/4/2015
    // Purpose: Returns the total value of the hand
    //      by summing the values of each tile
    // Input/Parameters: -
    // Return/Output: total value of the hand(integer)
    //***************************************************************

    public int value ()
    {
	return tileOne.value () + tileTwo.value () + tileThree.value () + tileFour.value () + tileFive.value ();
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/4/2015
    // Purpose: Returns the letters of the hand as a single string consisting of five letters
    // Input/Parameters: -
    // Return/Output: The letters of each tile in the hand as a single string
    //***************************************************************

    public String toString ()
    {
	String result = tileOne.toString () + tileTwo.toString () + tileThree.toString () +
	    tileFour.toString () + tileFive.toString ();
	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/4/2015
    // Purpose: Prints the Scrabble hand, which contains five tiles, on a given
    //      console, side by side, along with its total value
    // Input/Parameters: x, y coordinates of console, size of hand, console its outputting to
    // Return/Output: Displays each of the five tiles side by size and the total value
    //***************************************************************

    public void print (int xCoord, int yCoord, int size, Console c)
    {
	tileOne.print (xCoord, yCoord, size, c);
	tileTwo.print (xCoord + size, yCoord, size, c);
	tileThree.print (xCoord + 2 * size, yCoord, size, c);
	tileFour.print (xCoord + 3 * size, yCoord, size, c);
	tileFive.print (xCoord + 4 * size, yCoord, size, c);
	Font f = new Font ("Arial", Font.BOLD, (int) (0.6 * size));
	c.setFont (f);
	c.drawString ("(" + value () + ")", (int) (xCoord + 5.25 * size), (int) (yCoord + 0.7 * size));
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/6/2015
    // Purpose: Calls the recursive permutations method
    // Input/Parameters: output console
    // Return/Output: -
    //***************************************************************

    public void printPermutations (Console c)
    {
	recursivePrintPermutations ("", this.toString (), c);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 10/5/2015
    // Purpose: Prints all the possible permutations of the scrabble letters
    //      for simplicity, repeated letters do not matter
    // Input/Parameters: first section of string, second section of string, and the output console
    // Return/Output: Prints the 120 permutations on the given console (Ignores repetition)
    //***************************************************************

    public void recursivePrintPermutations (String first, String second, Console c)
    {
	int length = second.length ();

	if (length == 0)
	{
	    c.print (first + " ");
	}
	else
	{
	    int count = 0;
	    for (count = 0 ; count < length ; count++)
	    {
		recursivePrintPermutations (first + second.charAt (count),
			second.substring (0, count) + second.substring (count + 1, length), c);
	    }
	}
    }
}
