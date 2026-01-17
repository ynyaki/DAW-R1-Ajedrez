import java.util.Scanner;

public class Formato {
    private static final Pieza.Tipo PEON = Pieza.Tipo.PEON;
    private static final Pieza.Tipo CABALLO = Pieza.Tipo.CABALLO;
    private static final Pieza.Tipo TORRE = Pieza.Tipo.TORRE;
    private static final Pieza.Tipo ALFIL = Pieza.Tipo.ALFIL;
    private static final Pieza.Tipo DAMA = Pieza.Tipo.DAMA;
    private static final Pieza.Tipo REY = Pieza.Tipo.REY;

    /**
     * Método para mostrar el mensaje de Bienvenida.
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
        System.out.println("\t· La inicial en mayúscula.");
        System.out.println("\t· La posición en minúscula.");
        System.out.println("\t· Primero se indica la pieza.");
        System.out.println("\t· Segundo se indica la fila.");
        System.out.println("\t· Tercero se indica la columna.");
        System.out.println("\t· Separa cada pieza con un espacio.");

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
     * Imprime las reglas para mover las piezas.
     * Solo <strong>imprime</strong> por terminal.
     */
    public static void reglasMovimientos() {
        System.out.print("\n");
        System.out.println("Para mover una pieza puedes: ");
        System.out.println("\t· Si solo puede llegar a la casilla de destino una unica pieza puedes solo escribir la casilla de destino.");
        System.out.println("\t\tEjemplo.: Rh2 → R para representar al Rey; h2 es donde quieres mover al rey.");
        System.out.println("\t· Si existen varias piezas que pueden llegar al destino escribe posición de inicio y final.");
        System.out.println("\t\tEjemplo.: Rh1h2 → R de Rey; h1 es la posición donde esta el rey; h2 a donde mueves al rey.");
        System.out.print("\n");
    }

    public static void criteriosParaVictoria() {
        System.out.print("\n");
        System.out.println("Para decidir si se ha ganado una partida se tienen en cuenta tres cosas:");
        System.out.println("\t· Si los dos reyes acaban en jaque → Tablas.");
        System.out.println("\t· Si esta un rey en jaque → Gana el color contrario.");
        System.out.println("\t· Se calculará un total de puntos según las piezas de cada color → El color con más puntos gana.");
        System.out.println("Los puntos que recibe cada pieza están asociados a su valor estratégico.");
        System.out.print("\n");
    }

