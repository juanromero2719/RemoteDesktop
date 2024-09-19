package Server;


import java.awt.Robot;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author wrydmoon
 */
public class ReceiveEvents extends Thread{
    
    private Socket socket= null;
    private Robot robot = null;
    private boolean continueLoop = true;

    public ReceiveEvents(Socket socket, Robot robot){

	this.socket = socket;
	this.robot = robot;
	start(); 
        
    }

    public void run(){
        
	Scanner scanner = null;
	
            try {
			
                scanner = new Scanner(socket.getInputStream());
                
                    while(continueLoop){
			
			int command = scanner.nextInt();
                        
                            switch(command){
                            
                                case-1:
                                    robot.mousePress(scanner.nextInt());
                                    break;
                                case-2:
                                    robot.mouseRelease(scanner.nextInt());
                                    break;
                                case-3:
                                    robot.keyPress(scanner.nextInt());
                                    break;
                                case-4:
                                    robot.keyRelease(scanner.nextInt());
                                    break;
                                case-5:
                                    robot.mouseMove(scanner.nextInt(),scanner.nextInt());
                                    break;
                            }

			}
                    
            }catch(IOException ex){
                
		ex.printStackTrace();
            }
    }
				
}
