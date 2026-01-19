import java.util.Arrays;

/**
 * Clase manejadora de una partida de ajedrez. Permite crear un tablero
 * y piezas de juego, así como ejecutar movimientos que serán válidos
 * de acuerdo a las reglas clásicas del ajedrez.
 * @version 1.0 (20/01/26)
 * @author Iñaki, Juan
 */
public class Partida {

    private static final Pieza.Tipo PEON = Pieza.Tipo.PEON;
    private static final Pieza.Tipo CABALLO = Pieza.Tipo.CABALLO;
    private static final Pieza.Tipo TORRE = Pieza.Tipo.TORRE;
    private static final Pieza.Tipo ALFIL = Pieza.Tipo.ALFIL;
    private static final Pieza.Tipo DAMA = Pieza.Tipo.DAMA;
    private static final Pieza.Tipo REY = Pieza.Tipo.REY;

    private static final Pieza.Color BLANCO = Pieza.Color.BLANCO;
    private static final Pieza.Color NEGRO = Pieza.Color.NEGRO;

    private Tablero t;
    private Pieza rB;
    private Pieza rN;

    // DELETE Main de pruebas
    public static void main(String[] args) {
        Partida p = new Partida();
        p.crearTableroClasico();
        imprTablero(p);

        // TORRE
            pruebaMover(p, 1, 1, 1, 5);
        pruebaMover(p, 1, 2, 1, 4);
        pruebaMover(p, 1, 1, 1, 3);
            pruebaMover(p, 1, 3, 1, 5);
        pruebaMover(p, 1, 3, 4, 3);
            pruebaMover(p, 4, 3, 4, 8);
        pruebaMover(p, 4, 3, 4, 7);
            pruebaMover(p, 4, 7, 4, 2);

        // ALFIL
        pruebaMover(p, 5, 2, 5, 3);
            pruebaMover(p, 6, 1, 8, 3);
            pruebaMover(p, 6, 1, 7, 2);
            pruebaMover(p, 6, 1, 3, 3);
        pruebaMover(p, 6, 1, 4, 3);
        pruebaMover(p, 4, 3, 8, 7);

        // DAMA
        pruebaMover(p, 4, 1, 6, 3);
        pruebaMover(p, 6, 3, 6, 7);
        pruebaMover(p, 6, 7, 4, 5);
        pruebaMover(p, 4, 5, 5, 4);
            pruebaMover(p, 5, 4, 3, 5);

        // PEÓN
        pruebaMover(p, 5, 7, 5, 5);
            pruebaMover(p, 5, 5, 5, 4);
            pruebaMover(p, 5, 5, 5, 3);
            pruebaMover(p, 5, 5, 4, 4);
            pruebaMover(p, 5, 5, 5, 6);
        pruebaMover(p, 5, 4, 6, 4);
        pruebaMover(p, 5, 5, 6, 4);
            pruebaMover(p, 6, 4, 7, 3);
        pruebaMover(p, 6, 4, 5, 3);

        // CABALLO
        pruebaMover(p, 7, 1, 6, 3);
        pruebaMover(p, 6, 3, 7, 5);
            pruebaMover(p, 7, 5, 8, 7);
        pruebaMover(p, 7, 5, 6, 7);
        pruebaMover(p, 6, 7, 8, 8);

        // REY
        pruebaMover(p, 5, 1, 5, 2);
            pruebaMover(p, 5, 2, 4, 2);
        pruebaMover(p, 5, 2, 5, 3);
            pruebaMover(p, 5, 3, 5, 5);
    }

    public Pieza[] getPiezasColor(Pieza.Color c) {
        Pieza[] listaPiezas = new Pieza[t.getNumPiezas(c)];
        int cont = 0;

        for (Pieza[] fila : t.get()) {
            for (Pieza p : fila) {
                if (p != null && p.getColor() == c) {
                    listaPiezas[cont] = p;
                    cont++;
                }
            }
        }

        return listaPiezas;
    }

