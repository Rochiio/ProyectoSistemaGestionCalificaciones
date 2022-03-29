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
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 0: System.out.println(colorize("\nAdiós, hasta pronto 👋", Attribute.CYAN_TEXT()));
                break;

        }
    }


    /**
     * Menu de gestión de alumnos.
     */
    private void studentManagementMenu() {
        int option;
        do {
            option = Menu.studentsScren();
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
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 0: System.out.println(colorize("Cerrando el gestor de alumnos ....",Attribute.GREEN_BACK()));
                 break;
        }
    }




}
