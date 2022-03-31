package repositories.calificaciones;

public interface ICRUDCalificacion<T> {
    T create(T item);
    T delete(T item);


}
