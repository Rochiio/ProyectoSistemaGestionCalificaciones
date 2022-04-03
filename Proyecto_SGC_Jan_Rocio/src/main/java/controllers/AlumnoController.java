package controllers;

import exceptions.AlumnoException;
import models.alumno.Alumno;
import repositories.alumno.IAlumnoRepository;

import java.util.List;

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
     * @throws AlumnoException si no existe ningún alumno con ese id o el alumno está añadido en pruebas de evaluación.
     */
    public Alumno delete(int numberStudent) throws AlumnoException {
        Alumno returnStudent;
        var exist = studentRepository.findById(numberStudent);
        if (exist!=null) {
            var numberEvaluation = studentRepository.hasEvaluationTest(numberStudent);
            if (numberEvaluation==0) {
                returnStudent = studentRepository.delete(numberStudent);
            }else {
                throw new AlumnoException("Error: No se puede eliminar a un alumno presente en una prueba de evaluación");
            }
        }else throw new AlumnoException("Error: No existe ningún alumno con este id");
        return returnStudent;
    }


    /**
     * Mostrar un alumno.
     * @param numberIdStudent id del alumno.
     * @return el alumno.
     * @throws AlumnoException si no existe un alumno con ese id.
     */
    public Alumno showStudent(int numberIdStudent) throws AlumnoException {
       var returnStudent = studentRepository.findById(numberIdStudent);
           if (returnStudent==null) {
               throw new AlumnoException("Error: No existe un alumno con este id");
           }
        return returnStudent;
    }


    /**
     * Mostrar todos los alumnos.
     * @return lista de alumnos.
     * @throws AlumnoException si no hay ningún alumno añadido.
     */
    public List<Alumno> showAllStudents() throws AlumnoException {
        var returnAllStudents = studentRepository.findAll();
            if (returnAllStudents.size() == 0){
                throw new AlumnoException("Error: No hay ningún alumno");
            }
        return returnAllStudents;
    }


    /**
     * Modificar datos alumno.
     * @param id id del alumno.
     * @param modify nuevos datos.
     * @return el alumno modificado.
     * @throws AlumnoException si no eiste un alumno con ese id.
     */
    public Alumno modifyStudent(int id, Alumno modify) throws AlumnoException {
        Alumno returnStudent;
        var exist = studentRepository.findById(id);
            if (exist != null) {
                var newDniOkey = studentRepository.findByDni(modify.getDni());
                    if (newDniOkey == null || newDniOkey.getId()==exist.getId()) {
                        returnStudent = studentRepository.update(id, modify);
                    } else{
                        throw new AlumnoException("Ya existe un alumno con este dni");
                    }
            } else {
                throw new AlumnoException("No existe un alumno con este id");
            }
        return returnStudent;
    }

}
