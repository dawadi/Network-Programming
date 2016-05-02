/*******************************************************************************
 * Name: Rakul Mahenthiran
 * Date: Oct 27, 2105
 * Student#: 822 240 982
 * Student ID: mhnr0075
 * Class: TcpClient.java
 * Description: This class contains a method for:
 *              -for transferring files to TCP server
 *              -for transferring server reply to GUI application to display
 ******************************************************************************/

package lab05client;

import java.io.*;
import java.net.*;


public class TcpClient {
    private String serverReply;
    
    public void TransferTcpFile(String ipAddr, int portNum, File file){
        Socket s;
        byte [] sendData;
           
        try {
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
            
            InputStream is = s.getInputStream();
            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(is));
            serverReply = new String (inFromClient.readLine());

            os.flush(); //clear output stream
            s.close();//close socket
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
    
    public String GetServerReply() {
        return serverReply;
    }
    
}
