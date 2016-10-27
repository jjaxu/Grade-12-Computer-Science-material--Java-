// The "BinarySearchTreeTest" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 1/15/2016
// Purpose: To learn and understand the functions and uses of binary trees
//***************************************************************

public class BinarySearchTreeTest
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console ();
	BinarySearchTree tree = new BinarySearchTree ();
	c.println ("Welcome to Binary Search Trees!!!");
	c.println ();
	char command = 'a';
	while (command != 'e')
	{
	    c.println ("Press '1' to make your own tree");
	    c.println ("Press '2' to generate a random tree");
	    c.println ("Press 'e' to exit");
	    c.println ("===================================");
	    while (command != '1' && command != '2' && command != 'e')
		command = c.getChar ();

	    c.clear ();

	    tree.root = null;
	    if (command == '2')
	    {
		int rand = 0;
		int limit = 0;

		limit = (int) (Math.random () * 20) + 3;

		for (int count = 0 ; count < limit ; count++)
		{
		    rand = (int) (Math.random () * 30) + 1;
		    tree.add (rand);

		}

	    }

	    while (command != '5' && command != 'e')
	    {
		c.clear ();
		c.println ("Press '1' to add a node");
		c.println ("Press '2' to see properties");
		c.println ("Press '3' to find level");
		c.println ("Press '4' to find a node's sibling");
		c.println ("Press '5' to go back");
		c.println ("=================================");
		c.println ();
		tree.printTree (c);
		command = c.getChar ();


		while (command != '1' && command != '2' && command != '3' && command != '4' && command != '5')
		    command = c.getChar ();

		c.println ();
		if (command == '1')
		{
		    c.print ("Please enter the number to be added (integer): ");
		    int added = c.readInt ();
		    tree.add (added);

		}
		else if (command == '2')
		{
		    c.println ("Pre-Order: " + tree.preOrder ());
		    c.println ("Post-Order: " + tree.postOrder ());
		    c.println ("In-Order: " + tree.inOrder ());
		    c.println ("Breadth First Order: " + tree.breadthFirstOrder ());
		    c.println ("Height of tree: " + tree.height ());
		    c.println ("Number of nodes: " + tree.nodes ());
		    c.print ("Perfectly balanced? ");
		    if (tree.perfectlyBalanced ())
			c.println ("Yes");
		    else
			c.println ("No");
		    c.print ("Height balanced? ");
		    if (tree.heightBalanced ())
			c.println ("Yes");
		    else
			c.println ("No");
		    c.println ();
		    c.println ("Press any key to continue...");
		    c.getChar ();
		}
		else if (command == '3')
		{
		    c.print ("Please enter the number you are looking for: ");
		    int search = c.readInt ();
		    int level = tree.getLevel (search);
		    if (level == -1)
			c.println ("Node not found");
		    else
			c.println ("Level of number " + search + ": " + level);

		    c.println ();
		    c.println ("Press any key to continue...");
		    c.getChar ();
		}
		else if (command == '4')
		{
		    c.print ("Please enter the number you are looking for the sibling of: ");
		    int search = c.readInt ();

		    if (tree.getLevel (search) == -1)
		    {
			c.println ("Node not found");
		    }
		    else
		    {
			Node sib = tree.findSibling (search);
			c.print ("Sibling of " + search + ": ");
			if (sib == null)
			    c.println ("None");
			else
			    c.println (sib.data);

		    }
		    c.println ();
		    c.println ("Press any key to continue...");
		    c.getChar ();

		}
		else
		    c.clear ();
	    }

	}
	c.close ();
    }
}


//***************************************************************
// Author: Unknown
//      Edited by: Jackie Xu
// Date: Unknown
//      Modified on: 1/15/2016
// Purpose/Description: Node object that can be used in a singly linked list
// Fields:
//      data (int) - the information held by the Node
//      right (Node) - points to its right child
//      left (Node) - points to its left child
//      next (Node) - points to next node (Used in queue only)
// Methods:
/*      recursivePreOrder - returns tree as string in pre-order form
	recursivePostOrder - returns tree as string in post-order form
	recursiveInOrder - returns tree as string in in-order form
	recursiveHeight - returns the height of tree
	recursiveNodes - returns the number of nodes
	recursivePerfectlyBalanced - checks if tree is perfectly balanced
	recursiveHeightBalanced - checks if tree is height balanced
*/
//***************************************************************

class Node
{
    public int data;
    public Node left;
    public Node right;
    public Node next;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: Explicit constructor for the node
    // Parameters: info (int)
    // Return/Output: -
    //***************************************************************

