package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Dashboard extends JPanel {
    private JPanel rootPanel;
    private JButton logout;
    private JTable calificaciones;
    private JScrollPane SP;

    public Dashboard() {
        createTable();
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             System.exit(0);

            }
        });

        try {
            DefaultTableModel modelo = new DefaultTableModel();
            calificaciones.setModel(modelo);

            PreparedStatement ps = null;
            ResultSet rs = null;
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            String cargo = "alumno";
            //String sql = "SELECT nombres, calificaciones FROM users";
            String sql = "SELECT nombres, calificaciones FROM users WHERE cargo = '"+cargo+"'";

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();

            modelo.addColumn("Nombres");
            modelo.addColumn("Calificaciones");

            while (rs.next()) {
                Object[]filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);

                }
                modelo.addRow(filas);
            }



        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    private void createTable() {
        /*
        Object[][] data = {
        };

        calificaciones.setModel(new DefaultTableModel(
                data,
                new String[]{"Nombre", "Calificaciones"}

        ));

         */

    }
}
