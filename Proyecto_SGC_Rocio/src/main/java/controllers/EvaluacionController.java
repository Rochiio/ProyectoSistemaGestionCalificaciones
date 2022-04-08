package controllers;

import exceptions.EvaluacionException;
import models.calificacion.Calificacion;
import models.evaluacion.Evaluacion;
import models.pruebaEvaluacion.PruebasEvaluacion;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public class EvaluacionController {
    private final Evaluacion evaluation;

    /**
     * Constructor.
     * @param evaluation repositorio de pruebas de evaluation.
     */
    public EvaluacionController(Evaluacion evaluation) {
        this.evaluation = evaluation;
    }



    /**
     * Devuelve todas evaluationTest
     * @return listas de evaluationTest
     */
    public List<PruebasEvaluacion> showAllTestEvaluation(DataBaseManager db) throws EvaluacionException, SQLException {
        var allList = this.evaluation.findAllEvaluationTestRepository(db);
            if (allList.isEmpty()) {
                throw new EvaluacionException("Error: la lista de pruebas de evaluación está vacía");
            }
        return allList;
    }


    /**
     * Devuelve una prueba de evaluation.
     * @return prueba de evaluation.
     * @throws EvaluacionException si no existe.
     */
    public Optional<PruebasEvaluacion> showTestEvaluation(int id, DataBaseManager db) throws EvaluacionException, SQLException {
        var test = this.evaluation.findByIdEvaluationTestRepository(id,db);
            if (test.isEmpty()) {
                throw new EvaluacionException("Error: la prueba de evaluación buscada no existe");
            }
        return test;
    }


    /**
     * Crear una evaluación.
     * @param newTest evaluación que se desea crear
     * @return Devuelve la evaluación
     * @throws EvaluacionException si la evaluación no existe.
     * @throws SQLException si hubiese algún fallo con la base de datos.
     * @throws ParseException si hay algún fallo con el parseo.
     */
    public PruebasEvaluacion createPruebaEvaluation(PruebasEvaluacion newTest, DataBaseManager db)
            throws EvaluacionException, SQLException, ParseException {

        PruebasEvaluacion show;
        var exists = evaluation.findByIdEvaluationTestRepository(newTest.getId(),db);
            if (exists.isEmpty()){
                show = this.evaluation.createEvaluationTest(newTest, db);
            }else {
                throw new EvaluacionException("Error: la prueba de evaluación ya existe.");
            }
      return show;
    }

    /**
     * Añadir a una nueva prueba de evaluación sus calificaciones.
     * @param test Prueba de evaluación a añadir calificaciones.
     * @param ratingsRepository calificaciones a añadir.
     * @param db base de datos.
     * @return Prueba de evaluación terminada
     * @throws SQLException si hay algún problema con la base de datos.
     */
    public Optional<PruebasEvaluacion> addRatings(PruebasEvaluacion test, List<Calificacion> ratingsRepository, DataBaseManager db) throws SQLException {
        return  this.evaluation.addRatings(test, ratingsRepository, db);
    }


    /**
     * Eliminar Evaluación
     * @param numberTest id de la evaluación a eliminar.
     * @return Devuelve evaluación
     * @throws EvaluacionException si la evaluación no existe.
     * @throws SQLException si hubiese algún fallo con la base de datos.
     */
    public PruebasEvaluacion deleteEvaluation(int numberTest, DataBaseManager db) throws EvaluacionException, SQLException {
        PruebasEvaluacion show;
        var exist = this.evaluation.findByIdEvaluationTestRepository(numberTest, db);
            if (exist.isPresent()) {
                show = this.evaluation.deleteEvaluationTest(exist.get(), db);
            }else {
                throw new EvaluacionException("Error: la prueba de evaluación que busca no existe.");
            }
     return show;
    }


}
