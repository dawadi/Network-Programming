/*******************************************************************************
 * Name: Rakul Mahenthiran
 * Date: Oct 27, 2105
 * Student#: 822 240 982
 * Student ID: mhnr0075
 * Class: MyGui.java
 * Description: This is the GUI for the client program
 ******************************************************************************/

package lab05client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class MyGui extends JFrame {

    private JPanel contentPane;
    private JTextField textField1;
    private JTextField textField2;
    private File file;
    private String ipAddr;
    private int portNum;
 
    
    public MyGui() {
       	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 643, 304);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
		
	JLabel name = new JLabel("Rakul Mahenthiran");
	name.setBounds(12, 13, 119, 16);
	contentPane.add(name);
		
	JLabel course = new JLabel("CENG 320, LAB5");
	course.setBounds(12, 33, 132, 16);
	contentPane.add(course);
		
	JLabel title = new JLabel("TCP/UDP File Backup");
	title.setBounds(12, 52, 132, 16);
	contentPane.add(title);
		
	JLabel ip = new JLabel("Remote Host IP: ");
	ip.setBounds(12, 98, 105, 16);
	contentPane.add(ip);
		
	textField1 = new JTextField();
	textField1.setBounds(112, 95, 188, 22);
	contentPane.add(textField1);
	textField1.setColumns(10);
		
	JLabel port = new JLabel("Port Number: ");
	port.setBounds(12, 127, 88, 16);
	contentPane.add(port);
		
	textField2 = new JTextField();
	textField2.setColumns(10);
	textField2.setBounds(112, 127, 188, 22);
	contentPane.add(textField2);
		
	final JTextArea textArea = new JTextArea();
	textArea.setBounds(310, 30, 303, 214);
	contentPane.add(textArea);
	
        ButtonGroup btnGrp = new ButtonGroup();
        
	final JRadioButton udpBtn = new JRadioButton("UDP File Transfer");
        udpBtn.addItemListener(new ItemListener() {
               public void itemStateChanged(ItemEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    //makes sure user selects a file
                    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                }
            }    
        });
	udpBtn.setBounds(12, 158, 163, 25);
	contentPane.add(udpBtn);
		
	final JRadioButton tcpBtn = new JRadioButton("TCP File Transfer");
        tcpBtn.addItemListener(new ItemListener() {
               public void itemStateChanged(ItemEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    //makes sure user selects a file
                    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                    file = fileChooser.getSelectedFile();
                }
            }    
        });
	tcpBtn.setBounds(12, 188, 163, 25);
	contentPane.add(tcpBtn);
        
        btnGrp.add(tcpBtn);
        btnGrp.add(udpBtn);
		
	JButton submit = new JButton("Submit");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String ipAddr = textField1.getText();//get IP
                int portNum = Integer.parseInt(textField2.getText());//get port#
                
                //TCP file transfer
                if(tcpBtn.isSelected()) {
                    TcpClient newTcp = new TcpClient();  
                    newTcp.TransferTcpFile(ipAddr, portNum, file);
                    textArea.append("\n" + newTcp.GetServerReply());
                    
                }
                
                //UDP file transfer
                else if(udpBtn.isSelected()) {
                    UdpClient newUdp = new UdpClient();
                    newUdp.TransferUdpFile(ipAddr, portNum, file);
                    textArea.append("\n" + newUdp.GetServerReply());
                }
           
            }
        });
	submit.setBounds(105, 222, 97, 25);
	contentPane.add(submit);
    }
   
}

