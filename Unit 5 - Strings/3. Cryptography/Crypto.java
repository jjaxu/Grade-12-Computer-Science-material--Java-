// The "Crypto" class.
import java.awt.*;
import hsa.*;

//***************************************************************
// Author: Jackie Xu
// Date: 11/3/2015
// Purpose: To learn how to encrypt and decrypt documents
//***************************************************************

public class Crypto
{
    static Console c;
    
    public static void main (String[] args)
    {
	c = new Console ();
	Vigenere secure;        
	String file;
	String key;
	char mode = 'e';
       
	c.println("Welcome to Cryptography!");
	
	c.println();
	c.println("Press 'e' to encrypt a file, any other key to decrypt a file...");
	mode = c.getChar();
	
	if (mode == 'e' || mode == 'E')
	{
	    c.print("Please enter the name of the text (.txt) file to encrypt: ");
	    file = c.readLine();
	    c.print("Please enter a key: ");
	    key = c.readLine();
	    secure = new Vigenere(key);  
	    secure.encrypt(file);
	}
	else
	{
	    c.print("Please enter the name of the cipher (.cyp) file to decrypt: ");
	    file = c.readLine();
	    c.print("Please enter the key: ");
	    key = c.readLine();
	    secure = new Vigenere(key);  
	    secure.decrypt(file);
	}        

    }
}
 
//***************************************************************
// Author: Jackie Xu
// Date: 11/3/2015
// Purpose: 
// Fields:
//      key - key of the cipher (String) 
// Methods:
/*      encrypt - encrypts the given .txt file with the key
	encryptLine - encrypts a single line (called by encrypt)
	decrypt - decrypts the given .txt file with the key
	decryptLine - decrypts a single line (called by decrypt)
*/
//***************************************************************

class Vigenere
{
    protected String key;
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/3/2015
    // Purpose: Set the key of the cipher
    // Parameters: inputKey (String)
    // Return/Output: -
    //***************************************************************
    
    public Vigenere (String inputKey)
    {
	this.key = inputKey;
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/3/2015
    // Purpose: encrypts a given .txt file and writes the encrypted file 
    //        to a .cyp file
    // Parameters: name of file (String)
    // Return/Output: -
    //***************************************************************
    
    public void encrypt (String fileName)
    {
	String line = "";
	TextInputFile input = new TextInputFile(fileName + ".txt");
	TextOutputFile output =  new TextOutputFile (fileName + ".cyp");
	
	while (!input.eof ())
	{
	    line = input.readLine ();
	    line = this.encryptLine(line);
	    output.println(line);
	}
	input.close ();
	output.close ();
	System.out.println ("Encrypted file has been successfully written to " + fileName + ".cyp");

    }
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/3/2015
    // Purpose: Encrypts and returns a line of text
    // Parameters: line (String)
    // Return/Output: encrypted line (String)
    //***************************************************************
    
    public String encryptLine(String line)
    {
	int valueChar;
	int offSet;
	int countKey = 0;
	int resultValue;
	String encrypted = "";
	for (int count = 0; count < line.length(); count++)
	{
	    valueChar = (int)line.charAt(count);
	    
	    if (countKey < this.key.length())
		offSet = (int)(this.key.charAt(countKey)) - 32;
	    else
	    {
		offSet = (int)(this.key.charAt(0)) - 32;
		countKey = 0;
	    }
	    countKey++; 
	    
	    resultValue = valueChar + offSet;
	    
	    if (resultValue > 126)
		resultValue = resultValue + (-126 + 31);
	    encrypted = encrypted + (char)(resultValue);            
	    
	}
	return encrypted;
	
    }
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/4/2015
    // Purpose: decrypts a given .cyp file and writes the decrypted file 
    //        to a plain text(.pln) file
    // Parameters: fileName (String)
    // Return/Output: -
    //***************************************************************
    
    public void decrypt (String fileName)
    {
	String line = "";
	
	TextInputFile input = new TextInputFile(fileName + ".cyp");
	TextOutputFile output = new TextOutputFile (fileName + ".pln");
	while (!input.eof ())
	{
	    line = input.readLine ();
	    line = this.decryptLine(line);            
	    output.println(line);
	}
	input.close ();
	output.close ();
	System.out.println ("Decrypted file has been successfully written to " + fileName + ".pln"); 
    }
    
    //***************************************************************
    // Author: Jackie Xu
    // Date: 11/4/2015
    // Purpose: Decrypts and returns a line of text
    // Parameters: line (String)
    // Return/Output: decrypted line (String)
    //***************************************************************
    
    public String decryptLine (String line)
    {    
	int valueChar;
	int offSet;
	int countKey = 0;
	int resultValue;
	String decrypted = "";
	
	
	for (int count = 0; count < line.length(); count++)
	{
	    
	    if (countKey < this.key.length())
		offSet = (int)(this.key.charAt(countKey)) - 32;
	    else
	    {
		offSet = (int)(this.key.charAt(0)) - 32;
		countKey = 0;
	    }
	    resultValue = (int)line.charAt(count);
	    
	    if (resultValue - offSet < 32)
		resultValue = resultValue - 31 + 126;  
	    countKey++;

	    valueChar = resultValue - offSet;
	    decrypted = decrypted + (char)(valueChar);  
	} 
	return decrypted;
    }
}

