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
        importar();

        p.imprimirTablero();

        // TODO
        // ordenJuego();
        // juego();
    }

    /**
     * Metodo para mostrar el mensaje de Bienvenida.
     * Solo <strong>imprime</strong> por terminal.
     */
    public static void bienvenida() {
        System.out.println("¡Bienvenido a nuestro programa de ajedrez!");
        System.out.println("Desarrollado por Iñaki, Iván, Juan y Manuel.");
        System.out.print("\n");

        System.out.println("Importa tu partida: ");
        System.out.println("Se debe usar la notación algebraica.");
        System.out.println("Consiste:");
        System.out.println("\t· Inicial de la pieza más su posición.");
        System.out.println("\t· Los peones no tienen inicial.");
        System.out.println("\t· La inicial en mayuscula.");
        System.out.println("\t· La posición en minuscula.");
        System.out.println("\t· Primero se indica la pieza.");
        System.out.println("\t· Segundo se indica la fila.");
        System.out.println("\t· Tercero se indica la columna.");
        System.out.println("\t· Sepera cada pieza con un espacio.");

        System.out.print("\n");

        System.out.println("Leyenda:");
        System.out.println("R → Rey   | D → Dama    |   T   → Torre");
        System.out.println("A → Alfil | C → Caballo | vacío → Peón");
        System.out.println("* El peón puede no tener letra.");

        System.out.print("\n");

        System.out.println("Ejemplo.:");
        System.out.println("Rg1 Tf1 h2 g2 f2 d4 e4 Ce5 a4 b3 c2 Ab2 Ta1");
    }

    /**
     * Metodo para la lógica de añadir las piezas de una partida.
     * <strong>Valida</strong> y <strong>coloca</strong> las piezas en el tablero.
     */
    public static void importar() {
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

                p.limpiar();    // Porque en caso de haberse ejecutado, las piezas se mantienen en el tablero.
        System.out.println("¿Cómo jugar?: ");
        // TODO Especificar más
        System.out.println("Para jugar introduzca una línea con la inicial de las piezas + la posiciónes en el tablero.");
        System.out.println("Debería de verse tal que así > Cb1 o Cg1, Ta1 o Th1, Ac1 o Af1...");
        System.out.println("La partida se detendrá si se detecta algún movimiento no permitido" +
                " o si desea salir introduciendo ( ).");
        System.out.println();
        System.out.println("¡Esperamos que disfrutes la partida!");
        comenzarPartida();

                // Validación dínamica mientras se añaden las piezas.
                for (Pieza pieza:listaPiezas) {
                    if (!p.colocar(pieza)) {
                        importacionCorrecta = false;
                        System.out.println("Se ha encontrado un problema en la importación.");
                        break;
                    }
                }

                // Validación posterior a colocar las piezas.
                if (!p.valPostColocar() && importacionCorrecta) {
                    importacionCorrecta = false;
                    System.out.println("Se ha encontrado un problema en la importación.");
                }
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
            boolean turnoBlancas = true; // empiezan blancas
            boolean jugando = true;
            System.out.print("Introduce la posición de las piezas de las figuras blancas y negras: ");
            sc.nextLine();
            // TODO Añadir coso de Ivan cuando pushee su .java :,)
            // Formato.colocarP();
            // - metodo de Ivan

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
