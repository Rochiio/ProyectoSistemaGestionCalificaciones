package View;

import com.diogonunes.jcolor.Attribute;
import utils.Menu;

import static com.diogonunes.jcolor.Ansi.colorize;

public class VistaPrincipal {
    VistaSecundaria secondaryView;

    public VistaPrincipal(VistaSecundaria secondaryView) {
        this.secondaryView = secondaryView;
    }


    /**
     * Repeticion program
     */
    public void program(){
        int option;
        do {
            option = Menu.mainScreen();
            switchPrincipalMenu(option);
        }while(option != 0);
    }


    /**
     * Switch del menu principal.
     * @param option opcion elegida.
     */
    private void switchPrincipalMenu(int option) {
        switch (option){
            case 1: studentManagementMenu();
                break;
            case 2: assessmentManagementMenu();
                break;
            case 3: categoryManagementMenu();
                break;
            case 4: secondaryView.toMarkdown();
                break;
            case 5: jsonManagementMenu();
                break;
            case 0: System.out.println(colorize("\nAdiós, hasta pronto 👋", Attribute.CYAN_TEXT()));
                break;

        }
    }


    /**
     * Menu de gestión de importacion y exportacion de Json.
     */
    private void jsonManagementMenu() {
        int option;
        do {
            option = Menu.jsonScreen();
            switchJsonMenu(option);
        }while(option != 0);
    }


    /**
     * Switch del menu de gestión de Json.
     * @param option opcion elegida.
     */
    private void switchJsonMenu(int option) {
        switch (option) {
            case 1: secondaryView.importJson();
                break;
            case 2: secondaryView.exportJson();
                break;
            case 0: System.out.println(colorize("Cerrando el gestor de Json ....",Attribute.GREEN_BACK()));
                break;
        }
    }


    /**
     * Menu de gestión de categorías.
     */
    private void categoryManagementMenu() {
        int option;
        do {
            option = Menu.categoryScreen();
            switchCategoryMenu(option);
        }while(option != 0);
    }


    /**
     * Switch del menu de gestión de categorías.
     * @param option opcion elegida.
     */
    private void switchCategoryMenu(int option) {
        switch (option) {
            case 1: secondaryView.addCategory();
                break;
            case 2: secondaryView.modifyCategory();
                break;
            case 3: secondaryView.showCategory();
                break;
            case 4: secondaryView.showAllCategories();
                break;
            case 0: System.out.println(colorize("Cerrando el gestor de categorías ....",Attribute.GREEN_BACK()));
                break;
        }
    }


    /**
     * Menu de gestión de alumnos.
     */
    private void studentManagementMenu() {
        int option;
        do {
            option = Menu.studentsScreen();
            switchStudentMenu(option);
        }while(option != 0);
    }


    /**
     * Switch del menu de gestión de estudiantes.
     * @param option opcion elegida.
     */
    private void switchStudentMenu(int option) {
        switch (option) {
            case 1: secondaryView.addStudent();
                break;
            case 2: secondaryView.deleteStudent();
                break;
            case 3: secondaryView.modifyStudent();
                break;
            case 4: secondaryView.showStudent();
                break;
            case 5: secondaryView.showAllStudents();
                break;
            case 0: System.out.println(colorize("Cerrando el gestor de alumnos ....",Attribute.GREEN_BACK()));
                 break;
        }
    }


    /**
     * Menú gestión evaluación
     */
    private void assessmentManagementMenu(){
        int option;
        do {
            option = Menu.assessmentScreen();
            assessmentMenu(option);
        }while(option != 0);
    }

    /**
     * Switch del menu de gestión de evaluación.
     * @param option opción seleccionada.
     */
    private void assessmentMenu(int option){
        switch (option) {
            case 1: secondaryView.showEvaluation();
                break;
            case 2: secondaryView.createPruebaEvaluation();
                break;
            case 3: secondaryView.deleteEvaluation();
                break;
            case 4: secondaryView.showAllEvaluations();
                break;
            case 0: System.out.println(colorize("Cerrando el gestor de pruebas de evaluación ....",Attribute.GREEN_BACK()));
                break;
        }
    }



}
