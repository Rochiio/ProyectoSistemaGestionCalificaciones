package repositories.pruebaEvaluacion;

public interface ICRUDEvaluacion<T> {
    T save(T evaluationTest);

    T delete(T id);

    T findById(int id);
}

