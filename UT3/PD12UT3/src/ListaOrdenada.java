/**
 *
 * @author ernesto
 * @param <T>
 */
public class ListaOrdenada<T> extends Lista<T> {

    @Override
    public void insertar(Nodo<T> unNodo) {
        // Si la lista está vacía, insertamos como primer elemento
        if (esVacia()) {
            setPrimero(unNodo);
            return;
        }

        // Si el nodo a insertar es menor que el primero, lo insertamos al inicio
        if (unNodo.compareTo(getPrimero().getEtiqueta()) < 0) {
            unNodo.setSiguiente(getPrimero());
            setPrimero(unNodo);
            return;
        }

        // Buscamos la posición correcta para insertar
        Nodo<T> actual = getPrimero();
        Nodo<T> anterior = null;

        while (actual != null && actual.compareTo(unNodo.getEtiqueta()) < 0) {
            anterior = actual;
            actual = actual.getSiguiente();
        }

        // Insertamos el nodo en la posición encontrada
        if (anterior != null) {
            anterior.setSiguiente(unNodo);
            unNodo.setSiguiente(actual);
        }
    }
}