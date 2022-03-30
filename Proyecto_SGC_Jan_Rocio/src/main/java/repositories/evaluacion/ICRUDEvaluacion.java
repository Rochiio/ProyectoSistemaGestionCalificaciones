package repositories.evaluacion;

import exceptions.evaluacionException;

public interface ICRUDEvaluacion<T> {

    /**
     * Devuelve todas la evaluaciones
     * @return Todos las evaluaciones
     */
    T findAll();

    /**
     * crear una evaluación
     * @param item evaluación a crear.
     * @return evaluación creada
     */
    T save (T item);

    /**
     * Eliminar una evaluación
     * @param item
     * @return
     * @throws evaluacionException
     */
    T delete(T item) throws evaluacionException;


}
