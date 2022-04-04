package repositories.alumno;

import controllers.DataBaseManager;
import models.alumno.Alumno;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IAlumnoRepository<T> extends ICRUAlumno<T> {
     List<Alumno> findAll(DataBaseManager db) throws SQLException;
     Optional<T> findByDni(String dni,DataBaseManager db) throws SQLException;
     int hasEvaluationTest(int id,DataBaseManager db) throws SQLException;
}
