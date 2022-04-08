package repositories.calificaciones;

import controllers.DataBaseManager;

import java.sql.SQLException;

public interface ICRUDCalificacion<T> {
    T create(T item, int id_Evaluation, DataBaseManager db) throws SQLException;
    int delete(int id, DataBaseManager db) throws SQLException;
}