    /** Inicializa un tablero vacío de 8x8 para la partida. */
    public Partida() {
        crearNuevoTablero();
    }

    /** Se asigna un nuevo tablero vacío de 8x8 a la partida. */
    public void crearNuevoTablero() {
        t = new Tablero(8, 8);
    }

    /**
     * Inicializa un tablero vacío de 8x8 para la partida, con la distribución
     * de piezas inicial estándar de un juego de ajedrez.
     */
    public void crearTableroClasico() {
        this.t = new Tablero(8, 8);

        t.setPieza(new Pieza(TORRE, BLANCO, 1, 1));
        t.setPieza(new Pieza(CABALLO, BLANCO, 2, 1));
        t.setPieza(new Pieza(ALFIL, BLANCO, 3, 1));
        t.setPieza(new Pieza(DAMA, BLANCO, 4, 1));
        t.setPieza(new Pieza(REY, BLANCO, 5, 1));
        t.setPieza(new Pieza(ALFIL, BLANCO, 6, 1));
        t.setPieza(new Pieza(CABALLO, BLANCO, 7, 1));
        t.setPieza(new Pieza(TORRE, BLANCO, 8, 1));
        for(int i = 1; i <= 8; i++)
            t.setPieza(new Pieza(PEON, BLANCO, i, 2));

        t.setPieza(new Pieza(TORRE, NEGRO, 1, 8));
        t.setPieza(new Pieza(CABALLO, NEGRO, 2, 8));
        t.setPieza(new Pieza(ALFIL, NEGRO, 3, 8));
        t.setPieza(new Pieza(DAMA, NEGRO, 4, 8));
        t.setPieza(new Pieza(REY, NEGRO, 5, 8));
        t.setPieza(new Pieza(ALFIL, NEGRO, 6, 8));
        t.setPieza(new Pieza(CABALLO, NEGRO, 7, 8));
        t.setPieza(new Pieza(TORRE, NEGRO, 8, 8));
        for(int i = 1; i <= 8; i++)
            t.setPieza(new Pieza(PEON, NEGRO, i, 7));
    }

    /**
     * Mueve un peón sobre el tablero de ajedrez tras comprobar que
     * el movimiento sea legal.<br>
     * Ejemplos: <code>e5</code> <code>d4</code> <code>c3</code>
     * @param color Color del peón a buscar.
     * @param pos Posición a la que se desea mover el peón.
     * @return Si el movimiento es legal y la pieza ha sido colocada.
     */
    public boolean mover(Pieza.Color color, Posicion pos) {
        Pieza p = getPiezaMovil(color, PEON, pos);
        if(p != null)
            return mover(p, pos);
        else
            return false;
    }

    /**
     * Mueve una pieza sobre el tablero de ajedrez tras comprobar que
     * el movimiento sea legal.<br>
     * Ejemplos: <code>Dh5</code> <code>Ta2</code> <code>Cg5</code>
     * @param color Color de la pieza a buscar.
     * @param tipo Tipo de la pieza a buscar.
     * @param pos Posición a la que se desea mover el peón.
     * @return Si el movimiento es legal y la pieza ha sido colocada.
     */
    public boolean mover(Pieza.Color color, Pieza.Tipo tipo, Posicion pos) {
        Pieza p = getPiezaMovil(color, tipo, pos);
        if(p != null)
            return mover(p, pos);
        else
            return false;
    }

    /**
     * Mueve un peón sobre el tablero de ajedrez tras comprobar que
     * el movimiento sea legal.<br>
     * Ejemplos: <code>e4e5</code> <code>d2d4</code> <code>b2c3</code>
     * @param color Color del peón a buscar.
     * @param posIni Posición donde se localice el peón a mover.
     * @param posFin Posición a la que se desea mover el peón.
     * @return Si el movimiento es legal y la pieza ha sido colocada.
     */
    public boolean mover(Pieza.Color color, Posicion posIni, Posicion posFin) {
        Pieza p = t.getPieza(posIni);
        if(p != null)
            return mover(p, posFin);
        else
            return false;
    }

