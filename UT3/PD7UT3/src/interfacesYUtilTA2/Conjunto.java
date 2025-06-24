package interfacesYUtilTA2;

public class Conjunto<T> implements IConjunto<T> {
    private Lista<T> listaInterna = new Lista<>();

    @Override
    public void insertar(Nodo<T> nodo) {
        if (buscar(nodo.getEtiqueta()) == null) listaInterna.insertar(nodo);
    }
    @Override
    public void insertar(Comparable etiqueta, T dato) {
        if (buscar(etiqueta) == null) listaInterna.insertar(etiqueta, dato);
    }

    @Override
    public Nodo<T> buscar(Comparable clave) {
        return listaInterna.buscar(clave);
    }


    @Override
    public String imprimir() {
        return listaInterna.imprimir();
    }

    @Override
    public String imprimir(String separador) {
        return listaInterna.imprimir(separador);
    }

    @Override
    public int cantElementos() {
        return listaInterna.cantElementos();
    }

    @Override
    public boolean esVacia() {
        return listaInterna.esVacia();
    }

    @Override
    public void setPrimero(Nodo<T> unNodo) {
        listaInterna.setPrimero(unNodo);
    }

    @Override
    public Nodo<T> getPrimero() {
        return listaInterna.getPrimero();
    }

    @Override
    public boolean eliminar(Comparable clave) {
        return listaInterna.eliminar(clave);
    }
    // delegar buscar, eliminar, imprimir, etc. a listaInterna

    @Override
    public IConjunto<T> union(IConjunto<T> otro) {
        Conjunto<T> res = new Conjunto<>();
        Nodo<T> a = this.getPrimero();
        Nodo<T> b = otro.getPrimero();
        // usar seudocódigo de Unión sobre listas internas
        while (a != null && b != null) {
            int cmp = a.getEtiqueta().compareTo(b.getEtiqueta());
            if (cmp < 0) { res.insertar(a); a = a.getSiguiente(); }
            else if (cmp > 0) { res.insertar(b); b = b.getSiguiente(); }
            else { res.insertar(a); a = a.getSiguiente(); b = b.getSiguiente(); }
        }
        while (a != null) { res.insertar(a); a = a.getSiguiente(); }
        while (b != null) { res.insertar(b); b = b.getSiguiente(); }
        return res;
    }

    @Override
    public IConjunto<T> interseccion(IConjunto<T> otro) {
        Conjunto<T> res = new Conjunto<>();
        Nodo<T> a = this.getPrimero();
        Nodo<T> b = otro.getPrimero();
        while (a != null && b != null) {
            int cmp = a.getEtiqueta().compareTo(b.getEtiqueta());
            if (cmp < 0) a = a.getSiguiente();
            else if (cmp > 0) b = b.getSiguiente();
            else { res.insertar(a); a = a.getSiguiente(); b = b.getSiguiente(); }
        }
        return res;
    }
}