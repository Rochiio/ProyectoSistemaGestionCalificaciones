import View.VistaPrincipal;
import View.VistaSecundaria;
import controllers.AlumnoController;
import controllers.CategoriaController;
import controllers.EvaluacionController;
import models.evaluacion.Evaluacion;
import repositories.alumno.AlumnoRepository;
import repositories.categoria.CategoriaRepository;
import repositories.pruebaEvaluacion.PruebaEvaluacionRepository;


public class Main {

    public static void main(String[] args){
        VistaPrincipal pantalla = new VistaPrincipal
                (new VistaSecundaria(new AlumnoController(new AlumnoRepository()),
                new CategoriaController(new CategoriaRepository()),new EvaluacionController(new Evaluacion(new PruebaEvaluacionRepository()))));


        pantalla.program();

    }
}
