package exceptions;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class CalificacionException extends Exception{
    public CalificacionException(String message){
        super(colorize(message, Attribute.RED_TEXT()));
    }
}
