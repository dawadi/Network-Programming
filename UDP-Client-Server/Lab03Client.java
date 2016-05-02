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

public class Lab03Client extends javax.swing.JFrame {

   public static void main (String [] args){
      new Lab03Client().setVisible(true);
   }
   
   
    /**
     * Creates new form NewJFrame
     */
    public Lab03Client() {
        initComponents();
    }
                         
    private void initComponents() {
        
      jPanel1 = new javax.swing.JPanel();
      jPanel2 = new javax.swing.JPanel();
      jPanel3 = new javax.swing.JPanel();
      jTextField1 = new javax.swing.JTextField();
      jTextField2 = new javax.swing.JTextField();
      jTextField3 = new javax.swing.JTextField();
      jTextField4 = new javax.swing.JTextField();
      jTextField5 = new javax.swing.JTextField();
      jTextField6 = new javax.swing.JTextField();
      jTextField7 = new javax.swing.JTextField();
      jTextField8 = new javax.swing.JTextField();
      jRadioButton1 = new javax.swing.JRadioButton();
      jRadioButton2 = new javax.swing.JRadioButton();
      jButton1 = new javax.swing.JButton(); 
      jScrollPane1 = new javax.swing.JScrollPane();
      jTextArea2 = new javax.swing.JTextArea();

      setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

      jTextField1.setText("Rakul Mahenthiran");
      jTextField1.setEditable(false);

      jTextField2.setText("CENG 320, Lab03");
      jTextField2.setEditable(false);

      jTextField3.setText("Remote DNS & Interface Information");
      jTextField3.setEditable(false);

      javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
      jPanel1.setLayout(jPanel1Layout);
      jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

      jTextField4.setText("Server IP Address:");
      jTextField4.setEditable(false);

      //jTextField5.setEditable(false);

      jTextField6.setText("Server Port Number:");
      jTextField6.setEditable(false);

      //jTextField7.setEditable(false);

      jRadioButton1.setText("Interface Information");
      jRadioButton1.addItemListener( new ItemListener(){
         public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
               buffer = new String ("Client Requesting Interface Information.....").getBytes ();
               jRadioButton2.setEnabled(false);   
            }
            else if (e.getStateChange() == ItemEvent.DESELECTED) {
               jRadioButton2.setEnabled(true);
            }
         }});

      jRadioButton2.setText("Fully Qualified Domain Name");
      jRadioButton2.addItemListener( new ItemListener(){
         public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
               buffer = new String ("Client Requesting Fully Qualified Domain Name.....").getBytes ();
               jRadioButton1.setEnabled(false);
                  
            }
            else if (e.getStateChange() == ItemEvent.DESELECTED) {
               jRadioButton1.setEnabled(true);
            }
         }});
      
      jButton1.setText("Remote Host Information");  
      jButton1.addActionListener( new ActionListener(){
         public void actionPerformed(ActionEvent e){
            boolean ipPresent = true;
            boolean portPresent = true;
            boolean buttonSelected = true;
            jTextArea2.setText("");
         
            if (jTextField5.getText().equals("")) {
               jTextArea2.append("Missing Server IP Address\n");
               ipPresent = false;
            }
               
            if (jTextField7.getText().equals("")) {
               jTextArea2.append("Missing Server Port Number\n");
               portPresent = false;
            }
            if ((jRadioButton1.isSelected() == false) && (jRadioButton2.isSelected() == false)) {
               jTextArea2.append("Please Check one of the Buttons");
               buttonSelected = false;
            }
            
            if((ipPresent == true) && (portPresent == true) && (buttonSelected == true)) 
               SendClientRequest();
           
         }   
      });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 220, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton1)
                                    .addComponent(jRadioButton2))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextField5)
                            .addComponent(jTextField7))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(190, Short.MAX_VALUE))
        );

        jTextField8.setText("Information Display From Server");
        jTextField8.setEditable(false);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jTextArea2.setEditable(false);
        jScrollPane1.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(275, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>    
    
    public static void SendClientRequest() {
      ipAddr = jTextField5.getText();
      portNum = Integer.parseInt(jTextField7.getText());
      
      DatagramSocket s = null;

      try
      {

          s = new DatagramSocket ();

          InetAddress ia = InetAddress.getByName (ipAddr);

          DatagramPacket dgp = new DatagramPacket (buffer,
                                                   buffer.length,
                                                   ia,
                                                   portNum);

          s.send (dgp);

          byte [] buffer2 = new byte [10000];


          dgp = new DatagramPacket (buffer2,
                                    buffer2.length,
                                    ia,
                                    40069);


          s.receive (dgp);


          jTextArea2.setText(new String (dgp.getData ()));

      }
      catch (IOException e)
      {
          System.out.println (e.toString ());
      }
      finally
      {
          if (s != null)
              s.close ();
      }

    }                        

    // Variables declaration - do not modify                     
    public static javax.swing.JButton jButton1;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    public static javax.swing.JRadioButton jRadioButton1;
    public static javax.swing.JRadioButton jRadioButton2;
    public static javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextArea jTextArea2;
    public static javax.swing.JTextField jTextField1;
    public static javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField3;
    public static javax.swing.JTextField jTextField4;
    public static javax.swing.JTextField jTextField5;
    public static javax.swing.JTextField jTextField6;
    public static javax.swing.JTextField jTextField7;
    public static javax.swing.JTextField jTextField8;
    private static byte [] buffer;
    private static String ipAddr; 
    private static int portNum;     

    // End of variables declaration                   
}
