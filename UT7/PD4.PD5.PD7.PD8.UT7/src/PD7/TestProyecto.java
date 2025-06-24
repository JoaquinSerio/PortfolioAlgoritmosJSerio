package PD7;

import tdas.*;
import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestProyecto {

    @Test
    public void testGrafoVacio() {
        Collection<IVertice> vertices = new ArrayList<>();
        Collection<IArista> aristas = new ArrayList<>();

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);
        LinkedList<Comparable> orden = grafo.ordenParcial();

        assertTrue(orden.isEmpty());
    }

    @Test
    public void testGrafoSinAristas() {
        Collection<IVertice> vertices = new ArrayList<>();
        vertices.add(new TVertice<>("A"));
        vertices.add(new TVertice<>("B"));
        vertices.add(new TVertice<>("C"));

        Collection<IArista> aristas = new ArrayList<>();

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);
        LinkedList<Comparable> orden = grafo.ordenParcial();

        assertEquals(3, orden.size());
        assertTrue(orden.contains("A"));
        assertTrue(orden.contains("B"));
        assertTrue(orden.contains("C"));
    }

    @Test
    public void testOrdenTopologicoSimple() {
        Collection<IVertice> vertices = new ArrayList<>();
        vertices.add(new TVertice<>("A"));
        vertices.add(new TVertice<>("B"));
        vertices.add(new TVertice<>("C"));

        Collection<IArista> aristas = new ArrayList<>();
        aristas.add(new TArista("A", "B", 1.0));
        aristas.add(new TArista("B", "C", 1.0));

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);
        LinkedList<Comparable> orden = grafo.ordenParcial();

        assertEquals(3, orden.size());

        int posA = orden.indexOf("A");
        int posB = orden.indexOf("B");
        int posC = orden.indexOf("C");

        assertTrue(posA < posB);
        assertTrue(posB < posC);
    }

    @Test
    public void testOrdenTopologicoComplejo() {
        Collection<IVertice> vertices = new ArrayList<>();
        vertices.add(new TVertice<>("A"));
        vertices.add(new TVertice<>("B"));
        vertices.add(new TVertice<>("C"));
        vertices.add(new TVertice<>("D"));

        Collection<IArista> aristas = new ArrayList<>();
        aristas.add(new TArista("A", "C", 1.0));
        aristas.add(new TArista("B", "C", 1.0));
        aristas.add(new TArista("C", "D", 1.0));

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);
        LinkedList<Comparable> orden = grafo.ordenParcial();

        assertEquals(4, orden.size());

        int posA = orden.indexOf("A");
        int posB = orden.indexOf("B");
        int posC = orden.indexOf("C");
        int posD = orden.indexOf("D");

        assertTrue(posA < posC);
        assertTrue(posB < posC);
        assertTrue(posC < posD);
    }

    @Test
    public void testVerticeAislado() {
        Collection<IVertice> vertices = new ArrayList<>();
        vertices.add(new TVertice<>("A"));
        vertices.add(new TVertice<>("B"));
        vertices.add(new TVertice<>("C"));

        Collection<IArista> aristas = new ArrayList<>();
        aristas.add(new TArista("A", "B", 1.0));

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);
        LinkedList<Comparable> orden = grafo.ordenParcial();

        assertEquals(3, orden.size());
        assertTrue(orden.contains("C"));

        int posA = orden.indexOf("A");
        int posB = orden.indexOf("B");
        assertTrue(posA < posB);
    }

    @Test
    public void testExisteVertice() {
        Collection<IVertice> vertices = new ArrayList<>();
        vertices.add(new TVertice<>("A"));
        vertices.add(new TVertice<>("B"));

        Collection<IArista> aristas = new ArrayList<>();

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        assertTrue(grafo.existeVertice("A"));
        assertTrue(grafo.existeVertice("B"));
        assertFalse(grafo.existeVertice("C"));
    }

    @Test
    public void testExisteArista() {
        Collection<IVertice> vertices = new ArrayList<>();
        vertices.add(new TVertice<>("A"));
        vertices.add(new TVertice<>("B"));
        vertices.add(new TVertice<>("C"));

        Collection<IArista> aristas = new ArrayList<>();
        aristas.add(new TArista("A", "B", 1.0));

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        assertTrue(grafo.existeArista("A", "B"));
        assertFalse(grafo.existeArista("B", "A"));
        assertFalse(grafo.existeArista("A", "C"));
    }

    @Test
    public void testInsertarVertice() {
        Collection<IVertice> vertices = new ArrayList<>();
        Collection<IArista> aristas = new ArrayList<>();

        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        assertTrue(grafo.insertarVertice("A"));
        assertTrue(grafo.existeVertice("A"));
        assertFalse(grafo.insertarVertice("A"));
    }
}