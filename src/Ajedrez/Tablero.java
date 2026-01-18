import java.util.InputMismatchException;

/**
 * Clase que permite crear un tablero de piezas, así como modificar las piezas
 * que se ubican en él. También puede representarse visualmente a través
 * de consola.
 * @version 1.0 (20/01/26)
 * @author Iñaki, Juan
 */
public class Tablero {

    // FIXME Pertenece a Formato
    /**
     * A través de consola, hace una representación visual del tablero,
     * así como de las piezas en su posición y con su tipo y color adecuado.
     * También imprime una leyenda explicando qué pieza es cada una según
     * su caracter Unicode.
     */
    public void imprTablero() {
        char letra = 'A';
        String simboloVacio = " 　 ";

        // Representación del tablero y de la leyenda
        for(int i = nFilas - 1; i >= 0; i--) {
            System.out.print(i + 1 + "┃ ");
            if (i % 2 == 0)
                colorearFila(t, i, NEGRO, BLANCO, simboloVacio);
            else
                colorearFila(t, i, BLANCO, NEGRO, simboloVacio);
            leyenda(i);
            System.out.println();
        }

        // Línea separadora
        System.out.print(" ┗━");
        for (int i = 0; i < nCols; i++)
            System.out.print("━－━");
        System.out.println();

        // Letras de las columnas
        System.out.print("   ");
        for(int i = 0; i < nCols; i++) {
            System.out.print("　" + letra + " ");
            letra = (char) (letra + 1);
        }

        System.out.println();
        System.out.println();
    }

    // FIXME Pertenece a formato
    /**
     * Se encarga de colorear el fondo que representan las celdas según el número de fila. Aparte de añadir el
     * contenido. También añade las letras y números.
     * @param tablero El <code>array</code> del tablero.
     * @param filaIterar Nº de fila. Tipo <code>int</code>.
     * @param colorInicio Color de inicio. Tipo <code>String</code>.
     * @param colorSiguiente Color a alternar. Tipo <code>String</code>.
     * @param simboloVacio Símbolo a colocar en caso de non tener contenido. Tipo <code>String</code>.
     */
    private void colorearFila(Pieza[][] tablero, int filaIterar, String colorInicio, String colorSiguiente, String simboloVacio){
        String simbolo;
        Pieza p;
        for (int i = 0; i < tablero[filaIterar].length; i++) {
            if(getFromTablero(new Posicion(i + 1, filaIterar + 1)) == null)
                simbolo = simboloVacio;
            else {
                p = getFromTablero(new Posicion(i + 1, filaIterar + 1));
                simbolo = " " + p.toString() + " ";
            }
            if(i % 2 == 0)
                System.out.print(colorInicio + simbolo + RESET);
            else
                System.out.print(colorSiguiente + simbolo + RESET);
        }
    }

    // FIXME Pertenece a Formato
    /**
     * Imprime una línea relacionada con la leyenda según la fila pasada por parámetro.
     * @param n Número de la fila donde se tiene que imprimir.
     */
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

    public static final String NEGRO = "\u001B[40m";
    public static final String BLANCO = "\u001B[47m";
    public static final String RESET = "\u001B[0m";

    private final Pieza[][] t;
    private final int nCols;
    private final int nFilas;

    /**
     * Crea un tablero de piezas de al menos 4x4 casillas.
     * @param nCols N.º de columnas del tablero.
     * @param nFilas N.º de filas del tablero.
     */
    public Tablero(int nCols, int nFilas) {
        if(nCols < 4 || nFilas < 4)
            throw new InputMismatchException(
                    "El tablero debe ser al menos de 4x4.");
        this.t = new Pieza[nFilas][nCols];
        this.nCols = nCols;
        this.nFilas = nFilas;
    }

    /**
     * Obtiene el tablero en forma de matriz de piezas, que permite almacenar
     * y distribuir las piezas de juego.
     * @return El objeto <code>Pieza[][]</code> del tablero.
     */
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
     * Devuelve si la casilla del tablero especificada por parámetro
     * está vacía.
     * @param pos Posición de la casilla a comprobar.
     * @return Si la casilla está vacía.
     */
    public boolean estaVacia(Posicion pos) {
        return (getPieza(pos) == null);
    }

    /**
     * Devuelve si la casilla del tablero especificada por parámetro
     * está ocupada.
     * @param pos Posición de la casilla a comprobar.
     * @return Si la casilla está ocupada por alguna pieza.
     */
    public boolean estaOcupada(Posicion pos) {
        return (getPieza(pos) != null);
    }

    /**
     * Coloca una pieza en el tablero en la posición que tiene guardada.
     * Si en la posición de la pieza ya había una pieza, la nueva
     * pieza se sobreescribirá en esa posición.
     * @param p Pieza a colocar.
     */
    public void setPieza(Pieza p) {
        setInTablero(p, p.getPos());
    }

    /**
     * Mueve una pieza en el tablero a la posición especificada.
     * Si en la nueva posición ya había una pieza, la nueva
     * pieza se sobreescribirá en esa posición.
     * @param p Pieza a mover.
     * @param nPos Nueva posición de la pieza (será almacenada
     *             como nueva posición de la pieza).
     */
    public void moverPieza(Pieza p, Posicion nPos) {
        setInTablero(null, p.getPos());
        setInTablero(p, nPos);
        p.setPos(nPos);
    }

    /**
     * Elimina una pieza del tablero, dejando su casilla vacía.
     * @param p Pieza a borrar.
     */
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
                if((pieza != null) && (pieza.getTipo().equals(tipo)))
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
                if((pieza != null) && pieza.getColor().equals(color))
                    nPiezas++;
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
                if((pieza != null) && (pieza.getTipo().equals(tipo))
                        && (pieza.getColor().equals(color)))
                    nPiezas++;
            }
        return nPiezas;
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