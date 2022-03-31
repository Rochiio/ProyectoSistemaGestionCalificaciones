package repositories.pruebaEvaluacion;


import models.pruebaEvaluacion.PruebasEvaluacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.diogonunes.jcolor.Ansi.colorize;

public class PruebaEvaluacionRepository implements IEvaluacionRepository<PruebasEvaluacion> {
    public final Map<Integer, PruebasEvaluacion>  evaluaciones = new HashMap<>();

    /**
     * Crea una evaluación
     * @param evaluationTest que deseamos crea.
     * @return Devuelve la evaluación que se ha añadido.
     */
    @Override
    public PruebasEvaluacion save(PruebasEvaluacion evaluationTest) {
        this.evaluaciones.put(evaluationTest.getId(), evaluationTest);
        return this.evaluaciones.get(evaluationTest.getId());
    }


    /**
     * Devuelve todas la evaluaciones
     * @return devuelve las evaluaciones
     */
    @Override
    public List<PruebasEvaluacion> findAll() {
        return new ArrayList<>(this.evaluaciones.values());
    }


    /**
     * Crea una prueba de evaluación
     * @param evaluationTest Prueba que se va a crear.
     * @return Devuelve la prueba.
     */
    @Override
    public PruebasEvaluacion create(PruebasEvaluacion evaluationTest) {
        this.evaluaciones.put(evaluationTest.getId(), evaluationTest);
        return this.evaluaciones.get(evaluationTest.getId());
    }


    /**
     * Eliminar prueba de evaluación
     * @param evaluationTest prueba de evaluación a eliminar.
     * @return Devuelve la prueba de evaluación eliminada.
     */
    @Override
    public PruebasEvaluacion delete(PruebasEvaluacion evaluationTest) {
        return this.evaluaciones.remove(evaluationTest);
    }


    /**
     * Busca la prueba de evaluación por su id
     * @param id de la prueba de evaluación a buscar.
     * @return Devuelve la prueba de evaluación.
     */
    private PruebasEvaluacion findById(int id) {
        return this.evaluaciones.get(id);
    }
}
