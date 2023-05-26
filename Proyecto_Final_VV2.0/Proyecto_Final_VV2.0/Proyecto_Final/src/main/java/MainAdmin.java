import admin.frm_Dashboard_admin;

import javax.swing.*;
import java.sql.SQLException;

public class MainAdmin {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    createGUIAdmin();
                    frm_Dashboard_admin ea = new frm_Dashboard_admin();
                    ea.MostrarDatos();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public static void createGUIAdmin() throws SQLException {
        frm_Dashboard_admin admin = new frm_Dashboard_admin();
        admin.MostrarDatos();
        frm_Login login = new frm_Login();

        JPanel root = admin.getRootPanel();
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(700,570);
    }
}
