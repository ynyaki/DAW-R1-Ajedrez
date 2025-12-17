import java.util.Scanner;

public abstract class Menu {

    private static Scanner sc;

    public static void ejecutar(Scanner sc) {
        Menu.sc = sc;

        // DELETE Pruebas de Partida
        Partida p = new Partida();
        p.empezar();
    }
}
