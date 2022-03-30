package utils;

import com.diogonunes.jcolor.Attribute;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.colorize;

public class Menu {
    private static final Scanner in = new Scanner(System.in);

    /**
     * Menu principal del programa.
     * @return opción elegida.
     */
    public static int mainScreen() {
        String regex="^[0-5,$]$";
        String option;
            do {
                System.out.println(colorize("\nElige una opción", Attribute.TEXT_COLOR(200)));
                System.out.println(" -1 Gestión de Alumnos \n" +
                        " -2 Gestión pruebas de evaluación \n" +
                        " -3 Gestión de categorías de pruebas de evaluación \n" +
                        " -4 Mostar evaluación \n" +
                        " -5 Importar/Exportar evaluación \n" +
                        " -0 Salir");
                option=in.nextLine();
            }while(!option.matches(regex));
        return Integer.parseInt(option);
    }


    /**
     * Menu de gestión de alumnos del programa.
     * @return opción elegida.
     */
    public static int studentsScren(){
        String regex="^[0-6,$]$";
        String option;
            do {
                System.out.println(colorize("\nElige una opción", Attribute.TEXT_COLOR(200)));
                System.out.println(" -1 Añadir alumno \n" +
                        " -2 Eliminar alumno \n" +
                        " -3 Modificar datos alumno \n" +
                        " -4 Mostrar alumno \n" +
                        " -5 Cambiar evaluación continua alumno \n" +
                        " -6 Mostrar todos los alumnos \n" +
                        " -0 Salir");
                option=in.nextLine();
            }while(!option.matches(regex));
        return Integer.parseInt(option);

    }




}
