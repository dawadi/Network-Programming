/*******************************************************************************
 * Name: Rakul Mahenthiran
 * Date: Oct 27, 2105
 * Student#: 822 240 982
 * Student ID: mhnr0075
 * Class: Lab05UdpServer.java
 * Description: This program handles the server end of the UDP client - server
 *              communication.It also saves a .bak file of the file the client 
 *              sends.
 ******************************************************************************/

package lab05udpserver;

import java.io.*;
import java.net.*;


public class Lab05UdpServer {

    public static void main(String args[])throws IOException {
        
        byte [] sendData;
        byte [] receiveData = new byte[1024];
        
        //Datagram socket listening to port 40060
        DatagramSocket s = new DatagramSocket(40060);
        FileOutputStream outputStream = new FileOutputStream("UDPfile.bak");
       
        System.out.println("Server active, waiting for UDP file transfer... ... ...");
        DatagramPacket dgp = new DatagramPacket(receiveData, receiveData.length);
        //recieve data
        s.receive(dgp);
        String trimString = new String (receiveData).trim();
        //write data to file
        outputStream.write(trimString.getBytes());
            
        System.out.println("File saved.");
            
        //send file recieved msg to client
        sendData = new String ("File save on UDP backup server complete.").getBytes();
        DatagramPacket dgp2 = new DatagramPacket (sendData, sendData.length, dgp.getSocketAddress());
        s.send (dgp2);
            
    }
    
}
