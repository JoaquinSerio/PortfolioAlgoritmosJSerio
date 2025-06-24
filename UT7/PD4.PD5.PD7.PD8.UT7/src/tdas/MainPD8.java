package tdas;

import java.util.*;

public class MainPD8 {
    public static void main(String[] args) {
        Collection<IVertice> vertices = new ArrayList<>();
        vertices.add(new TVertice("A"));
        vertices.add(new TVertice("B"));
        vertices.add(new TVertice("C"));
        vertices.add(new TVertice("D"));

        Collection<IArista> aristas = new ArrayList<>();
        aristas.add(new TArista("A", "B", 1.0));
        aristas.add(new TArista("A", "C", 1.0));
        aristas.add(new TArista("B", "D", 1.0));
        aristas.add(new TArista("C", "B", 1.0));
        aristas.add(new TArista("D", "C", 1.0));

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        List<IArista> arcosArbol = new ArrayList<>();
        List<IArista> arcosRetroceso = new ArrayList<>();
        List<IArista> arcosAvance = new ArrayList<>();
        List<IArista> arcosCruzados = new ArrayList<>();

        grafo.clasificarArcos("A", arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);

        System.out.println("Arcos de árbol:");
        for (IArista arco : arcosArbol) {
            System.out.println(arco);
        }

        System.out.println("\nArcos de retroceso:");
        for (IArista arco : arcosRetroceso) {
            System.out.println(arco);
        }

        System.out.println("\nArcos de avance:");
        for (IArista arco : arcosAvance) {
            System.out.println(arco);
        }

        System.out.println("\nArcos cruzados:");
        for (IArista arco : arcosCruzados) {
            System.out.println(arco);
        }
    }
}