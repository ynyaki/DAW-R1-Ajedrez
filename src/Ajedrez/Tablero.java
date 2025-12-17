import java.util.InputMismatchException;

public class Tablero {

    public static final String NEGRO = "\u001B[40m";
    public static final String BLANCO = "\u001B[47m";
    public static final String RESET = "\u001B[0m";

    private final Pieza[][] tablero;
    private final int nCols;
    private final int nFilas;

    public Tablero(int nCols, int nFilas) {
        if(nCols < 8 || nFilas < 8)
            throw new InputMismatchException("El tablero debe ser al menos de 8x8.");
        this.tablero = new Pieza[nFilas][nCols];
        this.nCols = nCols;
        this.nFilas = nFilas;
    }

    public int getNCols() {
        return nCols;
    }

    public int getNFilas() {
        return nFilas;
    }

    public Pieza getPieza(Posicion pos) {
        return getFromTablero(pos);
    }

    public int getNumPiezas(Pieza.Tipo tipo) {
        int nPiezas = 0;
        for (Pieza[] filaPiezas : tablero)
            for (Pieza pieza : filaPiezas)
                if(pieza.getTipo() == tipo)
                    nPiezas++;
        return nPiezas;
    }

    public int getNumPiezas(Pieza.Color color) {
        int nPiezas = 0;
        for (Pieza[] filaPiezas : tablero)
            for (Pieza pieza : filaPiezas)
                if(pieza.getColor() == color)
                    nPiezas++;
        return nPiezas;
    }

    public int getNumPiezas(Pieza.Tipo tipo, Pieza.Color color) {
        int nPiezas = 0;
        for (Pieza[] filaPiezas : tablero)
            for (Pieza pieza : filaPiezas)
                if((pieza.getTipo() == tipo) && (pieza.getColor() == color))
                    nPiezas++;
        return nPiezas;
    }

    public void colocar(Pieza p) {
        colocar(p, p.getPos());
    }

    public void colocar(Pieza p, Posicion nPos) {
        setInTablero(p, nPos);
    }

    public void borrar(Posicion pos) {
        setInTablero(null, pos);
    }

    @Override
    public String toString() {
        String sTablero = "";
        for (Pieza[] filaPiezas : tablero) {
            for (Pieza pieza : filaPiezas) {
                System.out.print("[");
                if (pieza != null)
                    System.out.print(pieza.toString());
                else
                    System.out.print("　");
                System.out.print("]");
            }
            System.out.println();
        }
        return sTablero;
    }

    public void print() {
        // TODO Recoger el símbolo de la pieza.
        String simbolo = "   ";
        for (int i = tablero.length - 1; i >= 0; i--) {
            System.out.print(i + 1 + "┃ "); // Para la parte de los números
            if (i % 2 == 0) {   // Las que deberían empezar por negro.
                colorearFila(tablero, i, NEGRO, BLANCO, simbolo);
            } else {    // Las que deberían empezar por blanco.
                colorearFila(tablero, i, BLANCO, NEGRO, simbolo);
            }
            System.out.print("\n");
        }
        // Para las letras
        System.out.print(" ┗━");
        for (int i = 0; i < tablero[0].length * 3; i++) {
            System.out.print("━");
        }
        System.out.println();
        System.out.print("   ");
        char letra = 'A';
        for (int i = 0; i < tablero[0].length; i++) {
            System.out.print(" " + letra + " ");
            letra = (char) (letra + 1);
        }
    }

    private Pieza getFromTablero(Posicion pos) {
        pos = transPos(pos);
        return tablero[pos.getFila() - 1][pos.getCol() - 1];
    }

    private void setInTablero(Pieza p, Posicion pos) {
        pos = transPos(pos);
        tablero[pos.getFila() - 1][pos.getCol() - 1] = p;
    }

    private Posicion transPos(Posicion pos) {
        int col = pos.getCol();
        int fila = nFilas - pos.getFila() + 1;
        return new Posicion(col, fila);
    }

    private void colorearFila(Pieza[][] tablero, int filaIterar, String colorInicio, String colorSiguiente, String simbolo){
        for (int i = 0; i < tablero[filaIterar].length; i++) {
            if (i % 2 == 0) {
                System.out.print(colorInicio + simbolo + RESET);
            } else {
                System.out.print(colorSiguiente + simbolo + RESET);
            }
        }
    }
}