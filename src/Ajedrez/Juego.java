import java.util.Scanner;

public class Juego {

    public static final String AMARILLO = "\u001B[33m";
    public static final String RESET = "\u001B[0m";

    public static void ejecutar(Pieza.Color turno, Partida partida, Scanner sc){
        boolean cometidoMovimientoLegal = true;
        boolean movDifuso;
        int hashTableroInicial = partida.getHashTablero();

        Formato.reglasMovimientos();
        do {
            movDifuso = turnoMovimiento(turno, partida, sc);

            if (movDifuso) Formato.movimientoDifuso();
        } while (movDifuso);

        if(partida.hayPromocion()) {
            Pieza.Tipo tipoProm = Menu.mostrarMenuPromocion();
            partida.promocionar(tipoProm);
        }

        if (hashTableroInicial == partida.getHashTablero()) {
            System.out.print("\n");
            System.out.println("Se ha cometido un movimiento ilegal.");
            System.out.println("Por tanto no se ha cambiado el tablero.");
            cometidoMovimientoLegal = false;
        }

        System.out.println("Tablero final:");
        System.out.println();
        partida.imprTablero();

        Formato.criteriosParaVictoria();

        encontrarGanador(cometidoMovimientoLegal, partida, turno);
    }

    private static boolean turnoMovimiento(Pieza.Color turno, Partida partida, Scanner sc) {
        Pieza[] mov = Formato.validarFormatoSAN(sc, turno);
        boolean difusion = false;

        if (mov.length == 2) {
            // Datos supuestos de la actual posición.
            Posicion posAct = mov[0].getPos();
            Pieza.Tipo pieza = mov[0].getTipo();
            Pieza.Color color = mov[0].getColor();

            // Datos de la posición deseada.
            Posicion posDes = mov[1].getPos();

            // Validación del supuesto de posición.
            if (posicionesEnLimites(posAct, posDes)) {
                partida.mover(color, pieza, posAct, posDes);
            }
        } else if (mov.length == 1) {
            // Tipo de pieza supuesta.
            Pieza.Tipo pieza = mov[0].getTipo();
            Pieza.Color color = mov[0].getColor();

            // Posicion Deseada
            Posicion posDes = mov[0].getPos();

            // Validación del supuesto de posición.
            if (
                    posicionesEnLimites(posDes)
                    && partida.getCantPiezaMovil(color, pieza, posDes) == 1
            ) {
                partida.mover(color, pieza, posDes);
            }

            difusion = partida.getCantPiezaMovil(color, pieza, posDes) >= 2;
        }

        return difusion;
    }

    private static Pieza.Color colorContrario(Pieza.Color color){
        return switch (color) {
            case Pieza.Color.BLANCO -> Pieza.Color.NEGRO;
            case Pieza.Color.NEGRO -> Pieza.Color.BLANCO;
        };
    }

    private static boolean posicionesEnLimites(Posicion posDes) {
        boolean esPosDesCol = posDes.getCol() <= 8;
        boolean esPosDesFila = posDes.getFila() <= 8;

        return esPosDesCol && esPosDesFila;
    }

    private static boolean posicionesEnLimites(Posicion posAct, Posicion posDes) {
        boolean esPosActCol = posAct.getCol() <= 8;
        boolean esPosActFila = posAct.getFila() <= 8;

        boolean esPosDesCol = posDes.getCol() <= 8;
        boolean esPosDesFila = posDes.getFila() <= 8;

        return esPosActCol && esPosActFila && esPosDesCol && esPosDesFila;
    }

    private static Pieza.Color ganadorSegunValoresEstrategicos(Partida p) {
        Pieza[] piezasBlancas = p.getPiezasColor(Pieza.Color.BLANCO);
        Pieza[] piezasNegras = p.getPiezasColor(Pieza.Color.NEGRO);

        if (calcularValor(piezasBlancas) == calcularValor(piezasNegras)) return null;
        return (calcularValor(piezasBlancas) > calcularValor(piezasNegras)) ? Pieza.Color.BLANCO : Pieza.Color.NEGRO;
    }

    private static int calcularValor(Pieza[] lista) {
        int valPeon = 1;
        int valCaballo = 3;
        int valAlfil = 3;
        int valTorre = 5;
        int valDama = 9;
        int valRey = 0;
        int cantPuntos = 0;

        for (Pieza p : lista) {
            switch (p.getTipo()) {
                case Pieza.Tipo.PEON -> cantPuntos = cantPuntos + valPeon;
                case Pieza.Tipo.CABALLO -> cantPuntos = cantPuntos + valCaballo;
                case Pieza.Tipo.ALFIL -> cantPuntos = cantPuntos + valAlfil;
                case Pieza.Tipo.TORRE -> cantPuntos = cantPuntos + valTorre;
                case Pieza.Tipo.DAMA -> cantPuntos = cantPuntos + valDama;
                case Pieza.Tipo.REY -> cantPuntos = cantPuntos + valRey;
            }
        }

        return cantPuntos;
    }

    private static void encontrarGanador(boolean cometidoMovimientoLegal, Partida partida, Pieza.Color turno){
        if (cometidoMovimientoLegal) {
            Pieza.Color ganadorPuntos = ganadorSegunValoresEstrategicos(partida);

            if (partida.estaReyEnJaque(Pieza.Color.BLANCO) && partida.estaReyEnJaque(Pieza.Color.NEGRO)) {
                System.out.printf("Quedaron %sTablas%s por %sDOBLE JAQUE%s.\n", AMARILLO, RESET, AMARILLO, RESET);
            } else if (partida.estaReyEnJaque(Pieza.Color.BLANCO)) {
                System.out.printf("Ha ganado el color %sNegras%s.\n", AMARILLO, RESET);
            } else if (partida.estaReyEnJaque(Pieza.Color.NEGRO)) {
                System.out.printf("Ha ganado el color %sBlancas%s.\n", AMARILLO, RESET);
            } else if (ganadorPuntos == null){
                System.out.printf("Quedaron %sTablas%s por misma %scantidad%s de %spuntos%s.\n",
                        AMARILLO, RESET,
                        AMARILLO, RESET,
                        AMARILLO, RESET);
            } else {
                System.out.printf("Ha ganado el color %s%s%s\n", AMARILLO, ganadorPuntos, RESET);
            }
        } else {
            System.out.print("\n");
            System.out.printf("Ha ganado el color %s%s%s por %sMovimiento Ilegal%s.",
                    AMARILLO,
                    colorContrario(turno).toString(),
                    RESET,
                    AMARILLO,
                    RESET);
        }
    }
}
