import java.util.List;
import java.util.Scanner;

public class AutocompletarApp {

    private TTrieHashMap diccionario;
    private Scanner scanner;

    public AutocompletarApp() {
        diccionario = new TTrieHashMap();
        scanner = new Scanner(System.in);
        inicializarDiccionario();
    }

    private void inicializarDiccionario() {
        String[] palabras = {
                "algoritmo", "aplicacion", "arbol", "buscar", "caracter", "codigo",
                "datos", "diccionario", "estructura", "hashmap", "java", "lista",
                "mapa", "nodo", "objeto", "palabra", "programa", "recurso",
                "sistema", "trie", "usuario", "valor", "vector"
        };

        for (String palabra : palabras) {
            diccionario.insertar(palabra);
        }
    }

    public void ejecutar() {
        System.out.println("=== APLICACIÓN DE AUTOCOMPLETAR ===");
        System.out.println("Escribe un prefijo para ver sugerencias (o 'salir' para terminar):");

        while (true) {
            System.out.print("\nPrefijo: ");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("salir")) {
                break;
            }

            if (entrada.isEmpty()) {
                continue;
            }

            List<String> sugerencias = diccionario.predecir(entrada);

            if (sugerencias.isEmpty()) {
                System.out.println("No se encontraron sugerencias para '" + entrada + "'");
            } else {
                System.out.println("Sugerencias para '" + entrada + "':");
                for (int i = 0; i < Math.min(sugerencias.size(), 5); i++) {
                    System.out.println((i + 1) + ". " + sugerencias.get(i));
                }
            }
        }

        System.out.println("¡Hasta luego!");
    }

    public static void main(String[] args) {
        new AutocompletarApp().ejecutar();
    }
}