import java.util.Scanner;

public class Formato {

    private static Scanner sc;

    public static void importar(Partida p) {
        boolean importacionCorrecta;
        String piezasBlancas;
        String piezasNegras;
        String[] listaPiezasBlancas;
        String[] listaPiezasNegras;
        Pieza[] listaPiezas;

        do {
            System.out.print("Piezas BLANCAS: ");
            piezasBlancas = sc.nextLine();

            System.out.print("Piezas NEGRAS: ");
            piezasNegras = sc.nextLine();

            System.out.print("\n");

            listaPiezasBlancas = listarPiezas(piezasBlancas);
            listaPiezasNegras = listarPiezas(piezasNegras);
            importacionCorrecta = true;

            // Valida la sintaxis
            if (!esValidarSintaxis(listaPiezasBlancas) || !esValidarSintaxis(listaPiezasNegras)) {
                importacionCorrecta = false;
                System.out.println("Se ha encontrado un problema en la importación.");
            }

            // Crea las piezas y se valida.
            if (importacionCorrecta) {
                listaPiezas = crearPiezas(listaPiezasBlancas, listaPiezasNegras);

                // TODO Añadir método correspondiente
                // p.limpiar();    // Porque en caso de haberse ejecutado, las piezas se mantienen en el tablero.
                System.out.println("¿Cómo jugar?: ");
                // TODO Especificar más
                System.out.println("Para jugar introduzca una línea con la inicial de las piezas + la posiciónes en el tablero.");
                System.out.println("Debería de verse tal que así > Cb1 o Cg1, Ta1 o Th1, Ac1 o Af1...");
                System.out.println("La partida se detendrá si se detecta algún movimiento no permitido" +
                        " o si desea salir introduciendo ( ).");
                System.out.println();
                System.out.println("¡Esperamos que disfrutes la partida!");
                Menu.comenzarPartida();

                // Validación dínamica mientras se añaden las piezas.
                for (Pieza pieza:listaPiezas) {
                    // TODO Quitar comentario
                    /*
                    if (!p.colocar(pieza)) {
                        importacionCorrecta = false;
                        System.out.println("Se ha encontrado un problema en la importación.");
                        break;
                    }
                     */
                }

                // TODO Añadir método correspondiente
                // Validación posterior a colocar las piezas.
                /*
                if (!p.valPostColocar() && importacionCorrecta) {
                    importacionCorrecta = false;
                    System.out.println("Se ha encontrado un problema en la importación.");
                }
                 */
            }
        } while(!importacionCorrecta);
    }

    /**
     * Recorta las ordenes pasadas por parametro y las almacena en un array.
     * @param conjuntoPiezas Un conjunto de piezas destro de un string en formato "Rg1 Ta2 a2"
     * @return Una array con las posiciones pasadas separadas.
     */
    private static String[] listarPiezas(String conjuntoPiezas) {
        conjuntoPiezas = conjuntoPiezas.trim();
        StringBuilder piezasIterables = new StringBuilder(conjuntoPiezas);
        String[] piezas;

        // Sacar el número de ordenes en el string.
        int cantidadEspacios = 1;
        for (int i = 0; i < conjuntoPiezas.length(); i++) {
            if (conjuntoPiezas.charAt(i) == ' ') cantidadEspacios++;
        }

        piezas = new String[cantidadEspacios];

        // Añadir las ordenes al array.
        for (int i = 0; i < piezas.length; i++) {
            String pieza = (piezasIterables.indexOf(" ") == -1 ) ?
                    piezasIterables.toString() :
                    piezasIterables.substring(0, piezasIterables.indexOf(" ") + 1);

            piezas[i] = pieza.trim();
            piezasIterables.delete(0, piezasIterables.indexOf(" ") + 1);
        }
        return piezas;
    }

    /**
     * Valida que la sistexis de la orden sea correcta.
     * @param listaPiezas Array con las ordenes a validar.
     * @return <code>True</code> si la sintaxis es correcta
     * <code>False</code> si la sintaxis <strong>NO</strong> es correcta.
     */
    private static boolean esValidarSintaxis(String[] listaPiezas) {
        char[] inicialesValidas = {'R', 'D', 'T', 'A', 'C'};

        for (String pieza:listaPiezas){
            if (pieza.length() > 3 || pieza.length() < 2) return false;

            if (pieza.length() == 3) {
                boolean esInicialValida = false;

                if (!Character.isLetter(pieza.charAt(0))) return false;
                if (!Character.isLetter(pieza.charAt(1))) return false;
                if (!Character.isDigit(pieza.charAt(2))) return false;

                if (Character.isLowerCase(pieza.charAt(0))) return false;
                if (Character.isUpperCase(pieza.charAt(1))) return false;

                for (char inicial:inicialesValidas) {
                    if (pieza.charAt(0) == inicial) {
                        esInicialValida = true;
                        break;
                    }
                }

                if (!esInicialValida) return false;
            }

            if (pieza.length() == 2) {
                if (!Character.isLetter(pieza.charAt(0))) return false;
                if (!Character.isDigit(pieza.charAt(1))) return false;

                if (Character.isUpperCase(pieza.charAt(0))) return false;
            }
        }

        return true;
    }

    /**
     * Crea un array con las piezas que se ordenan en los arrays pasador por parametro.
     * @param blancas Array con las ordenes de colocación de la piezas blancas.
     * @param negras Array con las ordenes de colocación de las piezas negras.
     * @return Array de tipo <code>Pieza</code> con las todas las piezas de las dos listas.
     */
    private static Pieza[] crearPiezas(String[] blancas, String[] negras) {
        Pieza[] listaPiezas = new Pieza[blancas.length + negras.length]; // Crea un array con la longitud de los dos arrays.
        Pieza.Tipo tipoPieza;
        char col;
        int fila;

        // Crear BLANCAS.
        for (int i = 0; i < blancas.length; i++) {
            tipoPieza = Pieza.obtenerTipoPieza(blancas[i]);
            col = (blancas[i].length() == 3) ? blancas[i].charAt(1) : blancas[i].charAt(0); // Recoge la columna especificada en la orden.
            fila = Integer.parseInt(        // Recoge la fila especificada en la orden.
                    String.valueOf(blancas[i].charAt(blancas[i].length() - 1))  // Si no se pasa a String trataría el char como su número de UNICODE.
            );

            listaPiezas[i] = new Pieza(tipoPieza, Pieza.Color.BLANCO, new Posicion(col, fila)); // Crea y añade la pieza al Array.
        }

        // Crear NEGRAS.
        for (int i = 0; i < negras.length; i++) {
            tipoPieza = Pieza.obtenerTipoPieza(negras[i]);
            col = (negras[i].length() == 3) ? negras[i].charAt(1) : negras[i].charAt(0);
            fila = Integer.parseInt(
                    String.valueOf(negras[i].charAt(negras[i].length() - 1))
            );

            listaPiezas[blancas.length + i] = new Pieza(tipoPieza, Pieza.Color.NEGRO, new Posicion(col, fila));
        }

        return listaPiezas;
    }

    public static String validarFormatoSAN(String movimiento) {
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

        String NT = "[TACDR]?[a-h][1-8]([a-h][1-8])?";


        /**
         * Esto comprueba si el usuario introduce O + D Y D solo.
         * v v v v
         * */

        if (movimiento.matches(NT) ||
                movimiento.matches(NT + "\\s+" + NT)) {
            return movimiento;

        }
        // TODO el formato correcto de movimiento es Cb6g4 (Ejemplo)
        return null;
    }
}
