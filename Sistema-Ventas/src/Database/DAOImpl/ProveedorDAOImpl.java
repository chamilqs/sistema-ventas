/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database.DAOImpl;

import Database.Connect.Conexion;
import Database.Connect.ConexionSingleton;
import Database.DAO.ProveedorDAO;
import Database.DTO.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Christian
 */
public class ProveedorDAOImpl implements ProveedorDAO{

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    @Override
    public Proveedor get(String id) throws SQLException {
        Connection cn = c.getConexion();
        Proveedor pr = null;
        
        String query1 = "select * from Proveedor where id = ?";
        
        PreparedStatement st = cn.prepareStatement(query1);
        st.setString(1, id);
        
        ResultSet rs = st.executeQuery();
        
        if(rs.next())
        {
            String oid = rs.getString("id");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            List<String> telefonos = new ArrayList<>();
            
            String query2 = "select telefono from telefonoProveedor where idProveedor = ?";
        
          PreparedStatement st2 = cn.prepareStatement(query2);
          st2.setString(1, id);
        
         ResultSet rs2 = st2.executeQuery();
        
          while(rs2.next())
             {
                 telefonos.add(rs2.getString("telefono"));
             }

            
           pr = new Proveedor(oid,nombre,correo,telefonos);
        }
        return pr;       
    }

    @Override
    public List getAll() throws SQLException {
        Connection cn = c.getConexion();
        List<Proveedor> proveedores = null;
        
        String query1 = "select * from Proveedor?";
        PreparedStatement st = cn.prepareStatement(query1);
        ResultSet rs = st.executeQuery();
        
        while(rs.next())
        {
            String oid = rs.getString("id");
            String nombre = rs.getString("nombre");
            String correo = rs.getString("correo");
            List<String> telefonos = new ArrayList<>();
            
            String query2 = "select telefono from DetalleProveedor where idProveedor = ?";
        
          PreparedStatement st2 = cn.prepareStatement(query2);
          st.setString(1, oid);
        
         ResultSet rs2 = st2.executeQuery();
        
          while(rs2.next())
             {
                 telefonos.add(rs2.getString("telefono"));
             }

           Proveedor pr = new Proveedor(oid,nombre,correo,telefonos);
           
           proveedores.add(pr);
        }
        return proveedores; 
    }

    @Override
    public int insert(Proveedor pr) throws SQLException, ClassNotFoundException {
        Connection cn = c.getConexion();
        
        String query1 = "insert into Proveedor(id,nombre,correo) values(?,?,?)";
        
        PreparedStatement pst = cn.prepareStatement(query1);
        pst.setString(1, pr.getId());
        pst.setString(2, pr.getNombre());
        pst.setString(3, pr.getCorreo());
        int retult = pst.executeUpdate();

        if(!pr.getTelefonos().isEmpty())
        {
           for(int i = 0; i < pr.getTelefonos().size(); i++)
           {
              String query2 = "insert into telefonoProveedor(idProveedor,telefono) values(?,?)";
              PreparedStatement pst2 = cn.prepareStatement(query2);
              pst2.setString(1, pr.getId());
              pst2.setString(2, pr.getTelefonos().get(i));

             int retult2 = pst2.executeUpdate();
           }
        }
       
        c.closePST(pst);
        c.desconectar();
        
        return retult;
    }

    @Override
    public int update(Proveedor pr) throws SQLException, ClassNotFoundException {
        Connection cn = c.getConexion();
        
        String query1 = "update Proveedor set id = ?, nombre = ?, correo = ? where id = ?";
        
        PreparedStatement pst = cn.prepareStatement(query1);
        pst.setString(1, pr.getId());
        pst.setString(2, pr.getNombre());
        pst.setString(3, pr.getCorreo());
        pst.setString(4, pr.getId());
        

        int retult = pst.executeUpdate();
        
        c.closePST(pst);
        c.desconectar();
        
        return retult;
    }

    @Override
    public int delete(Proveedor pr) throws SQLException, ClassNotFoundException {
        Connection cn = c.getConexion();
        
        String query1 = "delete from Proveedor where id = ?";
        String query2 = "delete from telefonoProveedor where id = ?";
        
        PreparedStatement pst = cn.prepareStatement(query1);
        pst.setString(1, pr.getId());
        
        PreparedStatement pst2 = cn.prepareStatement(query2);
        pst2.setString(1, pr.getId());
        
        int retult2 = pst2.executeUpdate();
        int retult = pst.executeUpdate();
        
        c.closePST(pst2);
        c.desconectar();
        
        return retult;      }

    @Override
    public int delete(String id) throws SQLException, ClassNotFoundException {
        Connection cn = c.getConexion();
        
        String query1 = "delete from Proveedor where id = ?";
        String query2 = "delete from telefonoProveedor where idProveedor = ?";
                
        PreparedStatement pst = cn.prepareStatement(query1);
        pst.setString(1, id);
        
        PreparedStatement pst2 = cn.prepareStatement(query2);
        pst2.setString(1, id);
        
        int retult2 = pst2.executeUpdate();
        int retult = pst.executeUpdate();    
        
        c.closePST(pst2);
        c.desconectar();
        
        return retult;          }
    
    
    public void updateTelefonos(Proveedor pr) throws SQLException
    {
        Connection cn = c.getConexion();
        List<Integer> idTelefonos = new ArrayList<Integer>();
        
        PreparedStatement pst1 = cn.prepareStatement("Select id from telefonoProveedor where idProveedor = ?");
        pst1.setString(1, pr.getId());
        
        ResultSet rs = pst1.executeQuery();
        
        while(rs.next())
        {
            idTelefonos.add(rs.getInt("id"));
        }
        
        if(idTelefonos.size() == 1)
        {
          String query2 = "update telefonoProveedor set telefono = ? where idProveedor = ? and id = ?";
          PreparedStatement pst2 = cn.prepareStatement(query2);
          pst2.setString(1, pr.getTelefonos().get(0));
          pst2.setString(2, pr.getId());
          pst2.setInt(3, idTelefonos.get(0));
          pst2.executeUpdate();
          
          
          if(pr.getTelefonos().size() > 1)
          {
              String query3 = "insert into telefonoProveedor(idProveedor,telefono) values(?,?)";
              PreparedStatement pst3 = cn.prepareStatement(query3);
              pst3.setString(1, pr.getId());
              pst3.setString(2, pr.getTelefonos().get(1));      
              pst3.executeUpdate();

          }

        }
        else{
            for(int i = 0; i < pr.getTelefonos().size(); i++)
             {

              String query2 = "update telefonoProveedor set telefono = ? where idProveedor = ? and id = ?";
              PreparedStatement pst2 = cn.prepareStatement(query2);
              pst2.setString(1, pr.getTelefonos().get(i));
              pst2.setString(2, pr.getId());
              pst2.setInt(3, idTelefonos.get(i));
              pst2.executeUpdate();
            }
        }
       
    }
}

    
