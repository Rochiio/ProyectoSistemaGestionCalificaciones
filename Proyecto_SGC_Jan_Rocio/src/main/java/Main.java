import View.VistaPrincipal;
import View.VistaSecundaria;
import controllers.AlumnoController;
import controllers.CategoriaController;
import repositories.alumno.AlumnoRepository;
import repositories.categoria.CategoriaRepository;

public class Main {

    public static void main(String[] args){
        VistaPrincipal pantalla = new VistaPrincipal
                (new VistaSecundaria(new AlumnoController(new AlumnoRepository()),
                new CategoriaController(new CategoriaRepository())));


        pantalla.program();

    }
}
