package models.evaluacion;

import controllers.DataBaseManager;
import repositories.calificaciones.CalificacionRepository;
import repositories.pruebaEvaluacion.IEvaluacionRepository;
import models.pruebaEvaluacion.PruebasEvaluacion;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Evaluacion {
    //Declaración de los atributos de la clase
    private final IEvaluacionRepository<PruebasEvaluacion> evaluationTestRepository;

    /**
     * Constructor.
     * @param evaluationTestRepository repositorio de pruebas de evaluación.
     */
    public Evaluacion(IEvaluacionRepository<PruebasEvaluacion> evaluationTestRepository) {
        this.evaluationTestRepository = evaluationTestRepository;
    }


    /**
     * Devuelve una lista con todas las pruebas de evaluacion.
     * @return lista.
     */
    public List<PruebasEvaluacion> findAllEvaluationTestRepository(DataBaseManager db) throws SQLException {
        return this.evaluationTestRepository.findAll(db);
    }


    /**
     * Buscar una prueba de evaluación por id
     * @param id identificador.
     * @return Prueba de evaluacion o null
     */
    public Optional<PruebasEvaluacion> findByIdEvaluationTestRepository(int id, DataBaseManager db) throws SQLException {
        return this.evaluationTestRepository.findById(id,db);
    }


    /**
     * Añadir una prueba de evaluación al repositorio.
     * @param newTest nueva prueba a añadir.
     * @return La prueba.
     */
    public PruebasEvaluacion createEvaluationTest(PruebasEvaluacion newTest, DataBaseManager db) throws SQLException, ParseException {
        return this.evaluationTestRepository.save(newTest,db);
    }

    /**
     * Añadir una prueba de evaluación al repositorio.
     * @param test nueva prueba a añadir.
     * @return La prueba.
     */
    public Optional<PruebasEvaluacion> addRatings(PruebasEvaluacion test, CalificacionRepository ratingRepository, DataBaseManager db) throws SQLException {
        return this.evaluationTestRepository.updateRatings(test,ratingRepository,db);
    }


    /**
     * Elimina una prueba de evaluacion.
     * @param exist prueba de evaluacion a eliminar.
     * @return la prueba eliminada.
     */
    public PruebasEvaluacion deleteEvaluationTest(PruebasEvaluacion exist, DataBaseManager db) throws SQLException {
        return this.evaluationTestRepository.delete(exist, db);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluacion that = (Evaluacion) o;
        return Objects.equals(evaluationTestRepository, that.evaluationTestRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evaluationTestRepository);
    }


    @Override
    public String toString() {
        return "Evaluacion{" +
                "evaluationTestRepository=" + evaluationTestRepository.toString() +
                '}';
    }



}
