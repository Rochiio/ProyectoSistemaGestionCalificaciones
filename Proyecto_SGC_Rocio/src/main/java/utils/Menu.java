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
                        " -4 Importar prueba de evaluación a markdown \n" +
                        " -5 Importar/Exportar prueba de evaluación a JSON \n" +
                        " -0 Salir");
                option=in.nextLine();
            }while(!option.matches(regex));
        return Integer.parseInt(option);
    }


    /**
     * Menu de gestión de alumnos del programa.
     * @return opción elegida.
     */
    public static int studentsScreen(){
        String regex="^[0-5,$]$";
        String option;
            do {
                System.out.println(colorize("\nElige una opción", Attribute.TEXT_COLOR(200)));
                System.out.println(" -1 Añadir alumno \n" +
                        " -2 Eliminar alumno \n" +
                        " -3 Modificar datos alumno \n" +
                        " -4 Mostrar alumno \n" +
                        " -5 Mostrar todos los alumnos \n" +
                        " -0 Salir");
                option=in.nextLine();
            }while(!option.matches(regex));
        return Integer.parseInt(option);
    }


    /**
     * Menu de gestión de categorías del programa.
     * @return opción elegida.
     */
    public static int categoryScreen(){
        String regex="^[0-4,$]$";
        String option;
        do {
            System.out.println(colorize("\nElige una opción", Attribute.TEXT_COLOR(200)));
            System.out.println(" -1 Añadir categoría \n" +
                    " -2 Modificar categoría \n" +
                    " -3 Mostrar categoría \n" +
                    " -4 Mostrar todas las categorías \n" +
                    " -0 Salir");
            option=in.nextLine();
        }while(!option.matches(regex));
        return Integer.parseInt(option);
    }


    /**
     * Menú de gestión de evaluationTest.
     * @return opción elegida.
     */
    public static int assessmentScreen(){
        String regex="^[0-3,$]$";
        String option;
        do {
            System.out.println(colorize("\nElige una opción", Attribute.TEXT_COLOR(200)));
            System.out.println(" -1 Mostrar prueba de evalución \n" +
                    " -2 Crear prueba de evaluación \n" +
                    " -3 Eliminar prueba de evaluación \n" +
                    " -4 Mostrar todas las pruebas de evaluación \n" +
                    " -0 Salir");
            option=in.nextLine();
        }while(!option.matches(regex));
        return Integer.parseInt(option);
    }


    /**
     * Menú de gestión de importar y exportar en json.
     * @return opción elegida.
     */
    public static int jsonScreen(){
        String regex="^[0-2,$]$";
        String option;
        do {
            System.out.println(colorize("\nElige una opción", Attribute.TEXT_COLOR(200)));
            System.out.println(" -1 Importar a Json \n" +
                    " -2 Exportar a Json \n" +
                    " -0 Salir");
            option=in.nextLine();
        }while(!option.matches(regex));
        return Integer.parseInt(option);
    }




}
