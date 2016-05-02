//http://cs.lmu.edu/~ray/notes/javanetexamples/

package lab06;

import java.io.*;
import java.net.*;

public class ChatServer {
    private int numClients;
    
    public void StartChatServer() {
       
        int clientNumber = 0;
        
        try {
            
            ServerSocket listener = new ServerSocket(40050);
            new ChatServerThread(listener.accept(), clientNumber++);
              
            
        }
        catch (IOException ioe) { // catch block begins here
                ioe.printStackTrace( );
            } 
    }

    private static class ChatServerThread extends Thread {  // begin the class ChatServerThread

        private Socket mySocketThread;
        private int clientNumber;
        

        /**************** ChatServerThread overloaded constructor begins below ******/
        ChatServerThread ( Socket socketThread, int clientNumber ) { // constructor begins here
            this.mySocketThread = socketThread;
            this.clientNumber = clientNumber;
            System.out.println("got here");
        } // end of the overloaded constructor

        /******** The thread overloaded method run ( ) begins here **********/
        public void run ( ) { // the method start ( ) begins the execution or run
            try { // the try block begins here
                
                System.out.println("Server ");
                String messageReceived = "";
                
                /**** Create DataInputStream and DataOutput Stream from mySocketThread ***********/
                DataInputStream dataInput = new DataInputStream (mySocketThread.getInputStream() );
                DataOutputStream dataOutput = new DataOutputStream (mySocketThread.getOutputStream ( ) );

                try{ // Inner try block begins
                    while ( ( messageReceived = dataInput.readUTF () ) != null ) { // the while loop begins here

                        String messageSent="";

                        messageSent = messageReceived;
                        System.out.println(messageSent);

                        /***** send the message out now.... **************/
                        dataOutput.writeUTF(messageSent);
                    } // while loop ends here
                } // inner try block ends

                catch (SocketException se) { // begin catch block inner
                    dataInput.close();
                    dataOutput.close ( );
                    mySocketThread.close(); // the client's messaged ended in loop and now
                    // close this client's socket.
                } // ends catch block
            } // the try block ends here
            
            catch (IOException ioe) { // catch block begins here
                ioe.printStackTrace( );
            } // catch block ends here                               
        } // the method run ends here. The method start( ) will begin run ( ) method
   } // end of the class ChatServerThread   
}
