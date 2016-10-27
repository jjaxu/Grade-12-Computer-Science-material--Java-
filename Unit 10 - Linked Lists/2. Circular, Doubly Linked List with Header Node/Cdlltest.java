// The "Cdlltest" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 12/24/2015
// Purpose: To explore the properties, functions and uses of a circular linked list
//          with a header node
//***************************************************************

public class Cdlltest
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();

	c.println ("Welcome to Circular Linked Lists!");
	c.println ();
	CircularList mainList = new CircularList ();

	char command = 'i';
	while (command == 'i' || command == 'r' || command == 'l' || command == 'd' ||
		command == 's' || command == 'n')
	{
	    c.println ("============================================================================");
	    c.println ("Please choose a command: 'i' to insert a node, 'd' to delete, 'n' to see the number of nodes, " +
		    "'l' to print the list going left, 'r' to print the list going right, 's' to split the list, or press any other key to exit...");
	    c.println ();
	    command = Character.toLowerCase (c.getChar ());

	    if (command == 'i')
	    {
		c.print ("Insert selected! Please enter the number to insert: ");
		int num = c.readInt ();
		c.print ("Please enter the number to be inserted before \n(if not found it will be inserted at the end): ");
		int before = c.readInt ();
		mainList.insertLeft (num, before);
		c.println ();
		c.println ("Node inserted!");
		c.println ();
	    }
	    else if (command == 'r')
	    {
		c.clear ();
		c.println ("Here the list of the numbers, going right:");
		c.println (mainList.toStringRight ());
	    }
	    else if (command == 'l')
	    {
		c.clear ();
		c.println ("Here the list of the numbers, going left:");
		c.println (mainList.toStringLeft ());
	    }
	    else if (command == 'n')
	    {
		c.println ("The list currently has " + mainList.numNodes () + " node(s)");
	    }
	    else if (command == 'd')
	    {
		c.print ("Delete selected! Please enter the number to be deleted: ");
		int num = c.readInt ();
		boolean del = mainList.delete (num);

		if (del)
		    c.println ("Node with number " + num + " has been deleted");
		else
		    c.println ("Node not found, no deletion was done");
		c.println ();
	    }
	    else if (command == 's')
	    {
		c.println ("Split selected!");

		if (mainList.numNodes () % 2 == 0)
		{
		    CircularList second = mainList.splitInTwo ();
		    c.println ("The first list is now (going right):");
		    c.println (mainList.toStringRight ());

		    c.println ();
		    c.println ("The newly split list is (going right):");
		    c.println (second.toStringRight ());
		}
		else
		    c.println ("Cannot split, number of nodes not even");
	    }
	}
	c.close ();
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 12/24/2015
// Purpose/Description: Node object that can be used in a doubly linked list
// Fields:
//      data - integer
//      left - points to the previous node
//      right - points to the next node
// Methods:
//      -
//***************************************************************

class Node
{
    public int data;
    public Node left;
    public Node right;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/24/2015
    // Purpose: Constructor for the node
    // Parameters: input (integer)
    // Return/Output: -
    //***************************************************************

