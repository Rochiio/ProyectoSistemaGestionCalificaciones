package models.alumno;

import utils.Format;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Objects;

public class Alumno {
    private static int contador=0;
    private final int id;
    private String dni;     //NNNNNNNNL
    private String name;
    private String lastName;
    private String email;
    private String telephone;       //NNN-NNNNNN
    private int evaluationTests=0;
    private boolean continuousEvaluation;
    private final LocalDateTime registrationDate;      //DD/MM/AAAA


    /**
     * Constructor de alumno.
     * @param dni DNI del alumno.
     * @param name Nombre del alumno.
     * @param lastName Apellidos del alumno.
     * @param email Correo del alumno.
     * @param telephone Teléfono del alumno.
     * @param continuousEvaluation Si tiene o no evaluacion continua.
     */
    public Alumno(String dni, String name, String lastName, String email, String telephone, boolean continuousEvaluation) {
        this.id = ++contador;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.continuousEvaluation = continuousEvaluation;
        this.registrationDate = LocalDateTime.now();
    }


    //------------------------------------------Getters & Setters-----------------------------------------------
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getEvaluationTests() {
        return evaluationTests;
    }

    public void setEvaluationTests(int evaluationTests) {
        this.evaluationTests = evaluationTests;
    }

    public boolean isContinuousEvaluation() {
        return this.continuousEvaluation;
    }

    public void setContinuousEvaluation(boolean continuousEvaluation) {
        this.continuousEvaluation = continuousEvaluation;
    }

    public int getId() {
        return this.id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return id == alumno.id && continuousEvaluation == alumno.continuousEvaluation && dni.equals(alumno.dni) && name.equals(alumno.name) && lastName.equals(alumno.lastName) && email.equals(alumno.email) && telephone.equals(alumno.telephone) && registrationDate.equals(alumno.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, name, lastName, email, telephone, continuousEvaluation, registrationDate);
    }

    @Override
    public String toString() {
        String returnString = "";
            try {
                returnString= "Alumno{" +
                        "id=" + id +
                        ", dni='" + dni + '\'' +
                        ", name='" + name + '\'' +
                        ", lastName='" + lastName + '\'' +
                        ", email='" + email + '\'' +
                        ", telephone='" + telephone + '\'' +
                        ", continuousEvaluation=" + continuousEvaluation +
                        ", registrationDate=" + Format.formatDateShort(registrationDate) +
                        '}';
            } catch (ParseException e) {
                e.printStackTrace();
        }
        return returnString;
    }




}

