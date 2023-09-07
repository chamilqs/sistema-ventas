/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAOImpl;

import Connect.Conexion;
import DAO.ProductoDAO;
import DTO.Producto;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author baez_
 */
public class ProductoDAOImpl implements ProductoDAO{

    @Override
    public Producto get(String id) throws SQLException {
        Connection c = Conexion.conectar();
        Producto p = null;
        
        String query1 = "select * from Producto where id = ?";
        
        PreparedStatement st = c.prepareStatement(query1);
        st.setString(1, id);
        
        ResultSet rs = st.executeQuery();
        
        if(rs.next())
        {
            String oid = rs.getString("id");
            String nombre = rs.getString("descripcion");
            float precio = rs.getFloat("precioU");
            String size = rs.getString("size");
            String prioridad = rs.getString("prioridad");
            
            p = new Producto(oid, nombre, precio, size,prioridad);
        }
        
        
        
        
        return p;    
    }

    @Override
    public List<Producto> getAll() throws SQLException {
    Connection c = Conexion.conectar();
        List<Producto> lp = null;
        
        String query1 = "select * from Producto";
        
        PreparedStatement st = c.prepareStatement(query1);        
        ResultSet rs = st.executeQuery();
        
        while(rs.next())
        {
            String oid = rs.getString("id");
            String nombre = rs.getString("nombre");
            float precio = rs.getFloat("precio");
            String size = rs.getString("size");
            String prioridad = rs.getString("prioridad");
            List<String> idProveedores = new ArrayList<>();
            
            String query2 = "select idProveedor from DetalleProducto where idProducto = ?";
        
          PreparedStatement st2 = c.prepareStatement(query2);
          st.setString(1, rs.getString(oid));
        
         ResultSet rs2 = st2.executeQuery();
        
          while(rs2.next())
             {
                 idProveedores.add(rs2.getString("idProveedor"));
            
             }
            
            Producto p = new Producto(oid, nombre, precio, size,prioridad, idProveedores);
            
            lp.add(p);
        }
        
        return lp;    
    }

    @Override
    public int insert(Producto p) throws SQLException {
        Connection c = Conexion.conectar();
        
        String query1 = "insert into Producto(id,descripcion,precioU,size,prioridad) values(?,?,?,?,?)";
        
        PreparedStatement pst = c.prepareStatement(query1);
        pst.setString(1, p.getId());
        pst.setString(2, p.getNombre());
        pst.setFloat(3, p.getPrecio());
        pst.setString(4, p.getSize());
        pst.setString(5, p.getPrioridad());

        int retult = pst.executeUpdate();
        
        Conexion.desconectar(c);
        Conexion.desconectarStm(pst);
        
        return retult;
    }

    @Override
    public int update(Producto p) throws SQLException {
        Connection c = Conexion.conectar();
        
        String query1 = "update Producto set id = ?, descripcion = ?, precioU = ?, size = ?, prioridad = ? where id = ?";
        
        PreparedStatement pst = c.prepareStatement(query1);
        pst.setString(1, p.getId());
        pst.setString(2, p.getNombre());
        pst.setFloat(3, p.getPrecio());
        pst.setString(4, p.getSize());
        pst.setString(5, p.getPrioridad());
        pst.setString(6, p.getId());

        int retult = pst.executeUpdate();
        
        Conexion.desconectar(c);
        Conexion.desconectarStm(pst);
        
        return retult;    }

    @Override
    public int delete(Producto p) throws SQLException {
        Connection c = Conexion.conectar();
        c.setAutoCommit(false);
        String query1 = "delete from Producto where id = ?";
        PreparedStatement pst = c.prepareStatement(query1);
        
        String query2 = "delete from detalleProducto where idProducto = ?";
        PreparedStatement pst2 = c.prepareStatement(query2);
        
        String query3 = "delete from inventario where idProducto = ?";
        PreparedStatement pst3 = c.prepareStatement(query3);
        
        int result = 0,result2 = 0, result3 = 0;
        try{
            
            pst3.setString(1, p.getId());

            result3 = pst3.executeUpdate();
            
            pst2.setString(1, p.getId());

            result2 = pst2.executeUpdate();

            pst.setString(1, p.getId());

            result = pst.executeUpdate();

            Conexion.desconectarStm(pst);
            c.commit(); 
            Conexion.desconectar(c);
            return result;  

        }catch(Exception e)
        {
            System.err.println(e);
            Conexion.desconectarStm(pst);
            c.rollback();
            Conexion.desconectar(c);
        }
        
        
        return result;
    }
    
    @Override
    public int delete(String id) throws SQLException {
        Connection c = Conexion.conectar();
        c.setAutoCommit(false);
        String query1 = "delete from Producto where id = ?";
        PreparedStatement pst = c.prepareStatement(query1);
        
        String query2 = "delete from detalleProducto where idProducto = ?";
        PreparedStatement pst2 = c.prepareStatement(query2);
        
        String query3 = "delete from inventario where idProducto = ?";
        PreparedStatement pst3 = c.prepareStatement(query3);
        
        int result = 0,result2 = 0, result3 = 0;
        try{
            
            pst3.setString(1, id);

            result3 = pst3.executeUpdate();
                       
            pst2.setString(1, id);

            result2 = pst2.executeUpdate();
                        
            pst.setString(1, id);

            result = pst.executeUpdate();

            Conexion.desconectarStm(pst);
            c.commit(); 
            
            Conexion.desconectar(c);
            return result;  

        }catch(Exception e)
        {
            System.err.println(e);
            Conexion.desconectarStm(pst);
            c.rollback();

            Conexion.desconectar(c);
        }
        
        
        return result;
    }


    
}

