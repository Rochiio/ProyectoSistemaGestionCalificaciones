package repositories.alumno;

import controllers.DataBaseManager;
import exceptions.AlumnoException;

import java.sql.SQLException;
import java.util.Optional;

public interface ICRUAlumno<T> {
    Optional<T> findById(int id, DataBaseManager db) throws SQLException;
    T create(T value,DataBaseManager db) throws SQLException;
    Optional<T> delete(Optional<T> value,DataBaseManager db) throws SQLException;
    Optional<T> update(int id, T value,DataBaseManager db) throws AlumnoException, SQLException;
}
