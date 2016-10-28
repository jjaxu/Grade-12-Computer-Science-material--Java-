// The "Geometry" class.
import java.awt.*;
import hsa.Console;

//***************************************************************
// Author: Jackie Xu
// Date: 11/12/2015
// Purpose: To learn how to program with inheritance
//***************************************************************

public class Geometry
{
    static Console c;

    public static void main (String[] args)
    {
	c = new Console (30,120,"The Family Tree of Shapes");
	char category = '2';
	c.println("Welcome to the Family Tree of Shapes!");
	c.println("=====================================");
	
	Shape [] dataBase = new Shape [1];
	
	c.println();
	int count = 0;
	while (count < dataBase.length)
	{
	    c.println("Please choose the category: '2' for 2D shape, or '3' for 3D shape: ");
	    category = c.getChar();
	
	    if (category == '2')
	    {
		c.println();
		c.println("You have chosen a 2D shape, now choose one of the follwing \n'c' for circle, " + 
			    "'s' for square, 't' for triangle, 'p' for regular pentagon or 'h' for regular hexagon: ");
		category = Character.toLowerCase(c.getChar());
		c.println();
		if (category == 'c')
		{
		    dataBase [count] = new Circle("Black",1,0);
		    dataBase[count].get(c);
		}
		else if (category == 's')
		{
		    dataBase [count] = new Square("Black",1,0);
		    dataBase[count].get(c);
		}
		else if (category == 't')
		{
		    dataBase [count] = new Triangle("Black",1,3,4,5);
		    dataBase[count].get(c);
		}
		else if (category == 'p')
		{
		    dataBase [count] = new RegPentagon("Black",1,0);
		    dataBase[count].get(c);
		}
		else
		{
		    dataBase [count] = new RegHexagon("Black",1,0);
		    dataBase[count].get(c);
		}
	    }
	    else
	    {
		c.println();
		c.println("You have chosen a 3D shape, now choose one of the follwing \n's' for sphere, " + 
			    "'c' for cube, 'l' for cylinder, 'p' for cone or 't' for tetrahedron: ");
		category = Character.toLowerCase(c.getChar());
		c.println();
		if (category == 's')
		{
		    dataBase [count] = new Sphere("Black",1,0);
		    dataBase[count].get(c);
		}
		else if (category == 'c')
		{
		    dataBase [count] = new Cube("Black",1,0);
		    dataBase[count].get(c);
		}
		else if (category == 'l')
		{
		    dataBase [count] = new Cylinder("Black",1,0,0);
		    dataBase[count].get(c);
		}
		else if (category == 'p')
		{
		    dataBase [count] = new Cone("Black",1,0,0);
		    dataBase[count].get(c);
		}
		else
		{
		    dataBase [count] = new Tetrahedron("Black",1,0);
		    dataBase[count].get(c);
		}
	    }
	    count++;
	    c.println();
	    c.println("Would you like to add another shape? (Y/N)");
	    category = c.getChar();
	    
	    if (category == 'Y' || category == 'y')
		dataBase = increaseSize (dataBase);
	    c.println();                
	    
	}
	c.clear();
	c.println("All shapes added, press any key to go through them...");
	c.getChar();
	for (count = 0; count < dataBase.length; count++)
	{
	    c.clear();
	    c.println("Shape #" + (count + 1));
	    c.println();
	    dataBase[count].put(c);
	    c.println();
	    c.println("Press any key to go to the next shape...");
	    c.getChar();
	    
	}
	c.clear();
	c.println("All shape testing complete. Press any key to exit...");
	c.getChar();
	c.close();

    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/16/2015
    // Purpose: increases array size by 1
    // Parameters: original shape array
    // Return/Output: shape array, larger by 1 partition
    //***************************************************************
    
    public static Shape [] increaseSize(Shape [] input)
    {       
	Shape [] output = new Shape [input.length + 1];
	for (int count = 0; count < input.length; count++)
	{
	    output[count] = input[count];
	}
	return output;
    }

}

//***************************************************************
// Author: Jackie Xu
// Date: 11/12/2015
// Purpose: To create an abstract class that has the potential to become a specific shape
// Fields:
//      colour (string) - this is the colour of the shape
// Methods:
/*      get - abstract at this level
	put - abstract at this level

*/
//***************************************************************

abstract class Shape
{
    protected String colour;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/12/2015
    // Purpose: Constructor that sets the colour of the shape
    // Parameters: colour (String)
    // Return/Output: -
    //***************************************************************

