public class HiloBanar implements Runnable {
    private Mascota mascota;
    Gameplay gameplay;

    public HiloBanar(Mascota mascota, Gameplay gameplay) {
        this.mascota = mascota;
        this.gameplay = gameplay;
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
        gameplay.actualizarBarras();
    }
}