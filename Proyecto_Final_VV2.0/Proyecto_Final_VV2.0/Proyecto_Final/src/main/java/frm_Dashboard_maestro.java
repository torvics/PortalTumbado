import UI.Conexion;
import com.mysql.cj.x.protobuf.MysqlxPrepare;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class frm_Dashboard_maestro {
    private JPanel rootPanel;
    private JScrollPane SP;
    private JTable calificacionesMaestro;
    private JButton logout;
    private JButton Editar;
    private JButton btn1;
    private JButton btn2;
    public frm_Dashboard_maestro() {
        createTable();
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);

            }
        });
        Editar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    actualizarDatos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public void MostrarDatos(){
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            calificacionesMaestro.setModel(modelo);

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
                    filas[i] = rs.getObject(i+1);
                }
                modelo.addRow(filas);
            }

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public void actualizarDatos() throws SQLException {
        try {
            int fila = calificacionesMaestro.getSelectedRow();
            String nombres = calificacionesMaestro.getValueAt(fila, 0).toString();
            String calificacion = calificacionesMaestro.getValueAt(fila, 1).toString();
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            try {
                PreparedStatement actu = con.prepareStatement("UPDATE users SET calificaciones='" + calificacion + "' WHERE nombres = '" + nombres + "'");
                actu.executeUpdate();
                MostrarDatos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e + "No se logró actualizar");
            }
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null,"No hay campo a editar");
        }


    }
    private void createTable() {

    }
        public JPanel getRootPanel() {
        return rootPanel;
    }

}


