public class Mascota {

    private int hambre;
    private int felicidad;
    private int suciedad;
    private int energia;


    public Mascota(int hambre, int felicidad, int suciedad, int energia){
        this.hambre = hambre;
        this.felicidad = felicidad;
        this.suciedad = suciedad;
        this.energia = energia;
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




}
