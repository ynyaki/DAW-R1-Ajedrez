public class Pieza {

    /*
     *   ♙  Peón
     *   ♖  Torre
     *   ♘  Caballo
     *   ♗  Alfil
     *   ♕  Reina
     *   ♔  Rey
     *
     *   -----------
     *
     *   ♟  Peón
     *   ♜  Torre
     *   ♞  Caballo
     *   ♝  Alfil
     *   ♛  Reina
     *   ♚  Rey
     */

    public enum TIPO {
        PEON,
        CABALLO,
        TORRE,
        ALFIL,
        DAMA,
        REY
    }

    public enum COLOR {
        BLANCA,
        NEGRA
    }

    private final TIPO tipo;
    private final COLOR color;
    private Posicion pos;

    public Pieza(TIPO tipo, COLOR color, Posicion pos) {
        if(tipo == null || color == null)
            throw new NullPointerException();
        this.tipo = tipo;
        this.color = color;
        this.pos = pos;
    }

    public Pieza(TIPO tipo, COLOR color, int col, int fila) {
        this(tipo, color, new Posicion(col, fila));
    }

    public TIPO getTipo() {
        return tipo;
    }

    public COLOR getColor() {
        return color;
    }

    public Posicion getPos() {
        return pos;
    }

    public void setPos(Posicion pos) {
        this.pos = pos;
    }
}
