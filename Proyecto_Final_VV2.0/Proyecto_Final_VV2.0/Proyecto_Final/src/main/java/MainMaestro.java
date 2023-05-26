
import UI.Dashboard;

import javax.swing.*;
public class MainMaestro {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUIMaestro();
                frm_Dashboard_maestro ea = new frm_Dashboard_maestro();
                ea.MostrarDatos();
            }
        });
    }
    public static void createGUIMaestro(){
        frm_Dashboard_maestro maestro = new frm_Dashboard_maestro();
        maestro.MostrarDatos();
        frm_Login login = new frm_Login();

        JPanel root = maestro.getRootPanel();
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(700,570);
    }
}