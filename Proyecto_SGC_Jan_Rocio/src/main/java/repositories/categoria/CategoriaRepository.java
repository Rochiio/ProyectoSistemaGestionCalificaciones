package repositories.categoria;

import controllers.DataBaseManager;
import models.categoria.Categoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CategoriaRepository implements ICategoriaRepository<Categoria> {


    /**
     * Devuelve todas las Categorías.
     * @return Devuelve categoría.
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
     * @return null o categoría.
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
     * @return Devuelve categoría creada.
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
     * @return Devuelve la categoría actualizada.
     */
    @Override
    public Optional<Categoria> update(Integer id, String newName, DataBaseManager db) throws SQLException {
        var beforeData=this.findById(id,db)
                .orElseThrow(() -> new SQLException("Error al actualizar categoría. Categoría con id " + id + " no encontrado"));

        String query = "UPDATE categoria SET Nombre= ? WHERE id = ?";

        db.open();
        db.update(query,newName,id);
        db.close();
        return this.findById(id,db);
    }



    /**
     * Buscar una categoria por su id.
     * @param id que desea Buscar.
     * @return Devuelve la cateria, o nul si no existe.
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


}
