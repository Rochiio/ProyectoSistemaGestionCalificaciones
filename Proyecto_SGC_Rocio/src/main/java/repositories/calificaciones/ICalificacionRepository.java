package repositories.calificaciones;

import controllers.DataBaseManager;
import exceptions.CalificacionException;
import models.calificacion.Calificacion;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ICalificacionRepository<T> extends ICRUDCalificacion<T> {

    List<T> findAll(DataBaseManager db) throws SQLException;

    Optional<T> findByAlumno(int idStudent, DataBaseManager db) throws SQLException;

    List<T> findById(int idRating, DataBaseManager db) throws SQLException;

    void clearAll(DataBaseManager db) throws SQLException;
}
