package models.calificaciones;

import models.alumno.Alumno;

import java.time.LocalDateTime;
import java.util.Objects;

public class Calificacion {
    //Declaración de los atributos de la clase.
    private static int contador = 0;
    private int id;
    private Alumno alumno;
    private float nota;
    private LocalDateTime createdAt;


    /**
     * Constructor de calificaciones
     * @param alumno el alumno.
     * @param nota nota del alumno.
     * @param localDateTime fecha de entrega de las notas.
     */
    public Calificacion(Alumno alumno, float nota, LocalDateTime localDateTime) {
        this.id = ++contador;
        this.alumno = alumno;
        this.nota = nota;
        this.createdAt = localDateTime;
    }


    //-----------------------------Getter and Setter------------------------------------//

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Calificacion.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



    //---------------------------------Equals and HashCode--------------------------------//




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calificacion that = (Calificacion) o;
        return id == that.id && Float.compare(that.nota, nota) == 0 && Objects.equals(alumno, that.alumno) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alumno, nota, createdAt);
    }


    @Override
    public String toString() {
        return "Calificacion{" +
                "id=" + id +
                ", alumno=" + alumno +
                ", nota=" + nota +
                ", localDateTime=" + createdAt +
                '}';
    }
}
