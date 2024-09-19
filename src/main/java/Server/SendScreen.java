/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;

/**
 *
 * @author wrydmoon
 */
public class SendScreen extends Thread{

    private Socket socket=null;
    private Robot robot=null;
    private Rectangle rectangle=null;
    private boolean continueLoop=true;
    private OutputStream oos=null;

    public SendScreen(Socket socket,Robot robot,Rectangle rect) {
        
        this.socket=socket;
        this.robot=robot;
        this.rectangle=rect;
        start();
    
    }

    public void run(){
	
	try{
            
            oos=socket.getOutputStream();
	
	}catch(IOException ex){
            ex.printStackTrace();
	}

	while(continueLoop){
            
            BufferedImage image=robot.createScreenCapture(rectangle);

            try{

                ImageIO.write(image,"jpeg",oos);
            }catch(IOException ex){
                ex.printStackTrace();
            }
	
            try{
                Thread.sleep(10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
	}
    }
}
