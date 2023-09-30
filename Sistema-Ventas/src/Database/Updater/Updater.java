/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.Updater;

import Database.Connect.Conexion;
import Database.Connect.ConexionSingleton;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author baez_
 */
public class Updater {

    static ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    public Updater() {
    }
    
    public static void actualizarTabla(String tabla,DefaultTableModel modelo) throws SQLException
    {
        
        try(Connection cn = c.getConexion()){
            //Agregar Columnas
            
            Statement stm = cn.createStatement();
            ResultSet rs = cn.getMetaData().getColumns(null, null, tabla, null);
            modelo.setColumnCount(0);
            modelo.setRowCount(0);
            
            while (rs.next()) {
                modelo.addColumn(rs.getString("COLUMN_NAME"));
            }
            
            //Agregar filas
            ResultSet rsFilas = stm.executeQuery("Select * from " + tabla);
            
            while(rsFilas.next())
            {
                Object[] fila = new Object[modelo.getColumnCount()];
                for(int i = 0; i < modelo.getColumnCount(); i++)
                {
                    fila[i] = rsFilas.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            
            cn.close();
            stm.close();
            rs.close();
            rsFilas.close(); 
        }catch(SQLException e1) 
        {
	// TODO Auto-generated catch block
            e1.printStackTrace();
	}
        //JAJ
    }
    
    
}
