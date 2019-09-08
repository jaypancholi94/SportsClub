/**
 * FileUtility is used for reading and writing to/from file
 *
 * @author Jay Pancholi
 * @version 27/04/2018
 */
import java.util.*;
//import java.io.*;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
public class FileUtility
{


    /**
     * Reads data from file returning the lines as a list, or null if error
     *  @param fileName String
     */
    public static ArrayList<String> readFromFile(String fileName) throws IOException
    {
       //System.out.println("To be implemented");
        ArrayList<String> temp = new ArrayList<String>();
        File txt = new File(fileName);
        Scanner txtInput = new Scanner(txt);
        if (txt.length()!=0) 
        {
            while (txtInput.hasNextLine()) 
            { 
                String line = txtInput.nextLine();
                // it will not add commnets and free lines
                if (line.indexOf("//") == -1 && line.length() !=0) 
                {
                   // System.out.println("hey");
                    temp.add(line);    
                } 
                
            }
            
         }
         txtInput.close();
         return temp;

    }
    
    /**
     * Write data to file 
     * @param String fileName, ArrayList<String> data
     */
    public static void writeToFile(String fileName, ArrayList<String> data) throws IOException
    {
         
        File fileOut = new File(fileName);
        PrintWriter out = new PrintWriter(fileOut);
         for (int i = 0 ; i< data.size() ;i++ ) 
         {
            out.println(data.get(i));    
         }
         out.close();
    }
    
}
