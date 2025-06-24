package Interfaces_TA2;

public class TArbolBB<T> implements IArbolBB<T> {
    private IElementoAB<T> raiz;

    public TArbolBB() {
        this.raiz = null;
    }

    @Override
    public boolean insertar(IElementoAB<T> unElemento) {
        if (this.raiz == null) {
            this.raiz = unElemento;
            return true;
        } else {
            return this.raiz.insertar((TElementoAB<T>) unElemento);
        }
    }

    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        return insertar((IElementoAB<T>) unElemento);
    }

    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (this.raiz == null) {
            return null;
        } else {
            return this.raiz.buscar(unaEtiqueta);
        }
    }

    @Override
    public String preOrden() {
        if (this.raiz == null) {
            return "";
        } else {
            return this.raiz.preOrden();
        }
    }

    @Override
    public String inOrden() {
        if (this.raiz == null) {
            return "";
        } else {
            return this.raiz.inOrden();
        }
    }

    @Override
    public String postOrden() {
        if (this.raiz == null) {
            return "";
        } else {
            return this.raiz.postOrden();
        }
    }

    @Override
    public void eliminar(Comparable unaEtiqueta) {
        // Implementación para el ejercicio no es necesaria
    }

    @Override
    public boolean esVacio() {
        return this.raiz == null;
    }

    @Override
    public int obtenerAltura() {
        if (this.raiz == null) {
            return -1;
        } else {
            return this.raiz.obtenerAltura();
        }
    }

    @Override
    public int obtenerTamaño() {
        if (this.raiz == null) {
            return 0;
        } else {
            return this.raiz.obtenerTamaño();
        }
    }

    @Override
    public int obtenerNivel(Comparable unaEtiqueta) {
        if (this.raiz == null) {
            return -1;
        } else {
            return this.raiz.obtenerNivel(unaEtiqueta);
        }
    }

    @Override
    public IElementoAB<T> getRaiz() {
        return this.raiz;
    }

    @Override
    public TElementoAB<T> menorNodo() {
        if (this.raiz == null) {
            return null;
        } else {
            return ((TElementoAB<T>) this.raiz).menorNodo();
        }
    }

    @Override
    public TElementoAB<T> mayorNodo() {
        if (this.raiz == null) {
            return null;
        } else {
            return ((TElementoAB<T>) this.raiz).mayorNodo();
        }
    }

    @Override
    public int contarEnNivel(int k) {
        if (this.raiz == null || k < 1) {
            return 0;
        } else {
            return this.raiz.contarEnNivel(k);
        }
    }
}