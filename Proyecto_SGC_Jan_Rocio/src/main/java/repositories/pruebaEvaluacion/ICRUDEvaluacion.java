package repositories.pruebaEvaluacion;

public interface ICRUDEvaluacion<T> {
    T create(T item);

    T delete(T id);


}

