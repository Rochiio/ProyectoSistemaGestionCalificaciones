package exceptions;

import com.diogonunes.jcolor.Attribute;
import models.alumno.Alumno;

import static com.diogonunes.jcolor.Ansi.colorize;

public class AlumnoException extends Exception{
    /**
     * Exception alumno.
     * @param mensaje mensaje de excepcion.
     */
    public AlumnoException(String mensaje) {
        super(colorize(mensaje, Attribute.RED_TEXT()));
    }
}
