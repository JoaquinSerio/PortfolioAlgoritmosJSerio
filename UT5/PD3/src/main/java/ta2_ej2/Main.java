package main.java.ta2_ej2;

import main.java.ta2_ej2.tdas.TArbolTrie;
import main.java.ta2_ej2.utils.ManejadorArchivosGenerico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void indizarLibro(TArbolTrie trie, String archivoLibro) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(archivoLibro));
        String linea;
        int numLinea = 0;
        int pagina = 1;

        while ((linea = br.readLine()) != null) {
            numLinea++;
            if (numLinea > 50) {
                pagina++;
                numLinea = 1;
            }

            String[] palabras = linea.split("\\s+");
            for (String palabra : palabras) {
                String limpia = palabra.replaceAll("[^a-zA-Z]", "").toLowerCase();
                if (!limpia.isEmpty()) {
                    trie.agregarPagina(limpia, pagina);
                }
            }
        }
        br.close();
    }

    public static void main(String[] args) throws IOException {
        TArbolTrie trie = new TArbolTrie();
        Scanner scanner = new Scanner(System.in);

        String archivoIndice = "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT5\\PD3\\src\\main\\java\\ta2_ej2\\archivos\\PalabrasIndice\\PalabrasIndice.txt";
        String[] palabrasIndice = ManejadorArchivosGenerico.leerArchivo(archivoIndice);

        for (String palabra : palabrasIndice) {
            String palabraLimpia = palabra.replaceAll("[^a-zA-Z]", "").toLowerCase();
            if (!palabraLimpia.isEmpty()) {
                trie.insertar(palabraLimpia);
            }
        }

        String archivoLibro = "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT5\\PD3\\src\\main\\java\\ta2_ej2\\archivos\\libro\\libro.txt";
        indizarLibro(trie, archivoLibro);

        System.out.println("=== ÍNDICE COMPLETO ===");
        trie.imprimirIndice();

        System.out.println("\n=== EJERCICIO 2: BÚSQUEDA DE PALABRAS ===");
        System.out.println("Ingrese palabras para buscar (escriba 'salir' para terminar):");

        while (true) {
            System.out.print("Palabra: ");
            String palabra = scanner.nextLine().trim();

            if (palabra.equalsIgnoreCase("salir")) {
                break;
            }

            if (!palabra.isEmpty()) {
                trie.buscar(palabra);
            }
        }

        System.out.println("\n=== EJERCICIO 3: PREDICCIÓN ===");
        System.out.println("Ingrese prefijos para predecir palabras (escriba 'salir' para terminar):");

        while (true) {
            System.out.print("Prefijo: ");
            String prefijo = scanner.nextLine().trim();

            if (prefijo.equalsIgnoreCase("salir")) {
                break;
            }

            if (!prefijo.isEmpty()) {
                LinkedList<String> predicciones = trie.predecir(prefijo);
                System.out.println("Palabras que comienzan con '" + prefijo + "':");
                if (predicciones.isEmpty()) {
                    System.out.println("No se encontraron palabras.");
                } else {
                    for (String palabra : predicciones) {
                        System.out.println("- " + palabra);
                    }
                }
            }
        }


    }
}