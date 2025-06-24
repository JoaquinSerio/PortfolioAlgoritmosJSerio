import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ContadorFrecuenciaPalabras {

    public static void main(String[] args) {
        String nombreArchivo = "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT6\\PD4UT6\\src\\libro.txt";

        Map<String, Integer> frecuencias = contarPalabras(nombreArchivo);

        if (frecuencias != null && !frecuencias.isEmpty()) {
            List<Map.Entry<String, Integer>> top10 = obtenerTop10(frecuencias);
            mostrarResultados(top10);
            generarGrafico(top10);
        } else {
            System.out.println("No se pudieron procesar las palabras del archivo.");
        }
    }

    public static Map<String, Integer> contarPalabras(String nombreArchivo) {
        Map<String, Integer> frecuencias = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] palabras = linea.toLowerCase()
                        .replaceAll("[^a-záéíóúñü\\s]", "")
                        .split("\\s+");

                for (String palabra : palabras) {
                    if (!palabra.trim().isEmpty()) {
                        frecuencias.put(palabra, frecuencias.getOrDefault(palabra, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return null;
        }

        return frecuencias;
    }

    public static List<Map.Entry<String, Integer>> obtenerTop10(Map<String, Integer> frecuencias) {
        return frecuencias.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    public static void mostrarResultados(List<Map.Entry<String, Integer>> top10) {
        System.out.println("TOP 10 PALABRAS MÁS FRECUENTES:");
        System.out.println("================================");

        int posicion = 1;
        for (Map.Entry<String, Integer> entrada : top10) {
            System.out.printf("%2d. %-15s: %d ocurrencias%n",
                    posicion++, entrada.getKey(), entrada.getValue());
        }
    }

    public static void generarGrafico(List<Map.Entry<String, Integer>> top10) {
        System.out.println("\nGRÁFICO DE BARRAS:");
        System.out.println("==================");

        int maxFrecuencia = top10.get(0).getValue();
        int anchoMaximo = 50;

        for (Map.Entry<String, Integer> entrada : top10) {
            String palabra = entrada.getKey();
            int frecuencia = entrada.getValue();
            int anchoBarra = (int) ((double) frecuencia / maxFrecuencia * anchoMaximo);

            System.out.printf("%-15s |", palabra);
            for (int i = 0; i < anchoBarra; i++) {
                System.out.print("█");
            }
            System.out.printf(" %d%n", frecuencia);
        }
    }
}


//Utilizamos HashMap ya que es la estructura más eficiente para este problema:
//
//Operaciones O(1) para inserción y búsqueda de claves
//Permite usar palabras como claves y frecuencias como valores
//getOrDefault() simplifica el conteo de ocurrencias
//Manejo automático de colisiones y redimensionamiento