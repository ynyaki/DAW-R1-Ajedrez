public class Pieza {

    public enum Tipo {
        PEON,
        CABALLO,
        TORRE,
        ALFIL,
        DAMA,
        REY
    }

    public enum Color {
        BLANCO,
        NEGRO
    }

    private final Tipo tipo;
    private final Color color;
    private Posicion pos;

    public Pieza(Tipo tipo, Color color, Posicion pos) {
        if(tipo == null || color == null)
            throw new NullPointerException();
        this.tipo = tipo;
        this.color = color;
        this.pos = pos;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Color getColor() {
        return color;
    }

    public Posicion getPos() {
        return pos;
    }

    public int getCol() {
        return pos.getCol();
    }

    public int getFila() {
        return pos.getFila();
    }

    public void setPos(Posicion pos) {
        this.pos = pos;
    }

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