import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Stack;

public class TGrafoDirigido implements IGrafoDirigido {

    private final Map<Comparable, TVertice> vertices;


    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    @Override
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        if (nombreVertice != null) {
            getVertices().remove(nombreVertice);
            return getVertices().containsKey(nombreVertice);
        }
        return false;
    }

    @Override
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    @Override
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            tempbool = (vertOrigen != null) && (vertDestino != null);
            if (tempbool) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }

        }
        return false;
    }

    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }


    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    @Override
    public void desvisitarVertices() {
        for (TVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    @Override
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Collection<TVertice> bpf(TVertice vertice) {
        Collection<TVertice> resultado = new LinkedList<>();
        if (vertice != null) {
            desvisitarVertices();
            Stack<TVertice> pila = new Stack<>();
            pila.push(vertice);

            while (!pila.isEmpty()) {
                TVertice actual = pila.pop();
                if (!actual.getVisitado()) {
                    actual.setVisitado(true);
                    resultado.add(actual);

                    for (Object objAdy : actual.getAdyacentes()) {
                        TAdyacencia ady = (TAdyacencia) objAdy;
                        if (!ady.getDestino().getVisitado()) {
                            pila.push(ady.getDestino());
                        }
                    }
                }
            }
        }
        return resultado;
    }

    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        TVertice vertice = buscarVertice(etiquetaOrigen);
        if (vertice != null) {
            return bpf(vertice);
        }
        return new LinkedList<>();
    }

    @Override
    public Collection<TVertice> bpf() {
        Collection<TVertice> resultado = new LinkedList<>();
        desvisitarVertices();

        for (TVertice vertice : getVertices().values()) {
            if (!vertice.getVisitado()) {
                resultado.addAll(bpf(vertice));
            }
        }

        return resultado;
    }

    @Override
    public boolean tieneCiclo(TCamino camino) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Comparable centroDelGrafo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Double[][] floyd() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean[][] warshall() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean tieneCiclo(Comparable etiquetaOrigen) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean tieneCiclo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Collection<TVertice> bea() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}