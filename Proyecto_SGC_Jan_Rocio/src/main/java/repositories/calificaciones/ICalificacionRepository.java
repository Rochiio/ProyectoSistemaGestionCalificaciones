package repositories.calificaciones;

import exceptions.CalificacionException;
import models.calificacion.Calificacion;

import java.util.List;

public interface ICalificacionRepository<T> extends ICRUDCalificacion<T> {
    List<T> findAll();
    T findByAlumno(T student);
    T findById(int id);
    T update(Integer id, Calificacion newCalificacion) throws CalificacionException;
    String toMarkdown();
}
