package storage;

import java.util.List;

public interface IStorage<T> {
    /**
     * Salvar los datos.
     * @param item a guardar
     */
    void save(List<T> item);

    /**
     * Importar los datos
     * @return una lista de los datos.
     */
    List<T> load();

}
