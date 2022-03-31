package models.calificacion;

import models.alumno.Alumno;

import java.util.Date;
import java.util.Objects;

public class Calificacion {
    //Declaración de los atributos de la clase.
    private static int contador = 0;
    private int id;
    private Alumno student;
    private float note;
    private Date createdAt;


    /**
     * Constructor de calificaciones
     * @param alumno el alumno.
     * @param note nota del alumno.
     * @param localDateTime fecha de entrega de las notas.
     */
    public Calificacion(Alumno alumno, float note, Date localDateTime) {
        this.id = ++contador;
        this.student = alumno;
        this.note = note;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
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
