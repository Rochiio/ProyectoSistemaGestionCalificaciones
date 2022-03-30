package models.pruebaEvaluacion;

import repositories.categoria.CategoriaRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class PruebaEvaluacion {
    //Declaración de los atributos de la clase
    private static int contador = 0;
    private int id;
    private Date fecha;
    private String descripcion;
    private CategoriaRepository categoriaRepository;
    private CalificacionRepository calificacionRepository;


    public PruebaEvaluacion(Date fecha, String descripcion, CategoriaRepository categoriaRepository, CalificacionRepository calificacionRepository) {
        this.id = ++contador;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.categoriaRepository = categoriaRepository;
        this.calificacionRepository = calificacionRepository;
    }

    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        PruebaEvaluacion.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaRepository getCategoriaRepository() {
        return categoriaRepository;
    }

    public void setCategoriaRepository(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CalificacionRepository getCalificacionRepository() {
        return calificacionRepository;
    }

    public void setCalificacionRepository(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PruebaEvaluacion that = (PruebaEvaluacion) o;
        return id == that.id && Objects.equals(fecha, that.fecha) && Objects.equals(descripcion, that.descripcion) && Objects.equals(categoriaRepository, that.categoriaRepository) && Objects.equals(calificacionRepository, that.calificacionRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fecha, descripcion, categoriaRepository, calificacionRepository);
    }


    @Override
    public String toString() {
        return "PruebaEvaluacion{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", categoriaRepository=" + categoriaRepository +
                ", calificacionRepository=" + calificacionRepository +
                '}';
    }
}
