package tdas;
import interfaces.IGrafoDirigido;

import java.util.*;

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, TVertice> vertices;

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
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

    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Double[][] floyd() {
        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(this.vertices);
        int n = matriz.length;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (matriz[i][k] != Double.MAX_VALUE && matriz[k][j] != Double.MAX_VALUE) {
                        if (matriz[i][k] + matriz[k][j] < matriz[i][j]) {
                            matriz[i][j] = matriz[i][k] + matriz[k][j];
                        }
                    }
                }
            }
        }
        return matriz;
    }

    @Override
    public boolean[][] warshall() {
        boolean[][] matriz = new boolean[vertices.size()][vertices.size()];
        Object[] etiquetas = getEtiquetasOrdenado();

        for (int i = 0; i < etiquetas.length; i++) {
            for (int j = 0; j < etiquetas.length; j++) {
                matriz[i][j] = existeArista((Comparable) etiquetas[i], (Comparable) etiquetas[j]);
            }
        }

        for (int k = 0; k < matriz.length; k++) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    matriz[i][j] = matriz[i][j] || (matriz[i][k] && matriz[k][j]);
                }
            }
        }
        return matriz;
    }

    public boolean existeConexion(Comparable origen, Comparable destino) {
        boolean[][] matrizWarshall = warshall();
        Object[] etiquetas = getEtiquetasOrdenado();

        int indiceOrigen = -1, indiceDestino = -1;
        for (int i = 0; i < etiquetas.length; i++) {
            if (etiquetas[i].equals(origen)) indiceOrigen = i;
            if (etiquetas[i].equals(destino)) indiceDestino = i;
        }

        if (indiceOrigen != -1 && indiceDestino != -1) {
            return matrizWarshall[indiceOrigen][indiceDestino];
        }
        return false;
    }

    public void bpf(Comparable etiquetaOrigen) {
        TVertice vertice = buscarVertice(etiquetaOrigen);
        if (vertice != null) {
            limpiarVisitados();
            bpfRecursivo(vertice);
        }
    }

    private void bpfRecursivo(TVertice vertice) {
        vertice.setVisitado(true);
        System.out.println(vertice.getEtiqueta());

        for (Object Objadyacencia : vertice.getAdyacentes()) {
            TAdyacencia adyacencia = (TAdyacencia) Objadyacencia;
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                bpfRecursivo(destino);
            }
        }
    }

    public void bpfCompleto() {
        limpiarVisitados();
        for (TVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                bpfRecursivo(vertice);
            }
        }
    }

    private void limpiarVisitados() {
        for (TVertice vertice : vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    public List<List<Comparable>> obtenerTodosLosCaminos(Comparable origen, Comparable destino) {
        List<List<Comparable>> caminos = new ArrayList<>();
        List<Comparable> caminoActual = new ArrayList<>();
        limpiarVisitados();

        TVertice verticeOrigen = buscarVertice(origen);
        if (verticeOrigen != null) {
            buscarCaminosRecursivo(verticeOrigen, destino, caminoActual, caminos);
        }
        return caminos;
    }

    private void buscarCaminosRecursivo(TVertice actual, Comparable destino,
                                        List<Comparable> caminoActual,
                                        List<List<Comparable>> caminos) {
        caminoActual.add(actual.getEtiqueta());
        actual.setVisitado(true);

        if (actual.getEtiqueta().equals(destino)) {
            caminos.add(new ArrayList<>(caminoActual));
        } else {
            for (Object objAdyacencia : actual.getAdyacentes()) {
                TAdyacencia adyacencia = (TAdyacencia) objAdyacencia;
                TVertice siguiente = adyacencia.getDestino();
                if (!siguiente.getVisitado()) {
                    buscarCaminosRecursivo(siguiente, destino, caminoActual, caminos);
                }
            }
        }

        caminoActual.remove(caminoActual.size() - 1);
        actual.setVisitado(false);
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Double[][] matrizFloyd = floyd();
        Object[] etiquetas = getEtiquetasOrdenado();
        int indiceVertice = -1;

        for (int i = 0; i < etiquetas.length; i++) {
            if (etiquetas[i].equals(etiquetaVertice)) {
                indiceVertice = i;
                break;
            }
        }

        if (indiceVertice == -1) {
            return -1;
        }

        double maxDistancia = 0;
        for (int j = 0; j < matrizFloyd[indiceVertice].length; j++) {
            if (matrizFloyd[indiceVertice][j] == Double.MAX_VALUE) {
                return -1;
            }
            if (matrizFloyd[indiceVertice][j] > maxDistancia) {
                maxDistancia = matrizFloyd[indiceVertice][j];
            }
        }

        return (int) maxDistancia;
    }

    @Override
    public Comparable centroDelGrafo() {
        Object[] etiquetas = getEtiquetasOrdenado();
        Comparable centro = null;
        int menorExcentricidad = Integer.MAX_VALUE;

        for (Object etiqueta : etiquetas) {
            Comparable excentricidad = obtenerExcentricidad((Comparable) etiqueta);
            if (excentricidad instanceof Integer) {
                int exc = (Integer) excentricidad;
                if (exc != -1 && exc < menorExcentricidad) {
                    menorExcentricidad = exc;
                    centro = (Comparable) etiqueta;
                }
            }
        }

        return centro;
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}