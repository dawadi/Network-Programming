/*******************************************************************************
 * Name: Rakul Mahenthiran
 * Date: Oct 27, 2105
 * Student#: 822 240 982
 * Student ID: mhnr0075
 * Class: Lab05TcpServer.java
 * Description: This program handles the server end of the TCP client - server
 *              communication.It also saves a .bak file of the file the client 
 *              sends.
 ******************************************************************************/

package lab05tcpserver;

import java.io.*;
import java.net.*;


public class Lab05TcpServer {

    public static void main(String[] args) throws Exception{
        ServerSocket ss = new ServerSocket(40050);
        byte [] receiveData;
        byte [] sentData;
   
     
        System.out.println("Server active, waiting for TCP file transfer... ... ...");
        //establish client connection
        Socket s = ss.accept();
        //byte array for data recieved
        receiveData = new byte[1024];

        // Create an instance of InputStream from instance of Client socket
        InputStream is = s.getInputStream();
        //output stream to write data to new file
        FileOutputStream fos = new FileOutputStream("TCPfile.bak");
        //output stream to write bytes to fos
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        //read from input stream
        int bytesRead = is.read(receiveData, 0, receiveData.length);
        //write to new file
        bos.write(receiveData, 0, bytesRead);
            
        System.out.println("File saved.");
            
        sentData = new String ("File save on TCP backup server complete.").getBytes();
        OutputStream os = s.getOutputStream();
        os.write(sentData);
          
        bos.close();//close buffer output stream
        s.close(); //close socket 
       
    }
    
}
