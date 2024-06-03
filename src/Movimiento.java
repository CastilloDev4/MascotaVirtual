import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

public class Movimiento implements KeyListener {
    private Mascota mascota;
    private int inicialX;
    private int inicialY;
    private ImageIcon animacionInicial;
    private boolean saltando = false;  // boolean para controlar el estado del salto

    public Movimiento(Mascota mascota) {
        this.mascota = mascota;
        this.inicialX = mascota.getMascotaLabel().getX();  //OBTENER LA POSICION INICIAL EN X DE LA MASCOTA
        this.inicialY = mascota.getMascotaLabel().getY(); //OBTENER LA POSICION INICIAL EN Y DE LA MASCOTA
        this.animacionInicial = mascota.getMascotaAnimacion(); //OBTENER LA ANIMACION INICIAL DE LA MASCOTA
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // NO ES NECESARIO ESTE METODO PARA EL MOVIMIENTO
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A' || e.getExtendedKeyCode() == KeyEvent.VK_LEFT) {

            mascota.getMascotaLabel().setIcon(mascota.getCorrerInvertidoAnimacion());
            mascota.getMascotaLabel().setLocation(mascota.getMascotaLabel().getX() - 10, mascota.getMascotaLabel().getY());
        }

        if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {

            mascota.getMascotaLabel().setIcon(mascota.getCorrerAnimacion());
            mascota.getMascotaLabel().setLocation(mascota.getMascotaLabel().getX() + 10, mascota.getMascotaLabel().getY());

        }

        if (!saltando && (e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getExtendedKeyCode() == KeyEvent.VK_UP)) {
            saltar();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyChar() == 'a' || e.getKeyChar() == 'A' || e.getExtendedKeyCode() == KeyEvent.VK_LEFT) {
            mascota.getMascotaLabel().setIcon(animacionInicial);
        }

        if (e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {
            mascota.getMascotaLabel().setIcon(animacionInicial);
        }
    }

    private void saltar() {
        saltando = true;
        mascota.getMascotaLabel().setIcon(mascota.getSaltarAnimacion());
        Timer timer = new Timer();
        TimerTask subir = new TimerTask() {
            @Override
            public void run() {
                mascota.getMascotaLabel().setLocation(mascota.getMascotaLabel().getX(), mascota.getMascotaLabel().getY() - 10);
                TimerTask bajar = new TimerTask() {
                    @Override
                    public void run() {
                        mascota.getMascotaLabel().setLocation(mascota.getMascotaLabel().getX(), mascota.getMascotaLabel().getY() + 10);
                        mascota.getMascotaLabel().setIcon(animacionInicial);
                        saltando = false;
                    }
                };
                timer.schedule(bajar, 1000); // Espera un segundo para bajar
            }
        };
        timer.schedule(subir, 0); // Sube  de inmediato sin delay
    }
}
