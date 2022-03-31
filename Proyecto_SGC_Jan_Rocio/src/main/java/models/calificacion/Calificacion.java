package models.calificacion;

import models.alumno.Alumno;

import java.util.Date;
import java.util.Objects;

public class Calificacion {
    //Declaración de los atributos de la clase.
    private static int contador = 0;
    private int id;
    private Alumno student;
    private float nota;
    private Date createdAt;


    /**
     * Constructor de calificaciones
     * @param student el student.
     * @param nota nota del student.
     * @param localDateTime fecha de entrega de las notas.
     */
    public Calificacion(Alumno student, float nota, Date localDateTime) {
        this.id = ++contador;
        this.student = student;
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

    public Alumno getStudent() {
        return student;
    }

    public void getStudent(Alumno alumno) {
        this.student = alumno;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
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
        return id == that.id && Float.compare(that.nota, nota) == 0 && Objects.equals(student, that.student) && Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, nota, createdAt);
    }


    @Override
    public String toString() {
        return "Calificacion{" +
                "id=" + id +
                ", student=" + student +
                ", nota=" + nota +
                ", localDateTime=" + createdAt +
                '}';
    }
}
