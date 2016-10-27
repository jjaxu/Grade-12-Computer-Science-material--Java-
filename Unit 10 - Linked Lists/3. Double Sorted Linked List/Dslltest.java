// The "Dslltest" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 1/2/2016
// Purpose: To explore the properties, functions and uses of double, sorted linked lists
//***************************************************************

public class Dslltest
{
    static Console c;
    
    public static void main (String[] args)
    {
	c = new Console ();        
	c.println("Welcome to Double Sorted Linked lists!");
	c.println();
	Dsll mainList = new Dsll ();
	
	char command = 'i';
	while (command == 'i' || command == 'f' ||command == 'b' ||command == 'p' ||
		command == 'd')
	{
	    c.println();
	    c.println("Please choose a command: 'i' to insert, 'd' to delete, 'f' to find, 'b' to find before, " + 
		"'p' to print the list, or press any other key to exit...");
	    c.println();
	    command = Character.toLowerCase (c.getChar ());         
	    
	    if (command == 'i')
	    {                
		c.print("Insert selected! Please enter the name (string) to be added: ");
		String name = c.readLine();
		c.print("Please enter the number (integer) to be added: ");
		int num = c.readInt();
		
		mainList.insert(name, num);
		c.println();
		c.println("Node inserted!");
	    }
	    
	    else if (command == 'p')
	    {
		c.clear();
		c.println("Print selected! By Name or by Number? (1 for Name, 2 for Number)");
		char command2 = Character.toLowerCase (c.getChar ());
		
		while (!(command2 == '1' || command2 == '2'))
		    command2 = Character.toLowerCase (c.getChar ());
		
		if (command2 == '1')
		{
		    c.println("Here's the list by Name: ");
		    c.println(mainList.byName());
		}
		else
		{
		    c.println("Here's the list by Number: ");
		    c.println(mainList.byNumber()); 
		}
	    }
	    
	    else if (command == 'f')
	    {
		c.print("Find selected! By Name or by Number? (1 for Name, 2 for number)");
		char command2 = Character.toLowerCase (c.getChar ());
		
		while (!(command2 == '1' || command2 == '2'))
		    command2 = Character.toLowerCase (c.getChar ());
		
		if (command2 == '1')
		{
		    c.println();
		    c.print("By Name selected! Please enter the name (string): ");
		    String target = c.readLine();
		    
		    Node search = mainList.find(target);
			
		    if (search == null)
		    {
			if (mainList.isEmpty())
			    c.println("List is empty! There's nothing to find!");
			else
			    c.println("The node with the name '" + target + "' does not exist");
		    }

		    else
		    {
			c.println("Node with '" + target + "' is found and contains the number " + search.number);

		    }

		}                
		
		else
		{
		    c.println();
		     c.print("By Number selected! Please enter the number (integer): ");
		    int target = c.readInt();
		    
		    Node search = mainList.find(target);
			
		    if (search == null)
		    {
			if (mainList.isEmpty())
			    c.println("List is empty! There's nothing to find!");
			else
			    c.println("The node with the number " + target + " does not exist");
		    }
		    
		    else
		    {
			c.println("Node with number " + target + " is found and contains the name '" + search.name + "'");

		    }

		}
		
	    }
	    
	    else if (command == 'd')
	    {
		c.println("Delete selected! By Name or by Number? (1 for Name, 2 for Number)");
		char command2 = Character.toLowerCase (c.getChar ());
		
		while (!(command2 == '1' || command2 == '2'))
		    command2 = Character.toLowerCase (c.getChar ());
		
		if (command2 == '1')
		{
		    c.print("By Name selected! Please enter the name: ");
		    String target = c.readLine();
		    Node find = mainList.find(target);
		    
		    if (find == null)
			c.println("Cannot delete, node not found");
		    else
		    {
			mainList.delete(target, true);
			c.println("Node with name '" + target + "', along with its number " + find.number + " has been deleted");
		    }
		    
		}
		else
		{
		    c.print("By Number selected! Please enter the number: ");
		    int target = c.readInt();
		    Node find = mainList.find(target);
		    
		    if (find == null)
			c.println("Cannot delete, node not found");
		    else
		    {
			mainList.delete(target, true);
			c.println("Node with number " + target + ", along with its name '" + find.name + "' has been deleted");
		    }
		}
		
	    }
	    
	    else if (command == 'b')
	    {
		c.print("Find before selected! By Name or by Number? (1 for Name, 2 for number)");
		char command2 = Character.toLowerCase (c.getChar ());
		
		while (!(command2 == '1' || command2 == '2'))
		    command2 = Character.toLowerCase (c.getChar ());
		
		if (command2 == '1')
		{
		    c.println();
		    c.print("By Name selected! Please enter the name (string): ");
		    String target = c.readLine();
		    
		    Node search = mainList.findBefore(target);
			
		    if (search == null)
		    {
			    c.println("The node with name '" + target + "' should go to the beginning of the list");
		    }
		    else if (search.nextName == null)
			c.println("Node with the name '" + target + "' should go to the end of the list");
		    else
			c.println("Node with the name '" + target + "' should come after the node with the name '" + search.name + "'");

		}                
		
		else
		{
		    c.println();
		    c.print("By Number selected! Please enter the number (integer): ");
		    int target = c.readInt();
		    
		    Node search = mainList.findBefore(target);
		    
		    if (search == null)
		    {
			   c.println("The node with number " + target + " should go to the beginning of the list");

		    }
		    else if (search.nextNumber == null)
			c.println("Node with number " + target + " should go to the end of the list");   
		    else
		    {
			c.println("Node with number " + target + " should come after the node with number " + search.number);
		    }

		}

	    } 
	}
	c.close();

    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 1/2/2016
// Purpose/Description: Node object that can be used in a double linked list
// Fields:
//      name - String
//      number - integer
//      nextName - pointer to the next name node
//      nextNumber - pointer to the next number node
// Methods:
/*      byName - returns the sorted list by name as a string
	byNumber - returns the sorted list by number as a string

*/
//***************************************************************

class Node
{
    public String name;
    public int number;
    public Node nextName;
    public Node nextNumber;
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/2/2016
    // Purpose: Constructor for the node
    // Parameters: name (string), number (int)
    // Return/Output: -    
    //***************************************************************    
    
    public Node (String name, int number)
    {
	this.name = name;
	this.number = number;
	this.nextName = null;
	this.nextNumber = null;
    } 
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/2/2016
    // Purpose: creates a string by name
    // Parameters: -
    // Return/Output: output (String)
    //***************************************************************      
    
    public String byName ()
    {
	String output = "";
	output = "(" + this.name + "," + this.number + ")";
	if (this.nextName != null)
	{                    
	    output = output + this.nextName.byName();
	}
	
	return output;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/2/2016
    // Purpose: creates a string by number
    // Parameters: -
    // Return/Output: output (String)
    //***************************************************************      
    
    public String byNumber ()
    {
	String output = "";
	output = "(" + this.number + "," + this.name + ")";
	if (this.nextNumber != null)
	{                    
	    output = output + this.nextNumber.byNumber();
	}
	
	return output;
    }
}


//***************************************************************
// Author: Jackie Xu
// Date: 1/2/2016
// Purpose/Description: A double sorted linked list class that sorts nodes by name and value
// Fields:
//      listName - pointer to a Node by name
//      listNumber - pointer to a Node by number
// Methods:
/* 
	isEmpty - checks of the list is empty
	findBefore (name) - returns the Node before with a given string
	findBefore (number) - returns the Node before with a given number
	find (name) - returns the node given the name
	find (number) - returns the node given the number
	byName - returns the list values in order of name as a string
	byNumber - returns the list values in order of number as a string
	delete (string) - deletes the node given its string (calls other delete method)
	delete (number) - deletes node given its number (calls other delete method)
	insert - inserts a new node with given name and number (so it remains sorted)
*/
//***************************************************************

class Dsll
{
    public Node listName;
    public Node listNumber;
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/2/2016
    // Purpose: Constructor for a double sorted linked list (name and number)
    // Parameters: -
    // Return/Output: -
    //***************************************************************     
    
    public Dsll ()
    {
	this.listName = null;
	this.listNumber = null;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/2/2016
    // Purpose: checks if the list is empty
    // Parameters: -
    // Return/Output: true/false
    //***************************************************************  
    
    public boolean isEmpty()
    {
	boolean empty = false;
	if (this.listName == null || this.listNumber == null)
	    empty = true;
	return empty;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/2/2016
    // Purpose: finds the node before a given name
    // Parameters: givenName (string)
    // Return/Output: Node
    //***************************************************************  
    
    public Node findBefore (String givenName)
    {
	Node temp = this.listName;
	boolean stop = false;
	Node before = null;
	
	if (!this.isEmpty())
	{
	    int compare = temp.name.compareTo(givenName);
	    int compare2;
	    
	    if (compare >= 0) // first
	    {
		before = null;
	    }
	    else
	    {
		while (temp.nextName != null && stop == false)
		{                    
		    compare = givenName.compareTo(temp.name);
		    compare2 = givenName.compareTo(temp.nextName.name);
		    
		    if (compare > 0 && compare2 <= 0)
		    {                        
			before = temp;
			stop = true;
		    }
		    else
			temp = temp.nextName;                    
		}
		if (stop == false)
		    before = temp;
	    }  
	}
	return before; 
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/2/2016
    // Purpose: finds the node before a given number
    // Parameters: givenNum (integer)
    // Return/Output: Node
    //***************************************************************  
    
    public Node findBefore (int givenNum)
    {
	Node temp = this.listNumber;
	boolean stop = false;
	Node before = null;
	
	if (!this.isEmpty())
	{
	    if (givenNum <= this.listNumber.number) // first
	    {
		before = null;
	    }
	    else
	    {
		while (temp.nextNumber != null && stop == false)
		{
		    if (givenNum > temp.number && givenNum <= temp.nextNumber.number)
		    {
			
			before = temp;
			stop = true;
		    }
		    else
			temp = temp.nextNumber;
		}
		if (stop == false)
		    before = temp;
	    }  
	}
	return before;       
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/2/2016
    // Purpose: finds the node with a given name
    // Parameters: givenName (String)
    // Return/Output: Node
    //***************************************************************  
    
    public Node find (String givenName)
    {
	Node temp = this.listName;
	if (temp != null)
	{            
	    boolean stop = false;
	    do
	    {                    
		if (temp.name.equals(givenName))
		{
		    stop = true;
		}
		else
		    temp = temp.nextName;
	    }
	    while (temp != null && stop == false);
	}
	return temp;
	    
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/2/2016
    // Purpose: finds the node with a given number
    // Parameters: givenNum (integer)
    // Return/Output: Node
    //***************************************************************  
    
    public Node find (int givenNum)
    {
	Node temp = this.listNumber;
	if (temp != null)
	{            
	    boolean stop = false;
	    do
	    {                    
		if (temp.number == givenNum)
		{
		    stop = true;
		}
		else
		    temp = temp.nextNumber;
	    }
	    while (temp != null && stop == false);
	}
	return temp;    
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/2/2016
    // Purpose: return the nodes as a string in order by name
    // Parameters: -
    // Return/Output: output (string)
    //***************************************************************  
    
    public String byName ()
    {
	String output;
	if (this.isEmpty())
	    output = "Empty";
	else
	    output = this.listName.byName();
	return output;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/2/2016
    // Purpose: return the nodes as a string in the order by number
    // Parameters: -
    // Return/Output: output (string)
    //***************************************************************  
    
    public String byNumber ()
    {
	String output;
	if (this.isEmpty())
	    output = "Empty";
	else
	    output = this.listNumber.byNumber();
	return output;
    }
    

    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/3/2016
    // Purpose: deletes a node given its name
    // Parameters: givenName(String), started (boolean)
    // Return/Output: -
    //***************************************************************  

    public void delete (String givenName, boolean started)
    {
	if (!this.isEmpty())
	{
	    Node beforeName = this.findBefore(givenName);
	    Node actualName = this.find(givenName);

	    if (beforeName == null) // if first element deleted
	    {
		this.listName = this.listName.nextName;
		if (started) //calls other delete to delete number pointers
		    this.delete(actualName.number, false);
	    }
	    else if (beforeName.nextName != null) // if found
	    {
		if (beforeName.nextName.nextName == null) // if last item
		    beforeName.nextName = null;
		else  // if in middle
		    beforeName.nextName = beforeName.nextName.nextName;
		if (started) //calls other delete to delete number pointers
		    this.delete(actualName.number,false);
		actualName = null;
	    }

	}
	
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/3/2016
    // Purpose: deletes a node given its name
    // Parameters: givenNum(integer), started (boolean)
    // Return/Output: -
    //***************************************************************  

    public void delete (int givenNum, boolean started)
    {
	if (!this.isEmpty())
	{
	    Node beforeNum = this.findBefore(givenNum);
	    Node actualNum = this.find(givenNum);

	    if (beforeNum == null) // if first element deleted
	    {
		this.listNumber = this.listNumber.nextNumber;
		
		if (started) //calls other delete to delete name pointers
		    this.delete(actualNum.name, false);
	    }
	    else if (beforeNum.nextNumber != null) // if found
	    {
		if (beforeNum.nextNumber.nextNumber == null) // if last item
		    beforeNum.nextNumber = null;
		else  // if in middle
		    beforeNum.nextNumber = beforeNum.nextNumber.nextNumber;
		    
		if (started) //calls other delete to delete name pointers
		    this.delete (actualNum.name, false);
		actualNum = null;
	    }
	  
	}
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 1/2/2016
    // Purpose: inserts a node in the correct position in the list so
    //          that it remains sorted by name and number
    // Parameters: givenName(String), givenNum(integer)
    // Return/Output: -
    //***************************************************************
      
    public void insert (String givenName, int givenNum)
    {
	Node added = new Node (givenName, givenNum);
	
	if (this.isEmpty())  // if list is empty
	{
	    this.listName = added;
	    this.listNumber = added;
	}
	
	else
	{
	    
	    // by name
	    Node beforeName = this.findBefore(givenName);
	    if (beforeName == null) // added first node
	    {
		added.nextName = this.listName;
		this.listName = added;
	    }
	    else if (beforeName.nextName == null)
	    {
		beforeName.nextName = added;
	    }
	    else
	    {
		added.nextName = beforeName.nextName;
		beforeName.nextName = added;
	    }
    
	    // by number            
	    Node beforeNumber = this.findBefore(givenNum);
	    if (beforeNumber == null) // added first node
	    {
		added.nextNumber = this.listNumber;
		this.listNumber = added;
	    }
	    else if (beforeNumber.nextNumber == null)
	    {
		beforeNumber.nextNumber = added;
	    }
	    else
	    {
		added.nextNumber = beforeNumber.nextNumber;
		beforeNumber.nextNumber = added;
	    }
	    

	}
	
    }
}
