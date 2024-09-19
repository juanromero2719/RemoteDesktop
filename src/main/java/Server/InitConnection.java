package Server;


import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wrydmoon
 */
public class InitConnection {
    
    private ServerSocket socket = null;
    private DataInputStream password = null;
    private DataOutputStream verify = null;
    private String width="";
    private String height="";
			
    public InitConnection(int port,String value1){
        
	Robot robot = null;
	Rectangle rectangle = null;
        
        try{

            System.out.println("Esperando respuesta del usuario");

            socket=new ServerSocket(port);

            GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
            Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
            String width=""+dim.getWidth();
            String height=""+dim.getHeight();
            rectangle=new Rectangle(dim);
            robot=new Robot(gDev);

            drawGUI();

            while(true){

                Socket sc=socket.accept();
                password=new DataInputStream(sc.getInputStream());
                verify=new DataOutputStream(sc.getOutputStream());

                String pssword=password.readUTF();

                if(pssword.equals(value1)){

                    verify.writeUTF("valid");
                    verify.writeUTF(width);
                    verify.writeUTF(height);

                    new SendScreen(sc,robot,rectangle);
                    new ReceiveEvents(sc,robot);}

                else{

                    verify.writeUTF("Invalid");
                }

            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
			
    private void drawGUI(){
    }
    
}
