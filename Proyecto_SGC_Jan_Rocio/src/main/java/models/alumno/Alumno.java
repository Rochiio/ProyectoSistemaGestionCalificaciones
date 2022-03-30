package models.alumno;

import java.util.Date;
import java.util.Objects;

public class Alumno {
    private static int contador=0;
    private final int id;
    private String dni;     //NNNNNNNNL
    private String name;
    private String lastName;
    private String email;
    private String telephone;       //NNN-NNNNNN
    private boolean continuousEvaluation;
    private final Date RegistrationDate;      //DD/MM/AAAA


    /**
     * Constructor de alumno.
     * @param dni DNI del alumno.
     * @param name Nombre del alumno.
     * @param lastName Apellidos del alumno.
     * @param email Correo del alumno.
     * @param telephone Teléfono del alumno.
     * @param continuousEvaluation Si tiene o no evaluacion continua.
     * @param registrationDate Fecha de registro/ matriculación.
     */
    public Alumno(String dni, String name, String lastName, String email, String telephone, boolean continuousEvaluation, Date registrationDate) {
        this.id = ++contador;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.continuousEvaluation = continuousEvaluation;
        RegistrationDate = registrationDate;
    }


    /**
     * Getters & Setter Alumno
     * @return el dni del alumno.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Getters & Setter Alumno
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Getters & Setter Alumno
     * @return el nombre del alumno.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getters & Setter Alumno
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getters & Setter Alumno
     * @return los apellidos del alumno.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Getters & Setter Alumno
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getters & Setter Alumno
     * @return el email del usuario.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Getters & Setter Alumno
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getters & Setter Alumno
     * @return el teléfono del usuario.
     */
    public String getTelephone() {
        return this.telephone;
    }

    /**
     * Getters & Setter Alumno
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Getters & Setter Alumno
     * @return si ha perdido la evaluación continua o no.
     */
    public boolean isContinuousEvaluation() {
        return this.continuousEvaluation;
    }

    /**
     * Getters & Setter Alumno
     */
    public void setContinuousEvaluation(boolean continuousEvaluation) {
        this.continuousEvaluation = continuousEvaluation;
    }


    /**
     * Getters & Setter Alumno
     * @return el id del alumno.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getters & Setter Alumno
     * @return la fecha de matriculación del alumno.
     */
    public Date getRegistrationDate() {
        return this.RegistrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return id == alumno.id && continuousEvaluation == alumno.continuousEvaluation && dni.equals(alumno.dni) && name.equals(alumno.name) && lastName.equals(alumno.lastName) && email.equals(alumno.email) && telephone.equals(alumno.telephone) && RegistrationDate.equals(alumno.RegistrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, name, lastName, email, telephone, continuousEvaluation, RegistrationDate);
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", continuousEvaluation=" + continuousEvaluation +
                ", RegistrationDate=" + RegistrationDate +
                '}';
    }
}

