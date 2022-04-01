package storage;

public interface IExport<T> {
    void init(String path);
    String pathSave(String directory);
    void createPath(String path);
    void save(T item);
}
