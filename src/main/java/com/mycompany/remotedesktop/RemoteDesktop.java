/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.remotedesktop;
 
import Client.Start;
import javax.swing.JOptionPane;

/**
 *
 * @author wrydmoon
 */
public class RemoteDesktop {

    public static void main(String[] args) {
        
           String port = "4907";
        
            // delegar a una fabrica
           Start conexion = new Start();
           
           // delegar a una vista
           String ip = JOptionPane.showInputDialog("Server ip: ");
           
           // delegar a un controaldor
           conexion.initialize(ip, Integer.parseInt(port));
    }
}