    public Node (int input)
    {
	this.data = input;
	this.left = null;
	this.right = null;
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 12/24/2015
// Purpose/Description: A doubly linked list class that handles nodes with a header node
// Fields:
//      list - linked list of nodes
// Methods:
/*      numNodes - returns the total number of nodes (value in header node)
	find - returns the node that contains the search value
	addEnd - adds a node to the end of the list
	insertLeft - inserts a node on the left of a given value
	toStringLeft - prints a list of values going left and total number of nodes
	toStringRight - prints a list of values going right and total number of nodes
	delete - deletes a node given its value (if possible)
	splitInTwo - splits list into two list (if even number of nodes), keeping only the first half  and returns
		    the second half as a new list

*/
//***************************************************************

class CircularList
{
    public Node header;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/24/2015
    // Purpose: Constructor for the circular linked list
    // Parameters: input (integer)
    // Return/Output: -
    //***************************************************************

    public CircularList ()
    {
	this.header = new Node (0);
	this.header.left = this.header;
	this.header.right = this.header;
	this.header.data = 0;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/24/2015
    // Purpose: Returns the number of nodes in the list (header doesn't count)
    // Parameters: -
    // Return/Output: number of nodes (int)
    //***************************************************************

    public int numNodes ()
    {
	return this.header.data;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/26/2015
    // Purpose: Finds and returns a node given its value
    // Parameters: input (int)
    // Return/Output: Node
    //***************************************************************

    public Node find (int input)
    {
	Node found = null;

	if (this.numNodes () != 0)
	{
	    Node temp = this.header.right;
	    do
	    {
		if (temp.data == input)
		{
		    found = temp;
		}
		temp = temp.right;
	    }
	    while (temp != this.header.right);
	}


	return found;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/26/2015
    // Purpose: adds a node with a given value at the end of the list (for testing purposes)
    // Parameters: input (int)
    // Return/Output: -
    //***************************************************************

    public void addEnd (int input)
    {
	Node added = new Node (input);
	if (this.numNodes () == 0)
	{
	    this.header.right = added;
	    this.header.left = added;
	    added.right = added;
	    added.left = added;
	}
	else
	{
	    Node last = this.header.right.left;

	    last.right = added;
	    added.right = this.header.right;
	    added.left = last;

	    this.header.left = added;
	    this.header.right.left = added;

	}
	this.header.data++;

    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/24/2015
    // Purpose: inserts a node on the left of another node's value (if not found, it is inserted to the left of                 the header aka. the end of the list
    // Parameters: input, before (int)
    // Return/Output: -
    //***************************************************************

    public void insertLeft (int input, int before)
    {
	Node added = new Node (input);

	if (this.numNodes () == 0) // empty list
	{
	    this.header.right = added;
	    this.header.left = added;
	    added.right = added;
	    added.left = added;
	}
	else
	{
	    Node beforeNode = this.find (before);
	    if (beforeNode != null) // if node before is found
	    {
		if (beforeNode.left == this.header.left) // before first node
		{
		    added.right = this.header.right;
		    added.left = this.header.left;
		    this.header.left.right = added;
		    this.header.right.left = added;
		    this.header.right = added;

		}
		else // general case
		{

		    added.right = beforeNode;
		    beforeNode.left.right = added;
		    added.left = beforeNode.left;
		    beforeNode.left = added;
		}
	    }
	    else // not found (added to end)
	    {
		this.addEnd (input);
		this.header.data--;
	    }
	}
	this.header.data++;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/3/2016
    // Purpose: Creates a string of the list of values, going left
    // Parameters: -
    // Return/Output: output (String)
    //***************************************************************

    public String toStringLeft ()
    {
	Node temp = this.header.left;
	String output = "";
	if (this.numNodes () == 0)
	    output = "(0)";
	else
	{
	    do
	    {
		output = output + temp.data + " ";
		temp = temp.left;
	    }
	    while (temp != this.header.left);

	    output = output + "(" + this.numNodes () + ")";
	}
	return output;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/3/2016
    // Purpose: Creates a string of the list of values, going right
    // Parameters: -
    // Return/Output: output (String)
    //***************************************************************

    public String toStringRight ()
    {
	Node temp = this.header.right;
	String output = "";
	if (this.numNodes () == 0)
	    output = "(0)";
	else
	{
	    do
	    {
		output = output + temp.data + " ";
		temp = temp.right;
	    }
	    while (temp != this.header.right);

	    output = output + "(" + this.numNodes () + ")";
	}
	return output;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/3/2016
    // Purpose: Deletes a node with a given value, and returns true if successfully deleted
    // Parameters: input (int)
    // Return/Output: true/false
    //***************************************************************

    public boolean delete (int input)
    {
	Node found = this.find (input);
	boolean deleted = true;

	if (found != null) // if found
	{
	    if (this.numNodes () == 1) // 1 element list
	    {
		this.header.left = this.header;
		this.header.right = this.header;
	    }
	    else if (found == this.header.right) // if first element
	    {
		this.header.right = found.right;
		found.right.left = this.header.left;
		this.header.left.right = found.right;
	    }
	    else if (found == this.header.left) // if last element
	    {
		found.left.right = this.header.right;
		this.header.right.left = found.left;
		this.header.left = found.left;
	    }
	    else // general case
	    {
		found.left.right = found.right;
		found.right.left = found.left;
	    }
	    this.header.data--;
	}
	else
	    deleted = false;
	return deleted;

    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/3/2016
    // Purpose: Splits list with a even number of nodes into two lists, keeping the first half on the original  list and returning the second half
    // Parameters: -
    // Return/Output: CircularList object
    //***************************************************************

    public CircularList splitInTwo ()
    {
	CircularList second = new CircularList ();
	if (this.numNodes () % 2 == 0 && this.numNodes () != 0) // if even number of nodes
	{

	    if (this.numNodes () == 2) // if only 2 items
	    {
		second.addEnd (this.header.left.data);
		this.delete (this.header.left.data);
	    }
	    else // general case
	    {
		Node cut = this.header.right;
		for (int count = 1 ; count <= this.numNodes () / 2 ; count++)
		{
		    cut = cut.right;
		}
		Node last = cut.left;

		do // creates seconds list
		{
		    second.addEnd (cut.data);
		    cut = cut.right;
		}
		while (cut != this.header.right);

		last.right = this.header.right;  //cuts old list
		this.header.right.left = last;
		this.header.left = last;
		this.header.data = this.header.data - this.numNodes () / 2;
	    }
	}
	return second;
    }
}
