package models.pruebaEvaluacion;

import models.categoria.Categoria;
import repositories.calificaciones.CalificacionRepository;

import java.util.Date;
import java.util.Objects;

public class PruebasEvaluacion {
    //Declaración de los atributos de la clase
    private static int contador = 0;
    private int id;
    private Date date;
    private String descripcion;
    private float maximumNote;
    private float minimumNote;
    private float averageGrade;
    private float passPercentages;
    private float failPercentages;
    private Categoria category;
    private CalificacionRepository calificacionRepository;


    public PruebasEvaluacion(Date date, String descripcion, float maximumNote, float minimumNote,
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

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        PruebasEvaluacion.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public CalificacionRepository getCalificacionRepository() {
        return calificacionRepository;
    }

    public void setCalificacionRepository(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
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
                ", categoriaRepository=" + category +
                ", calificacionRepository=" + calificacionRepository +
                '}';
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



