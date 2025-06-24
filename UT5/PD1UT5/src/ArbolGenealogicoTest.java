import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ArbolGenealogicoTest {
    private ArbolGenealogico<String> arbol;

    @BeforeEach
    void init() {
        arbol = new ArbolGenealogico<>();
    }

    @Test
    void testInsertarRaiz() {
        assertTrue(arbol.insertar("Director", ""));
        assertNotNull(arbol.getRaiz());
        assertEquals("Director", arbol.getRaiz().getEtiqueta());
    }

    @Test
    void testInsertarSinPadre() {
        assertFalse(arbol.insertar("Jefe", "NoExiste"));
    }

    @Test
    void testBuscarExistenteYNoExistente() {
        arbol.insertar("A", "");
        arbol.insertar("B", "A");
        assertNotNull(arbol.buscar("B"));
        assertNull(arbol.buscar("C"));
    }

    @Test
    void testListarIndentado() {
        arbol.insertar("A", "");
        arbol.insertar("B", "A");
        arbol.insertar("C", "A");
        arbol.insertar("D", "B");
        String esperado =
                "A\n" +
                        "  B\n" +
                        "    D\n" +
                        "  C\n";
        assertEquals(esperado, arbol.listarIndentado());
    }

    @Test
    void testEscenarioCompleto() {
        String[] ids = {"R", "C1", "C2", "GC1", "GC2", "GC3"};
        String[] padres = {"", "R", "R", "C1", "C1", "C2"};
        for (int i = 0; i < ids.length; i++) {
            assertTrue(arbol.insertar(ids[i], padres[i]));
        }
        assertEquals("R", arbol.getRaiz().getEtiqueta());
        assertEquals("C1", arbol.getRaiz().getPrimerHijo().getEtiqueta());
        assertNotNull(arbol.buscar("GC3"));
        String indent = arbol.listarIndentado();
        assertTrue(indent.contains("GC3"));
    }
}
