package util;

import java.util.Scanner;

public class ScannerUtils {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static String catchText (String mensaje) {
        System.out.println(mensaje + ": ");
        return SCANNER.nextLine();
    }

    public static int catchNumber ( String mensaje){
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextInt()) {
            System.out.println("Por favor ingrese un número válido." + mensaje + ": ");
            SCANNER.next();
        }
        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    public static double catchDecimal ( String mensaje){
        System.out.println(mensaje + ": ");
        while (!SCANNER.hasNextDouble()) {
            System.out.println("Por favor ingrese un número decimal válido." + mensaje + ": ");
            SCANNER.next();
        }
        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }
}
