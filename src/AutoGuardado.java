import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/*public class AutoGuardado { //👻👻👻👻👻👻
    private Mascota mascota;
    private ArrayList<Integer> barrasEstado;
    private int hambre, felicidad, energia, suciedad, lvl;

    public AutoGuardado() {
        barrasEstado = new ArrayList<>();
        guardarLista(barrasEstado);
    }

    public void guardarDatos(int hambre, int felicidad, int energia, int suciedad, int lvl) {
        //this.mascota = new Mascota(hambre, felicidad, energia, suciedad);
        cargarLista();
        barrasEstado.add(hambre);
        barrasEstado.add(felicidad);
        barrasEstado.add(suciedad);
        barrasEstado.add(energia);
        barrasEstado.add(lvl);
        guardarLista(barrasEstado);
    }

    public void imprimirLista(){
        System.out.println(barrasEstado);
    }

    public void guardarEstado(){
        cargarLista();
        if (barrasEstado.size() == 5) {
            hambre = barrasEstado.get(0);
            felicidad = barrasEstado.get(1);
            suciedad = barrasEstado.get(2);
            energia = barrasEstado.get(3);
            lvl = barrasEstado.get(4);
        }
    }
    public void actualizarDatos(){
        if (barrasEstado.size() == 5) {
            mascota.setHambre(barrasEstado.get(0));
            mascota.setFelicidad(barrasEstado.get(1));
            mascota.setSuciedad(barrasEstado.get(2));
            mascota.setEnergia(barrasEstado.get(3));
            mascota.setNivel(barrasEstado.get(4));
            new Mascota(hambre, felicidad, suciedad,energia);
        }
    }
    public void guardarLista(ArrayList<Integer> barrasEstado) {
        try {
            FileOutputStream fos = new FileOutputStream("barrasEstado.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(barrasEstado);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> cargarLista() {
        ArrayList<Integer> barrasEstado = null;
        try {
            FileInputStream fis = new FileInputStream("barrasEstado.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            barrasEstado = (ArrayList<Integer>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return barrasEstado;
    }
}*/
