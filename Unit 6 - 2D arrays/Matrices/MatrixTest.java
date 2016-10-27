// The "MatrixTest" class.
import java.awt.*;
import hsa.*;
import java.util.StringTokenizer;
import java.text.DecimalFormat;

//***************************************************************
// Author: Jackie Xu
// Date: 11/1/2015
// Purpose: To learn how to use 2D arrays
//***************************************************************

public class MatrixTest
{
    static Console c;

    public static void main (String[] args) throws CloneNotSupportedException
    {
	char next;
	c = new Console (30, 160, "Matrices!");
	TextInputFile input;
	String fileName;

	Matrix testMatrix = new Matrix ();
	Matrix matrixTwo;

	c.println ("Matrix #1");
	testMatrix.get (c);

	testMatrix.print (c, 440, 60, "Matrix #1");        
	c.print("Square Matrix? ");
	if (testMatrix.square())
	{
	    c.println("Yes");
	    c.println ("Determinant: " + testMatrix.determinant());
	}
	else
	{
	    c.println("No");
	    c.println ("Determinant: None, matrix not square");
	}
	    
	testMatrix.columnAverage().print(c,900,60,"Column Average (Matrix #1)");

	c.println ();
	
	c.println ("Matrix #2");
	c.print ("The second matrix will be read from a file, \nplease enter the file name: ");
	fileName = c.readLine () + ".txt";
 
	input = new TextInputFile (fileName);
	matrixTwo = new Matrix ();
	matrixTwo.getFromFile (input);
	
	c.println();
	c.print("Square Matrix? ");
	if (matrixTwo.square())
	{
	    c.println("Yes");
	    c.println ("Determinant: " + matrixTwo.determinant());
	}
	else
	{
	    c.println("No");
	    c.println ("Determinant: None, matrix not square");
	}
	
	c.println();
	if (testMatrix.sameSize(matrixTwo))
	    c.println("These matrices are the same size");
	else
	    c.println("These matrices are not the same size");
	
	c.println();
	c.print("Press any key to go to the next set of operations...");
		
	matrixTwo.print (c, 440, 220, "Matrix #2");
	matrixTwo.columnAverage().print (c, 900, 220, "Column Average (Matrix #2)");
	
	next = c.getChar();
	
	c.clear();
	c.println("Transposition");
	testMatrix.print (c, 200, 100, "Matrix #1"); 
	testMatrix.transpose().print (c, 700, 100, "Matrix #1 Transposed");
	
	matrixTwo.print (c, 200, 280, "Matrix #2");
	matrixTwo.transpose().print (c, 700, 280, "Matrix #2 Transposed");
	
	c.println();
	c.print("Press any key to go to the next set of operations...");
	next = c.getChar();
	c.clear();
	
	c.println("Sum and difference");
	c.println();
	
	if (testMatrix.sameSize(matrixTwo))
	{
	    testMatrix.print (c, 200, 100, "Matrix #1"); 
	    matrixTwo.print (c, 200, 280, "Matrix #2");
	    testMatrix.add(matrixTwo).print (c, 700, 100, "Matrix #1 + Matrix #2");
	    testMatrix.subtract(matrixTwo).print (c, 700, 280, "Matrix #1 - Matrix #2");
	    
	}
	else
	    c.println("The matrices are different sizes, these procedures cannot be done");
	c.println();
	c.print("Press any key to go to the next set of operations...");
	next = c.getChar();
	c.clear();
	
	c.println("Substitution");
	c.println();
	
	testMatrix.print (c, 200, 100, "Matrix #1"); 
	matrixTwo.print (c, 200, 280, "Matrix #2");
	
	if (testMatrix.rows == matrixTwo.rows && matrixTwo.cols == 1)
	{
	    c.print("Please enter the column in Matrix #1 to substitute: ");
	    int columnNum = c.readInt();
	    testMatrix.substitude(matrixTwo, columnNum).print (c, 700, 100, "Matrix #2 subbed into Matrix #1"); 
	}
	else
	    c.print("Substitution cannot be done");
	    
	c.println();
	c.print("Press any key to go to the next set of operations...");
	next = c.getChar();
	c.clear();

	c.println("Concatenation");
	c.println();

	if (testMatrix.rows == matrixTwo.rows)
	{
	    testMatrix.concatenate(matrixTwo).print (c, 400, 100, "Matrix #1 and Matrix #2 concatenated"); 
	}
	else
	    c.print("Different row numbers, concatenation cannot be done");
	
	c.println();
	c.print("Press any key to go to the next set of operations...");
	next = c.getChar();
	c.clear();
	
	c.println("Splitting");
	c.println();
	testMatrix.print (c, 200, 160, "Matrix #1"); 
	
	c.print("Please enter the column to be split Matrix #1 from: ");
	int splitCol = c.readInt();
	testMatrix.split(splitCol).print (c, 800, 160, "Matrix #1 Split");
	
	matrixTwo.print (c, 200, 340, "Matrix #2");
	c.println();
	c.print("Please enter the column to be split Matrix #2 from: ");
	splitCol = c.readInt();
	matrixTwo.split(splitCol).print (c, 800, 340, "Matrix #2 Split");
	c.println();
	c.println("All testing complete, press any key to exit...");
	next = c.getChar();
	c.close();
    }
}

