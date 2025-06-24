package interfacesYUtilTA2;
public class Lista<T> implements ILista<T> {
    private Nodo<T> primero;

    public Lista() {
        this.primero = null;
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
    }

    @Override
    public void insertar(Comparable etiqueta, T dato) {
        insertar(new Nodo<>(etiqueta, dato));
    }

    @Override
    public Nodo<T> buscar(Comparable clave) {
        Nodo<T> actual = primero;
        while (actual != null) {
            if (actual.getEtiqueta().equals(clave)) {
                return actual;
            }
            actual = actual.getSiguiente();
        }
        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        if (primero == null) return false;
        if (primero.getEtiqueta().equals(clave)) {
            primero = primero.getSiguiente();
            return true;
        }
        Nodo<T> actual = primero;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getEtiqueta().equals(clave)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    @Override
    public String imprimir() {
        return imprimir(" ");
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
        int count = 0;
        Nodo<T> actual = primero;
        while (actual != null) {
            count++;
            actual = actual.getSiguiente();
        }
        return count;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }

    @Override
    public void setPrimero(Nodo<T> unNodo) {
        primero = unNodo;
    }

    @Override
    public Nodo<T> getPrimero() {
        return primero;
    }
}