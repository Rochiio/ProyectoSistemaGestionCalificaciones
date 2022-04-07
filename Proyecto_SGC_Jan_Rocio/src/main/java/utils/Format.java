package utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class Format {

    /**
     * Formatear la salida de una fecha.
     * @param date fecha a parsear.
     * @return String de fecha formateado.
     */
    public static String formatDateShort(LocalDateTime date) throws ParseException {
        final Locale locale = new Locale("es", "ES");
        return date.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.SHORT).withLocale(locale));
    }

    /**
     * Formatear la salida de una fecha.
     * @param date fecha a parsear.
     * @return String de fecha formateado.
     */
    public static String formatDateMedium(LocalDateTime date) throws ParseException {
        final Locale locale = new Locale("es", "ES");
        return date.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale));
    }


    /**
     * Formatear salida de los double.
     * @param note nota a formatear la salida.
     * @return nota en forma de String.
     */
    public static String formatNote (float note){
        final Locale locale = new Locale("es", "ES");
        return DecimalFormat.getCurrencyInstance(locale).format(note);
    }
}
