//Realizado por Sandra Blázquez Aldea bu0060 y Dong Jinghong IWSIM11

/**
 * Clase en el que se gestionan los datos y diferentes acciones que se llevan a cabo de las naves
 *
 * @author Sandra Blázquez Aldea
 * @author Dong Jinghong
 * @version 1.0
 */
public class Nave {
    private String marca;
    private String modelo;
    private String matricula;
    private int columnas;
    private int filas;
    private double alcance;


    /**
     * Constructor of the class
     *
     * @param marca de la nave
     * @param modelo de la nave
     * @param matricula de la nave
     * @param columnas  en la que se sitúa la nave
     * @param filas en la que se sitúa la nave
     * @param alcance de la nave
     */
    public Nave(String marca, String modelo, String matricula, int columnas, int filas, double alcance) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.columnas = columnas;
        this.filas = filas;
        this.alcance = alcance;
    }

    /**
     *
     * @return la marca de la nave
     */
    public String getMarca() {
        return marca;
    }

    /**
     *
     * @return el modelo de la nave
     */
    public String getModelo() {
        return modelo;
    }

    /**
     *
     * @return la matrícula en la que se encuentra la nave
     */
    public String getMatricula() {
        return matricula;
    }

    /**
     *
     * @return la columna en la que se encuentra la nave
     */
    public int getColumnas() {
        return columnas;
    }

    /**
     *
     * @return la fila en la que se encuentra la nave
     */
    public int getFilas() {
        return filas;
    }

    /**
     * Devuleve el alcance de la nave
     * @return el alcance de la nave
     */

    public double getAlcance() {
        return alcance;
    }


    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     *
     * @return ejemplo del formato -> "Planet Express One (EP-245732X): 40 contenedores, hasta 1.57 UA"
     */
    public String toString() {
        return getMarca() + " " + getModelo() + " (" + getMatricula() + "): " + (getFilas() * getColumnas()) + " contenedores, hasta " + getAlcance() + " UA";
    }


    /**
     * TODO: Crea un String con los datos de una nave con el siguiente formato:
     *
     * @return ejemplo del formato -> "Planet Express One (EP-245732X)"
     */
    public String toStringSimple() {
        return getMarca() + " " + getModelo() + " (" + getMatricula() + ") ";
    }
}
