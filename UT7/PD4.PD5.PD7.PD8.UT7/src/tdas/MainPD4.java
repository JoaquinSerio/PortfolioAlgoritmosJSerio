package tdas;

import java.util.Scanner;

public class MainPD4 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TGrafoDirigido grafo = UtilGrafos.cargarGrafo(
                "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT7\\PD4.PD5.PD7.PD8.UT7\\src\\archivos\\aeropuertos_2.txt",
                "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT7\\PD4.PD5.PD7.PD8.UT7\\src\\archivos\\conexiones_2.txt",
                true,
                TGrafoDirigido.class
        );

        if (grafo == null) {
            System.out.println("Error: No se pudo cargar el grafo.");
            return;
        }

        System.out.println("Aeropuertos disponibles:");
        Object[] aeropuertos = grafo.getEtiquetasOrdenado();
        for (Object aeropuerto : aeropuertos) {
            System.out.println("- " + aeropuerto);
        }

        boolean continuar = true;
        while (continuar) {
            System.out.print("\nAeropuerto de origen: ");
            String origen = scanner.nextLine().trim();

            System.out.print("Aeropuerto de destino: ");
            String destino = scanner.nextLine().trim();

            if (!grafo.existeVertice(origen) || !grafo.existeVertice(destino)) {
                System.out.println("Aeropuerto no válido.");
                continue;
            }

            grafo.desvisitarVertices();
            TCaminos caminos = grafo.todosLosCaminos(origen, destino);

            if (caminos == null || caminos.getCaminos().isEmpty()) {
                System.out.println("No hay conexiones entre " + origen + " y " + destino);
            } else {
                System.out.println("\nConexiones encontradas:");
                for (TCamino camino : caminos.getCaminos()) {
                    System.out.println(camino.imprimirEtiquetas());
                }
            }

            System.out.print("\n¿Continuar? (s/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            continuar = respuesta.equals("s");
        }

        scanner.close();
    }
}
