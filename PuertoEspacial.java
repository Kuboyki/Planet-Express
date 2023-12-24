//Realizado por Sandra Blázquez Aldea bu0060 y Dong Jinghong IWSIM11

/**
 * Description of the class
 *
 * @author Sandra Blázquez Aldea
 * @author Dong Jinghong
 * @version     1.0
 */
public class PuertoEspacial {

    private String nombre;
    private String codigo;
    private double radio;

    private double azimut;

    private double polar;

    private int numMuelles;

    /**
     * Constructor of the class
     *
     * @param nombre
     * @param codigo
     * @param radio
     * @param azimut
     * @param polar
     * @param numMuelles
     */
    public PuertoEspacial(String nombre, String codigo, double radio, double azimut, double polar, int numMuelles) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.radio = Math.toRadians(radio);
        this.azimut = Math.toRadians(azimut);
        this.polar =Math.toRadians( polar);
        this.numMuelles = numMuelles;
    }

    /**
     *
     * @return el nombre del puerto espacial
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @return el codigo del puerto espacial
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     *
      * @return el radio del puerto espacial
     */
    public double getRadio() {
        return radio;
    }

    /**
     *
     * @return el azimut del puerto espacial
     */
    public double getAzimut() {
        return azimut;
    }

    /**
     *
      * @return el polar del puerto espacial
     */
    public double getPolar() {
        return polar;
    }

    /**
     *
     * @return los muelles del puerto espacial
     */
    public int getMuelles() {
        return numMuelles;
    }

    /**
     * TODO: Método para calcular la distancia entre el puerto espacial que recibe el mensaje y el puerto
     *  espacial "destino" siguiendo las ecuaciones del enunciado (Las formulas se encuentran en el enunciado)
     * @param destino el otro puerto
     * @return distancia entre los dos puertos
     */
    public double distancia(PuertoEspacial destino) {
        // TODO: Para calcular la distancia entre dos Puertos Espaciales, se transforman sus coordenadas esféricas a cartesianas
        //coordenadas cartesianas del objeto de tipo PuestoEspacial que recibe el mensaje
        double x1=getRadio()*Math.sin(getAzimut())*Math.cos(getPolar());
        double y1= getRadio()*Math.sin(getAzimut())*Math.sin(getPolar());
        double z1 = getRadio()* Math.cos(getAzimut());

        //coordenadas cartesianas del objeto destino de tipo PuestoEspacial
        double x2= destino.getRadio()*Math.sin(destino.getAzimut())*Math.cos(destino.getPolar());
        double y2= destino.getRadio()*Math.sin(destino.getAzimut())*Math.sin(destino.getPolar());
        double z2 = destino.getRadio()* Math.cos(destino.getAzimut());

        // TODO: Una vez se tienen las coordenadas cartesianas, basta con calcular la distancia euclídea entre ellas:

        return  Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2) + Math.pow((z2-z1),2));
    }

    /**
     * TODO: Método que crea un String con los datos de un puerto espacial con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal(GGT), en (1.0 90.0 0.0), con 8 muelles" (Radio, Azimut, Polar)
     */
    public String toString() {
        return getNombre()+", en ( "+getCodigo()+"), en ("+getRadio()+", "+getAzimut()+", "+getPolar()+"), con "+getMuelles()+" muelles ";
    }

    /**
     * TODO: Método que crea un String con los datos de un aeropuerto con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal (GGT)"
     */
    public String toStringSimple() {
        return getNombre() + "("+getCodigo()+")";

    }
}
