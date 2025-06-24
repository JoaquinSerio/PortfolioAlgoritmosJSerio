import tdas.TArbolTrie;
import tdas.TArbolTrieHashMap;
import utils.ManejadorArchivosGenerico;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        String[] palabrasClave = ManejadorArchivosGenerico.leerArchivo("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT6\\PD1UT6\\src\\archivos\\palabras1.txt");

        System.out.println("=== TRIE CON ARRAY ===");
        TArbolTrie trieArray = new TArbolTrie();
        for (String p : palabrasClave) {
            trieArray.insertar(p);
        }
        trieArray.imprimir();

        System.out.println("\n=== TRIE CON HASHMAP ===");
        TArbolTrieHashMap trieHashMap = new TArbolTrieHashMap();
        for (String p : palabrasClave) {
            trieHashMap.insertar(p);
        }
        trieHashMap.imprimir();

        System.out.println("\n=== PRUEBA DE PREDICCIÓN ===");
        System.out.println("Predicciones con 'ca' (Array):");
        LinkedList<String> prediccionesArray = trieArray.predecir("ca");
        for (String palabra : prediccionesArray) {
            System.out.println(palabra);
        }

        System.out.println("\nPredicciones con 'ca' (HashMap):");
        LinkedList<String> prediccionesHashMap = trieHashMap.predecir("ca");
        for (String palabra : prediccionesHashMap) {
            System.out.println(palabra);
        }

        System.out.println("\n=== COMPARACIÓN DE RENDIMIENTO ===");
        compararRendimiento(trieArray, trieHashMap, palabrasClave);
    }

    private static void compararRendimiento(TArbolTrie trieArray, TArbolTrieHashMap trieHashMap, String[] palabras) {
        int iteraciones = 100000;

        long inicioArray = System.nanoTime();
        for (int i = 0; i < iteraciones; i++) {
            for (String palabra : palabras) {
                trieArray.buscar(palabra);
            }
        }
        long finArray = System.nanoTime();

        long inicioHashMap = System.nanoTime();
        for (int i = 0; i < iteraciones; i++) {
            for (String palabra : palabras) {
                trieHashMap.buscar(palabra);
            }
        }
        long finHashMap = System.nanoTime();

        double tiempoArray = (finArray - inicioArray) / 1_000_000.0;
        double tiempoHashMap = (finHashMap - inicioHashMap) / 1_000_000.0;

        System.out.println("Tiempo Array: " + tiempoArray + " ms");
        System.out.println("Tiempo HashMap: " + tiempoHashMap + " ms");
        System.out.println("Diferencia: " + Math.abs(tiempoArray - tiempoHashMap) + " ms");

        if (tiempoArray < tiempoHashMap) {
            System.out.println("El Trie con Array es más rápido");
        } else {
            System.out.println("El Trie con HashMap es más rápido");
        }

        System.out.println("\n=== ANÁLISIS DE MEMORIA ===");
        Runtime runtime = Runtime.getRuntime();

        runtime.gc();
        long memoriaAntes = runtime.totalMemory() - runtime.freeMemory();

        TArbolTrie nuevoTrieArray = new TArbolTrie();
        for (String palabra : palabras) {
            nuevoTrieArray.insertar(palabra);
        }

        long memoriaArray = runtime.totalMemory() - runtime.freeMemory() - memoriaAntes;

        runtime.gc();
        memoriaAntes = runtime.totalMemory() - runtime.freeMemory();

        TArbolTrieHashMap nuevoTrieHashMap = new TArbolTrieHashMap();
        for (String palabra : palabras) {
            nuevoTrieHashMap.insertar(palabra);
        }

        long memoriaHashMap = runtime.totalMemory() - runtime.freeMemory() - memoriaAntes;

        System.out.println("Memoria aproximada Array: " + memoriaArray + " bytes");
        System.out.println("Memoria aproximada HashMap: " + memoriaHashMap + " bytes");

        if (memoriaArray < memoriaHashMap) {
            System.out.println("El Trie con Array usa menos memoria");
        } else {
            System.out.println("El Trie con HashMap usa menos memoria");
        }
    }
}