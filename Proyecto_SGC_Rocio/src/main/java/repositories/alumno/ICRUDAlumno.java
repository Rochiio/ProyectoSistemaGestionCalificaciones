package repositories.alumno;

import controllers.DataBaseManager;

import java.sql.SQLException;
import java.util.Optional;

public interface ICRUDAlumno<T>{
    Optional<T> findById(int id, DataBaseManager db) throws SQLException;
    T create(T value,DataBaseManager db) throws SQLException;
    T delete(T value,DataBaseManager db) throws SQLException;
    Optional<T> update(int id, T value,DataBaseManager db) throws SQLException;
}
