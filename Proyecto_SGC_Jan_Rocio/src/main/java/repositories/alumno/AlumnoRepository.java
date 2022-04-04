package repositories.alumno;

import controllers.DataBaseManager;
import models.alumno.Alumno;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class AlumnoRepository implements IAlumnoRepository<Alumno> {
    private final HashMap<Integer, Alumno> lista= new HashMap<>();


    /**
     * Encontrar un alumno por su id.
     * @param id id del alumno a buscar.
     * @return el alumno si lo encuentra o null.
     */
    @Override
    public Optional<Alumno> findById(int id, DataBaseManager db) throws SQLException {
        String query = "SELECT * FROM alumno WHERE id = ?";
        db.open();
            ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar alumno con id: " + id));
                if (result.next()) {
                    Alumno alumno = new Alumno(
                            result.getInt("id"),
                            result.getString("Dni"),
                            result.getString("Nombre"),
                            result.getString("Apellidos"),
                            result.getString("Email"),
                            result.getString("Telefono"),
                            result.getInt("Pruebas_Evaluacion"),
                            result.getBoolean("Perdida_Evaluacion"),
                            result.getObject("Fecha_Matriculacion", LocalDateTime.class)
                    );
                db.close();
                return Optional.of(alumno);
        }
        return Optional.empty();
    }


    /**
     * Te devuelve todos los valores.
     * @return lista con todos los alumnos.
     */
    @Override
    public List<Alumno> findAll(DataBaseManager db) throws SQLException {
        String query = "SELECT * FROM alumno";
        db.open();
            ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al obtener todos los alumnos"));
            List<Alumno> list = new ArrayList<>();
                while (result.next()) {
                        list.add(
                                new Alumno(
                                        result.getInt("id"),
                                        result.getString("Dni"),
                                        result.getString("Nombre"),
                                        result.getString("Apellidos"),
                                        result.getString("Email"),
                                        result.getString("Telefono"),
                                        result.getInt("Pruebas_Evaluacion"),
                                        result.getBoolean("Perdida_Evaluacion"),
                                        result.getObject("Fecha_Matriculacion", LocalDateTime.class)
                                )
                        );
            }
        db.close();
        return list;
    }


    /**
     * Te devuelve si un alumno ya exite por dni ya que es único.
     * @param dni dni a buscar
     * @return si existe un alumno con ese dni o no.
     */
    public Optional<Alumno> findByDni(String dni, DataBaseManager db) throws SQLException {
        String query = "SELECT * FROM alumno WHERE dni = ?";
        db.open();
            ResultSet result = db.select(query, dni).orElseThrow(() -> new SQLException("Error al consultar alumno con dni: " + dni));
                if (result.next()) {
                        Alumno alumno = new Alumno(
                                result.getInt("id"),
                                result.getString("Dni"),
                                result.getString("Nombre"),
                                result.getString("Apellidos"),
                                result.getString("Email"),
                                result.getString("Telefono"),
                                result.getInt("Pruebas_Evaluacion"),
                                result.getBoolean("Perdida_Evaluacion"),
                                result.getObject("Fecha_Matriculacion", LocalDateTime.class)
                        );
                    db.close();
                    return Optional.of(alumno);
                }
            return Optional.empty();
    }


    /**
     * Para saber si el alumno está en alguna prueba de evaluación.
     * @param id identificador del alumno.
     * @return el número de pruebas de evaluación en las que está metido.
     */
    @Override
    public int hasEvaluationTest(int id,DataBaseManager db) throws SQLException {
        String query = "SELECT * FROM alumno WHERE id = ?";
        db.open();
            ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar alumno con id: " + id));
                if (result.next()) {
                    int evaluationTest = result.getInt("Pruebas_Evaluacion");
                    db.close();
                    return evaluationTest;
            }
        return -1;
    }


    /**
     * Añadir alumno al repositorio.
     * @param value alumno a añadir.
     * @return el alumno añadido.
     */
    @Override
    public Alumno create(Alumno value,DataBaseManager db) throws SQLException {
        String query = "INSERT INTO alumno VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?)";
        db.open();
        ResultSet res = db.insert(query, value.getDni(),value.getName(),value.getLastName(),value.getEmail(),
                value.getTelephone(),0,value.getEvaluationTests(),value.getRegistrationDate())
                .orElseThrow(() -> new SQLException("Error al insertar alumno"));

        if (res.first()) {
            value.setId(res.getInt(1));
            db.close();
            return value;
        }
        return null;
    }


    /**
     * Eliminar alumno.
     * @param value alumno que se va a eliminar.
     * @return el alumno eliminado.
     */
    @Override
    public Optional<Alumno> delete(Optional<Alumno> value, DataBaseManager db) throws SQLException {
        String query = "DELETE FROM alumno WHERE id = ?";
            db.open();
                if (value.isPresent()) {
                    db.delete(query, value.get().getId());
                }else{
                    throw new SQLException("Error al eliminar alumno");
                }
            db.close();
        return value;
    }


    /**
     * Actualizar datos de un alumno.
     * @param id id dle alumno a actualizar.
     * @param value nuevos valores del alumno.
     * @return el alumno actualizado.
     */
    @Override
    public Alumno update(int id, Alumno value) {
        var beforeData = this.lista.get(id);
                this.lista.get(id).setDni((value.getDni().isEmpty())? beforeData.getDni() : value.getDni());
                this.lista.get(id).setName((value.getName().isEmpty())? beforeData.getName(): value.getName());
                this.lista.get(id).setLastName((value.getLastName().isEmpty())? beforeData.getLastName(): value.getLastName());
                this.lista.get(id).setEmail((value.getEmail().isEmpty())? beforeData.getEmail(): value.getEmail());
                this.lista.get(id).setTelephone((value.getTelephone().isEmpty())? beforeData.getTelephone(): value.getTelephone());
                this.lista.get(id).setContinuousEvaluation(value.isContinuousEvaluation());
        return this.lista.get(id);
    }




}
