/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.caroclient;
import java.net.*;
import java.io.*;
import java.util.Scanner;
/**
 *
 * @author doloc
 */
public class CaroClient {

    public static void main(String[] args) throws IOException {
        String dataFromServer;

        // Create socket from client
        Socket mySocket = new Socket("192.168.1.3", 8080);

        // Create Object to send/ receive data
        BufferedReader br = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(mySocket.getOutputStream());

        while(true)
        {
            System.out.print("Input a string: ");
            Scanner sc = new Scanner(System.in);
            String data = sc.nextLine();
            printWriter.println(data);
            printWriter.flush();
            if(data.equals("X"))
            {
                break;
            }

            // Receive data from server
            dataFromServer = br.readLine();
            System.out.println("Data of the server return for client: " + dataFromServer);
            if(dataFromServer.equals("X"))
            {
                break;
            }
        }

        mySocket.close();
    }
}
