package View;

import Controller.ClientHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnRegister;
    private JButton btnBack;
    private ClientHandler clientHandler;

    public RegisterFrame(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
        initComponents();
    }

    private void initComponents() {
        setTitle("Register");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel lblTitle = new JLabel("CREATE ACCOUNT", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblUsername = new JLabel("Username:");
        JLabel lblPassword = new JLabel("Password:");

        txtUsername = new JTextField(20);
        txtPassword = new JPasswordField(20);

        btnRegister = new JButton("Register");
        btnBack = new JButton("Back");

        // Layout
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        panel.add(lblTitle);
        panel.add(lblUsername);
        panel.add(txtUsername);
        panel.add(lblPassword);
        panel.add(txtPassword);

        JPanel btnPanel = new JPanel();
        btnPanel.add(btnRegister);
        btnPanel.add(btnBack);

        add(panel, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.SOUTH);

        // Sự kiện nút Register
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onRegister();
            }
        });

        // Sự kiện nút Back (quay lại Login)
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame(clientHandler).setVisible(true);
            }
        });
    }

    private void onRegister() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields");
            return;
        }

        try {
            // Gửi yêu cầu đăng ký đến Server
            String request = "REGISTER|" + username + "|" + password;
            clientHandler.sendMessage(request);

            // Nhận phản hồi từ Server
            String response = clientHandler.receiveMessage();

            if (response == null) {
                JOptionPane.showMessageDialog(this, "No response from server");
                return;
            }

            if (response.equalsIgnoreCase("REGISTER_SUCCESS")) {
                JOptionPane.showMessageDialog(this, "Registration successful! You can login now.");
                dispose();
                new LoginFrame(clientHandler).setVisible(true);
            } else if (response.equalsIgnoreCase("REGISTER_FAILED")) {
                JOptionPane.showMessageDialog(this, "Username already exists or registration failed!");
            } else {
                JOptionPane.showMessageDialog(this, "Unknown response from server: " + response);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    // Chạy thử standalone (test)
    public static void main(String[] args) {
        ClientHandler clientHandler = new ClientHandler("localhost", 5000);
        SwingUtilities.invokeLater(() -> new RegisterFrame(clientHandler).setVisible(true));
    }
}
