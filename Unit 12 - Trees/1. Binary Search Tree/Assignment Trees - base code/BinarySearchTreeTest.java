// The "BinarySearchTreeTest" class.
//Input:    None
//Output:   None
//Purpose:  To run given main.
import hsa.*;
import java.util.*;

public class BinarySearchTreeTest
{
    static Console c = new Console ();

    public static void main (String[] args)
    {
    }
}

//Class Node
//Fields:   int data    -   the information held by the Node
//          Node right  -   the next node to the right of the tree
//          Node left   -   the next node to the left of the tree
class Node
{
    public int data;
    public Node left;
    public Node right;

    public Node (int info)
    {
	this.data = info;
	this.left = null;
	this.right = null;
    }


    public Node ()
    {
	this (0);
    }
}
//Class QNode
//Fields:   Node data   -   the information held by the QNode
//          QNode next  -   the next node in the list
class QNode
{
    public Node data;
    public QNode next;

    public QNode (Node info)
    {
	this.data = info;
	this.next = null;
    }


    public QNode ()
    {
	this (null);
    }
}

//Class Queue
//Fields:   QNode front -   the front of the queue
//          QNode back  -   the back of the queue
//Methods:  add     -   adds a given node to the Queue
//          remove  -   removes a node at the front and returns removed node
//          empty   -   returns true if queue is empty
class Queue
{
    public QNode front;
    public QNode back;

    public Queue ()
    {
	this.front = null;
	this.back = null;
    }


    //add
    //Purpose:      adds a node at the end of the queue
    //Parameters:   Node x   -   the node being added
    //Output:       None
    public void add (Node x)
    {
	if (this.isEmpty ())
	{
	    this.front = new QNode(x);
	    this.back = this.front;
	}
	else
	{
	    this.back.next = new QNode(x);
	    this.back = this.back.next;
	}
    }


    //remove
    //Purpose:      removes a node at the front of the queue
    //Parameters:   None
    //Output:       the node that was removed
    public Node remove ()
    {
	Node ans;

	ans = this.front.data;
	this.front = this.front.next;
	return ans;
    }


    //isEmpty
    //Purpose:      returns true if array is empty
    //Parameters:   None
    //Output:       true or false
    public boolean isEmpty ()
    {
	boolean status = false;
	if (this.front == null)
	    status = true;
	return status;
    }
}

//Class BinarySearchTree
//Fields:   Node root           -   the root of the tree
//Methods:  add                 -   adds a leaf to the tree with given info
//          find                -   returns the node with the given information

class BinarySearchTree
{
    protected Node root;

    public BinarySearchTree ()
    {
	root = null;
    }


    //add
    //Purpose:      adds a leaf to the tree
    //Parameters:   int x   -   the info of the leaf being added
    //Output:       true or false
    public boolean add (int info)
    {
	
	if (this.root == null)
	    this.root = new Node (info);
	else
	{
	    Node ptr = this.root;
	    while (ptr != null)
	    {
		if (info < ptr.data)
		    if (ptr.left != null)
			ptr = ptr.left;
		    else
		    {
			ptr.left = new Node (info);
			return true;
		    }
		else if (info > ptr.data)
		    if (ptr.right != null)
			ptr = ptr.right;
		    else
		    {
			ptr.right = new Node (info);
			return true;
		    }
		else if (info == ptr.data)
		{
		    return false;
		}
	    }
	}
	return true;
    }


    //find
    //Purpose:      finds the node with the given information
    //Parameters:   int x   -   the info of the node that is being looked for
    //Output:       returns the node with the given information
    public Node find (int x)
    {
	Node ptr;
	ptr = this.root;
	while (ptr != null && ptr.data != x)
	    if (x < ptr.data)
		ptr = ptr.left;
	    else
		ptr = ptr.right;
	return ptr;
    }


    /************************************************
    Method:printTree
    Purpose:Prints the tree in a normal way.
	(Can't handle super wide trees.)
	-  Developed by Jonathan Chan, May 1997
	-  Java version by Edmund Wong, Dec 2000
	-  Modified by Ian Halliday, June 2001
    Parameters: The Console
    Return Value: None
    *************************************************/
    public void printTree (Console c)
    {
	c.setCursor
	    (rprintTree (c, root, c.getRow ()), 1);
    }


    /************************************************
    Method: rprintTree
    Purpose: See above.
    Parameters: the console, the node being printed
		and the row to print on
    Return Value: The maximum row reached by
		  printing (allowing for useful
		  cursor placement)
    *************************************************/
    protected int rprintTree (Console c, Node n, int r)
    {
	int maxRow;
	if (n != null)
	{
	    maxRow = rprintTree (c, n.left, r + 2);
	    c.setCursor (r, c.getColumn ());
	    c.print (n.data, 3);
	    maxRow = Math.max (maxRow, rprintTree (c, n.right, r + 2));
	    return maxRow;
	}
	return r - 1;
    }

}


