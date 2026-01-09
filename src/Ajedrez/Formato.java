import java.util.Scanner;

public class Formato {

    private static Scanner sc;


    static boolean validarFormatoSAN(String movimiento) {
        while (movimiento == null) {
            System.out.println("Introduce un movimiento válido: ");
            movimiento = sc.nextLine();
            movimiento = movimiento.trim();

            /**
             * Quitamos espacios al principio y al final
             * Ej: "  Ta4 Ta5  " → "Ta4 Ta5"
             */
        }

        /**
         * Defino la SAN al programa (SAN = Notación Algebraica Estándar)
         * Ejemplos válidos:
         * Ta4
         * Ac3
         * a2   (peón, sin letra)
         */
        String NT = "[TACDR]?[a-h][1-8]";

        /**
         * Esto comprueba si el usuario introduce O + D Y D solo.
         * v v v v
         * */

        if (movimiento.matches(NT)) {
            return true;}
        if (movimiento.matches(NT + "\\s+" + NT)) {
            return true;
        }

        return false;
    }

}
