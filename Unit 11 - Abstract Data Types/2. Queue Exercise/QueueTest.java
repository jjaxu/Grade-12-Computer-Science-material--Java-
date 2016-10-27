// The "QueueTest" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 1/13/2016
// Purpose: Implement and demonstrate queues with arrays and linked lists
//***************************************************************

public class QueueTest
{
    static Console c;      

    public static void main (String[] args)
    {
	
	c = new Console ();
	int count = 1;
	char a = 'a';
	ArrayQueue q1 = new StraightQueue(10);
	ArrayQueue q2 = new ShiftedQueueA(10);
	ArrayQueue q3 = new ShiftedQueueB(10);
	LLQueue lq = new LLQueue();
	
	c.println("Welcome to Queues!");
	c.println("==================");
	c.println("Press 'a' to add a number...");
	c.println("Press 'd' to delete a number...");
	c.println("Press 'r' to reset the queue...");
	c.println("Press 'q' to exit...");
	
	while (a != 'q')
	{
	    a = c.getChar ();
	    c.clear();
	    c.println("============================");
	    c.println("Press 'a' to add a number...");
	    c.println("Press 'd' to delete a number...");
	    c.println("Press 'e' to extend queue(arrays only)...");
	    c.println("Press 'r' to reset the queue...");
	    c.println("Press 'q' to quit...");            
	    c.println("============================");
	    c.println();
	    
	    if (a == 'a')
	    {
		q1.add (count);
		q2.add (count);
		q3.add (count);
		lq.add (count);
		count++;
	    }
	    else if (a == 'r')
	    {
		q1.reset ();
		q2.reset ();
		q3.reset ();
		lq.reset ();
	    }
	    else if (a == 'd')
	    {
		q1.remove ();
		q2.remove ();
		q3.remove ();
		lq.remove ();
	    }
	    else if (a == 'e')
	    {
		q1.extend ();
		q2.extend ();
		q3.extend ();
	    }
	    
	    c.println("'()' indicates where the front of the queue is");
	    c.println("'[]' indicates where the back of the queue is");
	    c.println("'{}' are used if the front and back overlaps");
	    
	    c.println();
	    c.println("Straight Queue:");
	    c.println (q1.toString ());
	    
	    c.println();
	    c.println("Shifting queue type A:");
	    c.println (q2.toString ());
	    
	    c.println();
	    c.println("Shifting queue type B:");
	    c.println (q3.toString ());
	    
	    c.println();
	    c.println("Linked list queue:");
	    c.println (lq.toString ());

	}
	c.close();
	
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 1/13/2016
// Purpose/Description: Abstract class that can become a specific type queue
// Fields:
//      size - size of queue (array)
//      queue - integer array
//      front - index of front of array
//      back - index of back of array
// Methods:
/*      add - abstract at this level
	remove - abstract at this level
	reset - abstract at this level
	isFull - abstract at this level
	isEmpty - abstract at this level
	extend - abstract at this level

*/
//***************************************************************

abstract class ArrayQueue
{
    protected int size;
    protected int[] queue;
    protected int front;
    protected int back;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/13/2016
    // Purpose: Constructor that creates a queue, given a size
    // Parameters: size (int)
    // Return/Output: -
    //***************************************************************

    public ArrayQueue (int size)
    {
	this.size = Math.abs (size);
	this.queue = new int [this.size];
	this.front = 0;
	this.back = 0;
    }


    abstract void add (int num);
    abstract int remove ();
    abstract void reset ();
    abstract boolean isFull ();
    abstract boolean isEmpty ();
    abstract void extend();
}

//***************************************************************
// Author: Jackie Xu
// Date: 1/13/2016
// Purpose/Description: Straight line array queue, inherited from Queue
// Fields:
//      All fields from ArrayQueue
// Methods:
/*
	isFull - checks if queue is full
	isEmpty - checks if queue is empty
	add - adds given number to the end of queue
	remove - removes an element from the queue
	reset - resets the queue
	toString - returns the queue as a string
	extend - expands the queue's physical size if exceeded
*/
//***************************************************************

class StraightQueue extends ArrayQueue
{
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/13/2016
    // Purpose: Constructor that creates a straight-line queue
    // Parameters: size
    // Return/Output: -
    //***************************************************************

    public StraightQueue (int size)
    {
	super (size);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/13/2016
    // Purpose: checks if the queue is full
    // Parameters: -
    // Return/Output: true/false
    //***************************************************************

    public boolean isFull ()
    {
	return (this.back == this.size);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/17/2016
    // Purpose: checks if the queue is empty
    // Parameters: -
    // Return/Output: true/false
    //***************************************************************

    public boolean isEmpty ()
    {
	boolean empty = true;
	for (int count = 0 ; count < this.queue.length ; count++)
	{
	    if (this.queue [count] != 0)
	    {
		empty = false;
		count = this.queue.length;
	    }
	}
	return empty;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/13/2016
    // Purpose: adds given number to the end of queue
    // Parameters: num (integer)
    // Return/Output: -
    //***************************************************************

    public void add (int num)
    {
	if (!this.isFull ())
	{
	    this.queue [this.back] = num;
	    this.back++;

	}
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/13/2016
    // Purpose: removes an item from the front of the queue and returns it
    // Parameters: -
    // Return/Output: out (integer)
    //***************************************************************

    public int remove ()
    {
	int out = -1;
	if (this.front < this.size && this.queue [this.front] != 0)
	{
	    out = this.queue [this.front];
	    this.queue [this.front] = 0;
	    this.front++;
	}
	return out;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/13/2016
    // Purpose: resets queue with original size
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public void reset ()
    {
	this.front = 0;
	this.back = 0;
	this.queue = new int [this.size];
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/17/2016
    // Purpose: Converts the array into printable string along with its size
    // Parameters: -
    // Return/Output: string output
    //***************************************************************

    public String toString ()
    {
	String result = "";
	int length = this.queue.length;

	for (int count = 0 ; count < length ; count++)
	{
	    result = result + " ";
	    if (count == front && count == back)
	    {
		result = result + "{" + this.queue [count] + "}";
	    }
	    else if (count == front)
	    {
		result = result + "(" + this.queue [count] + ")";
	    }
	    else if (count == back)
	    {
		result = result + "[" + this.queue [count] + "]";
	    }
	    else 
		result = result + this.queue [count];
	}
	return "[" + result.trim() + "] " + " (Size: " + this.size + ")";
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/17/2016
    // Purpose: increase the size of the queue by 1
    // Parameters: -
    // Return/Output: -
    //***************************************************************
    
    public void extend ()
    {
	int [] temp = new int [this.size + 1];
	for (int count = 0; count < this.size; count++)
	{
	    temp [count] = this.queue[count];
	}
	this.queue = temp;
	this.size++;
    } 
}

//***************************************************************
// Author: Jackie Xu
// Date: 1/13/2016
// Purpose/Description: Shifted array queue type A, inherited from Straight line queue
// Fields:
//      All fields from StraightQueue
// Methods:
/*      All methods from StraightQueue
	add (overwritten)- adds given number to the end of queue
	    adjusts queue as needed

*/
//***************************************************************

class ShiftedQueueA extends StraightQueue
{
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/13/2016
    // Purpose: Constructor that creates a shifted array queue
    // Parameters: size
    // Return/Output: -
    //***************************************************************

    public ShiftedQueueA (int size)
    {
	super (size);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/13/2016
    // Purpose: adds given number to the end of queue, shifts array as needed
    // Parameters: num (integer)
    // Return/Output: -
    //***************************************************************

    public void add (int num)
    {
	if (!this.isFull ())
	{
	    this.queue [this.back] = num;
	    this.back++;
	}
	else
	{
	    int[] temp = new int [this.size];
	    int count = 0;
	    for (int count2 = 0 ; count2 < this.size ; count2++)
	    {
		if (this.queue [count2] != 0)
		{
		    temp [count] = this.queue [count2];
		    count++;
		}
	    }
	    this.front = 0;
	    this.back = count;
	    this.queue = temp;
	    super.add (num);

	}
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 1/14/2016
// Purpose/Description: Shifted array queue type B, inherited from Straight line queue
//                      (front is always at index 0)
// Fields:
//      All fields from StraightQueue
// Methods:
/*      All methods from StraightQueue
	remove (overwritten)- removes number from beginning of queue and shifts arary

*/
//***************************************************************

class ShiftedQueueB extends StraightQueue
{
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/14/2016
    // Purpose: Constructor that creates a shifted array queue type B
    // Parameters: size
    // Return/Output: -
    //***************************************************************

    public ShiftedQueueB (int size)
    {
	super (size);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/14/2016
    // Purpose: removes an item from the front of the queue, and shifts queue forward in array
    // Parameters: -
    // Return/Output: out (integer)
    //***************************************************************

    public int remove ()
    {
	int out = this.queue [this.front];
	int last = 0;
	if (out != 0)
	{
	    last = this.queue [this.back - 1];
	    this.queue [this.back - 1] = 0;
	    this.back--;
	}
	for (int count = 1 ; count <= this.back ; count++)
	{

	    this.queue [count - 1] = this.queue [count];
	}
	if (back != 0)
	    this.queue[back - 1] = last;
	return out;
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 12/6/2015
// Purpose/Description: Node object that can be used in a singly linked list
// Fields:
//      data - stores node data (integer)
//      next - points to the next node (Node)
// Methods:
/*      toString - converts the data to a string
*/
//***************************************************************

class Node
{
    protected int data;
    protected Node next;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: Constructor for the node
    // Parameters: num (int)
    // Return/Output: -
    //***************************************************************

    public Node (int num)
    {
	this.data = num;
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
}


//***************************************************************
// Author: Jackie Xu
// Date: 1/13/2016
// Purpose/Description: Linked List implementation of a queue
// Fields:
//      size - size of queue
//      front - Node pointer to front of queue
//      back - Node pointer to back of queue
// Methods:
//      isEmpty - checks if queue is empty
//      add - adds node to the end of queue
//      remove - removes a node from the front of queue
//      reset - resets the queue
//      toString - returns the queue as a string
//***************************************************************

class LLQueue
{
    protected int size;
    protected Node front;
    protected Node back;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/13/2016
    // Purpose: Constructor for the Linked list queue
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public LLQueue ()
    {
	this.size = 0;
	this.front = null;
	this.back = null;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/14/2016
    // Purpose: Checks if the queue is empty
    // Parameters: -
    // Return/Output: true/false
    //***************************************************************

    public boolean isEmpty ()
    {
	return (this.front == null && this.back == null);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/14/2016
    // Purpose: adds given number to the end of queue
    // Parameters: num (integer)
    // Return/Output: -
    //***************************************************************

    public void add (int num)
    {
	Node added = new Node (num);

	if (this.isEmpty ())
	    this.front = added;
	else
	    this.back.next = added;
	this.back = added;
	this.size++;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/14/2016
    // Purpose: removes an item from the front of the queue, if not empty
    // Parameters: -
    // Return/Output: out (integer)
    //***************************************************************

    public int remove ()
    {
	int out = 0;
	if (!this.isEmpty ())
	{
	    out = this.front.data;

	    if (this.front.next != null)
	    {
		this.front = this.front.next;
	    }
	    else
	    {
		this.front = null;
		this.back = null;
	    }
	    this.size--;
	}
	return out;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/14/2016
    // Purpose: resets queue
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public void reset ()
    {
	this.front = null;
	this.back = null;
	this.size = 0;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/14/2016
    // Purpose: Converts linked list to printable string
    // Parameters: -
    // Return/Output: String object
    //***************************************************************

    public String toString ()
    {
	String s;
	if (this.isEmpty ())
	    s = "(front) Empty (back)";
	else
	{
	    s = this.front.toString ();
	    s = "(front) " + s + " (back)";
	}
	return s;
    }
}
