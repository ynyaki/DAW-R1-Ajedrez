import java.util.Scanner;

public class Juego {

    public static void main(Pieza.Color turno, Partida partida, Scanner sc){
        int hashTableroInicial = partida.obtenerHashTablero();

        turnoMovimiento(turno, partida, sc);

        if (hashTableroInicial == partida.obtenerHashTablero()) System.out.println("Se tendría terminar la partida.");
        hashTableroInicial = partida.obtenerHashTablero();

        partida.imprTablero();

        turno = colorContrario(turno);

        turnoMovimiento(turno, partida, sc);

        if (hashTableroInicial == partida.obtenerHashTablero()) System.out.println("Se tendría terminar la partida.");
        partida.imprTablero();
    }

    private static void turnoMovimiento(Pieza.Color turno, Partida partida, Scanner sc) {
        Pieza[] mov = Formato.validarFormatoSAN(sc, turno);

        // TODO Métodos de validación.
        // FIXME Esto es de Partida.
        // Si no es del color en turno se acaba.
//        boolean esNoPosible = false;
//        if (partida.getPieza(posOrg) != null) {
//            if (partida.getPieza(posOrg).getColor() != turno) esNoPosible = true;
//        }
//        if (partida.getPieza(posDes) != null) {
//            if (partida.getPieza(posDes).getColor() == turno) esNoPosible = true;
//        }
//        if (partida.getPieza(posOrg) == null) esNoPosible = true;
//        // Comprobar que tengan sea a una casilla valida.
//
//
//        // TODO Bifurcación -> Se mueve, Se acaba la partida.
//        if (!esNoPosible) {
//            partida.setPieza(partida.getPieza(posOrg), posDes);
//            partida.getPieza(posOrg).setPos(posDes);
//
//            partida.setPieza(null, posOrg);
//        }
    }

    private static Pieza.Color colorContrario(Pieza.Color color){
        return switch (color) {
            case Pieza.Color.BLANCO -> Pieza.Color.NEGRO;
            case Pieza.Color.NEGRO -> Pieza.Color.BLANCO;
        };
    }
}
