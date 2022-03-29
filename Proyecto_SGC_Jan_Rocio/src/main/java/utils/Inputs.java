package utils;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Inputs {
    private final static Scanner in = new Scanner(System.in);

    /**
     * Entradas mediante expresiones regulares.
     * @param regex expresión regular.
     * @param sentence frase sobre lo que se necesita.
     * @return lo que ha escrito el usuario.
     */
    public static String inputWithRegex(String regex,String sentence){
        String option;
            do {
                System.out.println(colorize(sentence, Attribute.CYAN_TEXT()));
                option=in.nextLine();
            }while(!option.matches(regex));
        return option;
    }







}
