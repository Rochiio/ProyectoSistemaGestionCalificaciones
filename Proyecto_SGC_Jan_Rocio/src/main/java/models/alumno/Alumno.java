package models.alumno;

import utils.Format;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Objects;

public class Alumno {
    private int id;
    private String dni;     //NNNNNNNNL
    private String name;
    private String lastName;
    private String email;
    private String telephone;       //NNN-NNNNNN
    private int evaluationTests=0;      //numero de pruebas de evaluacion en las que está añadido el usuario.
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
//        this.id = ++contador;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.continuousEvaluation = continuousEvaluation;
        this.registrationDate = LocalDateTime.now();
    }


    /**
     * Constructor para las bases de datos
     * @param id id
     * @param dni DNI del alumno.
     * @param name Nombre del alumno.
     * @param lastName Apellidos del alumno.
     * @param email Correo del alumno.
     * @param telephone Teléfono del alumno.
     * @param continuousEvaluation Si tiene o no evaluacion continua.
     * @param registrationDate fecha registro
     */
    public Alumno(int id, String dni, String name, String lastName, String email, String telephone,int evaluationTests, boolean continuousEvaluation, LocalDateTime registrationDate) {
        this.id = id;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.evaluationTests = evaluationTests;
        this.continuousEvaluation = continuousEvaluation;
        this.registrationDate = registrationDate;
    }


    //------------------------------------------Getters & Setters-----------------------------------------------

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setContinuousEvaluation(boolean continuousEvaluation) {
        this.continuousEvaluation = continuousEvaluation;
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
                        ", evaluationTest='" + evaluationTests + '\'' +
                        ", continuousEvaluation=" + continuousEvaluation +
                        ", registrationDate=" + Format.formatDateShort(registrationDate) +
                        '}';
            } catch (ParseException e) {
                e.printStackTrace();
        }
        return returnString;
    }




}