    /**
     * Mueve una pieza sobre el tablero de ajedrez tras comprobar que
     * el movimiento sea legal.<br>
     * Ejemplos: <code>Ta1g1</code> <code>Cc3e4</code> <code>Ah4g5</code>
     * @param color Color de la pieza a buscar.
     * @param tipo Tipo de la pieza a buscar.
     * @param posIni Posición donde se localice la pieza a mover.
     * @param posFin Posición a la que se desea mover el peón.
     * @return Si el movimiento es legal y la pieza ha sido colocada.
     */
    public boolean mover(Pieza.Color color, Pieza.Tipo tipo,
            Posicion posIni, Posicion posFin) {
        Pieza p = t.getPieza(posIni);
        if(p != null)
            return mover(p, posFin);
        else
            return false;
    }

    /**
     * Promociona un peón al tipo de pieza indicado por parámetro.
     * @param color Color del peón a buscar.
     * @param pos Posición a la que se moverá el peón tras la promoción.
     */
    public boolean hayPromocion(Pieza.Color color, Posicion pos) {
        return (getPiezaMovil(color, PEON, pos) != null);
    }

    /**
     * Promociona un peón al tipo de pieza indicado por parámetro.
     * @param color Color del peón a buscar.
     * @param pos Posición a la que se moverá el peón tras la promoción.
     * @param tipoNuevo Tipo de pieza al que se quiere cambiar
     * (debe ser coherente con las reglas de promoción del ajedrez).
     */
    public void promocionar(
            Pieza.Color color, Posicion pos, Pieza.Tipo tipoNuevo) {
        Pieza p = getPiezaMovil(color, PEON, pos);
        if((p != null) && !tipoNuevo.equals(PEON) && !tipoNuevo.equals(REY)
                && ((p.esBlanca() && p.getPos().enFila(8))
                || p.esNegra() && p.getPos().enFila(1))) {
            t.setPieza(new Pieza(tipoNuevo, p.getColor(), pos));
            t.borrarPieza(p);
        }
    }

    /**
     * Coloca una pieza sobre el tablero de ajedrez. La casilla del tablero
     * donde se posicionará viene dada por la posición asociada a la pieza
     * parámetro.
     * @param p Pieza a colocar.
     * @return Si la colocación ha sido exitosa o ha fallado.
     */
    public boolean colocar(Pieza p) {
        boolean esValida = esColocacionValida(p);
        if(esValida) {
            t.setPieza(p);
            if(p.getTipo().equals(REY))
                if(p.getColor().equals(BLANCO))
                    rB = p;
                else if(p.getColor().equals(NEGRO))
                    rN = p;
        }
        return esValida;
    }

    /**
     * Valida la colocación de las piezas en el tablero de ajedrez antes
     * de comenzar la partida. Una partida de ajedrez no puede comenzar
     * si no hay al menos un rey de cada color, ni si ambos están en jaque.
     * @return Si la partida es válida para ser jugada o no.
     */
    public boolean validarPartida() {
        if(numReyesValido())
            return !(estaReyEnJaque(BLANCO) && estaReyEnJaque(NEGRO));
        else
            return false;
    }

    /**
     * Comprueba y devuelve si el rey de un determinado color está en jaque.
     * @param colorRey Color del rey que se comprobará.
     * @return Si el rey del color parámetro está en jaque.
     */
    public boolean estaReyEnJaque(Pieza.Color colorRey) {
        boolean reyEnJaque = false;
        Pieza rey = null;

        if(colorRey.equals(BLANCO))
            rey = rB;
        else if(colorRey.equals(NEGRO))
            rey = rN;

        if(rey != null)
            for(Pieza[] fila : t.get())
                for(Pieza pieza : fila)
                    if(pieza != null && pieza.getColor() != rey.getColor()
                            && esMovLegal(pieza, rey.getPos()))
                        reyEnJaque = true;

        return reyEnJaque;
    }

