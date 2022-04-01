package models.evaluacion;

import models.pruebaEvaluacion.PruebasEvaluacion;
import repositories.pruebaEvaluacion.IPruebaEvaluacionRepository;

import java.util.Objects;

public class Evaluacion {
    //Declaración de los atributos de la clase
    private static int contador = 0;
    private int id;
    private IPruebaEvaluacionRepository<PruebasEvaluacion> evaluationTestRepository;


    public Evaluacion(IPruebaEvaluacionRepository<PruebasEvaluacion> evaluationTestRepository) {
        this.id = ++contador;
        this.evaluationTestRepository = evaluationTestRepository;
    }

    public IPruebaEvaluacionRepository<PruebasEvaluacion> getEvaluationTestRepository() {
        return (IPruebaEvaluacionRepository<PruebasEvaluacion>) evaluationTestRepository;
    }

    public void setEvaluationTestRepository(IPruebaEvaluacionRepository<PruebasEvaluacion> evaluationTestRepository) {
        this.evaluationTestRepository =  evaluationTestRepository;
    }

    public int getContador() {
        return contador;
    }

    public int getId() {
        return id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluacion that = (Evaluacion) o;
        return Objects.equals(evaluationTestRepository, that.evaluationTestRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(evaluationTestRepository);
    }


    @Override
    public String toString() {
        return "Evaluacion{" +
                "evaluationTestRepository=" + evaluationTestRepository +
                '}';
    }


}
