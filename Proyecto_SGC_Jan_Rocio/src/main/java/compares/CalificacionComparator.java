package compares;

import models.calificacion.Calificacion;

import java.util.Comparator;

public class CalificacionComparator implements Comparator<Calificacion> {
    @Override
    public int compare(Calificacion c1, Calificacion c2) {
        return (int) (c1.getNota() - c2.getNota());
    }

    @Override
    public Comparator<Calificacion> reversed() {
        return Comparator.super.reversed();
    }
}
