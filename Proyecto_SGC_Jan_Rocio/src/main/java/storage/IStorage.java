package storage;

import java.util.List;

public interface IStorage<T> {
    /**
     * Salvar los datos.
     */
    public void save();

    /**
     * Importar los datos
     * @return una lista de los datos.
     */
    public List<T> load();
}
