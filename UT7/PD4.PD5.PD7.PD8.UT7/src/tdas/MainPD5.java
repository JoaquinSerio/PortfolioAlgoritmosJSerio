package tdas;

import java.util.*;

public class MainPD5 {

    public static void main(String[] args) {

        // Prueba 1: Grafo sin ciclos
        System.out.println("1. PRUEBA GRAFO SIN CICLOS:");
        TGrafoDirigido grafoSinCiclos = crearGrafoSinCiclos();
        probarFuncionalidades(grafoSinCiclos);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Prueba 2: Grafo con ciclos
        System.out.println("2. PRUEBA GRAFO CON CICLOS:");
        TGrafoDirigido grafoConCiclos = crearGrafoConCiclos();
        probarFuncionalidades(grafoConCiclos);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Prueba 3: Grafo fuertemente conexo
        System.out.println("3. PRUEBA GRAFO FUERTEMENTE CONEXO:");
        TGrafoDirigido grafoFuerteConexo = crearGrafoFuerteConexo();
        probarFuncionalidades(grafoFuerteConexo);

        System.out.println("\n" + "=".repeat(50) + "\n");

        // Prueba 4: Grafo desconectado
        System.out.println("4. PRUEBA GRAFO DESCONECTADO:");
        TGrafoDirigido grafoDesconectado = crearGrafoDesconectado();
        probarFuncionalidades(grafoDesconectado);
    }

    private static void probarFuncionalidades(TGrafoDirigido grafo) {
        System.out.println("Vértices: " + Arrays.toString(grafo.getEtiquetasOrdenado()));

        // Ejercicio 1: Detección de ciclos
        System.out.println("¿Tiene ciclos? " + grafo.tieneCiclo());

        // Ejercicio 2: Ordenación topológica
        LinkedList<Comparable> ordenTopologico = grafo.ordenTopologico();
        if (ordenTopologico != null) {
            System.out.println("Orden topológico: " + ordenTopologico);
        } else {
            System.out.println("No se puede hacer orden topológico (tiene ciclos)");
        }

        // Ejercicio 3: Conectividad fuerte
        System.out.println("¿Es fuertemente conexo? " + grafo.esConexo());

        Collection<Collection<Comparable>> componentes = grafo.obtenerComponentesFuertes();
        System.out.println("Componentes fuertes (" + componentes.size() + "):");
        int i = 1;
        for (Collection<Comparable> componente : componentes) {
            System.out.println("  Componente " + i + ": " + componente);
            i++;
        }
    }

    // Grafo DAG: A -> B -> D, A -> C -> D
    private static TGrafoDirigido crearGrafoSinCiclos() {
        Collection<IVertice> vertices = Arrays.asList(
                new TVertice("A"), new TVertice("B"),
                new TVertice("C"), new TVertice("D")
        );

        Collection<IArista> aristas = Arrays.asList(
                new TArista("A", "B", 1.0),
                new TArista("A", "C", 2.0),
                new TArista("B", "D", 3.0),
                new TArista("C", "D", 4.0)
        );

        return new TGrafoDirigido(vertices, aristas);
    }

    // Grafo con ciclo: A -> B -> C -> A
    private static TGrafoDirigido crearGrafoConCiclos() {
        Collection<IVertice> vertices = Arrays.asList(
                new TVertice("A"), new TVertice("B"),
                new TVertice("C"), new TVertice("D")
        );

        Collection<IArista> aristas = Arrays.asList(
                new TArista("A", "B", 1.0),
                new TArista("B", "C", 2.0),
                new TArista("C", "A", 3.0),  // Ciclo
                new TArista("A", "D", 4.0)
        );

        return new TGrafoDirigido(vertices, aristas);
    }

    // Grafo fuertemente conexo: todos pueden llegar a todos
    private static TGrafoDirigido crearGrafoFuerteConexo() {
        Collection<IVertice> vertices = Arrays.asList(
                new TVertice("A"), new TVertice("B"), new TVertice("C")
        );

        Collection<IArista> aristas = Arrays.asList(
                new TArista("A", "B", 1.0),
                new TArista("B", "C", 2.0),
                new TArista("C", "A", 3.0)
        );

        return new TGrafoDirigido(vertices, aristas);
    }

    // Grafo con múltiples componentes: A->B, C->D->E->C
    private static TGrafoDirigido crearGrafoDesconectado() {
        Collection<IVertice> vertices = Arrays.asList(
                new TVertice("A"), new TVertice("B"),
                new TVertice("C"), new TVertice("D"), new TVertice("E")
        );

        Collection<IArista> aristas = Arrays.asList(
                new TArista("A", "B", 1.0),
                new TArista("C", "D", 2.0),
                new TArista("D", "E", 3.0),
                new TArista("E", "C", 4.0)
        );

        return new TGrafoDirigido(vertices, aristas);
    }
}
