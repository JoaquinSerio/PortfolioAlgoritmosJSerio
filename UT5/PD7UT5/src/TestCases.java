import java.util.LinkedList;

public class TestCases {

    public static void main(String[] args) {
        testInsercionYBusqueda();
        testOrdenAlfabetico();
        testBusquedaSinResultados();
        testMultiplesAbonados();
    }

    public static void testInsercionYBusqueda() {
        System.out.println("Test 1: Inserción y búsqueda básica");
        TArbolTrieTelefonos trie = new TArbolTrieTelefonos();

        TAbonado abonado1 = new TAbonado("Juan", "05412312345");
        TAbonado abonado2 = new TAbonado("Ana", "05412398765");

        trie.insertar(abonado1);
        trie.insertar(abonado2);

        LinkedList<TAbonado> resultado = trie.buscarTelefonos("054", "12");

        if (resultado.size() == 2) {
            System.out.println("PASS: Se encontraron 2 abonados");
        } else {
            System.out.println("FAIL: Se esperaban 2 abonados, se encontraron " + resultado.size());
        }
    }

    public static void testOrdenAlfabetico() {
        System.out.println("\nTest 2: Orden alfabético");
        TArbolTrieTelefonos trie = new TArbolTrieTelefonos();

        TAbonado abonado1 = new TAbonado("Carlos", "05412312345");
        TAbonado abonado2 = new TAbonado("Ana", "05412398765");
        TAbonado abonado3 = new TAbonado("Bruno", "05412387654");

        trie.insertar(abonado1);
        trie.insertar(abonado2);
        trie.insertar(abonado3);

        LinkedList<TAbonado> resultado = trie.buscarTelefonos("054", "12");

        if (resultado.size() == 3 &&
                resultado.get(0).getNombre().equals("Ana") &&
                resultado.get(1).getNombre().equals("Bruno") &&
                resultado.get(2).getNombre().equals("Carlos")) {
            System.out.println("PASS: Los abonados están ordenados alfabéticamente");
        } else {
            System.out.println("FAIL: Los abonados no están ordenados correctamente");
        }
    }

    public static void testBusquedaSinResultados() {
        System.out.println("\nTest 3: Búsqueda sin resultados");
        TArbolTrieTelefonos trie = new TArbolTrieTelefonos();

        TAbonado abonado1 = new TAbonado("Juan", "05412312345");
        trie.insertar(abonado1);

        LinkedList<TAbonado> resultado = trie.buscarTelefonos("598", "99");

        if (resultado.size() == 0) {
            System.out.println("PASS: No se encontraron abonados para código inexistente");
        } else {
            System.out.println("FAIL: Se encontraron abonados cuando no debería");
        }
    }

    public static void testMultiplesAbonados() {
        System.out.println("\nTest 4: Múltiples abonados mismo prefijo");
        TArbolTrieTelefonos trie = new TArbolTrieTelefonos();

        TAbonado abonado1 = new TAbonado("Pedro", "59893123456");
        TAbonado abonado2 = new TAbonado("Maria", "59893987654");
        TAbonado abonado3 = new TAbonado("Luis", "59893555555");
        TAbonado abonado4 = new TAbonado("Sara", "59894111111");

        trie.insertar(abonado1);
        trie.insertar(abonado2);
        trie.insertar(abonado3);
        trie.insertar(abonado4);

        LinkedList<TAbonado> resultado93 = trie.buscarTelefonos("598", "93");
        LinkedList<TAbonado> resultado94 = trie.buscarTelefonos("598", "94");

        if (resultado93.size() == 3 && resultado94.size() == 1) {
            System.out.println("PASS: Búsqueda por diferentes áreas funciona correctamente");
        } else {
            System.out.println("FAIL: Búsqueda por áreas no funciona correctamente");
        }
    }
}
