import javax.swing.*;

public class HiloComer implements Runnable {
    private Mascota mascota;


    public HiloComer(Mascota mascota) {

        this.mascota = mascota;

    }

    @Override
    public void run() {
        mascota.comer();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            mascota.getMascotaLabel().setIcon(mascota.getMascotaAnimacion());
            //gameplay.actualizarBarras();
        ;
    }
}