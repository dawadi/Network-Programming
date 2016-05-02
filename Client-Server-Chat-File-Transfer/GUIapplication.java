//http://techwiki.ordak.org/index.php?title=How_To_Write_A_Multi-Threaded_Client-Server_Communications_in_Jav
package lab06;

import java.awt.event.*;
import java.io.File;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class GUIapplication extends JFrame {

    private JPanel contentPane;
    private JTextField textField1, textField2, textField3;
    private JRadioButton rdbtn1, rdbtn2, rdbtn3;
    private File file;
    private String communicationEnd;
    private int sentMsgs;
    private ChatClient chatClient;
    private ChatServer chatServer;
    private JTextArea textArea;


    public static void main(String[] args) {
		
	GUIapplication frame = new GUIapplication();
	frame.setVisible(true);
		
    }

    /**
    * Create the frame.
    */
    public GUIapplication() {
        
        setTitle("Multi- Threaded Chat & FTP Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 561, 462);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
        
        chatClient = new ChatClient();
        chatServer = new ChatServer();
	
	JLabel label1 = new JLabel("Name: Rakul Mahenthiran");
	label1.setBounds(10, 11, 165, 14);
	contentPane.add(label1);
		
	JLabel label2 = new JLabel("CENG 320, Lab 06");
	label2.setBounds(10, 31, 115, 14);
	contentPane.add(label2);
		
	JLabel label3 = new JLabel("IP Address:");
	label3.setBounds(10, 67, 75, 14);
	contentPane.add(label3);
		
	textField1 = new JTextField();
	textField1.setBounds(92, 64, 140, 20);
	contentPane.add(textField1);
	textField1.setColumns(10);
		
	JLabel label4 = new JLabel("Port Number:");
	label4.setBounds(10, 93, 85, 14);
	contentPane.add(label4);
		
	textField2 = new JTextField();
	textField2.setBounds(92, 90, 140, 20);
	contentPane.add(textField2);
	textField2.setColumns(10);
		
	JLabel label5 = new JLabel("Message:");
	label5.setBounds(10, 118, 64, 14);
	contentPane.add(label5);
		
	textField3 = new JTextField();
	textField3.setBounds(75, 118, 460, 20);
	contentPane.add(textField3);
	textField3.setColumns(10);
        textField3.setText("Enter Message here");
		
        ButtonGroup grp = new ButtonGroup();
        
        rdbtn1 = new JRadioButton("Initiate Server");
	rdbtn1.setBounds(10, 155, 120, 23);
	contentPane.add(rdbtn1);
		
        rdbtn2 = new JRadioButton("Initiate Client");
	rdbtn2.setBounds(140, 155, 120, 23);
	contentPane.add(rdbtn2);
        
        grp.add(rdbtn1);
        grp.add(rdbtn2);
		
	rdbtn3 = new JRadioButton("File Transfer Option");
	rdbtn3.setBounds(275, 155, 165, 23);
	contentPane.add(rdbtn3);
	
	JButton btn1 = new JButton("Connect");
        btn1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(rdbtn1.isSelected() && rdbtn3.isSelected() ) {
                    ServerFileTransfer fileServer;
                    fileServer = new ServerFileTransfer();
                    fileServer.UseFileServer();
                }
                else if(rdbtn1.isSelected()) {
                    String[] args = {};
                    chatServer.StartChatServer();
                    
                }
                else if(rdbtn2.isSelected()) { 
                    chatClient.UseChatClient(textField1.getText(), 
                            Integer.parseInt(textField2.getText()), 
                            textField3.getText());
                    
                    chatClient.start();
                }
            }
        });
	btn1.setBounds(10, 185, 89, 23);
	contentPane.add(btn1);
	
	JButton btn2 = new JButton("Disconnect");
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                chatClient.EndConnection();
            }
        });
	btn2.setBounds(125, 185, 95, 23);
	contentPane.add(btn2);
	
        //sentMsgs = 0;
        //communicationEnd = null;
        
	JButton btn3 = new JButton("Send");
        btn3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(rdbtn1.isSelected()){
                    //chatServer.SetMsg(textField3.getText()); 
                }
                else if(rdbtn2.isSelected()){
                    chatClient.SetMsg(textField3.getText()); 
                }
            }
        });
	btn3.setBounds(237, 185, 89, 23);
	contentPane.add(btn3);
		
	JButton btn4 = new JButton("File Transfer");
        btn4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(rdbtn2.isSelected() && rdbtn3.isSelected()) {
                    JFileChooser fileChooser = new JFileChooser();
                    if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                        file = fileChooser.getSelectedFile();
                    }
                    
                    ClientFileTransfer fileClient = new ClientFileTransfer();
                    fileClient.UseFileClient(textField1.getText(), 
                            Integer.parseInt(textField2.getText()), file);
                }
            }    
        });
	btn4.setBounds(356, 185, 110, 23);
	contentPane.add(btn4);
		
	textArea = new JTextArea();
	textArea.setBounds(10, 219, 525, 194);
	contentPane.add(textArea);
        
    }
    
    public void AppendTextArea(String text) {
        textArea.setText("\n" + text);
        textArea.append("hello");
        
    }
}
