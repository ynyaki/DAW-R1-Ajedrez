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

    /** Inicializa un tablero vacío de 8x8 para la partida. */
    public Partida() {
        crearNuevoTablero();
    }

    /** Se asigna un nuevo tablero vacío de 8x8 a la partida. */
    public void crearNuevoTablero() {
        t = new Tablero(8, 8);
    }

    // TODO Documentar
    public boolean mover(Pieza p, Posicion nPos) {
        boolean hayMov = esMovLegal(p, nPos);
        if(hayMov) {
            t.borrarPieza(p);
            t.setPieza(p, nPos);
            p.setPos(nPos);
        }
        return hayMov;
    }

    // TODO Documentar
    public Pieza.Color hayJaque() {
        Pieza.Color color;
        if(isReyEnJaque(rB))
            color = BLANCO;
        else if(isReyEnJaque(rN))
            color = NEGRO;
        else
            color = null;
        return color;
    }

    // TODO Documentar
    public boolean isReyEnJaque(Pieza rey) {
        boolean reyEnJaque = false;
        for(Pieza[] fila : t.get())
            for(Pieza pieza : fila)
                if(pieza.getColor() != rey.getColor()
                        && esMovLegal(pieza, rey.getPos()) && !reyEnJaque)
                    reyEnJaque = true;
        return reyEnJaque;
    }

    // TODO Revisar
    /**
     * Coloca pieza en el tablero. Aparte de devulver si es posible o no colocar la pieza, en caso de poder
     * colocarla la coloca.
     * @param p Objeto de tipo <code>Pieza</code>
     * @return <code>True</code> cuando se puede colocar la pieza en el tablero, <code>False</code> en caso contrario.
     */
    public boolean colocar(Pieza p) {
        boolean esValida = esColocacionValida(p);
        if(esValida) {
            t.setPieza(p);
            if(p.getTipo() == REY && p.getColor() == BLANCO)
                rB = p;
            else if(p.getTipo() == REY && p.getColor() == NEGRO)
                rN = p;
        }
        return esValida;
    }

    /**
     * Validación post Piezas colocadas. Existen validaciones que no se pueden comprobar mientras se colocan las piezas.
     * @return <code>True</code> validación valida <code>False</code> validación invalida.
     */
    public boolean validarPartida() {
        return false;
    }

    // TODO Documentar
    public void imprTablero() {
        t.impr();
    }

    private boolean esMovLegal(Pieza p, Posicion pos) {
        return false;
    }

    private boolean esColocacionValida(Pieza p) {
        return false;
    }

    private boolean numReyesValido() {
        return (t.getNumPiezas(REY, BLANCO) == 1
                && t.getNumPiezas(REY, NEGRO) == 1);
    }

    private boolean noSuperaMargenes(Pieza p) {
        return (p.getCol() <= t.getNCols() && p.getFila() <= t.getNFilas());
    }

    private boolean esPosVacia(Pieza p) {
        return (t.getPieza(p.getPos()) == null);
    }

    private boolean noSuperaNumPiezas() {
        int nW = t.getNumPiezas(BLANCO);
        int nB = t.getNumPiezas(NEGRO);
        return (nW <= 16 && nB <= 16);
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

    private boolean noSuperaNumPeones() {
        return (t.getNumPiezas(PEON, BLANCO) <= 8
                && t.getNumPiezas(PEON, NEGRO) <= 8);
    }

    private boolean esCasillaVacia(Pieza p) {
        return (t.getPieza(p.getPos()) == null);
    }

    // DELETE Crear tablero por defecto
    private void crearTableroClasico() {
        this.t = new Tablero(8, 8);

        t.setPieza(new Pieza(TORRE, BLANCO, 1, 1));
        t.setPieza(new Pieza(TORRE, BLANCO, 8, 1));
        t.setPieza(new Pieza(TORRE, NEGRO, 1, 8));
        t.setPieza(new Pieza(TORRE, NEGRO, 8, 8));

        t.setPieza(new Pieza(CABALLO, BLANCO, 2, 1));
        t.setPieza(new Pieza(CABALLO, BLANCO, 7, 1));
        t.setPieza(new Pieza(CABALLO, NEGRO, 2, 8));
        t.setPieza(new Pieza(CABALLO, NEGRO, 7, 8));

        t.setPieza(new Pieza(ALFIL, BLANCO, 3, 1));
        t.setPieza(new Pieza(ALFIL, BLANCO, 6, 1));
        t.setPieza(new Pieza(ALFIL, NEGRO, 3, 8));
        t.setPieza(new Pieza(ALFIL, NEGRO, 6, 8));

        t.setPieza(new Pieza(DAMA, BLANCO, 4, 1));
        t.setPieza(new Pieza(DAMA, NEGRO, 4, 8));

        t.setPieza(new Pieza(REY, BLANCO, 5, 1));
        t.setPieza(new Pieza(REY, NEGRO, 5, 8));

        for(int i = 1; i <= 8; i++)
            t.setPieza(new Pieza(PEON, BLANCO, i, 2));

        for(int i = 1; i <= 8; i++)
            t.setPieza(new Pieza(PEON, NEGRO, i, 7));

        t.setPieza(new Pieza(PEON, NEGRO, 8, 8));
    }
}
