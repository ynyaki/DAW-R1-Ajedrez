import java.util.Scanner;

public abstract class Menu {

    private static Scanner sc;

    public static void ejecutar(Scanner sc) {
        Menu.sc = sc;

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
        System.out.println("Para jugar introduce la inicial de la pieza + la posición en el tablero.");
        System.out.println("La partida se detendrá si se detecta algún movimiento no permitido");
        System.out.println("o si desea salir introduciendo ( ).");
        System.out.println(" ");
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

    private Pieza.Color turnoActual;
    private boolean partidaActiva;

    public void jugar(Scanner sc) {

        while (partidaActiva) {
            // Que vaya imprimiendo el turno actual

            System.out.println();
            System.out.println("Turno de las " +
                    (turnoActual == Pieza.Color.BLANCO ? "BLANCAS" : "NEGRAS"));

            System.out.print("Introduce tu jugada (o SALIR): ");
            String entrada = sc.nextLine();

            if (entrada.equalsIgnoreCase("SALIR")) {
                System.out.println("Partida finalizada.");
                partidaActiva = false;
                return;
            }

            cambiarTurno();
        }
    }

    private void cambiarTurno() {
        if (turnoActual == Pieza.Color.BLANCO) {
            turnoActual = Pieza.Color.NEGRO;
        } else {
            turnoActual = Pieza.Color.BLANCO; }
    }
}
