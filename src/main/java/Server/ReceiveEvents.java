package Server;


import java.awt.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        
         try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String command = reader.readLine();
            System.out.println("Command received: " + command); // <-- Aquí

            if (command.equals(Commands.MOVE_MOUSE.getAbbrev())) {
                int x = Integer.parseInt(reader.readLine());
                int y = Integer.parseInt(reader.readLine());
                System.out.println("Moving mouse to: " + x + ", " + y); // <-- Aquí
                robot.mouseMove(x, y);
            } else if (command.equals(Commands.PRESS_MOUSE.getAbbrev())) {
                int button = Integer.parseInt(reader.readLine());
                System.out.println("Pressing mouse button: " + button); // <-- Aquí
                robot.mousePress(button);
            } else if (command.equals(Commands.RELEASE_MOUSE.getAbbrev())) {
                int button = Integer.parseInt(reader.readLine());
                System.out.println("Releasing mouse button: " + button); // <-- Aquí
                robot.mouseRelease(button);
            } else if (command.equals(Commands.PRESS_KEY.getAbbrev())) {
                int keycode = Integer.parseInt(reader.readLine());
                System.out.println("Pressing key: " + keycode); // <-- Aquí
                robot.keyPress(keycode);
            } else if (command.equals(Commands.RELEASE_KEY.getAbbrev())) {
                int keycode = Integer.parseInt(reader.readLine());
                System.out.println("Releasing key: " + keycode); // <-- Aquí
                robot.keyRelease(keycode);
            }
            // ... (manejo de otros comandos)
        } catch (Exception e) {
            e.printStackTrace();
        }

	this.socket = socket;
	this.robot = robot;
	start(); 
        
        
        
    }

    @Override
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
