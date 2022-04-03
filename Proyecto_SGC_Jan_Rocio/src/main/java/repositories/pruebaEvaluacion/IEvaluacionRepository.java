package repositories.pruebaEvaluacion;

import java.util.List;

public interface IEvaluacionRepository<T> extends ICRUDEvaluacion<T> {
    List<T> findAll();

}
