package models.categoria;

import java.util.Objects;

public class Categoria {
    //Atributo de la clase.
    private static int contador = 0;
    private int id;
    private String nombre;


    /**
     * Constructor de categoría
     * @param nombre de la categoría.
     */
    public Categoria(String nombre) {
        this.id = ++contador;
        this.nombre = nombre;
    }


    //-----------------------------Getter and Setter------------------------------------//
    public static int getContador() {
        return contador;
    }

    public static void setContador(int contador) {
        Categoria.contador = contador;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    //---------------------------------Equals and HashCode--------------------------------//

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id == categoria.id && Objects.equals(nombre, categoria.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }


    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
