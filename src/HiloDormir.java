public class HiloDormir implements Runnable {
    private Mascota mascota;

    public HiloDormir(Mascota mascota) {
        this.mascota = mascota;
    }

    @Override
    public void run() {
        mascota.dormir();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mascota.getMascotaLabel().setIcon(mascota.getMascotaAnimacion());
    }
}
