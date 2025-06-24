import java.util.Collection;
import java.util.LinkedList;

public class TGrafoRedElectrica extends TGrafoNoDirigido implements IGrafoRedElectrica {

    public TGrafoRedElectrica(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
    }

    @Override
    public TAristas mejorRedElectrica() {
        Collection<Comparable> verticesU = new LinkedList<>();
        Collection<Comparable> verticesV = new LinkedList<>();
        TAristas aristasAAM = new TAristas();

        for (Comparable etiqueta : this.getVertices().keySet()) {
            verticesV.add(etiqueta);
        }

        if (verticesV.isEmpty()) {
            return aristasAAM;
        }

        Comparable primerVertice = verticesV.iterator().next();
        verticesU.add(primerVertice);
        verticesV.remove(primerVertice);

        while (!verticesV.isEmpty()) {
            TArista aristaMinima = getLasAristas().buscarMin(verticesU, verticesV);

            if (aristaMinima != null) {
                aristasAAM.add(aristaMinima);
                verticesU.add(aristaMinima.getEtiquetaDestino());
                verticesV.remove(aristaMinima.getEtiquetaDestino());
            } else {
                break;
            }
        }

        return aristasAAM;
    }
}