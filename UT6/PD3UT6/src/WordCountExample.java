import java.util.*;
import java.io.*;

public class WordCountExample {
    public static void main(String[] args) {
        String texto = "java es un lenguaje de programación java es poderoso java es popular";

        Map<String, Integer> contadorPalabras = new HashMap<>();
        Scanner scanner = new Scanner(texto);

        while (scanner.hasNext()) {
            String palabra = scanner.next().toLowerCase();
            contadorPalabras.put(palabra, contadorPalabras.getOrDefault(palabra, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : contadorPalabras.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }
}
