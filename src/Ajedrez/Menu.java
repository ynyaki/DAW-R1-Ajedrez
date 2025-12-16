import java.util.Scanner;

public abstract class Menu {

    private static Scanner sc;

    // TODO Desarrollar menú
    public static void ejecutar(Scanner sc) {
        Menu.sc = sc;

        //  DELETE Pruebas de creación y muestra de tablero
        Tablero t = new Tablero(8, 8);
        t.colocar(new Pieza(Pieza.Tipo.TORRE, Pieza.Color.BLANCO, new Posicion(1, 1)));
        System.out.println(t);
    }
}
