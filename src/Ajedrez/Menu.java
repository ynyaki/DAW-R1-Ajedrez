import java.util.Scanner;

/**
 * Programa de un juego de ajedrez, que permite configurar un tablero
 * con una posición inicial, así como ejecutar un movimiento, válido o no según
 * siga las reglas del ajedrez. El movimiento lo ejecutará el bando elegido, o
 * aquel que comience en jaque. Un movimiento ilegal hará que se pierda
 * la partida automáticamente.
 * @version 1.0 (20/01/26)
 * @author Juan, Iván, Manuel
 */
public abstract class Menu {

    private static final Pieza.Color BLANCO = Pieza.Color.BLANCO;
    private static final Pieza.Color NEGRO = Pieza.Color.NEGRO;

    private static Scanner sc;
    private static Partida partida;

    /**
     * Ejecuta el programa de ajedrez, permitiendo al usuario elegir
     * una configuración inicial, elegir turno y realizar un movimiento.
     */
    public static void ejecutar(Scanner sc) {
        Menu.sc = sc;
        partida = new Partida();
        Pieza.Color colorSalida;
        Formato.bienvenida();
        Formato.importar(sc, partida);
        partida.imprTablero();
        colorSalida = elegirTurno(sc);
        Juego.ejecutar(colorSalida, partida, sc);
    }

    public static Pieza.Tipo mostrarMenuPromocion() {
        String opcion;
        Pieza.Tipo tipo = null;

        System.out.println();
        System.out.println("PROMOCIÓN DE PEÓN");

        while (tipo == null) {

            System.out.println("Piezas disponibles a las que promocionar:");
            System.out.println("D → Dama (♔)");
            System.out.println("T → Torre (♖)");
            System.out.println("A → Alfil (♗)");
            System.out.println("C → Caballo (♘)");
            System.out.println();

            System.out.print("Promocionar a: ");
            opcion = sc.nextLine().trim().toUpperCase();
            tipo = switch (opcion) {
                case "D" -> Pieza.Tipo.DAMA;
                case "T" -> Pieza.Tipo.TORRE;
                case "A" -> Pieza.Tipo.ALFIL;
                case "C" -> Pieza.Tipo.CABALLO;
                default -> null;
            };

            if(tipo == null) {
                System.out.println("Error: Comando inválido");
            }
        }
        System.out.println();
        return tipo;
    }

    private static Pieza.Color elegirTurno(Scanner sc){
        final String ROJO = "\u001B[31m";
        final String RESET = "\u001B[0m";

        String color;
        Pieza.Color colorEnJaque = turnoJaque();
        Pieza.Color pColor = null;
        boolean esTurnoValido = false;

        if(colorEnJaque != null) {
            esTurnoValido = true;
            if (colorEnJaque == BLANCO) {
                System.out.printf("%s¡MUEVEN LAS BLANCAS AL ESTAR EN AMENAZA DE JAQUE!\n%s", ROJO, RESET);
                return BLANCO;
            }

            if (colorEnJaque == NEGRO) {
                System.out.printf("%s¡MUEVEN LAS NEGRAS AL ESTAR EN AMENAZA DE JAQUE!\n%s", ROJO, RESET);
                return NEGRO;
            }
        }

        do {
            System.out.print("Elige el color que moverá pieza (B/N): ");
            color = sc.nextLine();

            if(color.equalsIgnoreCase("b")){
                pColor = Pieza.Color.BLANCO;
                esTurnoValido=true;
            } else if(color.equalsIgnoreCase("n")) {
                pColor = Pieza.Color.NEGRO;
                esTurnoValido=true;
            } else {
                System.out.println("Error, Introduce b para Blancas o n para negras");
            }
        } while(!esTurnoValido);

        if(pColor == BLANCO)
            System.out.println("Mueven las Blancas.");
        else if(pColor == NEGRO)
            System.out.println("Mueven las Negras.");

        return pColor;
    }

    /**
     * Comprueba si existe un jaque, y devuelve el color en jaque.
     * @return <code>Pieza.Color</code>
     */
    private static Pieza.Color turnoJaque() {
        if(partida.estaReyEnJaque(BLANCO) )
            return BLANCO;
        else if(partida.estaReyEnJaque(NEGRO))
            return NEGRO;
        else
            return null;
    }
}
