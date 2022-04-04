package repositories.categoria;

import controllers.DataBaseManager;

import java.sql.SQLException;

public interface ICRUDCategoria<T>{
    T save(T item, DataBaseManager db) throws SQLException;

    T update(Integer id, String item) throws SQLException;



}
