package repositories;

import com.diogonunes.jcolor.Attribute;
import exceptions.evaluacionException;
import models.evaluacion.Evaluacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.diogonunes.jcolor.Ansi.colorize;

public class EvaluacionRepository implements ICRUDEvaluacionRepository<Evaluacion> {
    public final Map<Integer, Evaluacion>  evaluaciones = new HashMap<>();

    /**
     * Crea una evaluación
     * @param eva que deseamos crea.
     * @return Devuelve la evaluación que se ha añadido.
     */
    @Override
    public Evaluacion save(Evaluacion eva) {
        this.evaluaciones.put(eva.getId(), eva);
        return this.evaluaciones.get(eva.getId());
    }


    /**
     * Devuelve todas la evaluaciones
     * @return devuelve las evaluaciones
     */
    @Override
    public Evaluacion findAll() {
        return (Evaluacion) this.evaluaciones.values();
    }




    @Override
    public Evaluacion create(Evaluacion eva) {
        this.evaluaciones.put(eva.getId(), eva);
        return this.evaluaciones.get(eva.getId());
    }


    /**
     * Eliminar una evaluación
     * @param eva que deseas eliminar.
     * @return Devuelve la evaluación eliminada.
     * @throws evaluacionException si la evaluación no existe.
     */
    @Override
    public Evaluacion delete(Evaluacion eva) throws evaluacionException {
        var existe = findById(eva.getId());
        if (existe != null){
            this.evaluaciones.remove(eva.getId());
            return eva;
        }
        throw new evaluacionException(colorize("La evaluación que deseas eliminar no existe..", Attribute.RED_TEXT()));
    }


    /**
     * Busca una evaluación por su id
     * @param id de la evaluación a buscar.
     * @return Devuelve la evaluación buscada, null si no existe.
     */
    private Evaluacion findById(int id) {
        for (var eva : evaluaciones.values()) {
            if (eva.getId() == id) {
                return eva;
            }
        }
       return null;
    }
}
