import View.VistaPrincipal;
import View.VistaSecundaria;
import controllers.AlumnoController;
import controllers.CategoriaController;
import controllers.DataBaseManager;
import controllers.EvaluacionController;
import models.evaluacion.Evaluacion;
import repositories.alumno.AlumnoRepository;
import repositories.calificaciones.CalificacionRepository;
import repositories.categoria.CategoriaRepository;
import repositories.pruebaEvaluacion.PruebaEvaluacionRepository;
import storage.StorageJson.StorageAlumno.StorageAlumnoJson;
import storage.StorageJson.StorageCalificacion.StoregeCalificacionJson;
import storage.StorageJson.StorageCategoria.StorageCategoriaJson;
import storage.StorageJson.StoragePruebaEva.StoragePruebaEvaluacionJson;
import storage.StorageMarkdown.TextoMarkdown;
import utils.ApplicationProperties;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class Main {

    public static void main(String[] args){
        checkServer();
        //initData();

        VistaPrincipal pantalla = new VistaPrincipal
                (new VistaSecundaria(new AlumnoController(new AlumnoRepository()),
                new CategoriaController(new CategoriaRepository()),
                        new EvaluacionController(new Evaluacion(PruebaEvaluacionRepository.getInstance())),
                        CalificacionRepository.getInstance(),new TextoMarkdown(), new StorageAlumnoJson(),
                        new StorageCategoriaJson(), new StoragePruebaEvaluacionJson(), new StoregeCalificacionJson()));


        pantalla.program();

    }



    private static void checkServer() {
        System.out.println("Comprobamos la conexión al Servidor BD");
        DataBaseManager controller = DataBaseManager.getInstance();
        try {
            controller.open();
            Optional<ResultSet> rs = controller.select("SELECT 'HOLA'");
            if (rs.isPresent()) {
                rs.get().next();
                controller.close();
                System.out.println("Conexión con la Base de Datos realizada con éxito");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
            System.exit(1);
        }
    }


    private static void initData() {
        ApplicationProperties properties = new ApplicationProperties();
        boolean init = Boolean.parseBoolean(properties.readProperty("database.initdata"));
        if (init) {
            System.out.println("Iniciamos los datos de ejemplo de la Base de Datos");
            DataBaseManager controller = DataBaseManager.getInstance();
            String dataPath = "sql" + File.separator + "init-db.sql";
            try {
                var sqlFile = Main.class.getClassLoader().getResource(dataPath).getPath();
                System.out.println(dataPath);
                controller.open();
                controller.initData(sqlFile, false);
                controller.close();
                System.out.println("Datos inicializados con éxito");
            } catch (SQLException e) {
                System.err.println("Error al conectar al servidor de Base de Datos: " + e.getMessage());
                System.exit(1);
            } catch (FileNotFoundException e) {
                System.err.println("Error al leer el fichero de datos iniciales: " + e.getMessage());
                System.exit(1);
            }
        }
    }


}
