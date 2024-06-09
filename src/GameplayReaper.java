import javax.swing.*;
import java.awt.*;

public class GameplayReaper {

    JFrame ReaperFrame;
    MascotaReaper reaperMascota;
    private JProgressBar progresoHambre;
    private JProgressBar progresoFelicidad;
    private JProgressBar progresoSuciedad;
    private JProgressBar progresoEnergia;
    private JLabel BackLabel;
    private JButton btnBanar, btnComer, btnEntrenar, btnDormir;
    private Timer timer;
    private JLabel nivelLabel;
    private JPanel panelInferior;

    public GameplayReaper(MascotaReaper reaperMascota){
        this.reaperMascota = reaperMascota;
        inicializarComponentes();
        configurarComponentes();
        configurarListeners();
        iniciarTimer();
        mostrarFrame();

    }

    public void inicializarComponentes(){
        ReaperFrame = new JFrame("Reaper Pet");
        reaperMascota = new MascotaReaper(50, 50, 50, 50);
        progresoHambre = new JProgressBar(0, 100);
        progresoFelicidad = new JProgressBar(0, 100);
        progresoSuciedad = new JProgressBar(0, 100);
        progresoEnergia = new JProgressBar(0, 100);

        progresoHambre.setValue(reaperMascota.getHambre());
        progresoFelicidad.setValue(reaperMascota.getFelicidad());
        progresoSuciedad.setValue(reaperMascota.getSuciedad());
        progresoEnergia.setValue(reaperMascota.getEnergia());

        btnBanar = new JButton("Bañar");
        btnComer = new JButton("Comer");
        btnEntrenar = new JButton("Entrenar");
        btnDormir = new JButton("Dormir");

        panelInferior = new JPanel();
        panelInferior.setBackground(Color.WHITE);

        ImageIcon fondo = new ImageIcon(getClass().getResource("BackgroundReaper.png"));
        Image fondoEscalado = fondo.getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
        fondo = new ImageIcon(fondoEscalado);
        BackLabel = new JLabel(fondo);

        nivelLabel = new JLabel("Nivel: " + reaperMascota.getNivel());
        nivelLabel.setFont(new Font("Arial", Font.BOLD, 40));
        nivelLabel.setForeground(Color.WHITE);
        BackLabel.add(nivelLabel);

        MovimientoReaper movimientoReaper = new MovimientoReaper(reaperMascota);
        ReaperFrame.addKeyListener(movimientoReaper);
        ReaperFrame.setFocusable(true);
        ReaperFrame.requestFocusInWindow();
    }

    public void configurarComponentes(){

        ReaperFrame.setSize(1000,800);
        ReaperFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ReaperFrame.setLocationRelativeTo(null);
        ReaperFrame.setContentPane(BackLabel);
        BackLabel.setLayout(null);

        JLabel ReapLabel = reaperMascota.getReaperLabel();
        ReapLabel.setBounds(340, 340, 300, 300);
        BackLabel.add(ReapLabel);

        nivelLabel.setSize(200, 60);
        nivelLabel.setLocation(ReaperFrame.getWidth() - nivelLabel.getWidth() - 10, 10);

        // Añadir barras de progreso al frame
        progresoHambre.setBounds(10, 60, 150, 30);
        progresoFelicidad.setBounds(10, 110, 150, 30);
        progresoSuciedad.setBounds(10, 10, 150, 30);
        progresoEnergia.setBounds(10, 160, 150, 30);
        progresoHambre.setForeground(Color.GREEN);
        progresoFelicidad.setForeground(Color.YELLOW);
        progresoSuciedad.setForeground(Color.CYAN);
        progresoEnergia.setForeground(Color.MAGENTA);

        //CONFIGURACION BOTONES
        btnBanar.setBackground(Color.CYAN);
        btnBanar.setPreferredSize(new Dimension(150, 80));
        btnComer.setBackground(Color.GREEN);
        btnComer.setPreferredSize(new Dimension(150, 80));
        btnEntrenar.setBackground(Color.YELLOW);
        btnEntrenar.setPreferredSize(new Dimension(150, 80));
        btnDormir.setBackground(Color.MAGENTA);
        btnDormir.setPreferredSize(new Dimension(150, 80));

        int panelHeight = 100;//Altura del panel
        panelInferior.setBounds(0, 670, 1000, panelHeight);
        panelInferior.setLayout(new GridLayout(1,4));
        panelInferior.setBackground(new Color(0xA7A7A7));
        panelInferior.setPreferredSize(new Dimension(ReaperFrame.getWidth(), panelHeight));
        BackLabel.add(panelInferior);


        BackLabel.add(progresoHambre);
        BackLabel.add(progresoFelicidad);
        BackLabel.add(progresoSuciedad);
        BackLabel.add(progresoEnergia);
        BackLabel.setBounds(0, 0, ReaperFrame.getWidth(), ReaperFrame.getHeight() - panelHeight);


        panelInferior.add(btnComer);
        panelInferior.add(btnEntrenar);
        panelInferior.add(btnDormir);
        panelInferior.add(btnBanar);

    }


