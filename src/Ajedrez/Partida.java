public class Partida {

    private static final Pieza.Tipo PEON = Pieza.Tipo.PEON;
    private static final Pieza.Tipo CABALLO = Pieza.Tipo.CABALLO;
    private static final Pieza.Tipo TORRE = Pieza.Tipo.TORRE;
    private static final Pieza.Tipo ALFIL = Pieza.Tipo.ALFIL;
    private static final Pieza.Tipo DAMA = Pieza.Tipo.DAMA;
    private static final Pieza.Tipo REY = Pieza.Tipo.REY;

    private static final Pieza.Color BLANCO = Pieza.Color.BLANCO;
    private static final Pieza.Color NEGRO = Pieza.Color.NEGRO;

    private static Tablero t;

    public Partida(int colsT, int filT) {
        t = new Tablero(colsT, filT);
    }

    // DELETE
    public void imprimirTablero() {
        t.print();
    }

    public void main() {
        System.out.println("PRUEBAS DE PARTIDA");
        System.out.println("------------------");
        System.out.println();
        Partida p = new Partida(8, 8);
        p.empezar();
    }

    //  DELETE Pruebas de creación y muestra de tablero
    public void empezar() {
        Tablero t = crearTableroClasico();
        System.out.print(t);
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

    /**
     * Comprueba que no existan peones en la úlitmas filas.
     * @return <code>True</code> si no encuentra peones en las últimas filas <code>False</code> en caso contrario.
     */
    private boolean noPeonesUltimasFilas() {
        boolean correcto = true;

        for (int i = 1; i <= t.getNCols() ; i++) {

            if (t.getPieza(new Posicion(i, 1)) == null) {continue;}

            if (t.getPieza(new Posicion(i, 1)).getTipo() == PEON){
                correcto = false;
            }
        }

        int ultimaFila = t.getNFilas();
        for (int i = 1; i <= t.getNCols(); i++) {

            if (t.getPieza(new Posicion(i, ultimaFila)) == null) {continue;}

            if (t.getPieza(new Posicion(i, ultimaFila)).getTipo() == PEON){
                correcto = false;
            }
        }

        return correcto;
    }

    /**
     * Comprueba que no se exceda un número máximo de peones posibles.
     * @return <code>True</code> número de peones posible <code>False</code> número <strong>NO</strong> posible de peones.
     */
    private boolean numPeonesMax() {
        return t.getNumPiezas(PEON, BLANCO) < 8 && t.getNumPiezas(PEON, NEGRO) < 8;
    }

    /**
     * Comprueba que la posición de la pieza que se le pasa por parámetro no se encuentre ocupada.
     * @param p Objeto de tipo <code>Pieza</code>.
     * @return <code>True</code> espacio disponible <code>False</code> en caso contrario.
     */
    private boolean posicionNoOcupada(Pieza p) {
        return t.getPieza(p.getPos()) == null;
    }

    /**
     * Valida que al menos exista un rey. Y solo pueda haber un rey.
     * @return <code>True</code> cuando solo exite un rey <code>False</code> cuando hay más o no hay rey.
     */
    private boolean existeRey() {
        return t.getNumPiezas(REY, BLANCO) == 1 && t.getNumPiezas(REY, NEGRO) == 1;
    }

    private Tablero crearTableroClasico() {
        this.t = new Tablero(8, 8);

        t.setPieza(new Pieza(TORRE, BLANCO, new Posicion(1, 1)));
        t.setPieza(new Pieza(TORRE, BLANCO, new Posicion(8, 1)));
        t.setPieza(new Pieza(TORRE, NEGRO, new Posicion(1, 8)));
        t.setPieza(new Pieza(TORRE, NEGRO, new Posicion(8, 8)));
        t.setPieza(new Pieza(CABALLO, BLANCO, new Posicion(2, 1)));
        t.setPieza(new Pieza(CABALLO, BLANCO, new Posicion(7, 1)));
        t.setPieza(new Pieza(CABALLO, NEGRO, new Posicion(2, 8)));
        t.setPieza(new Pieza(CABALLO, NEGRO, new Posicion(7, 8)));

        t.setPieza(new Pieza(ALFIL, BLANCO, new Posicion(3, 1)));
        t.setPieza(new Pieza(ALFIL, BLANCO, new Posicion(6, 1)));
        t.setPieza(new Pieza(ALFIL, NEGRO, new Posicion(3, 8)));
        t.setPieza(new Pieza(ALFIL, NEGRO, new Posicion(6, 8)));

        t.setPieza(new Pieza(DAMA, BLANCO, new Posicion(4, 1)));
        t.setPieza(new Pieza(DAMA, NEGRO, new Posicion(4, 8)));

        t.setPieza(new Pieza(REY, BLANCO, new Posicion(5, 1)));
        t.setPieza(new Pieza(REY, NEGRO, new Posicion(5, 8)));

        for(int i = 1; i <= 8; i++)
            t.setPieza(new Pieza(PEON, BLANCO, new Posicion(i, 2)));

        for(int i = 1; i <= 8; i++)
            t.setPieza(new Pieza(PEON, NEGRO, new Posicion(i, 7)));

        t.setPieza(new Pieza(PEON, NEGRO, new Posicion(8, 8)));

        return t;
    }
}
