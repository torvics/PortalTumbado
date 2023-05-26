import javax.swing.*;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

    public class frm_Registro extends JDialog{
        private JLabel lbl_Titulo;
        private JLabel lbl_nombre;
        private JTextField txt_nombre;
        private JTextField txt_apellidos;
        private JButton btn_registrar;
        private JButton btn_cancelar;
        private JLabel lbl_apellidos;
        private JLabel lbl_telefono;
        private JLabel lbl_email;
        private JLabel lbl_contraseña;
        private JTextField txt_telefono;
        private JTextField txt_email;
        private JTextField txt_contraseña;
        private JPanel panel_registro;
        private JButton atrásButton;

        public  frm_Registro (JFrame parent){
            super(parent);
            setTitle("Create a new account");
            setContentPane(panel_registro);
            setMinimumSize(new Dimension(650,450));
            setModal(true);
            setLocationRelativeTo(parent);
           // setVisible(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);

          /* btn_registrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

           */



            btn_registrar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    btn_registrar();
                }
            });

            atrásButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    frm_Login washas = new frm_Login(null);
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

        public frm_Registro() {
            frm_Registro miFormulario = new frm_Registro(null);
            User user = miFormulario.user;

            if (user != null) {
            frm_Login washas = new frm_Login(null);
            }
            else{
                System.out.println("Registro cancelado");


            }
        }

        private void btn_registrar() {
            String nombres = txt_nombre.getText();
            String apellidos= txt_apellidos.getText();
            String telefono = txt_telefono.getText();
            String email = txt_email.getText();
            // String contraseña = String.valueOf(getPassword);
            String contraseña = txt_contraseña.getText();

           /* boolean sexom = rbt_masculino.isSelected();
            boolean sexof= rbt_fememino.getAutoscrolls();
            boolean cargom = rbt_maestro.isSelected();
            boolean cargoa= rbt_alumno.isSelected();
                |
            */


            if (nombres.isEmpty() || apellidos.isEmpty() || telefono.isEmpty() || email.isEmpty() ||  contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Porfavor ingrese todos los campos",
                        "intente de nuevo",
                        JOptionPane.ERROR_MESSAGE);
                return;
        }
            //Metodo que agrega usuarios a la ase d edatos
           user = addUserToDatabase (nombres,apellidos,telefono,email,contraseña);//a, sexom, sexof, cargom, cargoa
            if (user!= null){
                dispose();
            }
            else{
                JOptionPane.showMessageDialog(this,
                        "Registro fallido",
                        "intenta de nuevo",
                        JOptionPane.ERROR_MESSAGE);
            }
    }

        public User user;
        private User addUserToDatabase(String nombres, String apellidos, String telefono, String email, String contraseña) {
            User user = null; //fue utilizada la clase ---User--
            final String DB_URL = "jdbc:mysql://localhost/desarrollo?serverTimezone=UTC";
            final String USERNAME = "root";
            final String PASSWORD = "";

            try{
                Connection conn = DriverManager.getConnection (DB_URL, USERNAME, PASSWORD);
                // Conectado a la base de datos con éxito...

                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO users (nombres, apellidos, telefono, email, contraseña) " + "VALUES (?, ?, ?, ?, ?)";

                PreparedStatement preparedStatement = conn.prepareStatement (sql);
                preparedStatement.setString(1, nombres);
                preparedStatement.setString( 2, apellidos);
                preparedStatement.setString(3, telefono);
                preparedStatement.setString( 4, email);
                preparedStatement.setString( 5, contraseña);

               /* preparedStatement.setString( 6, String.valueOf(sexom));
                preparedStatement.setString( 7, String.valueOf(sexof));
                preparedStatement.setString( 8, String.valueOf(cargom));
                preparedStatement.setString( 9, String.valueOf(cargoa));

                */



                //Insertar fila en la tabla
                int addFila = preparedStatement.executeUpdate();
                if (addFila > 0) {

                    user = new User();
                    user.nombres = nombres;
                    user.apellidos = apellidos;
                    user.telefono = telefono;
                    user.email = email;

                    /*user.sexom = sexom;
                    user.sexof= sexof;
                    user.cargom= cargom;
                    user.cargoa= cargoa;

                     */
                }
                stmt.close();
                conn.close();

            }catch(Exception e) {
                e.printStackTrace();
            }



            return user;
        }


    }


