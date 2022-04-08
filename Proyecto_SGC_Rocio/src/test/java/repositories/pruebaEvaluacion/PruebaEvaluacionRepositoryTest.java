package repositories.pruebaEvaluacion;

import controllers.DataBaseManager;
import models.calificacion.Calificacion;
import models.pruebaEvaluacion.PruebasEvaluacion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PruebaEvaluacionRepositoryTest {
    private final PruebaEvaluacionRepository pruebaRepository = new PruebaEvaluacionRepository();

    private final PruebasEvaluacion pruebaTest = new PruebasEvaluacion("Examen de prueba",1);
    private final Calificacion calificacionTest = new Calificacion(1,1,5f);

    @BeforeEach
    void setUp() {
        try {
            pruebaRepository.clearAll(DataBaseManager.getInstance());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void save() {
        try {
            var returnTest = pruebaRepository.save(pruebaTest, DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertNotNull(returnTest.getId()),
                    () -> assertEquals(returnTest.getDescripcion(),pruebaTest.getDescripcion()),
                    () -> assertEquals(returnTest.getIdCategory(),pruebaTest.getIdCategory()),
                    () -> assertEquals(returnTest.getAverageGrade(),0.0),
                    () -> assertEquals(returnTest.getFailPercentages(),0.0),
                    () -> assertEquals(returnTest.getMaximumNote(),0.0),
                    () -> assertEquals(returnTest.getMinimumNote(),0.0),
                    () -> assertEquals(returnTest.getPassPercentages(),0.0)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void updateRatings() {
        try{
            var returnTest = pruebaRepository.save(pruebaTest, DataBaseManager.getInstance());
            List<Calificacion> list= new ArrayList<>();
            list.add(calificacionTest);
            var returnUpdateRatings = pruebaRepository.updateRatings(returnTest,list,DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertTrue(returnUpdateRatings.isPresent()),
                    () -> assertEquals(returnUpdateRatings.get().getId(),returnTest.getId()),
                    () -> assertEquals(returnUpdateRatings.get().getDescripcion(),returnTest.getDescripcion()),
                    () -> assertEquals(returnUpdateRatings.get().getIdCategory(),returnTest.getIdCategory()),
                    () -> assertEquals(returnUpdateRatings.get().getAverageGrade(),5),
                    () -> assertEquals(returnUpdateRatings.get().getFailPercentages(),0.0),
                    () -> assertEquals(returnUpdateRatings.get().getMaximumNote(),5),
                    () -> assertEquals(returnUpdateRatings.get().getMinimumNote(),5),
                    () -> assertEquals(returnUpdateRatings.get().getPassPercentages(),100)
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findAll() {
        try{
            pruebaRepository.save(pruebaTest, DataBaseManager.getInstance());
            var listTest = pruebaRepository.findAll(DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertEquals(listTest.size(),1),
                    () -> assertEquals(listTest.get(0).getDescripcion(),pruebaTest.getDescripcion()),
                    () -> assertEquals(listTest.get(0).getIdCategory(),pruebaTest.getIdCategory()),
                    () -> assertEquals(listTest.get(0).getAverageGrade(),0.0),
                    () -> assertEquals(listTest.get(0).getFailPercentages(),0.0),
                    () -> assertEquals(listTest.get(0).getMaximumNote(),0.0),
                    () -> assertEquals(listTest.get(0).getMinimumNote(),0.0),
                    () -> assertEquals(listTest.get(0).getPassPercentages(),0.0)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void delete() {
        try {
            var test = pruebaRepository.save(pruebaTest, DataBaseManager.getInstance());
            var returnDelete = pruebaRepository.delete(pruebaTest,DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertEquals(returnDelete.getId(), test.getId()),
                    () -> assertEquals(returnDelete.getDescripcion(), test.getDescripcion()),
                    () -> assertEquals(returnDelete.getIdCategory(), test.getIdCategory()),
                    () -> assertEquals(returnDelete.getAverageGrade(), test.getAverageGrade()),
                    () -> assertEquals(returnDelete.getFailPercentages(), test.getFailPercentages()),
                    () -> assertEquals(returnDelete.getMaximumNote(), test.getMaximumNote()),
                    () -> assertEquals(returnDelete.getMinimumNote(), test.getMinimumNote()),
                    () -> assertEquals(returnDelete.getPassPercentages(), test.getPassPercentages())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findById() {
        try {
            pruebaRepository.save(pruebaTest, DataBaseManager.getInstance());
            var returnTest = pruebaRepository.findById(pruebaTest.getId(),DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertTrue(returnTest.isPresent()),
                    () -> assertEquals(returnTest.get().getDescripcion(),pruebaTest.getDescripcion()),
                    () -> assertEquals(returnTest.get().getIdCategory(),pruebaTest.getIdCategory()),
                    () -> assertEquals(returnTest.get().getAverageGrade(),0.0),
                    () -> assertEquals(returnTest.get().getFailPercentages(),0.0),
                    () -> assertEquals(returnTest.get().getMaximumNote(),0.0),
                    () -> assertEquals(returnTest.get().getMinimumNote(),0.0),
                    () -> assertEquals(returnTest.get().getPassPercentages(),0.0)
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void clearAll() {
        try {
            pruebaRepository.clearAll(DataBaseManager.getInstance());
            assertEquals(pruebaRepository.findAll(DataBaseManager.getInstance()).size(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}