// The "Hash" class.
import java.awt.*;
import java.util.StringTokenizer;
import hsa.*;

//***************************************************************
// Author: Jackie Xu
// Date: 11/25/2015
// Purpose: To use the method Hashing to store and find large amounts of data
//***************************************************************

public class Hash
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console (30, 120, "Hashing");
	c.println ("Welcome to the database of movies!");
	c.println ("==================================");
	c.println ();
	c.print ("Please enter the starting size of the database (at least 5), it will be adjusted to the nearest prime: ");
	int size = c.readInt ();
	while (size < 5)
	{
	    c.print ("Invalid input! Please enter a size that is 5 or greater: ");
	    size = c.readInt ();
	}

	HashDataBase database = new HashDataBase (size);

	c.println ();
	c.println ("A database of size " + database.physicalSize + " has been created");

	c.println ();

	char command = 'a';
	while (command == 'a' || command == 'f' || command == 'p' || command == 'd' || command == 'c')
	{
	    c.println ("***************************************************************************************************");
	    c.println ("Please select a command: 'a' to add record, 'f' to find, 'p' to peek, 'd' to delete, 'c' to change, \nor any other key to exit...");
	    command = Character.toLowerCase (c.getChar ());

	    c.println ();
	    if (command == 'a')
	    {
		database.add (c);
	    }
	    else if (command == 'f')
	    {
		c.print ("Find selected! Please enter the ID: ");
		int id = c.readInt ();
		
		while (id < 0)
		{
		    c.print ("ID cannot be negative, please re-enter the ID: ");
		    id = c.readInt ();
		}
		c.println();
		database.find (id, c,true);
	    }
	    else if (command == 'p')
	    {
		c.clear ();
		c.println ("Peeking...");
		c.println ();
		database.peek (c);
	    }
	    else if (command == 'd')
	    {
		c.print ("Delete selected! Please enter the ID of the record to delete: ");
		int id = c.readInt ();
		
		while (id < 0)
		{
		    c.print ("ID cannot be negative, please re-enter the ID: ");
		    id = c.readInt ();
		}
		c.println();
		database.delete (id, c);
	    }
	    else if (command == 'c')
	    {
		c.print ("Change selected! Please enter the ID of the record to change: ");
		int id = c.readInt ();
		
		while (id < 0)
		{
		    c.print ("ID cannot be negative, please re-enter another ID: ");
		    id = c.readInt ();
		}
		c.println();
		database.change (id, c);
	    }
	    c.println ();

	}
	c.close ();

    }
}

//***************************************************************
// Author: Don Smith
// Date: Unknown
// Purpose/Description: to create a movie record containing an id, title, type, cost, distance, and date
// Fields:
//      id - record id
//      title - record title
//      type - type of movie
//      cost - cost
//      dist - distance to travel
//      date - date of record
// Methods:
/*      constructor - creates a DBRecord given all the information
	toString - aligns information in a string
	rightPad - pads a string with spaces on the left to a certain width

*/
//***************************************************************

class DBRecord
{
    public int id;
    protected String title;
    protected String type;
    protected double cost;
    protected String dist;
    protected String date;

    //***************************************************************
    // Author: Don Smith
    // Date: Unknown
    // Purpose: creates a DBRecord given all the information
    // Parameters: id,title, type, cost, dist, date
    // Return/Output: -
    //***************************************************************

    public DBRecord (int x, String tit, String typ, double cos, String dis, String dat)
    {
	id = x;
	title = tit;
	type = typ;
	cost = cos;
	dist = dis;
	date = dat;
    }


    //***************************************************************
    // Author: Don Smith
    // Date: Unknown
    // Purpose: aligns information in a string
    // Parameters: -
    // Return/Output: the string of the record's information
    //***************************************************************

    public String toString ()
    {
	return (rightPad ("" + id, 8) + rightPad (title, 50) + rightPad (type, 10)
		+ rightPad ("" + cost, 8) + rightPad (dist, 6) + rightPad (date, 12));
    }


    //***************************************************************
    // Author: Don Smith
    // Date: Unknown
    // Purpose: pads a string with spaces on the right to a certain width
    // Parameters: string to pad, width
    // Return/Output: the string with a correct number of characters
    //***************************************************************

