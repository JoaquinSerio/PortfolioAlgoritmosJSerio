public class ArbolGenealogico<T> {
    private NodoGen<T> raiz;

    public boolean insertar(T etiqueta, T etiquetaPadre) {
        // Si etiquetaPadre es null o cadena vacía, se trata de insertar la raíz
        if (etiquetaPadre == null || (etiquetaPadre instanceof String && ((String) etiquetaPadre).isEmpty())) {
            if (raiz == null) {
                raiz = new NodoGen<>(etiqueta);
                return true;
            }
            return false;
        }

        NodoGen<T> padre = (raiz != null) ? raiz.buscar(etiquetaPadre) : null;
        if (padre == null) return false;

        NodoGen<T> nuevo = new NodoGen<>(etiqueta);
        if (padre.getPrimerHijo() == null) {
            padre.setPrimerHijo(nuevo);
        } else {
            NodoGen<T> actual = padre.getPrimerHijo();
            while (actual.getHermanoDerecho() != null) {
                actual = actual.getHermanoDerecho();
            }
            actual.setHermanoDerecho(nuevo);
        }
        return true;
    }

    public NodoGen<T> buscar(T etiqueta) {
        return (raiz != null) ? raiz.buscar(etiqueta) : null;
    }

    public String listarIndentado() {
        if (raiz == null) return "";
        StringBuilder sb = new StringBuilder();
        raiz.listarIndentado(sb, 1);
        return sb.toString();
    }

    public NodoGen<T> getRaiz() {
        return raiz;
    }
}

