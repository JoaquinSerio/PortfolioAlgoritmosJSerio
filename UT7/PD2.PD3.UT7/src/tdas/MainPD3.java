package tdas;

import java.util.*;

public class MainPD3 {

    public static void main(String[] args) {
        TGrafoDirigido grafo = UtilGrafos.cargarGrafo("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT7\\PD2.PD3.UT7\\src\\archivos\\aeropuertos.txt", "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT7\\PD2.PD3.UT7\\src\\archivos\\conexiones.txt", false, TGrafoDirigido.class);

        if (grafo == null) {
            System.out.println("Error cargando el grafo desde archivos");
            return;
        }

        System.out.println("=== EJERCICIO 1 ===");
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(grafo.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matrizCostos, grafo.getVertices(), "Matriz de Conexiones");

        Double[][] matrizFloyd = grafo.floyd();
        UtilGrafos.imprimirMatrizMejorado(matrizFloyd, grafo.getVertices(), "Matriz Floyd - Costos Minimos");

        System.out.println("Respuestas:");
        System.out.println("1. Montevideo a Rio de Janeiro: " + obtenerCosto(matrizFloyd, grafo, "Montevideo", "Rio de Janeiro"));
        System.out.println("2. Montevideo a Curitiba: " + obtenerCosto(matrizFloyd, grafo, "Montevideo", "Curitiba"));
        System.out.println("3. Centro del grafo (servicios mantenimiento): " + grafo.centroDelGrafo());

        System.out.println("\n=== EJERCICIO 2 ===");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Verificaciones automaticas:");
        System.out.println("Montevideo a Curitiba: " + grafo.existeConexion("Montevideo", "Curitiba"));
        System.out.println("Porto Alegre a Santos: " + grafo.existeConexion("Porto Alegre", "Santos"));

        System.out.print("\nConsultar conectividad interactiva (s/n)? ");
        if (scanner.nextLine().toLowerCase().startsWith("s")) {
            consultarConectividad(grafo, scanner);
        }

        System.out.println("\n=== EJERCICIO 3 ===");
        System.out.println("BPF desde Montevideo:");
        grafo.bpf("Montevideo");

        System.out.println("\nComplexidad BPF: O(V + A) donde V=vertices, A=aristas");

        System.out.println("\nTodos los caminos de Montevideo a Rio de Janeiro:");
        List<List<Comparable>> caminos = grafo.obtenerTodosLosCaminos("Montevideo", "Rio de Janeiro");
        for (int i = 0; i < caminos.size(); i++) {
            System.out.println("Camino " + (i + 1) + ": " + caminos.get(i));
        }

        scanner.close();
    }

    private static double obtenerCosto(Double[][] matriz, TGrafoDirigido grafo, String origen, String destino) {
        Object[] etiquetas = grafo.getEtiquetasOrdenado();
        int indiceOrigen = -1, indiceDestino = -1;

        for (int i = 0; i < etiquetas.length; i++) {
            if (etiquetas[i].equals(origen)) indiceOrigen = i;
            if (etiquetas[i].equals(destino)) indiceDestino = i;
        }

        if (indiceOrigen != -1 && indiceDestino != -1) {
            return matriz[indiceOrigen][indiceDestino];
        }
        return -1;
    }

    private static void consultarConectividad(TGrafoDirigido grafo, Scanner scanner) {
        while (true) {
            System.out.print("Ciudad origen (o 'salir'): ");
            String origen = scanner.nextLine();
            if (origen.equals("salir")) break;

            System.out.print("Ciudad destino: ");
            String destino = scanner.nextLine();

            boolean conectado = grafo.existeConexion(origen, destino);
            System.out.println("Conexion de " + origen + " a " + destino + ": " +
                    (conectado ? "SI existe" : "NO existe"));
        }
    }
}