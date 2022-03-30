package repositories;

public interface ICRUDCategoria<T>{
    T save(T item);

    T update(Integer id, T item);



}
