package repositories.evaluacion;

import models.categoria.Categoria;

public class PruebasEvaluacion {
    private int contador = 0;
    private final int id;
    private String date;
    private String description;
    private Categoria category;
    //TODO falta poner aqui repositorio de calificaciones.


    public PruebasEvaluacion(String date, String description, Categoria category) {
        this.id=++contador;
        this.date = date;
        this.description = description;
        this.category = category;
    }
}
