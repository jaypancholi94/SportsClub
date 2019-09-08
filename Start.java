
/**
 * Write a description of class Start here.
 *
 * @author Jay Pancholi
 * @version 27/04/2018
 */

import java.util.*;
import java.io.*; 
public class Start
{

	// main method, starting point of program.
    public static void main(String[] args) throws IOException
    {
        Club sportsClub = new Club("Sports Club");
        UserInterface consoleApp = new UserInterface(sportsClub);
        consoleApp.run();
    }


}
