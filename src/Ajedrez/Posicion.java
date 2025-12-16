import java.util.InputMismatchException;

public class Posicion {

    private final int col;
    private final int fila;

    public Posicion(int col, int fila) {
        if(fila <= 0 || col <= 0)
            throw new InputMismatchException("La posición debe estar formada por números naturales.");
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
}
