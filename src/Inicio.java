import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Inicio {

    public static JFrame inicioFrame;
    private JLabel fondoLabel;
    private JLabel bienvenido;
    private JButton btnJugar, btnSalir;


    public Inicio() {
        inicializarComponentes();
        configurarComponentes();
        configurarListeners();
        mostrarFrame();

    }


    public void inicializarComponentes() {

        inicioFrame = new JFrame("Bienvenido");
        // GET CLASS,GETCLASSLOADER Y GET RESOURCE ES PARA OBTENER LA RUTA DE LA IMAGEN
        //LA CARPETA RESOURCES ES LA CARPETA DONDE SE VAN AÑADIR TODAS LAS IMAGENES Y LA RUTA VA A SER SIEMPRE LA MISMA
        // SOLO CAMBIARA EL NOMBRE DEPENDE LA IMAGEN O GIF
        ImageIcon gifFondo = new ImageIcon(getClass().getClassLoader().getResource("background.gif"));
        fondoLabel = new JLabel(gifFondo);

        bienvenido = new JLabel("BIENVENIDO A VIRTUAL PET");
        btnJugar = new JButton("JUGAR");
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

        btnJugar.setBounds(230, 300, 100, 50);
        btnJugar.setBackground(Color.GREEN);
        fondoLabel.add(btnJugar);
        btnSalir.setBounds(230, 360, 100, 50);
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


    }


    public void mostrarFrame() {
        inicioFrame.setVisible(true);
    }




}








