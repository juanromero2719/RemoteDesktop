/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author wrydmoon
 */
public class Authenticate extends JFrame implements ActionListener {
    
    private Socket clientSocket = null;
    DataOutputStream passchk = null;
    DataInputStream verification = null;
    String verify = "";
    JButton submit;
    JPanel panel;
    JLabel label, labell;
    String width="", height="";
    JTextField text;

    public Authenticate(Socket clientSocket) {
        
        this.clientSocket = clientSocket;
        this.setLayout(new BorderLayout());
        
        labell = new JLabel();
        labell.setText("Password");
        text = new JTextField(15);
        label = new JLabel();
        label.setText("");
        submit = new JButton("Submit");
        panel = new JPanel(new GridLayout(2,1));
        panel.add(labell);
        panel.add(text);
        panel.add(label);
        panel.add(submit);
        add(panel, BorderLayout.CENTER);
        submit.addActionListener(this);
        setTitle("Login Form");
        
        
    }
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
        
        String value1 = text.getText();
        
        try{
                                
            passchk = new DataOutputStream(clientSocket.getOutputStream());
            verification = new DataInputStream(clientSocket.getInputStream());
            
            passchk.writeUTF(value1);
            verify = verification.readUTF();
                               
        }catch(Exception exception){
            exception.printStackTrace();
        }
        
        if(verify.equals("valid")){
            
            try{
                
                width = verification.readUTF();
                height = verification.readUTF();
                
            }catch(IOException exception){
                
                exception.printStackTrace();
            }
                        
            CreateFrame abc = new CreateFrame(clientSocket, width, height);
            dispose();
            
        }else{
        
            System.out.println("Ingresa una password valida");
            JOptionPane.showMessageDialog(this, "password es incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            dispose();
        }
        
    }
    
}
