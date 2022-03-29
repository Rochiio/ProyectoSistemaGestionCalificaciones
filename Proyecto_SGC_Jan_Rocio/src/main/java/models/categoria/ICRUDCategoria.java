package models.categoria;

public interface ICRUDCategoria<T>{

    T save(T item);

    T update(T item);

}
