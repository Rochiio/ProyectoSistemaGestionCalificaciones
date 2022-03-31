package models.evaluacion;

import repositories.pruebaEvaluacion.IEvaluacionRepository;
import models.pruebaEvaluacion.PruebasEvaluacion;

import java.util.Objects;

public class Evaluacion {
    //Declaración de los atributos de la clase
    private IEvaluacionRepository<PruebasEvaluacion> evaluationTestRepository;


    public Evaluacion(IEvaluacionRepository<PruebasEvaluacion> evaluationTestRepository) {
        this.evaluationTestRepository = evaluationTestRepository;
    }

    public IEvaluacionRepository<PruebasEvaluacion> getEvaluationTestRepository() {
        return evaluationTestRepository;
    }

    public void setEvaluationTestRepository(IEvaluacionRepository<PruebasEvaluacion> evaluationTestRepository) {
        this.evaluationTestRepository = evaluationTestRepository;
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
