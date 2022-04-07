package repositories.categoria;

import controllers.DataBaseManager;
import models.categoria.Categoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CategoriaRepository implements ICategoriaRepository<Categoria> {


    /**
     * Devuelve todas las Categorías.
     * @param db Base de datos.
     * @return Devuelve categoría.
     * @throws SQLException si hay algún problema con la base de datos.
     */
    @Override
    public List<Categoria> findAll(DataBaseManager db) throws SQLException {
        String query = "SELECT * FROM categoria";
        db.open();
            ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al obtener todas las categorías"));
                List<Categoria> list = new ArrayList<>();
                    while (result.next()) {
                        list.add(
                                new Categoria(
                                        result.getInt("id"),
                                        result.getString("Nombre")
                                )
                        );
                    }
            db.close();
        return list;
    }


    /**
     * Devuelve si una categoría tiene un nombre ya.
     * @param name nombre de la categoría.
     * @param db Base de datos.
     * @return null o categoría.
     * @throws SQLException si hay algún problema con la base de datos.
     */
    @Override
    public Optional<Categoria> findByName(String name, DataBaseManager db) throws SQLException {
        String query = "SELECT * FROM categoria WHERE nombre = ?";
        db.open();
            ResultSet result = db.select(query, name).orElseThrow(() -> new SQLException("Error al consultar categoría con nombre: " + name));
                if (result.next()) {
                        Categoria categoria = new Categoria(
                                result.getInt("id"),
                                result.getString("Nombre")
                        );
                    db.close();
                    return Optional.of(categoria);
                }
        return Optional.empty();
    }


    /**
     * Crea una categoría
     * @param item que se va a crear.
     * @param db Base de datos.
     * @return Devuelve categoría creada.
     * @throws SQLException si hay algún problema con la base de datos.
     */
    @Override
    public Categoria save(Categoria item , DataBaseManager db) throws SQLException {
        String query = "INSERT INTO categoria VALUES (null, ?)";
        db.open();
            ResultSet res = db.insert(query, item.getName())
                    .orElseThrow(() -> new SQLException("Error al insertar categoría"));

                if (res.first()) {
                    item.setId(res.getInt(1));
                    db.close();
                    return item;
                }
        return null;
    }


    /**
     * Actualiza una categoría.
     * @param id de la categoría que se quiere actualizar.
     * @param newName categoría nueva.
     * @param db Base de daots.
     * @return Devuelve la categoría actualizada.
     * @throws SQLException si hay algún problema con la base de datos.
     */
    @Override
    public Optional<Categoria> update(Integer id, String newName, DataBaseManager db) throws SQLException {
        var beforeData=this.findById(id,db)
                .orElseThrow(() -> new SQLException("Error al actualizar categoría. Categoría con id " + id + " no encontrado"));

        String query = "UPDATE categoria SET Nombre= ? WHERE id = ?";

        db.open();
        db.update(query,((newName.isEmpty())? beforeData.getName() : newName),id);
        db.close();
        return this.findById(id,db);
    }



    /**
     * Buscar una categoria por su id.
     * @param id que desea Buscar.
     * @param db Base de datos.
     * @return Devuelve la cateria, o nul si no existe.
     * @throws SQLException si hay algún problema con la base de datos.
     */
    public Optional<Categoria> findById(int id, DataBaseManager db) throws SQLException {
        String query = "SELECT * FROM categoria WHERE id = ?";
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar categoría con id: " + id));
        if (result.next()) {
            Categoria categoria = new Categoria(
                    result.getInt("id"),
                    result.getString("Nombre")
            );
            db.close();
            return Optional.of(categoria);
        }
        return Optional.empty();
    }


    /**
     * Vaciar categoria
     * @param db base de datos
     * @throws SQLException si hay algun error con la base.
     */
    @Override
    public void clearAll(DataBaseManager db) throws SQLException {
        String query = "DELETE FROM categoria";
        db.open();
        db.delete(query);
        db.close();
    }


}
