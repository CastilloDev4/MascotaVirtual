import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound implements Runnable{
    private Clip clip;
    public void cargarSonido(String ruta) {
        try {
            File audio = new File(ruta);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reproducir() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.start();
        }
    }
    public void run() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Reproducir en bucle
            clip.start(); // Iniciar la reproducción
        }
    }
    public void iniciarReproduccionEnHilo() {
        Thread hiloReproduccion = new Thread(this);
        hiloReproduccion.start();
    }

    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

}
