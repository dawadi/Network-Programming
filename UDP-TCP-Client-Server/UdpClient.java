/*******************************************************************************
 * Name: Rakul Mahenthiran
 * Date: Oct 27, 2105
 * Student#: 822 240 982
 * Student ID: mhnr0075
 * Class: UdpClient.java
* Description: This class contains a method for:
 *              -for transferring files to UDP server
 *              -for transferring server reply to GUI application to display
 ******************************************************************************/
package lab05client;

import java.io.*;
import java.net.*;


public class UdpClient {
    private String serverReply;
    
    public void TransferUdpFile(String ipAddr, int portNum, File file){
        byte [] sendData = new byte [1024];
        byte [] receiveData = new byte [1024]; 
        
        DatagramSocket s = null;
        DatagramPacket dgp = null;
        
        try {
            InetAddress ia = InetAddress.getByName(ipAddr);
            s = new DatagramSocket();
            
            FileInputStream inputStream = new FileInputStream(file);
            
            int i=0;
            //write file content to byte buffer
            while(inputStream.available()!=0) {
                sendData[i]=(byte)inputStream.read();
                i++;
            }                    
            //create dgp
            dgp = new DatagramPacket (sendData, sendData.length,ia,40060);
            //send dgp
            s.send(dgp);
            
            //receiving dgp 
            dgp = new DatagramPacket (receiveData, receiveData.length, ia, 40061);
            //receive dgp
            s.receive (dgp);
            
            serverReply = new String (dgp.getData()).trim();
            
            inputStream.close();
            s.close();   
        }
        
        catch(FileNotFoundException ex) { 
            System.out.println("Unable to open file '" + file + "'");
        }               
        
        catch(IOException ex) {
            System.out.println("Error reading file '" + file + "'");
        }    
    } 
    
    public String GetServerReply() {
        return serverReply;
    }
}
