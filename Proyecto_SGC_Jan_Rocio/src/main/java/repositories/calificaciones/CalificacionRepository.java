package repositories.calificaciones;

import controllers.DataBaseManager;
import models.calificacion.Calificacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class CalificacionRepository implements ICalificacionRepository<Calificacion> {
    public static CalificacionRepository instance=null;

    /**
     * Singleton
     * @return siempre la misma instancia.
     */
    public static CalificacionRepository getInstance(){
        if(instance ==null){
            instance = new CalificacionRepository();
        }
        return instance;
    }


    /**
     * Muestra la lista de calificaciones
     * @return Devuelve las calificaciones.
     */
    @Override
    public List<Calificacion> findAll(DataBaseManager db) throws SQLException {
        String query = "SELECT * FROM calificacion";
        db.open();
            ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al obtener todos los alumnos"));
                List<Calificacion> list = new ArrayList<>();
                    while (result.next()) {
                        list.add(
                                new Calificacion(result.getInt("id"),
                                        result.getInt("id_Alumno"),
                                        result.getFloat("Nota"),
                                        result.getObject("Entrega", LocalDateTime.class)
                                )
                        );
            }
        db.close();
        return list;
    }


    /**
     * Buscar las calificaciones
     * @param idStudent calificacion a buscar por el id de alumno.
     * @return Devuelve la calificación buscada.
     */
    @Override
    public Optional<Calificacion> findByAlumno(int idStudent, DataBaseManager db) throws SQLException {
        String query = "SELECT * FROM calificacion WHERE id_Alumno = ?";
        db.open();
            ResultSet result = db.select(query, idStudent).orElseThrow(() -> new SQLException("Error al consultar alumno con id: " + idStudent));
            if (result.next()) {
                    Calificacion rating = new Calificacion(result.getInt("id"),
                            result.getInt("id_Alumno"),
                            result.getFloat("Nota"),
                            result.getObject("Entrega", LocalDateTime.class)
                    );
                db.close();
                return Optional.of(rating);
            }
        return Optional.empty();
    }


    /**
     *  Busca la calificaciones por si id
     * @param idRating de las calificaciones a buscar.
     * @return Devuelve la calificación buscada
     */
    @Override
    public List<Calificacion> findById(int idRating, DataBaseManager db) throws SQLException {
        String query = "SELECT * FROM calificacion WHERE id = ?";
        db.open();
            ResultSet result = db.select(query,idRating).orElseThrow(() -> new SQLException("Error al obtener las calificaciones con id " + idRating));
            List<Calificacion> list = new ArrayList<>();
                while (result.next()) {
                    list.add(
                            new Calificacion(result.getInt("id"),
                                    result.getInt("id_Alumno"),
                                    result.getFloat("Nota"),
                                    result.getObject("Entrega", LocalDateTime.class)
                            )
                    );
            }
        db.close();
        return list;
    }


    /**
     * Crear calificaión
     * @param item calificación que se desea crear.
     * @return Devuelve la calificación creada.
     */
    @Override
    public Calificacion create(Calificacion item, int id_Evaluation, DataBaseManager db) throws SQLException {
        String query = "INSERT INTO calificacion VALUES (?, ?, ?, ?)";
        db.open();
        ResultSet res = db.insert(query, id_Evaluation,item.getId_Student(),item.getNota(),LocalDateTime.now())
                .orElseThrow(() -> new SQLException("Error al insertar calificación"));

        if (res.first()) {
            item.setId(res.getInt(1));
            db.close();
            return item;
        }
        return null;
    }


    /**
     * Elimina una calificación.
     * @param id id de calificación a eliminar.
     * @return Devuelve la calificación eliminada.
     */
    @Override
    public int delete(int id, DataBaseManager db) throws SQLException {
        String query = "DELETE FROM calificacion WHERE id = ?";
        db.open();
                db.delete(query, id);
        db.close();
        return id;
    }


    @Override
    public String toString(int id, DataBaseManager db) throws SQLException {
        StringBuilder sb = new StringBuilder();
        List<Calificacion> list = this.findById(id,db);
        for (Calificacion calificacion : list){
            sb.append(calificacion.toString()).append("\n");
        }
        return sb.toString();
    }


    /**
     * Vaciar calificaciones
     * @param db base de datos
     * @throws SQLException si hay algun error con la base.
     */
    @Override
    public void clearAll(DataBaseManager db) throws SQLException {
        String query = "DELETE FROM calificacion";
        db.open();
        db.delete(query);
        db.close();
    }

}
