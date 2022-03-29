package controllers;

import exceptions.AlumnoException;
import models.alumno.Alumno;
import models.alumno.IAlumnoRepository;

public class AlumnoController {
    private final IAlumnoRepository<Alumno> studentRepository;


    /**
     * Constructor.
     * @param studentRepository repositorio.
     */
    public AlumnoController(IAlumnoRepository<Alumno> studentRepository) {
        this.studentRepository = studentRepository;
    }


    /**
     * Añadir estudiante.
     * @param newStudent estudiante a añadir.
     * @throws AlumnoException si existe ya un alumno con ese dni.
     */
    public Alumno add(Alumno newStudent) throws AlumnoException {
        Alumno returnStudent;
        var exist = studentRepository.findByDni(newStudent.getDni());
            if (exist==null){
                returnStudent = studentRepository.create(newStudent);
            }else{
               throw new AlumnoException("Ya existe un alumno con este DNI");
            }
      return returnStudent;
    }


    /**
     * Eliminar estudiante.
     * @param numberStudent id del estudiante a eliminar.
     * @return el alumno eliminado
     * @throws AlumnoException si no existe ningún alumno con ese id.
     */
    public Alumno delete(int numberStudent) throws AlumnoException {
        Alumno returnStudent;
        var exist = studentRepository.findById(numberStudent);
        if (exist!=null){
            returnStudent = studentRepository.delete(numberStudent);
        }else{
            throw new AlumnoException("No existe ningún alumno con este id");
        }
        return returnStudent;
    }
}
