package models.pruebaEvaluacion;

import models.calificacion.Calificacion;
import models.categoria.Categoria;
import repositories.calificaciones.CalificacionRepository;
import repositories.calificaciones.ICalificacionRepository;
import utils.Format;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Objects;

public class PruebasEvaluacion {
    //Declaración de los atributos de la clase
    private static int contador = 0;
    private final int id;
    private final LocalDateTime date;
    private String descripcion;
    private float maximumNote;
    private float minimumNote;
    private float averageGrade;
    private float passPercentages;
    private float failPercentages;
    private Categoria category;
    private ICalificacionRepository<Calificacion> calificacionRepository;


    public PruebasEvaluacion(LocalDateTime date, String descripcion, float maximumNote, float minimumNote,
                             float averageGrade, float passPercentages, float failPercentages, Categoria category,
                             CalificacionRepository calificacionRepository) {
        this.id = ++contador;
        this.date = date;
        this.descripcion = descripcion;
        this.maximumNote = maximumNote;
        this.minimumNote = minimumNote;
        this.averageGrade = averageGrade;
        this.passPercentages = passPercentages;
        this.failPercentages = failPercentages;
        this.category = category;
        this.calificacionRepository = calificacionRepository;
    }


    public int getId() {
        return id;
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

    public Categoria getCategoriaRepository() {
        return category;
    }

    public void setCategoriaRepository(Categoria category) {
        this.category = category;
    }

    public ICalificacionRepository getCalificacionRepository() {
        return calificacionRepository;
    }

    public void setCalificacionRepository(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }


    @Override
    public String toString() {
    String result = "";
            try {
                result= "PruebasEvaluacion{" +
                        "id=" + id +
                        ", date=" + Format.formatDateMedium(date) +
                        ", descripcion='" + descripcion + '\'' +
                        ", maximumNote=" + maximumNote +
                        ", minimumNote=" + minimumNote +
                        ", averageGrade=" + averageGrade +
                        ", passPercentages=" + passPercentages +
                        ", failPercentages=" + failPercentages +
                        ", categoriaRepository=" + category +
                        ", calificacionRepository=" + calificacionRepository +
                        '}';
            } catch (ParseException e) {
                e.printStackTrace();
            }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PruebasEvaluacion that = (PruebasEvaluacion) o;
        return id == that.id && Float.compare(that.maximumNote, maximumNote) == 0 && Float.compare(that.minimumNote, minimumNote) == 0 && Float.compare(that.averageGrade, averageGrade) == 0 && Float.compare(that.passPercentages, passPercentages) == 0 && Float.compare(that.failPercentages, failPercentages) == 0 && Objects.equals(date, that.date) && Objects.equals(descripcion, that.descripcion) && Objects.equals(category, that.category) && Objects.equals(calificacionRepository, that.calificacionRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, descripcion, maximumNote, minimumNote, averageGrade, passPercentages, failPercentages, category, calificacionRepository);
    }
}



