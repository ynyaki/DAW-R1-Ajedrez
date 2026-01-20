import java.util.InputMismatchException;

/**
 * Clase que permite crear y operar con posiciones bidimensionales para
 * ser usadas en partidas de ajedrez y otros juegos basados en tableros
 * de cuadrícula. Almacena un par ordenado de números naturales: columna
 * y fila de la posición, respectivamente.
 * @version 1.0 (20/01/26)
 * @author Iñaki, Juan
 */
public class Posicion {

    private final int col;
    private final int fila;

    /**
     * Crea un vector posición, con formato <code>columna, fila</code>.
     * Cada coordenada debe ser un número natural (en caso contrario,
     * lanza <code>InputMismatchException</code>.
     * @param col Columna (posición horizontal) de la posición.
     * @param fila Fila (posición vertical) de la posición.
     */
    public Posicion(int col, int fila) {
        if(fila <= 0 || col <= 0)
            throw new InputMismatchException(
                    "La posición debe estar formada por números naturales.");
        this.col = col;
        this.fila = fila;
    }

    /**
     * <p>
     *     Crea un vector posición, con formato <code>columna, fila</code>.
     *     Cada coordenada debe ser un número natural (en caso contrario,
     *     lanza <code>InputMismatchException</code>.
     * </p>
     * <p>
     *     Esta posición se forma sumando la posición parámetro con
     *     los parámetros de entrada: <code>colAdd</code> para la nueva
     *     columna y <code>filaAdd</code> para la nueva fila.
     * </p>
     * @param pos Pieza
     */
    public Posicion(Posicion pos, int colAdd, int filaAdd) {
        this(pos.getCol() + colAdd, pos.getFila() + filaAdd);
    }

    /**
     * Crea una nueva posición con una letra para la columna y un número
     * para la fila.
     * @param col Columna (letra).
     * @param fila Fila (número).
     */
    public Posicion(char col, int fila) {
        int unicodeAnteriorPrimeraCol = 'a' - 1;
        this.col = col - unicodeAnteriorPrimeraCol;
        this.fila = fila;
    }

    /**
     * Devuelve la columna (pos. en el eje x) de la posición.
     * @return Columna de la posición.
     */
    public int getCol() {
        return col;
    }

    /**
     * Devuelve la fila (pos. en el eje y) de la posición.
     * @return Fila de la posición.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Comprueba si esta posición es igual a la especificada por parámetro.
     * @param pos Posición a comparar.
     * @return Si ambas posiciones son idénticas.
     */
    public boolean esMismaPos(Posicion pos) {
        return (this.col == pos.getCol() && this.fila == pos.getFila());
    }

    /**
     * Comprueba si la columna de esta posición es igual a la de la especificada
     * por parámetro.
     * @param pos Posición con la que comparar.
     * @return Si las columnas de ambas posiciones son idénticas.
     */
    public boolean enMismaCol(Posicion pos) {
        return (this.col == pos.getCol());
    }

    /**
     * Comprueba si la fila de esta posición es igual a la de la especificada
     * por parámetro.
     * @param pos Posición con la que comparar.
     * @return Si las filas de ambas posiciones son idénticas.
     */
    public boolean enMismaFila(Posicion pos) {
        return (this.fila == pos.getFila());
    }

    /**
     * Comprueba si la columna de esta posición es igual a la especificada
     * por parámetro.
     * @param col Columna con la que comparar.
     * @return Si la columna de la posición y la columna parámetro son idénticas.
     */
    public boolean enCol(int col) {
        return (this.col == col);
    }

    /**
     * Comprueba si la fila de esta posición es igual a la especificada
     * por parámetro.
     * @param fila Fila con la que comparar.
     * @return Si la fila de la posición y la fila parámetro son idénticas.
     */
    public boolean enFila(int fila) {
        return (this.fila == fila);
    }

    /**
     * Comprueba si la diferencia de la posición respecto a la posición
     * parámetro es de una sola casilla.
     * @param pos Posición con la que comparar.
     * @return Si ambas posiciones están a una casilla de distancia
     *         en cualquier dirección.
     */
    public boolean esMov1Casilla(Posicion pos) {
        return (this.nColsDif(pos) <= 1 && this.nFilasDif(pos) <= 1);
    }

    /**
     * Comprueba si la posición se encuentra en la misma diagonal que
     * la posición parámetro.
     * @param pos Posición con la que comparar.
     * @return Si ambas posiciones están en la misma diagonal.
     */
    public boolean esMovDiagonal(Posicion pos) {
        return (this.nColsDif(pos) == this.nFilasDif(pos));
    }

    /**
     * Comprueba si una posición forma una L con respecto a otra
     * (dos casillas en una dirección y una casilla en la otra).
     * @param pos Posición con la que comparar.
     * @return Si ambas posiciones forman una L.
     */
    public boolean esMovEnL(Posicion pos) {
        return ((Math.abs(this.nColsDif(pos)) == 2
                && this.nFilasDif(pos) == 1)
                || (this.nColsDif(pos) == 1
                && this.nFilasDif(pos) == 2));
    }

    /**
     * Devuelve la diferencia de columnas entre esta posición y la posición
     * pasada por parámetro.
     * @param pos Posición con la que comparar.
     * @return Número de columnas de diferencia entre ambas posiciones.
     */
    public int nColsDif(Posicion pos) {
        return Math.abs(pos.getCol() - this.col);
    }

    /**
     * Devuelve la diferencia de filas entre esta posición y la posición
     * pasada por parámetro.
     * @param pos Posición con la que comparar.
     * @return Número de filas de diferencia entre ambas posiciones.
     */
    public int nFilasDif(Posicion pos) {
        return Math.abs(pos.getFila() - this.fila);
    }
}