    public Shape (String colour)
    {
	this.colour = colour;
    }

    abstract void get (Console c);
    abstract void put (Console c);
}

//***************************************************************
// Author: Jackie Xu
// Date: 11/12/2015
// Purpose: An abstract that that is inherited from Shape and has the
//      potential to become a 2D shape
// Fields:
//      size (double) - size or magnification of the 2D shape
//      all fields from Shape
// Methods:
/*      
	get - abstract at this level
	put - abstract at this level
	perimeter - abstract at this level
	area - abstract at this level
*/
//***************************************************************

abstract class D2Shape extends Shape
{
    protected double size;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/12/2015
    // Purpose: Constructor that creates a 2D shape given a size and colour
    // Parameters: colour (String), size (double)
    // Return/Output: -
    //***************************************************************

    public D2Shape (String colour, double size)
    {
	super (colour);
	this.size = size;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/17/2015
    // Purpose: Gets the colour and size of the shape
    // Parameters: Input console
    // Return/Output: -
    //***************************************************************
    
    public void get (Console c)
    {
	c.print("Please enter the colour: ");
	this.colour = c.readLine();
	
	c.print("Please enter the size: ");
	this.size = Math.abs(c.readDouble());
    }
    
    abstract void put (Console c);
    abstract double perimeter ();
    abstract double area ();

}

//***************************************************************
// Author: Jackie Xu
// Date: 11/12/2015
// Purpose/Description: A concrete class that can become a circle
// Fields:
//      radius (double) - radius of the circle
//      all fields from D2Shape
// Methods:
/*      get - gets the data of the circle from user
	put - prints out the characteristics of the shape
	perimeter - returns the perimeter of the circle
	area - returns the area of the circle

*/
//***************************************************************

class Circle extends D2Shape
{
    protected double radius;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/12/2015
    // Purpose: Constructor that creates a circle object
    // Parameters: colour (string), size (double), radius (double)
    // Return/Output: -
    //***************************************************************

