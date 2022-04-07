package repositories.alumno;

import controllers.DataBaseManager;
import models.alumno.Alumno;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AlumnoRepositoryTest {
    private final AlumnoRepository alumnosRepository = new AlumnoRepository();

    private final Alumno alumnoTest = new Alumno("12456789B","Paco","Perez Santos",
            "pacopaquito@paco.com","999-999999",true,true);

    @BeforeEach
    void setUp() {
        try {
            alumnosRepository.clearAll(DataBaseManager.getInstance());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findById() {
        try {
            alumnosRepository.create(alumnoTest, DataBaseManager.getInstance());
            var returnStudent = alumnosRepository.findById(alumnoTest.getId(),DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertTrue(returnStudent.isPresent()),
                    () -> assertEquals(returnStudent.get().getDni(),alumnoTest.getDni()),
                    () -> assertEquals(returnStudent.get().getName(),alumnoTest.getName()),
                    () -> assertEquals(returnStudent.get().getLastName(),alumnoTest.getLastName()),
                    () -> assertEquals(returnStudent.get().getEmail(),alumnoTest.getEmail()),
                    () -> assertEquals(returnStudent.get().getTelephone(),alumnoTest.getTelephone()),
                    () -> assertEquals(returnStudent.get().getStudentAvailable(),alumnoTest.getStudentAvailable()),
                    () -> assertEquals(returnStudent.get().getContinuousEvaluation(),alumnoTest.getContinuousEvaluation())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findAll() {
        try{
            alumnosRepository.create(alumnoTest, DataBaseManager.getInstance());
            var listStudent = alumnosRepository.findAll(DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertEquals(listStudent.size(),1),
                    () -> assertEquals(listStudent.get(0).getDni(),alumnoTest.getDni()),
                    () -> assertEquals(listStudent.get(0).getName(),alumnoTest.getName()),
                    () -> assertEquals(listStudent.get(0).getLastName(),alumnoTest.getLastName()),
                    () -> assertEquals(listStudent.get(0).getEmail(),alumnoTest.getEmail()),
                    () -> assertEquals(listStudent.get(0).getTelephone(),alumnoTest.getTelephone()),
                    () -> assertEquals(listStudent.get(0).getStudentAvailable(),alumnoTest.getStudentAvailable()),
                    () -> assertEquals(listStudent.get(0).getContinuousEvaluation(),alumnoTest.getContinuousEvaluation())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findByDni() {
        try{
            alumnosRepository.create(alumnoTest, DataBaseManager.getInstance());
            var returnStudent = alumnosRepository.findByDni(alumnoTest.getDni(),DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertTrue(returnStudent.isPresent()),
                    () -> assertEquals(returnStudent.get().getDni(),alumnoTest.getDni()),
                    () -> assertEquals(returnStudent.get().getName(),alumnoTest.getName()),
                    () -> assertEquals(returnStudent.get().getLastName(),alumnoTest.getLastName()),
                    () -> assertEquals(returnStudent.get().getEmail(),alumnoTest.getEmail()),
                    () -> assertEquals(returnStudent.get().getTelephone(),alumnoTest.getTelephone()),
                    () -> assertEquals(returnStudent.get().getStudentAvailable(),alumnoTest.getStudentAvailable()),
                    () -> assertEquals(returnStudent.get().getContinuousEvaluation(),alumnoTest.getContinuousEvaluation())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void isAvailableStudent() {
        try{
            alumnosRepository.create(alumnoTest, DataBaseManager.getInstance());
            var returnStudentBoolean = alumnosRepository.isAvailableStudent(alumnoTest.getId(),DataBaseManager.getInstance());

            assertEquals(returnStudentBoolean,alumnoTest.getStudentAvailable());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void create() {
        try {
            var returnStudent = alumnosRepository.create(alumnoTest, DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertNotNull(returnStudent.getId()),
                    () -> assertEquals(returnStudent.getDni(),alumnoTest.getDni()),
                    () -> assertEquals(returnStudent.getName(),alumnoTest.getName()),
                    () -> assertEquals(returnStudent.getLastName(),alumnoTest.getLastName()),
                    () -> assertEquals(returnStudent.getEmail(),alumnoTest.getEmail()),
                    () -> assertEquals(returnStudent.getTelephone(),alumnoTest.getTelephone()),
                    () -> assertEquals(returnStudent.getRegistrationDate(),alumnoTest.getRegistrationDate()),
                    () -> assertEquals(returnStudent.getStudentAvailable(),alumnoTest.getStudentAvailable()),
                    () -> assertEquals(returnStudent.getContinuousEvaluation(),alumnoTest.getContinuousEvaluation())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void delete() {
        try {
            var student = alumnosRepository.create(alumnoTest, DataBaseManager.getInstance());
            var returnDelete = alumnosRepository.delete(alumnoTest,DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertEquals(returnDelete.getId(),student.getId()),
                    () -> assertEquals(returnDelete.getDni(),student.getDni()),
                    () -> assertEquals(returnDelete.getName(),student.getName()),
                    () -> assertEquals(returnDelete.getLastName(),student.getLastName()),
                    () -> assertEquals(returnDelete.getEmail(),student.getEmail()),
                    () -> assertEquals(returnDelete.getTelephone(),student.getTelephone()),
                    () -> assertEquals(returnDelete.getRegistrationDate(),student.getRegistrationDate()),
                    () -> assertEquals(returnDelete.getStudentAvailable(),student.getStudentAvailable()),
                    () -> assertEquals(returnDelete.getContinuousEvaluation(),student.getContinuousEvaluation())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void update() {
        try{
            var student = alumnosRepository.create(alumnoTest, DataBaseManager.getInstance());
            student.setEmail("nuevoemail@email.com");
            var modifyStudent = alumnosRepository.update(student.getId(),student,DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertTrue(modifyStudent.isPresent()),
                    () -> assertEquals(modifyStudent.get().getId(),student.getId()),
                    () -> assertEquals(modifyStudent.get().getDni(),student.getDni()),
                    () -> assertEquals(modifyStudent.get().getName(),student.getName()),
                    () -> assertEquals(modifyStudent.get().getLastName(),student.getLastName()),
                    () -> assertEquals(modifyStudent.get().getEmail(),student.getEmail()),
                    () -> assertEquals(modifyStudent.get().getTelephone(),student.getTelephone()),
                    () -> assertEquals(modifyStudent.get().getStudentAvailable(),student.getStudentAvailable()),
                    () -> assertEquals(modifyStudent.get().getContinuousEvaluation(),student.getContinuousEvaluation())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void clearAll(){
        try {
            alumnosRepository.clearAll(DataBaseManager.getInstance());
            assertEquals(alumnosRepository.findAll(DataBaseManager.getInstance()).size(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}