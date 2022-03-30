package controllers;

import exceptions.AlumnoException;
import exceptions.CategoriaException;
import models.alumno.Alumno;
import models.categoria.Categoria;

import repositories.categoria.ICategoriaRepository;

import java.util.List;

public class CategoriaController {
    private final ICategoriaRepository<Categoria> categoryRepository;


    /**
     * Constructor.
     * @param categoryRepository repositorio.
     */
    public CategoriaController(ICategoriaRepository<Categoria> categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    /**
     * Añadir categoria.
     * @param category nueva categoria.
     * @return categoria nueva añadida.
     * @throws CategoriaException si ya existe una categoría con ese nombre.
     */
    public Categoria addCategory(Categoria category) throws CategoriaException {
        Categoria returnCategory;
        var exist = categoryRepository.findByName(category.getName());
            if (exist == null){
                returnCategory=categoryRepository.save(category);
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
    public Categoria modifyCategory(int id, String newName) throws CategoriaException {
        Categoria returnCategory;
        var exist = categoryRepository.findById(id);
            if (exist != null) {
                var newNameOkey = categoryRepository.findByName(newName);
                    if (newNameOkey == null) {
                        returnCategory = categoryRepository.update(id, newName);
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
    public Categoria showCategory(int numberIdCategory) throws CategoriaException {
        var returnCategory = categoryRepository.findById(numberIdCategory);
        if (returnCategory==null) {
            throw new CategoriaException("Error: No existe una categoría con este id");
        }
        return returnCategory;
    }



    public List<Categoria> showAllCategories() throws CategoriaException {
        var returnAllCategories = categoryRepository.findAll();
        if (returnAllCategories.size() == 0){
            throw new CategoriaException("Error: No hay ninguna categoría");
        }
        return returnAllCategories;
    }
}
