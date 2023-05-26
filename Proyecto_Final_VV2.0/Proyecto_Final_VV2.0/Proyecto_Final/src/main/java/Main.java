import UI.Dashboard;

import javax.swing.*;
public class Main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createGUI();
            }
        });
    }
    public static void createGUI(){
        Dashboard dashboard = new Dashboard();
        frm_Login login = new frm_Login();


       JPanel root = dashboard.getRootPanel();
        JFrame frame = new JFrame();



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setSize(700,550);
    }
}
