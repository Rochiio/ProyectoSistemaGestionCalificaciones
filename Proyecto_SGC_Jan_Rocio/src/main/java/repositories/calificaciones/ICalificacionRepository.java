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

    String toMarkdown(DataBaseManager db) throws SQLException;

    double getMax(int idRatings, DataBaseManager db) throws SQLException;

    double getAverage(int idRatings, DataBaseManager db) throws SQLException;

    double getMin(int idRatings, DataBaseManager db) throws SQLException;

    double getPass(int idRatings, DataBaseManager db) throws SQLException;

    double getFail(int idRatings, DataBaseManager db) throws SQLException;



}
