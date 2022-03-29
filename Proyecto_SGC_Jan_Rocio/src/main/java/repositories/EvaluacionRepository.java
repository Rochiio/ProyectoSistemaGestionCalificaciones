package repositories;

import models.evaluacion.Evaluacion;

import java.util.ArrayList;
import java.util.List;

public class EvaluacionRepository implements ICRUDEvaluacion<Evaluacion>, List<Evaluacion> {
    public static EvaluacionRepository instance;


    public static EvaluacionRepository getInstance(){
        if (instance == null){
            instance = new EvaluacionRepository();
        }
        return instance;
    }




    @Override
    public List<Evaluacion> findAll() {
        for (var evaluacion :  this.
             ) {

        }
    }

    @Override
    public Evaluacion save(Evaluacion item) {
        return null;
    }

    @Override
    public Evaluacion delete(Evaluacion item) {
        return null;
    }


}
