package utils;

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
    public static String formatDateOne(LocalDateTime date) throws ParseException {
        final Locale locale = new Locale("es", "ES");
        String pattern = "dd/MM/yyyy";
        return date.format(DateTimeFormatter
                .ofLocalizedDate(FormatStyle.valueOf(pattern)).withLocale(locale));
    }
}