    public Node (int info)
    {
	this.data = info;
	this.left = null;
	this.right = null;
	this.next = null;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: Constructor for value of 0
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public Node ()
    {
	this (0);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: returns a string of the tree recursively in pre-order form
    // Parameters: -
    // Return/Output: out (String)
    //***************************************************************

    protected String recursivePreOrder ()
    {
	String out = "";
	if (this.left == null && this.right == null)
	    out = this.data + "";
	else if (this.left != null && this.right == null)
	    out = this.data + " " + this.left.recursivePreOrder ();
	else if (this.left == null && this.right != null)
	    out = this.data + " " + this.right.recursivePreOrder ();
	else
	    out = this.data + " " + this.left.recursivePreOrder () + " " + this.right.recursivePreOrder ();
	return out;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: returns a string of the tree recursively in post-order form
    // Parameters: -
    // Return/Output: out (String)
    //***************************************************************

    protected String recursivePostOrder ()
    {
	String out = "";
	if (this.left == null && this.right == null)
	    out = this.data + "";
	else if (this.left != null && this.right == null)
	    out = this.left.recursivePostOrder () + " " + this.data;
	else if (this.left == null && this.right != null)
	    out = this.right.recursivePostOrder () + " " + this.data;
	else
	    out = this.left.recursivePostOrder () + " " + this.right.recursivePostOrder () + " " + this.data;
	return out;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: returns a string of the tree recursively in in-order form
    // Parameters: -
    // Return/Output: out (String)
    //***************************************************************

    protected String recursiveInOrder ()
    {
	String out = "";
	if (this.left == null && this.right == null)
	    out = this.data + "";
	else if (this.left != null && this.right == null)
	    out = this.left.recursiveInOrder () + " " + this.data;
	else if (this.left == null && this.right != null)
	    out = this.data + " " + this.right.recursiveInOrder ();
	else
	    out = this.left.recursiveInOrder () + " " + this.data + " " + this.right.recursiveInOrder ();
	return out;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: returns the height of the tree recursively
    // Parameters: -
    // Return/Output: output (int)
    //***************************************************************

    protected int recursiveHeight ()
    {
	int output = 1;

	if (this.left == null && this.right != null)
	{
	    output = output + this.right.recursiveHeight ();
	}
	else if (this.left != null && this.right == null)
	{
	    output = output + this.left.recursiveHeight ();
	}
	else if (!(this.left == null && this.right == null))
	{
	    if (this.left.recursiveHeight () > this.right.recursiveHeight ())
	    {
		output = output + this.left.recursiveHeight ();
	    }
	    else
	    {
		output = output + this.right.recursiveHeight ();
	    }
	}
	return output;

    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: returns the number of nodes in the tree
    // Parameters: -
    // Return/Output: num (int)
    //***************************************************************

    protected int recursiveNodes ()
    {
	int num = 1;
	if (this.left != null)
	    num = num + this.left.recursiveNodes ();

	if (this.right != null)
	    num = num + this.right.recursiveNodes ();

	return num;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: checks if tree is perfectly balanced
    // Parameters: -
    // Return/Output: true/false
    //***************************************************************

    protected boolean recursivePerfectlyBalanced ()
    {
	boolean output = true;

	if (this.left != null && this.right == null)
	{
	    if (this.left.recursiveNodes () > 1)
		output = false;
	}
	else if (this.left == null && this.right != null)
	{
	    if (this.right.recursiveNodes () > 1)
		output = false;
	}
	else if (!(this.left == null && this.right == null))
	{
	    if (!this.left.recursivePerfectlyBalanced () || !this.right.recursivePerfectlyBalanced ())
		output = false;
	    else if (this.left.recursiveNodes () - this.right.recursiveNodes () > 1)
		output = false;
	    else if (this.right.recursiveNodes () - this.left.recursiveNodes () > 1)
		output = false;
	}
	return output;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: checks if tree is height balanced
    // Parameters: -
    // Return/Output: true/false
    //***************************************************************

    protected boolean recursiveHeightBalanced ()
    {

	boolean output = true;

	if (this.left != null && this.right == null)
	{
	    if (this.left.recursiveHeight () > 1)
		output = false;

	}
	else if (this.left == null && this.right != null)
	{
	    if (this.right.recursiveHeight () > 1)
		output = false;

	}
	else if (!(this.left == null && this.right == null))
	{
	    if (!this.left.recursiveHeightBalanced () || !this.right.recursiveHeightBalanced ())
		output = false;

	    else if (this.left.recursiveHeight () - this.right.recursiveHeight () > 1)
		output = false;

	    else if (this.right.recursiveHeight () - this.left.recursiveHeight () > 1)
		output = false;

	}
	return output;

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
//      remove - removes a node from the queue
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
    // Purpose: adds given node to the end of queue
    // Parameters: given (Node)
    // Return/Output: -
    //***************************************************************

    public void add (Node given)
    {
	Node added = given;

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
    // Return/Output: out (Node)
    //***************************************************************

    public Node remove ()
    {
	Node out = null;
	if (!this.isEmpty ())
	{
	    out = this.front;

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
}

//***************************************************************
// Author: Unknown
//      Edited by: Jackie Xu
// Date: Unknown
//      Modified on: 1/15/2016
// Purpose/Description: Binary tree class
// Fields:
//      root (Node) - root of the tree
// Methods:
/*      add - adds a leaf to the tree with given info
	find - returns the node with the given information
	printTree - calls rprintTree with console
	rprintTree - prints tree(recursive)
	preOrder - returns the tree as in string in pre-order form (call's Node's recursive method)
	postOrder - returns the tree as in string in post-order form (call's Node's recursive method)
	InOrder - returns the tree as in string in in-order form (call's Node's recursive method)
	height - returns height of the tree (call's Node's recursive method)
	nodes - returns the number of nodes in the tree (call's Node's recursive method)
	perfectlyBalanced - checks if the tree is perfectly balanced (call's Node's recursive method)
	heightBalanced - checks if the tree is height balanced (call's Node's recursive method)
	found - checks if a node with a given number exists in the tree
	find sibling - finds a node's sibling, if it exists and/or possible
	getLevel - find the level of a node with a given number
	breadthFirstOrder - returns the tree in breadth first order
*/
//***************************************************************

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


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/15/2016
    // Purpose: returns the node's values as a string in pre-order form
    // Parameters: -
    // Return/Output: out (String)
    //***************************************************************

    public String preOrder ()
    {
	String out;
	if (this.root != null)
	    out = this.root.recursivePreOrder ();
	else
	    out = "Empty";
	return out;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: returns the node's values as a string in post-order form
    // Parameters: -
    // Return/Output: out (String)
    //***************************************************************

    public String postOrder ()
    {
	String out;
	if (this.root != null)
	    out = this.root.recursivePostOrder ();
	else
	    out = "Empty";
	return out;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: returns the node's values as a string in in-order form
    // Parameters: -
    // Return/Output: out (String)
    //***************************************************************

    public String inOrder ()
    {
	String out;
	if (this.root != null)
	    out = this.root.recursiveInOrder ();
	else
	    out = "Empty";
	return out;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: returns the height of the tree
    // Parameters: -
    // Return/Output: height (int)
    //***************************************************************

    public int height ()
    {
	int height = 0;
	if (this.root != null)
	    height = this.root.recursiveHeight ();
	return height;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: returns the number of nodes in the tree
    // Parameters: -
    // Return/Output: num (int)
    //***************************************************************

    public int nodes ()
    {
	int num = 0;
	if (this.root != null)
	    num = this.root.recursiveNodes ();
	return num;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: checks if the tree is perfectly balanced
    // Parameters: -
    // Return/Output: true/false
    //***************************************************************

    public boolean perfectlyBalanced ()
    {
	boolean perf = true;
	if (this.root != null)
	    perf = this.root.recursivePerfectlyBalanced ();
	return perf;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: checks if the tree is height balanced
    // Parameters: -
    // Return/Output: true/false
    //***************************************************************

    public boolean heightBalanced ()
    {
	boolean heightB = true;
	if (this.root != null)
	    heightB = this.root.recursiveHeightBalanced ();
	return heightB;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: checks if node with a given number exists in the tree
    // Parameters: input (int)
    // Return/Output: true/false
    //***************************************************************

    public boolean found (int input)
    {
	boolean output = false;
	boolean loop = true;
	Node current = this.root;

	while (loop)
	{
	    if (current == null)
	    {
		loop = false;
		output = false;
	    }
	    else if (current.data == input)
	    {
		loop = false;
		output = true;
	    }
	    else
	    {
		if (input > current.data)
		{
		    current = current.right;
		}
		else
		{
		    current = current.left;
		}
	    }
	}
	return output;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: returns the sibling of a given node
    // Parameters: input (int)
    // Return/Output: Node
    //***************************************************************

    public Node findSibling (int input)
    {

	Node output = null;
	Node previous = this.root;
	boolean loop = true;

	if (this.root != null && input == this.root.data)
	    output = null;
	else if (this.found (input))
	{
	    while (loop)
	    {
		if (input > previous.data)
		{
		    if (input == previous.right.data)
		    {
			loop = false;
			output = previous.left;
		    }
		    else
			previous = previous.right;
		}
		else
		{
		    if (input == previous.left.data)
		    {
			loop = false;
			output = previous.right;
		    }
		    else
			previous = previous.left;
		}
	    }

	}
	return output;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: returns the level of a given integer (-1 if not found)
    // Parameters: input (int)
    // Return/Output: lvl (int)
    //***************************************************************

    public int getLevel (int input)
    {
	int lvl = -1;

	if (this.root != null && this.found (input))
	{
	    boolean loop = true;
	    Node current = this.root;

	    while (loop)
	    {
		lvl++;
		if (current.data == input)
		    loop = false;
		else
		{
		    if (input > current.data)
			current = current.right;
		    else
			current = current.left;
		}
	    }
	}
	return lvl;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/18/2016
    // Purpose: returns the string of the tree in breadth first order
    // Parameters: -
    // Return/Output: out (String)
    //***************************************************************

    public String breadthFirstOrder ()
    {
	String out = "Empty";
	if (this.root != null)
	{
	    out = "";
	    LLQueue q = new LLQueue ();
	    q.add (this.root);

	    while (!q.isEmpty ())
	    {
		Node x = q.remove ();
		out = out + x.data + " ";

		if (x.left != null)
		    q.add (x.left);
		if (x.right != null)
		    q.add (x.right);
	    }

	}
	return out;
    }
}
