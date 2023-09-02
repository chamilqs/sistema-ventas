/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author baez_
 */
public interface DAO<T> {
    
    T get(String id) throws SQLException;
    
    List<T> getAll() throws SQLException;
        
    int insert(T t) throws SQLException;
    
    int update(T t) throws SQLException;
    
    int delete(T t) throws SQLException;
    
    int delete(String id) throws SQLException;

    
}
