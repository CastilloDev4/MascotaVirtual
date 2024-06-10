import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Inicio {

    public static JFrame inicioFrame;
    private JLabel fondoLabel;
    private JLabel bienvenido;
    private JLabel perrito, fantasma;
    private ImageIcon imagePerrito, imageFantasma;
    private JButton btnJugar1,btnJugar2, btnSalir;
    private Mascota mascota;
    private MascotaReaper reaperMascota;
    private  Sound sound;
   // private Gameplay gameplay;


    public Inicio() {
        inicializarComponentes();
        configurarComponentes();
        configurarSonido("src/Sounds/inicio.wav");
        configurarListeners();
        mostrarFrame();

    }


    public void inicializarComponentes() {

        inicioFrame = new JFrame("Bienvenido");
        //gameplay = new Gameplay();
        // GET CLASS,GETCLASSLOADER Y GET RESOURCE ES PARA OBTENER LA RUTA DE LA IMAGEN
        //LA CARPETA RESOURCES ES LA CARPETA DONDE SE VAN AÑADIR TODAS LAS IMAGENES Y LA RUTA VA A SER SIEMPRE LA MISMA
        // SOLO CAMBIARA EL NOMBRE DEPENDE LA IMAGEN O GIF
        ImageIcon gifFondo = new ImageIcon(getClass().getClassLoader().getResource("background.gif"));
        fondoLabel = new JLabel(gifFondo);bienvenido = new JLabel("BIENVENIDO A VIRTUAL PET");
        imagePerrito = new ImageIcon("src/Imagenes/perrito.png");
        imageFantasma = new ImageIcon("src/Imagenes/fantasma.png");
        bienvenido = new JLabel("BIENVENIDO A VIRTUAL PET");
        btnJugar1 = new JButton("DogChaw");
        btnJugar2 = new JButton("Fantasmin");
        btnSalir = new JButton("SALIR");

    }

    public void configurarComponentes() {

        inicioFrame.setSize(600, 600);
        inicioFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicioFrame.setLocationRelativeTo(null);
        inicioFrame.setContentPane(fondoLabel);
        inicioFrame.setUndecorated(true);

        fondoLabel.setLayout(null);
        bienvenido.setBounds(80, -200, 500, 500);
        bienvenido.setFont(new Font("Arial", Font.BOLD, 30));
        bienvenido.setForeground(Color.BLACK);
        fondoLabel.add(bienvenido);

        Image imageP = imagePerrito.getImage().getScaledInstance(350, 380, Image.SCALE_SMOOTH);
        ImageIcon sImagePerrito = new ImageIcon(imageP);
        perrito = new JLabel(sImagePerrito);
        perrito.setBounds(-10, -35, 400, 400);
        fondoLabel.add(perrito);

        Image imageF = imageFantasma.getImage().getScaledInstance(120, 150, Image.SCALE_SMOOTH);
        ImageIcon sImageFantasma = new ImageIcon(imageF);
        fantasma = new JLabel(sImageFantasma);
        fantasma.setBounds(180, 25, 400, 400);
        fondoLabel.add(fantasma);

        btnJugar1.setBounds(160, 300, 100, 50);
        btnJugar1.setBackground(Color.GREEN);
        btnJugar2.setBounds(320, 300, 100, 50);
        btnJugar2.setBackground(Color.GREEN);
        fondoLabel.add(btnJugar1);
        fondoLabel.add(btnJugar2);
        btnSalir.setBounds(230, 400, 100, 50);
        btnSalir.setBackground(Color.RED);
        fondoLabel.add(btnSalir);

    }

    public void configurarListeners() {
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnJugar1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.detener();
                mascota = new Mascota(50, 50, 50, 50);
                mascota.CargarAnimaciones();
                Gameplay gameplay = new Gameplay();
                gameplay.mostrarFrame();
                inicioFrame.dispose();
            }
        });

        btnJugar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sound.detener();
                reaperMascota = new MascotaReaper(100, 100, 100, 100);
                reaperMascota.CargarAnimaciones();
                GameplayReaper gameplayReaper = new GameplayReaper(reaperMascota);
                inicioFrame.dispose();
            }
        });
    }

    public void configurarSonido(String ruta) {
        sound = new Sound();
        sound.cargarSonido(ruta);
        sound.iniciarReproduccionEnHilo();
    }

    public void mostrarFrame() {
        inicioFrame.setVisible(true);
    }

}








