import javax.swing.*;
import java.awt.event.*;

public class Intermedio extends Juego
{
    public Intermedio(int numeroDeFilas, int numeroDeColumnas) {
         super(numeroDeFilas, numeroDeColumnas);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new Intermedio(8,9);
            }
        });
    }
    
    @Override
    public void jugar() {
        int columnaIA;
        if(hayRiesgoVertical() == -1) {
            columnaIA = (int)(Math.random() * (numeroDeColumnas - 0) + 0);
        } else {
            columnaIA = hayRiesgoVertical();
        }
        if(hayRiesgoHorizontal() == -1) {
            
        } else {
            columnaIA = hayRiesgoHorizontal();
        }
        if(hayRiesgoDiagonalIzquierda() == -1) {
            
        } else {
            columnaIA = hayRiesgoDiagonalIzquierda();
        }
        if(hayRiesgoDiagonalDerecha() == -1) {
            
        } else {
            columnaIA = hayRiesgoDiagonalDerecha();
        }
        int filaIA = posicionDisponible(columnaIA);
        while(filaIA == -1) {
            columnaIA = (int)(Math.random() * (numeroDeColumnas - 0) + 0);
            filaIA = posicionDisponible(columnaIA);
        }
        botones[filaIA][columnaIA].setIcon(c2);
        tablaLogica[filaIA][columnaIA] = 2;
        try {
             tablaLogica[filaIA - 1][columnaIA] = 0;
        } catch (ArrayIndexOutOfBoundsException e) {
             System.out.println("Se alcanzo el borde de la columna " + (columnaIA + 1));
        }
        System.out.println("Computadora mueve a columna " + (columnaIA + 1) + " y fila " + (filaIA + 1));
        if (verificarSiGano()) {
             System.out.println("Gana la computadora");
             pararJuego();
        }
        turno = turno + 1;
    }
    
    public int hayRiesgoHorizontal() {
        int hay = -1;
        for (int x = 0; x < numeroDeFilas; x++) {
            for (int y = 0; y < numeroDeColumnas - 3; y++) {
                if (tablaLogica[x][y] != 0 && tablaLogica[x][y] != -1 && 1 == tablaLogica[x][y+1] && 1 == tablaLogica[x][y+2] && tablaLogica[x][y+3] == 0) {
                    hay = y + 3;
                    break;
                }
                if(tablaLogica[x][y] == 0 && 1 == tablaLogica[x][y+1] && 1 == tablaLogica[x][y+2] && 1 == tablaLogica[x][y+3]) {
                    hay = y;
                    break;
                }
            }
        }
        return hay;
    }
    
    public int hayRiesgoVertical() {
        int hay = -1;
        for (int x = 0; x < numeroDeFilas - 3; x++) {
            for (int y = 0; y < numeroDeColumnas; y++) {
                if (tablaLogica[x][y] != -1 && 0 == tablaLogica[x][y] && 1 == tablaLogica[x + 1][y] && 1 == tablaLogica[x+2][y] && 1 == tablaLogica[x+3][y]) {
                    hay = y;
                    break;
                }
            }
        }
        return hay;
    }
    
    public int hayRiesgoDiagonalIzquierda() {
        int hay = -1;
        for (int x = 0; x < numeroDeFilas - 3; x++) {
            for (int y = 0; y < numeroDeColumnas - 3; y++) {
                if (tablaLogica[x][y] == 0 && tablaLogica[x][y] != -1 && 1 == tablaLogica[x+1][y+1] && 1 == tablaLogica[x+2][y+2] && 1 == tablaLogica[x+3][y+3]) {
                    hay = y;
                    break;
                }
                if (tablaLogica[x][y] == 1 && tablaLogica[x][y] != -1 && 1 == tablaLogica[x+1][y+1] && 1 == tablaLogica[x+2][y+2] && 0 == tablaLogica[x+3][y+3]) {
                    hay = y + 3;
                    break;
                }
            }
        }
        return hay;
    }
    
    public int hayRiesgoDiagonalDerecha() {
        int hay = -1;
        for (int x = numeroDeFilas - 3; x < numeroDeFilas; x++) {
            for (int y = 0; y < numeroDeColumnas - 3; y++) {
                if (tablaLogica[x][y] == 1 && tablaLogica[x][y] != -1 && 1 == tablaLogica[x-1][y+1] && 1 == tablaLogica[x-2][y+2] && 0 == tablaLogica[x-3][y+3]) {
                    hay = y + 3;
                    break;
                }
                if (tablaLogica[x][y] == 0 && tablaLogica[x][y] != -1 && 1 == tablaLogica[x-1][y+1] && 1 == tablaLogica[x-2][y+2] && 1 == tablaLogica[x-3][y+3]) {
                    hay = y;
                    break;
                }
            }
        }
        return hay;
    }
}
