import javax.swing.*;
import java.io.Serializable;

public class MascotaReaper implements Serializable {

    private ImageIcon mascotaAnimacion;
    private ImageIcon hambreAnimacion;
    private ImageIcon dormirAnimacion;
    private ImageIcon banarAnimacion;
    private ImageIcon entrenarAnimacion;
    private ImageIcon correrAnimacion;
    private ImageIcon saltarAnimacion;
    private ImageIcon correrInvertidoAnimacion;
    private JLabel reaperLabel;
    private int Hambre;
    private int Felicidad;
    private int Suciedad;
    private int Energia;
    private int nivel;
    private boolean nivelSubido;


    public MascotaReaper(int hambre, int felicidad, int suciedad, int energia){
        this.Hambre = hambre;
        this.Felicidad = felicidad;
        this.Suciedad = suciedad;
        this.Energia = energia;
        this.nivel = 1;

        CargarAnimaciones();
    }


    public void CargarAnimaciones(){
        mascotaAnimacion = new ImageIcon(getClass().getClassLoader().getResource("ReaperStatic.gif"));
        entrenarAnimacion = new ImageIcon(getClass().getClassLoader().getResource("ReaperEntrenar.gif"));
        banarAnimacion = new ImageIcon(getClass().getClassLoader().getResource("ReaperBañar.gif"));
        dormirAnimacion = new ImageIcon(getClass().getClassLoader().getResource("ReaperDormir.gif"));
        correrAnimacion = new ImageIcon(getClass().getClassLoader().getResource("ReaperCaminar.gif"));
        correrInvertidoAnimacion = new ImageIcon(getClass().getClassLoader().getResource("ReaperCaminarReverse.gif"));
        hambreAnimacion = new ImageIcon(getClass().getClassLoader().getResource("ReaperComer.gif"));
        saltarAnimacion = new ImageIcon(getClass().getClassLoader().getResource("ReaperSaltar.gif"));
        reaperLabel = new JLabel(mascotaAnimacion);
    }

    //Getters y Setters
    public int getHambre() {return Hambre;}

    public void setHambre(int hambre) {this.Hambre = hambre;}

    public int getFelicidad() {return Felicidad;}

    public void setFelicidad(int felicidad) {this.Felicidad = felicidad;}

    public int getSuciedad() {return Suciedad;}

    public void setSuciedad(int suciedad) {this.Suciedad = suciedad;}

    public int getEnergia() {return Energia;}

    public void setEnergia(int energia) {this.Energia = energia;}

    public int getNivel() {return nivel;}

    public void setNivel(int nivel) {this.nivel = nivel;}

    public void subirNivel() {
        this.nivel++;
        this.nivelSubido = true;
    }

    public void reiniciarNivelSubido() {
        this.nivelSubido = false;
    }

    public boolean isNivelSubido() {
        return nivelSubido;
    }

    public ImageIcon getMascotaAnimacion() {return mascotaAnimacion;}

    public ImageIcon getHambreAnimacion() {return hambreAnimacion;}

    public ImageIcon getDormirAnimacion() {return dormirAnimacion;}

    public ImageIcon getBanarAnimacion() {return banarAnimacion;}

    public ImageIcon getEntrenarAnimacion() {return entrenarAnimacion;}

    public ImageIcon getCorrerAnimacion() {return correrAnimacion;}

    public ImageIcon getSaltarAnimacion() {return saltarAnimacion;}

    public ImageIcon getCorrerInvertidoAnimacion() {return correrInvertidoAnimacion;}

    private void incrementarHambre(int cantidad) {
        Hambre = Math.min(Hambre + cantidad, 100);
    }

    private void incrementarFelicidad(int cantidad) {
        Felicidad = Math.min(Felicidad + cantidad, 100);
    }

    private void incrementarLimpieza(int cantidad) {
        Suciedad = Math.min(Suciedad + cantidad, 100);
    }

    private void incrementarEnergia(int cantidad) {
        Energia = Math.min(Energia + cantidad, 100);
    }

    public JLabel getReaperLabel() {return reaperLabel;}

    public void decrementarAtributos(int decremento){
        Hambre = Math.max(Hambre - decremento, 0);
        Felicidad = Math.max(Felicidad - decremento, 0);
        Suciedad = Math.max(Suciedad - decremento, 0);
        Energia = Math.max(Energia - decremento, 0);
    }

    public void comer(){
        reaperLabel.setIcon(hambreAnimacion);
        incrementarHambre(10);
    }

    public void banar(){
        reaperLabel.setIcon(banarAnimacion);
        incrementarLimpieza(10);
    }

    public void entrenar(){
        reaperLabel.setIcon(entrenarAnimacion);
        incrementarFelicidad(10);
    }

    public void dormir(){
        reaperLabel.setIcon(dormirAnimacion);
        incrementarEnergia(10);
    }


}


