/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.caroserver;
import java.net.*;
import java.io.*;
/**
 *
 * @author doloc
 */
public class CaroServer {

    public static void main(String[] args) throws IOException{
        String dataFromClient;
        String dataFinal;
        // Create seversocket from server
        ServerSocket myServerSocket = new ServerSocket(8080);
        Socket mySocket = myServerSocket.accept();
        // Create Object for send/receive the data
        BufferedReader br =  new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        PrintWriter pw = new PrintWriter(mySocket.getOutputStream());
        // Receive data from Client
        dataFromClient = br.readLine();
        // Process data
        dataFinal = dataFromClient.toUpperCase();

        // send data back to client
        pw.println(dataFinal);
        pw.flush();

    }
}
