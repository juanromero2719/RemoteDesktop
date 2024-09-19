/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Client;

import javax.swing.JOptionPane;
import java.net.Socket;

/**
 *
 * @author wrydmoon
 */
public class Start {

    static String port = "4907";

    public static void main(String args[]) {

        String ip = JOptionPane.showInputDialog("Please enter server ip");
        new Start().initialize(ip, Integer.parseInt(port));

    }

    // estos parametros deben pasarsele al controlador o servicio y debe recibir el objeto socket
    public void initialize(String ip, int port) {

        try {

            // Delegar a una fabrica
            Socket sc = new Socket(ip, port);

            System.out.println("Conectando al servidor");

            // delegar a una fabrica
            Authenticate frame = new Authenticate(sc);

            // delegar a una vista
            frame.setSize(300, 80);
            frame.setLocation(500, 300);
            frame.setVisible(true);

        } catch (Exception exception) {
            System.out.println("Error al intentar realizar la conexion con el servidor");
            exception.printStackTrace();
        }

    }

}
