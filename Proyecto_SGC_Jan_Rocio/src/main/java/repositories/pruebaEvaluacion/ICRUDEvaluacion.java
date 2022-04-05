package repositories.pruebaEvaluacion;

import controllers.DataBaseManager;
import models.pruebaEvaluacion.PruebasEvaluacion;
import repositories.calificaciones.CalificacionRepository;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Optional;

public interface ICRUDEvaluacion<T> {
    T save(T evaluationTest, DataBaseManager db) throws SQLException, ParseException;
    T delete(T id, DataBaseManager db) throws SQLException;
    Optional<T> findById(int id, DataBaseManager db) throws SQLException;
    Optional<T> updateRatings(T evaluationTest,CalificacionRepository ratingRepository, DataBaseManager db) throws SQLException;
}

