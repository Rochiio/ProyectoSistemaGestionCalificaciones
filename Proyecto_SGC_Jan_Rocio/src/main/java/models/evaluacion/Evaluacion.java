package models.evaluacion;

import repositories.evaluacion.PruebaEvaluacionRepository;
import repositories.evaluacion.IEvaluacionRepository;
import repositories.evaluacion.PruebasEvaluacion;

import java.util.Objects;

public class Evaluacion {
    //Declaración de los atributos de la clase
    private static int contador = 0;
    private final int id;
    private float maximumNote;
    private float minimumNote;
    private float averageGrade;
    private float passPercentages;
    private float failPercentages;
    private IEvaluacionRepository<PruebasEvaluacion> evaluationTestRepository;


    /**
     * Constructor de evaluacion
     * @param maximumNote del alumno
     * @param minimumNote el alumno
     * @param averageGrade media del alumno
     * @param passPercentages de todos los alumnos
     * @param failPercentages de todos los alumnos
     * @param evaluationTestRepository repositorio de pruebas de evaluación.
     */
    public Evaluacion(float maximumNote, float minimumNote, float averageGrade, float passPercentages,
                      float failPercentages, IEvaluacionRepository<PruebasEvaluacion> evaluationTestRepository) {
        this.id = ++contador;
        this.maximumNote = maximumNote;
        this.minimumNote = minimumNote;
        this.averageGrade = averageGrade;
        this.passPercentages = passPercentages;
        this.failPercentages = failPercentages;
        this.evaluationTestRepository = evaluationTestRepository;
    }

    public int getId() {
        return id;
    }

    public float getMaximumNote() {
        return maximumNote;
    }

    public void setMaximumNote(float maximumNote) {
        this.maximumNote = maximumNote;
    }

    public float getMinimumNote() {
        return minimumNote;
    }

    public void setMinimumNote(float minimumNote) {
        this.minimumNote = minimumNote;
    }

    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    public float getPassPercentages() {
        return passPercentages;
    }

    public void setPassPercentages(float passPercentages) {
        this.passPercentages = passPercentages;
    }

    public float getFailPercentages() {
        return failPercentages;
    }

    public void setFailPercentages(float failPercentages) {
        this.failPercentages = failPercentages;
    }

    public IEvaluacionRepository<PruebasEvaluacion> getEvaluationTestRepository() {
        return evaluationTestRepository;
    }

    public void setEvaluationTestRepository(PruebaEvaluacionRepository evaluationTestRepository) {
        this.evaluationTestRepository = evaluationTestRepository;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluacion that = (Evaluacion) o;
        return id == that.id && Float.compare(that.maximumNote, maximumNote) == 0 && Float.compare(that.minimumNote, minimumNote) == 0 && Float.compare(that.averageGrade, averageGrade) == 0 && Float.compare(that.passPercentages, passPercentages) == 0 && Float.compare(that.failPercentages, failPercentages) == 0 && Objects.equals(evaluationTestRepository, that.evaluationTestRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, maximumNote, minimumNote, averageGrade, passPercentages, failPercentages, evaluationTestRepository);
    }


    @Override
    public String toString() {
        return "Evaluacion{" +
                "id: " + id +
                ", maximumNote: " + maximumNote +
                ", minimumNote: " + minimumNote +
                ", averageGrade: " + averageGrade +
                ", passPercentages: " + passPercentages +
                ", failPercentages:" + failPercentages +
                ", evaluationTestRepository: " + evaluationTestRepository +
                '}';
    }
}
