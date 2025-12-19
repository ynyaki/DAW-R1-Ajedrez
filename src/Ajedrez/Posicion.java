import java.util.InputMismatchException;

public class Posicion {

    private final int col;
    private final int fila;

    // TODO Método de pruebas de la clase
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

    /**
     * Crea un vector posición, en formato (columna, fila).
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
     * Devuelve la columna (posición horizontal) de la posición.
     * @return Columna de la posición.
     */
    public int getCol() {
        return col;
    }

    /**
     * Devuelve la fila (posición vertical) de la posición.
     * @return Fila de la posición.
     */
    public int getFila() {
        return fila;
    }

    /**
     * Devuelve si una posición está entre dos columnas especificadas
     * (<code>colMin <= col <= colMax</code>).
     * @param colMin
     * @param colMax
     * @return
     */
    public boolean entreCols(int colMin, int colMax) {
        return (colMin <= this.getCol() && this.getCol() <= colMax);
    }

    public boolean entreFilas(int filaMin, int filaMax) {
        return (filaMin <= this.getFila() && this.getFila() <= filaMax);
    }

    public boolean esMov1Casilla(Posicion pos) {
        return (this.nColsDif(pos) <= 1 && this.nFilasDif(pos) <= 1);
    }

    public boolean enMismaCol(Posicion pos) {
        return (this.col != pos.getCol());
    }

    public boolean enMismaFila(Posicion pos) {
        return (this.fila != pos.getFila());
    }

    public boolean esMovDiagonal(Posicion pos) {
        return (this.nColsDif(pos) == this.nFilasDif(pos));
    }

    public boolean esMovEnL(Posicion pos) {
        return ((this.nColsDif(pos) == 2) && (this.nFilasDif(pos) == 1)
                || (this.nColsDif(pos) == 1) && (this.nFilasDif(pos) == 2));
    }

    public int nFilasDif(Posicion pos) {
        return Math.abs(this.fila - pos.getFila());
    }

    public int nColsDif(Posicion pos) {
        return Math.abs(this.col - pos.getCol());
    }
}
