/************************
 * Name: Rakul Mahenthiran
 * Date: Dec 1, 2015 
 * Course: CENG320
 * Lab: Lab7
 ************************/

package lab7server;

import java.util.*;
import java.io.*;
import java.net.*;

public class Lab7Server {
    private static ArrayList<Books> library;
    private static String[]info;
    private static String resultString;

    /***************************************************************************
     * Main Method
     **************************************************************************/
    public static void main(String[] args) {
        /*
        ***Create Arraylist of Books, add default books to arraylist***
        */
        library = new ArrayList<Books>();
        library.add(new Books("Rakul Mahenthiran", "Rakul's Life", "Bibliography" , "1", "$9999.99"));
        library.add(new Books("Muhammad Khan", "Introduction to CENG", "Technology", "33", "$39.99"));
        library.add(new Books("Dr.Seuss", "The Cat in the Hat", "Children's Literature", "60", "$14.99"));

        System.out.println("Initiate Server...");
        System.out.println("Enter port number: ");
        
        //assign port to listen to
        Scanner input = new Scanner(System.in);
        int port = input.nextInt();
        
        
        boolean loopRun = true; //loop controller
        
        try {
            //create server socket & accept incomping connections from port
            ServerSocket serverSocket = new ServerSocket(port);
            Socket clientSock = serverSocket.accept();
            
            while (loopRun == true) {
                byte[] data = new byte[1024];
                
                InputStream in = clientSock.getInputStream();

                int readBytes = in.read(data, 0, data.length);

                //splits entries from client message using "," as an splitter
                //stores each entry in aray "info"
                String readin = new String(data).trim();
                info = readin.split(",");
            
                OutputStream out = clientSock.getOutputStream();
                String sentMsg = "";
                Iterator iterator = library.listIterator();
                
                /***************************************************************
                 * If client selects option 1 - Display All. Iterates through 
                 * array list and pints entries to terminal and sends to client.
                 **************************************************************/
                if (info[0].equalsIgnoreCase("1")) {
                    while (iterator.hasNext()) {
                        sentMsg += iterator.next().toString() + "\n";
                    }
                    System.out.println("\n"+sentMsg);
                    out.write(sentMsg.getBytes(), 0, sentMsg.getBytes().length);
                    
                }
                
                /***************************************************************
                 * If client selects option 2 - Add. Adds book to array list 
                 * using fields entered by client. Prints new book and send back
                 * to client.  
                 **************************************************************/
                else if (info[0].equalsIgnoreCase("2")) {
                    library.add(new Books(info[1], info[2], info[3], info[4], info[5]));
                    sentMsg += ("Added: "+library.get(library.size()-1));
                    System.out.println("\n"+sentMsg);
                    out.write(sentMsg.getBytes(), 0, sentMsg.getBytes().length);     
                } 
                
                /***************************************************************
                 * If client selects option 3 - Search. Calls method 
                 * BookSearch() to perform search. Then sends search response to
                 * client.
                 **************************************************************/
                else if (info[0].equalsIgnoreCase("3")) {
                    sentMsg = BookSearch();
                    out.write(sentMsg.getBytes(), 0, sentMsg.getBytes().length);
                }
                
                /***************************************************************
                 * If client selects option 4 - Update. Calls method 
                 * UpdateBook() to perform update. Then sends search response to
                 * client.
                 **************************************************************/
                else if (info[0].equalsIgnoreCase("4")) {
                    sentMsg = BookUpdate();
                    out.write(sentMsg.getBytes(), 0, sentMsg.getBytes().length);
                } 
                
                /***************************************************************
                 * If client selects option 5 - Quit. Ends while loop and closes
                 * socket.
                 **************************************************************/
                else if (info[0].equalsIgnoreCase("5")) {
                    System.out.println("\nServer Shutting Down......");
                    loopRun = false;
                    clientSock.close();
                } 
            }    
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /***************************************************************************
    * This method performs the Book Search. Iterates through array list 
    * searching for book based on the Author Name, Subject Area, or Book Title.
    * Prints search result, or error message if not found. Returns string to 
    * caller.
    ***************************************************************************/
    public static String BookSearch (){
        resultString = "";
        
        //search by author
        if (info[1].equalsIgnoreCase("1")) {
            for (int x = 0; x < library.size(); x++) {
                if (info[2].equalsIgnoreCase(library.get(x).getAuthorName())) {
                    resultString += library.get(x).toString() + "\n";
                }
            }
            if (resultString.equalsIgnoreCase("")) {
                resultString = "\nNo matches found";
            }
            System.out.println("\n"+resultString);
            return resultString;
        } 
        
        //search ny subject
        else if (info[1].equalsIgnoreCase("2")) {                   
            for (int x = 0; x < library.size(); x++) {
                if (info[2].equalsIgnoreCase(library.get(x).getSubjectArea())) {
                    resultString += library.get(x).toString() + "\n";
                }
            }
            if (resultString.equalsIgnoreCase("")) {
                resultString = "\nNo matches found";
            }
            System.out.println("\n"+resultString);
            return resultString;
        } 
        
        //search by title
        else if (info[1].equalsIgnoreCase("3")) {
            for (int x = 0; x < library.size(); x++) {
                if (info[2].equalsIgnoreCase(library.get(x).getBookTitle())) {
                    resultString += library.get(x).toString() + "\n";
                }
            }
            if (resultString.equalsIgnoreCase("")) {
                resultString = "\nNo matches found";
            }
            System.out.println("\n"+resultString);
            return resultString;
        }
        
        else{
            return "\nError";
        }
    }
    
    /***************************************************************************
    * This method performs the Book Update. Iterates through array list 
    * searching for book based on title client entered. Then goes to the field
    * client wants to update and updates.Prints search result, or error message 
    * if not found. Returns string to caller.
    ***************************************************************************/
    public static String BookUpdate(){
        resultString = "";
        
        //update book title
        if (info[2].equalsIgnoreCase("1")) {
            for (int x = 0; x < library.size(); x++) {
                if (info[1].equalsIgnoreCase(library.get(x).getBookTitle())) {
                    library.get(x).setTitle(info[3]);
                    resultString = "Updated: " + library.get(x).toString();
                }
            }
            if (resultString.equalsIgnoreCase("")) {
                resultString = "\nError: " + info[1] + " was not found";
            }
            System.out.println("\n"+resultString);
            return resultString;
        }
                
        //udpate book quantity
        else if (info[2].equalsIgnoreCase("2")) {
            for (int x = 0; x < library.size(); x++) {
                if (info[1].equalsIgnoreCase(library.get(x).getBookTitle())) {
                    library.get(x).setQuantity(info[3]);
                    resultString = "Updated: " + library.get(x).toString();
                }
            }
            if (resultString.equalsIgnoreCase("")) {
                resultString = "\nError: " + info[1] + " was not found";
            }
            System.out.println("\n"+resultString);
            return resultString;  
        }
        
        //update book price
        else if (info[2].equalsIgnoreCase("3")) {
            for (int x = 0; x < library.size(); x++) {
                if (info[1].equalsIgnoreCase(library.get(x).getBookTitle())) {
                    library.get(x).setPrice(info[3]);
                    resultString = "Updated: " + library.get(x).toString();
                }
            }
            if (resultString.equalsIgnoreCase("")) {
                resultString = "\nError: " + info[1] + " was not found";
            }
            System.out.println("\n"+resultString);
            return resultString;  
        }
        
        else{
            return "Error";
        }
    }
}
