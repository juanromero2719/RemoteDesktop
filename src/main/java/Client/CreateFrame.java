/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.awt.BorderLayout;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

/**
 *
 * @author wrydmoon
 */
public class CreateFrame extends Thread {
    
    String width="", height = "";
    private JFrame frame = new JFrame();
    private JDesktopPane desktop = new JDesktopPane();
    private Socket clientSocket = null;
    private JInternalFrame interFrame = new JInternalFrame("Server Screen", true, true, true);
    private JPanel clientPanel = new JPanel();

    public CreateFrame(Socket clientSocket, String width, String height) {
        this.width = width;
        this.height = height;
        this.clientSocket = clientSocket;
        start();
    }
    
    public void drawGUI(){
        
        frame.add(desktop, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState( frame.getExtendedState() | JFrame.MAXIMIZED_BOTH );
        frame.setVisible(true); 
        
        interFrame.setLayout(new BorderLayout());
        interFrame.getContentPane().add(clientPanel, BorderLayout.CENTER);
        interFrame.setSize(100,100);
        
        desktop.add(interFrame);
        
        try{
    
            interFrame.setMaximum(true);

        }catch(PropertyVetoException exception){

            exception.printStackTrace();
        }

        clientPanel.setFocusable(true);
        clientPanel.setVisible(true);
    }
    
    public void run() { 
		
        InputStream in = null;		
	drawGUI();

	try{
            in = clientSocket.getInputStream();
            
	}catch (IOException exception){
            
            exception.printStackTrace();
	}
		
	new ReceiveScreen(in,clientPanel);		
	new SendEvents(clientSocket,clientPanel,width,height);
        
    }
    
    
    
    
}
