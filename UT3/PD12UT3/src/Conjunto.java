/**
 *
 * @author ernesto
 * @param <T>
 */
public class Conjunto<T> extends Lista<T> implements IConjunto {
// MEJOR: public class Conjunto<T> extends ListaOrdenada<T> implements IConjunto {

    @Override
    public void insertar(Nodo<T> unNodo) {
        // En un conjunto no se permiten elementos duplicados
        if (buscar(unNodo.getEtiqueta()) == null) {
            super.insertar(unNodo);
        }
    }

    @Override
    public Conjunto union(Conjunto otroConjunto) {
        Conjunto<T> resultado = new Conjunto<>();

        // Agregamos todos los elementos del conjunto actual
        Nodo<T> actual = this.getPrimero();
        while (actual != null) {
            resultado.insertar(actual.clonar());
            actual = actual.getSiguiente();
        }

        // Agregamos los elementos del otro conjunto que no estén ya en el resultado
        actual = otroConjunto.getPrimero();
        while (actual != null) {
            if (resultado.buscar(actual.getEtiqueta()) == null) {
                resultado.insertar(actual.clonar());
            }
            actual = actual.getSiguiente();
        }

        return resultado;
    }

    @Override
    public Conjunto interseccion(Conjunto otroConjunto) {
        Conjunto<T> resultado = new Conjunto<>();

        // Recorremos el conjunto actual y agregamos al resultado solo los elementos
        // que también están en el otro conjunto
        Nodo<T> actual = this.getPrimero();
        while (actual != null) {
            if (otroConjunto.buscar(actual.getEtiqueta()) != null) {
                resultado.insertar(actual.clonar());
            }
            actual = actual.getSiguiente();
        }

        return resultado;
    }
}