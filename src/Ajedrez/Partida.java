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

    //  DELETE Pruebas de creación y muestra de tablero
    public void empezar() {
        Tablero t = crearTableroClasico();
        Pieza p1 = new Pieza(TORRE, BLANCO, 1, 8);
        colocar(p1);
        System.out.print(t);
    }

    public void colocar(Pieza p) {
        if(esColocacionValida(p))
            t.setPieza(p);
    }

    public boolean esColocacionValida(Pieza p) {
        return piezaDentroDeLimites(p)
                && noSuperaNumReyes()
                && noSuperaNumPiezas();
    }

    private boolean piezaDentroDeLimites(Pieza p) {
        int col = p.getCol();
        int fil = p.getFila();
        int colMax = t.getNCols();
        int filMax = t.getNFilas();

        return col <= colMax && fil <= filMax;
    }

    private boolean noSuperaNumReyes() {
        int nWK = t.getNumPiezas(REY, BLANCO);
        int nBK = t.getNumPiezas(REY, NEGRO);
        return (nWK <= 1 && nBK <= 1);
    }

    private boolean noSuperaNumPiezas() {
        int nW = t.getNumPiezas(BLANCO);
        int nB = t.getNumPiezas(NEGRO);
        return (nW <= 16 && nB <= 16);
    }

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

    private boolean numPeonesMax() {
        return t.getNumPiezas(PEON, BLANCO) > 8 && t.getNumPiezas(PEON, NEGRO) > 8;
    }

    /**
     * Método que devuelve True cuando se le pasa una pieza con una posición que todavía no ha sido ocupada.
     * @param Pieza
     * @return boolean
     */
    public boolean posicionOcupada(Pieza p) {
        boolean posicionValida = false;

        if (t.getPieza(p.getPos()) == null) {
            posicionValida = true;
        }

        return posicionValida;
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
