import java.util.Arrays;
import java.util.Scanner;

public class Juego {

    public static void main(Pieza.Color turno, Tablero t){
        int hashTableroInicial = Arrays.deepHashCode(t.get());

        turnoMovimiento(turno, t);

        if (hashTableroInicial == Arrays.deepHashCode(t.get())) System.out.println("Se tendría terminar la partida.");
        hashTableroInicial = Arrays.deepHashCode(t.get());

        t.impr();

        turno = colorContrario(turno);

        turnoMovimiento(turno, t);

        if (hashTableroInicial == Arrays.deepHashCode(t.get())) System.out.println("Se tendría terminar la partida.");
        t.impr();
    }

    private static void turnoMovimiento(Pieza.Color turno, Tablero t) {
        // TODO Solicitar movimiento
        Scanner sc = new Scanner(System.in);

        System.out.print("Org col: ");
        String orgColU = sc.nextLine();
        char orgCol = orgColU.toCharArray()[0];
        System.out.print("Org fila: ");
        int orgFila = sc.nextInt();

        sc.nextLine();

        Posicion posOrg = new Posicion(orgCol, orgFila);

        System.out.print("Des col: ");
        String desColU = sc.nextLine();
        char desCol = desColU.toCharArray()[0];
        System.out.print("Des fila: ");
        int desFila = sc.nextInt();

        Posicion posDes = new Posicion(desCol, desFila);

        // TODO Métodos de validación.
        // FIXME Esto es de Partida.
        // Si no es del color en turno se acaba.
        boolean esNoPosible = false;
        if (t.getPieza(posOrg) != null) {
            if (t.getPieza(posOrg).getColor() != turno) esNoPosible = true;
        }
        if (t.getPieza(posDes) != null) {
            if (t.getPieza(posDes).getColor() == turno) esNoPosible = true;
        }
        if (t.getPieza(posOrg) == null) esNoPosible = true;
        // Comprobar que tengan sea a una casilla valida.


        // TODO Bifurcación -> Se mueve, Se acaba la partida.
        if (!esNoPosible) {
            t.setPieza(t.getPieza(posOrg), posDes);
            t.getPieza(posOrg).setPos(posDes);

            t.setPieza(null, posOrg);
        }
    }

    private static Pieza.Color colorContrario(Pieza.Color color){
        return switch (color) {
            case Pieza.Color.BLANCO -> Pieza.Color.NEGRO;
            case Pieza.Color.NEGRO -> Pieza.Color.BLANCO;
        };
    }
}
