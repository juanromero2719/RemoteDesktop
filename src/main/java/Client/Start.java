/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import java.io.IOException;
import javax.swing.JOptionPane;
import java.net.Socket;
import Client.Authenticate;


/**
 *
 * @author wrydmoon
 */
public class Start {
    
    // estos parametros deben pasarsele al controlador o servicio y debe recibir el objeto socket
    public void initialize(String ip, int port){
    
        try{
            
            // Delegar a una fabrica
            Socket socket = new Socket(ip, port);
                      
            System.out.println("Conectando al servidor");
            
            // delegar a una fabrica
            Authenticate framel = new Authenticate(socket);
            
            // delegar a una vista
            framel.setSize(300,800);
            framel.setLocation(500,300);
            framel.setVisible(true);
            
        }catch(Exception exception){
            System.out.println("Error al intentar realizar la conexion con el servidor");
            exception.printStackTrace();
        }
        
    }
    
    static String port = "4907";

    public static void main(String args[]){
        
	String ip = JOptionPane.showInputDialog("Please enter server ip");
	new Start().initialize(ip, Integer.parseInt(port));
        
    }
       
}
