import javax.swing.JFrame;

public class Dificil extends Intermedio
{
    public Dificil(int numeroDeFilas, int numeroDeColumnas) {
         super(numeroDeFilas, numeroDeColumnas);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame.setDefaultLookAndFeelDecorated(true);
                new Dificil(9,10);
            }
        });
    }
    
    @Override
    public void jugar() {
        int columnaIA;
        if(!hayVentaja()){
            if(hayRiesgoVertical() == -1) {
                columnaIA = (int)(Math.random() * (numeroDeColumnas - 1) + 1);
            } else {
                columnaIA = hayRiesgoVertical();
            }
            if(hayRiesgoHorizontal() != -1) {
                columnaIA = hayRiesgoHorizontal();
            }
            if(hayRiesgoDiagonalIzquierda() != -1) {
                columnaIA = hayRiesgoDiagonalIzquierda();
            }
            if(hayRiesgoDiagonalDerecha() != -1) {
                columnaIA = hayRiesgoDiagonalDerecha();
            }
        } else {
            if(ventajaVertical() == -1) {
                columnaIA = (int)(Math.random() * (numeroDeColumnas - 1) + 1);
            } else {
                columnaIA = ventajaVertical();
            }
            if(ventajaHorizontal() != -1) {
                columnaIA = ventajaHorizontal();
            }
            if(ventajaDiagonalIzquierda() != -1) {
                columnaIA = ventajaDiagonalIzquierda();
            }
            if(ventajaDiagonalDerecha() != -1) {
                columnaIA = ventajaDiagonalDerecha();
            }
        }
        int filaIA = posicionDisponible(columnaIA);
        while(filaIA == -1) {
            columnaIA = (int)(Math.random() * (numeroDeColumnas - 1) + 1);
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
    
    public int ventajaHorizontal() {
        int hay = -1;
        for (int x = 0; x < numeroDeFilas; x++) {
            for (int y = 0; y < numeroDeColumnas - 3; y++) {
                if (tablaLogica[x][y] != 0 && tablaLogica[x][y] != -1 && 2 == tablaLogica[x][y+1] && 2 == tablaLogica[x][y+2] && tablaLogica[x][y+3] == 0) {
                    hay = y + 3;
                    break;
                }
                if(tablaLogica[x][y] == 0 && 2 == tablaLogica[x][y+1] && 2 == tablaLogica[x][y+2] && 2 == tablaLogica[x][y+3]) {
                    hay = y;
                    break;
                }
            }
        }
        return hay;
    }
    
    public int ventajaVertical() {
        int hay = -1;
        for (int x = 0; x < numeroDeFilas - 3; x++) {
            for (int y = 0; y < numeroDeColumnas; y++) {
                if (tablaLogica[x][y] != -1 && 0 == tablaLogica[x][y] && 2 == tablaLogica[x + 1][y] && 2 == tablaLogica[x+2][y] && 2 == tablaLogica[x+3][y]) {
                    hay = y;
                    break;
                }
            }
        }
        return hay;
    }
    
    public int ventajaDiagonalIzquierda() {
        int hay = -1;
        for (int x = 0; x < numeroDeFilas - 3; x++) {
            for (int y = 0; y < numeroDeColumnas - 3; y++) {
                if (tablaLogica[x][y] == 0 && tablaLogica[x][y] != -1 && 2 == tablaLogica[x+1][y+1] && 2 == tablaLogica[x+2][y+2] && 2 == tablaLogica[x+3][y+3]) {
                    hay = y;
                    break;
                }
                if (tablaLogica[x][y] == 2 && tablaLogica[x][y] != -1 && 2 == tablaLogica[x+1][y+1] && 2 == tablaLogica[x+2][y+2] && 0 == tablaLogica[x+3][y+3]) {
                    hay = y + 3;
                    break;
                }
            }
        }
        return hay;
    }
    
    public int ventajaDiagonalDerecha() {
        int hay = -1;
        for (int x = numeroDeFilas - 3; x < numeroDeFilas; x++) {
            for (int y = 0; y < numeroDeColumnas - 3; y++) {
                if (tablaLogica[x][y] == 2 && tablaLogica[x][y] != -1 && 2 == tablaLogica[x-1][y+1] && 2 == tablaLogica[x-2][y+2] && 0 == tablaLogica[x-3][y+3]) {
                    hay = y + 3;
                    break;
                }
                if (tablaLogica[x][y] == 0 && tablaLogica[x][y] != -1 && 2 == tablaLogica[x-1][y+1] && 2 == tablaLogica[x-2][y+2] && 2 == tablaLogica[x-3][y+3]) {
                    hay = y;
                    break;
                }
            }
        }
        return hay;
    }
    
    public boolean hayVentaja() {
        boolean hay = false;
        if(ventajaHorizontal() != -1 || ventajaVertical() != -1 || ventajaDiagonalIzquierda() != -1 || ventajaDiagonalDerecha() != -1) {
            hay = true;
        }
        return hay;
    }
}
