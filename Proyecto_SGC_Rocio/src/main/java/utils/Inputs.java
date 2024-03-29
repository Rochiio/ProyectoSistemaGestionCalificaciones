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


    /**
     * Entradas de strings.
     * @param sentence pregunta.
     * @return la respuesta del usuario.
     */
    public static String inputStrings(String sentence){
        System.out.println(colorize(sentence, Attribute.CYAN_TEXT()));
        return  in.nextLine();
    }


    /**
     * Entradas de double.
     * @param sentence pregunta.
     * @return la respuesta del usuario.
     */
    public static float inputFloat(String sentence){
        float result;
        do {
            try {
                System.out.println(colorize(sentence, Attribute.CYAN_TEXT()));
                result = in.nextFloat();
            } catch (Exception e) {
                result = -1;
                in.next();
            }
        }while (result<0);

        return  result;
    }







}
