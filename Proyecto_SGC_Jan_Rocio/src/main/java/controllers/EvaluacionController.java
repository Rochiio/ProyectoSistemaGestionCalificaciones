package controllers;

import exceptions.EvaluacionException;
import models.evaluacion.Evaluacion;
import repositories.evaluacion.IEvaluacionRepository;


import java.util.List;

public class EvaluacionController {
    private IEvaluacionRepository evaluacionRepository;

    public EvaluacionController(IEvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
        //loaData();
    }

    //TODO CREAR PRUEBAS DE EVALUACIÓN
    private void loaData() {
        System.out.println("Crear evaluación");


    }

    /**
     * Devuelve todas evaluaciones
     * @return listas de evaluaciones
     */
    public List<Evaluacion> showTestEvaluation() throws EvaluacionException {
        var allList = this.evaluacionRepository.findAll();
        if (allList.size() == 0) {
            throw new EvaluacionException("La lista de evaluación esta vacia");
        }
        return allList;
    }


    /**
     * Crear una evaluación.
     * @param newTest evaluación que se desea crear
     * @return Devuelve la evaluación
     * @throws EvaluacionException si la evaluación no existe.
     */
    public Evaluacion CreateEvaluation(Evaluacion newTest) throws EvaluacionException {
        var exists = evaluacionRepository.findById(newTest.getId());
        if (exists == null){
            this.evaluacionRepository.createEvaluacion(newTest);
            return newTest;
        }
        throw new EvaluacionException("La evaluación ya existe.");
    }


    /**
     * Eliminar Evaluación
     * @param numberTest id de la evaluación a eliminar.
     * @return Devuelve evaluación
     * @throws EvaluacionException si la evaluación no existe.
     */
    public Object deleteEvalluacion(int numberTest) throws EvaluacionException {
        var exist = evaluacionRepository.findById(numberTest);
        if (exist!=null) {
            evaluacionRepository.deleteEvalluacion(exist);
            return exist;
        }
        throw new EvaluacionException("La prueba de evaluación que busca no existe.");
    }
}
