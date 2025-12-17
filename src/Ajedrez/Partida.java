public class Partida {

    //  DELETE Pruebas de creación y muestra de tablero
    public void empezar() {
        Tablero t = crearTableroClasico();
        System.out.println(t);
    }

    public boolean esColocacionValida(Pieza p) {
        boolean esValida = true;
        // TODO Añadir validaciones
        return esValida;
    }

    private static Tablero crearTableroClasico() {
        Tablero t = new Tablero(8, 8);

        t.colocar(new Pieza(Pieza.Tipo.TORRE, Pieza.Color.BLANCO, new Posicion(1, 1)));
        t.colocar(new Pieza(Pieza.Tipo.TORRE, Pieza.Color.BLANCO, new Posicion(8, 1)));
        t.colocar(new Pieza(Pieza.Tipo.TORRE, Pieza.Color.NEGRO, new Posicion(1, 8)));
        t.colocar(new Pieza(Pieza.Tipo.TORRE, Pieza.Color.NEGRO, new Posicion(8, 8)));

        t.colocar(new Pieza(Pieza.Tipo.CABALLO, Pieza.Color.BLANCO, new Posicion(2, 1)));
        t.colocar(new Pieza(Pieza.Tipo.CABALLO, Pieza.Color.BLANCO, new Posicion(7, 1)));
        t.colocar(new Pieza(Pieza.Tipo.CABALLO, Pieza.Color.NEGRO, new Posicion(2, 8)));
        t.colocar(new Pieza(Pieza.Tipo.CABALLO, Pieza.Color.NEGRO, new Posicion(7, 8)));

        t.colocar(new Pieza(Pieza.Tipo.ALFIL, Pieza.Color.BLANCO, new Posicion(3, 1)));
        t.colocar(new Pieza(Pieza.Tipo.ALFIL, Pieza.Color.BLANCO, new Posicion(6, 1)));
        t.colocar(new Pieza(Pieza.Tipo.ALFIL, Pieza.Color.NEGRO, new Posicion(3, 8)));
        t.colocar(new Pieza(Pieza.Tipo.ALFIL, Pieza.Color.NEGRO, new Posicion(6, 8)));

        t.colocar(new Pieza(Pieza.Tipo.DAMA, Pieza.Color.BLANCO, new Posicion(4, 1)));
        t.colocar(new Pieza(Pieza.Tipo.DAMA, Pieza.Color.NEGRO, new Posicion(4, 8)));

        t.colocar(new Pieza(Pieza.Tipo.REY, Pieza.Color.BLANCO, new Posicion(5, 1)));
        t.colocar(new Pieza(Pieza.Tipo.REY, Pieza.Color.NEGRO, new Posicion(5, 8)));

        for(int i = 1; i <= 8; i++)
            t.colocar(new Pieza(Pieza.Tipo.PEON, Pieza.Color.BLANCO, new Posicion(i, 2)));

        for(int i = 1; i <= 8; i++)
            t.colocar(new Pieza(Pieza.Tipo.PEON, Pieza.Color.NEGRO, new Posicion(i, 7)));

        return t;
    }
}
