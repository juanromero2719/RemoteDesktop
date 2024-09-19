/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.awt.Graphics;
import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author wrydmoon
 */
class ReceiveScreen extends Thread{
    
    private ObjectInputStream cObjectInputStream = null;
    private JPanel clientPanel = null;
    private boolean continueLoop = true;
    InputStream oin = null;
    Image image1 = null;

    public ReceiveScreen(InputStream in,JPanel p){
	oin = in;
	clientPanel = p;
	start();
    }

    public void run(){
            
        try{
			
            while(continueLoop){
                        
            byte[] bytes = new byte[1024*1024];
            int count = 0;

                do{

                    count+=oin.read(bytes,count,bytes.length-count);

                }while(!(count>4 && bytes[count-2]==(byte)-1 && bytes[count-1]==(byte)-39));

                image1 = ImageIO.read(new ByteArrayInputStream(bytes));
                image1 = image1.getScaledInstance(clientPanel.getWidth(),clientPanel.getHeight(),Image.SCALE_FAST);

                Graphics graphics = clientPanel.getGraphics();
                graphics.drawImage(image1, 0, 0, clientPanel.getWidth(), clientPanel.getHeight(), clientPanel);
            }

        } catch(IOException ex) {
            ex.printStackTrace();
        }
            
    }
}
