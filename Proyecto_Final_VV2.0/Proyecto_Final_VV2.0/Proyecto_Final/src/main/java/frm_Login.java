
import UI.Dashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class frm_Login extends JDialog {
    private JLabel lbl_Titulo;
    private JLabel lbl_nombre;
    private JLabel lbl_email;
    private JTextField txt_email;
    private JTextField txt_contraseña;
    private JLabel lbl_contraseña;
    private JButton btn_cancelar;
    private JButton btn_login;
    private JPanel panel_login;
    private JButton registrarteButton;

    public frm_Login(JFrame parent){
        super(parent);
        setTitle("Login");
        setContentPane(panel_login);
        setMinimumSize(new Dimension(600,400));
        setModal(true);
        setLocationRelativeTo(parent);
        // setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        btn_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txt_email.getText();
                // String contraseña = String.valueOf(getPassword);
                String contraseña = txt_contraseña.getText();

                final String DB_URL = "jdbc:mysql://localhost/desarrollo?serverTimezone=UTC";
                final String USERNAME = "root";
                final String PASSWORD = "";

                String sql = "SELECT email, contraseña, cargo FROM users WHERE email = '" + email + "'";

                try {
                    Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                    PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        String em = resultSet.getString("email");
                        String co = resultSet.getString("contraseña");
                        String ca = resultSet.getString("cargo");

                        if (contraseña.equals(co)) {

                            if (ca.equals("alumno")) {
                                dispose();
                                Main ea = new Main();
                                ea.createGUI();
                            } else if (ca.equals("maestro")) {
                                dispose();
                                MainMaestro maestro = new MainMaestro();
                                maestro.createGUIMaestro();
                            } else if (ca.equals("admin")) {
                                dispose();
                                MainAdmin admin = new MainAdmin();
                                admin.createGUIAdmin();
                            } else {
                                JOptionPane.showMessageDialog(null, "la contraseña ta mal");
                            }


                        } else {
                            JOptionPane.showMessageDialog(null, "el usuario no se encuentra en la base de datos");
                        }
                    }

                    } catch(SQLException ex){
                        System.out.println(ex.toString());
                    }



                /*
              user = Autenticacion(email,contraseña);
                if (user != null){
                    dispose();
                    Main ea = new Main();
                    ea.createGUI();
                }
                else{
                    JOptionPane.showMessageDialog(frm_Login.this  ,
                            "Correo o contraseña incorrecctos",
                            "intenta de nuevo",
                            JOptionPane.ERROR_MESSAGE);
                }

                 */

              /*
                if(email.isEmpty() || contraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Completa todos los campos.",
                            "Intente de nuevo",
                            JOptionPane.ERROR_MESSAGE);
                    return;

                }else{
                    dispose();
                    Main ea = new Main();
                    ea.createGUI();
                }

               */


            }
        });
        registrarteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new frm_Registro();
            }
        });
        btn_cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            dispose();

            }
        });
        setVisible(true);
    }

   public static User user;
    /*
    private User Autenticacion(String email, String contraseña) {
        User user = null;
        final String DB_URL = "jdbc:mysql://localhost/desarrollo?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try{
            Connection conn = DriverManager.getConnection (DB_URL, USERNAME, PASSWORD);
            // Conectado a la base de datos con éxito...

            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE email=? AND contraseña=?";

            PreparedStatement preparedStatement = conn.prepareStatement (sql);
            preparedStatement.setString( 1, email);
            preparedStatement.setString(2 , contraseña);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new User();
                user.nombres = resultSet.getString("nombres");
                user.apellidos = resultSet.getString("apellidos");
                user.telefono = resultSet.getString("telefono");
                user.email = resultSet.getString("email");
                user.contraseña = resultSet.getString("contraseña");

            }
            stmt.close();
            conn.close();

        }catch(Exception e) {
                  e.printStackTrace();
                 }

        return user;
    }

     */
    public frm_Login(){

    }
/*
    public static void main(String[] args) {
        frm_Login miLogin = new frm_Login(null);
        User user = frm_Login.user;

        if(user!=null){
            Dashboard dashboard = new Dashboard();
            System.out.println("eaaa");

        }else {
            System.out.println("Autenticacion cancelada");
        }

    }

 */


}
