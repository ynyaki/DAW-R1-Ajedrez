public class Tablero {

//    private final Pieza[][] tablero;
//
//    public Tablero(Pieza[][] tablero) {
//        this.tablero = tablero;
//    }
//
//    public Pieza[][] getTablero() {
//        return tablero;
//    }

    private Pieza[][] tablero = new Pieza[8][8];

    // Colores
    public static final String NEGRO = "\u001B[40m";
    public static final String ROJO = "\u001B[41m";
    public static final String VERDE = "\u001B[42m";
    public static final String AMARILLO = "\u001B[43m";
    public static final String AZUL = "\u001B[44m";
    public static final String CIAN = "\u001B[46m";
    public static final String BLANCO = "\u001B[47m";
    public static final String RESET = "\u001B[0m";

    public Tablero(){

    }

    public void print() {
        // TODO Recoger el simbolo de la pieza.
        String simbolo = "   ";
        for (int i = tablero.length - 1; i >= 0; i--) {
            System.out.print(i + 1 + "┃ "); // Para la parte de los números

            if (i % 2 == 0) {   // Las que deberían empezar por negro.
                colorearFila(tablero, i, NEGRO, BLANCO, simbolo);
            } else {    // Las que deberían empezar por blanco.
                colorearFila(tablero, i, BLANCO, NEGRO, simbolo);
            }
            System.out.print("\n");
        }

        // Para las letras
        System.out.print(" ┗━");
        for (int i = 0; i < tablero[0].length * 3; i++) {
            System.out.print("━");
        }
        System.out.print("\n");
        System.out.print("   ");
        char letra = 'A';
        for (int i = 0; i < tablero[0].length; i++) {
            System.out.print(" " + letra + " ");
            letra = (char) (letra + 1);
        }
    }

    private void colorearFila(Pieza[][] tablero, int filaIterar, String colorInicio, String colorSiguiente, String simbolo){
        for (int i = 0; i < tablero[filaIterar].length; i++) {
            if (i % 2 == 0) {
                System.out.print(colorInicio + simbolo + RESET);
            } else {
                System.out.print(colorSiguiente + simbolo + RESET);
            }
        }
    }
}