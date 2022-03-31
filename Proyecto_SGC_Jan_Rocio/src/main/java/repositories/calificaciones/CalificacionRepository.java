package repositories.calificaciones;

import exceptions.CalificacionException;
import models.calificacion.Calificacion;

import java.util.*;

public class CalificacionRepository implements ICalificacionRepository<Calificacion> {
    private final Map<Integer, Calificacion> qualifications = new HashMap<>();


    /**
     * Muestra la lista de calificaciones
     * @return Devuelve las calificaciones.
     */
    @Override
    public List<Calificacion> findAll() {
        return new ArrayList<>(this.qualifications.values());
    }

    /**
     * Buscar las calificaciones
     * @param ratings calificaciones a buscar.
     * @return Devuelve la calificación buscada.
     */
    @Override
    public Calificacion findByAlumno(Calificacion ratings) {
        var exist = findByAlumno(ratings);
        if (exist.getStudent().equals(ratings.getStudent())){
            return exist;
        }
        return null;
    }


    /**
     *  Busca la calificaciones por si id
     * @param id de la calificación a buscar.
     * @return Devuelve la calificación buscada
     */
    @Override
    public Calificacion findById(int id) {
        return this.qualifications.get(id);
    }


    /**
     * Actualiza una calificación
     * @param id de la calificaciones a actualizar
     * @param newCalificacion la nueva calificación
     * @return Devuelve la nueva calificación
     * @throws CalificacionException
     */
    @Override
    public Calificacion update(Integer id, Calificacion newCalificacion) throws CalificacionException {
        var exists = findById(id);
        if (exists.getId() == newCalificacion.getId()){
            this.qualifications.get(id).setNota(newCalificacion.getNota());
            this.qualifications.get(id).setCreatedAt(new Date());
        }else {
            throw new CalificacionException("La calificaciones con ese id no existe.");
        }
        return this.qualifications.get(id);
    }


    /**
     * Crear calificaión
     * @param item calificación que se desea crear.
     * @return Devuelve la calificación creada.
     */
    @Override
    public Calificacion create(Calificacion item) {
        this.qualifications.put(item.getId(), item);
        return this.qualifications.get(item.getId());
    }


    /**
     * Elimina una calificación.
     * @param item calificación a eliminar
     * @return Devuelve la calificación eliminada
     */
    @Override
    public Calificacion delete(Calificacion item) {
        return this.qualifications.remove(item.getId());
    }

}
