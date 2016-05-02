/***************************************************
   Name: Rakul Mahenthiran
   Date: Oct 13, 2015
   Student#: 822 240 982
   Course: CENG 320
   Program: Lab04FileServer.java
   Details: This is the Server portion of a 
            Client-Server TCP communication 
            program. The server is registered to 
            socket 40050. It recieves a Text File
            from the client and creates a backup 
            file on the remote host running the 
            server.
***************************************************/

import java.io.*;
import java.net.*;

public class Lab04FileServer {
   public static void main(String[] args) throws Exception {
      //socket server listening to port 40050
      ServerSocket ss = new ServerSocket(40050);
   
      while (true) {
         System.out.println("Server active & listening... ... ...");
         //establish client connection
         Socket s = ss.accept();
         //byte array for data recieved
         byte[] fileBuffer = new byte[1024];
         
         // Create an instance of InputStream from instance of Client socket
         InputStream is = s.getInputStream();
         //output stream to write data to new file
         FileOutputStream fos = new FileOutputStream("clientSentFile.bak");
         //output stream to write bytes to fos
         BufferedOutputStream bos = new BufferedOutputStream(fos);
         //read from input stream
         int bytesRead = is.read(fileBuffer, 0, fileBuffer.length);
         //write to new file
         bos.write(fileBuffer, 0, bytesRead);
         
         System.out.println("File recieved.");
         bos.close();//close buffer output stream
         s.close(); //close socket  
      }
   }
}