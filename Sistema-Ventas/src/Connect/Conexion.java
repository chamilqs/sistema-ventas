/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author baez_
 */
public class Conexion {

        private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/paradafria";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	static {
		try {
			Class.forName(CONTROLADOR);

		} catch (ClassNotFoundException e)
		{
			System.out.println("Error en el driver");
			e.printStackTrace();		
		}
	}

	public static Connection conectar() throws SQLException
	{
                 Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection(URL,USER,PASSWORD);
						
		} catch (SQLException e) {
			System.out.println("Error en la conexion");
			e.printStackTrace();
                        throw e;    
		}
                
		
		return conexion;
	}
        
        public static void desconectar(Connection c) throws SQLException
        {
               if(c != null)
               {
                  if(!c.isClosed())
                       {
                          c.close();
                       }
               }
        }
        
         public static void desconectarStm(PreparedStatement p) throws SQLException
        {
               if(p != null)
               {
                  if(!p.isClosed())
                       {
                          p.close();
                       }
               }
        }
         
          public static void desconectarStm(Statement s) throws SQLException
        {
               if(s != null)
               {
                  if(!s.isClosed())
                       {
                          s.close();
                       }
               }
        }
}
