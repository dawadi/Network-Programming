package lab06;

import java.io.*;
import java.net.*;

public class ChatClient extends Thread{
    private String messageSent = "";
    private String messageReceived = "";
    private volatile boolean isRunning = true;
    private String serverIp, userName;
    private int serverPort;
    StringBuilder sb;
    GUIapplication myGui;
    
    
    public void UseChatClient(String serverIp, int serverPort, String userName) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.userName = userName;
    }
    
    public void run() {
        try { // begin try block
            myGui = new GUIapplication();
            Socket mySocket = new Socket(serverIp, serverPort); // server IP and port #

            /********************* Create DataInputStream instance ********/
            DataInputStream dataIn = new DataInputStream ( mySocket.getInputStream() );
            /********************* Create DataOutputStream instance *******/
            DataOutputStream dataOut = new DataOutputStream (mySocket.getOutputStream ( ) );
            
            System.out.println("Client code Started");
            
            while (isRunning) { // while loop begins here  
                
                sb = new StringBuilder();
                
                //if(!messageSent.equals("")) {
                  
                    /**************Now that the client entered the message send it out ********/
                    
                    dataOut.writeUTF (userName + ": " +messageSent);
                    /******* Message sent to the Server ******************/
                    //System.out.println("\n Message Sent to the Server:\t" + messageSent);

                    /************ Reset the String.....*******************/
                    //messageSent = "";

                    /*************** Receive the message now....... *********/
                    messageReceived = dataIn.readUTF( );
                    sb.append(messageReceived);

                    /**************** Display the received message **********/
                    //System.out.println("\nReceived Message from the Server...\t"+ messageReceived);
                    //myGui.AppendTextArea("hey");
                    //System.out.println(sb.toString());
                //}

            } // end of the while loop                            
        } // end of the try block
        catch (Exception e) { // Exception catch block begins here...
            e.printStackTrace();
        } // end of the Exception catch block
    }
    
    public void SetMsg(String clientMsg){
        messageSent = clientMsg;
    }
    
    public void EndConnection(){
        isRunning = false;
    }

} // end of the main begins
    
