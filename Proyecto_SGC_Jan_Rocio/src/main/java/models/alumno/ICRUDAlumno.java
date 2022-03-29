package models.alumno;

public interface ICrudAlumno<T> {
    T create(T value);
    T delete(int id);
    T update(int id, T value);
    T readAlumno(int id);
}
