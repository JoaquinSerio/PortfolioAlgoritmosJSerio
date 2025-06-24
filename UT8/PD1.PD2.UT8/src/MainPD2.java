import java.util.*;

public class MainPD2 {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO 1: IMPLEMENTACIÓN DE KRUSKAL ===\n");

        System.out.println("--- GRAFO 1:  ---");
        Collection<TVertice> vertices1 = new LinkedList<>();
        Collection<TArista> aristas1 = new LinkedList<>();

        vertices1.add(new TVertice("A"));
        vertices1.add(new TVertice("B"));
        vertices1.add(new TVertice("C"));
        vertices1.add(new TVertice("D"));

        aristas1.add(new TArista("A", "B", 1));
        aristas1.add(new TArista("A", "C", 4));
        aristas1.add(new TArista("B", "C", 2));
        aristas1.add(new TArista("B", "D", 5));
        aristas1.add(new TArista("C", "D", 3));

        TGrafoNoDirigido grafo1 = new TGrafoNoDirigido(vertices1, aristas1);

        System.out.println("Aristas originales: " + grafo1.getLasAristas().imprimirEtiquetas());

        TGrafoNoDirigido aamKruskal1 = grafo1.Kruskal();
        TGrafoNoDirigido aamPrim1 = grafo1.Prim();

        System.out.println("AAM Kruskal: " + aamKruskal1.getLasAristas().imprimirEtiquetas());
        System.out.println("AAM Prim: " + aamPrim1.getLasAristas().imprimirEtiquetas());
        System.out.println("Costo AAM Kruskal: " + calcularCostoTotal(aamKruskal1.getLasAristas()));
        System.out.println("Costo AAM Prim: " + calcularCostoTotal(aamPrim1.getLasAristas()));

        System.out.println("\n--- GRAFO 2: ---");
        Collection<TVertice> vertices2 = new LinkedList<>();
        Collection<TArista> aristas2 = new LinkedList<>();

        vertices2.add(new TVertice("A"));
        vertices2.add(new TVertice("B"));
        vertices2.add(new TVertice("C"));
        vertices2.add(new TVertice("D"));
        vertices2.add(new TVertice("E"));
        vertices2.add(new TVertice("F"));

        aristas2.add(new TArista("A", "B", 4));
        aristas2.add(new TArista("A", "C", 2));
        aristas2.add(new TArista("B", "C", 1));
        aristas2.add(new TArista("B", "D", 5));
        aristas2.add(new TArista("C", "D", 8));
        aristas2.add(new TArista("C", "E", 10));
        aristas2.add(new TArista("D", "E", 2));
        aristas2.add(new TArista("D", "F", 6));
        aristas2.add(new TArista("E", "F", 3));

        TGrafoNoDirigido grafo2 = new TGrafoNoDirigido(vertices2, aristas2);

        System.out.println("Aristas originales: " + grafo2.getLasAristas().imprimirEtiquetas());

        TGrafoNoDirigido aamKruskal2 = grafo2.Kruskal();
        TGrafoNoDirigido aamPrim2 = grafo2.Prim();

        System.out.println("AAM Kruskal: " + aamKruskal2.getLasAristas().imprimirEtiquetas());
        System.out.println("AAM Prim: " + aamPrim2.getLasAristas().imprimirEtiquetas());
        System.out.println("Costo AAM Kruskal: " + calcularCostoTotal(aamKruskal2.getLasAristas()));
        System.out.println("Costo AAM Prim: " + calcularCostoTotal(aamPrim2.getLasAristas()));

        System.out.println("\n--- GRAFO 3: ---");
        Collection<TVertice> vertices3 = new LinkedList<>();
        Collection<TArista> aristas3 = new LinkedList<>();

        vertices3.add(new TVertice("1"));
        vertices3.add(new TVertice("2"));
        vertices3.add(new TVertice("3"));
        vertices3.add(new TVertice("4"));
        vertices3.add(new TVertice("5"));
        vertices3.add(new TVertice("6"));
        vertices3.add(new TVertice("7"));

        aristas3.add(new TArista("1", "2", 7));
        aristas3.add(new TArista("1", "4", 5));
        aristas3.add(new TArista("2", "3", 8));
        aristas3.add(new TArista("2", "4", 9));
        aristas3.add(new TArista("2", "5", 7));
        aristas3.add(new TArista("3", "5", 5));
        aristas3.add(new TArista("4", "5", 15));
        aristas3.add(new TArista("4", "6", 6));
        aristas3.add(new TArista("5", "6", 8));
        aristas3.add(new TArista("5", "7", 9));
        aristas3.add(new TArista("6", "7", 11));

        TGrafoNoDirigido grafo3 = new TGrafoNoDirigido(vertices3, aristas3);

        System.out.println("Aristas originales: " + grafo3.getLasAristas().imprimirEtiquetas());

        TGrafoNoDirigido aamKruskal3 = grafo3.Kruskal();
        TGrafoNoDirigido aamPrim3 = grafo3.Prim();

        System.out.println("AAM Kruskal: " + aamKruskal3.getLasAristas().imprimirEtiquetas());
        System.out.println("AAM Prim: " + aamPrim3.getLasAristas().imprimirEtiquetas());
        System.out.println("Costo AAM Kruskal: " + calcularCostoTotal(aamKruskal3.getLasAristas()));
        System.out.println("Costo AAM Prim: " + calcularCostoTotal(aamPrim3.getLasAristas()));

    }

    private static double calcularCostoTotal(TAristas aristas) {
        double total = 0;
        for (TArista arista : aristas) {
            total += arista.getCosto();
        }
        return total;
    }

    }
