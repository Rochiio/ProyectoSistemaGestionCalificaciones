package models.evaluacion;

import java.util.List;

public interface ICRUDEvaluacion<T> {
    List<T> findAll();

    T save (T item);

    T delete(T item);


}
