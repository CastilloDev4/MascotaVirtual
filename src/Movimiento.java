import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;

public class Movimiento implements KeyListener {
    private Mascota mascota;
    private int inicialX;
    private int inicialY;
    private ImageIcon animacionInicial;


    public Movimiento(Mascota mascota) {
        this.mascota = mascota;
        this.inicialX = mascota.getMascotaLabel().getX();
        this.inicialY = mascota.getMascotaLabel().getY();
        this.animacionInicial = mascota.getMascotaAnimacion();

    }


    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'w' || e.getKeyChar() == 'W' || e.getExtendedKeyCode() == KeyEvent.VK_UP){
            mascota.getMascotaLabel().setIcon(mascota.getSaltarAnimacion());
            mascota.getMascotaLabel().setLocation(mascota.getMascotaLabel().getX(), mascota.getMascotaLabel().getY() - 10);
            


        }
        if(e.getKeyChar() == 'a' || e.getKeyChar() == 'A' || e.getExtendedKeyCode() == KeyEvent.VK_LEFT){
            mascota.getMascotaLabel().setIcon(mascota.getCorrerAnimacion());
            mascota.getMascotaLabel().setLocation(mascota.getMascotaLabel().getX() - 10, mascota.getMascotaLabel().getY());

        }

        if(e.getKeyChar() == 'd' || e.getKeyChar() == 'D' || e.getExtendedKeyCode() == KeyEvent.VK_RIGHT){
            mascota.getMascotaLabel().setIcon(mascota.getCorrerAnimacion());
            mascota.getMascotaLabel().setLocation(mascota.getMascotaLabel().getX() + 10, mascota.getMascotaLabel().getY());

        }







    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
