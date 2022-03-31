package repositories.pruebaEvaluacion;

import models.pruebaEvaluacion.PruebasEvaluacion;

import java.util.List;

public interface IEvaluacionRepository<T> extends ICRUDEvaluacion<T> {

    PruebasEvaluacion save(PruebasEvaluacion eva);

    //Devuelve todas las evaluaciones
    List<T> findAll();

}
