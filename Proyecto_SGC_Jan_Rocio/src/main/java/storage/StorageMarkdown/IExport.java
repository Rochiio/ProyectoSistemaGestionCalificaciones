package storage.StorageMarkdown;


import models.calificacion.Calificacion;

import java.util.List;

public interface IExport<T> {
    void init(String file);
    void save(T item, List<Calificacion> ratings);
}
