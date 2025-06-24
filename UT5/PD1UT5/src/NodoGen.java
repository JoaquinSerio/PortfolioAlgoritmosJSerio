public class NodoGen<T> {
    private T etiqueta;
    private NodoGen<T> primerHijo;
    private NodoGen<T> hermanoDerecho;

    public NodoGen(T etiqueta) {
        this.etiqueta = etiqueta;
    }

    public T getEtiqueta() {
        return etiqueta;
    }

    public NodoGen<T> getPrimerHijo() {
        return primerHijo;
    }

    public NodoGen<T> getHermanoDerecho() {
        return hermanoDerecho;
    }

    void setPrimerHijo(NodoGen<T> nodo) {
        this.primerHijo = nodo;
    }

    void setHermanoDerecho(NodoGen<T> nodo) {
        this.hermanoDerecho = nodo;
    }

    NodoGen<T> buscar(T clave) {
        if (etiqueta.equals(clave)) return this;
        NodoGen<T> hijo = primerHijo;
        while (hijo != null) {
            NodoGen<T> encontrado = hijo.buscar(clave);
            if (encontrado != null) return encontrado;
            hijo = hijo.hermanoDerecho;
        }
        return null;
    }

    void listarIndentado(StringBuilder sb, int nivel) {
        sb.append("  ".repeat(nivel - 1)).append(etiqueta).append("\n");
        NodoGen<T> hijo = primerHijo;
        while (hijo != null) {
            hijo.listarIndentado(sb, nivel + 1);
            hijo = hijo.hermanoDerecho;
        }
    }
}