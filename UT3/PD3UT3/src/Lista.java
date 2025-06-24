public class Lista<T> implements ILista<T> {
    private Nodo<T> primero;
    private int cantidad;

    public Lista() {
        this.primero = null;
        this.cantidad = 0;
    }

    @Override
    public void insertar(Nodo<T> nodo) {
        if (primero == null) {
            primero = nodo;
        } else {
            Nodo<T> actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nodo);
        }
        cantidad++;
    }

    @Override
    public void insertar(Comparable etiqueta, T dato) {
        insertar(new Nodo<>(etiqueta, dato));
    }

    @Override
    public Nodo<T> buscar(Comparable clave) {
        Nodo<T> actual = primero;
        while (actual != null) {
            if (actual.compareTo(clave) == 0) return actual;
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        Nodo<T> actual = primero;
        Nodo<T> anterior = null;
        while (actual != null && actual.compareTo(clave) != 0) {
            anterior = actual;
            actual = actual.getSiguiente();
        }
        if (actual == null) return false;
        if (anterior == null) primero = actual.getSiguiente();
        else anterior.setSiguiente(actual.getSiguiente());
        cantidad--;
        return true;
    }

    @Override
    public String imprimir() {
        return imprimir(",");
    }

    @Override
    public String imprimir(String separador) {
        StringBuilder sb = new StringBuilder();
        Nodo<T> actual = primero;
        while (actual != null) {
            sb.append(actual.getEtiqueta());
            if (actual.getSiguiente() != null) sb.append(separador);
            actual = actual.getSiguiente();
        }
        return sb.toString();
    }

    @Override
    public int cantElementos() {
        return cantidad;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }

    @Override
    public void setPrimero(Nodo<T> unNodo) {
        this.primero = unNodo;
    }
}
