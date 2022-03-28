package compares;

import models.Alumno;

import java.util.Comparator;

public class AlumnoOrdenListaComparator implements Comparator<Alumno> {
    @Override
    public int compare(Alumno o1, Alumno o2) {
        return o1.getId()-o2.getId();
    }
}