//***************************************************************
// Author: Jackie Xu
// Date: 11/1/2015
// Purpose: To create a class that allows manipulations with matrices using 2D arrays
// Fields:
//      element - 2D double array
//      rows - integer that represents the number of rows
//      cols - integer that represents the number of columns
// Methods:
/*      print (overloaded) - prints matrix that includes a title
	print - prints matrix given the x,y coords
	get - gets matrix from user input
	square - checks whether of the matrix is square
	sameSize - checks if another given matrix is the same size as the current one
	columnAverage - Calculates the average value for each column
	add - adds another matrix to current one if possible
	subtract - subtracts another matrix from current one if possible
	getFromFile - gets matrix from a text file
	minor - returns the minor of the matrix given a row and column, if possible
	determinant - calculates the determinant of the matrix if possible
	concatenate - concatenates the current matrix with another if possible
	substitute - substitute a column in the current matrix with another given column if possible
	split - splits the matrix at the given column if it can be done
	transpose - returns the transposed matrix
	clone - creates a deep clone of the current matrix        

*/
//***************************************************************


class Matrix implements Cloneable
{
    protected double[] [] element;
    protected int rows, cols;

    public static boolean checkLow (int given, int rangeLow)
    {
	boolean result = false;

	if (given >= rangeLow)
	{
	    result = true;
	}
	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/1/2015
    // Purpose: Most specific constructor for object
    // Parameters: rows, columns and the value
    // Return/Output: -
    //***************************************************************

    public Matrix (int row, int col, double value)
    {
	this.rows = Math.abs (row);
	this.cols = Math.abs (col);

	this.element = new double [this.rows] [this.cols];
	for (int count = 0 ; count < this.rows ; count++)
	{
	    for (int countTwo = 0 ; countTwo < this.cols ; countTwo++)
	    {
		this.element [count] [countTwo] = value;
	    }
	}

    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/1/2015
    // Purpose: Constructor that's less specific, it only takes 2 parameters
    // Parameters: rows, columns
    // Return/Output: -
    //***************************************************************

    public Matrix (int row, int col)
    {
	this (row, col, 0);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/1/2015
    // Purpose: simple constructor that creates a square matrix given the size
    // Parameters: size
    // Return/Output: -
    //***************************************************************

    public Matrix (int size)
    {
	this (size, size, 0);
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/1/2015
    // Purpose: simplest constructor that creates an empty matrix
    // Parameters: -
    // Return/Output: -
    //***************************************************************

    public Matrix ()
    {
	this (0, 0, 0);
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/12/2015
    // Purpose: prints the matrix with a title to reduce confusion (overloaded)
    // Parameters: output console, x, y coordinates of console, name
    // Return/Output: -
    //***************************************************************

    public void print (Console c, int x, int y, String name)
    {
	c.setFont (new Font ("Arial", Font.PLAIN, 14));
	c.drawString(name, x, y);
	this.print(c,x,y + 30);
    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/1/2015
    // Purpose: prints the matrix for a visual
    // Parameters: output console, x, y coordinates of console
    // Return/Output: -
    //***************************************************************

    public void print (Console c, int x, int y)
    {

	int startX = x;
	long spaceCount = 0;
	c.setFont (new Font ("Arial", Font.PLAIN, 14));
	DecimalFormat df = new DecimalFormat("###.##");

	for (int count = 0 ; count < this.rows ; count++)
	{
	    for (int countTwo = 0 ; countTwo < this.cols ; countTwo++)
	    {
		if (this.element[count][countTwo] < 0)
		    c.drawString ("-", x, y);
		
		    
		spaceCount = 4 - Long.toString(Math.round(Math.abs(this.element[count][countTwo]))).length();

		for (int i = 0; i < spaceCount; i++)
		{
		    c.drawString (" ", x, y);
		    x = x + 8;
		}

		if (Math.round(this.element[count][countTwo]) == this.element[count][countTwo])
		{
		    c.drawString (df.format(Math.abs(this.element[count][countTwo])) + ".00", x, y);
		}
		else if ((int)(1000 * Math.abs(this.element[count][countTwo])) % 10 >= 5 && 
			(int)(100 * Math.abs(this.element[count][countTwo])) % 10 == 9)
		{
		    c.drawString (df.format(Math.abs(this.element[count][countTwo])) + ".00", x, y);
		    
		}

			
		else if (Math.round(Math.abs(100 * this.element[count][countTwo])) % 10 == 0 && (int)Math.abs(this.element[count][countTwo]) != 0)
		{                    
		    c.drawString (df.format(Math.abs(this.element[count][countTwo])) + "0", x, y);
   
		}

		else
		{
		    c.drawString (df.format(Math.abs(this.element[count][countTwo])), x, y);
		    
		}
		
		for (int i = 4 - (int)spaceCount; i < 3; i++)
		{
		    x = x - 8;
		}
		x = x + 80;
	    }
	    x = startX;
	    y = y + 30;
	    
	    
	}
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/1/2015
    // Purpose: gets the matrix properties from user
    // Parameters: Console c
    // Return/Output: -
    //***************************************************************

    public void get (Console c)
    {
	int row = 0;
	int column = 0;

	c.print ("Please enter the number of rows: ");
	row = c.readInt ();

	while (Matrix.checkLow (row, 0) == false)
	{
	    c.println ();
	    c.println ("Invalid input! The row must be positive!");
	    c.print ("Please re-enter the number of rows: ");
	    row = c.readInt ();
	}

	c.print ("Please enter the number of columns: ");
	column = c.readInt ();

	while (Matrix.checkLow (column, 0) == false)
	{
	    c.println ();
	    c.println ("Invalid input! The column must be positive!");
	    c.print ("Please re-enter the number of columns: ");
	    column = c.readInt ();
	}
	c.println ();
	this.rows = row;
	this.cols = column;

	this.element = new double [row] [column];

	for (int count = 0 ; count < this.rows ; count++)
	{
	    for (int countTwo = 0 ; countTwo < this.cols ; countTwo++)
	    {
		c.print ("Please enter the value at row " + (count + 1) + ", column " + (countTwo + 1) + ": ");
		this.element [count] [countTwo] = c.readDouble ();

	    }
	    c.println ();
	}

    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/1/2015
    // Purpose: checks whether if the matrix is square
    // Parameters: -
    // Return/Output: true or false
    //***************************************************************

    public boolean square ()
    {
	boolean isSquare = false;
	if (this.rows == this.cols)
	    isSquare = true;
	return isSquare;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/1/2015
    // Purpose: checks whether if a given matrix is the same size as the current one
    // Parameters: Another matrix object
    // Return/Output: true or false
    //***************************************************************

    public boolean sameSize (Matrix given)
    {
	boolean isSameSize = false;
	if (this.rows == given.rows && this.cols == given.cols)
	    isSameSize = true;
	return isSameSize;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/1/2015
    // Purpose: calculates the column average in the given matrix
    // Parameters: -
    // Return/Output: 1D array
    //***************************************************************

    public Matrix columnAverage ()
    {
	Matrix result = new Matrix (1, this.cols);
	double sum = 0;
	int countRow, countCol = 0;

	for (countCol = 0 ; countCol < this.cols ; countCol++)
	{
	    for (countRow = 0 ; countRow < this.rows ; countRow++)
	    {
		sum = sum + this.element [countRow] [countCol];
	    }
	    result.element [0] [countCol] = sum / this.rows;
	    sum = 0;
	}
	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/2/2015
    // Purpose: adds another matrix to the current one, if possible
    // Parameters: another Matrix
    // Return/Output: Matrix Object
    //***************************************************************

    public Matrix add (Matrix given)
    {
	Matrix result;

	if (this.sameSize (given))
	{
	    result = new Matrix (this.rows, this.cols);
	    for (int count = 0 ; count < this.rows ; count++)
	    {
		for (int countTwo = 0 ; countTwo < this.cols ; countTwo++)
		{
		    result.element [count] [countTwo] = this.element [count] [countTwo] + given.element [count] [countTwo];
		}
	    }

	}
	else
	    result = new Matrix ();
	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: Subtract another matrix from the current one, if possible
    // Parameters: another Matrix
    // Return/Output: Matrix Object
    //***************************************************************

    public Matrix subtract (Matrix given)
    {
	Matrix result;

	if (this.sameSize (given))
	{
	    result = new Matrix (this.rows, this.cols);
	    for (int count = 0 ; count < this.rows ; count++)
	    {
		for (int countTwo = 0 ; countTwo < this.cols ; countTwo++)
		{
		    result.element [count] [countTwo] = this.element [count] [countTwo] - given.element [count] [countTwo];
		}
	    }

	}
	else
	    result = new Matrix ();
	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/6/2015
    // Purpose: Reads the input matrix from a file
    // Parameters: TestInputFile object
    // Return/Output: -
    //***************************************************************

    public void getFromFile (TextInputFile inputFile)
    {
	StringTokenizer values;
	String line = inputFile.readLine ();
	values = new StringTokenizer (line);
	int count = 0;

	this.rows = Integer.parseInt (values.nextToken ());
	this.cols = Integer.parseInt (values.nextToken ());
	this.element = new double [this.rows] [this.cols];

	while (!inputFile.eof ())
	{
	    line = inputFile.readLine ();
	    values = new StringTokenizer (line);

	    for (int countTwo = 0 ; countTwo < this.cols ; countTwo++)
	    {
		this.element [count] [countTwo] = Double.parseDouble (values.nextToken ());
	    }
	    count++;

	}
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/7/2015
    // Purpose: Returns the minor of the matrix
    // Parameters: row and column to be removed
    // Return/Output: Matrix object
    //***************************************************************

    public Matrix minor (int row, int col)
    {
	Matrix result;

	if (row >= 1 && row <= this.rows && col >= 1 && col <= this.cols)
	{
	    result = new Matrix (this.rows - 1, this.cols - 1);

	    for (int count = 0 ; count < result.rows ; count++)
	    {
		for (int countTwo = 0 ; countTwo < result.cols ; countTwo++)
		{
		    if (count < row - 1)
		    {
			if (countTwo < col - 1)
			{
			    result.element [count] [countTwo] = this.element [count] [countTwo];
			}
			else
			{
			    result.element [count] [countTwo] = this.element [count] [countTwo + 1];
			}
		    }
		    else
		    {
			if (countTwo < col - 1)
			{
			    result.element [count] [countTwo] = this.element [count + 1] [countTwo];
			}
			else
			{
			    result.element [count] [countTwo] = this.element [count + 1] [countTwo + 1];
			}
		    }
		}

	    }
	}

	else
	    result = new Matrix ();
	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/7/2015
    // Purpose: determinant the matrix, if possible
    // Parameters: -
    // Return/Output: integer
    //***************************************************************

    public double determinant ()
    {
	int s = 1;
	int j = 0;
	double ans = 0;


	if (this.square ())
	{
	    if (this.rows != 2)
	    {
		for (j = 0 ; j < this.cols ; j++)
		{
		    if (j % 2 == 0)
			s = 1;
		    else
			s = -1;

		    ans = ans + s * this.element [0] [j] * this.minor (1, j + 1).determinant ();
		}
	    }
	    else
	    {
		ans = this.element [0] [0] * this.element [1] [1] - this.element [0] [1] * this.element [1] [0];
	    }
	}
	else
	{
	    System.out.println ("Cannot calculate determinate, matrix is not square");
	    ans = 0;
	}
	return ans;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/7/2015
    // Purpose: concatenate the current matrix with another with the
    //      same number of rows, if not possible, original matrix is returned
    // Parameters: another matrix
    // Return/Output: Matrix object
    //***************************************************************

    public Matrix concatenate (Matrix given)
    {
	Matrix result;
	int tracker = 0;

	if (given.rows == this.rows)
	{
	    result = new Matrix (this.rows, this.cols + given.cols);

	    for (int count = 0 ; count < result.rows ; count++)
	    {
		for (tracker = 0 ; tracker < this.cols ; tracker++)
		{
		    result.element [count] [tracker] = this.element [count] [tracker];
		}

		for (int countTwo = 0 ; countTwo < given.cols ; countTwo++)
		{
		    result.element [count] [tracker] = given.element [count] [countTwo];
		    tracker++;
		}
	    }
	}
	else
	    result = this;
	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/8/2015
    // Purpose: Substitute one column of the matrix with another
    // Parameters: another matrix (rows x 1), column number
    // Return/Output: Matrix object
    //***************************************************************

    public Matrix substitude (Matrix given, int column) throws CloneNotSupportedException
    {
	Matrix result;
	if (given.cols == 1 && column >= 1 && column <= this.cols && given.rows == this.rows)
	{
	    result = new Matrix (this.rows, this.cols);
	    result = (Matrix)this.clone ();

	    for (int count = 0 ; count < result.rows ; count++)
	    {
		result.element [count] [column - 1] = given.element [count] [0];
	    }
	}
	else
	{
	    result = this;
	    System.out.println ("Cannot substitute");
	}

	return result;

    }

    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/8/2015
    // Purpose: splits the matrix into a smaller one at a given column
    // Parameters: column
    // Return/Output: Matrix object
    //***************************************************************

    public Matrix split (int column)
    {
	Matrix result;

	if (column >= 1 && column <= this.cols)
	{
	    int adjustment = column;
	    result = new Matrix (this.rows, this.cols - column);

	    for (int count = 0 ; count < result.rows ; count++)
	    {
		for (int countTwo = 0 ; countTwo < result.cols ; countTwo++)
		{

		    result.element [count] [countTwo] = this.element [count] [adjustment];
		    adjustment++;
		}
		adjustment = column;
	    }
	}
	else
	{
	    result = new Matrix ();
	    System.out.println ("Cannot split");
	}
	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/8/2015
    // Purpose: find the transposed matrix
    // Parameters: -
    // Return/Output: Matrix object
    //***************************************************************

    public Matrix transpose ()
    {
	Matrix result = new Matrix (this.cols, this.rows);

	for (int count = 0 ; count < this.rows ; count++)
	{
	    for (int countTwo = 0 ; countTwo < this.cols ; countTwo++)
	    {
		result.element [countTwo] [count] = this.element [count] [countTwo];
	    }
	}
	return result;
    }


    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/9/2015
    // Purpose: Clones the matrix (Deep clone)
    // Parameters: -
    // Return/Output: identical Matrix
    //***************************************************************

    public Object clone () throws CloneNotSupportedException
    {
	Matrix newMatrix = (Matrix) super.clone ();
	newMatrix.element = (double[] []) this.element.clone ();
	for (int count = 0 ; count < this.rows ; count++)
	{
	    newMatrix.element [count] = (double[]) this.element [count].clone ();
	}
	return newMatrix;
    }
}




