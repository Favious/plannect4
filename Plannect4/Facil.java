import javax.swing.*;

public class Facil extends Juego
{
    public Facil(int numeroDeFilas, int numeroDeColumnas) {
        super(numeroDeFilas, numeroDeColumnas);
    }
     
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new Facil(7,8);
            }
        });
    }
}
