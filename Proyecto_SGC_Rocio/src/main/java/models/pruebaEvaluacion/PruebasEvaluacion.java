package models.pruebaEvaluacion;

import utils.Format;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class PruebasEvaluacion {
    private int id;
    private LocalDateTime date;
    private String descripcion;
    private int idCategory;
    private float maximumNote;
    private float averageGrade;
    private float minimumNote;
    private float passPercentages;
    private float failPercentages;
    private int idRatings;


    /**
     * Constructor
     * @param descripcion descripcion
     * @param idCategory categoria
     */
    public PruebasEvaluacion(String descripcion, int idCategory) {
        this.descripcion = descripcion;
        this.idCategory = idCategory;
    }


    /**
     * Constructor base de datos.
     * @param id id
     * @param date fecha
     * @param descripcion descripcion
     * @param maximumNote nota maxima
     * @param minimumNote nota minima
     * @param averageGrade nota media
     * @param passPercentages porcentaje de aprobados
     * @param failPercentages porcentaje de suspensos
     * @param idCategory categoria
     * @param idRatings calificaciones
     */
    public PruebasEvaluacion(int id, LocalDateTime date, String descripcion, int idCategory, float maximumNote, float averageGrade,
                             float minimumNote, float passPercentages, float failPercentages, int idRatings) {
        this.id = id;
        this.date = date;
        this.descripcion = descripcion;
        this.maximumNote = maximumNote;
        this.minimumNote = minimumNote;
        this.averageGrade = averageGrade;
        this.passPercentages = passPercentages;
        this.failPercentages = failPercentages;
        this.idCategory = idCategory;
        this.idRatings = idRatings;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() throws ParseException {
        return Format.formatDateMedium(date);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getMaximumNote() {
        return maximumNote;
    }

    public void setMaximumNote(float maximumNote) {
        this.maximumNote = maximumNote;
    }

    public float getMinimumNote() {
        return minimumNote;
    }

    public void setMinimumNote(float minimumNote) {
        this.minimumNote = minimumNote;
    }

    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    public float getPassPercentages() {
        return passPercentages;
    }

    public void setPassPercentages(float passPercentages) {
        this.passPercentages = passPercentages;
    }

    public float getFailPercentages() {
        return failPercentages;
    }

    public void setFailPercentages(float failPercentages) {
        this.failPercentages = failPercentages;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdRatings() {
        return idRatings;
    }

    public void setIdRatings(int idRatings) {
        this.idRatings = idRatings;
    }


    /**
     * Método para poner la clase en markdown para añadirlo a un fichero
     * @return String en forma markdown
     */
    public String toMarkdown(){
    String result = "";
        try {
            result= "# PruebasEvaluacion \n" +
                    "*id=" + id + "* \n" +
                    "<br>" +
                    "*date=" + Format.formatDateMedium(date) + "* \n" +
                    "<br>" +
                    "*descripcion='" + descripcion + "* \n" +
                    "<br>" +
                    "***maximumNote=" + Format.formatNote(maximumNote) + "*** \n" +
                    "<br>" +
                    "***minimumNote=" + Format.formatNote(minimumNote) + "*** \n" +
                    "<br>" +
                    "***averageGrade=" + Format.formatNote(averageGrade) + "*** \n" +
                    "<br>" +
                    "***passPercentages=" + Format.floatPercentParser(passPercentages) + "*** \n" +
                    "<br>" +
                    "***failPercentages=" + Format.floatPercentParser(failPercentages) + "*** \n" +
                    "<br>" +
                    "**categoriaRepository=" + idCategory + "** \n" +
                    "<br>" +
                    "**idRatings=" + idRatings+ "**";
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * Stirng formateado
     * @return string
     */
    public String toStringFormat() {
                return  "PruebasEvaluacion{" +
                        "id=" + id +
                        ", date=" + date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +
                        ", descripcion='" + descripcion + '\'' +
                        ", maximumNote=" + Format.formatNote(maximumNote) +
                        ", minimumNote=" + Format.formatNote(minimumNote) +
                        ", averageGrade=" + Format.formatNote(averageGrade) +
                        ", passPercentages=" + Format.floatPercentParser(passPercentages) +
                        ", failPercentages=" + Format.floatPercentParser(failPercentages) +
                        ", categoriaRepository=" + idCategory +
                        ", idRatings=" + idRatings +
                        '}';
    }


    @Override
    public String toString() {
        return "PruebasEvaluacion{" +
                    "id=" + id +
                    ", date=" + date +
                    ", descripcion='" + descripcion + '\'' +
                    ", maximumNote=" + maximumNote +
                    ", minimumNote=" + minimumNote +
                    ", averageGrade=" + averageGrade +
                    ", passPercentages=" + passPercentages +
                    ", failPercentages=" + failPercentages +
                    ", categoriaRepository=" + idCategory +
                    ", idRatings=" + idRatings +
                    '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PruebasEvaluacion that = (PruebasEvaluacion) o;
        return id == that.id && idCategory == that.idCategory && Float.compare(that.maximumNote, maximumNote) == 0 && Double.compare(that.averageGrade, averageGrade) == 0 && Double.compare(that.minimumNote, minimumNote) == 0 && Double.compare(that.passPercentages, passPercentages) == 0 && Double.compare(that.failPercentages, failPercentages) == 0 && idRatings == that.idRatings && date.equals(that.date) && descripcion.equals(that.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, descripcion, maximumNote, minimumNote, averageGrade, passPercentages, failPercentages, idCategory, idRatings);
    }
}



