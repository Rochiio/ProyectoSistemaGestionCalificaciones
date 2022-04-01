package models.calificacion;

import models.alumno.Alumno;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Calificacion {
    //Declaración de los atributos de la clase.
    private static int contador = 0;
    private int id;
    private Alumno student;
    private float note;
    private LocalDateTime createdAt;


    /**
     * Constructor de calificaciones
     * @param student el alumno.
     * @param note nota del alumno.
     */
    public Calificacion(Alumno student, float note) {
        this.id = ++contador;
        this.student = student;
        this.note = note;
        this.createdAt = LocalDateTime.now();
    }

    public Calificacion() {
        this.id = ++contador;
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


    public Alumno getStudent() {
        return student;
    }

    public void setStudent(Alumno student) {
        this.student = student;
    }

    public float getNota() {
        return note;
    }

    public void setNota(float nota) {
        this.note = nota;
    }



    //---------------------------------Equals and HashCode--------------------------------//



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calificacion that = (Calificacion) o;
        return id == that.id && Float.compare(that.note, note) == 0 && Objects.equals(student, that.student) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, note, createdAt);
    }


    @Override
    public String toString() {
        return "Calificacion{" +
                "id=" + id +
                ", alumno=" + student +
                ", nota=" + note +
                ", localDateTime=" + createdAt +
                '}';
    }


}
