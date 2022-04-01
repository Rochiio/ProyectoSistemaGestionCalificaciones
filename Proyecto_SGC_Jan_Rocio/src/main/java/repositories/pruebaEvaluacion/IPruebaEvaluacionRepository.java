package repositories.pruebaEvaluacion;

import models.pruebaEvaluacion.PruebasEvaluacion;

import java.util.List;

public interface IPruebaEvaluacionRepository<T> extends ICRUDEvaluacion<T> {

    T save(PruebasEvaluacion eva);
    //Devuelve todas las evaluaciones
    List<T> findAll();
    T findById(int id);
}
