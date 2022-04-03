package compares;

import models.alumno.Alumno;

import java.util.Comparator;

public class AlumnoOrdenAlfabeticoComparator implements Comparator<Alumno> {
    @Override
    public int compare(Alumno o1, Alumno o2) {
        return o1.getLastName().compareToIgnoreCase(o2.getLastName());
    }
}
