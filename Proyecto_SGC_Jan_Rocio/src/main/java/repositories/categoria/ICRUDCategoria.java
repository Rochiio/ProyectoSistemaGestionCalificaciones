package repositories.categoria;

import controllers.DataBaseManager;

import java.sql.SQLException;
import java.util.Optional;

public interface ICRUDCategoria<T>{
    T save(T item, DataBaseManager db) throws SQLException;

    Optional<T> update(Integer id, String item, DataBaseManager db) throws SQLException;



}
