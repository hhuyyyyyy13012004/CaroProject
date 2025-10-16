/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.UserManager;
import Model.User;
import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket socket;
    private final UserManager userManager;
    private BufferedReader in;
    private PrintWriter out;

    public ClientHandler(Socket socket, UserManager userManager) {
        this.socket = socket;
        this.userManager = userManager;
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Welcome to Caro Server!");
            String line;
            while ((line = in.readLine()) != null) {
                handleMessage(line);
            }
        } catch (IOException e) {
            System.out.println("[ClientHandler] Connection closed: " + socket.getInetAddress());
        } finally {
            try {
                socket.close();
            } catch (IOException ignored) {}
        }
    }

    private void handleMessage(String msg) {
        // Ví dụ xử lý tạm thời
        System.out.println("[Server] Received: " + msg);
        out.println("Echo: " + msg);

        // Sau này sẽ thêm lệnh như: LOGIN username password | REGISTER user pass | PLAY ...
    }
}