    /**
     * Devuelve el tipo de la pieza situada en una determinada posición
     * del tablero.
     * @param pos Posición de la pieza a comprobar.
     * @return Tipo de la pieza (<code>null</code> si la casilla está vacía.
     */
    public Pieza.Tipo getTipoPieza(Posicion pos) {
        if(t.estaOcupada(pos))
            return t.getPieza(pos).getTipo();
        else
            return null;
    }

    /**
     * Devuelve el color de la pieza situada en una determinada posición
     * del tablero.
     * @param pos Posición de la pieza a comprobar.
     * @return Color de la pieza (<code>null</code> si la casilla está vacía.
     */
    public Pieza.Color getColorPieza(Posicion pos) {
        if(t.estaOcupada(pos))
            return t.getPieza(pos).getColor();
        else
            return null;
    }

    /**
     * Imprime el tablero de ajedrez con la disposición actual de las piezas
     * en la partida, junto a una leyenda indicando qué símbolo representa
     * cada pieza.
     */
    public void imprTablero() {
        t.imprTablero();
    }

    /**
     * Obtiene el código <i>hash</i> del tablero, determinado según
     * la información de las piezas distribuidas en él.
     * @return El código <i>hash</i> del tablero de ajedrez.
     */
    public int getHashTablero() {
        return Arrays.deepHashCode(t.get());
    }

    public int getCantPiezaMovil (
            Pieza.Color color, Pieza.Tipo tipo, Posicion nPos) {
        int contPiezasEncontradas = 0;

        for(Pieza[] fila : t.get())
            for(Pieza p : fila)
                if((p != null) && (p.getTipo() == tipo)
                        && (p.getColor() == color) && esMovLegal(p, nPos)) contPiezasEncontradas++;

        return contPiezasEncontradas;
    }

    private Pieza getPiezaMovil(
            Pieza.Color color, Pieza.Tipo tipo, Posicion nPos) {
        for(Pieza[] fila : t.get())
            for(Pieza p : fila)
                if((p != null) && (p.getTipo() == tipo)
                        && (p.getColor() == color) && esMovLegal(p, nPos))
                    return p;
        return null;
    }

    private boolean mover(Pieza p, Posicion nPos) {
        boolean hayMov = esMovLegal(p, nPos);
        if(hayMov)
            t.moverPieza(p, nPos);
        return hayMov;
    }

    private boolean mover(Posicion iPos, Posicion nPos) {
        if(t.getPieza(iPos) != null)
            return mover(t.getPieza(iPos), nPos);
        else
            return false;
    }

    private boolean esMovLegal(Pieza p, Posicion nPos) {
        return (noSuperaMargenes(nPos)
                && esDiferentePos(p, nPos)
                && esCasillaVaciaOEnemiga(p, nPos)
                && esMovLegalDePieza(p, nPos));
    }

    private boolean esColocacionValida(Pieza p) {
        Posicion nPos = p.getPos();
        return (noSuperaMargenes(nPos)
                && esPosVacia(nPos)
                && noSuperaNumPiezas()
                && noSuperaNumCadaPieza()
                && noHayPeonesEnMargenes());
    }

    private boolean esMovLegalDePieza(Pieza p, Posicion nPos) {
        return switch(p.getTipo()) {
            case REY -> esMovLegalRey(p, nPos);
            case DAMA -> esMovLegalDama(p, nPos);
            case TORRE -> esMovLegalTorre(p, nPos);
            case ALFIL -> esMovLegalAlfil(p, nPos);
            case CABALLO -> esMovLegalCaballo(p, nPos);
            case PEON -> esMovLegalPeon(p, nPos);
        };
    }

