package repositories.categoria;

import controllers.DataBaseManager;
import models.categoria.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CategoriaRepositoryTest {
    private final CategoriaRepository categoriaRepository = new CategoriaRepository();

    private final Categoria categoriaTest = new Categoria("pruebaTest");

    @BeforeEach
    void setUp() {
        try {
            categoriaRepository.clearAll(DataBaseManager.getInstance());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findAll() {
        try{
            categoriaRepository.save(categoriaTest, DataBaseManager.getInstance());
            var listCategories = categoriaRepository.findAll(DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertEquals(listCategories.size(),1),
                    () -> assertEquals(listCategories.get(0).getName(),categoriaTest.getName())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findByName() {
        try{
            var category = categoriaRepository.save(categoriaTest, DataBaseManager.getInstance());
            var returnCategory = categoriaRepository.findByName(categoriaTest.getName(),DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertTrue(returnCategory.isPresent()),
                    () -> assertEquals(returnCategory.get().getId(),category.getId()),
                    () -> assertEquals(returnCategory.get().getName(),category.getName())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void save() {
        try {
            var returnCategory = categoriaRepository.save(categoriaTest, DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertNotNull(returnCategory),
                    () -> assertEquals(returnCategory.getName(),categoriaTest.getName())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void update() {
        try{
            var category = categoriaRepository.save(categoriaTest, DataBaseManager.getInstance());
            category.setName("nuevoNombre");
            var modifyCategory = categoriaRepository.update(category.getId(),category.getName(),DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertTrue(modifyCategory.isPresent()),
                    () -> assertEquals(modifyCategory.get().getId(),category.getId()),
                    () -> assertEquals(modifyCategory.get().getName(),category.getName()),
                    () -> assertEquals(modifyCategory.get().getName(),category.getName())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void findById() {
        try {
            categoriaRepository.save(categoriaTest, DataBaseManager.getInstance());
            var returnCategory = categoriaRepository.findById(categoriaTest.getId(),DataBaseManager.getInstance());

            Assertions.assertAll(
                    () -> assertTrue(returnCategory.isPresent()),
                    () -> assertEquals(returnCategory.get().getName(),categoriaTest.getName())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    void clearAll() {
        try {
            categoriaRepository.clearAll(DataBaseManager.getInstance());
            assertEquals(categoriaRepository.findAll(DataBaseManager.getInstance()).size(), 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}