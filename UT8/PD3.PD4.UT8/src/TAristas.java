import java.util.Collection;
import java.util.LinkedList;

public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        for (TArista arista : this) {
            if (arista.getEtiquetaOrigen().equals(etOrigen) &&
                    arista.getEtiquetaDestino().equals(etDestino)) {
                return arista;
            }
        }
        return null;
    }

    public TArista buscarMin(Collection<Comparable> VerticesU, Collection<Comparable> VerticesV) {
        TArista aristaMin = null;
        double costoMin = Double.MAX_VALUE;

        for (Comparable u : VerticesU) {
            for (Comparable v : VerticesV) {
                TArista arista = buscar(u, v);
                if (arista != null && arista.getCosto() < costoMin) {
                    aristaMin = arista;
                    costoMin = arista.getCosto();
                }
            }
        }
        return aristaMin;
    }

    public String imprimirEtiquetas() {
        if (this.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        for (TArista arista : this) {
            salida.append(arista.getEtiquetaOrigen())
                    .append(" - ")
                    .append(arista.getEtiquetaDestino())
                    .append(" - ")
                    .append(arista.getCosto())
                    .append("\n");
        }
        return salida.toString();
    }

    void insertarAmbosSentidos(Collection<TArista> aristas) {
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }
}