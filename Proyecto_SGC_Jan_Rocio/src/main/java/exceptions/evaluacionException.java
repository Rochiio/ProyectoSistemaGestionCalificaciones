package exceptions;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class evaluacionException extends Exception{

    /**
     * Exception de evaluación
     * @param message menaje que queremos devolver de la exception
     */
    public evaluacionException(String message){
        super(colorize(message, Attribute.RED_TEXT()));
    }
}
