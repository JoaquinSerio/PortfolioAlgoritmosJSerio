
import java.util.List;

public class AplicacionPruebas {

    public static void main(String[] args) {
        // Prueba de autocompletar
        System.out.println("=== PRUEBA DE AUTOCOMPLETAR ===");
        pruebaAutocompletar();

        System.out.println("\n=== PRUEBA DE BÚSQUEDA DE PATRONES ===");
        pruebaBusquedaPatrones();
    }

    private static void pruebaAutocompletar() {
        TTrieHashMap trie = new TTrieHashMap();

        // Insertar palabras
        String[] palabras = {"casa", "auto", "carta", "cartón", "perro", "Formula", "personal"};
        for (String palabra : palabras) {
            trie.insertar(palabra);
        }

        System.out.println("Palabras insertadas:");
        trie.imprimir();

        // Probar autocompletar
        System.out.println("\nAutocompletar 'car':");
        List<String> sugerencias = trie.predecir("car");
        for (String sugerencia : sugerencias) {
            System.out.println("- " + sugerencia);
        }

        System.out.println("\nAutocompletar 'per':");
        sugerencias = trie.predecir("per");
        for (String sugerencia : sugerencias) {
            System.out.println("- " + sugerencia);
        }

        // Probar búsqueda
        System.out.println("\nBuscar 'casa': " + trie.buscar("casa"));
        System.out.println("Buscar 'gato': " + trie.buscar("gato"));
    }

    private static void pruebaBusquedaPatrones() {
        TTrieHashMap trie = new TTrieHashMap();

        String texto = "En casa hay un gato. El gato come en casa todos los días.";
        System.out.println("Texto: " + texto);

        // Buscar patrones
        String[] patrones = {"casa", "gato", "en", "el"};

        for (String patron : patrones) {
            trie.insertarPatron(texto, patron);
        }

        System.out.println("\nPatrones encontrados:");
        trie.imprimir();

        // Mostrar posiciones específicas
        for (String patron : patrones) {
            List<Integer> posiciones = trie.buscarPatron(patron);
            if (!posiciones.isEmpty()) {
                System.out.println("'" + patron + "' encontrado en posiciones: " + posiciones);
            }
        }
    }
}