    public Circle (String colour, double size, double radius)
    {
	super (colour, size);
	this.radius = radius;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: gets the information of the circle needed from the user
    // Parameters: Input Console
    // Return/Output: -
    //***************************************************************

    public void get (Console c)
    {
	super.get(c);
	c.print ("Please enter the radius of the circle: ");
	this.radius = Math.abs (c.readDouble ());
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: prints out the characteristics of the Circle
    // Parameters: Console c
    // Return/Output: -
    //***************************************************************

    public void put (Console c)
    {
	c.println("Shape: Circle");
	c.println("Colour: " + this.colour);
	c.println("Size: " + this.size);
	c.println("Radius: " + this.radius);
	c.println("Perimeter: " + this.perimeter());
	c.println("Area: " + this.area());
	
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Calculates the perimeter of the circle
    // Parameters: -
    // Return/Output: perimeter (double)
    //***************************************************************

    public double perimeter ()
    {
	return 2 * Math.PI * this.radius;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Calculates the area of the circle
    // Parameters: -
    // Return/Output: area (double)
    //***************************************************************

    public double area ()
    {
	return Math.PI * this.radius * this.radius;
    }
}


//***************************************************************
// Author: Jackie Xu
// Date: 11/14/2015
// Purpose/Description: A concrete class that can become a square
// Fields:
//      width (double) - width of the square
//      all fields from D2Shape
// Methods:
/*      get - gets the data of the square from user
	put - prints out the characteristics of the shape
	perimeter - returns the perimeter of the square
	area - returns the area of the square
*/
//***************************************************************

class Square extends D2Shape
{
    protected double width;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Constructor that creates a square object
    // Parameters: colour (string), size (double), width (double)
    // Return/Output: -
    //***************************************************************

    public Square (String colour, double size, double width)
    {
	super (colour, size);
	this.width = width;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: gets the information of the square needed from the user
    // Parameters: Input Console
    // Return/Output: -
    //***************************************************************

    public void get (Console c)
    {
	super.get(c);
	c.print ("Please enter the size length of the square: ");
	this.width = Math.abs (c.readDouble ());
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/16/2015
    // Purpose: prints out the characteristics of the square
    // Parameters: Console c
    // Return/Output: -
    //***************************************************************

    public void put (Console c)
    {
	c.println("Shape: Square");
	c.println("Colour: " + this.colour);
	c.println("Size: " + this.size);
	c.println("Width: " + this.width);
	c.println("Perimeter: " + this.perimeter());
	c.println("Area: " + this.area());
	
    }



    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Calculates the perimeter of the square
    // Parameters: -
    // Return/Output: perimeter (double)
    //***************************************************************

    public double perimeter ()
    {
	return 4 * this.width;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Calculates the area of the square
    // Parameters: -
    // Return/Output: area (double)
    //***************************************************************

    public double area ()
    {
	return this.width * this.width;
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 11/14/2015
// Purpose/Description: A concrete class that can become a triangle
// Fields:
//      side 1, side 2, side 3(doubles) - side lengths of the triangle
//      all fields from D2Shape
// Methods:
/*      get - gets the data of the triangle from user
	put - prints out the characteristics of the shape
	perimeter - returns the perimeter of the triangle
	area - returns the area of the triangle

*/
//***************************************************************

class Triangle extends D2Shape
{
    protected double s1, s2, s3;
    
    public Triangle (String colour, double size, double s1, double s2, double s3)
    {
	super (colour, size);
	
	if (s1 + s2 > s3 && s1 + s3 > s2 && s2 + s3 > s1)
	{
	    this.s1 = s1;
	    this.s2 = s2;
	    this.s3 = s3;
	}
	else
	{
	    this.s1 = 0;
	    this.s2 = 0; 
	    this.s3 = 0;
	    this.colour = "Invalid Triangle";
	}

    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: gets the information of the triangle needed from the user
    // Parameters: Input Console
    // Return/Output: -
    //***************************************************************

    public void get (Console c)
    {
	super.get(c);
	
	c.print ("Please enter the first side length of the triangle: ");
	this.s1 = Math.abs (c.readDouble ());

	c.print ("Please enter the second side length of the triangle: ");
	this.s2 = Math.abs (c.readDouble ());

	c.print ("Please enter the third side length of the triangle: ");
	this.s3 = Math.abs (c.readDouble ());
	
	if (!(s1 + s2 > s3 && s1 + s3 > s2 && s2 + s3 > s1))
	{
	    this.s1 = 0;
	    this.s2 = 0; 
	    this.s3 = 0;
	    this.colour = "Invalid Triangle";
	}
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/16/2015
    // Purpose: prints out the characteristics of the triangle
    // Parameters: Console c
    // Return/Output: -
    //***************************************************************

    public void put (Console c)
    {
	c.println("Shape: Triangle");
	c.println("Colour: " + this.colour);
	c.println("Size: " + this.size);
	c.println("Side length #1: " + this.s1);
	c.println("Side length #2: " + this.s2);
	c.println("Side length #3: " + this.s3);
	c.println("Perimeter: " + this.perimeter());
	c.println("Area: " + this.area());
	
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Calculates the perimeter of the triangle
    // Parameters: -
    // Return/Output: perimeter (double)
    //***************************************************************

    public double perimeter ()
    {
	return this.s1 + this.s2 + this.s3;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Calculates the area of the triangle
    // Parameters: -
    // Return/Output: area (double)
    //***************************************************************

    public double area ()
    {
	double area, semi;
	
	semi = (s1 + s2 + s3) / 2;
	area = Math.sqrt(semi * (semi - s1) * (semi - s2) * (semi - s3));
	return area;
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 11/14/2015
// Purpose/Description: A concrete class that can become a regular pentagon
// Fields:
//      width (double) - width of the pentagon
//      all fields from D2Shape
// Methods:
/*      get - gets the data of the pentagon from user
	put - prints out the characteristics of the shape
	perimeter - returns the perimeter of the pentagon
	area - returns the area of the pentagon
*/
//***************************************************************

class RegPentagon extends D2Shape
{
    protected double width;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Constructor that creates a regular pentagon object
    // Parameters: colour (string), size (double), width (double)
    // Return/Output: -
    //***************************************************************

    public RegPentagon (String colour, double size, double width)
    {
	super (colour, size);
	this.width = width;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: gets the information of the pentagon needed from the user
    // Parameters: Input Console
    // Return/Output: -
    //***************************************************************

    public void get (Console c)
    {
	super.get(c);
	c.print ("Please enter the size length of the regular pentagon: ");
	this.width = Math.abs (c.readDouble ());
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/16/2015
    // Purpose: prints out the characteristics of the pentagon
    // Parameters: Console c
    // Return/Output: -
    //***************************************************************

    public void put (Console c)
    {
	c.println("Shape: Regular Pentagon");
	c.println("Colour: " + this.colour);
	c.println("Size: " + this.size);
	c.println("Width: " + this.width);
	c.println("Perimeter: " + this.perimeter());
	c.println("Area: " + this.area());
	
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Calculates the perimeter of the pentagon
    // Parameters: -
    // Return/Output: perimeter (double)
    //***************************************************************

    public double perimeter ()
    {
	return 5 * this.width;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Calculates the area of the pentagon
    // Parameters: -
    // Return/Output: area (double)
    //***************************************************************

    public double area ()
    {
	double angleA = 72 * Math.PI / 180;
	double angleB = 54 * Math.PI / 180;
	double sideSlant = this.width / Math.sin(angleA) * Math.sin(angleB);
	double area, semi;

	semi = (2 * sideSlant + this.width) / 2;
	area = 5 * Math.sqrt(semi * (semi - sideSlant) * (semi - sideSlant) * (semi - this.width));
	
	return area;
    }
}

class RegHexagon extends D2Shape
{
    protected double width;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Constructor that creates a regular hexagon object
    // Parameters: colour (string), size (double), width (double)
    // Return/Output: -
    //***************************************************************

    public RegHexagon (String colour, double size, double width)
    {
	super (colour, size);
	this.width = width;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: gets the information of the hexagon needed from the user
    // Parameters: Input Console
    // Return/Output: -
    //***************************************************************

    public void get (Console c)
    {
	super.get(c);
	c.print ("Please enter the size length of the regular hexagon: ");
	this.width = Math.abs (c.readDouble ());
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/16/2015
    // Purpose: prints out the characteristics of the regular hexagon
    // Parameters: Console c
    // Return/Output: -
    //***************************************************************

    public void put (Console c)
    {
	c.println("Shape: Regular Hexagon");
	c.println("Colour: " + this.colour);
	c.println("Size: " + this.size);
	c.println("Width: " + this.width);
	c.println("Perimeter: " + this.perimeter());
	c.println("Area: " + this.area());
	
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Calculates the perimeter of the hexagon
    // Parameters: -
    // Return/Output: perimeter (double)
    //***************************************************************

    public double perimeter ()
    {
	return 6 * this.width;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/14/2015
    // Purpose: Calculates the area of the hexagon
    // Parameters: -
    // Return/Output: area (double)
    //***************************************************************

    public double area ()
    {
	return 3.0 * Math.sqrt(3.0) / 2.0 * this.width * this.width;
    }
}


//***************************************************************
// Author: Jackie Xu
// Date: 11/15/2015
// Purpose/Description: A class that has the potential to become a 3D shape
// Fields:
//      size (double) - size or magnification of the 3D shape
//      all fields from class Shape
// Methods:
/*      get - abstract at this level
	put - abstract at this level
	surfaceArea - abstract at this level
	volume - abstract at this level

*/
//***************************************************************

abstract class D3Shape extends Shape
{
    protected double size;
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Constructor that creates an abstract 3D shape
    // Parameters: colour (String), size (double)
    // Return/Output: -
    //***************************************************************
    
    public D3Shape (String colour, double size)
    {
	super (colour);
	this.size = size;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/17/2015
    // Purpose: Gets the colour and size of the shape
    // Parameters: Input console
    // Return/Output: -
    //***************************************************************
    
    public void get (Console c)
    {
	
	c.print("Please enter the colour: ");
	this.colour = c.readLine();
	
	c.print("Please enter the size: ");
	this.size = Math.abs(c.readDouble());
    }
    
    abstract void put (Console c);    
    abstract double surfaceArea ();
    abstract double volume ();
}
//***************************************************************
// Author: Mohit Patel
// Date: 28/10/2016
// Purpose/Description: A concrete class that can become a regular decagon
// Fields:
//      side (double) - side length of the decagon
//      all fields from D2Shape
// Methods:
/*      get - gets the data of the decagon from user
	put - prints out the characteristics of the shape
	perimeter - returns the perimeter of the decagon
	area - returns the area of the decagon
*/
//***************************************************************

class RegDecagon extends D2Shape
{
    protected double side;

    //***************************************************************
    // Author: Mohit Patel
    // Date: 28/10/2016
    // Purpose: Constructor that creates a regular decagon object
    // Parameters: colour (string), size (double), side (double)
    // Return/Output: -
    //***************************************************************

    public RegDecagon (String colour, double size, double side)
    {
	super (colour, size);
	this.side = side;
    }


    //***************************************************************
    // Author: Mohit Patel
    // Date: 28/10/2016
    // Purpose: gets the information of the decagon needed from the user
    // Parameters: Input Console
    // Return/Output: -
    //***************************************************************

    public void get (Console c)
    {
	super.get(c);
	c.print ("Please enter the size length of the regular decagon: ");
	this.side = Math.abs (c.readDouble ());
    }


    //***************************************************************
    // Author: Mohit Patel
    // Date: 28/10/2016
    // Purpose: prints out the characteristics of the decagon
    // Parameters: Console c
    // Return/Output: -
    //***************************************************************

    public void put (Console c)
    {
	c.println("Shape: Regular Decagon");
	c.println("Colour: " + this.colour);
	c.println("Size: " + this.size);
	c.println("Side: " + this.size);
	c.println("Perimeter: " + this.perimeter());
	c.println("Area: " + this.area());
	
    }

    //***************************************************************
    // Author: Mohit Patel
    // Date: 28/10/2016
    // Purpose: Calculates the perimeter of the decagon
    // Parameters: -
    // Return/Output: perimeter (double)
    //***************************************************************

    public double perimeter ()
    {
	return 10 * this.side;
    }


    //***************************************************************
    // Author: Mohit Patel
    // Date: 28/10/2016
    // Purpose: Calculates the area of the decagon
    // Parameters: -
    // Return/Output: area (double)
    //***************************************************************

    public double area ()
    {
        double area;
	area = (5/2)*this.width*this.width*Math.sqrt(5+2*Math.sqrt(5));
	return area;
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 11/15/2015
// Purpose/Description: A concrete class that can create sphere objects
// Fields:
//      radius (double) - radius of the sphere
//      all fields from D3Shape class
// Methods:
/*      get - gets data needed from user
	put - prints out the characteristics of the shape
	surfaceArea - returns the surface area of sphere
	volume - returns the volume of the sphere
*/
//***************************************************************

class Sphere extends D3Shape
{
    protected double radius;
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Constructor that creates a concrete sphere object
    // Parameters: colour (string), size (double), radius (double)
    // Return/Output: -
    //***************************************************************
    
    public Sphere (String colour, double size, double radius)
    {
	super (colour, size);
	this.radius = radius;
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: gets the information of the sphere needed from the user
    // Parameters: Input Console
    // Return/Output: -
    //***************************************************************

    public void get (Console c)
    {
	super.get(c);
	c.print ("Please enter the radius of the sphere: ");
	this.radius = Math.abs (c.readDouble ());
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/16/2015
    // Purpose: prints out the characteristics of the sphere
    // Parameters: Console c
    // Return/Output: -
    //***************************************************************

    public void put (Console c)
    {
	c.println("Shape: Sphere");
	c.println("Colour: " + this.colour);
	c.println("Size: " + this.size);
	c.println("Radius: " + this.radius);
	c.println("Surface Area: " + this.surfaceArea());
	c.println("Volume: " + this.volume());        
    }



    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Calculate the surface area of the sphere
    // Parameters: -
    // Return/Output: surfaceArea (double)
    //***************************************************************

    public double surfaceArea ()
    {
	return 4 * Math.PI * this.radius * this.radius;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Calculates the volume of the sphere
    // Parameters: -
    // Return/Output: volume (double)
    //***************************************************************

    public double volume ()
    {
	return 4.0 / 3.0 * Math.PI * this.radius * this.radius * this.radius;
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 11/15/2015
// Purpose/Description: A concrete class that can become a cube
// Fields:
//      width (double) - width of the cube
//      all fields from D3Shape
// Methods:
/*      get - gets the data of the cube from user
	put - prints out the characteristics of the shape
	surfaceArea - returns the surface area of the cube
	volume - returns the volume of the cube
*/
//***************************************************************


class Cube extends D3Shape
{
    protected double width;

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Constructor that creates a cube object
    // Parameters: colour (string), size (double), width (double)
    // Return/Output: -
    //***************************************************************

    public Cube (String colour, double size, double width)
    {
	super (colour, size);
	this.width = width;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: gets the information of the cube needed from the user
    // Parameters: Input Console
    // Return/Output: -
    //***************************************************************

    public void get (Console c)
    {
	super.get(c);
	c.print ("Please enter the size length of the cube: ");
	this.width = Math.abs (c.readDouble ());
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/16/2015
    // Purpose: prints out the characteristics of the cube
    // Parameters: Console c
    // Return/Output: -
    //***************************************************************

    public void put (Console c)
    {
	c.println("Shape: Cube");
	c.println("Colour: " + this.colour);
	c.println("Size: " + this.size);
	c.println("Width: " + this.width);
	c.println("Surface Area: " + this.surfaceArea());
	c.println("Volume: " + this.volume());        
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Calculates the surface area of the cube
    // Parameters: -
    // Return/Output: surface area (double)
    //***************************************************************

    public double surfaceArea ()
    {
	return 6 * this.width * this.width;
    }
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Calculate the volume of the cube
    // Parameters: -
    // Return/Output: volume (double)
    //***************************************************************

    public double volume ()
    {
	return this.width * this.width * this.width;
    }

}

//***************************************************************
// Author: Jackie Xu
// Date: 11/15/2015
// Purpose/Description: A concrete class capable of creating cylinder objects
// Fields:
//      radius (double) - radius of cylinder
//      height (double) - height of cylinder
//      all fields from D3Shape class
// Methods:
/*      get - gets the data of the cylinder from user
	put - prints out the characteristics of the shape
	surfaceArea - returns the surface area of the cylinder
	volume - returns the volume of the cylinder
*/
//***************************************************************

class Cylinder extends D3Shape
{
    protected double radius;
    protected double height;
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Constructor that creates a cylinder object
    // Parameters: colour (string), size (double), radius (double)
    // Return/Output: -
    //***************************************************************    
    
    public Cylinder (String colour, double size, double radius, double height)
    {
	super (colour, size);
	this.radius = radius;
	this.height = height;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: gets the information of the cylinder needed from the user
    // Parameters: Input Console
    // Return/Output: -
    //***************************************************************
    
    public void get (Console c)
    {
	super.get(c);
	
	c.print ("Please enter the radius of the cylinder: ");
	this.radius = Math.abs (c.readDouble ());
	
	c.print ("Please enter the height of the cylinder: ");
	this.height = Math.abs (c.readDouble ());
    }    

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/16/2015
    // Purpose: prints out the characteristics of the cylinder
    // Parameters: Console c
    // Return/Output: -
    //***************************************************************

    public void put (Console c)
    {
	c.println("Shape: Cylinder");
	c.println("Colour: " + this.colour);
	c.println("Size: " + this.size);
	c.println("Radius: " + this.radius);
	c.println("Height: " + this.height);        
	c.println("Surface Area: " + this.surfaceArea());
	c.println("Volume: " + this.volume());        
    }

    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Calculates the surface area of the cylinder
    // Parameters: -
    // Return/Output: surface area (double)
    //***************************************************************

    public double surfaceArea ()
    {
	return 2 * Math.PI * this.radius * this.radius + 2 * Math.PI * this.radius * this.height;
    }
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Calculate the volume of the cylinder
    // Parameters: -
    // Return/Output: volume (double)
    //***************************************************************

    public double volume ()
    {
	return Math.PI * this.radius * this.radius * this.height;
    }
    
}

//***************************************************************
// Author: Jackie Xu
// Date: 11/15/2015
// Purpose/Description: A concrete class capable of creating cone objects
// Fields:
//      radius (double) - radius of the cone
//      height (double) - height of the cone
//      all fields from D3Shape class
// Methods:
/*      get - gets the data of the cone from user
	put - prints out the characteristics of the shape
	surfaceArea - returns the surface area of the cone
	volume - returns the volume of the cone
*/
//***************************************************************

class Cone extends D3Shape
{
    protected double radius;
    protected double height;
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Constructor that creates a cone object
    // Parameters: colour (string), size (double), radius (double)
    // Return/Output: -
    //***************************************************************    
    
    public Cone (String colour, double size, double radius, double height)
    {
	super (colour, size);
	this.radius = radius;
	this.height = height;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: gets the information of the cone needed from the user
    // Parameters: Input Console
    // Return/Output: -
    //***************************************************************
    
    public void get (Console c)
    {
	super.get(c);
	c.print ("Please enter the radius of the cone: ");
	this.radius = Math.abs (c.readDouble ());
	
	c.print ("Please enter the height of the cone: ");
	this.height = Math.abs (c.readDouble ());
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/16/2015
    // Purpose: prints out the characteristics of the cone
    // Parameters: Console c
    // Return/Output: -
    //***************************************************************

    public void put (Console c)
    {
	c.println("Shape: Cone");
	c.println("Colour: " + this.colour);
	c.println("Size: " + this.size);
	c.println("Radius: " + this.radius);
	c.println("Height: " + this.height);        
	c.println("Surface Area: " + this.surfaceArea());
	c.println("Volume: " + this.volume());        
    }

    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Calculates the surface area of the cone
    // Parameters: -
    // Return/Output: surface area (double)
    //***************************************************************

    public double surfaceArea ()
    {
	double slant = Math.sqrt(radius * radius + height * height);
	return Math.PI * radius * radius + Math.PI * radius * slant;
    }
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Calculate the volume of the cone
    // Parameters: -
    // Return/Output: volume (double)
    //***************************************************************

    public double volume ()
    {
	return Math.PI * this.radius * this.radius * this.height / 3;
    }
    
}

//***************************************************************
// Author: Jackie Xu
// Date: 11/16/2015
// Purpose/Description: A concrete class that can create tetrahedron objects
// Fields:
//      length (double) - length of edge
//      all fields from D3Shape class
// Methods:
/*      get - gets data needed from user
	put - prints out the characteristics of the shape
	surfaceArea - returns the surface area of tetrahedron
	volume - returns the volume of the tetrahedron
*/
//***************************************************************

class Tetrahedron extends D3Shape
{
    protected double length;
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Constructor that creates a concrete tetrahedron object
    // Parameters: colour (string), size (double), radius (double)
    // Return/Output: -
    //***************************************************************
    
    public Tetrahedron (String colour, double size, double length)
    {
	super (colour, size);
	this.length = length;
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: gets the information of the tetrahedron needed from the user
    // Parameters: Input Console
    // Return/Output: -
    //***************************************************************

    public void get (Console c)
    {
	super.get(c);
	
	c.print ("Please enter the edge length of the tetrahedron: ");
	this.length = Math.abs (c.readDouble ());
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/16/2015
    // Purpose: prints out the characteristics of the tetrahedron
    // Parameters: Console c
    // Return/Output: -
    //***************************************************************

    public void put (Console c)
    {
	c.println("Shape: Tetrahedron");
	c.println("Colour: " + this.colour);
	c.println("Size: " + this.size);
	c.println("Edge Length: " + this.length);
	c.println("Surface Area: " + this.surfaceArea());
	c.println("Volume: " + this.volume());        
    }



    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Calculate the surface area of the tetrahedron
    // Parameters: -
    // Return/Output: surfaceArea (double)
    //***************************************************************

    public double surfaceArea ()
    {
	return Math.sqrt(3.0) * this.length * this.length;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/15/2015
    // Purpose: Calculates the volume of the tetrahedron
    // Parameters: -
    // Return/Output: volume (double)
    //***************************************************************

    public double volume ()
    {
	return this.length * this.length * this.length / 6.0 / Math.sqrt(2.0);
    }
}
