import java.util.InputMismatchException;

public class Tablero {

    public static final String NEGRO = "\u001B[40m";
    public static final String BLANCO = "\u001B[47m";
    public static final String RESET = "\u001B[0m";

    private final Pieza[][] tablero;
    private final int nCols;
    private final int nFilas;

    /**
     * Crea un tablero de piezas (<code>Pieza[][]</code>), de al menos 8x8
     * casillas.
     * @param nCols N.º de columnas del tablero.
     * @param nFilas N.º de filas del tablero.
     */
    public Tablero(int nCols, int nFilas) {
        if(nCols < 8 || nFilas < 8)
            throw new InputMismatchException(
                    "El tablero debe ser al menos de 8x8.");
        this.tablero = new Pieza[nFilas][nCols];
        this.nCols = nCols;
        this.nFilas = nFilas;
    }

    /**
     * Obtiene el n.º de columnas del tablero.
     * @return N.º de columnas del tablero.
     */
    public int getNCols() {
        return nCols;
    }

    /**
     * Obtiene el n.º de filas del tablero.
     * @return N.º de filas del tablero.
     */
    public int getNFilas() {
        return nFilas;
    }

    /**
     * Obtiene el objeto <code>Pieza</code> en una posición del tablero
     * (si no hay ninguna pieza devuelve <code>null</code>).
     * @param pos Posición donde buscar la pieza.
     * @return Pieza en la posición dada por parámetro.
     */
    public Pieza getPieza(Posicion pos) {
        return getFromTablero(pos);
    }

    /**
     * Coloca una pieza en el tablero en la posición que tiene guardada.
     * @param p Pieza a colocar.
     */
     public void setPieza(Pieza p) {
        setPieza(p, p.getPos());
    }

    /**
     * Coloca una pieza en el tablero en la posición que tiene guardada.
     * @param p Pieza a colocar.
     */
    public void setPieza(Pieza p, Posicion nPos) {
        setInTablero(p, nPos);
    }

    public void borrarPieza(Posicion pos) {
        setInTablero(null, pos);
    }

    /**
     * Devuelve el n.º de piezas que hay en el tablero del tipo especificado.
     * @param tipo Tipo de pieza a buscar.
     * @return N.º de piezas del tipo pasado por parámetro.
     */
    public int getNumPiezas(Pieza.Tipo tipo) {
        int nPiezas = 0;
        for (Pieza[] filaPiezas : tablero)
            for (Pieza pieza : filaPiezas)
                if(pieza.getTipo() == tipo)
                    nPiezas++;
        return nPiezas;
    }

    /**
     * Devuelve el n.º de piezas que hay en el tablero del color especificado.
     * @param color Color de pieza a buscar.
     * @return N.º de piezas del color pasado por parámetro.
     */
    public int getNumPiezas(Pieza.Color color) {
        int nPiezas = 0;
        for (Pieza[] filaPiezas : tablero)
            for (Pieza pieza : filaPiezas)
                if(pieza.getColor() == color)
                    nPiezas++;
        return nPiezas;
    }

    /**
     * Devuelve el n.º de piezas que hay en el tablero del tipo y color
     * especificados.
     * @param tipo Tipo de pieza a buscar.
     * @param color Color de pieza a buscar.
     * @return N.º de piezas del tipo y color pasados por parámetro.
     */
    public int getNumPiezas(Pieza.Tipo tipo, Pieza.Color color) {
        int nPiezas = 0;
        for (Pieza[] filaPiezas : tablero)
            for (Pieza pieza : filaPiezas)
                if((pieza.getTipo() == tipo) && (pieza.getColor() == color))
                    nPiezas++;
        return nPiezas;
    }

    /**
     * Devuelve una representación del tablero en formato <code>String</code>
     * con todas las casillas, y las piezas en su posición correspondiente.
     * Las casillas se representan con <code>[　]</code> (ejemplo de casilla
     * vacía) y las piezas con su caracter Unicode correspondiente.
     * @return Tablero en formato <code>String</code>
     */
    @Override
    public String toString() {
        String sTablero = "";
        for (Pieza[] filaPiezas : tablero) {
            for (Pieza pieza : filaPiezas) {
                sTablero = sTablero.concat("[");
                if (pieza != null)
                    sTablero = sTablero.concat(pieza.toString());
                else
                    sTablero = sTablero.concat("　");
                sTablero = sTablero.concat("]");
            }
            sTablero = sTablero.concat("\n");
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