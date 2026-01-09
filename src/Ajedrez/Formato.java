import java.util.Scanner;

public abstract class Formato {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            String pos = sc.nextLine();
            validarP(pos);
            sc.close();
        }
        catch (StringIndexOutOfBoundsException e){
            if(e.getMessage()==null)
                System.out.println("Error!");
            else
                System.out.println(e.getMessage());
        }
        catch (NumberFormatException e) {
            System.out.println("Error de formato!");
        }
    }

    /**
     * formato: pieza(Caracter)+col(caracter)+fil(numero) <br>
     * formato2 (para colocar el peon): col(caracter)+fil(numero) si y solo si no existe "KQRBH" en la posicion 0
     * @param pos
     * @return pieza, x, y
     */
    public static void validarP(String pos) {

        String piezasTotal = "RDTAC";
        char pieza = pos.charAt(0);
        int x = 0, y = 0;
        boolean esPeon = true;
        boolean esPiezaVal = false;

        for(int i = 0; i <=piezasTotal.length()-1;i++) {
            if (pieza==piezasTotal.charAt(i)) {
                x = charToNum(pos.charAt(1));
                y = Integer.parseInt(String.valueOf(pos.charAt(2)));
                esPeon = false;
                esPiezaVal = true;
            }
        }
        if (esPeon && pos.length()==2){
            pieza = 'P';
            x = charToNum(pos.charAt(0));
            y = Integer.parseInt(String.valueOf(pos.charAt(1)));
            esPiezaVal = true;
        }
        if(y>8 || y<1 || pos.length()>3) esPiezaVal = false;

        if(!esPiezaVal)
            throw new StringIndexOutOfBoundsException("Error de formato!");
        // Comprobador si funciona (despues se elimina)
        System.out.printf("Se ha colocado un %s en la posicion(%d, %d)", pieza,x,y);
    }

    /**
     * @param col
     * @return <code>num > 0 || num<=8</code>
     */
    public static int charToNum (char col){
        String posiciones = "abcdefgh";
        for(int i = 0; i<=posiciones.length()-1;i++){
            if(col==posiciones.charAt(i)){
                return i+1;
            }
        }
        throw new StringIndexOutOfBoundsException("Error de formato!");
    }
}
