// The "Slltest" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 12/6/2015
// Purpose: To explore the properties, functions and uses of singly linked lists
//***************************************************************

public class Slltest
{
    static Console c;

    public static void main (String[] args) throws CloneNotSupportedException
    {
	c = new Console ();
	c.println ("Welcome to Singly Linked lists!");
	c.println ();

	SinglyLinkedList sglList = new SinglyLinkedList ();

	char command = 'i';
	while (command == 'i' || command == 'd' || command == 'f' || command == 'p' ||
		command == 'o' || command == 'e' || command == 'c' || command == 'r')
	{
	    c.println ();
	    c.println ("Please choose a command: 'i' to insert, 'd' to delete, 'f' to find, 'p' to print the list, " +
		    "'o' to print the odd nodes, 'e' to change two nodes, 'c' to concatenate another list, 'r' to reverse the list" +
		    " or press any other key to exit...");
	    c.println ();
	    command = Character.toLowerCase (c.getChar ());

	    if (command == 'i')
	    {
		c.print ("Insert selected! Please enter the string to be added: ");
		String input = c.readLine ();
		c.print ("Please enter the string in the node which will be inserted before: ");
		String before = c.readLine ();

		sglList.insert (input, before);
		c.println ();
		c.println ("Node inserted!");
	    }

	    else if (command == 'p')
	    {
		c.clear ();
		c.println ("Print selected! The list is now: ");
		c.println (sglList.toString ());
	    }

	    else if (command == 'd')
	    {
		c.print ("Deleted selected! Please enter the string of the node to delete: ");
		String del = c.readLine ();
		boolean isDel = sglList.delete (del);

		if (isDel)
		    c.println ("Node deleted!");
		else
		    c.println ("Node not found, no deletion was done");

	    }
	    else if (command == 'f')
	    {
		c.print ("Find selected! Please enter the string to be searched for: ");
		String target = c.readLine ();

		c.println ();
		c.println ("Enable self organizing search? (Y/N)");
		char sos = Character.toLowerCase (c.getChar ());

		while (sos != 'y' && sos != 'n')
		    sos = Character.toLowerCase (c.getChar ());

		Node search = sglList.find (target);

		if (search == null)
		{
		    if (sglList.isEmpty ())
			c.println ("List is empty! There's nothing to find!");
		    else
			c.println ("The node with the string '" + target + "' is the first node");
		}
		else if (search.next == null)
		    c.println ("Node with '" + target + "' does not exist");
		else
		{
		    c.println ("Node with '" + target + "' is after the node with '" + search.data + "'");
		    if (sos == 'y')
			sglList.selfOrganizingSearch (target);
		}
	    }

	    else if (command == 'o')
	    {
		c.println ("Printing odd nodes...");
		c.println (sglList.printOdd ());
	    }

	    else if (command == 'e')
	    {
		c.println ("Exange selected!");
		c.print ("Please enter the string of the first node: ");
		String first = c.readLine ();
		c.print ("Please enter the string of the second node: ");
		String second = c.readLine ();

		while (first.equals (second))
		{
		    c.println ();
		    c.println ("Your second string cannot be identical to the first one");
		    c.print ("Please re-enter the string of the second node: ");
		    second = c.readLine ();
		}

		sglList.exchange (first, second);
		c.println ("Done!");
	    }
	    else if (command == 'c')
	    {
		c.println ("Concatenate selected! For simplicity, the second list will be a sequence of numbers");
		c.print ("Please enter a size for the second list: ");
		int size = c.readInt ();

		while (size < 0)
		{
		    c.print ("Size cannot be negative, please re-enter a size for the second list: ");
		    size = c.readInt ();
		}

		SinglyLinkedList sglList2 = new SinglyLinkedList ();

		for (int count = 1 ; count <= size ; count++)
		{
		    sglList2.addEnd (count + "");
		}
		c.println ("The second list is:");
		c.println (sglList2.toString ());
		c.println ();
		c.println ("The concatenated list is:");
		c.println (sglList.concatenate (sglList2).toString ());
	    }
	    else if (command == 'r')
	    {
		sglList.reverse ();
		c.println ("List reversed! The list is now:");
		c.println (sglList.toString ());

	    }

	}
	c.close ();
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 12/6/2015
// Purpose/Description: Node object that can be used in a singly linked list
// Fields:
//      data - stores node data (String)
//      next - points to the next node (Node)
// Methods:
/*      toString - converts the data to a string
	clone - clones the node


*/
//***************************************************************

class Node implements Cloneable
{
    protected String data;
    protected Node next;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: Constructor for the node
    // Parameters: input (String)
    // Return/Output: -
    //***************************************************************

    public Node (String input)
    {
	this.data = input;
	this.next = null;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: converts node to a printable string
    // Parameters: -
    // Return/Output: String output
    //***************************************************************

    public String toString ()
    {
	String s = "";
	s = s + this.data + " ";
	if (this.next != null)
	    s = s + this.next;

	return s;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: Clones the node object
    // Parameters: -
    // Return/Output: Node object
    //***************************************************************

    public Object clone () throws CloneNotSupportedException
    {
	Node newObject = (Node) super.clone ();
	if (this.next == null)
	    newObject.next = null;
	else
	    newObject.next = (Node) this.next.clone ();
	return newObject;
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 12/6/2015
// Purpose/Description: A singly linked list class that handles nodes
// Fields:
//      list - linked list of nodes (Node)
// Methods:
/*      isEmpty - checks if the list is empty
	addEnd - adds node to end of list (added method)
	toString - converts the data to a string
	clone - clones the node
	find - find the node before the node containing a given string value (some exceptions apply)
	insert - inserts node with a string before the node with a given string (some exceptions apply)
	delete - deletes (if possible) a node with a given string value and returns true if deleted successfully
	printOdd - prints the odd nodes in the list
	selfOrganizingSearch - puts the node in the beginning of list if found
	concatenate - attaches another given list to the end of this current list and returns it
	exchange - exchanges two nodes
	lastNode - returns the last node in the list
	reverse - reverses the list

*/
//***************************************************************

class SinglyLinkedList implements Cloneable
{
    protected Node list;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: Constructor for the list
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public SinglyLinkedList ()
    {
	this.list = null;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: Checks if the list is empty
    // Parameters: -
    // Return/Output: true/false
    //***************************************************************

    public boolean isEmpty ()
    {
	return (this.list == null);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: adds a node at the end of the list
    // Parameters: input (string)
    // Return/Output: -
    //***************************************************************

    public void addEnd (String input)
    {
	Node newNode = new Node (input);
	Node tempNode;

	if (this.list == null)
	    this.list = newNode;
	else
	{
	    tempNode = this.list;
	    while (tempNode.next != null)
	    {
		tempNode = tempNode.next;
	    }
	    tempNode.next = newNode;
	}
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: Converts linked list to printable string
    // Parameters: -
    // Return/Output: String object
    //***************************************************************

    public String toString ()
    {
	String s;
	if (this.isEmpty ())
	    s = "Empty";
	else
	    s = this.list.toString ();
	return s;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: Clones the linked list
    // Parameters: -
    // Return/Output: SinglyLinkedList object
    //***************************************************************

    public Object clone () throws CloneNotSupportedException
    {
	SinglyLinkedList newObject = (SinglyLinkedList) super.clone ();
	if (this.list == null)
	    newObject.list = null;
	else
	    newObject.list = (Node) this.list.clone ();
	return newObject;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: finds the node before the node containing the given string
    // Parameters: input (string)
    // Return/Output: Node
    //***************************************************************

    public Node find (String input)
    {
	Node result = null;
	Node tempNode;

	if (this.isEmpty ())
	    result = null;
	else
	{
	    tempNode = this.list;

	    if (tempNode.data.equals (input))
		result = null;
	    else
	    {
		boolean found = false;

		while (tempNode.next != null && found == false)
		{
		    if (tempNode.next.data.equals (input))
		    {
			result = tempNode;
			found = true;
		    }
		    else
			tempNode = tempNode.next;
		}
		if (tempNode.next == null)
		    result = tempNode;
	    }
	}
	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: inserts a node with string input before a given second node
    //      with string input
    // Parameters: input (string), stringBefore (string)
    // Return/Output: -
    //***************************************************************

    public void insert (String input, String stringBefore)
    {
	Node tempNode = new Node (input);
	if (this.isEmpty ()) // If list is empty
	    this.list = tempNode;
	else if (this.find (stringBefore) == null) // If inserted before first node
	{
	    tempNode.next = this.list;
	    this.list = tempNode;
	}
	else if (this.find (stringBefore).next == null) // if node not found, inserted at the end
	{
	    this.addEnd (input);
	}
	else  // if inserted in the middle
	{
	    Node foundNode = this.find (stringBefore);
	    tempNode.next = foundNode.next;
	    foundNode.next = tempNode;

	}
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: deletes a node from the list with the given string, returns true
    //      if successfully deleted
    // Parameters: input (string)
    // Return/Output: true/false
    //***************************************************************

    public boolean delete (String input)
    {
	boolean del;
	if (this.isEmpty ())
	    del = false;

	else if (this.find (input) == null) // found at first node
	{
	    this.list = this.list.next;
	    del = true;
	}
	else if (this.find (input).next == null) // not found
	{
	    del = false;
	}
	else if (this.find (input).next.next == null) // found at last node
	{
	    this.find (input).next = null;
	    del = true;
	}
	else // found in middle
	{
	    this.find (input).next = this.find (input).next.next;
	    del = true;
	}
	return del;

    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Returns a string containing all the odd nodes
    // Parameters: -
    // Return/Output: output (String)
    //***************************************************************

    public String printOdd ()
    {
	String output = "";
	if (!this.isEmpty ())
	{
	    int count = 1;
	    Node temp = this.list;
	    while (temp != null)
	    {
		if (count % 2 != 0)
		    output = output + temp.data + " ";
		temp = temp.next;
		count++;

	    }
	}
	return output.trim ();
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: finds node with given string and places it the front of the list if found
    // Parameters: input (string)
    // Return/Output: Node
    //***************************************************************

    public Node selfOrganizingSearch (String input)
    {
	Node found;
	Node searched;
	if (this.isEmpty ()) // if empty list
	    found = null;

	else if (this.find (input) == null) // first item
	{
	    found = this.list;
	}
	else if (this.find (input).next == null) // not found
	{
	    found = null;
	}


	else if (this.find (input).next.next == null) // last item
	{
	    searched = this.find (input);
	    found = this.find (input).next;

	    searched.next.next = this.list;
	    this.list = searched.next;
	    searched.next = null;

	}
	else // found in the middle
	{
	    searched = this.find (input);
	    found = this.find (input).next;

	    Node tempFirst = searched.next;
	    searched.next = searched.next.next;
	    tempFirst.next = this.list;
	    this.list = tempFirst;

	}
	return found;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: concatenates two lists and returning the new one
    // Parameters: another list (SinglyLinkedList)
    // Return/Output: merged list (SinglyLinkedList)
    //***************************************************************

    public SinglyLinkedList concatenate (SinglyLinkedList given) throws CloneNotSupportedException
    {
	SinglyLinkedList merged = new SinglyLinkedList ();
	if (this.isEmpty ())
	    merged.list = given.list;
	else
	{
	    SinglyLinkedList copy = (SinglyLinkedList) this.clone ();
	    Node last = copy.list;
	    while (last.next != null)
		last = last.next;
	    last.next = given.list;
	    merged.list = copy.list;
	}
	return merged;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/18/2015
    // Purpose: exchanges two nodes in the list with the given strings
    //          input1 (node1) must physically come before input2 (node2)
    //          for it to work properly
    // Parameters: input1, input2 (strings)
    // Return/Output: -
    //***************************************************************

    public void exchange (String input1, String input2)
    {
	Node search1 = this.find (input1);
	Node search2 = this.find (input2);
	boolean ok1 = true;
	boolean ok2 = true;

	if (search1 != null)
	{
	    if (search1.next == null)
		ok1 = false;
	}

	if (search2 != null)
	{
	    if (search2.next == null)
		ok2 = false;
	}


	if (!this.isEmpty () && this.list.next != null && ok1 && ok2)
	{
	    if (search1 != null && search2 != null)
	    {
		if (search1.next == search2 || search2.next == search1) // consecutive not in beginning
		{
		    Node temp2 = search2.next;
		    search2.next = temp2.next;

		    temp2.next = search2;
		    search1.next = temp2;
		}

		else if (!(search1.next.next == null && search2.next.next == null))  // general case
		{
		    Node temp = search1.next;
		    search1.next = search2.next;

		    Node temp2 = search2.next.next;
		    search2.next.next = temp.next;
		    search2.next = temp;
		    temp.next = temp2;

		}
	    }
	    else if (search1 == null)
	    {
		if (this.list != search2) //first and other term, not consecutive
		{
		    Node temp = this.list;
		    this.list = search2.next;

		    Node temp2 = search2.next.next;
		    this.list.next = temp.next;
		    search2.next = temp;
		    temp.next = temp2;
		}
		else // first two terms
		{
		    Node temp = this.list.next;
		    this.list.next = temp.next;
		    temp.next = this.list;
		    this.list = temp;
		}
	    }
	}

    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/3/2016
    // Purpose: finds the last node in the list
    // Parameters: -
    // Return/Output: Node
    //***************************************************************

    public Node lastNode ()
    {
	Node temp = this.list;
	if (!this.isEmpty ())
	{
	    while (temp.next != null)
	    {
		temp = temp.next;
	    }
	}
	return temp;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/3/2016
    // Purpose: reverses the list
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public void reverse ()
    {
	if (!this.isEmpty ())
	{
	    if (this.list.next != null)
	    {
		SinglyLinkedList newStart = new SinglyLinkedList ();
		Node temp = this.lastNode ();

		while (temp != null)
		{
		    newStart.addEnd (temp.data);
		    this.delete (temp.data);
		    temp = this.lastNode ();
		}

		this.list = newStart.list;
	    }
	}
    }
}
