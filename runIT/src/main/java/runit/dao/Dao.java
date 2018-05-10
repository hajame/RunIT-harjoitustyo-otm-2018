package runit.dao;

import java.sql.*;
import java.util.*;

/**
 * Data Access Object. Interface for accessing Database objects.
 * 
 * @param <T> Object itself
 * @param <K> Key such as id or username
 */
public interface Dao<T, K> {

    T findOne(K key) throws SQLException;

    List<T> findAll() throws SQLException;
    
    T saveOrUpdate(T object) throws SQLException;

    void delete(K key) throws SQLException;
}
