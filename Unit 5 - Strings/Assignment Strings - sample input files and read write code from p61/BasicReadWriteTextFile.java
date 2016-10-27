import hsa.*;
//Reference Page 61 in your notes

public class BasicReadWriteTextFile
{
    static Console c;
    final static int MAX = 50;

    static public void main (String args [])
    {
	c = new Console ();
	int i;
	String fileNameIn;
	String fileNameOut;
	String line;
	
	// *********************************
	//     Reading and writing from and to a file
	c.println ("Text File name to read? ");
	fileNameIn = c.readLine ();
	
	c.println ("File name to save #? ");
	fileNameOut = c.readLine ();
	
	TextInputFile input;      // builtin class in ReadyForJava for easy file reading
	input = new TextInputFile (fileNameIn);
	
	       
	TextOutputFile output;    // builtin class in ReadyForJava for easy file writing
	output = new TextOutputFile (fileNameOut);

	i = 0;
	while (!input.eof())      // builtin method 'eof' within object TextInputFile Object input that detects
	{                         // with 'end of file' is reached.
	    line = input.readLine();
	    c.print (i);
	    c.println (line);
	    output.println (line);
	    i++;
	}
	
	input.close ();  // you must remember to close any files you open
	output.close (); // you must remember to close any files you open
	c.println ("\nDone!");    

    }
}
