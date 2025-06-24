package tdas;

import java.util.*;

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, IVertice> vertices;

    public TGrafoDirigido(Collection<IVertice> vertices, Collection<IArista> aristas) {
        this.vertices = new HashMap<>();
        for (IVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (IArista arista : aristas) {
            insertarArista(arista);
        }
    }

    @Override
    public LinkedList<IVertice> bpf() {
        LinkedList<IVertice> list = new LinkedList<>();

        for (IVertice vertice: vertices.values()){
            if(!vertice.getVisitado()){
                vertice.bpf(list);
            }
        }
        return list;
    }

    @Override
    public LinkedList<IVertice> bpf(IVertice vertice) {
        LinkedList<IVertice> list = new LinkedList<>();
        if(!vertice.getVisitado()){
            vertice.bpf(list);
        }
        return list;
    }

    @Override
    public LinkedList<IVertice> bpf(Comparable etiquetaOrigen) {
        LinkedList<IVertice> list = new LinkedList<>();
        IVertice vertice = buscarVertice(etiquetaOrigen);
        if(vertice != null && !vertice.getVisitado()){
            vertice.bpf(list);
        }
        return list;
    }

    public void bpfConTiempos() {
        desvisitarVertices();
        int tiempo = 0;
        for (IVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                tiempo = ((TVertice) vertice).bpfConTiempos(tiempo);
            }
        }
    }

    public void clasificarArcos(Comparable etiquetaOrigen,
                                List<IArista> arcosArbol,
                                List<IArista> arcosRetroceso,
                                List<IArista> arcosAvance,
                                List<IArista> arcosCruzados) {

        bpfConTiempos();

        for (IVertice vertice : vertices.values()) {
            TVertice v = (TVertice) vertice;

            for (Object Objadyacencia : v.getAdyacentes()) {
                TAdyacencia adyacencia = (TAdyacencia) Objadyacencia;
                TVertice destino = (TVertice) adyacencia.getDestino();
                IArista arista = new TArista(v.getEtiqueta(), destino.getEtiqueta(), adyacencia.getCosto());

                if (destino.getTiempoInicio() > v.getTiempoInicio() &&
                        destino.getTiempoFin() < v.getTiempoFin()) {
                    arcosArbol.add(arista);
                } else if (destino.getTiempoInicio() <= v.getTiempoInicio() &&
                        destino.getTiempoFin() >= v.getTiempoFin()) {
                    arcosRetroceso.add(arista);
                } else if (destino.getTiempoInicio() > v.getTiempoInicio() &&
                        destino.getTiempoFin() > v.getTiempoFin()) {
                    arcosAvance.add(arista);
                } else {
                    arcosCruzados.add(arista);
                }
            }
        }

        desvisitarVertices();
    }

    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            IVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        IVertice vertOrigen = buscarVertice(etiquetaOrigen);
        IVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    private IVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    public boolean insertarArista(IArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            IVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            IVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            IVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override
    public boolean insertarVertice(IVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, IVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    public Map<Comparable, IVertice> getVertices() {
        return vertices;
    }

    @Override
    public void desvisitarVertices() {
        for (IVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    public Boolean tieneCiclo(){
        desvisitarVertices();
        for (IVertice vertice : this.vertices.values()) {
            if (!vertice.getVisitado()) {
                TCamino camino = new TCamino(vertice);
                if (((TVertice) vertice).tieneCiclo(camino)) {
                    desvisitarVertices();
                    return true;
                }
            }
        }
        desvisitarVertices();
        return false;
    }

    public LinkedList<Comparable> ordenTopologico() {
        if (tieneCiclo()) {
            return null;
        }

        LinkedList<Comparable> resultado = new LinkedList<>();
        Set<Comparable> visitados = new HashSet<>();
        desvisitarVertices();

        for (IVertice vertice : this.getVertices().values()) {
            if (!visitados.contains(vertice.getEtiqueta())) {
                ((TVertice) vertice).ordenTopologico(resultado, visitados);
            }
        }

        desvisitarVertices();
        return resultado;
    }

    public LinkedList<Comparable> ordenParcial() {
        LinkedList<Comparable> orden = new LinkedList<>();

        desvisitarVertices();

        for (IVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                ((TVertice) vertice).ordenParcialRec(orden);
            }
        }

        return orden;
    }

    public void listarTareas(LinkedList<Comparable> orden) {
        System.out.println("Orden de ejecución de tareas.txt:");
        for (Comparable tarea : orden) {
            System.out.println(tarea);
        }
    }

    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino){
        IVertice origen = buscarVertice(etiquetaOrigen);
        if(origen == null) {
            return null;
        }

        TCamino camino = new TCamino(origen);
        TCaminos caminos = new TCaminos();

        origen.todosLosCaminos(etiquetaDestino, camino, caminos);

        return caminos;
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean esConexo() {
        if (vertices.isEmpty()) {
            return true;
        }

        desvisitarVertices();
        IVertice primerVertice = vertices.values().iterator().next();
        LinkedList<IVertice> alcanzables = new LinkedList<>();
        primerVertice.bpf(alcanzables);

        if (alcanzables.size() != vertices.size()) {
            desvisitarVertices();
            return false;
        }

        TGrafoDirigido grafoTranspuesto = obtenerGrafoTranspuesto();

        grafoTranspuesto.desvisitarVertices();
        IVertice verticeTranspuesto = grafoTranspuesto.buscarVertice(primerVertice.getEtiqueta());
        LinkedList<IVertice> alcanzablesTranspuesto = new LinkedList<>();
        verticeTranspuesto.bpf(alcanzablesTranspuesto);

        boolean resultado = alcanzablesTranspuesto.size() == vertices.size();

        desvisitarVertices();
        grafoTranspuesto.desvisitarVertices();

        return resultado;
    }

    private TGrafoDirigido obtenerGrafoTranspuesto() {
        Collection<IVertice> verticesTranspuesto = new ArrayList<>();
        Collection<IArista> aristasTranspuesto = new ArrayList<>();

        for (IVertice vertice : vertices.values()) {
            verticesTranspuesto.add(new TVertice(vertice.getEtiqueta()));
        }

        for (IVertice vertice : vertices.values()) {
            for (IAdyacencia adyacencia : vertice.getAdyacentes()) {
                aristasTranspuesto.add(new TArista(
                        adyacencia.getDestino().getEtiqueta(),
                        vertice.getEtiqueta(),
                        adyacencia.getCosto()
                ));
            }
        }

        return new TGrafoDirigido(verticesTranspuesto, aristasTranspuesto);
    }

    public Collection<Collection<Comparable>> obtenerComponentesFuertes() {
        Collection<Collection<Comparable>> componentes = new ArrayList<>();

        desvisitarVertices();
        Stack<Comparable> ordenFinalizacion = new Stack<>();

        for (IVertice vertice : vertices.values()) {
            if (!vertice.getVisitado()) {
                ((TVertice) vertice).bpfConOrdenFinalizacion(ordenFinalizacion);
            }
        }

        TGrafoDirigido grafoTranspuesto = obtenerGrafoTranspuesto();
        grafoTranspuesto.desvisitarVertices();

        while (!ordenFinalizacion.isEmpty()) {
            Comparable etiqueta = ordenFinalizacion.pop();
            IVertice vertice = grafoTranspuesto.buscarVertice(etiqueta);

            if (!vertice.getVisitado()) {
                LinkedList<IVertice> componente = new LinkedList<>();
                vertice.bpf(componente);

                Collection<Comparable> componenteEtiquetas = new ArrayList<>();
                for (IVertice v : componente) {
                    componenteEtiquetas.add(v.getEtiqueta());
                }
                componentes.add(componenteEtiquetas);
            }
        }

        desvisitarVertices();
        grafoTranspuesto.desvisitarVertices();

        return componentes;
    }
}