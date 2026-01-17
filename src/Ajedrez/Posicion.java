import java.util.InputMismatchException;

// TODO Documentar
public class Posicion {

    private final int col;
    private final int fila;

    // DELETE Método de pruebas de la clase
    public static void main(String[] args) {
        Posicion p1;
        Posicion p2;

        // Probar movimientos del caballo
        p1 = new Posicion(5, 5);

        // Posiciones válidas
        p2 = new Posicion(7, 6);
        System.out.println(p1.esMovEnL(p2));
        p2 = new Posicion(6, 7);
        System.out.println(p1.esMovEnL(p2));
        p2 = new Posicion(4, 7);
        System.out.println(p1.esMovEnL(p2));
        p2 = new Posicion(3, 6);
        System.out.println(p1.esMovEnL(p2));
        p2 = new Posicion(3, 4);
        System.out.println(p1.esMovEnL(p2));
        p2 = new Posicion(4, 3);
        System.out.println(p1.esMovEnL(p2));
        p2 = new Posicion(6, 3);
        System.out.println(p1.esMovEnL(p2));
        p2 = new Posicion(7, 4);
        System.out.println(p1.esMovEnL(p2));

        // Posiciones inválidas
        p2 = new Posicion(6, 2);
        System.out.println(p1.esMovEnL(p2));
        p2 = new Posicion(5, 5);
        System.out.println(p1.esMovEnL(p2));
        p2 = new Posicion(5, 7);
        System.out.println(p1.esMovEnL(p2));
    }

    // TODO Revisar
    /**
     * Crea un vector posición, en formato (columna, fila). Con formato número de columna, número de fila.
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

    // TODO Documentar
    public Posicion(Pieza p, int colAdd, int filaAdd) {
        this(p.getCol() + colAdd, p.getFila() + filaAdd);
    }

    // TODO Revisar
    /**
     * Crea un vector posición, en formato (columna, fila). Con formato letra, número.
     * @param col Columna (posición horizontal) de la posición.
     * @param fila Fila (posición vertical) de la posición.
     */
    public Posicion(char col, int fila) {
        int unicodeAnteriorPrimeraCol = 'a' - 1;
        this.col = col - unicodeAnteriorPrimeraCol;
        this.fila = fila;
    }

    // TODO Revisar
    /**
     * Devuelve la columna (posición horizontal) de la posición.
     * @return Columna de la posición.
     */
    public int getCol() {
        return col;
    }

    // TODO Revisar
    /**
     * Devuelve la fila (posición vertical) de la posición.
     * @return Fila de la posición.
     */
    public int getFila() {
        return fila;
    }

    // TODO Revisar
    /**
     * Comprueba si la posición pasada por parametros es la misma que la del objeto.
     * @param pos Objeto de tipo <code>Posicion</code>.
     * @return <code>True</code> si contiene el mismo contenido, <code>False</code> en caso contrario.
     */
    public boolean esMismaPos(Posicion pos) {
        return (this.col == pos.getCol() && this.fila == pos.getFila());
    }

    // TODO Revisar
    /**
     * Comprueba si la columna pasada por parametro es la misma que la que contiene el objeto.
     * @param pos Objeto de tipo <code>Posicion</code>.
     * @return <code>True</code> si contiene el mismo contenido, <code>False</code> en caso contrario.
     */
    public boolean enMismaCol(Posicion pos) {
        return (this.col == pos.getCol());
    }

    // TODO Revisar
    /**
     * Comprueba si la columna pasada por parametro es la misma que la que contiene el objeto.
     * @param pos Objeto de tipo <code>Posicion</code>.
     * @return <code>True</code> si contiene el mismo contenido, <code>False</code> en caso contrario.
     */
    public boolean enMismaFila(Posicion pos) {
        return (this.fila == pos.getFila());
    }

    // TODO Documentar
    public boolean enCol(int col) {
        return (this.col == col);
    }

    // TODO Documentar
    public boolean enFila(int fila) {
        return (this.fila == fila);
    }

    // TODO Documentar
    public boolean esMov1Casilla(Posicion pos) {
        return (this.nColsDif(pos) <= 1 && this.nFilasDif(pos) <= 1);
    }

    // TODO Documentar
    public boolean esMovDiagonal(Posicion pos) {
        return (this.nColsDif(pos) == this.nFilasDif(pos));
    }

    // TODO Documentar
    public boolean esMovEnL(Posicion pos) {
        return ((Math.abs(this.nColsDif(pos)) == 2
                && this.nFilasDif(pos) == 1)
                || (this.nColsDif(pos) == 1
                && this.nFilasDif(pos) == 2));
    }

    // TODO Revisar
    /**
     * Devuelve la diferencia entre las filas de dos objeto posición.
     * @param pos Objeto de tipo <code>Posicion</code>.
     * @return Devuelve un <code>int</code> sobre la distancia entre las dos filas.
     */
    public int nFilasDif(Posicion pos) {
        return Math.abs(pos.getFila() - this.fila);
    }

    // TODO Revisar
    /**
     * Devuelve la diferencia entre las columnas de dos objeto posición.
     * @param pos Objeto de tipo <code>Posicion</code>.
     * @return Devuelve un <code>int</code> sobre la distancia entre las dos columnas.
     */
    public int nColsDif(Posicion pos) {
        return Math.abs(pos.getCol() - this.col);
    }

    // TODO Revisar
    /**
     * Devuelve la diferencia entre las filas de dos objeto posición.
     * @param pos Objeto de tipo <code>Posicion</code>.
     * @return Devuelve un <code>int</code> sobre la distancia entre las dos filas.
     */
    public int nFilasDifSigno(Posicion pos) {
        return (pos.getFila() - this.fila);
    }

    // TODO Revisar
    /**
     * Devuelve la diferencia entre las columnas de dos objeto posición.
     * @param pos Objeto de tipo <code>Posicion</code>.
     * @return Devuelve un <code>int</code> sobre la distancia entre las dos columnas.
     */
    public int nColsDifSigno(Posicion pos) {
        return (pos.getCol() - this.col);
    }
}
