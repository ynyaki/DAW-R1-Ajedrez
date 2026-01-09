import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            String pos = sc.nextLine();
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
}
