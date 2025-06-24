import java.util.*;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> cadenas = new ArrayList<>();

        System.out.println("Ingrese cadenas (presione Enter en línea vacía para terminar):");
        String linea;
        while (!(linea = scanner.nextLine()).isEmpty()) {
            cadenas.add(linea);
        }

        cadenas.sort((a, b) -> {
            int comparacionLongitud = Integer.compare(a.length(), b.length());
            return comparacionLongitud != 0 ? comparacionLongitud : a.compareTo(b);
        });

        System.out.println("Cadenas ordenadas:");
        for (String cadena : cadenas) {
            System.out.println(cadena);
        }

        scanner.close();
    }
}
