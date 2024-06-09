import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.io.Serializable;

public class Gameplay implements Serializable {
    private AutoGuardado autoGuardado;
    private Sound sound;
    private JFrame gameplayFrame;
    private JProgressBar progresoHambre;
    private JProgressBar progresoFelicidad;
    private JProgressBar progresoSuciedad;
    private JProgressBar progresoEnergia;
    private Mascota mascota;
    private JLabel fondoLabel;
    private JButton btnComer, btnBanar, btnEntrenar, btnDormir;
    private JPanel panelInferior;
    private Timer timer;
    private Timer timer1, timer2, timer3;
    private JLabel nivelLabel;


//<<<<<<< Updated upstream
//=======
    public Gameplay() {
        //this.fondoLabel = new JLabel();

        inicializarComponentes();
        configurarComponentes();
        configurarListeners();
        cargarEstados(); //👻👻👻👻👻
        mostrarFrame();
        iniciarTimer();
        configurarSonido("src/Sounds/Clear-Skies.wav"); //👻👻👻👻👻
        iniciarTimerAutoguardado(); //👻👻👻👻👻

    }


    public void inicializarComponentes(){
        gameplayFrame = new JFrame("Virtual Pet");
        //Inicializacion de la mascota con sus valores en 100
        mascota = new Mascota(50, 50, 50, 50);
        //Inicializar barras de progreso
        progresoHambre = new JProgressBar(0, 100);
        progresoFelicidad = new JProgressBar(0, 100);
        progresoSuciedad = new JProgressBar(0, 100);
        progresoEnergia = new JProgressBar(0, 100);

        // Setiar barras de progreso con los valores iniciales de la mascota
        progresoHambre.setValue(mascota.getHambre());
        progresoFelicidad.setValue(mascota.getFelicidad());
        progresoSuciedad.setValue(mascota.getSuciedad());
        progresoEnergia.setValue(mascota.getEnergia());

        //inicializacion de los botones

        btnBanar = new JButton("Bañar");
        btnComer = new JButton("Comer");
        btnEntrenar = new JButton("Entrenar");
        btnDormir = new JButton("Dormir");

        //panel para almacenar las barras de progreso PERO NO SE SI IMPLEMENTARLO O NO!
        panelInferior = new JPanel();
        panelInferior.setBackground(Color.WHITE);



        // Cargar el fondo desde el classpath
        ImageIcon Fondo = new ImageIcon(getClass().getClassLoader().getResource("mascotaBackground.jpg"));
        Image FondoEscalado = Fondo.getImage().getScaledInstance(1000, 800, Image.SCALE_SMOOTH);
        Fondo = new ImageIcon(FondoEscalado);
        fondoLabel = new JLabel(Fondo);

        // Añadir el KeyListener
        Movimiento movimiento = new Movimiento(mascota);
        gameplayFrame.addKeyListener(movimiento);
        gameplayFrame.setFocusable(true); // Asegurarse de que el frame sea focusable
        gameplayFrame.requestFocusInWindow(); // Solicitar el foco

        nivelLabel = new JLabel("Nivel: " + mascota.getNivel());
        nivelLabel.setFont(new Font("Arial", Font.BOLD, 40));
        nivelLabel.setForeground(Color.WHITE);
        fondoLabel.add(nivelLabel);

    }

    public void configurarComponentes() {

        gameplayFrame.setSize(1000, 800);
        gameplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameplayFrame.setLocationRelativeTo(null);
        // gameplayFrame.setUndecorated(true);

        gameplayFrame.setContentPane(fondoLabel);

        fondoLabel.setLayout(null);  //

        // Añadir la mascota al fondo
        JLabel mascotaLabel = mascota.getMascotaLabel();
        mascotaLabel.setBounds(310, 360, 350, 350);
        fondoLabel.add(mascotaLabel);


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
        btnBanar.setPreferredSize(new Dimension(150, 100));
        btnComer.setBackground(Color.GREEN);
        btnComer.setPreferredSize(new Dimension(150, 100));
        btnEntrenar.setBackground(Color.YELLOW);
        btnEntrenar.setPreferredSize(new Dimension(150, 100));
        btnDormir.setBackground(Color.MAGENTA);
        btnDormir.setPreferredSize(new Dimension(150, 100));

        //CONFIGURACION PANEL, COMENTADO PARA QUE NO APAREZCA IGUAL QUE LOS BOTONES
        int panelHeight = 200;//Altura del panel
        panelInferior.setBounds(0, 670, 1000, 100);
        panelInferior.setLayout(new GridLayout(1,4));
        panelInferior.setBackground(new Color(0xA7A7A7));
        panelInferior.setPreferredSize(new Dimension(gameplayFrame.getWidth(), panelHeight));

        //Agregar panel al fondo
        fondoLabel.add(panelInferior);

        //Agregar barras de progreso al fondo
        fondoLabel.add(progresoHambre);
        fondoLabel.add(progresoFelicidad);
        fondoLabel.add(progresoSuciedad);
        fondoLabel.add(progresoEnergia);
        fondoLabel.setBounds(0, 0, gameplayFrame.getWidth(), gameplayFrame.getHeight() - panelHeight);

        //Agregrar botones
        panelInferior.add(btnBanar);
        panelInferior.add(btnComer);
        panelInferior.add(btnEntrenar);
        panelInferior.add(btnDormir);

        //Label nivel
        nivelLabel.setSize(200, 60);
        nivelLabel.setLocation(gameplayFrame.getWidth() - nivelLabel.getWidth() - 10, 10);


    }

