package repositories.pruebaEvaluacion;


import models.pruebaEvaluacion.PruebasEvaluacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PruebaEvaluacionRepository implements IEvaluacionRepository<PruebasEvaluacion> {
    public final Map<Integer, PruebasEvaluacion> evaluationTest = new HashMap<>();

    /**
     * Crea una evaluación
     * @param evaluationTest que deseamos crea.
     * @return Devuelve la evaluación que se ha añadido.
     */
    @Override
    public PruebasEvaluacion save(PruebasEvaluacion evaluationTest) {
        this.evaluationTest.put(evaluationTest.getId(), evaluationTest);
        return this.evaluationTest.get(evaluationTest.getId());
    }


    /**
     * Devuelve todas la evaluationTest
     * @return devuelve las evaluationTest
     */
    @Override
    public List<PruebasEvaluacion> findAll() {
        return new ArrayList<>(this.evaluationTest.values());
    }



    /**
     * Eliminar prueba de evaluación
     * @param evaluationTest prueba de evaluación a eliminar.
     * @return Devuelve la prueba de evaluación eliminada.
     */
    @Override
    public PruebasEvaluacion delete(PruebasEvaluacion evaluationTest) {
        return this.evaluationTest.remove(evaluationTest.getId());
    }


    /**
     * Busca la prueba de evaluación por su id
     * @param id de la prueba de evaluación a buscar.
     * @return Devuelve la prueba de evaluación.
     */
    public PruebasEvaluacion findById(int id) {
        return this.evaluationTest.get(id);
    }
}
