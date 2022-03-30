package repositories;

import exceptions.evaluacionException;

public interface ICRUDEvaluacion<T> {

    //Devuelve todas la evaluaciones
    T findAll();

    //crear una evaluación
    T save (T item);

    //Eliminar una evaluación
    T delete(T item) throws evaluacionException;


}
