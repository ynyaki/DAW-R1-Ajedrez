// TODO Documentar
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
        pruebaImprTablero(p);

        pruebaMover(p, 5, 2, 5, 4);
            pruebaMover(p, 5, 7, 5, 7); // Misma pos
            pruebaMover(p, 5, 7, 5, 4); // Mov de 3C + cas ocupada
        pruebaMover(p, 5, 7, 5, 5);
        pruebaMover(p, 7, 1, 6, 3);
        pruebaMover(p, 6, 7, 6, 6);
    }

    // DELETE Método de pruebas
    private static void pruebaImprTablero(Partida p) {
        System.out.println();
        System.out.println();
        p.imprTablero();
        System.out.println();
        System.out.println();
        System.out.println();
    }

    // DELETE Método de pruebas
    private static void pruebaMover(
            Partida p, int cPI, int fPI, int cPF, int fPF) {
        if(p.mover(new Posicion(cPI, fPI), new Posicion(cPF, fPF))) {
            System.out.println("Movimiento válido");
            System.out.println();
            p.imprTablero();
            System.out.println();
            System.out.println();
            System.out.println();
        } else {
            System.out.println("Movimiento inválido");
            System.out.println();
        }
    }

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
        if(hayMov)
            t.moverPieza(p, nPos);
        return hayMov;
    }

    // TODO Documentar
    public boolean mover(Posicion pos, Posicion nPos) {
        return mover(t.getPieza(pos), nPos);
    }

    // TODO Revisar
    /**
     * Coloca pieza en el tablero. Aparte de develver si es posible o no colocar la pieza, en caso de poder
     * colocarla la coloca.
     * @param p Objeto de tipo <code>Pieza</code>
     * @return <code>True</code> cuando se puede colocar la pieza en el tablero, <code>False</code> en caso contrario.
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
     * Validación post Piezas colocadas. Existen validaciones que no se pueden comprobar mientras se colocan las piezas.
     * @return <code>True</code> validación válida <code>False</code> validación inválida.
     */
    public boolean validarPartida() {
        if(numReyesValido())
            return (estaReyEnJaque(BLANCO) && estaReyEnJaque(NEGRO));
        else
            return false;
    }

    // TODO Documentar
    public boolean estaReyEnJaque(Pieza.Color colorRey) {
        boolean reyEnJaque = false;
        Pieza rey;

        if(colorRey.equals(BLANCO))
            rey = rB;
        else if(colorRey.equals(NEGRO))
            rey = rN;
        else
            return false;

        for(Pieza[] fila : t.get())
            for(Pieza pieza : fila)
                if(pieza != null && pieza.getColor() != rey.getColor()
                        && esMovLegal(pieza, rey.getPos()))
                    reyEnJaque = true;

        return reyEnJaque;
    }

    // TODO Documentar
    public boolean hayPromocion(Pieza p, Posicion nPos) {
        Posicion pos = p.getPos();
        Posicion posPB = new Posicion(pos.getCol(), 8);
        Posicion posPN = new Posicion(pos.getCol(), 1);
        return (t.estaVacia(nPos) && p.getTipo().equals(PEON)
                && (p.getColor().equals(BLANCO) && nPos.esMismaPos(posPB))
                || (p.getColor().equals(NEGRO) && nPos.esMismaPos(posPN)));
    }

    // TODO Documentar
    public void promocionar(Pieza p, Posicion nPos, Pieza.Tipo tipoNuevo) {
        t.setPieza(new Pieza(tipoNuevo, p.getColor(), nPos));
        t.borrarPieza(p);
    }

    // TODO Documentar
    public void imprTablero() {
        t.impr();
    }

    // TODO Añadir restricciones
    private boolean esEnPassant(Pieza p, Posicion nPos) {
        Posicion posEnPI;
        Posicion posEnPD;
        Posicion pos = p.getPos();
        int col = pos.getCol();
        int fila = pos.getFila();
        int desp1C;

        if(p.getColor() == BLANCO)
            desp1C = 1;
        else if(p.getColor() == NEGRO)
            desp1C = -1;
        else
            desp1C = 0;

        posEnPI = new Posicion(col, fila + desp1C);
        posEnPD = new Posicion(col, fila + desp1C);

        return (nPos.esMismaPos(posEnPI) && t.estaVacia(posEnPI)
                || nPos.esMismaPos(posEnPD) && t.estaVacia(posEnPD));
    }

    // FIXME No borrar peón
    private void moverEnPassant(Pieza p, Posicion nPos) {
        Posicion posPComida;
        int desp1C;

        if(p.getColor() == BLANCO)
            desp1C = 1;
        else if(p.getColor() == NEGRO)
            desp1C = -1;
        else
            desp1C = 0;

        posPComida = new Posicion(nPos.getCol(), nPos.getFila() - desp1C);

        t.moverPieza(p, nPos);
        t.borrarPieza(t.getPieza(posPComida));
    }

    private boolean esMovLegal(Pieza p, Posicion nPos) {
        return (noSuperaMargenes(nPos)
                && esDiferentePos(p, nPos)
                && noEsPiezaPropia(p, nPos)
                && esMovLegalDePieza(p, nPos));
    }

    // TODO Añadir restricciones faltantes
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

    // TODO Revisar
    private boolean esMovLegalTorre(Pieza p, Posicion nPos) {
        Posicion pos = p.getPos();
        boolean esMovEnCruz = (pos.enMismaFila(nPos) || pos.enMismaCol(nPos));
        boolean esMovConVision = false;
        int pC = pos.getCol();
        int pF = pos.getFila();
        int nC = nPos.getCol();
        int nF = nPos.getFila();

        if(nF > pF) {
            // Dir. Arriba
            for(int i = 0; (pF + i <= 8)
                    && t.estaVacia(new Posicion(pC, pF + i)); i++) {
                if(nF <= i)
                    esMovConVision = true;
            }
        } else if(nF < pF) {
            // Dir. Abajo
            for(int i = 0; (pF - i >= 1)
                    && t.estaVacia(new Posicion(pC, pF - i)); i++) {
                if(nF >= i)
                    esMovConVision = true;
            }
        } else if(nC > pC) {
            // Dir. Izquierda
            for(int i = 0; (pC - i >= 1)
                    && t.estaVacia(new Posicion(pC - i, pF)); i++) {
                if(nC >= i)
                    esMovConVision = true;
            }
        } else if(nC < pC) {
            // Dir. Derecha
            for(int i = 0; (pC + i <= 8)
                    && t.estaVacia(new Posicion(pC + i, pF)); i++) {
                if(pC <= i)
                    esMovConVision = true;
            }
        }

        return (esMovEnCruz && esMovConVision);
    }

    // TODO Añadir restricciones faltantes
    private boolean esMovLegalAlfil(Pieza p, Posicion nPos) {
        return p.getPos().esMovDiagonal(nPos);
    }

    private boolean esMovLegalCaballo(Pieza p, Posicion nPos) {
        return p.getPos().esMovEnL(nPos);
    }

    private boolean esMovLegalPeon(Pieza p, Posicion nPos) {
        int dir = 0;
        boolean enFilaMov2C = false;
        int desp1C;
        int desp2C;
        Posicion posMov1C;
        Posicion posMov2C;
        Posicion posComI;
        Posicion posComD;
        Posicion pos = p.getPos();
        int col = pos.getCol();
        int fila = pos.getFila();

        if(p.getColor() == BLANCO) {
            dir = 1;
            if(pos.getFila() == 2)
                enFilaMov2C = true;
        }
        else if(p.getColor() == NEGRO) {
            dir = -1;
            if(pos.getFila() == 7)
                enFilaMov2C = true;
        }

        desp1C = dir;
        desp2C = 2 * dir;

        posMov1C = new Posicion(col, fila + desp1C);
        posMov2C = new Posicion(col, fila + desp2C);
        posComI = new Posicion(col - 1, fila + desp1C);
        posComD = new Posicion(col + 1, fila + desp1C);

        return (nPos.esMismaPos(posMov1C) && t.estaVacia(posMov1C)
                || (nPos.esMismaPos(posMov2C) && enFilaMov2C
                && t.estaVacia(posMov1C) && t.estaVacia(posMov2C))
                || nPos.esMismaPos(posComI) && t.estaOcupada(posComI)
                || nPos.esMismaPos(posComD) && t.estaOcupada(posComD));
    }

    private boolean noSuperaMargenes(Posicion pos) {
        return (pos.getCol() <= t.getNCols() && pos.getFila() <= t.getNFilas());
    }

    private boolean esDiferentePos(Pieza p, Posicion nPos) {
        return !p.getPos().esMismaPos(nPos);
    }

    private boolean noEsPiezaPropia(Pieza p, Posicion nPos) {
        if(t.getPieza(nPos) == null)
            return true;
        else
            return (p.getColor() != t.getPieza(nPos).getColor());
    }

    private boolean esPosVacia(Posicion pos) {
        return t.estaVacia(pos);
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

    private boolean numReyesValido() {
        return (t.getNumPiezas(REY, BLANCO) == 1
                && t.getNumPiezas(REY, NEGRO) == 1);
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