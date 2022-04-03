package repositories.categoria;

import models.categoria.Categoria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriaRepository implements ICategoriaRepository<Categoria> {
    private final Map<Integer, Categoria> categoria = new HashMap<>();


    /**
     * Devuelve todas las Categorías.
     * @return Devuelve categoría.
     */
    @Override
    public List<Categoria> findAll() {
        return new ArrayList<>(this.categoria.values());
    }


    /**
     * Devuelve si una categoría tiene un nombre ya.
     * @param name nombre de la categoría.
     * @return null o categoría.
     */
    @Override
    public Categoria findByName(String name) {
        Categoria isExist = null;
            for (Categoria categoria : this.categoria.values()){
                if (categoria.getName().equals(name)){
                    isExist =this.categoria.get(categoria.getId());
                }
            }
        return isExist;
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
     * @param newName categoría nueva.
     * @return Devuelve la categoría actualizada.
     */
    @Override
    public Categoria update(Integer id, String newName) {
        var beforeData= this.categoria.get(id);
            if (newName.isEmpty()|| beforeData.getName().equals(newName)){
                this.categoria.get(id).setName((newName.isEmpty()) ? beforeData.getName() : newName);
            }
        return this.categoria.get(id);
    }



    /**
     * Buscar una categoria por su id.
     * @param id que desea Buscar.
     * @return Devuelve la cateria, o nul si no existe.
     */
    public Categoria findById(int id) {
        return this.categoria.get(id);
    }


}
