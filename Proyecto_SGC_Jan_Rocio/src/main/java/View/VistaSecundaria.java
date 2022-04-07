package View;

import compares.CalificacionComparator;
import controllers.AlumnoController;
import controllers.CategoriaController;
import controllers.DataBaseManager;
import controllers.EvaluacionController;
import exceptions.AlumnoException;
import exceptions.CategoriaException;
import exceptions.EvaluacionException;
import models.alumno.Alumno;
import models.calificacion.Calificacion;
import models.categoria.Categoria;
import models.pruebaEvaluacion.PruebasEvaluacion;
import repositories.calificaciones.CalificacionRepository;
import storage.StorageJson.StorageAlumno.IStorageAlumnos;
import storage.StorageJson.StorageCalificacion.IStorageCalificacion;
import storage.StorageJson.StorageCategoria.IStorageCategoria;
import storage.StorageJson.StoragePruebaEva.IStoragePruebas;
import storage.StorageMarkdown.IExport;
import utils.Inputs;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Optional;

import static com.diogonunes.jcolor.Ansi.colorize;


public class VistaSecundaria {
    private final AlumnoController studentController;
    private final CategoriaController categoryController;
    private final EvaluacionController evaluationController;
    private final CalificacionRepository ratingsRepository;
    private final IExport<PruebasEvaluacion> textToMarkdown;
    private final IStorageAlumnos storageStudents;
    private final IStorageCategoria storageCategory;
    private final IStoragePruebas storageTest;
    private final IStorageCalificacion storageRatings;

    /**
     * Constructor
     * @param studentController controlador de alumnos.
     * @param categoryController controlador de categorías.
     * @param evaluacionController controlador  de evaluaciones.
     * @param storageStudents json de alumnos.
     * @param storageCategory json de categorias.
     * @param storageTest json de pruebas.
     * @param storageRatings json de calificaciones.
     */
    public VistaSecundaria(AlumnoController studentController, CategoriaController categoryController,
                           EvaluacionController evaluacionController, CalificacionRepository ratingsRepository,
                           IExport<PruebasEvaluacion> textToMarkdown, IStorageAlumnos storageStudents,
                           IStorageCategoria storageCategory, IStoragePruebas storageTest,
                           IStorageCalificacion storageRatings) {

        this.studentController = studentController;
        this.categoryController = categoryController;
        this.evaluationController = evaluacionController;
        this.ratingsRepository = ratingsRepository;
        this.textToMarkdown = textToMarkdown;
        this.storageStudents = storageStudents;
        this.storageCategory = storageCategory;
        this.storageTest = storageTest;
        this.storageRatings = storageRatings;
    }


    //------------------------------------------------ALUMNOS----------------------------------------------------------


