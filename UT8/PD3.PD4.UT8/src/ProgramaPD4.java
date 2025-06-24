import java.util.Collection;
import java.util.LinkedList;

public class ProgramaPD4 {

    public static void main(String[] args) {
        ejercicio1();
        ejercicio2();
    }

    public static void ejercicio1() {
        System.out.println("EJERCICIO 1 - UcuRedes");

        Collection<TVertice> vertices = new LinkedList<>();
        vertices.add(new TVertice("CC1"));
        vertices.add(new TVertice("CC2"));
        vertices.add(new TVertice("CC3"));
        vertices.add(new TVertice("CC4"));
        vertices.add(new TVertice("CC5"));
        vertices.add(new TVertice("CC6"));

        Collection<TArista> aristas = new LinkedList<>();
        aristas.add(new TArista("CC1", "CC2", 5));
        aristas.add(new TArista("CC1", "CC3", 7));
        aristas.add(new TArista("CC1", "CC4", 3));
        aristas.add(new TArista("CC1", "CC5", 9));
        aristas.add(new TArista("CC1", "CC6", 4));
        aristas.add(new TArista("CC2", "CC3", 3));
        aristas.add(new TArista("CC2", "CC4", 5));
        aristas.add(new TArista("CC2", "CC5", 7));
        aristas.add(new TArista("CC2", "CC6", 8));
        aristas.add(new TArista("CC3", "CC4", 4));
        aristas.add(new TArista("CC3", "CC5", 5));
        aristas.add(new TArista("CC3", "CC6", 7));
        aristas.add(new TArista("CC4", "CC5", 9));
        aristas.add(new TArista("CC4", "CC6", 3));
        aristas.add(new TArista("CC5", "CC6", 6));

        TGrafoNoDirigido grafoUcuRedes = new TGrafoNoDirigido(vertices, aristas);

        TAristas mst = grafoUcuRedes.mejorRedElectrica();

        double costoTotal = 0;
        System.out.println("Conexiones de fibra óptica (Árbol Abarcador Mínimo):");
        for (TArista arista : mst) {
            costoTotal += arista.getCosto();
            System.out.println(arista.getEtiquetaOrigen() + " - " +
                    arista.getEtiquetaDestino() + " : " + arista.getCosto());
        }
        System.out.println("Costo total: " + costoTotal);
        System.out.println();
    }

    public static void ejercicio2() {
        System.out.println("EJERCICIO 2 - Conectividad");

        Collection<TVertice> vertices = new LinkedList<>();
        vertices.add(new TVertice("A"));
        vertices.add(new TVertice("B"));
        vertices.add(new TVertice("C"));
        vertices.add(new TVertice("D"));
        vertices.add(new TVertice("E"));

        Collection<TArista> aristas = new LinkedList<>();
        aristas.add(new TArista("A", "B", 1));
        aristas.add(new TArista("B", "C", 1));
        aristas.add(new TArista("D", "E", 1));

        TGrafoNoDirigido grafo = new TGrafoNoDirigido(vertices, aristas);

        TVertice verticeA = grafo.buscarVertice("A");
        TVertice verticeC = grafo.buscarVertice("C");
        TVertice verticeD = grafo.buscarVertice("D");
        TVertice verticeE = grafo.buscarVertice("E");

        System.out.println("¿A está conectado con C? " + grafo.conectados(verticeA, verticeC));
        System.out.println("¿A está conectado con D? " + grafo.conectados(verticeA, verticeD));
        System.out.println("¿D está conectado con E? " + grafo.conectados(verticeD, verticeE));
    }
}