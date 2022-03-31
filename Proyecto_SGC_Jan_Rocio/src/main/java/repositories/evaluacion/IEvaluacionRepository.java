package repositories.evaluacion;

import java.util.List;

public interface IEvaluacionRepository<T> extends ICRUDEvaluacion<T> {
    //Devuelve todas las evaluaciones
    List<T> findAll();
}
