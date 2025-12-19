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
        p.empezar();
    }

    public void empezar() {
        t = crearTableroClasico();
        Pieza p1 = new Pieza(TORRE, BLANCO, 1, 8);
        colocar(p1);
        System.out.print(t);
    }

    public void colocar(Pieza p) {
        if(esColocacionValida(p))
            t.setPieza(p);
        if(p.getTipo() == REY) {
            // TODO Asignar pieza a rB y rN
        }
    }

    // Métodos de validación

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
     * Método que devuelve <code>True</code> cuando se le pasa una pieza con una posición no ocupada.
     * @param p Representa un objeto de tipo <code>Pieza</code>.
     * @return boolean
     */
    public boolean posicionNoOcupada(Pieza p) {
        boolean posicionValida = false;

        if (t.getPieza(p.getPos()) == null) {
            posicionValida = true;
        }

        return posicionValida;
    }

    // Métodos de movimiento

    private boolean esMovLegal(Pieza p, Posicion pos) {
        return false;
    }

    public Pieza.Color getJaque() {
        Pieza.Color color;
        if(hayJaque(rB))
            color = BLANCO;
        else if(hayJaque(rN))
            color = NEGRO;
        else
            color = null;
        return color;
    }

    private boolean hayJaque(Pieza rey) {
        boolean hayJaque = false;
        for(Pieza[] fila : t.get())
            for(Pieza pieza : fila)
                if(pieza.getColor() != rey.getColor()
                        && esMovLegal(pieza, rey.getPos()) && !hayJaque)
                    hayJaque = true;
        return hayJaque;
    }

    // DELETE Métodos provisionales

    private Tablero crearTableroClasico() {
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

        return t;
    }
}
