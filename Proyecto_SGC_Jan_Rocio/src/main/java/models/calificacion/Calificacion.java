package models.calificacion;

import models.alumno.Alumno;
import utils.Format;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Calificacion {
    //Declaración de los atributos de la clase.
    private static int contador = 0;
    private final int id;
    private final Alumno student;
    private float nota;
    private LocalDateTime delivered;


    /**
     * Constructor de calificaciones
     * @param student el student.
     * @param nota nota del student.
     * @param localDateTime fecha de entrega de las notas.
     */
    public Calificacion(Alumno student, float nota, LocalDateTime localDateTime) {
        this.id = ++contador;
        this.student = student;
        this.nota = nota;
        this.delivered = localDateTime;
    }


    //-----------------------------Getter and Setter------------------------------------//

    public int getId() {
        return id;
    }

    public Alumno getStudent() {
        return student;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public LocalDateTime getDelivered() {
        return delivered;
    }

    public void setDelivered(LocalDateTime delivered) {
        this.delivered = delivered;
    }


    /**
     * Para pasar a markdown la calificación
     * @return String markdonw calificacion
     */
    public String toMarkdown() {
        return "## Calificacion{" +
                "* id=" + id + " * \n" +
                "* student=" + student + " * \n" +
                "* nota=" + Format.formatNote(nota) + " * \n" +
                "* localDateTime=" + delivered + " * \n" +
                "## }";
    }



    //---------------------------------Equals and HashCode--------------------------------//


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calificacion that = (Calificacion) o;
        return id == that.id && Float.compare(that.nota, nota) == 0 && Objects.equals(student, that.student) && Objects.equals(delivered, that.delivered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, nota, delivered);
    }


    @Override
    public String toString() {
        return "Calificacion{" +
                "id=" + id +
                ", student=" + student +
                ", nota=" + Format.formatNote(nota) +
                ", localDateTime=" + delivered +
                '}';
    }


}
