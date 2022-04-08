package repositories.calificaciones;

import controllers.DataBaseManager;
import models.calificacion.Calificacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CalificacionRepositoryTest {
    private final CalificacionRepository calificacionRepository = new CalificacionRepository();

    private final Calificacion calificacionTest = new Calificacion(1,1,8.5f);

    @BeforeEach
    void setUp() {
        try {
            calificacionRepository.clearAll(DataBaseManager.getInstance());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findAll() {
        try{
            calificacionRepository.create(calificacionTest,calificacionTest.getId(),DataBaseManager.getInstance());
            var listRatings = calificacionRepository.findAll(DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertEquals(listRatings.size(),1),
                    () -> assertEquals(listRatings.get(0).getId(),calificacionTest.getId()),
                    () -> assertEquals(listRatings.get(0).getId_Student(),calificacionTest.getId_Student()),
                    () -> assertEquals(listRatings.get(0).getNota(),calificacionTest.getNota())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findByAlumno() {
        try{
            calificacionRepository.create(calificacionTest,calificacionTest.getId(), DataBaseManager.getInstance());
            var returnRating = calificacionRepository.findByAlumno(calificacionTest.getId_Student(),DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertTrue(returnRating.isPresent()),
                    () -> assertEquals(returnRating.get().getId(),calificacionTest.getId()),
                    () -> assertEquals(returnRating.get().getId_Student(),calificacionTest.getId_Student()),
                    () -> assertEquals(returnRating.get().getNota(),calificacionTest.getNota())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findById() {
        try {
            calificacionRepository.create(calificacionTest,calificacionTest.getId(), DataBaseManager.getInstance());
            var listRatings = calificacionRepository.findById(calificacionTest.getId(),DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertEquals(listRatings.size(),1),
                    () -> assertEquals(listRatings.get(0).getId(),calificacionTest.getId()),
                    () -> assertEquals(listRatings.get(0).getId_Student(),calificacionTest.getId_Student()),
                    () -> assertEquals(listRatings.get(0).getNota(),calificacionTest.getNota())

            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void create() {
        try {
            var returnRating = calificacionRepository.create(calificacionTest,calificacionTest.getId(), DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertEquals(returnRating.getId(),calificacionTest.getId()),
                    () -> assertEquals(returnRating.getId_Student(),calificacionTest.getId_Student()),
                    () -> assertEquals(returnRating.getNota(),calificacionTest.getNota())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void delete() {
        try {
            var rating = calificacionRepository.create(calificacionTest,calificacionTest.getId(), DataBaseManager.getInstance());
            var returnDelete = calificacionRepository.delete(calificacionTest.getId(),DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertEquals(returnDelete,rating.getId())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void clearAll() {
        try {
            calificacionRepository.clearAll(DataBaseManager.getInstance());

            assertEquals(calificacionRepository.findAll(DataBaseManager.getInstance()).size(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}