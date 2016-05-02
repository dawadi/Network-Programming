// DGSServer.java
// This Server application is taken from an article published by Quepublishing

import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.Enumeration;
import java.net.*;
import java.io.*;
import java.net.*;

class Lab03Server
{
   public static void main (String [] args) throws IOException
   {
      System.out.println ("Server starting ...\n");

      DatagramSocket s = new DatagramSocket (40050);

      byte [] data = new byte [100];
      
      byte [] buffer2;
      buffer2 = new String (DisplayInterface()).getBytes ();
      
      byte [] buffer3;
      buffer3 = new String (DisplayDNSInfo()).getBytes();


      DatagramPacket dgp = new DatagramPacket (data, data.length);


      while (true)
      {

         s.receive (dgp);
         
         String request = (new String(dgp.getData()).trim());
         

         System.out.println (new String (data));
         
         if (request.equals("Client Requesting Interface Information.....")){
            DatagramPacket dgp2 = new DatagramPacket (buffer2, buffer2.length, dgp.getSocketAddress());
            s.send (dgp2);
            
         }
         
         else if (request.equals("Client Requesting Fully Qualified Domain Name.....")){
            DatagramPacket dgp3 = new DatagramPacket (buffer3, buffer3.length, dgp.getSocketAddress());
            s.send (dgp3);
         }
      }
   }
   
   
   
   
   
   //Genrates interface details
   public static String DisplayInterface(){
      StringBuilder sb1 = new StringBuilder();
      String temp;
   
      try {
         Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
         if (interfaceList == null) {
            sb1.append("\n--NO Interfaces found -- ");
         } 
         else {
            while (interfaceList.hasMoreElements() ) {
				   NetworkInterface iface = interfaceList.nextElement();
				   sb1.append("\nInterface " + iface.getName() + ":");
               Enumeration<InetAddress> addrList = iface.getInetAddresses();
               if (!addrList.hasMoreElements() ) {
                  sb1.append("\n\t --No Addresses for this interface...---");
               } // if
               while (addrList.hasMoreElements( ) ) {
                  InetAddress address  =  addrList.nextElement ( );
                  sb1.append("\n\t Address " 
                                     + (( address instanceof Inet4Address ? "(v4)"
                                     : (address instanceof Inet6Address ? "(v6)" : "(?)" ))));
                  sb1.append(" : " + address.getHostAddress() );
               } //while
               //Display Interface Status
               if(iface.isUp() == true)
                  sb1.append("\n\t Staus: up");
               else
                  sb1.append("\n\t Staus: down");
               //Display Interface Format      
               if(iface.isVirtual() == true)
                  sb1.append("\n\t Format: virtual");
               else
                  sb1.append("\n\t Format: physical");
            } // while
         } // else
      } // try ends
      
      catch (SocketException se) {
         sb1.append("Error getting network interfaces: " + se.getMessage() );
      } // catch
      temp = sb1.toString();
      return temp;

   } 
   
   
   
   
   
   /* Returns the DNS information of the localhost (the remote server) */
    public static String DisplayDNSInfo() {

        String myDNS = new String();
        try {
            InetAddress[] addressList = InetAddress.getAllByName("localhost");
            for (InetAddress address : addressList) {
                myDNS += (address.getHostName() + "/" + address.getHostAddress() + "\n");
            }
        } catch (UnknownHostException e) {
            System.out.println("\n Unable to find address for localhost");
        }

        return myDNS;
    }  
}