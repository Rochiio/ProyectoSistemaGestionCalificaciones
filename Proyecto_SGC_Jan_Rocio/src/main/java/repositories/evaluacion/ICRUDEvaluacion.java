package repositories.evaluacion;

import exceptions.EvaluacionException;

public interface ICRUDEvaluacion<T> {
    T create(T item);

    T delete(T id) throws EvaluacionException;


}

