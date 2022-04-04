package controllers;

import exceptions.AlumnoException;
import models.alumno.Alumno;
import repositories.alumno.IAlumnoRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

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
     * @throws SQLException si hay error al buscar dni en la base de daots.
     */
    public Alumno add(Alumno newStudent) throws AlumnoException, SQLException {
        Alumno returnStudent;
        var exist = studentRepository.findByDni(newStudent.getDni(),DataBaseManager.getInstance());
            if (exist.isEmpty()){
                returnStudent = studentRepository.create(newStudent,DataBaseManager.getInstance());
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
    public Optional<Alumno> delete(int numberStudent) throws AlumnoException, SQLException {
        Optional<Alumno> returnStudent;
        var exist = studentRepository.findById(numberStudent,DataBaseManager.getInstance());
            if (exist.isPresent()) {
                var numberEvaluation = studentRepository.hasEvaluationTest(numberStudent,DataBaseManager.getInstance());
                    if (numberEvaluation==0) {
                        returnStudent = studentRepository.delete(exist,DataBaseManager.getInstance());
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
    public Optional<Alumno> showStudent(int numberIdStudent) throws AlumnoException, SQLException {
       var returnStudent = studentRepository.findById(numberIdStudent,DataBaseManager.getInstance());
           if (returnStudent.isEmpty()) {
               throw new AlumnoException("Error: No existe un alumno con este id");
           }
        return returnStudent;
    }


    /**
     * Mostrar todos los alumnos.
     * @return lista de alumnos.
     * @throws AlumnoException si no hay ningún alumno añadido.
     * @throws SQLException si hay algún error al recoger todos los datos.
     */
    public List<Alumno> showAllStudents() throws AlumnoException, SQLException {
        var returnAllStudents = studentRepository.findAll(DataBaseManager.getInstance());
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
     * @throws SQLException si hay un error al obtener el id.
     */
    public Optional<Alumno> modifyStudent(int id, Alumno modify) throws AlumnoException, SQLException {
        Optional<Alumno> returnStudent;
        var exist = studentRepository.findById(id,DataBaseManager.getInstance());
            if (exist.isPresent()) {
                var newDniOkey = studentRepository.findByDni(modify.getDni(),DataBaseManager.getInstance());
                    if (newDniOkey.isEmpty() || newDniOkey.get().getId()==exist.get().getId()) {
                        returnStudent = studentRepository.update(id, modify,DataBaseManager.getInstance());
                    } else{
                        throw new AlumnoException("Ya existe un alumno con este dni");
                    }
            } else {
                throw new AlumnoException("No existe un alumno con este id");
            }
        return returnStudent;
    }

}
