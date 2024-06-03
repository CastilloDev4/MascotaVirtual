import javax.swing.*;
public class Mascota {

    private int hambre;
    private int felicidad;
    private int suciedad;
    private int energia;
    private JLabel mascotaLabel;
    private ImageIcon mascotaAnimacion;
    private ImageIcon hambreAnimacion;
    private ImageIcon felicidadAnimacion;
    private ImageIcon suciedadAnimacion;
    private ImageIcon energiaAnimacion;
    private ImageIcon dormirAnimacion;
    private ImageIcon banarAnimacion;
    private ImageIcon entrenarAnimacion;
    private ImageIcon correrAnimacion;

    private ImageIcon saltarAnimacion;


    public Mascota(int hambre, int felicidad, int suciedad, int energia){
        this.hambre = hambre;
        this.felicidad = felicidad;
        this.suciedad = suciedad;
        this.energia = energia;


        CargarAnimaciones();
    }

    public int getHambre() {
        return hambre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }

    public int getFelicidad() {
        return felicidad;
    }

    public void setFelicidad(int felicidad) {
        this.felicidad = felicidad;
    }

    public int getSuciedad() {
        return suciedad;
    }

    public void setSuciedad(int suciedad) {
        this.suciedad = suciedad;
    }

    public int getEnergia() {
        return energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public JLabel getMascotaLabel() {
        return mascotaLabel;
    }


    //METODO PARA CARGAR LAS ANIMACIONES DE LA MASCOTA. Hay que ingresar mas supongo
    public void CargarAnimaciones(){
       mascotaAnimacion = new ImageIcon(getClass().getClassLoader().getResource("mascota.gif"));
        hambreAnimacion = new ImageIcon(getClass().getClassLoader().getResource("hambre.gif"));
        entrenarAnimacion = new ImageIcon(getClass().getClassLoader().getResource("entrenar.gif"));
        banarAnimacion = new ImageIcon(getClass().getClassLoader().getResource("banar.gif"));
        //felicidadAnimacion = new ImageIcon(getClass().getClassLoader().getResource("felicidad.gif"));
        //suciedadAnimacion = new ImageIcon(getClass().getClassLoader().getResource("suciedad.gif"));
        //energiaAnimacion = new ImageIcon(getClass().getClassLoader().getResource("energia.gif"));
       dormirAnimacion = new ImageIcon(getClass().getClassLoader().getResource("dormir.gif"));
       correrAnimacion = new ImageIcon(getClass().getClassLoader().getResource("correr.gif"));
       saltarAnimacion = new ImageIcon(getClass().getClassLoader().getResource("saltar.gif"));
       mascotaLabel = new JLabel(mascotaAnimacion);
    }

    //Metodo para comer, setea la imagen de mascotaLabel e incrementa el nivel de hambre
    public void comer(){
        mascotaLabel.setIcon(hambreAnimacion);
        incrementarHambre(10);

    }
    //Metodo para entrenar, setea la imagen de mascotaLabel e incrementa el nivel de Felicidad y energia.
    public void entrenar(){
        mascotaLabel.setIcon(entrenarAnimacion);
        incrementarFelicidad(10);
        incrementarEnergia(5);

    }

    //Metodo para banar, setea la imagen de mascotaLabel e incrementa el nivel de limpieza.
    public void banar(){
        mascotaLabel.setIcon(banarAnimacion);
        incrementarLimpieza(10);

    }

    //Metodo para dormir, setea la imagen de mascotaLabel e incrementa el nivel de energia.
    public void dormir(){
        mascotaLabel.setIcon(dormirAnimacion);
        incrementarEnergia(10);

    }

    //Metodo para obtener la imagen de la mascota
    public ImageIcon getMascotaAnimacion(){
        return mascotaAnimacion;
    }


    public ImageIcon getCorrerAnimacion() {return correrAnimacion;}

    public ImageIcon getSaltarAnimacion() {return saltarAnimacion;}

    /*Metodos para incrementar los atributos.
    Basicamente reciben un valor el cual va a ser de 10 para todos.
    se calcula el valor actual de hambre + la cantidad de aumento.
    Math.min compara estos valores y devuelve el mas pequeño, esto garantiza que nunca pase de 100
    Asi la suma sea mayor a 100 siempre devolverá 100, asi controlamos la barra de progreso.
    */

    private void incrementarHambre(int cantidad) {
        hambre = Math.min(hambre + cantidad, 100);
    }
    private void incrementarFelicidad(int cantidad) {
        felicidad = Math.min(felicidad + cantidad, 100);
    }
    private void incrementarLimpieza(int cantidad) {
        suciedad = Math.min(suciedad + cantidad, 100);
    }
    private void incrementarEnergia(int cantidad) {
        energia = Math.min(energia + cantidad, 100);
    }

    public void decrementarAtributos(int decremento){
        hambre = Math.max(hambre - decremento, 0);
        felicidad = Math.max(felicidad - decremento, 0);
        suciedad = Math.max(suciedad - decremento, 0);
        energia = Math.max(energia - decremento, 0);
    }








}
