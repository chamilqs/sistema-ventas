/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import DTO.Usuario;
import java.sql.SQLException;

/**
 *
 * @author baez_
 */
public interface UsuarioDAO extends DAO<Usuario>{
    
    int actualizarLogin(Usuario u) throws SQLException;
    int actualizarLogout(Usuario u) throws SQLException;

    
}
