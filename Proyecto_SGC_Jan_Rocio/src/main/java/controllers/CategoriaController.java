package controllers;

import exceptions.CategoriaException;
import models.categoria.Categoria;

import repositories.categoria.ICategoriaRepository;

import java.util.List;

public class CategoriaController {
    private static ICategoriaRepository<Categoria> CategoriaRepository = null;


    /**
     * Constructor.
     * @param categoryRepository repositorio.
     */
    public CategoriaController(ICategoriaRepository<Categoria> categoryRepository) {
        this.CategoriaRepository = categoryRepository;
    }


    /**
     * Añadir categoria.
     * @param category nueva categoria.
     * @return categoria nueva añadida.
     * @throws CategoriaException si ya existe una categoría con ese nombre.
     */
    public static Categoria addCategory(Categoria category) throws CategoriaException {
        Categoria returnCategory;
        var exist = CategoriaRepository.findByName(category.getName());
            if (exist == null){
                returnCategory= CategoriaRepository.save(category);
            }else {
                throw new CategoriaException("Ya existe una categoría con este nombre");
            }
        return returnCategory;
    }


    /**
     * Modificar categoría.
     * @param id id categoria.
     * @param newName nuevo nombre.
     * @return la categoría modificada.
     * @throws CategoriaException si no existe una categoría con ese id.
     */
    public static Categoria modifyCategory(int id, String newName) throws CategoriaException {
        Categoria returnCategory;
        var exist = CategoriaRepository.findById(id);
            if (exist != null) {
                var newNameOkey = CategoriaRepository.findByName(newName);
                    if (newNameOkey == null) {
                        returnCategory = CategoriaRepository.update(id, newName);
                    } else {
                        throw new CategoriaException("Ya existe una categoría con este nombre");
                    }
            } else {
                throw new CategoriaException("No existe una categoría con este id");
            }
        return returnCategory;
    }


    /**
     * Mostrar una categoría.
     * @param numberIdCategory id de la categoría a mostrar.
     * @return la categoría.
     */
    public static Categoria showCategory(int numberIdCategory) throws CategoriaException {
        var returnCategory = CategoriaRepository.findById(numberIdCategory);
        if (returnCategory==null) {
            throw new CategoriaException("Error: No existe una categoría con este id");
        }
        return returnCategory;
    }



    public static List<Categoria> showAllCategories() throws CategoriaException {
        var returnAllCategories = CategoriaRepository.findAll();
        if (returnAllCategories.size() == 0){
            throw new CategoriaException("Error: No hay ninguna categoría");
        }
        return returnAllCategories;
    }
}
