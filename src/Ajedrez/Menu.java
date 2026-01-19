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

    private static final Pieza.Tipo PEON = Pieza.Tipo.PEON;
    private static final Pieza.Tipo CABALLO = Pieza.Tipo.CABALLO;
    private static final Pieza.Tipo TORRE = Pieza.Tipo.TORRE;
    private static final Pieza.Tipo ALFIL = Pieza.Tipo.ALFIL;
    private static final Pieza.Tipo DAMA = Pieza.Tipo.DAMA;
    private static final Pieza.Tipo REY = Pieza.Tipo.REY;

    private static final Pieza.Color BLANCO = Pieza.Color.BLANCO;
    private static final Pieza.Color NEGRO = Pieza.Color.NEGRO;

    private static Scanner sc;
    private static Partida partida;
    private static Pieza.Color turno;

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

        System.out.println("La partida actual:");
        partida.imprTablero();
        System.out.print("\n");

        colorSalida = elegirTurno(sc);

        Juego.main(colorSalida, partida, sc);
    }

    private static Pieza.Color elegirTurno(Scanner sc){
        final String ROJO = "\u001B[31m";
        final String RESET = "\u001B[0m";

        String color;
        Pieza.Color colorEnJaque = turnoJaque();
        Pieza.Color pColor = null;
        boolean esTurnoValido = false;

        if (colorEnJaque != null) {
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

        do{
            System.out.print("Que color quieres que empiece primero (b/n): ");
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

        if(pColor == BLANCO){
            System.out.println("Mueven las Blancas.");
        }

        if(pColor == NEGRO){
            System.out.println("Mueven las Negras.");
        }

        return pColor;
    }

    /**
     * Comprueba si existe un jaque, y devuelve el color en jaque.
     * @return <code>Pieza.Color</code>
     */
    private static Pieza.Color turnoJaque(){
        if(partida.estaReyEnJaque(Pieza.Color.BLANCO) ){
            return Pieza.Color.BLANCO;
        }

        if(partida.estaReyEnJaque(Pieza.Color.NEGRO)){
            return Pieza.Color.NEGRO;
        }

        return null;
    }

    private static Pieza.Tipo menuPromocionar() {
        // TODO Mensaje de pedir pieza (con instrucciones de comando para cada pieza)
        /* Ejemplo:
        *
        * Elija a qué pieza desea promocionar:
        *   - Dama: D
        *   - Torre: T
        *   - Alfil: A
        *   - Caballo: C
        *
        * Promocionar a: "D"
        */
        // TODO Escáner para obtener input
        //   (decidir si es mov. ilegal o repetir inf. hasta input válido).
        // TODO Validación del tipo de pieza
        // TODO Cambiar valor de retorno
        return null;
    }
}