    public void configurarListeners(){
            btnBanar.addActionListener(e -> {
                new Thread(() -> {
                    new HiloAnimacion(reaperMascota,"banar").run();
                    SwingUtilities.invokeLater(this::actualizarProgreso);
                    SwingUtilities.invokeLater(() -> ReaperFrame.requestFocusInWindow());
                }).start();
            });

            btnComer.addActionListener(e -> {
                new Thread(() -> {
                    new HiloAnimacion(reaperMascota,"comer").run();
                    SwingUtilities.invokeLater(this::actualizarProgreso);
                    SwingUtilities.invokeLater(() -> ReaperFrame.requestFocusInWindow());
                }).start();
            });

            btnEntrenar.addActionListener(e -> {
                new Thread(() -> {
                    new HiloAnimacion(reaperMascota,"entrenar").run();
                    SwingUtilities.invokeLater(this::actualizarProgreso);
                    SwingUtilities.invokeLater(() -> ReaperFrame.requestFocusInWindow());
                }).start();
            });

            btnDormir.addActionListener(e -> {
                new Thread(() -> {
                    new HiloAnimacion(reaperMascota,"dormir").run();
                    SwingUtilities.invokeLater(this::actualizarProgreso);
                    SwingUtilities.invokeLater(() -> ReaperFrame.requestFocusInWindow());
                }).start();
            });
        };


    public void actualizarProgreso(){
        progresoHambre.setValue(reaperMascota.getHambre());
        progresoFelicidad.setValue(reaperMascota.getFelicidad());
        progresoSuciedad.setValue(reaperMascota.getSuciedad());
        progresoEnergia.setValue(reaperMascota.getEnergia());

        verificarNivel();
    }

    private void verificarNivel() {
        boolean todasBarrasAltas = reaperMascota.getHambre() > 80 && reaperMascota.getFelicidad() > 80 &&
                reaperMascota.getSuciedad() > 80 && reaperMascota.getEnergia() > 80;

        if (todasBarrasAltas && !reaperMascota.isNivelSubido()) {
            reaperMascota.subirNivel();
            nivelLabel.setText("Nivel: " + reaperMascota.getNivel());
        } else if (!todasBarrasAltas) {
            reaperMascota.reiniciarNivelSubido();
        }
    }


    public void iniciarTimer() {
        timer = new Timer(700, e -> {
            reaperMascota.decrementarAtributos(1);
            actualizarProgreso();
            if (reaperMascota.getHambre() >= 50 && reaperMascota.getFelicidad() >= 50 && reaperMascota.getSuciedad() > 50 && reaperMascota.getEnergia() >= 50) {
                reaperMascota.setNivel(reaperMascota.getNivel() + 1);
                nivelLabel.setText("Nivel: " + reaperMascota.getNivel());
            }
        });
        timer.start();
    }

    public void mostrarFrame(){ReaperFrame.setVisible(true);}

}
