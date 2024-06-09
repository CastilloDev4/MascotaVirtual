public class HiloAnimacion implements Runnable {
    private MascotaReaper reapermascota;
    private String accion;

    public HiloAnimacion(MascotaReaper reapermascota, String accion) {
        this.reapermascota = reapermascota;
        this.accion = accion;
    }

    @Override
    public void run() {
        switch (accion) {
            case "banar":
                reapermascota.banar();
                break;
            case "comer":
                reapermascota.comer();
                break;
            case "entrenar":
                reapermascota.entrenar();
                break;
            case "dormir":
                reapermascota.dormir();
                break;
            default:
                break;
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        reapermascota.getReaperLabel().setIcon(reapermascota.getMascotaAnimacion());
    }
}