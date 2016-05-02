/***************************************************
   Name: Rakul Mahenthiran
   Date: Oct 13, 2015
   Student#: 822 240 982
   Course: CENG 320
   Program: Lab04FileClient.java
   Details: This is the Client portion of a 
            Client-Server TCP communication 
            program. The client sends an existing
            Text File throught a TCP socket to a 
            remote Host running the server program. 
***************************************************/

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class Lab04FileClient {
   
   private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
   private File myFile;
   
   public static void main(String[] args) throws Exception {
   
      Lab04FileClient window = new Lab04FileClient();
	   window.frame.setVisible(true);
   }
   
   //create application
   public Lab04FileClient() {
		initialize();
	}
   
   //initalize components
   private void initialize() {
	   frame = new JFrame();
		frame.setBounds(100, 100, 414, 232);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHostIp = new JLabel("Host IP:");
		lblHostIp.setBounds(12, 85, 56, 16);
		frame.getContentPane().add(lblHostIp);
		
      //Host IP field
		textField = new JTextField();
		textField.setBounds(64, 82, 146, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPort = new JLabel("Port #: ");
		lblPort.setBounds(241, 85, 45, 16);
		frame.getContentPane().add(lblPort);
		
      //Port# field
		textField_1 = new JTextField();
		textField_1.setBounds(289, 82, 92, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
      //Save File button
		JButton btnSaveFile = new JButton("Save File");
      btnSaveFile.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            //create file chooser
            JFileChooser fileChooser = new JFileChooser();
            //makes sure user selects a file
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
               myFile = fileChooser.getSelectedFile();
               //put selected file name in text field
               textField_2.setText(myFile.getName()); 
            }    
         }
      });
		btnSaveFile.setBounds(284, 116, 97, 25);
		frame.getContentPane().add(btnSaveFile);      
		
      //File name text field
		textField_2 = new JTextField();
		textField_2.setBounds(12, 117, 264, 22);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
      btnSubmit.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e){
            //check if all fields have an input
            if ((textField_1.getText() != null) && (textField.getText() != null) && (myFile != null)) {
               Client(); //calls client method  
            }      
         }
      });
		btnSubmit.setBounds(147, 152, 97, 25);
		frame.getContentPane().add(btnSubmit);
		
		JLabel lblRakulMahenthiran = new JLabel("Rakul Mahenthiran");
		lblRakulMahenthiran.setBounds(12, 13, 111, 16);
		frame.getContentPane().add(lblRakulMahenthiran);
		
		JLabel lblCengLab = new JLabel("CENG 320 Lab04");
		lblCengLab.setBounds(12, 30, 111, 16);
		frame.getContentPane().add(lblCengLab);
		
		JLabel lblTcpFileBackup = new JLabel("TCP File Backup");
		lblTcpFileBackup.setBounds(12, 48, 111, 16);
		frame.getContentPane().add(lblTcpFileBackup);
	}
  
   //Method handles the sending the file throught tcp socket   
   public void Client() {
      String ipAddr = textField.getText();//get IP
      int portNum = Integer.parseInt(textField_1.getText());//get port#
      Socket s;
      
      try {
         //create socket from user input ip and port#
         s = new Socket(ipAddr,portNum);
         //byte array size of file sending
         byte[] fileBuffer = new byte[(int) myFile.length()];
         //stream to read file bytes
         BufferedInputStream bis = new BufferedInputStream(new FileInputStream(myFile));
         bis.read(fileBuffer, 0, fileBuffer.length);
         //output stream will be used to send data to server with TCP socket
         OutputStream os = s.getOutputStream();
         //file content being sent
         os.write(fileBuffer, 0, fileBuffer.length);
                     
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
        
}