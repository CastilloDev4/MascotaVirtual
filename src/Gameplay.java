import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gameplay {

    private JFrame gameplayFrame;
    private JProgressBar progresoHambre;
    private JProgressBar progresoFelicidad;
    private JProgressBar progresoSuciedad;
    private JProgressBar progresoEnergia;
    private Mascota mascota;
    private JLabel fondoLabel;
    private JButton btnComer, btnBanar, btnEntrenar, btnDormir;
    private JPanel panelBarras;



    public Gameplay(){

            inicializarComponentes();
            configurarComponentes();
            configurarListeners();
            mostrarFrame();

    }


    public void inicializarComponentes(){

        gameplayFrame = new JFrame("Virtual Pet");
        //Inicializacion de la mascota con sus valores en 100
        mascota = new Mascota(100, 100, 100, 100);
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

        btnBanar = new JButton("Banar");
        btnComer = new JButton("Comer");
        btnEntrenar = new JButton("Entrenar");
        btnDormir = new JButton("Dormir");

        //panel para almacenar las barras de progreso PERO NO SE SI IMPLEMENTARLO O NO!
        panelBarras = new JPanel();




        // Cargar el fondo desde el classpath
        ImageIcon Fondo = new ImageIcon(getClass().getClassLoader().getResource("mascotaBackground.jpg"));
        fondoLabel = new JLabel(Fondo);




    }

    public void configurarComponentes(){

        gameplayFrame.setSize(1000, 800);
        gameplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameplayFrame.setLocationRelativeTo(null);
        // gameplayFrame.setUndecorated(true);

        gameplayFrame.setContentPane(fondoLabel);

        fondoLabel.setLayout(null);  //

        // Añadir la mascota al fondo
        JLabel mascotaLabel = mascota.getMascotaLabel();
        mascotaLabel.setBounds(50, 380, 300, 300);
        fondoLabel.add(mascotaLabel);


        // Añadir barras de progreso al frame
        progresoHambre.setBounds(10, 10, 150, 30);
        progresoFelicidad.setBounds(10, 60, 150, 30);
        progresoSuciedad.setBounds(10, 110, 150, 30);
        progresoEnergia.setBounds(10, 160, 150, 30);

        //CONFIGURACION BOTONES
        btnBanar.setBounds(10, 230, 80, 50);
        btnBanar.setBackground(Color.CYAN);
        btnComer.setBounds(50, 230, 100, 50);
        btnComer.setBackground(Color.GREEN);
        btnEntrenar.setBounds(100, 230, 100, 50);
        btnEntrenar.setBackground(Color.YELLOW);
        btnDormir.setBounds(150, 230, 100, 50);
        btnDormir.setBackground(Color.MAGENTA);

        //CONFIGURACION PANEL, COMENTADO PARA QUE NO APAREZCA IGUAL QUE LOS BOTONES

        panelBarras.setBounds(0, 0, 300, 320);
        panelBarras.setLayout(null);
        panelBarras.setBackground(Color.WHITE);
        //fondoLabel.add(panelBarras);
        fondoLabel.add(progresoHambre);
        fondoLabel.add(progresoFelicidad);
        fondoLabel.add(progresoSuciedad);
        fondoLabel.add(progresoEnergia);
        /*panelBarras.add(btnBanar);
        panelBarras.add(btnComer);
        panelBarras.add(btnEntrenar);
        panelBarras.add(btnDormir);*/




    }


    public void mostrarFrame(){
        gameplayFrame.setVisible(true);
    }


        // METODO PARA ACTUALIZAR LAS BARRAS CADA VEZ QUE SE OPRIMA UN BOTON, PARA DARLES EL NUEVO VALOR
    private void actualizarBarras() {
        progresoHambre.setValue(mascota.getHambre());
        progresoFelicidad.setValue(mascota.getFelicidad());
        progresoSuciedad.setValue(mascota.getSuciedad());
        progresoEnergia.setValue(mascota.getEnergia());
    }

    public void configurarListeners(){


        }






    }





