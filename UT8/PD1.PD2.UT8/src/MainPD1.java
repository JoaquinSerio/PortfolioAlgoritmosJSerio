import java.util.*;

public class MainPD1 {
    public static void main(String[] args) {
        // Ejercicio 1: Crear grafo no dirigido simple
        System.out.println("=== EJERCICIO 1: Grafo No Dirigido ===");

        Collection<TVertice> vertices = new LinkedList<>();
        Collection<TArista> aristas = new LinkedList<>();

        // Crear vértices
        vertices.add(new TVertice("A"));
        vertices.add(new TVertice("B"));
        vertices.add(new TVertice("C"));
        vertices.add(new TVertice("D"));

        // Crear aristas
        aristas.add(new TArista("A", "B", 1));
        aristas.add(new TArista("B", "C", 2));
        aristas.add(new TArista("C", "D", 1));
        aristas.add(new TArista("A", "D", 3));

        TGrafoNoDirigido grafo = new TGrafoNoDirigido(vertices, aristas);

        // Probar BPF
        System.out.println("BPF desde A:");
        Collection<TVertice> resultadoBPF = grafo.bpf("A");
        for (TVertice v : resultadoBPF) {
            System.out.print(v.getEtiqueta() + " ");
        }
        System.out.println();

        // Ejercicio 2: Algoritmo de Prim
        System.out.println("\n=== EJERCICIO 2: Algoritmo de Prim ===");

        Collection<TVertice> vertices2 = new LinkedList<>();
        Collection<TArista> aristas2 = new LinkedList<>();

        // Crear un grafo más complejo para Prim
        vertices2.add(new TVertice("A"));
        vertices2.add(new TVertice("B"));
        vertices2.add(new TVertice("C"));
        vertices2.add(new TVertice("D"));
        vertices2.add(new TVertice("E"));

        aristas2.add(new TArista("A", "B", 2));
        aristas2.add(new TArista("A", "C", 3));
        aristas2.add(new TArista("B", "C", 1));
        aristas2.add(new TArista("B", "D", 1));
        aristas2.add(new TArista("C", "E", 4));
        aristas2.add(new TArista("D", "E", 2));

        TGrafoNoDirigido grafoPrim = new TGrafoNoDirigido(vertices2, aristas2);

        TGrafoNoDirigido aam = grafoPrim.Prim();
        System.out.println("Árbol Abarcador Mínimo (Prim):");
        System.out.println(aam.getLasAristas().imprimirEtiquetas());

        // Ejercicio 3: BEA (Búsqueda en Amplitud)
        System.out.println("=== EJERCICIO 3: Búsqueda en Amplitud ===");

        Collection<TVertice> vertices3 = new LinkedList<>();
        Collection<TArista> aristas3 = new LinkedList<>();

        // Crear el grafo del ejercicio 3
        vertices3.add(new TVertice("a"));
        vertices3.add(new TVertice("b"));
        vertices3.add(new TVertice("c"));
        vertices3.add(new TVertice("d"));
        vertices3.add(new TVertice("e"));
        vertices3.add(new TVertice("f"));
        vertices3.add(new TVertice("g"));

        aristas3.add(new TArista("a", "b", 1));
        aristas3.add(new TArista("a", "c", 1));
        aristas3.add(new TArista("b", "d", 1));
        aristas3.add(new TArista("c", "e", 1));
        aristas3.add(new TArista("d", "f", 1));
        aristas3.add(new TArista("e", "f", 1));
        aristas3.add(new TArista("f", "g", 1));

        TGrafoNoDirigido grafoBEA = new TGrafoNoDirigido(vertices3, aristas3);

        System.out.println("BEA desde 'a':");
        Collection<TVertice> resultadoBEA = grafoBEA.bea("a");
        for (TVertice v : resultadoBEA) {
            System.out.print(v.getEtiqueta() + " ");
        }
        System.out.println();

        // Probar conectividad
        System.out.println("¿Es conexo el grafo? " + grafoBEA.esConexo());

        // Probar Kruskal
        System.out.println("\n=== ALGORITMO DE KRUSKAL ===");
        TGrafoNoDirigido aamKruskal = grafoPrim.Kruskal();
        System.out.println("Árbol Abarcador Mínimo (Kruskal):");
        System.out.println(aamKruskal.getLasAristas().imprimirEtiquetas());

        // Probar puntos de articulación
        System.out.println("=== PUNTOS DE ARTICULACIÓN ===");
        LinkedList<TVertice> puntosArt = grafoBEA.puntosArticulacion("a");
        System.out.println("Puntos de articulación:");
        for (TVertice v : puntosArt) {
            System.out.print(v.getEtiqueta() + " ");
        }
        System.out.println();
    }
}