    private boolean esMovLegalRey(Pieza p, Posicion nPos) {
        return p.getPos().esMov1Casilla(nPos);
    }

    private boolean esMovLegalDama(Pieza p, Posicion nPos) {
        return (esMovLegalTorre(p, nPos) || esMovLegalAlfil(p, nPos));
    }

    private boolean esMovLegalTorre(Pieza p, Posicion nPos) {
        return ((p.getPos().enMismaCol(nPos) || p.getPos().enMismaFila(nPos))
                && (esMovLegalConVision(p, nPos, 0, 1)
                || esMovLegalConVision(p, nPos, 0, -1)
                || esMovLegalConVision(p, nPos, -1, 0)
                || esMovLegalConVision(p, nPos, 1, 0)));
    }

    private boolean esMovLegalAlfil(Pieza p, Posicion nPos) {
        return (p.getPos().esMovDiagonal(nPos)
                && (esMovLegalConVision(p, nPos, -1, -1)
                || esMovLegalConVision(p, nPos, 1, 1)
                || esMovLegalConVision(p, nPos, -1, 1)
                || esMovLegalConVision(p, nPos, 1, -1)));
    }

    private boolean esMovLegalConVision(Pieza p, Posicion nPos, int iC, int iF) {
        Posicion iPos = null;
        boolean esPosMax = estaEnBordeTablero(p.getPos(), iC, iF);
        for(int i = 1; !esPosMax; i++) {
            iPos = new Posicion(p.getPos(), (iC * i), (iF * i));
            esPosMax = (estaEnBordeTablero(iPos, iC, iF)
                || iPos.esMismaPos(nPos) || t.estaOcupada(iPos));
        }
        return ((iPos != null) && iPos.esMismaPos(nPos));
    }

    private boolean estaEnBordeTablero(Posicion p, int iC, int iF) {
        int nCol = p.getCol() + iC;
        int nFila = p.getFila() + iF;
        return (nCol < 1) || (nCol > 8) || (nFila < 1) || (nFila > 8);
    }

    private boolean esMovLegalCaballo(Pieza p, Posicion nPos) {
        return p.getPos().esMovEnL(nPos);
    }

    private boolean esMovLegalPeon(Pieza p, Posicion nPos) {
        return (esMovPeon1Cas(p, nPos) || esMovPeon2Cas(p, nPos)
                || esMovPeonComerIzq(p, nPos) || esMovPeonComerDer(p, nPos));
    }

    private boolean esMovPeon1Cas(Pieza p, Posicion nPos) {
        Posicion pos = p.getPos();
        Posicion posVal = null;

        if(p.esBlanca())
            posVal = new Posicion(pos, 0, 1);
        else if(p.esNegra())
            posVal = new Posicion(pos, 0 , -1);

        return ((posVal != null) && t.estaVacia(posVal)
                && nPos.esMismaPos(posVal));
    }

    private boolean esMovPeon2Cas(Pieza p, Posicion nPos) {
        Posicion pos = p.getPos();
        Posicion posVal = null;

        if(p.esBlanca() && (p.getFila() == 2)
                && t.estaVacia(new Posicion(pos, 0, 1)))
            posVal = new Posicion(pos, 0, 2);
        else if(p.esNegra() && (p.getFila() == 7)
                && t.estaVacia(new Posicion(pos, 0, -1)))
            posVal = new Posicion(pos, 0, -2);

        return ((posVal != null) && t.estaVacia(posVal)
                && nPos.esMismaPos(posVal));
    }

    private boolean esMovPeonComerIzq(Pieza p, Posicion nPos) {
        Posicion pos = p.getPos();
        Posicion posVal = null;

        if(p.getCol() >= 2)
            if(p.esBlanca())
                posVal = new Posicion(pos, -1, 1);
            else if(p.esNegra())
                posVal = new Posicion(pos, -1, -1);

        return ((posVal != null) && posVal.esMismaPos(nPos)
                && hayPiezaEnemiga(p, nPos));
    }

