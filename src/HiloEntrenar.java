public class HiloEntrenar implements Runnable {
    private Mascota mascota;

    public HiloEntrenar(Mascota mascota) {
        this.mascota = mascota;
    }

    @Override
    public void run() {
        mascota.entrenar();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mascota.getMascotaLabel().setIcon(mascota.getMascotaAnimacion());
    }
}