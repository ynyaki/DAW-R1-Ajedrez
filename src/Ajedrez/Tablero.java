import java.util.InputMismatchException;

public class Tablero {

    public static final String NEGRO = "\u001B[40m";
    public static final String BLANCO = "\u001B[47m";
    public static final String RESET = "\u001B[0m";

    private final Pieza[][] t;
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
        this.t = new Pieza[nFilas][nCols];
        this.nCols = nCols;
        this.nFilas = nFilas;
    }

    public Pieza[][] get() {
        return this.t;
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

    // TODO Revisar
    /**
     * Obtiene el objeto <code>Pieza</code> en una posición del tablero
     * (si no hay ninguna pieza devuelve <code>null</code>).
     * @param pos Posición donde buscar la pieza.
     * @return Pieza en la posición dada por parámetro.
     */
    public Pieza getPieza(Posicion pos) {
        return getFromTablero(pos);
    }

    // TODO Documentar
    public Pieza getPieza(int col, int fila) {
        return getFromTablero(new Posicion(col, fila));
    }

    // TODO Documentar
    public boolean estaVacia(Posicion pos) {
        return (getPieza(pos) == null);
    }

    // TODO Documentar
    public boolean estaOcupada(Posicion pos) {
        return (getPieza(pos) != null);
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

    // TODO Documentar
    public void borrarPieza(Pieza p) {
        setInTablero(null, p.getPos());
    }

    /**
     * Devuelve el n.º de piezas que hay en el tablero del tipo especificado.
     * @param tipo Tipo de pieza a buscar.
     * @return N.º de piezas del tipo pasado por parámetro.
     */
    public int getNumPiezas(Pieza.Tipo tipo) {
        int nPiezas = 0;
        for(Pieza[] fila : t)
            for(Pieza pieza : fila)
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
        for (Pieza[] filaPiezas : t)
            for (Pieza pieza : filaPiezas) {
                if (pieza == null) continue;
                if (pieza.getColor() == color) nPiezas++;
            }
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
        for (Pieza[] filaPiezas : t)
            for (Pieza pieza : filaPiezas) {
                if (pieza == null) continue;
                if ((pieza.getTipo() == tipo) && (pieza.getColor() == color)) nPiezas++;
            }
        return nPiezas;
    }

    // FIXME Podría formar parte de Formato.imprTablero(t)
    public void impr() {
        String simboloVacio = " 　 ";
        for (int i = t.length - 1; i >= 0; i--) {
            System.out.print(i + 1 + "┃ "); // Para la parte de los números
            if (i % 2 == 0) {   // Las que deberían empezar por negro.
                colorearFila(t, i, NEGRO, BLANCO, simboloVacio);
            } else {    // Las que deberían empezar por blanco.
                colorearFila(t, i, BLANCO, NEGRO, simboloVacio);
            }

            leyenda(i);

            System.out.print("\n");
        }

        // Para las letras
        System.out.print(" ┗━");
        for (int i = 0; i < t[0].length; i++) {
            System.out.print("━－━");
        }

        System.out.print("\n");

        System.out.print("   ");
        char letra = 'A';
        for (int i = 0; i < t[0].length; i++) {
            System.out.print("　" + letra + " ");
            letra = (char) (letra + 1);
        }
    }

    // FIXME Podría formar parte de Formato.imprTablero(t)
    private void colorearFila(Pieza[][] tablero, int filaIterar, String colorInicio, String colorSiguiente, String simboloVacio){
        String simbolo;
        Pieza p;

        for (int i = 0; i < tablero[filaIterar].length; i++) {
            if (getFromTablero(new Posicion(i + 1, filaIterar + 1)) == null) {
                simbolo = simboloVacio;
            } else {
                p = getFromTablero(new Posicion(i + 1, filaIterar + 1));

                simbolo = " " + p.toString() + " ";
            }

            if (i % 2 == 0) {
                System.out.print(colorInicio + simbolo + RESET);
            } else {
                System.out.print(colorSiguiente + simbolo + RESET);
            }
        }
    }

    // FIXME Podría formar parte de Formato.imprTablero(t)
    private void leyenda(int n) {
        switch (n) {
            case 7 -> System.out.print("\t\tLeyenda:");
            case 6 -> System.out.print("\t\t♔ Rey Blanco     ┃ ♚ Rey Negro");
            case 5 -> System.out.print("\t\t♕ Dama Blanca    ┃ ♛ Dama Negra");
            case 4 -> System.out.print("\t\t♖ Torre Blanca   ┃ ♜ Torre Negra");
            case 3 -> System.out.print("\t\t♗ Alfil Blanco   ┃ ♝ Alfil Negro");
            case 2 -> System.out.print("\t\t♘ Caballo Blanco ┃ ♞ Caballo Negro");
            case 1 -> System.out.print("\t\t♙ Peón Blanco    ┃ ♟ Peón Negro");
        }
    }

    private Pieza getFromTablero(Posicion pos) {
        pos = transPos(pos);
        return t[pos.getFila() - 1][pos.getCol() - 1];
    }

    private void setInTablero(Pieza p, Posicion pos) {
        pos = transPos(pos);
        t[pos.getFila() - 1][pos.getCol() - 1] = p;
    }

    private Posicion transPos(Posicion pos) {
        int col = pos.getCol();
        int fila = nFilas - pos.getFila() + 1;
        return new Posicion(col, fila);
    }
}