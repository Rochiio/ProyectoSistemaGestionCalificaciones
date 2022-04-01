package repositories.evaluacion;

import java.util.List;

public interface IEvaluacionRepository<T> {
    List<T> findAll();
    T createEvaluacion(T test);
    T deleteEvalluacion(T test);
    T findById(int id);
}
