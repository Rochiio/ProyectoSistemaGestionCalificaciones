package View;

import controllers.AlumnoController;
import exceptions.AlumnoException;
import models.alumno.Alumno;
import repositories.alumno.AlumnoRepository;
import utils.Format;
import utils.Inputs;

import java.text.ParseException;

public class VistaSecundaria {
    private final AlumnoController studentController = new AlumnoController(new AlumnoRepository());


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
                        (Inputs.inputWithRegex("^[0-1,$]$", "Tiene evaluación continua 1.Si 0.No").equals("1")),
                        Format.formatDate(Inputs.inputWithRegex("^([0-2][0-9]|3[0-1])(\\/|-)(0[1-9]|1[0-2])\\2(\\d{4})$",
                                "Dime la fecha de matriculación del alumno"))
                  );

                  var mostrar = studentController.add(newStudent);
                  System.out.println("Alumno añadido: " +mostrar);

            } catch (ParseException e) {
                System.out.println("Error: Fecha incorrecta");
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
                mostrar = studentController.delete(Integer.parseInt(numberStudent));
            } catch (AlumnoException e) {
                e.printStackTrace();
            }
        System.out.println("Alumno eliminado: "+ mostrar);
    }
}
