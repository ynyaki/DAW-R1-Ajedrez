/**
 * Permite crear, modificar y obtener datos de piezas de ajedrez sobre
 * un tablero o a una partida. Almacena datos de tipo, color y posición.
 * @version 1.0 (20/01/26)
 * @author Iñaki, Juan
 */
public class Pieza {

    // FIXME Pertenece a Formato
    /**
     * Extrae el tipo de pieza a partir de la entrada del usuario.
     * El formato del comando debe ser <code>Px0</code> ó <code>Px0y0</code>,
     * siendo <code>P</code> el tipo de pieza. Si tiene otro formato,
     * se interpretará que es un <b>peón</b>.
     * @param com Comando del usuario en formato <code>String</code>.
     * @return Valor del enum <code>Tipo</code> según el formato y valor
     *         del comando.
     */
    public static Tipo getTipoPieza(String com) {
        Tipo tipo;
        if(com.length() == 3 || com.length() == 5)
            tipo = switch(com.trim().toUpperCase().charAt(0)) {
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

    /** Tipo de pieza, con diferentes movimientos y reglas asociadas. */
    public enum Tipo {
        PEON,
        CABALLO,
        TORRE,
        ALFIL,
        DAMA,
        REY
    }

    /** Color de pieza, que determina el bando en el juego. */
    public enum Color {
        BLANCO,
        NEGRO
    }

    private final Tipo tipo;
    private final Color color;
    private Posicion pos;

    /**
     * Crea un objeto pieza, con el tipo, color y posición en el tablero
     * determinados por parámetros.
     * @param tipo Tipo de pieza (peón, caballo...) (inmutable).
     * @param color Color de pieza (blanco, negro) (inmutable).
     * @param pos Coordenadas de la pieza dentro del tablero.
     */
    public Pieza(Tipo tipo, Color color, Posicion pos) {
        if(tipo == null || color == null || pos == null)
            throw new NullPointerException();
        this.tipo = tipo;
        this.color = color;
        this.pos = pos;
    }

    /** Obtiene el color de la pieza (blanco, negro). */
    public Pieza(Tipo tipo, Color color, int col, int fila) {
        this(tipo, color, new Posicion(col, fila));
    }

    /**
     * Obtiene el tipo de la pieza (peón, caballo...).
     * @return El valor del <i>enum</i> <code>Tipo</code> correspondiente.
     */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Obtiene el color de la pieza (blanco, negro).
     * @return El valor del <i>enum</i> <code>Color</code> correspondiente.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Devuelve si la pieza es o no de color blanco.
     * @return <code>true</code> ó <code>false</code> según si la pieza
     * es o no blanca.
     */
    public boolean esBlanca() {
        return color.equals(Color.BLANCO);
    }

    /**
     * Devuelve si la pieza es o no de color negro.
     * @return <code>true</code> ó <code>false</code> según si la pieza
     * es o no negra.
     */
    public boolean esNegra() {
        return color.equals(Color.NEGRO);
    }

    /**
     * Devuelve la posición de la pieza en el tablero.
     * @return El objeto <code>Posición</code> de la pieza.
     */
    public Posicion getPos() {
        return pos;
    }

    /**
     * Obtiene la columna en la que se encuentra la pieza en el tablero.
     * @return La columna del objeto <code>Posición</code>.
     */
    public int getCol() {
        return pos.getCol();
    }

    /**
     * Obtiene la fila en la que se encuentra la pieza en el tablero.
     * @return La fila del objeto <code>Posición</code>.
     */
    public int getFila() {
        return pos.getFila();
    }

    /**
     * Cambia la posición de la pieza dentro del tablero (no se actualiza
     * automáticamente en un objeto <code>Tablero</code>).
     * @param pos Nueva posición de la pieza.
     */
    public void setPos(Posicion pos) {
        this.pos = pos;
    }

    /**
     * Representa con un caracter Unicode tanto el tipo como el color
     * de la pieza, para poder ser representada en el tablero a través
     * de la consola.
     * @return Un caracter Unicode que representa la pieza.
     */
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