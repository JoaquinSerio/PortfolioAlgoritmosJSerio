import Interfaces_TA2.IElementoAB;
import Interfaces_TA2.TArbolBB;
import Interfaces_TA2.TElementoAB;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("\nEjecución 2: Prueba con archivo clavesPrueba.txt");
        // Crear árbol binario de búsqueda
        TArbolBB<String> arbol = new TArbolBB<>();

        // Leer archivo de claves de prueba
        String rutaClavesPrueba = "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT4\\PD2UT4\\src\\clavesYconsultaPrueba\\clavesPrueba.txt";
        String[] clavesPrueba = ManejadorArchivosGenerico.leerArchivo(rutaClavesPrueba);

        // Insertar claves en el árbol
        for (String clave : clavesPrueba) {
            try {
                int valor = Integer.parseInt(clave.trim());
                TElementoAB<String> elemento = new TElementoAB<>(valor, clave);
                arbol.insertar(elemento);
            } catch (NumberFormatException e) {
                System.out.println("Error al procesar la clave: " + clave);
            }
        }

        // Obtener los recorridos
        String preorden = arbol.preOrden();
        String inorden = arbol.inOrden();
        String postorden = arbol.postOrden();

        // Mostrar recorridos por consola
        System.out.println("Preorden: " + preorden);
        System.out.println("Inorden: " + inorden);
        System.out.println("Postorden: " + postorden);

        // Guardar recorridos en archivos
        List<String> lineasPreorden = new ArrayList<>();
        lineasPreorden.add(preorden);
        ManejadorArchivosGenerico.escribirArchivo("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT4\\PD2UT4\\src\\preorden.txt", lineasPreorden.toArray(new String[0]));

        List<String> lineasInorden = new ArrayList<>();
        lineasInorden.add(inorden);
        ManejadorArchivosGenerico.escribirArchivo("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT4\\PD2UT4\\src\\inorden.txt", lineasInorden.toArray(new String[0]));

        List<String> lineasPostorden = new ArrayList<>();
        lineasPostorden.add(postorden);
        ManejadorArchivosGenerico.escribirArchivo("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT4\\PD2UT4\\src\\postorden.txt", lineasPostorden.toArray(new String[0]));

        System.out.println("Resultados guardados en archivos de texto.");

        // Ejecución 3: Archivo más grande
        System.out.println("\nEjecución 3: Prueba con archivo claves.txt");
        TArbolBB<String> arbolGrande = new TArbolBB<>();

        // Seleccionar archivo de claves según ejercicio
        String rutaClaves = "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT4\\PD2UT4\\src\\claves1\\claves1.txt";
        String[] claves = ManejadorArchivosGenerico.leerArchivo(rutaClaves);

        // Insertar claves en el árbol grande
        for (String clave : claves) {
            try {
                int valor = Integer.parseInt(clave.trim());
                TElementoAB<String> elemento = new TElementoAB<>(valor, clave);
                arbolGrande.insertar(elemento);
            } catch (NumberFormatException e) {
                System.out.println("Error al procesar la clave: " + clave);
            }
        }

        // Buscar claves específicas
        int[] clavesABuscar = {10635, 4567, 12, 8978};
        for (int clave : clavesABuscar) {
            IElementoAB<String> resultado = arbolGrande.buscar(clave);
            if (resultado != null) {
                System.out.println("La clave " + clave + " existe en el árbol.");
            } else {
                System.out.println("La clave " + clave + " NO existe en el árbol.");
            }
        }

        // Obtener la décima clave del listado en preorden
        String[] clavesPreorden = arbolGrande.preOrden().split(" ");
        if (clavesPreorden.length >= 10) {
            System.out.println("La décima clave del listado en preorden es: " + clavesPreorden[9]);
        } else {
            System.out.println("El árbol tiene menos de 10 elementos.");
        }
    }
}