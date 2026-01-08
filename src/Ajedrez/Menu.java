import java.util.Scanner;

public abstract class Menu {

    private static Scanner sc;
    private static Partida partida;

    private static Pieza.Color turnoActual;
    private static boolean partidaActiva;

    public static void ejecutar(Scanner sc) {
        Menu.sc = sc;
        partida = new Partida();
        bienvenida();

    // DELETE Pruebas de Partida
        Partida p = new Partida();
        p.empezar();
    }

    public static void bienvenida() {
        System.out.println("¡Bienvenido a nuestro programa de ajedrez!");
        System.out.println("Desarrollado por Iñaki, Iván, Juan y Manuel.");
        System.out.println(" ");

        System.out.println("¿Cómo jugar?: ");
        // TODO Especificar más
        System.out.println("Para jugar introduzca una línea con la inicial de las piezas + la posiciónes en el tablero.");
        System.out.println("Debería de verse tal que así > Cb1 o Cg1, Ta1 o Th1, Ac1 o Af1...");
        System.out.println("La partida se detendrá si se detecta algún movimiento no permitido" +
                " o si desea salir introduciendo ( ).");
        System.out.println();
        System.out.println("¡Esperamos que disfrutes la partida!");
        comenzarPartida();

    }

    public static void comenzarPartida() {

        //Metodo para
        System.out.println("¿Deseas iniciar una partida? (S/N)");
        String res = sc.nextLine();

        if (res.equalsIgnoreCase("S")){

            boolean turnoBlancas = true; // empiezan blancas
            boolean jugando = true;
            System.out.print("Introduce la posición de las piezas de las figuras blancas y negras: ");
            sc.nextLine();
            // TODO Añadir coso de Ivan cuando pushee su .java :,)
            // Formato.colocarP();
            // - metodo de Ivan

            while (jugando) {

                if (turnoBlancas) {
                    System.out.println("Turno de las BLANCAS");
                } else {
                    System.out.println("Turno de las NEGRAS");
                }

                System.out.print("Introduce tu jugada (o SALIR): ");
                String entrada = sc.nextLine();

                if (entrada.equalsIgnoreCase("SALIR")) {
                    System.out.println("Partida finalizada.");
                    jugando = false;
                } else {
                    turnoBlancas = !turnoBlancas;
                }

                System.out.println();
            }
        }
    }

    private static boolean validarMovimiento(String movimiento) {
            // Si el usuario no ha escrito nada (null), es inválido ya de por si xD
            if (movimiento == null) {
                /* return false;*/
                // TODO que no devuelva falso sino que simplemente vuelva a pedir movimiento
            }

            //quitamos espacios al principio y al final
            // Ej: "  Ta4 Ta5  " → "Ta4 Ta5"
            movimiento = movimiento.trim();

        /*
         Defino como se ha de poner
         Ejemplos válidos:
         - Ta4
         - Ac3
         - a2   (peón, sin letra)
        */
            String norma = "[TACDR]?[a-h][1-8]";

            String movimientoValido = norma + "\\s+" + norma;
        /*
         comprueba si se cumple la regla que hemos hecho
         */
            return movimiento.matches(movimientoValido);
        }


}
