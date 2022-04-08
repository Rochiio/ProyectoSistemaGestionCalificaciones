package storage.StorageJson.StorageCalificacion;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.calificacion.Calificacion;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StoregeCalificacionJson implements  IStorageCalificacion{
    private final Path currentRelativePath = Paths.get("");
    private final String ruta = currentRelativePath.toAbsolutePath().toString();
    private final String dir = ruta + File.separator + "backup";
    private final String calificacionFile = dir + File.separator + "calificaciones.json";


    /**
     * Constructor
     */
    public StoregeCalificacionJson() {
        init();
    }


    /**
     * Inicializar
     */
    private void init() {
        Path path = Paths.get(dir);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Exportar.
     * @param item a guardar
     */
    @Override
    public void save(List<Calificacion> item) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        PrintWriter f = null;
        try {
            f = new PrintWriter(new FileWriter(calificacionFile));
            f.println(gson.toJson(item));
            System.out.println("Calificaciones exportadas correctamente.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (f != null) {
                f.close();
            }
        }

    }


    /**
     * Importar
     * @return lista a añadir.
     */
    @Override
    public List<Calificacion> load() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Calificacion> lista = new ArrayList<>();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(calificacionFile));

            lista = gson.fromJson(reader, new TypeToken<List<Calificacion>>() {
            }.getType());

            System.out.println("Alumnos importados correctamente");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        return lista;
    }
}
