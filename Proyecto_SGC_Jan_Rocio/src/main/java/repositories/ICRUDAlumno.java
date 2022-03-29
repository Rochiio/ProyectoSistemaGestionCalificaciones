package repositories;

public interface ICRUDAlumno<T> {
    //Devuelve todos los alumnos.
    T findAlumnos();

    //Devuelve un alumno.
    T find();
}
