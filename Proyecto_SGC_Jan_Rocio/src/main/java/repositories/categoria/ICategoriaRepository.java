package repositories.categoria;

import models.categoria.Categoria;

import java.util.List;

public interface ICategoriaRepository<T> extends ICRUDCategoria<T> {
    List<Categoria> findAll();
    T findByName(String name);
    T findById(int id);
}
