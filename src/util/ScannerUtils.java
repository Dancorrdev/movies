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
        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    public static double catchDecimal ( String mensaje){
        System.out.println(mensaje + ": ");
        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }
}
