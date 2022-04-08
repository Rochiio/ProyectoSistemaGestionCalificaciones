package storage.StorageJson.StorageCategoria;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import models.categoria.Categoria;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StorageCategoriaJson implements IStorageCategoria{
    private final Path currentRelativePath = Paths.get("");
    private final String ruta = currentRelativePath.toAbsolutePath().toString();
    private final String dir = ruta + File.separator + "backup";
    private final String categoriasFile = dir + File.separator + "categorias.json";


    /**
     * Constructor
     */
    public StorageCategoriaJson() {
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
     * Exportar
     * @param item a guardar
     */
    @Override
    public void save(List<Categoria> item) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        PrintWriter f = null;
        try {
            f = new PrintWriter(new FileWriter(categoriasFile));
            f.println(gson.toJson(item));
            System.out.println("Categorías exportadas correctamente.");

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
     * @return lista a añadir
     */
    @Override
    public List<Categoria> load() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Categoria> lista = new ArrayList<>();
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(categoriasFile));

            lista = gson.fromJson(reader, new TypeToken<List<Categoria>>() {
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
