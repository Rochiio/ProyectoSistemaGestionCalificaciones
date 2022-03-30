package repositories.evaluacion;

import exceptions.evaluacionException;

public interface ICRUDEvaluacionRepository<T> extends ICRUDEvaluacion<T> {
    //Devuelve todas las evaluaciones
    T findAll();

    //Crea evaluaciones
    T create(T item);

    //Elimina evaluaciones
    T delete(T id) throws evaluacionException;

}
