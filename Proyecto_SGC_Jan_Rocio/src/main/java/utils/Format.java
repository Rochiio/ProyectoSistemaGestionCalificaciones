package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {

    /**
     * Parsear el String a date.
     * @param format fecha a parsear.
     * @return date
     */
    public static Date formatDate(String format) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(format);
    }
}
