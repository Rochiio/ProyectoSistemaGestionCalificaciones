package models.categoria;

import java.util.Objects;

public class Categoria {
    //Atributo de la clase.
    private int id;
    private String name;


    /**
     * Constructor de categoría
     * @param name de la categoría.
     */
    public Categoria(String name) {
        //this.id = ++contador;
        this.name = name;
    }


    /**
     * Constructor para las bases de datos.
     * @param id id de la categoría.
     * @param name nombre de la categoría.
     */
    public Categoria(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //-----------------------------Getter and Setter------------------------------------//

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String nombre) {
        this.name = nombre;
    }


    //---------------------------------Equals and HashCode--------------------------------//

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id == categoria.id && Objects.equals(name, categoria.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }


    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
