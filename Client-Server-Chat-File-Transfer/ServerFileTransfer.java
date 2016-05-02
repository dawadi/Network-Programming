package lab06;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;


public class ServerFileTransfer {
    
    public void UseFileServer(){
        ServerSocket ss;
        byte [] receiveData;
   
        try {
            ss = new ServerSocket(40050);
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

            bos.close();//close buffer output stream
            s.close(); //close socket
            
        }
        //socket error msg
        catch (UnknownHostException e){
            System.out.println("Sock:"+e.getMessage());}
        //file error
        catch (EOFException e){
            System.out.println("EOF:"+e.getMessage()); }
        //io error
        catch (IOException e){
            System.out.println("IO:"+e.getMessage());}
    }
    
}
