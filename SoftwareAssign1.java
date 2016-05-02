/***************************************************
   Name: Rakul Mahenthiran
   Date: Sept 29, 2015
   Course: CENG 320
   Program: SoftwareAssign1.java
   Details: Given a domain name/IP address this
            program displays all the IP's for the
            given host using DNS. This program
            also displays all the interfaces
            supporting the host.
***************************************************/

import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.Enumeration;
import java.net.*;


public class SoftwareAssign1 extends JFrame {

	private static JFrame frame;
	private static JTextField textField;
	private static JTextField txtName;
   private static JButton btnDisplay;
   private static JTextArea textArea;
   private static JRadioButton rdbtnInterfaceDetails, rdbtnDnsService;
   private static StringBuilder sb1 = new StringBuilder();

	public static void main(String[] args) {
      SoftwareAssign1 window = new SoftwareAssign1();
      window.frame.setVisible(true);
   }

   public SoftwareAssign1(){
      createGUI();
   }

   //Initalize frame contents
   private void createGUI() {

      frame = new JFrame();
      frame.setTitle("Rakul Mahenthiran - Lab 01");
		frame.setBounds(100, 100, 468, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

      //Display name in Text Field
      txtName = new JTextField();
		txtName.setText("Rakul Mahenthiran");
		txtName.setBounds(158, 28, 116, 22);
		frame.getContentPane().add(txtName);
		txtName.setColumns(10);
		txtName.setEditable(false);//non-editable

      //Create Radio Button for DNS Services
		rdbtnDnsService = new JRadioButton("DNS Service");
		rdbtnDnsService.setBounds(32, 83, 127, 25);
		frame.getContentPane().add(rdbtnDnsService);
      //Radio button state controls state of
      //text field and dispaly button
      rdbtnDnsService.addItemListener(new ItemListener() {

         public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
               textField.setEditable(true);
               btnDisplay.setEnabled(true);
            }
            else if (e.getStateChange() == ItemEvent.DESELECTED) {
               textField.setEditable(false);
               btnDisplay.setEnabled(false);
            }
         }
      });

      //Create Radio Button for Interface Details
		rdbtnInterfaceDetails = new JRadioButton("Interface Details");
		rdbtnInterfaceDetails.setBounds(286, 83, 127, 25);
		frame.getContentPane().add(rdbtnInterfaceDetails);
      rdbtnInterfaceDetails.addItemListener(new ItemListener() {

         public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED)
               btnDisplay.setEnabled(true);

            else if (e.getStateChange() == ItemEvent.DESELECTED)
               btnDisplay.setEnabled(false);
         }
      });

      //Create Text Field
		textField = new JTextField();
		textField.setBounds(32, 131, 381, 34);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
      textField.setEditable(false);

      //Create Scroll Bar
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 217, 381, 186);
		frame.getContentPane().add(scrollPane);

      //Create Text Area to display output
		textArea = new JTextArea();
      textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

      //Create Result Button
		btnDisplay = new JButton("Display");
		btnDisplay.setBounds(177, 434, 97, 25);
		frame.getContentPane().add(btnDisplay);
      btnDisplay.setEnabled(false);
      btnDisplay.addActionListener(new ActionListener()
      {
         public void actionPerformed(ActionEvent e)
         {
            //Stringbuilder helps create output to display in text area
            sb1 = new StringBuilder();
            if (rdbtnDnsService.isSelected()){
               DisplayIP();
               sb1.append("\n\n");
            }

            if (rdbtnInterfaceDetails.isSelected()){
               DisplayInterface();
            }

            //Direct stringbuilder to text area
            textArea.setText(sb1.toString());
         }
      });
	}


   //Genrates interface details
   public static void DisplayInterface(){
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
   }


   //Display all the DNS IP's for given host
   public static void DisplayIP(){
      // Get name or address of hosts given on
      // text field
      String [] hostName = {textField.getText()};

      for (String host : hostName ) { // for begins
         try { // try begins
            sb1.append("\n" + host + ":" );
            InetAddress [] addressList = InetAddress.getAllByName(host);
            for (InetAddress address : addressList ) {
               sb1.append("\n\t" + address.getHostName() + "/"
                                  + address.getHostAddress());
            } //for
         } // end of try
         catch (UnknownHostException e) {
            sb1.append("\n\n Unable to find address for " + host );
         } // end of catch
      } // end of for
   }
}
