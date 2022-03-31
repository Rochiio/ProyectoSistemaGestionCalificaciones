package repositories.evaluacion;

import com.diogonunes.jcolor.Attribute;
import exceptions.EvaluacionException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.diogonunes.jcolor.Ansi.colorize;

public class PruebaEvaluacionRepository implements IEvaluacionRepository<PruebasEvaluacion> {
    public final Map<Integer, PruebasEvaluacion>  evaluaciones = new HashMap<>();

    @Override
    public PruebasEvaluacion create(PruebasEvaluacion item) {
        return null;
    }

    @Override
    public PruebasEvaluacion delete(PruebasEvaluacion id) throws EvaluacionException {
        return null;
    }

    @Override
    public List<PruebasEvaluacion> findAll() {
        return null;
    }

//    /**
//     * Crea una evaluación
//     * @param eva que deseamos crea.
//     * @return Devuelve la evaluación que se ha añadido.
//     */
//    @Override
//    public PruebasEvaluacion save(PruebasEvaluacion eva) {
//        this.evaluaciones.put(eva.getId(), eva);
//        return this.evaluaciones.get(eva.getId());
//    }
//
//
//    /**
//     * Devuelve todas la evaluaciones
//     * @return devuelve las evaluaciones
//     */
//    @Override
//    public List<PruebasEvaluacion> findAll() {
//        return new ArrayList<>(this.evaluaciones.values());
//    }
//
//
//
//
//    @Override
//    public PruebasEvaluacion create(PruebasEvaluacion eva) {
//        this.evaluaciones.put(eva.getId(), eva);
//        return this.evaluaciones.get(eva.getId());
//    }
//
//
//    /**
//     * Eliminar una evaluación
//     * @param eva que deseas eliminar.
//     * @return Devuelve la evaluación eliminada.
//     * @throws EvaluacionException si la evaluación no existe.
//     */
//    @Override
//    public PruebasEvaluacion delete(PruebasEvaluacion eva) throws EvaluacionException {
//        var existe = findById(eva.getId());
//        if (existe != null){
//            this.evaluaciones.remove(eva.getId());
//            return eva;
//        }
//        throw new EvaluacionException(colorize("La evaluación que deseas eliminar no existe..", Attribute.RED_TEXT()));
//    }
//
//
//    /**
//     * Busca una evaluación por su id
//     * @param id de la evaluación a buscar.
//     * @return Devuelve la evaluación buscada, null si no existe.
//     */
//    private PruebasEvaluacion findById(int id) {
//        for (var eva : evaluaciones.values()) {
//            if (eva.getId() == id) {
//                return eva;
//            }
//        }
//       return null;
//    }
}
