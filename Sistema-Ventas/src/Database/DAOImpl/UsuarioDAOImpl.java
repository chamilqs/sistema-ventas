package Database.DAOImpl;

import Database.Connect.Conexion;
import Database.Connect.ConexionSingleton;
import Database.DAO.UsuarioDAO;
import Database.DTO.Usuario;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class UsuarioDAOImpl implements UsuarioDAO {

    ConexionSingleton c = ConexionSingleton.obtenerInstancia();
    
    @Override
    public Usuario get(String id) throws SQLException {
        try (Connection cn = c.getConexion()) {
            Usuario u = null;
            String query = "select * from Usuario where id = ?";
            PreparedStatement st = cn.prepareStatement(query);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                String oid = rs.getString("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                String password = rs.getString("password");
                String nivelAcceso = rs.getString("nivelAcceso");
                u = new Usuario(oid, nombre, correo, password, nivelAcceso);
            }
            return u;
        }
    }

    @Override
    public List<Usuario> getAll() throws SQLException {
        try (Connection cn = c.getConexion()) {
            List<Usuario> lu = new ArrayList<>();
            String query = "select * from Usuario";
            PreparedStatement st = cn.prepareStatement(query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String oid = rs.getString("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                String password = rs.getString("password");
                String nivelAcceso = rs.getString("nivelAcceso");
                Usuario u = new Usuario(oid, nombre, correo, password, nivelAcceso);
                lu.add(u);
            }
            return lu;
        }
    }

    @Override
    public int insert(Usuario u) throws SQLException, ClassNotFoundException {
        try (Connection cn = c.getConexion()) {
            String query = "insert into Usuario(id,nombre,correo,password,nivelAcceso) values(?,?,?,?,?)";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, u.getId());
            pst.setString(2, u.getNombre());
            pst.setString(3, u.getCorreo());
            pst.setString(4, u.getPassword());
            pst.setString(5, u.getNivelAcceso());
            int result = pst.executeUpdate();
            c.desconectar();
            return result;
        }
    }

    @Override
    public int update(Usuario u) throws SQLException, ClassNotFoundException {
        try (Connection cn = c.getConexion()) {
            String query = "update Usuario set id = ?, nombre = ?, correo = ?, password = ?, nivelAcceso = ? where id = ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, u.getId());
            pst.setString(2, u.getNombre());
            pst.setString(3, u.getCorreo());
            pst.setString(4, u.getPassword());
            pst.setString(5, u.getNivelAcceso());
            pst.setString(6, u.getId());
            int result = pst.executeUpdate();
            c.desconectar();
            return result;
        }
    }

    @Override
    public int delete(Usuario u) throws SQLException, ClassNotFoundException {
        try (Connection cn = c.getConexion()) {
            String query = "delete from Usuario where id = ?";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, u.getId());
            int result = pst.executeUpdate();
            c.desconectar();
            return result;
        }
    }

    @Override
    public int actualizarLogin(Usuario u) throws SQLException, ClassNotFoundException {
        try (Connection cn = c.getConexion()) {
            String query = "insert into Historial(idUsuario, login) values(?,?);";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, u.getId());
            pst.setTimestamp(2, Timestamp.valueOf(u.getLogin()));
            int result = pst.executeUpdate();
            c.desconectar();
            return result;
        }  
    }

    @Override
    public int actualizarLogout(Usuario u) throws SQLException {
        try (Connection cn = c.getConexion()) {
            String query = "update historial set logout = ? where idUsuario = ? and logout IS null;";
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setTimestamp(1, Timestamp.valueOf(u.getLogout()));
            pst.setString(2, u.getId());
            int result = pst.executeUpdate();
            c.closePST(pst);
            return result;
        }    }

    @Override
    public int delete(String id) throws SQLException {
    try (Connection cn = c.getConexion()) {
                String query = "delete from Usuario where id = ?";
                PreparedStatement pst = cn.prepareStatement(query);
                pst.setString(1, id);
                int result = pst.executeUpdate();
                c.closePST(pst);
                return result;
                
            } 
    } 

}
