package repositories.calificaciones;

import controllers.DataBaseManager;
import models.calificacion.Calificacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

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
            ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al obtener las calificaciones con id " + idRating));
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
     *Para imprimir la lista en modo markdown
     * @return String en forma markdown.
     */
    @Override
    public String toMarkdown(DataBaseManager db) throws SQLException {
        StringBuilder result = new StringBuilder();
        List<Calificacion> list = this.findAll(db);
            for (Calificacion calificacion : list){
                result.append("- ").append(calificacion.toMarkdown()).append("\n");
            }
      return result.toString();
    }


    /**
     * Conseguir la nota más alta de las calificaciones con un id.
     * @param idRatings id de las calificaciones a buscar.
     * @param db base de datos.
     * @return el maximo.
     * @throws SQLException si hay algún error con la base de datos.
     */
    @Override
    public double getMax(int idRatings, DataBaseManager db) throws SQLException {
        List<Calificacion> list = this.findById(idRatings,db);
        DoubleSummaryStatistics rating = list.stream().mapToDouble(Calificacion::getNota).summaryStatistics();
        return rating.getMax();
    }


    /**
     * Conseguir la nota media de las calificaciones con un id.
     * @param idRatings id de las calificaciones a buscar.
     * @param db base de datos.
     * @return el maximo.
     * @throws SQLException si hay algún error con la base de datos.
     */
    @Override
    public double getAverage(int idRatings, DataBaseManager db) throws SQLException {
        List<Calificacion> list = this.findById(idRatings,db);
        DoubleSummaryStatistics rating = list.stream().mapToDouble(Calificacion::getNota).summaryStatistics();
        return rating.getAverage();
    }


    /**
     * Conseguir la nota minima de las calificaciones con un id.
     * @param idRatings id de las calificaciones a buscar.
     * @param db base de datos.
     * @return el maximo.
     * @throws SQLException si hay algún error con la base de datos.
     */
    @Override
    public double getMin(int idRatings, DataBaseManager db) throws SQLException {
        List<Calificacion> list = this.findById(idRatings,db);
        DoubleSummaryStatistics rating = list.stream().mapToDouble(Calificacion::getNota).summaryStatistics();
        return rating.getMin();
    }


    /**
     * Conseguir el procentaje de alumnos aprobados de un id concreto.
     * @param idRatings id
     * @param db base de datos
     * @return la media de alumnos aprobados
     * @throws SQLException si hay algún problema en la base de datos.
     */
    @Override
    public double getPass(int idRatings, DataBaseManager db) throws SQLException {
        List<Calificacion> list = this.findById(idRatings,db);
        List<Calificacion> passStudentsNumber = list.stream().filter(a -> a.getNota() >= 5).collect(Collectors.toList());
        return ((passStudentsNumber.size()/list.size())*list.size());
    }


    /**
     * Conseguir el procentaje de alumnos suspendidos de un id concreto.
     * @param idRatings id
     * @param db base de datos
     * @return la media de alumnos aprobados
     * @throws SQLException si hay algún problema en la base de datos.
     */
    @Override
    public double getFail(int idRatings, DataBaseManager db) throws SQLException {
        List<Calificacion> list = this.findById(idRatings,db);
        List<Calificacion> failStudentsNumber = list.stream().filter(a -> a.getNota() < 5).collect(Collectors.toList());
        return ((failStudentsNumber.size()/list.size())*list.size());
    }


    /**
     * Crear calificaión
     * @param item calificación que se desea crear.
     * @return Devuelve la calificación creada.
     */
    @Override
    public Calificacion create(Calificacion item, int id_Evaluation, DataBaseManager db) throws SQLException {
        String query = "INSERT INTO calificacion VALUES (?, ?, ?, now())";
        db.open();
        ResultSet res = db.insert(query, id_Evaluation,item.getId_Student(),item.getNota())
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

}
