// Archivo: Main.java
package interfacesYUtilTA2;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Lista<String> sucursales = new Lista<>();
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(
                "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT3\\PD6UT3\\src\\interfacesYUtilTA2\\sucursales.txt"
        );

        for (String ciudad : lineas) {
            sucursales.insertar(new Nodo<>(ciudad, ciudad));
        }

        System.out.println("Paso 1: Cargar sucursales");
        System.out.println("Cantidad: " + sucursales.cantElementos());
        System.out.println("Listado:");
        System.out.println(sucursales.imprimir("\n"));

        sucursales.eliminar("Chicago");
        System.out.println("\nPaso 2: Eliminar 'Chicago'");
        System.out.println("Listado:");
        System.out.println(sucursales.imprimir("\n"));
        Nodo<String> nodoHongKong = sucursales.buscar("Hong Kong");
        String siguiente = (nodoHongKong != null && nodoHongKong.getSiguiente() != null)
                ? nodoHongKong.getSiguiente().getDato()
                : "";
        System.out.println("Después de 'Hong Kong' viene: " + siguiente);

        Lista<String> segLista = new Lista<>();
        String[] lineas2 = ManejadorArchivosGenerico.leerArchivo(
                "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT3\\PD6UT3\\src\\interfacesYUtilTA2\\suc2.txt"
        );
        for (String ciudad : lineas2) segLista.insertar(new Nodo<>(ciudad, ciudad));
        segLista.eliminar("Shenzen");
        segLista.eliminar("Tokio");
        System.out.println("\nPaso 3: Segundo archivo, eliminar 'Shenzen' y 'Tokio'");
        System.out.println("Cantidad: " + segLista.cantElementos());
        System.out.println("Listado:");
        System.out.println(segLista.imprimir("\n"));

        Lista<String> terLista = new Lista<>();
        String[] lineas3 = ManejadorArchivosGenerico.leerArchivo(
                "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT3\\PD6UT3\\src\\interfacesYUtilTA2\\suc3.txt"
        );
        for (String ciudad : lineas3) terLista.insertar(new Nodo<>(ciudad, ciudad));
        System.out.println("\nPaso 4: Tercer archivo, imprimir con separador ';_' ");
        System.out.println(terLista.imprimir(";_"));
    }
}
