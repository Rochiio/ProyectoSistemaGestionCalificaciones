package repositories.pruebaEvaluacion;

import controllers.DataBaseManager;

import java.sql.SQLException;
import java.util.List;

public interface IEvaluacionRepository<T> extends ICRUDEvaluacion<T> {
    List<T> findAll(DataBaseManager db) throws SQLException;
    void clearAll(DataBaseManager db) throws SQLException;
}
