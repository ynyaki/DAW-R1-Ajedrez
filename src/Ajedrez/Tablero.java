import java.util.InputMismatchException;

public class Tablero {

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
        for(int i = 0; i < tablero.length; i++) {
            for(int j = 0; j < tablero[i].length; j++) {
                System.out.print("[");
                if(tablero[i][j] != null)
                    System.out.print(tablero[i][j].toString());
                else
                    System.out.print("　");
                System.out.print("]");
            }
            System.out.println();
        }
        return sTablero;
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
}
