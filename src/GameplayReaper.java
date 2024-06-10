import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class GameplayReaper /*implements Serializable*/ {

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
    private Guardado guardado = new Guardado();
    private Sound sound;

    public GameplayReaper(MascotaReaper reaperMascota){
        this.reaperMascota = reaperMascota;
        inicializarComponentes();
        configurarComponentes();
        configurarListeners();
        iniciarTimer();
        mostrarFrame();
        configurarSonido("src/Sounds/fantasma.wav");

    }
    public void guardarDatos() {
        guardado.guardarDatosReaper(reaperMascota);
    }

    public void inicializarComponentes(){
        ReaperFrame = new JFrame("Reaper Pet");
        //Guadado de datos
        this.reaperMascota = guardado.cargarDatosReaper();
        if (this.reaperMascota == null) {
            this.reaperMascota = new MascotaReaper(50, 50, 50, 50);
        }

        Timer timerReaper = new Timer(10000, e -> guardarDatos());
        timerReaper.start();

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
            configurarSonidoAcciones("src/Sounds/bañar-f.wav");

        });

        btnComer.addActionListener(e -> {
            new Thread(() -> {
                new HiloAnimacion(reaperMascota,"comer").run();
                SwingUtilities.invokeLater(this::actualizarProgreso);
                SwingUtilities.invokeLater(() -> ReaperFrame.requestFocusInWindow());
            }).start();
            configurarSonidoAcciones("src/Sounds/comer.wav");

        });

        btnEntrenar.addActionListener(e -> {
            new Thread(() -> {
                new HiloAnimacion(reaperMascota,"entrenar").run();
                SwingUtilities.invokeLater(this::actualizarProgreso);
                SwingUtilities.invokeLater(() -> ReaperFrame.requestFocusInWindow());
            }).start();
            configurarSonidoAcciones("src/Sounds/entrenar-f.wav");

        });

        btnDormir.addActionListener(e -> {
            new Thread(() -> {
                new HiloAnimacion(reaperMascota,"dormir").run();
                SwingUtilities.invokeLater(this::actualizarProgreso);
                SwingUtilities.invokeLater(() -> ReaperFrame.requestFocusInWindow());
            }).start();
            configurarSonidoAcciones("src/Sounds/dormir.wav");

        });

        };


    public void actualizarProgreso(){
        progresoHambre.setValue(reaperMascota.getHambre());
        progresoFelicidad.setValue(reaperMascota.getFelicidad());
        progresoSuciedad.setValue(reaperMascota.getSuciedad());
        progresoEnergia.setValue(reaperMascota.getEnergia());

        verificarNivel();
        verificarMuerte();
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
    public void verificarMuerte() {
        if (reaperMascota.getHambre() <= 0 && reaperMascota.getFelicidad() <= 0 && reaperMascota.getSuciedad() <= 0 && reaperMascota.getEnergia() <= 0) {
            //JOptionPane.showMessageDialog(ReaperFrame, "Perdiste! tu mascota ha muerto </3", "Game Over", JOptionPane.ERROR_MESSAGE);
            configurarSonidoAcciones("src/Sounds/Game Over.wav");
            mostrarVentanaPerdida();

            // Resetear las barras de progreso y el nivel de la mascota
            reaperMascota.setHambre(50);
            reaperMascota.setFelicidad(50);
            reaperMascota.setSuciedad(50);
            reaperMascota.setEnergia(50);
            reaperMascota.setNivel(1);

            // Guardar los datos reseteados
            guardarDatos();

            System.exit(0);
        }
    }

    public static void mostrarVentanaPerdida() {
        JPanel panel = new JPanel() {
            private Image backgroundImage;
            {
                try {
                    backgroundImage = ImageIO.read(new File("src/Imagenes/imagen.jpg")); // Ruta de la imagen
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel labelMensaje = new JLabel("Perdiste! tu mascota ha muerto </3");
        labelMensaje.setForeground(Color.WHITE);
        labelMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelMensaje.setFont(new Font("Arial", Font.BOLD, 16));


        // Añadir espacio entre el fondo y el texto
        panel.add(Box.createVerticalGlue());
        panel.add(labelMensaje);
        panel.add(Box.createVerticalGlue());

        // Configurar el JOptionPane
        JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = optionPane.createDialog("Fin del Juego");
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

//DELAY MANEJA LA VELOCIDAD EN LA QUE DECREMENTAN LAS BARRAS
    public void iniciarTimer() {
        timer = new Timer(500, e -> {
            reaperMascota.decrementarAtributos(1);
            actualizarProgreso();

        });
        timer.start();
    }

    public void configurarSonido(String ruta) {
        sound = new Sound();
        sound.cargarSonido(ruta);
        sound.iniciarReproduccionEnHilo();
    }
    public void configurarSonidoAcciones(String ruta){
        sound = new Sound();
        sound.detener();
        sound.cargarSonido(ruta);
        sound.reproducir();
    }

    public void mostrarFrame(){ReaperFrame.setVisible(true);}

}
