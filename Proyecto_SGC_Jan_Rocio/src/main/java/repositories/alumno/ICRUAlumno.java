package repositories.alumno;

import exceptions.AlumnoException;

public interface ICRUAlumno<T> {
    T create(T value);
    T delete(int id);
    T update(int id, T value) throws AlumnoException;
    T readAlumno(int id);
}