    /**
     * Método para la lógica de añadir las piezas de una partida.
     * <strong>Válida</strong> y <strong>coloca</strong> las piezas en el tablero.
     */
    public static void importar(Scanner sc, Partida partida) {
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
            if (!esValidaSintaxis(listaPiezasBlancas) || !esValidaSintaxis(listaPiezasNegras)) {
                importacionCorrecta = false;
                System.out.println("Se ha encontrado un problema en la importación.");
            }

            // Crea las piezas y se valida.
            if (importacionCorrecta) {
                listaPiezas = crearPiezas(listaPiezasBlancas, listaPiezasNegras);

                partida.crearNuevoTablero();    // Porque en caso de haberse ejecutado, las piezas se mantienen en el tablero.

                // Validación dinámica mientras se añaden las piezas.
                for (Pieza pieza:listaPiezas) {
                    if (!partida.colocar(pieza)) {
                        importacionCorrecta = false;
                        System.out.println("Se ha encontrado un problema en la importación.");
                        break;
                    }
                }

                // Validación posterior a colocar las piezas.
                if (!partida.validarPartida() && importacionCorrecta) {
                    importacionCorrecta = false;
                    System.out.println("Se ha encontrado un problema en la importación.");
                }
            }
        } while(!importacionCorrecta);
    }

    /**
     * Recorta las órdenes pasadas por parámetro y las almacena en un array.
     * @param conjuntoPiezas Un conjunto de piezas dentro de un string en formato "Rg1 Ta2 a2"
     * @return Una array con las posiciones pasadas separadas.
     */
    private static String[] listarPiezas(String conjuntoPiezas) {
        conjuntoPiezas = conjuntoPiezas.trim();
        StringBuilder piezasIterables = new StringBuilder(conjuntoPiezas);
        String[] piezas;

        // Sacar el número de órdenes en el string.
        int cantidadEspacios = 1;
        for (int i = 0; i < conjuntoPiezas.length(); i++) {
            if (conjuntoPiezas.charAt(i) == ' ') cantidadEspacios++;
        }

        piezas = new String[cantidadEspacios];

        // Añadir las órdenes al array.
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
     * Válida que la sintaxis de la orden sea correcta.
     * @param listaPiezas Array con las órdenes a validar.
     * @return <code>True</code> si la sintaxis es correcta
     * <code>False</code> si la sintaxis <strong>NO</strong> es correcta.
     */
    private static boolean esValidaSintaxis(String[] listaPiezas) {
        char[] inicialesValidas = {'R', 'D', 'T', 'A', 'C'};

        for (String pieza:listaPiezas){
            if (pieza.length() > 3 || pieza.length() < 2) return false;
            boolean esInicialValida;

            if (pieza.length() == 3) {
                esInicialValida = false;

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
     * Crea un array con las piezas que se ordenan en los arrays pasador por parámetro.
     * @param blancas Array con las órdenes de colocación de las piezas blancas.
     * @param negras Array con las órdenes de colocación de las piezas negras.
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

    /**
     * Válida que la sintaxis introducida por terminal tenga un determinado formato.
     * Posteriormente, una vez localizada una sintaxis válida devuelve un array de
     * tipo <code>Pieza</code> con la cantidad de elementos pasados.
     * @param sc Objeto <code>Scanner</code>
     * @param color Color del turno al que toca mover.<code></code>
     * @return Array de tipo <code>Pieza</code> con una órden de movimiento.
     */
    public static Pieza[] validarFormatoSAN(Scanner sc, Pieza.Color color) {
        Pieza[] movimiento = null;
        String movUsu;
        Pieza.Tipo tipoPieza;
        boolean esSintaxisValida = false;
        char[] inicialesValidas = {'R', 'D', 'T', 'A', 'C'};
        int fila;

        do {
            System.out.print("Inserta un movimiento: ");
            movUsu = sc.nextLine();

            if (movUsu.isEmpty()) continue;

            // g1g2 || Tg1g2 || g1 || Tg1
            switch (movUsu.length()) {
                case 2 -> {
                    esSintaxisValida = Character.isLowerCase(movUsu.charAt(0))
                            && Character.isDigit(movUsu.charAt(1));

                    if (esSintaxisValida) {
                        movimiento = new Pieza[1];
                        fila = Integer.parseInt(Character.toString(movUsu.charAt(1)));
                        movimiento[0] = new Pieza(PEON, color, new Posicion(movUsu.charAt(0), fila));
                    }
                }
                case 3 -> {
                    if (Character.isLowerCase(movUsu.charAt(1)) && Character.isDigit(movUsu.charAt(2))) {
                        for (char inicial:inicialesValidas) {
                            if (inicial == movUsu.charAt(0)) {
                                esSintaxisValida = true;
                                break;
                            }
                        }
                    }

                    if (esSintaxisValida) {
                        movimiento = new Pieza[1];

                        tipoPieza = switch (movUsu.charAt(0)) {
                            case 'C' -> CABALLO;
                            case 'T' -> TORRE;
                            case 'A' -> ALFIL;
                            case 'D' -> DAMA;
                            case 'R' -> REY;
                            default -> null;
                        };

                        fila = Integer.parseInt(Character.toString(movUsu.charAt(2)));
                        movimiento[0] = new Pieza(tipoPieza, color, new Posicion(movUsu.charAt(1), fila));
                    }
                }
                case 4 -> {
                    esSintaxisValida = Character.isLowerCase(movUsu.charAt(0))
                            && Character.isDigit(movUsu.charAt(1))
                            && Character.isLowerCase(movUsu.charAt(2))
                            && Character.isDigit(movUsu.charAt(3));

                    if (esSintaxisValida) {
                        movimiento = new Pieza[2];

                        fila = Integer.parseInt(Character.toString(movUsu.charAt(1)));
                        movimiento[0] = new Pieza(PEON, color, new Posicion(movUsu.charAt(0), fila));
                        fila = Integer.parseInt(Character.toString(movUsu.charAt(3)));
                        movimiento[1] = new Pieza(PEON, color, new Posicion(movUsu.charAt(2), fila));
                    }
                }
                case 5 -> {
                    if (
                            Character.isLowerCase(movUsu.charAt(1))
                            && Character.isDigit(movUsu.charAt(2))
                            && Character.isLowerCase(movUsu.charAt(3))
                            && Character.isDigit(movUsu.charAt(4))
                    ){
                        for (char inicial:inicialesValidas) {
                            if (inicial == movUsu.charAt(0)) {
                                esSintaxisValida = true;
                                break;
                            }
                        }
                    }

                    if (esSintaxisValida) {
                        movimiento = new Pieza[2];

                        tipoPieza = switch (movUsu.charAt(0)) {
                            case 'C' -> CABALLO;
                            case 'T' -> TORRE;
                            case 'A' -> ALFIL;
                            case 'D' -> DAMA;
                            case 'R' -> REY;
                            default -> null;
                        };

                        fila = Integer.parseInt(Character.toString(movUsu.charAt(2)));
                        movimiento[0] = new Pieza(tipoPieza, color, new Posicion(movUsu.charAt(1), fila));
                        fila = Integer.parseInt(Character.toString(movUsu.charAt(4)));
                        movimiento[1] = new Pieza(tipoPieza, color, new Posicion(movUsu.charAt(3), fila));
                    }
                }
                default -> esSintaxisValida = false;
            }

            if (!esSintaxisValida) System.out.println("Has cometido un error al insertar el movimiento.");
        } while (!esSintaxisValida);

        return movimiento;
    }
}