    public static String rightPad (String x, int w)
    {
	String s = "" + x;
	while (s.length () < w)
	    s = s + " ";
	return s;
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 11/25/2015
// Purpose/Description: A database that holds DBRecords records
// Fields:
//      textInput - The input file of the database
//      dataBase - array of DBRecords
//      physicalSize - Physical size of the array
//      logicalSize - Logical size of the array
// Methods:
/*      isPrime - checks if a given integer is prime
	isFull - checks if logicalSize / physical size is > 70%
	rehash - increase the size of data base by 20% and transfer the data
	add - reads a line from file and inserts the record to table
	insert - inserts a given DBRecord to the database
	peek - prints out the complete table
	search - returns the location of the given ID in the table
	find - find the record given the ID
	delete - deletes the record with the given ID if it exists
	change - changes a record to another if given ID if possible
	h - first hash function
	h2 - seconds hash function, effective only when collisions occur

*/
//***************************************************************

class HashDataBase
{
    protected TextInputFile textInput;
    protected DBRecord[] dataBase;
    protected int physicalSize;
    protected int logicalSize;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/25/2015
    // Purpose: Constructor for the array given a size (>= 5), if it's not prime
    //          then it will set it to the next biggest prime
    // Parameters: size (int)
    // Return/Output: -
    //***************************************************************

    public HashDataBase (int size)
    {
	if (size < 5)
	    size = 5;

	this.textInput = new TextInputFile ("videodb.csv.txt");
	while (isPrime (size) == false)
	{
	    size++;
	}
	this.physicalSize = size;
	this.dataBase = new DBRecord [size];
	this.logicalSize = 0;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/25/2015
    // Purpose: checks whether if a given integer is prime
    // Parameters: input (int)
    // Return/Output: true/false
    //***************************************************************

    public static boolean isPrime (int input)
    {
	boolean prime = true;
	input = Math.abs (input);
	if (input == 0 || input == 1 || input % 2 == 0 && input != 2)
	    prime = false;
	else

	    for (int i = 2 ; i <= (int) Math.sqrt (input) ; i++)
	    {
		if (input % i == 0)
		{
		    prime = false;
		    i = (int) Math.sqrt (input);
		}
	    }
	return prime;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/28/2015
    // Purpose: returns true if the array is more than 70% filled
    // Parameters: -
    // Return/Output: true/false
    //***************************************************************

    public boolean isFull ()
    {
	boolean full = false;
	if ((float) this.logicalSize / this.physicalSize > 0.7)
	    full = true;
	return full;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/30/2015
    // Purpose: increase the table size and rehashes the records
    // Parameters: output console, output switch, expand switch
    // Return/Output: -
    //***************************************************************

    public void rehash (Console c, boolean output, boolean expand)
    {
	HashDataBase bigger;
	if (expand)
	    bigger = new HashDataBase ((int) (this.physicalSize * 1.2));
	else
	    bigger = new HashDataBase (this.physicalSize);

	if (output)
	{
	    if (expand)
		c.println ("Expanding and rehashing...");
	    else
		c.println ("Rehashing...");
	}

	for (int i = 0 ; i < this.physicalSize ; i++)
	{
	    if (this.dataBase [i] != null)
	    {
		bigger.insert (this.dataBase [i], c, true);
	    }

	}
	if (output)
	    c.println("Done!");
	this.dataBase = bigger.dataBase;
	this.physicalSize = bigger.physicalSize;
	this.logicalSize = bigger.logicalSize;

    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/28/2015
    // Purpose: adds the next entry from the file
    // Parameters: output console
    // Return/Output: -
    //***************************************************************

    public void add (Console c)
    {
	if (!this.textInput.eof ())
	{
	    String line = this.textInput.readLine ();
	    StringTokenizer t = new StringTokenizer (line, ",");

	    int id = Integer.parseInt (t.nextToken ());
	    String title = t.nextToken ();
	    String type = t.nextToken ();
	    double cost = Double.parseDouble (t.nextToken ());
	    String dist = t.nextToken ();
	    String date = t.nextToken ();

	    DBRecord entry = new DBRecord (id, title, type, cost, dist, date);
	    this.insert (entry, c, true);

	    c.println ("Record added!");
	    c.println ("The record added was: ");
	    c.println (entry.toString ());
	}
	else
	{
	    c.println ("End of file reached, the input file has been reset");
	    this.textInput = new TextInputFile ("videodb.csv.txt");
	}
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 12/1/2015
    // Purpose: Inserts a given DBRecord
    // Parameters: DBRecord object, output console, output switch
    // Return/Output: -
    //***************************************************************

    public void insert (DBRecord inputRecord, Console c, boolean output)
    {
	if (this.isFull ())
	    this.rehash (c, true, true);

	int count = 0;
	int hash = this.h (inputRecord.id, count);

	while (this.dataBase [hash] != null)
	{
	    count++;
	    hash = this.h (inputRecord.id, count);

	}

	if (count == 1 && output == true)
	    c.println ("1 collision has occured.");
	else if (count != 0 && output == true)
	    c.println (count + " collisions have occured.");

	this.dataBase [hash] = inputRecord;
	this.logicalSize++;

    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/28/2015
    // Purpose: prints out the complete table
    // Parameters: Output console
    // Return/Output: the complete record table
    //***************************************************************

    public void peek (Console c)
    {
	for (int count = 0 ; count < this.physicalSize ; count++)
	{
	    if (c.getRow () > 24)
	    {
		char next = 'a';
		c.println ();
		c.println ("Press any key to go to the next page...");
		next = c.getChar ();
		c.clear ();
	    }

	    if (this.dataBase [count] != null)
		c.println (this.dataBase [count].toString ());
	    else
		c.println ("Empty slot");
	}
	c.println ();
	c.println ("Physical size: " + this.physicalSize);
	c.println ("Logical size: " + this.logicalSize);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 12/1/2015
    // Purpose: returns the position of the record given its ID
    // Parameters: id (integer)
    // Return/Output: position (integer)
    //***************************************************************

    public int search (int id)
    {
	int pos = -1;
	int count = 0;
	int hash = this.h (id, count);

	while (this.dataBase [hash] != null && this.dataBase [hash].id != id)
	{
	    count++;
	    hash = this.h (id, count);

	}

	if (this.dataBase [hash] != null && this.dataBase [hash].id == id)
	    pos = hash;

	return pos;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 12/1/2015
    // Purpose: displays the complete record given its ID
    // Parameters: id (integer), output console, output switch
    // Return/Output: prints the complete record
    //***************************************************************

    public void find (int id, Console c, boolean output)
    {
	int position = this.search (id);
	if (!(position == -1))
	{
	    if (output)
		c.println("Record found!");
	    c.println (this.dataBase [position].toString ());
	}
	else
	    c.println ("Record with such ID does not exist");
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 12/1/2015
    // Purpose: deletes the record given its id
    // Parameters: id (integer), console c
    // Return/Output:
    //***************************************************************

    public void delete (int id, Console c)
    {
	int location = this.search (id);
	if (location == -1)
	    c.println ("Cannot delete, record with such ID does not exist");
	else
	{
	    c.println ("Record with ID " + id + " has been deleted");
	    c.println ("The record delected was: ");
	    this.find (id, c, false);
	    c.println ();
	    this.dataBase [location] = null;
	    this.logicalSize--;
	    this.rehash (c, true, false);
	}
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 12/1/2015
    // Purpose: changes the record given new characteristics
    // Parameters: id (integer), console c
    // Return/Output: -
    //***************************************************************

    public void change (int id, Console c)
    {
	int location = this.search (id);

	if (location == -1)
	    c.println ("Cannot change, previous record with such ID does not exist");
	else
	{
	    c.println ("The record that will be changed is: ");
	    this.find (id, c, false);

	    c.println ();
	    c.print ("Please enter the ID of the new record: ");
	    int newID = c.readInt ();

	    while (newID < 0)
	    {
		c.print ("ID cannot be negative, please enter another ID: ");
		newID = c.readInt ();
	    }

	    while (this.search (newID) != -1 && newID != id)
	    {
		c.print ("Another record with this ID already exists, please try another: ");
		newID = c.readInt ();
		while (newID < 0)
		{
		    c.print ("ID cannot be negative, please enter another ID: ");
		    newID = c.readInt ();
		}

	    }

	    c.print ("Please enter the title the new record: ");
	    String title = c.readLine ();

	    c.print ("Please enter the type of the new record: ");
	    String type = c.readLine ();

	    c.print ("Please enter the cost of the new record: ");
	    double cost = c.readDouble ();
	    while (cost < 0)
		{
		    c.print ("Cost cannot be negative, please re-enter the cost: ");
		    cost = c.readDouble ();
		}

	    c.print ("Please enter the distance away from the new record: ");
	    String distance = c.readLine ();

	    c.print ("Please enter the release date of the new record: ");
	    String date = c.readLine ();
	    c.println ();
	    if (newID == id)
	    {
		this.dataBase [location].title = title;
		this.dataBase [location].type = type;
		this.dataBase [location].cost = cost;
		this.dataBase [location].dist = distance;
		this.dataBase [location].date = date;
		c.println ("Record changed!");
		c.println ("The record is now: ");
		c.println (this.dataBase [location].toString ());
	    }
	    else
	    {

		this.delete (id, c);
		DBRecord newRecord = new DBRecord (newID, title, type, cost, distance, date);
		this.insert (newRecord, c, true);
		c.println ();
		c.println ("New record with ID " + newID + " has been added");
		c.println ("The new record added was: ");
		c.println (newRecord.toString ());
	    }

	}
    }


    //***************************************************************
    // Author: Don Smith
    // Date: Unknown
    // Purpose: returns the hash f()
    // Parameters: id, counter
    // Return/Output: the hash function
    //***************************************************************

    public int h (int k, int i)
    {
	return (k % this.physicalSize + i * h2 (k)) % this.physicalSize;
    }


    //***************************************************************
    // Author: Don Smith
    // Date: Unknown
    // Purpose: returns the double hash f()
    // Parameters: id
    // Return/Output: the double hash function
    //***************************************************************

    public int h2 (int k)
    {
	return (this.physicalSize - 2) - (k % (this.physicalSize - 2));
    }
}
