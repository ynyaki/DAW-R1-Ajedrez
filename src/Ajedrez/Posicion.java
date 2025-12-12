public class Posicion {

    private final int col;
    private final int fila;

    public Posicion(int col, int fila) {
        if(fila <= 0 || col <= 0)
            throw new IndexOutOfBoundsException();
        this.col = col;
        this.fila = fila;
    }

    // TODO Constructor con parámetro String ("A1", "H8")

    public int getCol() {
        return col;
    }

    public int getFila() {
        return fila;
    }

    // TODO Operaciones con posiciones

    public String toStringCol() {
        return colALetra(this.col);
    }

    public String toStringFila() {
        return String.valueOf(this.fila);
    }

    @Override
    public String toString() {
        return toStringCol().concat(toStringFila());
    }

    private static String colALetra(int col) {
        return switch (col) {
            case 1 -> "A";
            case 2 -> "B";
            case 3 -> "C";
            case 4 -> "D";
            case 5 -> "E";
            case 6 -> "F";
            case 7 -> "G";
            case 8 -> "H";
            default -> String.valueOf(col);
        };
    }
}
