package repositories.categoria;

public interface ICRUDCategoria<T>{
    T save(T item);
    T update(Integer id, String item);



}
