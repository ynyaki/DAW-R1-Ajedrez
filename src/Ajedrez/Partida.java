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

    // DELETE Pruebas de la clase
    public static void main() {
        Partida p = new Partida();
        p.crearTableroClasico();
        Pieza p1 = new Pieza(TORRE, BLANCO, 1, 8);
        p.colocar(p1);
        System.out.print(p.t);
    }

    public Partida() {
        this.t = new Tablero(8, 8);
    }

    /**
     * Coloca pieza en el tablero. Aparte de devulver si es posible o no colocar la pieza, en caso de poder
     * colocarla la coloca.
     * @param p Objeto de tipo <code>Pieza</code>
     * @return <code>True</code> cuando se puede colocar la pieza en el tablero, <code>False</code> en caso contrario.
     */
    public boolean colocar(Pieza p) {
        if (esColocacionValida(p)){
            t.setPieza(p);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Limpia el tablero.
     */
    public void limpiar(){
        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j <= 8; j++) {
                if (t.getPieza(new Posicion(i, j)) != null) {
                    t.borrarPieza(new Posicion(i, j));
                }
            }
        }
    }

    /**
     * Validación post Piezas colocadas. Existen validaciones que no se pueden comprobar mientras se colocan las piezas.
     * @return <code>True</code> validación valida <code>False</code> validación invalida.
     */
    public boolean valPostColocar(){
        boolean a = existeRey();
        boolean b = noSuperaNumPiezas();
        boolean c = noPeonesUltimasFilas();
        boolean d = numPeonesMax();

        return existeRey()
                && noSuperaNumPiezas()
                && noPeonesUltimasFilas()
                && numPeonesMax();
    }

    /**
     * Valida que sea posible introducir una pieza en el tablero.
     * @param p Objeto de tipo <code>Pieza</code>
     * @return <code>True</code> cuando es posible la colocación, <code>False</code> en caso contrario.
     */
    private boolean esColocacionValida(Pieza p) {
        return piezaDentroDeLimites(p)
                && posicionNoOcupada(p);
    }

    /**
     * Comprueba que la posición del objeto pasado de tipo <code>Pieza</code> este dentro de los límetes del tablero.
     * @param p Objeto de tipo <code>Pieza</code> a evaluar.
     * @return <code>True</code> posición valida <code>False</code> posición <strong>NO</strong> valida.
     */
    private boolean piezaDentroDeLimites(Pieza p) {
        int col = p.getCol();
        int fil = p.getFila();
        int colMax = t.getNCols();
        int filMax = t.getNFilas();

        return col <= colMax && fil <= filMax;
        if(p.getTipo() == REY) {
            // TODO Asignar pieza a rB y rN
        }
    }

    // Métodos de validación

    public boolean esColocacionValida(Pieza p) {
        return (noSuperaMargenes(p)
                && esCasillaVacia(p)
                && noSuperaNumPiezas()
                && noSuperaNumPeones()
                && noSuperaNumReyes()
                && noHayPeonesEnMargenes());
    }

    private boolean noSuperaMargenes(Pieza p) {
        Posicion pos = p.getPos();
        return (pos.entreFilas(1, t.getNFilas())
                && pos.entreCols(1, t.getNCols()));
    }

    /**
     * Comprueba que no exitan más piezas que las permitidas en el ajedrez.
     * @return <code>True</code> cantidad valida <code>False</code> cantidad <strong>NO</strong> valida.
     */
    private boolean noSuperaNumPiezas() {
        int nW = t.getNumPiezas(BLANCO);
        int nB = t.getNumPiezas(NEGRO);
        return (nW <= 16 && nB <= 16);
    }

    private boolean noHayPeonesEnMargenes() {
        boolean noHayPeonEnMargenes = true;
        for(int i = 1; i <= t.getNCols() && noHayPeonEnMargenes; i++) {
            if(t.getPieza(new Posicion(i, 1)).getTipo() == PEON
                    || t.getPieza(new Posicion(i,t.getNFilas())).getTipo() == PEON)
                noHayPeonEnMargenes = false;
        }
        return noHayPeonEnMargenes;
    }

    /**
     * Comprueba que no se exceda un número máximo de peones posibles.
     * @return <code>True</code> número de peones posible <code>False</code> número <strong>NO</strong> posible de peones.
     */
    private boolean numPeonesMax() {
        return t.getNumPiezas(PEON, BLANCO) <= 8 && t.getNumPiezas(PEON, NEGRO) <= 8;
    private boolean noSuperaNumPeones() {
        return (t.getNumPiezas(PEON, BLANCO) <= 8
                && t.getNumPiezas(PEON, NEGRO) <= 8);
    }

    /**
     * Comprueba que la posición de la pieza que se le pasa por parámetro no se encuentre ocupada.
     * @param p Objeto de tipo <code>Pieza</code>.
     * @return <code>True</code> espacio disponible <code>False</code> en caso contrario.
     */
    public boolean esCasillaVacia(Pieza p) {
        return (t.getPieza(p.getPos()) == null);
    }

    // Métodos de movimiento

    private boolean esMovLegal(Pieza p, Posicion pos) {
        return false;
    }

    private Pieza.Color hayJaque() {
        Pieza.Color color;
        if(isReyEnJaque(rB))
            color = BLANCO;
        else if(isReyEnJaque(rN))
            color = NEGRO;
        else
            color = null;
        return color;
    }

    private boolean isReyEnJaque(Pieza rey) {
        boolean reyEnJaque = false;
        for(Pieza[] fila : t.get())
            for(Pieza pieza : fila)
                if(pieza.getColor() != rey.getColor()
                        && esMovLegal(pieza, rey.getPos()) && !reyEnJaque)
                    reyEnJaque = true;
        return reyEnJaque;
    }

    // DELETE Métodos provisionales

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
