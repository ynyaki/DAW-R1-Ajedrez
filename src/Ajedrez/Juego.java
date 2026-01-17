import java.util.Scanner;

public class Juego {

    public static void main(Pieza.Color turno, Partida partida, Scanner sc){
        int hashTableroInicial = partida.getHashTablero();

        turnoMovimiento(turno, partida, sc);

        if (hashTableroInicial == partida.getHashTablero()) System.out.println("Se tendría terminar la partida.");

        partida.imprTablero();
    }

    private static void turnoMovimiento(Pieza.Color turno, Partida partida, Scanner sc) {
        Pieza[] mov = Formato.validarFormatoSAN(sc, turno);

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
            if (posicionesEnLimites(posDes)) {
                partida.mover(color, pieza, posDes);
            }
        }
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
}
