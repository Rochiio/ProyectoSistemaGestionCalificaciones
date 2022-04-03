package models.evaluacion;

import repositories.pruebaEvaluacion.IEvaluacionRepository;
import models.pruebaEvaluacion.PruebasEvaluacion;

import java.util.List;
import java.util.Objects;

public class Evaluacion {
    //Declaración de los atributos de la clase
    private final IEvaluacionRepository<PruebasEvaluacion> evaluationTestRepository;

    /**
     * Constructor.
     * @param evaluationTestRepository repositorio de pruebas de evaluación.
     */
    public Evaluacion(IEvaluacionRepository<PruebasEvaluacion> evaluationTestRepository) {
        this.evaluationTestRepository = evaluationTestRepository;
    }


    /**
     * Devuelve una lista con todas las pruebas de evaluacion.
     * @return lista.
     */
    public List<PruebasEvaluacion> findAllEvaluationTestRepository() {
        return this.evaluationTestRepository.findAll();
    }


    /**
     * Buscar una prueba de evaluación por id
     * TODO mirar porque ni tiene sentido, cada prueba va a tener un id diferente siempre.
     * @param id identificador.
     * @return Prueba de evaluacion o null
     */
    public PruebasEvaluacion findByIdEvaluationTestRepository(int id) {
        return this.evaluationTestRepository.findById(id);
    }


    /**
     * Añadir una prueba de evaluación al repositorio.
     * @param newTest nueva prueba a añadir.
     * @return La prueba.
     */
    public PruebasEvaluacion createEvaluationTest(PruebasEvaluacion newTest) {
        return this.evaluationTestRepository.save(newTest);
    }


    /**
     * Elimina una prueba de evaluacion.
     * @param exist prueba de evaluacion a eliminar.
     * @return la prueba eliminada.
     */
    public PruebasEvaluacion deleteEvaluationTest(PruebasEvaluacion exist) {
        return this.evaluationTestRepository.delete(exist);
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
