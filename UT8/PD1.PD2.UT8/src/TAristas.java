import java.util.Collection;
import java.util.LinkedList;

public class TAristas extends LinkedList<TArista> {

    private final static String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

    public TArista buscar(Comparable etOrigen, Comparable etDestino) {
        for (TArista arista : this) {
            if (arista.getEtiquetaOrigen().equals(etOrigen) && arista.getEtiquetaDestino().equals(etDestino)) {
                return arista;
            }
        }
        return null;
    }

    public TArista buscarMin(Collection<Comparable> VerticesU, Collection<Comparable> VerticesV) {
        TArista tAMin = null;
        double costoMin = Double.MAX_VALUE;

        for (Comparable u : VerticesU) {
            for (Comparable v : VerticesV) {
                TArista tA = buscar(u, v);
                if (tA != null && tA.getCosto() < costoMin) {
                    tAMin = tA;
                    costoMin = tA.getCosto();
                }
            }
        }

        return tAMin;
    }

    public String imprimirEtiquetas() {
        if (this.isEmpty()) {
            return null;
        }
        StringBuilder salida = new StringBuilder();
        for (TArista arista : this) {
            salida.append(arista.getEtiquetaOrigen())
                    .append(SEPARADOR_ELEMENTOS_IMPRESOS)
                    .append(arista.getEtiquetaDestino())
                    .append(SEPARADOR_ELEMENTOS_IMPRESOS)
                    .append(arista.getCosto())
                    .append("\n");
        }
        return salida.toString();
    }

    void insertarAmbosSentidos(Collection<TArista> aristas) {
        if (aristas == null) return;
        for (TArista ta : aristas) {
            this.add(ta);
            this.add(ta.aristaInversa());
        }
    }
}