    private boolean hayPiezaEnemiga(Pieza p, Posicion pos) {
        return (t.estaOcupada(pos)
                && t.getPieza(p.getPos()).esDifColor(t.getPieza(pos)));
    }

    private boolean esMovPeonComerDer(Pieza p, Posicion nPos) {
        Posicion pos = p.getPos();
        Posicion posVal = null;

        if(p.getCol() <= 7)
            if(p.esBlanca())
                posVal = new Posicion(pos, 1, 1);
            else if(p.esNegra())
                posVal = new Posicion(pos, 1, -1);

        return ((posVal != null) && posVal.esMismaPos(nPos)
                && hayPiezaEnemiga(p, nPos));
    }

    private boolean noSuperaMargenes(Posicion pos) {
        return (pos.getCol() <= t.getNCols() && pos.getFila() <= t.getNFilas());
    }

    private boolean esDiferentePos(Pieza p, Posicion nPos) {
        return !p.getPos().esMismaPos(nPos);
    }

    private boolean esCasillaVaciaOEnemiga(Pieza p, Posicion nPos) {
        if(t.estaVacia(nPos))
            return true;
        else
            return (p.getColor() != t.getPieza(nPos).getColor());
    }

    private boolean noSuperaNumPiezas() {
        int nW = t.getNumPiezas(BLANCO);
        int nB = t.getNumPiezas(NEGRO);
        return (nW <= 16 && nB <= 16);
    }

    private boolean noSuperaNumCadaPieza() {
        return (t.getNumPiezas(REY, BLANCO) <= 1
                && t.getNumPiezas(DAMA, BLANCO) <= 9
                && t.getNumPiezas(TORRE, BLANCO) <= 10
                && t.getNumPiezas(ALFIL, BLANCO) <= 10
                && t.getNumPiezas(CABALLO, BLANCO) <= 10
                && t.getNumPiezas(PEON, BLANCO) <= 8
                && t.getNumPiezas(REY, NEGRO) <= 1
                && t.getNumPiezas(DAMA, NEGRO) <= 9
                && t.getNumPiezas(TORRE, NEGRO) <= 10
                && t.getNumPiezas(ALFIL, NEGRO) <= 10
                && t.getNumPiezas(CABALLO, NEGRO) <= 10
                && t.getNumPiezas(PEON, NEGRO) <= 8);
    }

    private boolean noHayPeonesEnMargenes() {
        boolean noHayPeonEnMargenes = true;
        Posicion p1;
        Posicion p2;
        for(int i = 1; i <= t.getNCols() && noHayPeonEnMargenes; i++) {
            p1 = new Posicion(i, 1);
            p2 = new Posicion(i, t.getNFilas());
            if((t.getPieza(p1) != null && t.getPieza(p1).getTipo() == PEON)
            || (t.getPieza(p2) != null && t.getPieza(p2).getTipo() == PEON))
                noHayPeonEnMargenes = false;
        }
        return noHayPeonEnMargenes;
    }

    private boolean esPosVacia(Posicion nPos) {
        return t.estaVacia(nPos);
    }

    private boolean numReyesValido() {
        return (t.getNumPiezas(REY, BLANCO) == 1
                && t.getNumPiezas(REY, NEGRO) == 1);
    }

    /** Método de pruebas para mover una pieza en el tablero. */
    private static void pruebaMover(
            Partida p, int cPI, int fPI, int cPF, int fPF) {
        System.out.println();
        if(p.mover(new Posicion(cPI, fPI), new Posicion(cPF, fPF))) {
            System.out.println("Movimiento válido");
            System.out.println();
            p.imprTablero();
        } else {
            System.out.println("Movimiento inválido");
            System.out.println();
        }
        System.out.println("---");
    }

    private static void imprTablero(Partida p) {
        System.out.println();
        p.imprTablero();
        System.out.println("---");
    }
}