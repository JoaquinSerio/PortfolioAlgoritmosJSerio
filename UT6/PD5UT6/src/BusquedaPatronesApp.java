import java.util.List;
import java.util.Scanner;

public class BusquedaPatronesApp {

    private TTrieHashMap trie;
    private Scanner scanner;
    private String textoActual;

    public BusquedaPatronesApp() {
        trie = new TTrieHashMap();
        scanner = new Scanner(System.in);
    }

    public void ejecutar() {
        System.out.println("=== APLICACIÓN DE BÚSQUEDA DE PATRONES ===");

        while (true) {
            mostrarMenu();
            int opcion = leerOpcion();

            switch (opcion) {
                case 1:
                    ingresarTexto();
                    break;
                case 2:
                    buscarPatron();
                    break;
                case 3:
                    mostrarTexto();
                    break;
                case 4:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private void mostrarMenu() {
        System.out.println("\n--- MENÚ ---");
        System.out.println("1. Ingresar texto");
        System.out.println("2. Buscar patrón");
        System.out.println("3. Mostrar texto actual");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void ingresarTexto() {
        System.out.print("Ingrese el texto: ");
        textoActual = scanner.nextLine();
        trie = new TTrieHashMap(); // Reiniciar el trie
        System.out.println("Texto ingresado correctamente.");
    }

    private void buscarPatron() {
        if (textoActual == null || textoActual.isEmpty()) {
            System.out.println("Primero debe ingresar un texto.");
            return;
        }

        System.out.print("Ingrese el patrón a buscar: ");
        String patron = scanner.nextLine();

        if (patron.isEmpty()) {
            System.out.println("El patrón no puede estar vacío.");
            return;
        }

        // Insertar el patrón en el trie con sus posiciones
        trie.insertarPatron(textoActual, patron);

        // Buscar las posiciones
        List<Integer> posiciones = trie.buscarPatron(patron);

        if (posiciones.isEmpty()) {
            System.out.println("El patrón '" + patron + "' no se encontró en el texto.");
        } else {
            System.out.println("Patrón '" + patron + "' encontrado en " + posiciones.size() + " posiciones:");
            for (int pos : posiciones) {
                System.out.println("- Posición " + pos + ": \"" +
                        mostrarContexto(textoActual, pos, patron.length()) + "\"");
            }
        }
    }

    private String mostrarContexto(String texto, int posicion, int longitudPatron) {
        int inicio = Math.max(0, posicion - 10);
        int fin = Math.min(texto.length(), posicion + longitudPatron + 10);
        String contexto = texto.substring(inicio, fin);

        if (inicio > 0) contexto = "..." + contexto;
        if (fin < texto.length()) contexto = contexto + "...";

        return contexto;
    }

    private void mostrarTexto() {
        if (textoActual == null || textoActual.isEmpty()) {
            System.out.println("No hay texto ingresado.");
        } else {
            System.out.println("Texto actual:");
            System.out.println("\"" + textoActual + "\"");
            System.out.println("Longitud: " + textoActual.length() + " caracteres");
        }
    }

    public static void main(String[] args) {
        new BusquedaPatronesApp().ejecutar();
    }
}