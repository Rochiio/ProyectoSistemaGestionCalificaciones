package models.alumno;

import java.util.HashMap;
import java.util.List;

public class AlumnoRepository implements IAlumnoRepository<Alumno> {
    private final HashMap<Integer, Alumno> lista= new HashMap<>();


    /**
     * Encontrar un alumno por su id.
     * @param id id del alumno a buscar.
     * @return el alumno si lo encuentra o null.
     */
    @Override
    public Alumno findById(int id) {
        return lista.get(id);
    }


    /**
     * Te devuelve todos los valores.
     * @return lista con todos los alumnos.
     */
    @Override
    public List<Alumno> findAll() {
        return (List<Alumno>) this.lista.values();
    }


    /**
     * Te devuelve si un alumno ya exite por dni ya que es único.
     * @param dni dni a buscar
     * @return si existe un alumno con ese dni o no.
     */
    @Override
    public Alumno findByDni(String dni) {
        Alumno exist=null;
        for (Integer key :this.lista.keySet()){
            if (this.lista.get(key).getDni().equals(dni)){
                exist = this.lista.get(key);
            }
        }
        return exist;
    }


    /**
     * Añadir alumno al repositorio.
     * @param value alumno a añadir.
     * @return el alumno añadido.
     */
    @Override
    public Alumno create(Alumno value) {
        this.lista.put(value.getId(),value);
        return this.lista.get(value.getId());
    }


    /**
     * Eliminar alumno.
     * @param id id del alumno a eliminar.
     * @return el alumno eliminado.
     */
    @Override
    public Alumno delete(int id) {
        return this.lista.remove(id);
    }


    /**
     * Actualizar datos de un alumno.
     * @param id id dle alumno a actualizar.
     * @param value nuevos valores del alumno.
     * @return el alumno actualizado.
     */
    @Override
    public Alumno update(int id, Alumno value) {
        var beforeData = this.lista.get(id);
            this.lista.get(id).setName((value.getName().isEmpty())? beforeData.getName(): value.getName());
            this.lista.get(id).setLastName((value.getLastName().isEmpty())? beforeData.getLastName(): value.getLastName());
            this.lista.get(id).setEmail((value.getEmail().isEmpty())? beforeData.getEmail(): value.getEmail());
            this.lista.get(id).setTelephone((value.getTelephone().isEmpty())? beforeData.getTelephone(): value.getTelephone());
            this.lista.get(id).setContinuousEvaluation(value.isContinuousEvaluation());
        return this.lista.get(id);
    }


    /**
     * Ver un alumno.
     * @param id id del alumno a ver.
     * @return el alumno si existe o null.
     */
    @Override
    public Alumno readAlumno(int id) {
        return this.lista.get(id);
    }
}
