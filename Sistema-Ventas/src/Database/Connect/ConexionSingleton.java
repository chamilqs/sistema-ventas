package Database.Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConexionSingleton {
    private static ConexionSingleton instancia = null;
    private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/ventassamil";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection conexion;

    private ConexionSingleton() {
        try {
            Class.forName(CONTROLADOR);
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Manejar la excepción adecuadamente
        }
    }

    public static ConexionSingleton obtenerInstancia() {
        if (instancia == null) {
            instancia = new ConexionSingleton();
        }
        return instancia;
    }

    public Connection getConexion() {
            try {
                if (conexion == null || conexion.isClosed()) {
                    Class.forName(CONTROLADOR);
                    conexion = DriverManager.getConnection(URL, USER, PASSWORD);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return conexion;
        }

    public void desconectar() throws SQLException, ClassNotFoundException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
        }
        Class.forName(CONTROLADOR);
        conexion = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public void commit() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.commit();
        }
    }

    public void rollback() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.rollback();
        }
    }

    public void desactivarAutoCommit() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.setAutoCommit(false);
        }
    }
    
    public void activarAutoCommit() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.setAutoCommit(true);
        }
    }
    
    public void closePST(PreparedStatement preparedStatement) throws SQLException {
        if (preparedStatement != null && !preparedStatement.isClosed()) {
            preparedStatement.close();
        }
    }
    
    public void closeST(Statement statement) throws SQLException {
        if (statement != null && !statement.isClosed()) {
            statement.close();
        }
    }
    
    public void closeRS(ResultSet resultSet) throws SQLException {
        if (resultSet != null && !resultSet.isClosed()) {
            resultSet.close();
        }
    }
}
