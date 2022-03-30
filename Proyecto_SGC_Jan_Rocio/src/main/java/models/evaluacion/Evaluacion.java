package models.evaluacion;

import repositories.evaluacion.EvaluacionRepository;

import java.util.Objects;

public class Evaluacion {
    //Declaración de los atributos de la clase
    private static int contador = 0;
    private int id;
    private float notaMaxima;
    private float notaMinima;
    private float notaMedia;
    private float porcentajeAprobado;
    private float porcentajeSuspenso;
    private EvaluacionRepository evaluacionRepository;


    /**
     * Constructor de evaluacion
     * @param notaMaxima del alumno
     * @param notaMinima el alumno
     * @param notaMedia media del alumno
     * @param porcentajeAprobado de todos los alumnos
     * @param porcentajeSuspenso de todos los alumnos
     * @param evaluacionRepository
     */
    public Evaluacion(float notaMaxima, float notaMinima, float notaMedia, float porcentajeAprobado, float porcentajeSuspenso, EvaluacionRepository evaluacionRepository) {
        this.id = ++contador;
        this.notaMaxima = notaMaxima;
        this.notaMinima = notaMinima;
        this.notaMedia = notaMedia;
        this.porcentajeAprobado = porcentajeAprobado;
        this.porcentajeSuspenso = porcentajeSuspenso;
        this.evaluacionRepository = evaluacionRepository;
    }



    public Evaluacion(float porcentajeSuspenso) {
        this.porcentajeSuspenso = porcentajeSuspenso;
    }

    public EvaluacionRepository getEvaluacionRepository() {
        return evaluacionRepository;
    }

    public void setEvaluacionRepository(EvaluacionRepository evaluacionRepository) {
        this.evaluacionRepository = evaluacionRepository;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Evaluacion.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getNotaMaxima() {
        return notaMaxima;
    }

    public void setNotaMaxima(float notaMaxima) {
        this.notaMaxima = notaMaxima;
    }

    public float getNotaMinima() {
        return notaMinima;
    }

    public void setNotaMinima(float notaMinima) {
        this.notaMinima = notaMinima;
    }

    public float getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(float notaMedia) {
        this.notaMedia = notaMedia;
    }

    public float getPorcentajeAprobado() {
        return porcentajeAprobado;
    }

    public void setPorcentajeAprobado(float porcentajeAprobado) {
        this.porcentajeAprobado = porcentajeAprobado;
    }

    public float getPorcentajeSuspenso() {
        return porcentajeSuspenso;
    }

    public void setPorcentajeSuspenso(float porcentajeSuspenso) {
        this.porcentajeSuspenso = porcentajeSuspenso;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluacion that = (Evaluacion) o;
        return id == that.id && Float.compare(that.notaMaxima, notaMaxima) == 0 && Float.compare(that.notaMinima, notaMinima) == 0 && Float.compare(that.notaMedia, notaMedia) == 0 && Float.compare(that.porcentajeAprobado, porcentajeAprobado) == 0 && Float.compare(that.porcentajeSuspenso, porcentajeSuspenso) == 0 && Objects.equals(evaluacionRepository, that.evaluacionRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, notaMaxima, notaMinima, notaMedia, porcentajeAprobado, porcentajeSuspenso, evaluacionRepository);
    }


    @Override
    public String toString() {
        return "Evaluacion{" +
                "id: " + id +
                ", NotaMaxima: " + notaMaxima +
                ", NotaMinima: " + notaMinima +
                ", NotaMedia: " + notaMedia +
                ", PorcentajeAprobado: " + porcentajeAprobado +
                ", PorcentajeSuspenso:" + porcentajeSuspenso +
                ", EvaluacionRepository: " + evaluacionRepository +
                '}';
    }
}
