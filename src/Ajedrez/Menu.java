import java.util.Scanner;

// TODO Documentar
public abstract class Menu {

    private static final Pieza.Tipo PEON = Pieza.Tipo.PEON;
    private static final Pieza.Tipo CABALLO = Pieza.Tipo.CABALLO;
    private static final Pieza.Tipo TORRE = Pieza.Tipo.TORRE;
    private static final Pieza.Tipo ALFIL = Pieza.Tipo.ALFIL;
    private static final Pieza.Tipo DAMA = Pieza.Tipo.DAMA;
    private static final Pieza.Tipo REY = Pieza.Tipo.REY;

    private static final Pieza.Color BLANCO = Pieza.Color.BLANCO;
    private static final Pieza.Color NEGRO = Pieza.Color.NEGRO;

    private static Partida partida;
    private static Pieza.Color turno;

    public static void ejecutar(Scanner sc) {
        partida = new Partida();
        Pieza.Color colorSalida;

        Formato.bienvenida();
        Formato.importar(sc, partida);

        partida.imprTablero();

        System.out.print("\n");

        colorSalida = elegirTurno(sc);

        Juego.main(colorSalida, partida, sc);

        // TODO
        // juego();
    }

    private static Pieza.Color elegirTurno(Scanner sc){
        String color;
        Pieza.Color colorEnJaque = turnoJaque();
        Pieza.Color pColor = null;
        boolean esTurnoValido = false;

        if (colorEnJaque != null) {
            esTurnoValido = true;
            if (colorEnJaque == BLANCO) {
                System.out.println("¡MUEVEN LAS BLANCAS AL ESTAR EN AMENAZA DE JAQUE!");
                return BLANCO;
            }

            if (colorEnJaque == NEGRO) {
                System.out.println("¡MUEVEN LAS NEGRAS AL ESTAR EN AMENAZA DE JAQUE!");
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
