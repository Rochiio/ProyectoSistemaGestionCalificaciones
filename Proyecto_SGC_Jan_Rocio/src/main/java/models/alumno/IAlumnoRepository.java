package models.alumno;

import java.util.List;

public interface IAlumnoRepository<T> extends ICrudAlumno<T> {
    public T findById(int id);
    public List<T> findAll();
    public T findByDni(String dni);

}
