/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.UserManager;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    private static final int PORT = 5000; // cổng server
    private ServerSocket serverSocket;
    private final UserManager userManager;

    public ServerMain() {
        userManager = new UserManager();
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("[Server] Started on port " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[Server] New client connected: " + clientSocket.getInetAddress());
                new Thread(new ClientHandler(clientSocket, userManager)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerMain server = new ServerMain();
        server.startServer();
    }
}

