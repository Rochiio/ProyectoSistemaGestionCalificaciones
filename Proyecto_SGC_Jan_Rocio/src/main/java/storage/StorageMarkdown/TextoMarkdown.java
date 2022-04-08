package storage.StorageMarkdown;

import models.calificacion.Calificacion;
import models.pruebaEvaluacion.PruebasEvaluacion;
import utils.Format;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;


public class TextoMarkdown implements IExport<PruebasEvaluacion> {
    private final Path currentRelativePath = Paths.get("");
    private final String ruta = currentRelativePath.toAbsolutePath().toString();
    private String pruebasEvaluacion;


    /**
     * Inicializar la ruta del fichero a donde el usuario elija.
     * @param file carpeta en la que va a querer guardar el fichero.
     */
    public void init(String file) {
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


    /**
     * Guardar el fichero en markdown.
     * @param item la prueba de evaluacion que se va a pasar a markdown.
     * @param ratings lista de calificaciones de la prueba
     */
    @Override
    public void save(PruebasEvaluacion item, List<Calificacion> ratings) {
        File fichero;
        BufferedWriter f = null;
        long startTime = System.nanoTime();
        try
        {
            fichero = new File(this.pruebasEvaluacion);
            System.out.println("Escribiendo en el fichero:"+ fichero.getAbsolutePath());


                f = new BufferedWriter(new FileWriter(fichero,false));

                f.write(item.toMarkdown());
                f.write("\n");
                for (Calificacion rating : ratings) {
                    f.write(rating.toMarkdown());
                }
                f.write("\n");


            long endTime = System.nanoTime() - startTime; // tiempo en que se ejecuta su método
            double seconds = (double)endTime / 1000000000.0;

            f.write("\nInforme generado el día " +Format.formatDateShort(LocalDateTime.now())+ " a las " +
                    LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")) + " en " +
                    NumberFormat.getNumberInstance(Locale.getDefault()).format(seconds) +" segundos.");

        } catch (IOException | ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                if (f != null)
                    f.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}
