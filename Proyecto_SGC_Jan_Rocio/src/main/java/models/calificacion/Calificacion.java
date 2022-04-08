package models.calificacion;

import utils.Format;

import java.time.LocalDateTime;
import java.util.Objects;

public class Calificacion {
    //Declaración de los atributos de la clase.
    private int id;
    private int id_Student;
    private float nota;
    private LocalDateTime delivered;


    /**
     * Constructor de calificaciones
     * @param id_Student id del student.
     * @param nota nota del student.
     */
    public Calificacion(int id,int id_Student, float nota) {
        this.id = id;
        this.id_Student = id_Student;
        this.nota = nota;
    }


    /**
     * Constructor calificaciones base de datos.
     * @param id id .
     * @param id_Student id del estudiante.
     * @param nota nota.
     * @param delivered fecha de entrega.
     */
    public Calificacion(int id, int id_Student, float nota, LocalDateTime delivered) {
        this.id = id;
        this.id_Student = id_Student;
        this.nota = nota;
        this.delivered = delivered;
    }


    //-----------------------------Getter and Setter------------------------------------//


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Student() {
        return id_Student;
    }

    public void setId_Student(int id_Student) {
        this.id_Student = id_Student;
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
     * @return String markdown calificacion
     */
    public String toMarkdown() {
        return "## Calificacion{" +
                "**id=" + id + "** \n" +
                "**id_student=" + id_Student + "** \n" +
                "**nota=" + Format.formatNote(nota) + "** \n" +
                "**localDateTime=" + delivered + "** \n" +
                "## }";
    }



    //---------------------------------Equals and HashCode--------------------------------//


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Calificacion that = (Calificacion) o;
        return id == that.id && id_Student == that.id_Student && Double.compare(that.nota, nota) == 0 && delivered.equals(that.delivered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_Student, nota, delivered);
    }

    @Override
    public String toString() {
        return "Calificacion{" +
                "id=" + id +
                ", id_student=" + id_Student +
                ", nota=" + nota +
                ", localDateTime=" + delivered +
                '}';
    }

    /**
     * El string con la nota formateada
     * @return String
     */
    public String toStringFormat() {
        return "Calificacion{" +
                "id=" + id +
                ", id_student=" + id_Student +
                ", nota=" + Format.formatNote(nota) +
                ", localDateTime=" + delivered +
                '}';
    }


}
