package exceptions;

import com.diogonunes.jcolor.Attribute;

import static com.diogonunes.jcolor.Ansi.colorize;

public class CategoriaException extends Exception{
    public CategoriaException(String mensaje) {
        super(colorize(mensaje, Attribute.RED_TEXT()));
    }
}
