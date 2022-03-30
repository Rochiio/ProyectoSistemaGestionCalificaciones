package repositories;

import models.categoria.Categoria;

import java.util.HashMap;
import java.util.Map;

public class CategoriaRepository implements ICRUDCategoriaRepository<Categoria> {
    private final Map<Integer, Categoria> categoria = new HashMap<>();


    /**
     * Devuelve todas las Categorías.
     * @return Devuelve categoría.
     */
    @Override
    public Categoria findAll() {
        return (Categoria) this.categoria.values();
    }


    /**
     * Crea una categoría
     * @param item que se va a crear.
     * @return Devuelve categoría creada.
     */
    @Override
    public Categoria save(Categoria item) {
        this.categoria.put(item.getId(), item);
        return this.categoria.get(item.getId());
    }


    /**
     * Actualiza una categoría.
     * @param id de la categoría que se quiere actualizar.
     * @param item categoría nueva.
     * @return Devuelve la categoría actualizada.
     */
    @Override
    public Categoria update(Integer id, Categoria item) {
        var exists = findById(item.getId());
        this.categoria.get(id).setNombre((item.getNombre().isEmpty()) ? item.getNombre() : exists.getNombre());
        return this.categoria.get(id);
    }



    /**
     * Buscar una categoria por su id.
     * @param id que desea Buscar.
     * @return Devuelve la cateria, o nul si no existe.
     */
    private Categoria findById(int id) {
        for (var categoria : this.categoria.values()) {
            if (categoria.getId() == id){
                return categoria;
            }
        }
        return null;
    }


}
