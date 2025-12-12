import java.util.Scanner;

public abstract class Menu {

    private static Scanner sc;

    public static void ejecutar(Scanner sc) {
        Menu.sc = sc;
        // TODO Desarrollar menú
        System.out.println("Hola, Mundo.");
    }
}
