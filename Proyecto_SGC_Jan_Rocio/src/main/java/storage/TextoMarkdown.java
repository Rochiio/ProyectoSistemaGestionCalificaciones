package storage;

import models.pruebaEvaluacion.PruebasEvaluacion;
import utils.Format;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDateTime;

public class TextoMarkdown implements IExport<PruebasEvaluacion> {
    private final Path currentRelativePath = Paths.get("");
    private final String ruta = currentRelativePath.toAbsolutePath().toString();
    private String pruebasEvaluacion;

    /**
     * Constructor
     */
    public TextoMarkdown(String file) {
        init(file);
    }

    private void init(String file) {
        String dir = ruta + File.separator + file;
        this.pruebasEvaluacion= dir + File.separator + "pruebaEvaluacion.md";
            Path path = Paths.get(dir);
                if (!Files.exists(path)) {
                    try {
                        Files.createDirectories(path);
                    } catch (IOException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
    }


    @Override
    public void save(PruebasEvaluacion item) {
        File fichero;
        BufferedWriter f = null;
        long startTime = System.nanoTime();
        try
        {
            fichero = new File(pruebasEvaluacion);
            System.out.println("Escribiendo en el fichero:"+fichero.getAbsolutePath());


            // Creamos el buffer y le asociamos el fichero
            // Usamos PrintWriter y no BufferedWriter porque tiene mejores metodos
            // Pero podríamos usar BufferedWriter y usar \n
            // al poner FileWriter -> true no sobrescribimos
            f = new BufferedWriter(new FileWriter(fichero,true));

            f.write(item.toMarkdown());
            long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
            f.write("Informe generado el " +Format.formatDateMedium(LocalDateTime.now())+ " en " +endTime+" segundos.");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (f != null)
                    f.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
