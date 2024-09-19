/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author wrydmoon
 */
public class SetPassword extends JFrame implements ActionListener{
    
    private static String port="4907";
    private JButton SUBMIT;
    private JPanel panel;
    private JLabel label1,label2;
    private JTextField text1,text2;
    private String value1;
    private String value2;
    private JLabel label;
	
    public SetPassword(){
        
        label1 = new JLabel();	
        label1.setText("Coloca una password");
        text1 = new JTextField(15);
        label=new JLabel();
        label.setText("");
        
        this.setLayout(new BorderLayout());
        
        SUBMIT = new JButton("SUBMIT");
        panel=new JPanel(new GridLayout(2,1));
        
	
        
	panel.add(label1);
        panel.add(text1);
        panel.add(label);
		
	
	panel.add(SUBMIT);
	add(panel,BorderLayout.CENTER);	
	SUBMIT.addActionListener(this);
	setTitle("Coloca una password para conectar con otros usuarios");	
					
    }
	
    public void actionPerformed(ActionEvent ae){

	value1=text1.getText();
	dispose();
        new InitConnection(Integer.parseInt(port),value1);
        
    }
	
    public String getValue1(){

        return value1;
    }
	
    public static void main(String[] args){

	SetPassword frame1= new SetPassword();
	frame1.setSize(300,80); 				
	frame1.setLocation(500,300);
        frame1.setVisible(true);	 
		
    }

}
