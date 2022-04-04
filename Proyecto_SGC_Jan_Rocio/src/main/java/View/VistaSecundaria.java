package View;

import controllers.AlumnoController;
import controllers.CategoriaController;
import controllers.EvaluacionController;
import exceptions.AlumnoException;
import exceptions.CategoriaException;
import exceptions.EvaluacionException;
import models.alumno.Alumno;
import models.categoria.Categoria;
import models.pruebaEvaluacion.PruebasEvaluacion;
import utils.Inputs;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.Optional;


public class VistaSecundaria {
    private final AlumnoController studentController;
    private final CategoriaController categoryController;
    private final EvaluacionController evaluationController;

    public VistaSecundaria(AlumnoController studentController, CategoriaController categoryController,EvaluacionController evaluacionController) {
        this.studentController = studentController;
        this.categoryController = categoryController;
        this.evaluationController = evaluacionController;
    }

    //Alumnos

    /**
     * Añadir un alumno.
     */
    public void addStudent() {
        Alumno newStudent;
            try {
                  newStudent = new Alumno(
                        Inputs.inputWithRegex("[0-9]{8}[a-zA-Z]","Dime el DNi del alumno [NNNNNNNNL]"),
                        Inputs.inputWithRegex("[A-Z][a-z]*","Dime el nombre del alumno"),
                        Inputs.inputWithRegex("[A-Z][a-z]*[ ][A-Z][a-z]*","Dime los apellidos del alumno"),
                        Inputs.inputWithRegex("[a-zA-Z,0-9]+[@][a-zA-Z,0-9]+[.][a-z]+","Dime el email del alumno"),
                        Inputs.inputWithRegex("[0-9]{3}[-][0-9]{6}","Dime el número de teléfono del alumno [NNN-NNNNNN]"),
                        (Inputs.inputWithRegex("^[0-1,$]$", "Tiene evaluación continua 1.Si 0.No").equals("1"))
                  );

                  var mostrar = studentController.add(newStudent);
                  System.out.println("Alumno añadido: " +mostrar);

            } catch (AlumnoException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Eliminar alumno.
     */
    public void deleteStudent() {
        var numberStudent = Inputs.inputWithRegex("[0-9]*","Dime el id del alumno a eliminar");
        Alumno mostrar = null;
            try {
                mostrar = studentController.delete(Integer.parseInt(numberStudent));
            } catch (AlumnoException | SQLException  e) {
                System.out.println(e.getMessage());
            }
        System.out.println("Alumno eliminado: "+ mostrar);
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
                (Inputs.inputStrings( "Tiene evaluación continua 1.Si 0.No").equals("1"))
        );

            try {
                var returnStudent = studentController.modifyStudent(id,modify);
                System.out.println("Modificado: " +returnStudent);
            } catch (AlumnoException | SQLException e) {
                System.out.println(e.getMessage());
            }
    }


    //Categorías


    /**
     * Añadir categoría.
     */
    public void addCategory() {
        Categoria newCategoria = new Categoria(
                Inputs.inputWithRegex("[a-zA-Z]*","Dime el nombre de la nueva categoría")
        );
            try{
                var show = categoryController.addCategory(newCategoria);
                System.out.println("Categoría añadida: " +show.toString());
            } catch (CategoriaException e) {
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
                } catch (CategoriaException e) {
                    System.out.println(e.getMessage());
                }
    }


    /**
     * Mostrar una categoría.
     */
    public void showCategory() {
        var numberIdCategory = Integer.parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id de la categoría a mostrar"));
        Categoria show;
            try {
                show = categoryController.showCategory(numberIdCategory);
                System.out.println(show.toString());
            } catch (CategoriaException e) {
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

        } catch (CategoriaException e) {
            System.out.println(e.getMessage());
        }
    }


    //Pruebas de evaluación


    /**
     * Muestra una prueba de evaluacion.
     */
    public void showEvaluation(){
        int option = Integer.parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id de la prueba d eevaluación a ver"));
            try{
                var showTest = this.evaluationController.showTestEvaluation(option);
                System.out.println(showTest.toString());
            }catch (EvaluacionException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Crea una prueba evaluación
     * TODO hacerlo
     */
    public void createEvaluation(){
        System.out.println("Crear una prueba de evaluación");
        /*Evaluacion eva = new Evaluacion(new PruebaEvaluacionRepository(Inputs.inputStrings("Dime la descripción: "),
                new Categoria(Inputs.inputStrings("Nombre de la valuación")),
                new CalificacionRepository<Calificacion>(new Alumno(
                        Inputs.inputStrings("Dime el DNi del alumno [NNNNNNNNL]"),
                        Inputs.inputStrings("Dime el nombre del alumno"),
                        Inputs.inputStrings("Dime los apellidos del alumno"),
                        Inputs.inputStrings("Dime el email del alumno"),
                        Inputs.inputStrings("Dime el número de teléfono del alumno [NNN-NNNNNN]"),
                        (Inputs.inputStrings( "Tiene evaluación continua 1.Si 0.No").equals("1")))), 5.6F)));


        this.evaluacionController.CreateEvaluation(eva);*/
    }


    /**
     * Elimina una prueba de evaluación.
     */
    public void deleteEvaluation() {
        var deleteTest = Inputs.inputStrings("Introduce la evaluación a eliminar: ");
            try{
                var res = this.evaluationController.deleteEvaluation(Integer.parseInt(deleteTest));
                System.out.println("Prueba de evaluación eliminada: " + res.toString());
            } catch (EvaluacionException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Mostrar todas las pruebas de evaluación.
     */
    public void showAllEvaluations() {
        try{
            var list = this.evaluationController.showAllTestEvaluation();

                for (PruebasEvaluacion test : list){
                    System.out.println(test.toString());
                }

        } catch (EvaluacionException e) {
            System.out.println(e.getMessage());
        }
    }
}
