package View;

import compares.AlumnoOrdenAlfabeticoComparator;
import compares.AlumnoOrdenListaComparator;
import controllers.AlumnoController;
import controllers.CategoriaController;
import controllers.EvaluacionController;
import exceptions.AlumnoException;
import exceptions.CategoriaException;
import exceptions.EvaluacionException;
import models.alumno.Alumno;
import models.calificacion.Calificacion;
import models.categoria.Categoria;
import models.evaluacion.Evaluacion;
import repositories.alumno.AlumnoRepository;
import repositories.calificaciones.CalificacionRepository;
import repositories.evaluacion.EvaluacionRepository;
import repositories.pruebaEvaluacion.PruebaEvaluacionRepository;
import utils.Inputs;

import java.util.List;

import static java.lang.Integer.parseInt;

public class VistaSecundaria {
    private AlumnoController studentController = new AlumnoController(new AlumnoRepository());
    private final  CategoriaController categoriaController;
    private EvaluacionController evaluacionController = new EvaluacionController(new EvaluacionRepository());

    public VistaSecundaria(AlumnoController alumnoController, CategoriaController categoriaController) {
        this.studentController = alumnoController;
        this.categoriaController = categoriaController;

    }

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
     * TODO añadirle que no esté en la evaluacion para poderlo eliminar.
     */
    public void deleteStudent() {
        var numberStudent = Inputs.inputWithRegex("[0-9]*","Dime el id del alumno a eliminar");
        Alumno mostrar = null;
            try {
                mostrar = studentController.delete(parseInt(numberStudent));
            } catch (AlumnoException e) {
                e.printStackTrace();
            }
        System.out.println("Alumno eliminado: "+ mostrar);
    }


    /**
     * Mostrar todos los alumnos.
     */
    public void showAllStudents() {
        var option = Inputs.inputWithRegex("[1-2,$]$","Cómo quieres que se muestren\n -1.Orden lista\n -2.Orden Alfabético");
        try {
            var show = studentController.showAllStudents();
                if (option.equals("1")){
                    show.sort(new AlumnoOrdenListaComparator());
                }else{
                    show.sort(new AlumnoOrdenAlfabeticoComparator());
                }

            for (Alumno alumno: show) {
                System.out.println(alumno);
            }

        } catch (AlumnoException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * Modificar datos alumno.
     * TODO revisar, encontrar la expresion regular correcta
     */
    public void modifyStudent() {
        var id = parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id del alumno a modificar"));
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
            } catch (AlumnoException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Añadir categoría.
     */
    public void addCategory() {
        Categoria newCategoria = new Categoria(
                Inputs.inputWithRegex("[a-zA-Z]*","Dime el nombre de la nueva categoría")
        );
            try{
                var show = CategoriaController.addCategory(newCategoria);
                System.out.println("Categoría añadida: " +show.toString());
            } catch (CategoriaException e) {
                System.out.println(e.getMessage());
            }
    }


    /**
     * Modificar categoría.
     */
    public void modifyCategory() {
        var id = parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id de la categoría a modificar"));
            var newName = Inputs.inputStrings("Dime el nombre de la nueva categoría");

                try {
                    var returnCategory = CategoriaController.modifyCategory(id,newName);
                    System.out.println("Categoría modificada: " + returnCategory.toString());
                } catch (CategoriaException e) {
                    System.out.println(e.getMessage());
                }
    }


    /**
     * Mostrar una categoría.
     */
    public void showCategory() {
        var numberIdCategory = parseInt(Inputs.inputWithRegex("[0-9]*","Dime el id de la categoría a mostrar"));
        Categoria show;
            try {
                show = CategoriaController.showCategory(numberIdCategory);
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
            var show = CategoriaController.showAllCategories();
                for (Categoria category: show) {
                    System.out.println(category.toString());
                }
        } catch (CategoriaException e) {
            System.out.println(e.getMessage());
        }
    }




    //-------------------------------Evaluación-------------------------------//

    /**
     * Muestra todas las evaluaciones.
     */
    public void showEvaluation() throws EvaluacionException {
        try{
            List<Evaluacion> showTest = evaluacionController.showTestEvaluation();
            for (var test : showTest) {
                System.out.println(test);
            }
        }catch (EvaluacionException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Crea una evaluación
     */
    public void createEvaluation() throws EvaluacionException {
        System.out.println("Crear evaluación");
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
     * Elimina una evaluación
     */
    public void deleteEvaluation() {
        var deleteTest = Inputs.inputStrings("Introduce la evaluación a eliminar: ");
        try{
            var res = evaluacionController.deleteEvalluacion(Integer.parseInt(deleteTest));
            System.out.println("evaluación eliminada.");
            System.out.println(res);
        } catch (EvaluacionException e) {
            System.out.println(e.getMessage());
        }

    }


}
