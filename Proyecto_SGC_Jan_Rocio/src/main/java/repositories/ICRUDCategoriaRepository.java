package repositories;

import models.categoria.Categoria;

public interface ICRUDCategoriaRepository<T> extends ICRUDCategoria<T>{

    T findAll();
    T save(T item);
    T update(Integer id, T item);
}
