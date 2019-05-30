import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Juego {
    JFrame frame;
    JPanel panel;
    final int numeroDeFilas;
    final int numeroDeColumnas;
    static int[][] tablaLogica;
    int fila, columna, filaSeleccionada, columnaSeleccionada;
    int turno;
    boolean gano;
    JButton[][] botones;
    GridLayout cuadricula;
    final ImageIcon c0 = new ImageIcon("c0.png");
    final ImageIcon c1;
    final ImageIcon c2;
    public Juego(int numeroDeFilas, int numeroDeColumnas) {
        this.numeroDeFilas = numeroDeFilas;
        this.numeroDeColumnas = numeroDeColumnas;
        tablaLogica = new int[numeroDeFilas][numeroDeColumnas];
        turno = 0;
        gano = false;
        botones = new JButton[numeroDeFilas][numeroDeColumnas];
        cuadricula = new GridLayout(numeroDeFilas, numeroDeColumnas);
        c1 = new ImageIcon(crearPlaneta(1));
        c2 = new ImageIcon(crearPlaneta(2));
        frame = new JFrame("Plannect 4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(numeroDeFilas * 100, numeroDeColumnas * 100 - 150));
        panel = new JPanel();
        panel.setLayout(cuadricula);
        
        rellenarJuego();
        
        frame.setContentPane(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.getContentPane().setBackground(new Color(15, 16, 89));
        frame.setVisible(true);
    }
    
    public boolean verificarSiGano() {
        // check for a horizontal win
        for (int x = 0; x < numeroDeFilas; x++) {
            for (int y = 0; y < numeroDeColumnas - 3; y++) {
                if (tablaLogica[x][y] != 0 && tablaLogica[x][y] != -1 && tablaLogica[x][y] == tablaLogica[x][y+1] && tablaLogica[x][y] == tablaLogica[x][y+2] && tablaLogica[x][y] == tablaLogica[x][y+3]) {
                    gano = true;
                }
            }
        }
        // check for a vertical win
        for (int x = 0; x < numeroDeFilas - 3; x++) {
            for (int y = 0; y < numeroDeColumnas; y++) {
                if (tablaLogica[x][y] != 0 && tablaLogica[x][y] != -1 &&tablaLogica[x][y] == tablaLogica[x+1][y] && tablaLogica[x][y] == tablaLogica[x+2][y] && tablaLogica[x][y] == tablaLogica[x+3][y]) {
                    gano = true;
                }
            }
        }
        // check for a diagonal win (positive slope)
        for (int x = 0; x < numeroDeFilas - 3; x++) {
            for (int y = 0; y < numeroDeColumnas - 3; y++) {
                if (tablaLogica[x][y] != 0 && tablaLogica[x][y] != -1 && tablaLogica[x][y] == tablaLogica[x+1][y+1] && tablaLogica[x][y] == tablaLogica[x+2][y+2] && tablaLogica[x][y] == tablaLogica[x+3][y+3]) {
                    gano = true;
                }
            }
        }
        // check for a diagonal win (negative slope)
        for (int x = numeroDeFilas - 3; x < numeroDeFilas; x++) {
            for (int y = 0; y < numeroDeColumnas - 3; y++) {
                if (tablaLogica[x][y] != 0 && tablaLogica[x][y] != -1 && tablaLogica[x][y] == tablaLogica[x-1][y+1] && tablaLogica[x][y] == tablaLogica[x-2][y+2] && tablaLogica[x][y] == tablaLogica[x-3][y+3]) {
                    gano = true;
                }
            }
        }
        return gano;
    }
    
    public void rellenarJuego() {
        //-1 are empty slots not allowed to go in (nothing is under them)
        //0 are empty slots allowed to fill, either bottom most or already a piece under them
        //1 for player1, 2 for player2
        for (int x = numeroDeFilas - 2; x >= 0; x--) {
            for (int y = numeroDeColumnas - 1; y >= 0; y--) {
                tablaLogica[x][y] = -1;
            }
        }       
        for (fila = 0; fila <= numeroDeFilas - 1; fila++) {
            for (columna = 0; columna <= numeroDeColumnas - 1; columna++) {
                botones[fila][columna] = new JButton(c0);
                botones[fila][columna].addActionListener(new AccionDeBoton());
                panel.add(botones[fila][columna]);
            }
        }
    }
    
    class AccionDeBoton implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            for (fila = numeroDeFilas - 1; fila >= 0; fila--) {
                for (columna = numeroDeColumnas - 1; columna >= 0; columna--) {
                    if (botones[fila][columna] == event.getSource()) {
                        if (turno % 2 == 0 && tablaLogica[fila][columna] == 0) {
                            botones[fila][columna].setIcon(c1);
                            tablaLogica[fila][columna] = 1;
                            try {
                                tablaLogica[fila - 1][columna] = 0;
                            } catch (ArrayIndexOutOfBoundsException e) {
                                System.out.println("Se alcanzo el borde de la columna " + (columna + 1));
                            }
                            System.out.println("Jugador 1 mueve a columna " + (columna + 1) + " y fila " + (fila + 1));
                            if (verificarSiGano()) {
                                System.out.println("Gana el jugador 1");
                                pararJuego();
                                break;
                            }
                            turno = turno + 1;
                            jugar();
                            break;
                        } else {
                            System.out.println("Accion no valida");//poker face
                        }
                    }
                }
            }
        }
    }
    
    public void jugar() {
        int columnaIA = (int)(Math.random() * (numeroDeColumnas - 0) + 0);
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
    
    public int posicionDisponible(int posicionColumna) {
        int respuesta = -1;
        fila = numeroDeFilas - 1;
        while (fila >= 0) {
              if(tablaLogica[fila][posicionColumna] == 0) {    
                  respuesta = fila;
              }
              fila--;
        } 
        return respuesta;
    }
    
    public void pararJuego() {
        for (int x = numeroDeFilas - 1; x >=0; x--) {
            for (int y = numeroDeColumnas - 1; y >= 0; y--) {
                tablaLogica[x][y] = -1;
            }
        }
    }
    
    public String crearPlaneta(int numero) {
        int respuesta;
        String planeta = "";
        if(numero == 1) {
            respuesta = (int)(Math.random()*(4 - 1) + 1);
            if(respuesta == 1) {
                planeta = "Mercurio1.png";
            } else if(respuesta == 2) {
                planeta = "Venus2.png";
            } else if(respuesta == 3) {
                planeta = "Tierra3.png";
            } else if(respuesta == 4) {
                planeta = "Marte4.png";
            }
        } else {
            respuesta = (int)(Math.random()*(8 - 5) + 5);
            if(respuesta == 5) {
                planeta = "Saturno5.png";
            } else if(respuesta == 6) {
                planeta = "Jupiter6.png";
            } else if(respuesta == 7) {
                planeta = "Neptuno7.png";
            } else if(respuesta == 8) {
                planeta = "Urano8.png"; 
            }
        }
        return planeta;
    }
}