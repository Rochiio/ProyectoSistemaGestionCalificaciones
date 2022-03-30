package repositories.alumno;

import models.alumno.Alumno;

import java.util.List;

public interface IAlumnoRepository<T> extends ICRUAlumno<T> {
     T findById(int id);
     List<Alumno> findAll();
     T findByDni(String dni);
     int hasEvaluationTest(int id);
}
