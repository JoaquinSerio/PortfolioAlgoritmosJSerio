public class TDAListaDemo {
    public static void main(String[] args) {
        Lista<String> lista = new Lista<>();
        lista.insertar(new Nodo<>(1, "Alice"));
        lista.insertar(3, "Charlie");
        lista.insertar(2, "Bob");

        System.out.println("Elementos tras inserciones: " + lista.imprimir(" -> "));
        System.out.println("Cantidad: " + lista.cantElementos());

        Nodo<String> buscado = lista.buscar(2);
        System.out.println("Buscar clave '2': " + (buscado != null ? buscado.getDato() : "no encontrado"));

        boolean eliminado = lista.eliminar(3);
        System.out.println("Eliminar clave '3': " + eliminado);
        System.out.println("Elementos tras eliminar: " + lista.imprimir(" -> "));
        System.out.println("Cantidad: " + lista.cantElementos());
    }
}
