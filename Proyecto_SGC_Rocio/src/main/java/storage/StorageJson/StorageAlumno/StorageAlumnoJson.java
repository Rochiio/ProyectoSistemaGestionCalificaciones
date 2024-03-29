package storage.StorageJson.StorageAlumno;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.alumno.Alumno;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StorageAlumnoJson implements IStorageAlumnos{
    private final Path currentRelativePath = Paths.get("");
    private final String ruta = currentRelativePath.toAbsolutePath().toString();
    private final String dir = ruta + File.separator + "backup";
    private final String alumnosFile = dir + File.separator + "alumnos.json";


    /**
     * Constructor
     */
    public StorageAlumnoJson() {
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
     * Importar
     * @param item a guardar
     */
    @Override
    public void save(List<Alumno> item) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        PrintWriter f = null;
        try {
            f = new PrintWriter(new FileWriter(alumnosFile));
            f.println(gson.toJson(item));
            System.out.println("Alumnos exportados correctamente.");

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
    public List<Alumno> load() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Alumno> lista = new ArrayList<>();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(alumnosFile));

            lista = gson.fromJson(reader, new TypeToken<List<Alumno>>() {
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
