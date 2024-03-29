package repositories.pruebaEvaluacion;

import controllers.DataBaseManager;
import models.calificacion.Calificacion;
import models.pruebaEvaluacion.PruebasEvaluacion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class PruebaEvaluacionRepository implements IEvaluacionRepository<PruebasEvaluacion> {
    public static PruebaEvaluacionRepository instance=null;

    /**
     * Singleton
     * @return siempre la misma instancia.
     */
    public static PruebaEvaluacionRepository getInstance(){
        if(instance ==null){
            instance = new PruebaEvaluacionRepository();
        }
        return instance;
    }



    /**
     * Crea una evaluación
     * @param evaluationTest que deseamos crea.
     * @return Devuelve la evaluación que se ha añadido.
     * @throws SQLException si hay algún error con la base de datos.
     */
    @Override
    public PruebasEvaluacion save(PruebasEvaluacion evaluationTest, DataBaseManager db) throws SQLException {
        String query = "INSERT INTO evaluacion VALUES (null, now(), ?, ?, null, null, null, null, null, null)";
        db.open();

                ResultSet res = db.insert(query,evaluationTest.getDescripcion()
                                ,evaluationTest.getIdCategory()
                        )
                        .orElseThrow(() -> new SQLException("Error al insertar prueba de evaluación"));

            if (res.first()) {
                evaluationTest.setId(res.getInt(1));
                db.close();
                return evaluationTest;
        }
        return null;
    }


    /**
     * Despues de crear la prueba de evaluación, poder añadir las calificaciones.
     * @param evaluationTest prueba de evaluacion a la que se le van a añadir las calificaciones.
     * @param ratingRepository lista de las calificaciones de la prueba.
     * @param db base de datos.
     * @return la prueba de evaluacion
     * @throws SQLException si hay algún error con la base de datos.
     */
    public Optional<PruebasEvaluacion> updateRatings(PruebasEvaluacion evaluationTest, List<Calificacion> ratingRepository, DataBaseManager db) throws SQLException {
        String query = "UPDATE evaluacion SET Nota_Maxima=?, Nota_Media=?, Nota_Minima=?,Porcentaje_Aprobados=?, " +
                "Porcentaje_Suspensos=?, id_Calificaciones=? WHERE id = ?";

        var pass =(((ratingRepository.stream().filter(a -> a.getNota()>=5).count())*100)/ (long) ratingRepository.size());
        var fail = (((ratingRepository.stream().filter(a -> a.getNota()<5).count())*100)/ (long) ratingRepository.size());
        DoubleSummaryStatistics stadistics = ratingRepository.stream().mapToDouble(Calificacion::getNota).summaryStatistics();

        db.open();
        db.update(query,
                stadistics.getMax()
                ,stadistics.getAverage()
                ,stadistics.getMin()
                ,pass
                ,fail
                ,evaluationTest.getId()
                ,evaluationTest.getId()
        );
        db.close();
        return this.findById(evaluationTest.getId(),db);
    }


    /**
     * Devuelve todas la evaluationTest
     * @return devuelve las evaluationTest
     * @throws SQLException si hay algún error con la base de datos.
     */
    @Override
    public List<PruebasEvaluacion> findAll(DataBaseManager db) throws SQLException {
        String query = "SELECT * FROM evaluacion";
        db.open();
            ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al obtener todas las pruebas de evaluación"));
                List<PruebasEvaluacion> list = new ArrayList<>();
                while (result.next()) {
                    list.add(
                            new PruebasEvaluacion(
                                    result.getInt("id"),
                                    result.getObject("Fecha",LocalDateTime.class),
                                    result.getString("Descripcion"),
                                    result.getInt("id_Categoria"),
                                    result.getFloat("Nota_Maxima"),
                                    result.getFloat("Nota_Media"),
                                    result.getFloat("Nota_Minima"),
                                    result.getFloat("Porcentaje_Aprobados"),
                                    result.getFloat("Porcentaje_Suspensos"),
                                    result.getInt("id_Calificaciones")
                            )
                    );
                }
        db.close();
        return list;
    }



    /**
     * Eliminar prueba de evaluación
     * @param evaluationTest prueba de evaluación a eliminar.
     * @return Devuelve la prueba de evaluación eliminada.
     * @throws SQLException si hay algún error con la base de datos.
     */
    @Override
    public PruebasEvaluacion delete(PruebasEvaluacion evaluationTest, DataBaseManager db) throws SQLException {
        String query = "DELETE FROM evaluacion WHERE id = ?";
        db.open();
            db.delete(query, evaluationTest.getId());
        db.close();
        return evaluationTest;
    }


    /**
     * Busca la prueba de evaluación por su id
     * @param id de la prueba de evaluación a buscar.
     * @return Devuelve la prueba de evaluación.
     * @throws SQLException si hay algún error con la base de datos.
     */
    public Optional<PruebasEvaluacion> findById(int id, DataBaseManager db) throws SQLException {
        String query = "SELECT * FROM evaluacion WHERE id = ?";
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar prueba de evaluacion con id: " + id));
        if (result.next()) {
            PruebasEvaluacion test =  new PruebasEvaluacion(
                    result.getInt("id"),
                    result.getObject("Fecha",LocalDateTime.class),
                    result.getString("Descripcion"),
                    result.getInt("id_Categoria"),
                    result.getFloat("Nota_Maxima"),
                    result.getFloat("Nota_Media"),
                    result.getFloat("Nota_Minima"),
                    result.getFloat("Porcentaje_Aprobados"),
                    result.getFloat("Porcentaje_Suspensos"),
                    result.getInt("id_Calificaciones")
            );
            db.close();
            return Optional.of(test);
        }
        return Optional.empty();
    }


    /**
     * Vaciar pruebas
     * @param db base de datos
     * @throws SQLException si hay algun error con la base.
     */
    @Override
    public void clearAll(DataBaseManager db) throws SQLException {
        String query = "DELETE FROM evaluacion";
        db.open();
        db.delete(query);
        db.close();
    }
}
