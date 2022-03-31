package exceptions;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class EvaluacionException extends Exception {
    /**
     * Exception de evaluación
     * @param message mensaje que queremos devolver de la exception
     */
    public EvaluacionException(String message){
        super(colorize(message, Attribute.RED_TEXT()));
    }
}
