package Interfaces_TA2;

public class TElementoAB<T> implements IElementoAB<T> {
    private Comparable etiqueta;
    private T datos;
    private IElementoAB<T> hijoIzq;
    private IElementoAB<T> hijoDer;

    public TElementoAB(Comparable etiqueta, T datos) {
        this.etiqueta = etiqueta;
        this.datos = datos;
        this.hijoIzq = null;
        this.hijoDer = null;
    }

    @Override
    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    @Override
    public T getDatos() {
        return this.datos;
    }

    @Override
    public TElementoAB eliminar(Comparable unaEtiqueta) {
        return null;
    }

    @Override
    public void setDatos(T datos) {
        this.datos = datos;
    }

    @Override
    public TElementoAB<T> getHijoIzq() {
        return (TElementoAB<T>) this.hijoIzq;
    }

    @Override
    public TElementoAB<T> getHijoDer() {
        return (TElementoAB<T>) this.hijoDer;
    }

    @Override
    public void setHijoIzq(TElementoAB<T> elemento) {

    }

    @Override
    public void setHijoDer(TElementoAB<T> elemento) {

    }

    @Override
    public void setHijoIzq(IElementoAB<T> elemento) {
        this.hijoIzq = elemento;
    }

    @Override
    public void setHijoDer(IElementoAB<T> elemento) {
        this.hijoDer = elemento;
    }

    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (unaEtiqueta.equals(this.etiqueta)) {
            return this;
        } else if (unaEtiqueta.compareTo(this.etiqueta) < 0) {
            if (this.hijoIzq != null) {
                return this.hijoIzq.buscar(unaEtiqueta);
            } else {
                return null;
            }
        } else if (this.hijoDer != null) {
            return this.hijoDer.buscar(unaEtiqueta);
        } else {
            return null;
        }
    }

    @Override
    public boolean insertar(TElementoAB<T> elemento) {
        return insertar((IElementoAB<T>) elemento);
    }

    @Override
    public boolean insertar(IElementoAB<T> elemento) {
        if (elemento.getEtiqueta().compareTo(this.etiqueta) < 0) {
            if (this.hijoIzq != null) {
                return this.hijoIzq.insertar(elemento);  // Sin casteo
            } else {
                this.hijoIzq = elemento;
                return true;
            }
        } else if (elemento.getEtiqueta().compareTo(this.etiqueta) > 0) {
            if (this.hijoDer != null) {
                return this.hijoDer.insertar(elemento);  // Sin casteo
            } else {
                this.hijoDer = elemento;
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public String preOrden() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.etiqueta.toString()).append(" ");

        if (this.hijoIzq != null) {
            sb.append(this.hijoIzq.preOrden());
        }

        if (this.hijoDer != null) {
            sb.append(this.hijoDer.preOrden());
        }

        return sb.toString();
    }

    @Override
    public String inOrden() {
        StringBuilder sb = new StringBuilder();

        if (this.hijoIzq != null) {
            sb.append(this.hijoIzq.inOrden());
        }

        sb.append(this.etiqueta.toString()).append(" ");

        if (this.hijoDer != null) {
            sb.append(this.hijoDer.inOrden());
        }

        return sb.toString();
    }

    @Override
    public String postOrden() {
        StringBuilder sb = new StringBuilder();

        if (this.hijoIzq != null) {
            sb.append(this.hijoIzq.postOrden());
        }

        if (this.hijoDer != null) {
            sb.append(this.hijoDer.postOrden());
        }

        sb.append(this.etiqueta.toString()).append(" ");

        return sb.toString();
    }

    @Override
    public int obtenerAltura() {
        int altIzq = -1;
        int altDer = -1;

        if (this.hijoIzq != null) {
            altIzq = this.hijoIzq.obtenerAltura();
        }

        if (this.hijoDer != null) {
            altDer = this.hijoDer.obtenerAltura();
        }

        return Math.max(altIzq, altDer) + 1;
    }

    @Override
    public int obtenerTamaño() {
        int tamIzq = 0;
        int tamDer = 0;

        if (this.hijoIzq != null) {
            tamIzq = this.hijoIzq.obtenerTamaño();
        }

        if (this.hijoDer != null) {
            tamDer = this.hijoDer.obtenerTamaño();
        }

        return tamIzq + tamDer + 1;
    }

    @Override
    public int obtenerNivel(Comparable etiqueta) {
        if (this.etiqueta.equals(etiqueta)) {
            return 0;
        } else if (etiqueta.compareTo(this.etiqueta) < 0 && this.hijoIzq != null) {
            int nivel = this.hijoIzq.obtenerNivel(etiqueta);
            if (nivel != -1) {
                return nivel + 1;
            }
        } else if (etiqueta.compareTo(this.etiqueta) > 0 && this.hijoDer != null) {
            int nivel = this.hijoDer.obtenerNivel(etiqueta);
            if (nivel != -1) {
                return nivel + 1;
            }
        }

        return -1;
    }

    @Override
    public TElementoAB<T> menorNodo() {
        if (this.hijoIzq == null) {
            return this;
        } else {
            return ((TElementoAB<T>) this.hijoIzq).menorNodo();
        }
    }

    @Override
    public TElementoAB<T> mayorNodo() {
        if (this.hijoDer == null) {
            return this;
        } else {
            return ((TElementoAB<T>) this.hijoDer).mayorNodo();
        }
    }

    @Override
    public int contarEnNivel(int k) {
        if (k == 1) {
            return 1;
        }
        int suma = 0;
        if (hijoIzq != null) {
            suma += hijoIzq.contarEnNivel(k - 1);
        }
        if (hijoDer != null) {
            suma += hijoDer.contarEnNivel(k - 1);
        }
        return suma;
    }
}