public class HiloBanar implements Runnable {
    private Mascota mascota;


    public HiloBanar(Mascota mascota ) {
        this.mascota = mascota;

    }

    @Override
    public void run() {
        mascota.banar();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mascota.getMascotaLabel().setIcon(mascota.getMascotaAnimacion());
        //gameplay.actualizarBarras();
    }
}