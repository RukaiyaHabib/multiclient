package chat_client;
import java.awt.EventQueue;
import java.net.*;
import java.awt.Image;
import java.lang.*;
import javax.swing.ImageIcon;
import java.io.*;
import java.util.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

public class client_frame extends javax.swing.JFrame 
{
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 2222;
    Boolean isConnected = false;
    
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    
    //--------------------------//
    
    public void ListenThread() 
    {
         Thread IncomingReader = new Thread(new IncomingReader());
         IncomingReader.start();
    }
    
    //--------------------------//
    
    public void userAdd(String data) 
    {
         users.add(data);
    }
    
    //--------------------------//
    
    public void userRemove(String data) 
    {
         ta_chat.append(data + " is now offline.\n");
    }
    
    //--------------------------//
    
    public void writeUsers() 
    {
         String[] tempList = new String[(users.size())];
         users.toArray(tempList);
         for (String token:tempList) 
         {
             //users.append(token + "\n");
         }
    }
    
    //--------------------------//
    
    public void sendDisconnect() 
    {
        String bye = (username + ": :Disconnect");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            ta_chat.append("Could not send Disconnect message.\n");
        }
    }

    //--------------------------//
    
    public void Disconnect() 
    {
        try 
        {
            ta_chat.append("Disconnected.\n");
            sock.close();
        } catch(Exception ex) {
            ta_chat.append("Failed to disconnect. \n");
        }
        isConnected = false;
        tf_username.setEditable(true);

    }
    
    public client_frame() 
    {
    	getContentPane().setBackground(new Color(127, 255, 212));
        initComponents();
    }
    
    //--------------------------//
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, done = "Done", connect = "Connect", disconnect = "Disconnect", chat = "Chat";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                     data = stream.split(":");

                     if (data[2].equals(chat)) 
                     {
                        ta_chat.append(data[0] + ": " + data[1] + "\n");
                        ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                     } 
                     else if (data[2].equals(connect))
                     {
                        ta_chat.removeAll();
                        userAdd(data[0]);
                     } 
                     else if (data[2].equals(disconnect)) 
                     {
                         userRemove(data[0]);
                     } 
                     else if (data[2].equals(done)) 
                     {
                        //users.setText("");
                        writeUsers();
                        users.clear();
                     }
                }
           }catch(Exception ex) { }
        }
    }

    //--------------------------//
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb_address = new javax.swing.JLabel();
        tf_address = new javax.swing.JTextField();
        lb_port = new javax.swing.JLabel();
        tf_port = new javax.swing.JTextField();
        lb_username = new javax.swing.JLabel();
        lb_username.setBackground(new Color(102, 205, 170));
        lb_username.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        tf_username = new javax.swing.JTextField();
        b_connect = new javax.swing.JButton();
        b_connect.setBackground(new Color(240, 230, 140));
        b_disconnect = new javax.swing.JButton();
        b_disconnect.setBackground(new Color(135, 206, 235));
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        tf_chat = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();
        b_send.setBackground(new Color(238, 130, 238));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setName("client"); // NOI18N
        setResizable(false);

        lb_address.setText("Address : ");

        tf_address.setText("localhost");
        tf_address.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_addressActionPerformed(evt);
            }
        });

        lb_port.setText("Port :");

        tf_port.setText("2222");
        tf_port.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_portActionPerformed(evt);
            }
        });

        lb_username.setText("Username :");

        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        b_connect.setText("Connect");
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        b_disconnect.setText("Disconnect");
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        b_send.setText("SEND");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });
       
        
        label = new JLabel("");
        Image img=new ImageIcon(this.getClass().getResource("/icon.png")).getImage();
        label.setIcon(new ImageIcon(img));
        
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        							.addComponent(tf_chat, GroupLayout.PREFERRED_SIZE, 352, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(b_send, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
        						.addGroup(Alignment.LEADING, layout.createSequentialGroup()
        							.addGap(10)
        							.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 289, GroupLayout.PREFERRED_SIZE)
        							.addGap(160)
        							.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(lb_port)
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addComponent(tf_port, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
        								.addComponent(b_disconnect)
        								.addComponent(b_connect)
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(lb_address, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE)
        									.addPreferredGap(ComponentPlacement.UNRELATED)
        									.addComponent(tf_address, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
        								.addGroup(layout.createSequentialGroup()
        									.addComponent(lb_username, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
        									.addGap(18)
        									.addComponent(tf_username, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)))))
        					.addGap(21))
        				.addGroup(Alignment.TRAILING, layout.createSequentialGroup()
        					.addComponent(label, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
        					.addGap(234))))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(label, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(tf_username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lb_username))
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(tf_address, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lb_address, GroupLayout.PREFERRED_SIZE, 12, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(tf_port, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lb_port))
        					.addGap(27)
        					.addComponent(b_connect)
        					.addGap(18)
        					.addComponent(b_disconnect)
        					.addGap(39))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)))
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(tf_chat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap(33, Short.MAX_VALUE))
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(b_send, GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        					.addGap(22))))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_addressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_addressActionPerformed
       
    }//GEN-LAST:event_tf_addressActionPerformed

    private void tf_portActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_portActionPerformed
   
    }//GEN-LAST:event_tf_portActionPerformed

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed
    
    }//GEN-LAST:event_tf_usernameActionPerformed

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed
        if (isConnected == false) 
        {
            username = tf_username.getText();
            tf_username.setEditable(false);

            try 
            {
                sock = new Socket("172.16.39.38", 2222);
                InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(streamreader);
                writer = new PrintWriter(sock.getOutputStream());
                writer.println(username + ":has connected.:Connect");
                writer.flush(); 
                isConnected = true; 
            } 
            catch (Exception ex) 
            {
                ta_chat.append("Cannot Connect! Try Again. \n");
                tf_username.setEditable(true);
            }
            
            ListenThread();
            
        } else if (isConnected == true) 
        {
            ta_chat.append("You are already connected. \n");
        }
    }//GEN-LAST:event_b_connectActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        sendDisconnect();
        Disconnect();
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
               writer.println(username + ":" + tf_chat.getText() + ":" + "Chat");
               writer.flush(); // flushes the buffer
            } catch (Exception ex) {
                ta_chat.append("Message was not sent. \n");
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }//GEN-LAST:event_b_sendActionPerformed

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new client_frame().setVisible(true);
            }
        });
    }
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_address;
    private javax.swing.JLabel lb_port;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_address;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JTextField tf_port;
    private javax.swing.JTextField tf_username;
    private JLabel label;
}