    ///METODO PARA MOSTRAR EL FRAME
    public void mostrarFrame(){
        gameplayFrame.setVisible(true);
    }

    // METODO PARA ACTUALIZAR LAS BARRAS CADA VEZ QUE SE OPRIMA UN BOTON, PARA DARLES EL NUEVO VALOR
    public  void actualizarBarras() {
        progresoHambre.setValue(mascota.getHambre());
        progresoFelicidad.setValue(mascota.getFelicidad());
        progresoSuciedad.setValue(mascota.getSuciedad());
        progresoEnergia.setValue(mascota.getEnergia());

        verificarNivel();
        verificarMuerte();
    }

    private void verificarNivel() {
        boolean todasBarrasAltas = mascota.getHambre() > 80 && mascota.getFelicidad() > 80 &&
                mascota.getSuciedad() > 80 && mascota.getEnergia() > 80;

        if (todasBarrasAltas && !mascota.isNivelSubido()) {
            mascota.subirNivel();
            // JOptionPane.showMessageDialog(gameplayFrame, " Tu mascota ha subido al nivel " + mascota.getNivel());
            nivelLabel.setText("Nivel: " + mascota.getNivel());
        } else if (!todasBarrasAltas) {
            mascota.reiniciarNivelSubido();
        }
    }

    public void verificarMuerte() {
        if (mascota.getHambre() <= 0 && mascota.getFelicidad() <= 0 && mascota.getSuciedad() <= 0 && mascota.getEnergia() <= 0) {
            JOptionPane.showMessageDialog(gameplayFrame, "Perdiste! tu mascota ha muerto </3","Game Over", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    public void configurarListeners()
//<<<<<<< Updated upstream
    {
        btnBanar.addActionListener(e -> {
            new Thread(() -> {
                new HiloBanar(mascota).run();
                SwingUtilities.invokeLater(this::actualizarBarras);
                SwingUtilities.invokeLater(() -> gameplayFrame.requestFocusInWindow());
            }).start();
            configurarSonidoAcciones("src/Sounds/bañar.wav");

        });

        btnComer.addActionListener(e -> {
            new Thread(() -> {
                new HiloComer(mascota).run();
                SwingUtilities.invokeLater(this::actualizarBarras);
                SwingUtilities.invokeLater(() -> gameplayFrame.requestFocusInWindow());
            }).start();
            configurarSonidoAcciones("src/Sounds/comer.wav");

        });

        btnEntrenar.addActionListener(e -> {
            new Thread(() -> {
                new HiloEntrenar(mascota).run();
                SwingUtilities.invokeLater(this::actualizarBarras);
                SwingUtilities.invokeLater(() -> gameplayFrame.requestFocusInWindow());
            }).start();
            configurarSonidoAcciones("src/Sounds/entrenar.wav");

        });

        btnDormir.addActionListener(e -> {
            new Thread(() -> {
                new HiloDormir(mascota).run();
                SwingUtilities.invokeLater(this::actualizarBarras);
                SwingUtilities.invokeLater(() -> gameplayFrame.requestFocusInWindow());
            }).start();
            configurarSonidoAcciones("src/Sounds/dormir.wav");

        });

//
//        // Configurar listeners para los botones
//        btnBanar.addActionListener(e -> {
//            new Thread(new HiloBanar(mascota)).start();
//
//        });
//
//        btnComer.addActionListener(e -> {
//            new Thread(new HiloComer(mascota)).start();//HiloComer funciona como un hilo que se encarga de alimentar a la mascota
//            // actualizarBarras();
//
//        });
//
//        btnEntrenar.addActionListener(e -> {
//            new Thread(new HiloEntrenar(mascota)).start();//HiloEntrenar funciona como un hilo que se encarga de entrenar a la mascota
//            //actualizarBarras();
//        });
//
//        btnDormir.addActionListener(e -> {
//            new Thread(new HiloDormir(mascota)).start();//HiloDormir funciona como un hilo que se encarga de dormir a la mascota
//            //actualizarBarras();
//
//        });
    }
    //TIMER QUE SE ENCARGA DE DECREMENTAR LAS BARRAS DE PROGRESO
    public void iniciarTimer() {
        timer = new Timer(500, e -> {
            // Crea un Timer que se dispara cada 1000 milisegundos (1 segundo)
            mascota.decrementarAtributos(1);
            actualizarBarras();
        });
        timer.start();
    }

    public void configurarSonido(String ruta) { //👻👻👻👻👻
        sound = new Sound();
        sound.cargarSonido(ruta);
        sound.iniciarReproduccionEnHilo();
    }
    public void configurarSonidoAcciones(String ruta){ //👻👻👻👻👻
        sound = new Sound();
        sound.detener();
        sound.cargarSonido(ruta);
        sound.reproducir();
    }
    private void iniciarTimerAutoguardado() { //👻👻👻👻👻
        autoGuardado = new AutoGuardado();
        timer1 = new Timer(10000, e -> autoGuardado.guardarDatos(mascota.getHambre(), mascota.getFelicidad(), mascota.getEnergia(), mascota.getSuciedad(), mascota.getNivel()));
        new AutoGuardado();
        timer1.start();
        timer2 = new Timer(10000, e -> autoGuardado.imprimirLista());
        timer2.start();
        timer3 = new Timer(10000, e -> autoGuardado.guardarEstado());
        timer3.start();
   }

   public void cargarEstados(){ //👻👻👻👻👻
        autoGuardado = new AutoGuardado();
        autoGuardado.cargarLista();
        autoGuardado.actualizarDatos();
   }
}







