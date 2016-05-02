package lab06;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class ClientFileTransfer {
    public void UseFileClient(String ipAddr, int portNum, File file){
        Socket s;
        byte [] sendData;
           
        
        //create socket from user input ip and port#
        s = new Socket(ipAddr,portNum);
        //byte array size of file sending
        sendData = new byte[(int) file.length()];
        //stream to read file bytes
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        bis.read(sendData, 0, sendData.length);
        //output stream will be used to send data to server with TCP socket
        OutputStream os = s.getOutputStream();         //file content being sent
        os.write(sendData, 0, sendData.length);

        os.flush(); //clear output stream
        s.close();//close socket
    }
    
}
