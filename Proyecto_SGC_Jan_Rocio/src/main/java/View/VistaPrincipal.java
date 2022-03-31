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
            case 2:
                break;
            case 3: categoryManagementMenu();
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 0: System.out.println(colorize("\nAdiós, hasta pronto 👋", Attribute.CYAN_TEXT()));
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
            case 1: //secondaryView.addCategory();
                break;
            case 2: //secondaryView.modifyCategory();
                break;
            case 3: //secondaryView.showCategory();
                break;
            case 4: //secondaryView.showAllCategories();
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
            case 3: //secondaryView.modifyStudent();
                break;
            case 4: //secondaryView.showStudent();
                break;
            case 5: //secondaryView.showAllStudents();
                break;
            case 0: System.out.println(colorize("Cerrando el gestor de alumnos ....",Attribute.GREEN_BACK()));
                 break;
        }
    }



}
