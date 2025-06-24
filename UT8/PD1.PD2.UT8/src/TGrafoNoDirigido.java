import java.util.*;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido {

    protected TAristas lasAristas = new TAristas();

    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, new LinkedList<>());
        lasAristas.insertarAmbosSentidos(aristas);
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        if (tempbool) {
            lasAristas.add(arista);
        }
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        Collection<TVertice> vertices = new LinkedList<>();
        Collection<TArista> aristas = new LinkedList<>();

        for (TVertice v : getVertices().values()) {
            vertices.add(new TVertice(v.getEtiqueta()));
        }

        List<TArista> todasAristas = new ArrayList<>(lasAristas);
        todasAristas.sort(Comparator.comparing(TArista::getCosto));

        UnionFind uf = new UnionFind(getVertices().keySet());

        for (TArista arista : todasAristas) {
            if (aristas.size() >= getVertices().size() - 1) {
                break;
            }

            if (uf.union(arista.getEtiquetaOrigen(), arista.getEtiquetaDestino())) {
                aristas.add(arista);
            }
        }

        return new TGrafoNoDirigido(vertices, aristas);
    }

    public TGrafoNoDirigido KruskalOptimizado() {
        Collection<TVertice> vertices = new LinkedList<>();
        Collection<TArista> aristas = new LinkedList<>();

        for (TVertice v : getVertices().values()) {
            vertices.add(new TVertice(v.getEtiqueta()));
        }

        PriorityQueue<TArista> colaPrioridad = new PriorityQueue<>(Comparator.comparing(TArista::getCosto));
        colaPrioridad.addAll(lasAristas);

        UnionFind uf = new UnionFind(getVertices().keySet());

        while (!colaPrioridad.isEmpty() && aristas.size() < getVertices().size() - 1) {
            TArista arista = colaPrioridad.poll();

            if (uf.union(arista.getEtiquetaOrigen(), arista.getEtiquetaDestino())) {
                aristas.add(arista);
            }
        }

        return new TGrafoNoDirigido(vertices, aristas);
    }

    private static class UnionFind {
        private Map<Comparable, Comparable> padre;
        private Map<Comparable, Integer> rango;

        public UnionFind(Set<Comparable> elementos) {
            padre = new HashMap<>();
            rango = new HashMap<>();
            for (Comparable elemento : elementos) {
                padre.put(elemento, elemento);
                rango.put(elemento, 0);
            }
        }

        public Comparable find(Comparable elemento) {
            if (!padre.get(elemento).equals(elemento)) {
                padre.put(elemento, find(padre.get(elemento)));
            }
            return padre.get(elemento);
        }

        public boolean union(Comparable a, Comparable b) {
            Comparable raizA = find(a);
            Comparable raizB = find(b);

            if (raizA.equals(raizB)) {
                return false;
            }

            if (rango.get(raizA) < rango.get(raizB)) {
                padre.put(raizA, raizB);
            } else if (rango.get(raizA) > rango.get(raizB)) {
                padre.put(raizB, raizA);
            } else {
                padre.put(raizB, raizA);
                rango.put(raizA, rango.get(raizA) + 1);
            }
            return true;
        }
    }

    @Override
    public TGrafoNoDirigido Prim() {
        if (getVertices().isEmpty()) {
            return null;
        }

        Collection<TVertice> vertices = new LinkedList<>();
        Collection<TArista> aristas = new LinkedList<>();

        Set<Comparable> visitados = new HashSet<>();
        PriorityQueue<TArista> colaPrioridad = new PriorityQueue<>(Comparator.comparing(TArista::getCosto));

        Comparable etiquetaInicial = getVertices().keySet().iterator().next();
        visitados.add(etiquetaInicial);
        vertices.add(getVertices().get(etiquetaInicial));

        TVertice verticeActual = getVertices().get(etiquetaInicial);
        for (Object Objady : verticeActual.getAdyacentes()) {
            TAdyacencia ady = (TAdyacencia) Objady;
            colaPrioridad.add(new TArista(etiquetaInicial, ady.getDestino().getEtiqueta(), ady.getCosto()));
        }

        while (!colaPrioridad.isEmpty() && visitados.size() < getVertices().size()) {
            TArista aristaMin = colaPrioridad.poll();

            if (!visitados.contains(aristaMin.getEtiquetaDestino())) {
                visitados.add(aristaMin.getEtiquetaDestino());
                vertices.add(getVertices().get(aristaMin.getEtiquetaDestino()));
                aristas.add(aristaMin);

                TVertice nuevoVertice = getVertices().get(aristaMin.getEtiquetaDestino());
                for (Object Objady : nuevoVertice.getAdyacentes()) {
                    TAdyacencia ady = (TAdyacencia) Objady;
                    if (!visitados.contains(ady.getDestino().getEtiqueta())) {
                        colaPrioridad.add(new TArista(aristaMin.getEtiquetaDestino(), ady.getDestino().getEtiqueta(), ady.getCosto()));
                    }
                }
            }
        }

        return new TGrafoNoDirigido(vertices, aristas);
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        Collection<TVertice> resultado = new LinkedList<>();
        if (!existeVertice(etiquetaOrigen)) {
            return resultado;
        }

        desvisitarVertices();
        Queue<TVertice> cola = new LinkedList<>();
        TVertice verticeOrigen = getVertices().get(etiquetaOrigen);

        cola.add(verticeOrigen);
        verticeOrigen.setVisitado(true);

        while (!cola.isEmpty()) {
            TVertice verticeActual = cola.poll();
            resultado.add(verticeActual);

            for (Object Objady : verticeActual.getAdyacentes()) {
                TAdyacencia ady = (TAdyacencia) Objady;
                TVertice verticeAdy = ady.getDestino();
                if (!verticeAdy.getVisitado()) {
                    verticeAdy.setVisitado(true);
                    cola.add(verticeAdy);
                }
            }
        }

        return resultado;
    }

    @Override
    public Collection<TVertice> bea() {
        Collection<TVertice> resultado = new LinkedList<>();
        desvisitarVertices();

        for (TVertice vertice : getVertices().values()) {
            if (!vertice.getVisitado()) {
                resultado.addAll(bea(vertice.getEtiqueta()));
            }
        }

        return resultado;
    }

    @Override
    public LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        LinkedList<TVertice> puntosArticulacion = new LinkedList<>();
        if (!existeVertice(etOrigen)) {
            return puntosArticulacion;
        }

        Map<Comparable, Integer> descubrimiento = new HashMap<>();
        Map<Comparable, Integer> low = new HashMap<>();
        Set<Comparable> visitado = new HashSet<>();
        Map<Comparable, Comparable> padre = new HashMap<>();
        Set<Comparable> articulaciones = new HashSet<>();

        puntosArticulacionUtil(etOrigen, visitado, descubrimiento, low, padre, articulaciones, 0);

        for (Comparable etiqueta : articulaciones) {
            puntosArticulacion.add(getVertices().get(etiqueta));
        }

        return puntosArticulacion;
    }

    private int puntosArticulacionUtil(Comparable u, Set<Comparable> visitado,
                                       Map<Comparable, Integer> descubrimiento, Map<Comparable, Integer> low,
                                       Map<Comparable, Comparable> padre, Set<Comparable> articulaciones,
                                       int tiempo) {
        int hijos = 0;
        visitado.add(u);

        descubrimiento.put(u, tiempo);
        low.put(u, tiempo);
        tiempo++;

        TVertice verticeU = getVertices().get(u);
        for (Object Objady : verticeU.getAdyacentes()) {
            TAdyacencia ady = (TAdyacencia) Objady;
            Comparable v = ady.getDestino().getEtiqueta();

            if (!visitado.contains(v)) {
                hijos++;
                padre.put(v, u);
                tiempo = puntosArticulacionUtil(v, visitado, descubrimiento, low, padre, articulaciones, tiempo);

                low.put(u, Math.min(low.get(u), low.get(v)));

                if (padre.get(u) == null && hijos > 1) {
                    articulaciones.add(u);
                }

                if (padre.get(u) != null && low.get(v) >= descubrimiento.get(u)) {
                    articulaciones.add(u);
                }
            } else if (!v.equals(padre.get(u))) {
                low.put(u, Math.min(low.get(u), descubrimiento.get(v)));
            }
        }

        return tiempo;
    }

    @Override
    public boolean esConexo() {
        if (getVertices().isEmpty()) {
            return true;
        }

        Comparable etiquetaInicial = getVertices().keySet().iterator().next();
        Collection<TVertice> alcanzables = bea(etiquetaInicial);

        return alcanzables.size() == getVertices().size();
    }
}