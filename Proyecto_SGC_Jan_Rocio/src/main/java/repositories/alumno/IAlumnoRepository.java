package repositories.alumno;

import controllers.DataBaseManager;
import models.alumno.Alumno;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IAlumnoRepository<T> extends ICRUAlumno<T> {
     Optional<T> findById(int id, DataBaseManager db) throws SQLException;
     List<Alumno> findAll(DataBaseManager db) throws SQLException;
     T findByDni(String dni);
     int hasEvaluationTest(int id);
}
