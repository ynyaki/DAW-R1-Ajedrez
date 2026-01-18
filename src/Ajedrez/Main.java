import java.util.Scanner;

public abstract class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu.ejecutar(sc);
        sc.close();
    }
}