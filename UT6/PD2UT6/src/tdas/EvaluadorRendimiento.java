package tdas;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EvaluadorRendimiento {

    private static final int TAMANIO_TABLA = 1000;
    private static final int REPETICIONES = 100;

    public static void main(String[] args) {
        evaluarRendimiento();
    }

    public static void evaluarRendimiento() {
        List<String> resultados = new ArrayList<>();
        resultados.add("Factor de carga %,Prom. Comparaciones Inserción,Prom. Comp. Búsqueda exitosa,Prom. Comp. Búsqueda sin éxito");

        int[] factoresCarga = generarFactoresCarga();

        for (int factor : factoresCarga) {
            double promedioInsercion = evaluarInsercion(factor);
            double promedioBusquedaExitosa = evaluarBusquedaExitosa(factor);
            double promedioBusquedaSinExito = evaluarBusquedaSinExito(factor);

            String linea = factor + "," +
                    String.format("%.2f", promedioInsercion) + "," +
                    String.format("%.2f", promedioBusquedaExitosa) + "," +
                    String.format("%.2f", promedioBusquedaSinExito);

            resultados.add(linea);
            System.out.println("Factor: " + factor + "% - Inserción: " +
                    String.format("%.2f", promedioInsercion) +
                    " - Búsqueda exitosa: " + String.format("%.2f", promedioBusquedaExitosa) +
                    " - Búsqueda sin éxito: " + String.format("%.2f", promedioBusquedaSinExito));
        }

        escribirCSV(resultados);
    }

    private static int[] generarFactoresCarga() {
        List<Integer> factores = new ArrayList<>();

        for (int i = 70; i <= 90; i += 5) {
            factores.add(i);
        }

        for (int i = 91; i <= 99; i++) {
            factores.add(i);
        }

        return factores.stream().mapToInt(i -> i).toArray();
    }

    private static double evaluarInsercion(int factorCarga) {
        double totalComparaciones = 0;
        int elementos = (TAMANIO_TABLA * factorCarga) / 100;

        for (int rep = 0; rep < REPETICIONES; rep++) {
            THash<Integer, String> tabla = new THash<>(TAMANIO_TABLA);
            List<Integer> claves = generarClaves(elementos);

            int comparacionesRep = 0;
            for (Integer clave : claves) {
                int comp = tabla.insertar(clave, "valor" + clave);
                if (comp > 0) {
                    comparacionesRep += comp;
                }
            }

            totalComparaciones += (double) comparacionesRep / elementos;
        }

        return totalComparaciones / REPETICIONES;
    }

    private static double evaluarBusquedaExitosa(int factorCarga) {
        double totalComparaciones = 0;
        int elementos = (TAMANIO_TABLA * factorCarga) / 100;

        for (int rep = 0; rep < REPETICIONES; rep++) {
            THash<Integer, String> tabla = new THash<>(TAMANIO_TABLA);
            List<Integer> claves = generarClaves(elementos);

            for (Integer clave : claves) {
                tabla.insertar(clave, "valor" + clave);
            }

            Collections.shuffle(claves);
            int comparacionesRep = 0;

            for (Integer clave : claves) {
                comparacionesRep += tabla.buscar(clave);
            }

            totalComparaciones += (double) comparacionesRep / elementos;
        }

        return totalComparaciones / REPETICIONES;
    }

    private static double evaluarBusquedaSinExito(int factorCarga) {
        double totalComparaciones = 0;
        int elementos = (TAMANIO_TABLA * factorCarga) / 100;

        for (int rep = 0; rep < REPETICIONES; rep++) {
            THash<Integer, String> tabla = new THash<>(TAMANIO_TABLA);
            List<Integer> claves = generarClaves(elementos);

            for (Integer clave : claves) {
                tabla.insertar(clave, "valor" + clave);
            }

            List<Integer> clavesNoExistentes = generarClavesNoExistentes(claves, elementos);
            int comparacionesRep = 0;

            for (Integer clave : clavesNoExistentes) {
                comparacionesRep += tabla.buscar(clave);
            }

            totalComparaciones += (double) comparacionesRep / elementos;
        }

        return totalComparaciones / REPETICIONES;
    }

    private static List<Integer> generarClaves(int cantidad) {
        List<Integer> claves = new ArrayList<>();
        Random random = new Random();

        while (claves.size() < cantidad) {
            int clave = random.nextInt(Integer.MAX_VALUE);
            if (!claves.contains(clave)) {
                claves.add(clave);
            }
        }

        return claves;
    }

    private static List<Integer> generarClavesNoExistentes(List<Integer> clavesExistentes, int cantidad) {
        List<Integer> clavesNoExistentes = new ArrayList<>();
        Random random = new Random();

        while (clavesNoExistentes.size() < cantidad) {
            int clave = random.nextInt(Integer.MAX_VALUE);
            if (!clavesExistentes.contains(clave) && !clavesNoExistentes.contains(clave)) {
                clavesNoExistentes.add(clave);
            }
        }

        return clavesNoExistentes;
    }

    private static void escribirCSV(List<String> resultados) {
        try (FileWriter writer = new FileWriter("UT6/PD2UT6/rendimiento_hash.csv")) {
            for (String linea : resultados) {
                writer.write(linea + "\n");
            }
            System.out.println("Resultados guardados en rendimiento_hash.csv");
        } catch (IOException e) {
            System.err.println("Error al escribir archivo: " + e.getMessage());
        }
    }
}
