package View;

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
import storage.IExport;
import utils.Inputs;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Optional;


public class VistaSecundaria {
    private final AlumnoController studentController;
    private final CategoriaController categoryController;
    private final EvaluacionController evaluationController;
    private final CalificacionRepository ratingsRepository;
    private final IExport<PruebasEvaluacion> textToMarkdown;

    /**
     * Constructor
     * @param studentController controlador de alumnos.
     * @param categoryController controlador de categorías.
     * @param evaluacionController controlador  de evaluaciones.
     */
    public VistaSecundaria(AlumnoController studentController, CategoriaController categoryController,
                           EvaluacionController evaluacionController,CalificacionRepository ratingsRepository,
                           IExport<PruebasEvaluacion> textToMarkdown) {
        this.studentController = studentController;
        this.categoryController = categoryController;
        this.evaluationController = evaluacionController;
        this.ratingsRepository = ratingsRepository;
        this.textToMarkdown = textToMarkdown;
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
        Optional<Alumno> show = Optional.empty();
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
        Optional<Alumno> show;
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
                System.out.println(showTest.toString());
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
                    Inputs.inputStrings("Dame una descripcíon de la prueba"),
                    addCategoryTest()
            );
                var created = evaluationController.createPruebaEvaluation(newTest,DataBaseManager.getInstance());

            String option = "";
                do {
                    var show = addAlumnsTest(created);
                        ratingsRepository.create(show,created.getId(),DataBaseManager.getInstance());
                        System.out.println("Calificacion creada: " + show);
                    Inputs.inputWithRegex("[0-1]","Quieres seguir añadiendo calificaciones 1.Si 0.No");
                }while(!option.equals("0"));

            var finalTest= evaluationController.addRatings(created, CalificacionRepository.getInstance(),DataBaseManager.getInstance());
            System.out.println("Prueba de evaluación creada: " + finalTest.get().toString());
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
        float value = 0;

        int numberStudent = Integer.parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id del alumno a añadir"));
            var alumn = studentController.showStudent(numberStudent);
                if (alumn.isPresent()){
                    value = Inputs.inputFloat("Dime la nota del alumno");
                }
        return new Calificacion(test.getId(),alumn.get().getId(),value);
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


    //------------------------------------IMPORTAR A MARKDONW-----------------------------------------------------------

    /**
     * Importar una prueba de evaluacion a markdown.
     */
    public void toMarkdown() {
        textToMarkdown.init(Inputs.inputStrings("Pon nombre a la carpeta en la que se va a guardar"));
            int numberTest = Integer.parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id de la prueba"));
                try {
                    var test = evaluationController.showTestEvaluation(numberTest,DataBaseManager.getInstance());
                    if (test.isPresent()){
                        var save = evaluationController.showTestEvaluation(Integer.parseInt
                                (Inputs.inputWithRegex("[0-9]*","Dime el id de la prueba de evaluacion a exportar")),
                                DataBaseManager.getInstance());
                        save.ifPresent(textToMarkdown::save);
                    }


                } catch (EvaluacionException | SQLException e) {
                    System.out.println(e.getMessage());
                }

    }








}
