package repositories.alumno;

public interface ICRUAlumno<T> {
    T create(T value);
    T delete(int id);
    T update(int id, T value);
    T readAlumno(int id);
}