    /**
     * Añadir un alumno.
     */
    public void addStudent() {
        Alumno newStudent;
            try {
                  newStudent = new Alumno(
                        Inputs.inputWithRegex("[0-9]{8}[a-zA-Z]","Dime el DNi del alumno [NNNNNNNNL]"),
                        Inputs.inputWithRegex("[a-zA-Z][a-z]*","Dime el nombre del alumno"),
                        Inputs.inputWithRegex("[a-zA-Z][a-z]*[ ][A-Z][a-z]*","Dime los apellidos del alumno"),
                        Inputs.inputWithRegex("[a-zA-Z,0-9]+[@][a-zA-Z,0-9]+[.][a-z]+","Dime el email del alumno"),
                        Inputs.inputWithRegex("[0-9]{3}[-][0-9]{6}","Dime el número de teléfono del alumno [NNN-NNNNNN]"),
                        (Inputs.inputWithRegex("^[0-1,$]$", "Está disponible el alumno 1.Si 0.No (Obligatorio)").equals("1")),
                        (Inputs.inputWithRegex("^[0-1,$]$", "Tiene evaluación continua 1.Si 0.No").equals("1"))
                  );

                  var show = studentController.add(newStudent);
                  System.out.println("Alumno añadido: " + show.toString());

            } catch (AlumnoException | SQLException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Eliminar alumno.
     */
    public void deleteStudent() {
        var numberStudent = Inputs.inputWithRegex("[0-9]*","Dime el id del alumno a eliminar");
        Alumno show=null;
            try {
                show = studentController.delete(Integer.parseInt(numberStudent));
            } catch (AlumnoException | SQLException  e) {
                System.out.println(e.getMessage());
            }
        System.out.println("Alumno eliminado: "+ show.toString());
    }


    /**
     * Mostrar un estudiante.
     */
    public void showStudent() {
        var numberIdStudent = Integer.parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id del alumno a mostrar"));
        Alumno show;
            try {
                show = studentController.showStudent(numberIdStudent);
                System.out.println(show.toString());
            } catch (AlumnoException | SQLException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Mostrar todos los alumnos.
     */
    public void showAllStudents() {
        var option = Inputs.inputWithRegex("[1-2,$]$","Cómo quieres que se muestren\n -1.Orden lista\n -2.Orden Alfabético");
        try {
            var show = studentController.showAllStudents();
                if (option.equals("1")){
                    show.stream().sorted(Comparator.comparing(Alumno::getId)).forEach(System.out::println);
                }else{
                    show.stream().sorted(Comparator.comparing(Alumno::getName)).forEach(System.out::println);
                }
        } catch (AlumnoException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Modificar datos alumno.
     * TODO revisar, encontrar la expresion regular correcta
     */
    public void modifyStudent() {
        var id = Integer.parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id del alumno a modificar"));
        var modify = new Alumno(
                Inputs.inputStrings("Dime el DNi del alumno [NNNNNNNNL]"),
                Inputs.inputStrings("Dime el nombre del alumno"),
                Inputs.inputStrings("Dime los apellidos del alumno"),
                Inputs.inputStrings("Dime el email del alumno"),
                Inputs.inputStrings("Dime el número de teléfono del alumno [NNN-NNNNNN]"),
                (Inputs.inputWithRegex("^[0-1,$]$", "Está disponible el alumno 1.Si 0.No (Obligatorio)").equals("1")),
                (Inputs.inputWithRegex("^[0-1,$]$", "Tiene evaluación continua 1.Si 0.No (Obligatorio)").equals("1"))
        );

            try {
                var returnStudent = studentController.modifyStudent(id,modify);
                System.out.println("Modificado: " + returnStudent.toString());
            } catch (AlumnoException | SQLException e) {
                System.out.println(e.getMessage());
            }
    }


    //--------------------------------------------CATEGORIAS-----------------------------------------------------------


    /**
     * Añadir categoría.
     */
    public void addCategory() {
        Categoria newCategoria = new Categoria(
                Inputs.inputWithRegex("[a-zA-Z]*","Dime el nombre de la nueva categoría")
        );
            try{
                var show = categoryController.addCategory(newCategoria);
                System.out.println("Categoría añadida: " + show.toString());
            } catch (CategoriaException | SQLException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Modificar categoría.
     */
    public void modifyCategory() {
        var id = Integer.parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id de la categoría a modificar"));
            var newName = Inputs.inputStrings("Dime el nombre de la nueva categoría");

                try {
                    var returnCategory = categoryController.modifyCategory(id,newName);
                    System.out.println("Categoría modificada: " + returnCategory.toString());
                } catch (CategoriaException | SQLException e) {
                    System.out.println(e.getMessage());
                }
    }


    /**
     * Mostrar una categoría.
     */
    public void showCategory() {
        var numberIdCategory = Integer.parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id de la categoría a mostrar"));
        Optional<Categoria> show;
            try {
                show = categoryController.showCategory(numberIdCategory);
                System.out.println(show.toString());
            } catch (CategoriaException | SQLException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Mostrar todas las categorías.
     */
    public void showAllCategories() {
        try {
            var show = categoryController.showAllCategories();

                for (Categoria category: show) {
                    System.out.println(category.toString());
                }

        } catch (CategoriaException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //----------------------------------------PRUEBAS DE EVALUACION----------------------------------------------------


    /**
     * Muestra una prueba de evaluacion.
     */
    public void showEvaluation(){
        int option = Integer.parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id de la prueba de evaluación a ver"));
            try{
                var showTest = this.evaluationController.showTestEvaluation(option, DataBaseManager.getInstance());
                if (showTest.isPresent()) {
                    var showRating = this.ratingsRepository.findById(showTest.get().getId(),DataBaseManager.getInstance());

                    System.out.println("Prueba evaluación: " + showTest);
                    System.out.println("Calificaciones: \n");
                        showRating.sort(new CalificacionComparator());
                        showRating.forEach(System.out::println);
                }

            }catch (EvaluacionException | SQLException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Crea una prueba de evaluación.
     * TODO revisar.
     */
    public void createPruebaEvaluation(){
        try {
            PruebasEvaluacion newTest = new PruebasEvaluacion(
                    Inputs.inputStrings("Dame una descripción de la prueba"),
                    addCategoryTest()
            );
                var created = evaluationController.createPruebaEvaluation(newTest,DataBaseManager.getInstance()); //Prueba de evaluación añadida

            int option;
                do {
                    var show = addAlumnsTest(created);
                        ratingsRepository.create(show,created.getId(),DataBaseManager.getInstance());
                        System.out.println("Calificacion creada: " + show);

                    option= Integer.parseInt(Inputs.inputWithRegex("[0-1]","Quieres seguir añadiendo calificaciones 1.Si 0.No"));
                }while(option!=0);

            var finalRatings = ratingsRepository.findById(created.getId(),DataBaseManager.getInstance()); //Todas las calificaciones añadidas para esa prueba de evaluacion.
            var finalTest= evaluationController.addRatings(created, finalRatings,DataBaseManager.getInstance());
            System.out.println("Prueba de evaluación creada: " + finalTest.get().toStringFormat());

        } catch (CategoriaException | SQLException | EvaluacionException | ParseException | AlumnoException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Elegir la categoría para la prueba.
     * @return id de la categoría
     * @throws CategoriaException si no existe dicha categoría.
     * @throws SQLException si hay problemas con la abse de daots.
     */
    private int addCategoryTest() throws CategoriaException, SQLException {
        int numberIdCategory = Integer.parseInt(Inputs.inputWithRegex("[0-9]*",
                "Dime el id de la categoría a añadir a la prueba"));
        var exist = categoryController.showCategory(numberIdCategory);
        if (exist.isEmpty()){
            throw new CategoriaException("La categoría buscada no existe");
        }
       return exist.get().getId();
    }


    /**
     * Añadir alumnos y sus calificaciones a una prueba de evaluación.
     * @param test Prueba de evaluacion a la que se van a añadir.
     * @return la nueva calificacion.
     * @throws AlumnoException Si no existe el alumno.
     * @throws SQLException problemas con la base de datos.
     */
    private Calificacion addAlumnsTest(PruebasEvaluacion test) throws AlumnoException, SQLException {
        boolean studentCorrect = false;
        float value = 0;
        Optional<Alumno> finalAlumn = Optional.empty();

        do {
            int numberStudent = Integer.parseInt(Inputs.inputWithRegex("[0-9]*", "Dime el id del alumno a añadir"));
            var alumn = studentController.showStudent(numberStudent);
                if(alumn.getContinuousEvaluation()) {
                    value = Inputs.inputFloat("Dime la nota del alumno");
                        alumn.setStudentAvailable(false);
                            finalAlumn = studentController.modifyStudent(alumn.getId(), alumn);
                                studentCorrect = true;
                }else{
                    System.out.println(colorize("No puedes añadir un alumno sin evaluación continua"));
                }
        }while(!studentCorrect);
        return new Calificacion(test.getId(),finalAlumn.get().getId(),value);
    }



    /**
     * Elimina una prueba de evaluación.
     */
    public void deleteEvaluation() {
        var deleteTest = Inputs.inputStrings("Introduce la prueba de evaluación a eliminar: ");
            try{
                var res = this.evaluationController.deleteEvaluation(Integer.parseInt(deleteTest), DataBaseManager.getInstance());
                this.ratingsRepository.delete(res.getId(),DataBaseManager.getInstance());
                System.out.println("Prueba de evaluación eliminada: " + res);
            } catch (EvaluacionException | SQLException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Mostrar todas las pruebas de evaluación.
     */
    public void showAllEvaluations() {
        try{
            var list = this.evaluationController.showAllTestEvaluation(DataBaseManager.getInstance());

                list.forEach(System.out::println);

        } catch (EvaluacionException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //---------------------------------------------MARKDONW-----------------------------------------------------------

    /**
     * Importar una prueba de evaluacion a markdown.
     */
    public void toMarkdown() {
        textToMarkdown.init(Inputs.inputStrings("Pon nombre a la carpeta en la que se va a guardar"));
            int numberTest = Integer.parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id de la prueba"));
                try {
                    var test = evaluationController.showTestEvaluation(numberTest,DataBaseManager.getInstance());
                    if (test.isPresent()){
                        var ratings = ratingsRepository.findById(test.get().getIdRatings(), DataBaseManager.getInstance());

                        textToMarkdown.save(test.get(),ratings);
                    }

                } catch (EvaluacionException | SQLException e) {
                    System.out.println(e.getMessage());
                }

    }



    //--------------------------------------------------JSON-----------------------------------------------------------


    /**
     * Importar a JSON
     */
    public void importJson() {

    }


    /**
     * Exportar a JSON
     */
    public void exportJson() {
        try {

            //Exportamos todos los alumnos que haya en la base de datos.
                var listStudents = studentController.showAllStudents();
                storageStudents.save(listStudents);
            //Exportamos todas las categorías que haya en la base de datos.
                var listCategories = categoryController.showAllCategories();
                storageCategory.save(listCategories);
            //Exportamos todas las pruebas de evaluación
                var listTest = evaluationController.showAllTestEvaluation(DataBaseManager.getInstance());
                storageTest.save(listTest);
            //Exportamos todas las calificaciones
                var listRatings = ratingsRepository.findAll(DataBaseManager.getInstance());
                storageRatings.save(listRatings);

        } catch (AlumnoException | SQLException | CategoriaException | EvaluacionException e) {
            System.out.println(e.getMessage());
        }
    }



}
