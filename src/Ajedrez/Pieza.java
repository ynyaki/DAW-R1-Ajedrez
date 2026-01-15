// TODO Documentar
public class Pieza {

    // TODO Documentar
    public enum Tipo {
        PEON,
        CABALLO,
        TORRE,
        ALFIL,
        DAMA,
        REY
    }

    // TODO Documentar
    public enum Color {
        BLANCO,
        NEGRO
    }

    private final Tipo tipo;
    private final Color color;
    private Posicion pos;

    // TODO Documentar
    public Pieza(Tipo tipo, Color color, int col, int fila) {
        this(tipo, color, new Posicion(col, fila));
    }

    // TODO Documentar
    public Pieza(Tipo tipo, Color color, Posicion pos) {
        if(tipo == null || color == null)
            throw new NullPointerException();
        this.tipo = tipo;
        this.color = color;
        this.pos = pos;
    }

    // TODO Documentar
    public Tipo getTipo() {
        return tipo;
    }

    // FIXME Pertenece al formato
    public static Tipo obtenerTipoPieza(String pieza) {
        Tipo tipo;
        if (pieza.length() == 3)
            tipo = switch (pieza.charAt(0)) {
                case 'R' -> Tipo.REY;
                case 'D' -> Tipo.DAMA;
                case 'T' -> Tipo.TORRE;
                case 'A' -> Tipo.ALFIL;
                case 'C' -> Tipo.CABALLO;
                default -> null;
            };
        else
            tipo = Tipo.PEON;
        return tipo;
    }

    // TODO Documentar
    public Color getColor() {
        return color;
    }

    // TODO Documentar
    public Posicion getPos() {
        return pos;
    }

    // TODO Documentar
    public int getCol() {
        return pos.getCol();
    }

    // TODO Documentar
    public int getFila() {
        return pos.getFila();
    }

    // TODO Documentar
    public void setPos(int col, int fila) {
        this.pos = new Posicion(col, fila);
    }

    // TODO Documentar
    public void setPos(Posicion pos) {
        this.pos = pos;
    }

    // TODO Documentar
    @Override
    public String toString() {
        if(this.tipo == Tipo.REY && this.color == Color.BLANCO)
            return "♔";
        else if(this.tipo == Tipo.DAMA && this.color == Color.BLANCO)
            return "♕";
        else if(this.tipo == Tipo.TORRE && this.color == Color.BLANCO)
            return "♖";
        else if(this.tipo == Tipo.ALFIL && this.color == Color.BLANCO)
            return "♗";
        else if(this.tipo == Tipo.CABALLO && this.color == Color.BLANCO)
            return "♘";
        else if(this.tipo == Tipo.PEON && this.color == Color.BLANCO)
            return "♙";
        else if(this.tipo == Tipo.REY && this.color == Color.NEGRO)
            return "♚";
        else if(this.tipo == Tipo.DAMA && this.color == Color.NEGRO)
            return "♛";
        else if(this.tipo == Tipo.TORRE && this.color == Color.NEGRO)
            return "♜";
        else if(this.tipo == Tipo.ALFIL && this.color == Color.NEGRO)
            return "♝";
        else if(this.tipo == Tipo.CABALLO && this.color == Color.NEGRO)
            return "♞";
        else if(this.tipo == Tipo.PEON && this.color == Color.NEGRO)
            return "♟";
        else
            return "?";
    }
}