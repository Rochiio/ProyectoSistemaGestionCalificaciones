package repositories.evaluacion;

import models.evaluacion.Evaluacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluacionRepository<T> implements IEvaluacionRepository<Evaluacion> {
    private final Map<Integer, Evaluacion> testRepository = new HashMap<>();


    /**
     *  Devuelve las evaluaciones.
     * @return Devuelve una lista de evaluaciones
     */
    @Override
    public List<Evaluacion> findAll() {
        return new ArrayList<>(this.testRepository.values());
    }

    /**
     * Crea una evaluación
     * @param test que se desea crear.
     * @return Devuelve la evaluación creada.
     */
    @Override
    public Evaluacion createEvaluacion(Evaluacion test) {
        this.testRepository.put(test.getId(), test);
        return test;
    }

    /**
     * Elimina una evaluación.
     * @param test evaluación que se desea eliminar.
     * @return Devuelve la evaluación a eliminar.
     */
    @Override
    public Evaluacion deleteEvalluacion(Evaluacion test) {
        return this.testRepository.remove(test.getId());
    }

    /**
     * Busca una evaluación por si id.
     * @param id evaluación a buscar.
     * @return Devuelve la evaluación o nul sino existe.
     */
    @Override
    public Evaluacion findById(int id) {
        for (var newTest: testRepository.values()) {
            if (newTest.getId() == id){
                return newTest;
            }
        }
        return null;
    }

}
