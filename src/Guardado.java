import java.io.*;

public class Guardado {
    public void guardarDatos(Mascota mascota) {
        try {
            FileOutputStream fileOut = new FileOutputStream("mascota.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(mascota);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public Mascota cargarDatos() {
        Mascota mascota = null;
        try {
            FileInputStream fileIn = new FileInputStream("mascota.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            mascota = (Mascota) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("La clase Mascota no se encontró");
            c.printStackTrace();
            return null;
        }
        return mascota;
    }

    public void guardarDatosReaper(MascotaReaper mascotaReaper) {
        try {
            FileOutputStream fileOut = new FileOutputStream("mascotaReaper.bin");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(mascotaReaper);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public MascotaReaper cargarDatosReaper() {
        MascotaReaper mascotaReaper = null;
        try {
            FileInputStream fileIn = new FileInputStream("mascotaReaper.bin");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            mascotaReaper = (MascotaReaper) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("La clase MascotaReaper no se encontró");
            c.printStackTrace();
            return null;
        }
        return mascotaReaper;
    }
}
