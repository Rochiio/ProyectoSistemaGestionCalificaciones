package controllers;

import exceptions.CategoriaException;
import models.categoria.Categoria;

import repositories.categoria.ICategoriaRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
     * @throws SQLException si hubiese algún fallo con la base de datos.
     */
    public Categoria addCategory(Categoria category) throws CategoriaException, SQLException {
        Categoria returnCategory;
        var exist = categoryRepository.findByName(category.getName(),DataBaseManager.getInstance());
            if (exist.isEmpty()){
                returnCategory=categoryRepository.save(category,DataBaseManager.getInstance());
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
     * @throws SQLException si hubiese algún fallo con la base de datos.
     */
    public Optional<Categoria> modifyCategory(int id, String newName) throws CategoriaException, SQLException {
        Optional<Categoria> returnCategory;
        var exist = categoryRepository.findById(id, DataBaseManager.getInstance());
            if (exist.isPresent()) {
                var newNameOkey = categoryRepository.findByName(newName,DataBaseManager.getInstance());
                    if (newNameOkey.isEmpty()) {
                        returnCategory = categoryRepository.update(id, newName, DataBaseManager.getInstance());
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
     * @throws SQLException si hubiese algún fallo con la base de datos.
     * @throws CategoriaException si no existe la categoría buscada.
     */
    public Optional<Categoria> showCategory(int numberIdCategory) throws CategoriaException, SQLException {
        var returnCategory = categoryRepository.findById(numberIdCategory,DataBaseManager.getInstance());
        if (returnCategory.isEmpty()) {
            throw new CategoriaException("Error: No existe una categoría con este id");
        }
        return returnCategory;
    }


    /**
     * Mostrar todas las categorías.
     * @return lista de categorías.
     * @throws CategoriaException si no hay ninguna categoria.
     * @throws SQLException si hay error con la base de datos.
     */
    public List<Categoria> showAllCategories() throws CategoriaException, SQLException {
        var returnAllCategories = categoryRepository.findAll(DataBaseManager.getInstance());
        if (returnAllCategories.size() == 0){
            throw new CategoriaException("Error: No hay ninguna categoría");
        }
        return returnAllCategories;
    }
}
