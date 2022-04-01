package models.pruebaEvaluacion;

import models.calificacion.Calificacion;
import models.categoria.Categoria;
import repositories.calificaciones.CalificacionRepository;

import java.time.LocalDateTime;
import java.util.Objects;

public class PruebasEvaluacion {
    //Declaración de los atributos de la clase
    private static int contador = 0;
    private int id;
    private LocalDateTime date;
    private String descripcion;
    private float maximumNote;
    private float minimumNote;
    private float averageGrade;
    private float passPercentages;
    private float failPercentages;
    private Categoria category;
    private CalificacionRepository<Calificacion> calificacionRepository;


    public PruebasEvaluacion() {
        this.id = ++contador;
    }

    public PruebasEvaluacion(String descripcion, Categoria category, CalificacionRepository<Calificacion> calificacionRepository) {
        this.id = ++contador;
        this.date = LocalDateTime.now();
        this.descripcion = descripcion;
        this.maximumNote = 5.6f;
        this.minimumNote = 5.0f;
        this.averageGrade = 5.5f;
        this.passPercentages = 5.3f;
        this.failPercentages = 3.3f;
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

    public CalificacionRepository<Calificacion> getCalificacionRepository() {
        return calificacionRepository;
    }

    public void setCalificacionRepository(CalificacionRepository<Calificacion> calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }


    /**
     * Calcula la nota media
     * @return Devuelve la nota media
     */
    /*private float averageScore(){
        float score;
        var todo = this.calificacionRepository.findAll();
        for (Calificacion calificacion: this.calificacionRepository.findAll()
             ) {

        }
    }*/

    //TODO HACER METODO PARA OBJETENER EL MAXIMO
    private float maximumScore() {
        return 3.00F;
    }


    //TODO HACER METODO PARA OBJETENER EL minimo
    private float minimumScore() {
        return 3.00F;
    }


    /**
     * Calcula el porcentaje de aprobados
     * @return Devuelve el porcentaje de aprobados.
     */
    private float percentageApproved(){
        float score = 0;
        var notas = calificacionRepository.findAll();
        for (var note: notas) {
            if (note.getNota() >= 5){
                score = note.getNota();
            }
        }
        return score;
    }

    /**
     * Calcula el procentaje de suspensos.
     * @return Devuelve el porcentaje de suspensos
     */
    private float failureRate(){
        float score = 0;
        var notas = calificacionRepository.findAll();
        for (var note: notas) {
            if (note.getNota() < 5){
                score = note.getNota();
            }
        }
        return score;
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



