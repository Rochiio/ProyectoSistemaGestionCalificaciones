package repositories.categoria;

import controllers.DataBaseManager;
import models.categoria.Categoria;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ICategoriaRepository<T> extends ICRUDCategoria<T> {
    List<Categoria> findAll(DataBaseManager db) throws SQLException;
    Optional<T> findByName(String name, DataBaseManager db) throws SQLException;
    Optional<T> findById(int id, DataBaseManager db) throws SQLException;
    void clearAll(DataBaseManager db) throws SQLException;
}
