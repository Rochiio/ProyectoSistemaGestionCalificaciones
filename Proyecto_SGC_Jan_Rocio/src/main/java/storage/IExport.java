package storage;

public interface IExport<T> {
    void save(T item);
    void init(String file);

}
