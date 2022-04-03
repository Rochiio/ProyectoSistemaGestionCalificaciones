package controllers;

import exceptions.EvaluacionException;
import models.evaluacion.Evaluacion;
import models.pruebaEvaluacion.PruebasEvaluacion;


import java.util.List;

public class EvaluacionController {
    private final Evaluacion evaluation;


    /**
     * Constructor.
     * @param evaluation evaluacion.
     */
    public EvaluacionController(Evaluacion evaluation) {
        this.evaluation = evaluation;
        //loaData();
    }


    //TODO CREAR PRUEBAS DE EVALUACIÓN
    private void loaData() {
        System.out.println("Crear evaluación");


    }


    /**
     * Devuelve todas evaluationTest
     * @return listas de evaluationTest
     */
    public List<PruebasEvaluacion> showAllTestEvaluation() throws EvaluacionException {
        var allList = this.evaluation.findAllEvaluationTestRepository();
            if (allList.size() == 0) {
                throw new EvaluacionException("Error: la lista de pruebas de evaluación está vacía");
            }
        return allList;
    }


    /**
     * Devuelve una prueba de evaluacion.
     * @return prueba de evaluacion.
     * @throws EvaluacionException si no existe.
     */
    public PruebasEvaluacion showTestEvaluation(int id) throws EvaluacionException {
        var test = this.evaluation.findByIdEvaluationTestRepository(id);
            if (test == null) {
                throw new EvaluacionException("Error: la prueba de evaluación buscada no existe");
            }
        return test;
    }


    /**
     * Crear una evaluación.
     * @param newTest evaluación que se desea crear
     * @return Devuelve la evaluación
     * @throws EvaluacionException si la evaluación no existe.
     */
    public PruebasEvaluacion createEvaluation(PruebasEvaluacion newTest) throws EvaluacionException {
        PruebasEvaluacion show;
        var exists = evaluation.findByIdEvaluationTestRepository(newTest.getId());
            if (exists == null){
                show = this.evaluation.createEvaluationTest(newTest);
            }else {
                throw new EvaluacionException("Error: la prueba de evaluación ya existe.");
            }
      return show;
    }


    /**
     * Eliminar Evaluación
     * @param numberTest id de la evaluación a eliminar.
     * @return Devuelve evaluación
     * @throws EvaluacionException si la evaluación no existe.
     */
    public PruebasEvaluacion deleteEvaluation(int numberTest) throws EvaluacionException {
        PruebasEvaluacion show;
        var exist = this.evaluation.findByIdEvaluationTestRepository(numberTest);
            if (exist!=null) {
                show = this.evaluation.deleteEvaluationTest(exist);
            }else {
                throw new EvaluacionException("Error: la prueba de evaluación que busca no existe.");
            }
     return show;
    }
}
