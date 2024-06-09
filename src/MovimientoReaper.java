import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class MovimientoReaper implements KeyListener {
    private MascotaReaper mascotaReaper;
    private int inicialX;
    private int inicialY;
    private ImageIcon animacionInicial;
    private boolean saltando = false;  // boolean para controlar el estado del salto

    public MovimientoReaper(MascotaReaper mascotaReaper) {
        this.mascotaReaper = mascotaReaper;
        this.inicialX = mascotaReaper.getReaperLabel().getX();  //OBTENER LA POSICION INICIAL EN X DE LA MASCOTA
        this.inicialY = mascotaReaper.getReaperLabel().getY(); //OBTENER LA POSICION INICIAL EN Y DE LA MASCOTA
        this.animacionInicial = mascotaReaper.getMascotaAnimacion(); //OBTENER LA ANIMACION INICIAL DE LA MASCOTA
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // NO ES NECESARIO ESTE METODO PARA EL MOVIMIENTO
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A' || e.getExtendedKeyCode() == KeyEvent.VK_LEFT) {

            mascotaReaper.getReaperLabel().setIcon(mascotaReaper.getCorrerInvertidoAnimacion());
            mascotaReaper.getReaperLabel().setLocation(mascotaReaper.getReaperLabel().getX() - 10, mascotaReaper.getReaperLabel().getY());
        }

        if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {

            mascotaReaper.getReaperLabel().setIcon(mascotaReaper.getCorrerAnimacion());
            mascotaReaper.getReaperLabel().setLocation(mascotaReaper.getReaperLabel().getX() + 10, mascotaReaper.getReaperLabel().getY());

        }

        if (!saltando && (e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getExtendedKeyCode() == KeyEvent.VK_UP)) {
            saltar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A' || e.getExtendedKeyCode() == KeyEvent.VK_LEFT) {
            mascotaReaper.getReaperLabel().setIcon(animacionInicial);
        }

        if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {
            mascotaReaper.getReaperLabel().setIcon(animacionInicial);
        }
    }

    private void saltar() {
        saltando = true;
        mascotaReaper.getReaperLabel().setIcon(mascotaReaper.getSaltarAnimacion());
        Timer timer = new Timer();
        TimerTask subir = new TimerTask() {
            @Override
            public void run() {
                mascotaReaper.getReaperLabel().setLocation(mascotaReaper.getReaperLabel().getX(), mascotaReaper.getReaperLabel().getY() - 10);
                TimerTask bajar = new TimerTask() {
                    @Override
                    public void run() {
                        mascotaReaper.getReaperLabel().setLocation(mascotaReaper.getReaperLabel().getX(), mascotaReaper.getReaperLabel().getY() + 10);
                        mascotaReaper.getReaperLabel().setIcon(animacionInicial);
                        saltando = false;
                    }
                };
                timer.schedule(bajar, 1000); // Espera un segundo para bajar
            }
        };
        timer.schedule(subir, 0); // Sube de inmediato sin delay
    }
}
