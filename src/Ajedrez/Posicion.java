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

    // TODO Posición horizontal entre dos valores [x1, x2]
    // TODO Posición vertical entre dos valores [x1, x2]
    // TODO Posición horizontal mayor que x
    // TODO Posición horizontal menor que x
    // TODO Posición vertical mayor que x
    // TODO Posición vertical menor que x
    // TODO Diferencia de posición horizontal
    // TODO Diferencia de posición vertical
    // TODO Posición comparte fila
    // TODO Posición comparte columna
    // TODO Posición comparte diagonal descendente
    // TODO Posición comparte diagonal ascendente
    // TODO Posición a una casilla de distancia
    // TODO Comprobar diferencia de posiciones dibuja una L

    public int getCol() {
        return col;
    }

    public int getFila() {
        return fila;
    }
}
