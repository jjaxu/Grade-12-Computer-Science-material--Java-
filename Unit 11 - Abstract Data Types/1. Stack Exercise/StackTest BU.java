// The "StackTest" class.
import java.awt.*;
import hsa.*;

//***************************************************************
// Author: Jackie Xu
// Date: 1/7/2016
// Purpose: To do proper bracket checks with a stack
//***************************************************************

public class StackTest
{
    static Console c;
    
    public static void main (String[] args)
    {
	c = new Console ();
	c.println("Welcome to Brackets check!");
	c.println("=========================");
	c.println();
	c.println("Please choose a command:");
	c.println("'1' for manual input");
	c.println("'2' to read from a file");
	c.println("Any other key to exit");
	c.println();
	Parenthesis p;
	
	char command = c.getChar();
	while (command == '1' || command == '2')
	{
	    if (command == '1')
	    {
		c.println("Manual input chosen!");
		c.print("Please enter the expression: ");
		String exp = c.readLine();
		p = new Parenthesis (exp, c);
		
		c.println();
		c.println("Your expression is:");
		c.println(p.expression);
		c.println("Bracket status: " + p.bracketCheck());
	    }
	    else
	    {
		p = new Parenthesis ("", c);
		c.println("File input chosen!"); 
		c.println(p.readFromFile(c));;
	    }
	    
	    c.println();
	    c.println("========================");
	    c.println("Please choose a command:");
	    c.println("'1' for manual input");
	    c.println("'2' to read from a file");
	    c.println("Any other key to exit");
	    c.println("========================");
	    command = c.getChar();
	    c.println();

	}
	c.close();

    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 12/6/2015
// Purpose/Description: Node object that can be used in a singly linked list
// Fields:
//      data - char
//      next - points to the next node
// Methods:
/*      toString - converts the data to a string
	clone - clones the node


*/
//***************************************************************

class Node implements Cloneable
{
    protected char data;
    protected Node next;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: Constructor for the node
    // Parameters: input (char)
    // Return/Output: -
    //***************************************************************

    public Node (char input)
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
// Date: 1/7/2016
// Purpose/Description: Singly linked listed implementation of a stack
// Fields:
//      list - linked list of nodes
// Methods:
/*      isEmpty - checks if the list is empty
	toString - converts the data to a string
	clone - clones the node
	push - adds item onto stack
	pop - removes node from stack and returns it
*/
//***************************************************************

class Stack implements Cloneable
{
    protected Node list;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: Constructor for the list
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public Stack ()
    {
	this.list = null;
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
    // Date: 1/7/2016
    // Purpose: pushes a node on to stack
    // Parameters: input (char)
    // Return/Output: -
    //***************************************************************

    public void push (char input)
    {
	Node tempNode = new Node (input);
	if (this.isEmpty ()) // If list is empty
	    this.list = tempNode;
	else  // general case
	{
	    tempNode.next = this.list;
	    this.list = tempNode;
	}
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/7/2016
    // Purpose: pops an item from the stack and returns the char
    // Parameters: -
    // Return/Output: char
    //***************************************************************
    
    public char pop ()
    {
	char popped = '|';
	if (!this.isEmpty())
	{
	    popped = this.list.data;
	    this.list = this.list.next;
	}
	return popped;
    }
}


//***************************************************************
// Author: Jackie Xu
// Date: 1/7/2016
// Purpose/Description: class that checks for proper brackets format using a stack
// Fields:
//      expression - String
//      c - Console
//      stack - Stack
// Methods:
/*      bracketCheck - checks if the brackets are in the proper format from its string field
	readFromFile - checks if the brackets are in the proper format from a text file given by user
	
*/
//***************************************************************

class Parenthesis
{
    protected String expression;
    protected Console c;
    protected Stack stack;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/7/2016
    // Purpose: Constructor for the parenthesis class
    // Parameters: -
    // Return/Output: -
    //***************************************************************
    
    public Parenthesis (String exp, Console c)
    {
	this.expression = exp;
	this.c = c;
	this.stack = new Stack();
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/7/2016
    // Purpose: Constructor for the parenthesis class
    // Parameters: -
    // Return/Output: -
    //***************************************************************    
    
    public void getString ()
    {
	this.expression = this.c.readLine();
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/10/2016
    // Purpose: checks if brackets are properly formatted
    // Parameters: -
    // Return/Output: String
    //*************************************************************** 
    
    public String bracketCheck ()
    {
	String message = "Good Structure";
	int count = 0;
	char character = ' ';
	boolean stop = false;
	this.stack.list = null;
	
	while (count < this.expression.length() && stop == false)
	{
	    character = this.expression.charAt(count);
	    
	    if (character == '(' || character == '[' || character == '{')
		this.stack.push(character);

	    else if (character == ')')
	    {
		if (this.stack.isEmpty())
		{
		    stop = true;
		    message = "Too many close brackets";
		    
		}
		else if (this.stack.pop() != '(')
		{
		    stop = true;
		    message = "Mismatched brackets";
		}
		
	    }
	    else if (character == ']')
	    {
		if (this.stack.isEmpty())
		{
		    stop = true;
		    message = "Too many close brackets";
		}
		else if (this.stack.pop() != '[')
		{
		    stop = true;
		    message = "Mismatched brackets";
		}
	    }
	    else if (character == '}')
	    {
		if (this.stack.isEmpty())
		{
		    stop = true;
		    message = "Too many close brackets";
		}
		else if (this.stack.pop() != '{')
		{
		    stop = true;
		    message = "Mismatched brackets";
		}
	    }
	    count++;        
	}
	
	c.println();
	if (!this.stack.isEmpty() && !stop)
	    message = "Too many open brackets";
	else if (stop)
	    message = message + "\nError location: character number " + (count) + ": '" + character + "'";
	    
	    
	return message;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/10/2016
    // Purpose: checks if brackets are properly formatted from a .txt file
    // Parameters: Console
    // Return/Output: String
    //***************************************************************
    
    public String readFromFile(Console c)
    {
	c.print("Please enter the file name: ");
	String fileName = c.readLine () + ".txt";
	TextInputFile input = new TextInputFile (fileName);
	char character = ' ';
	String line;
	String message = "Good Structure";
	int count = 0;
	int lineNum = 0;
	boolean stop = false;
	this.stack.list = null;
	
	c.println();
	c.println("File: ");
	while (!input.eof())
	{
	    line = input.readLine();
	    c.println(line);
	    
	    if (!stop)
	    {
		lineNum++;
		count = 0;
	    }
	    
	    while(count < line.length() && stop == false)
	    {   
		character = line.charAt(count);
		if (character == '(' || character == '[' || character == '{')
		    this.stack.push(character);
		
		else if (character == ')')
		{
		    if (this.stack.isEmpty())
		    {
			stop = true;
			message = "Too many close brackets";
			
		    }
		    else if (this.stack.pop() != '(')
		    {
			stop = true;
			message = "Mismatched brackets";
		    }
		   
		}
		else if (character == ']')
		{
		    if (this.stack.isEmpty())
		    {
			stop = true;
			message = "Too many close brackets";
		    }
		    else if (this.stack.pop() != '[')
		    {
			stop = true;
			message = "Mismatched brackets";
		    }
		}
		else if (character == '}')
		{
		    if (this.stack.isEmpty())
		    {
			stop = true;
			message = "Too many close brackets";
		    }
		    else if (this.stack.pop() != '{')
		    {
			stop = true;
			message = "Mismatched brackets";
		    }
		}
		count++;

	    }

	}
	
	if (!this.stack.isEmpty() && !stop)
	    message = "Too many open brackets";                
	else if (stop)
	{
	    message = message + "\nError location: Line number " + lineNum + ", character number " + (count) + ": '" + character + "'"; 
	}  
	c.println();
	return message;
	
    }
}
