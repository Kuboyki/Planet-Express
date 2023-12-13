/**
 * Description of the class
 *
 * @author
 * @author
 * @version     1.0
 */
public class PuertoEspacial {

    private String nombre;
    private String codigo;
    private double radio;
    //puertos se determina según la distancia al Sol
    // en unidades astronómicas UA
    private double azimut;
    //el ángulo desde el eje positivo x hasta la
    // proyección del punto en el plano xy
    private double polar;
    // el ángulo desde el eje positivo z
    //hasta el puerto
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
        this.radio = radio;
        this.azimut = azimut;
        this.polar = polar;
        this.numMuelles = numMuelles;
    }
    public String getNombre() {
        return nombre;
    }
    public String getCodigo() {
        return codigo;
    }
    public double getRadio() {
        return radio;
    }
    public double getAzimut() {
        return azimut;
    }

    public double getPolar() {
        return polar;
    }
    public int getMuelles() {
        return numMuelles;
    }

    /**
     * TODO: Método para calcular la distancia entre el puerto espacial que recibe el mensaje y el puerto
     *  espacial "destino" siguiendo las ecuaciones del enunciado (Las formulas se encuentran en el enunciado)
     * @param destino
     * @return
     */
    public double distancia(PuertoEspacial destino) {
        // TODO: Para calcular la distancia entre dos Puertos Espaciales, se transforman sus coordenadas esféricas a cartesianas
        //coordenadas cartesianas del objeto de tipo PuestoEspacial que recibe el mensaje
//hay que usar el metodo seleccionarPuertoEspacial de ListaPuertosEspaciales para buscar dicho puerto ?????'
        double x1=getRadio()*Math.sin(getAzimut())*Math.cos(getPolar());
        double y1= getRadio()*Math.sin(getAzimut())*Math.sin(getPolar());
        double z1 = getRadio()* Math.cos(getAzimut());

        //coordenadas cartesianas del objeto destino de tipo PuestoEspacial
        double x2= destino.getRadio()*Math.sin(destino.getAzimut())*Math.cos(destino.getPolar());
        double y2= destino.getRadio()*Math.sin(destino.getAzimut())*Math.sin(destino.getPolar());
        double z2 = destino.getRadio()* Math.cos(destino.getAzimut());

        // TODO: Una vez se tienen las coordenadas cartesianas, basta con calcular la distancia euclídea entre ellas:
        double distancia= Math.sqrt(Math.pow((x2-x1),2) + Math.pow((y2-y1),2) + Math.pow((z2-z1),2));

        return  distancia;
    }

    /**
     * TODO: Método que crea un String con los datos de un puerto espacial con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal(GGT), en (1.0 90.0 0.0), con 8 muelles" (Radio, Azimut, Polar)
     */
    public String toString() {
        //GGT es un codigo o son las iniciales del nombre , como lo he puesto en toStringSimple ???????????????????'
        return getNombre()+", en ( "+getCodigo()+"), en ("+getRadio()+", "+getAzimut()+", "+getPolar()+"), con "+getMuelles()+" muelles ";
    }

    /**
     * TODO: Método que crea un String con los datos de un aeropuerto con el siguiente formato:
     * @return ejemplo -> "Gaia Galactic Terminal (GGT)"
     */
    public String toStringSimple() {
        //como hago para que por cada palabra me coja la inicial ?? pq así solo me cogería la primera G
        return getNombre() + "("+getCodigo()+")";

    }
}
