package tdas;

import java.util.*;

public class TVertice<T> implements IVertice {

    private final Comparable etiqueta;
    private LinkedList<IAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private int tiempoInicio;
    private int tiempoFin;

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
        tiempoInicio = 0;
        tiempoFin = 0;
    }

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    public LinkedList<IAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    public int getTiempoInicio() {
        return tiempoInicio;
    }

    public int getTiempoFin() {
        return tiempoFin;
    }

    public int bpfConTiempos(int tiempo) {
        this.visitado = true;
        this.tiempoInicio = ++tiempo;

        for (IAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice destino = (TVertice) adyacencia.getDestino();
            if (!destino.getVisitado()) {
                tiempo = destino.bpfConTiempos(tiempo);
            }
        }

        this.tiempoFin = ++tiempo;
        return tiempo;
    }

    public void clasificarArcos(List<IArista> arcosArbol,
                                List<IArista> arcosRetroceso,
                                List<IArista> arcosAvance,
                                List<IArista> arcosCruzados) {
        this.setVisitado(true);

        for (IAdyacencia adyacencia : this.getAdyacentes()) {
            TVertice destino = (TVertice) adyacencia.getDestino();
            IArista arista = new TArista(this.getEtiqueta(), destino.getEtiqueta(), adyacencia.getCosto());

            if (destino.getTiempoInicio() > this.getTiempoInicio() &&
                    destino.getTiempoFin() < this.getTiempoFin()) {
                arcosArbol.add(arista);
            } else if (destino.getTiempoInicio() < this.getTiempoInicio() &&
                    destino.getTiempoFin() > this.getTiempoFin()) {
                arcosRetroceso.add(arista);
            } else if (destino.getTiempoInicio() > this.getTiempoInicio() &&
                    destino.getTiempoFin() > this.getTiempoFin()) {
                arcosAvance.add(arista);
            } else {
                arcosCruzados.add(arista);
            }

            if (!destino.getVisitado()) {
                destino.clasificarArcos(arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);
            }
        }
    }

    @Override
    public void bpf(Collection<IVertice> visitados) {
        this.visitado = true;
        visitados.add(this);

        for(IAdyacencia adyacencia: this.getAdyacentes()){
            IVertice destino = adyacencia.getDestino();
            if(!destino.getVisitado()){
                destino.bpf(visitados);
            }
        }
    }



    @Override
    public IAdyacencia buscarAdyacencia(IVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public IAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (IAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(IVertice verticeDestino) {
        IAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, IVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        IAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public IVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    public IVertice siguienteAdyacente(IVertice w) {
        IAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaVertDest, TCamino caminoPrevio, TCaminos losCaminos){
        this.setVisitado(true);
        for(IAdyacencia adyacencia: this.getAdyacentes()){
            IVertice destino = adyacencia.getDestino();
            if(!destino.getVisitado()){
                TCamino copia = caminoPrevio.copiar();
                copia.agregarAdyacencia(adyacencia);
                if(destino.getEtiqueta().compareTo(etiquetaVertDest) == 0){
                    losCaminos.getCaminos().add(copia);
                }else {
                    destino.todosLosCaminos(etiquetaVertDest, copia, losCaminos);
                }
            }
        }
        this.setVisitado(false);
        return losCaminos;
    }

    public boolean tieneCiclo(TCamino unCamino) {
        this.setVisitado(true);

        for (IAdyacencia adyacencia : this.adyacentes) {
            IVertice destino = adyacencia.getDestino();

            if (unCamino.getOtrosVertices().contains(destino.getEtiqueta()) ||
                    destino.getEtiqueta().equals(unCamino.getOrigen().getEtiqueta())) {
                this.setVisitado(false);
                return true;
            }

            if (!destino.getVisitado()) {
                TCamino nuevoCamino = unCamino.copiar();
                nuevoCamino.agregarAdyacencia(adyacencia);

                if (destino.tieneCiclo(nuevoCamino)) {
                    this.setVisitado(false);
                    return true;
                }
            }
        }

        this.setVisitado(false);
        return false;
    }

    public void ordenTopologico(LinkedList<Comparable> resultado, Set<Comparable> visitados) {
        if (!visitados.contains(this.getEtiqueta())) {
            visitados.add(this.getEtiqueta());
            this.setVisitado(true);

            for (IAdyacencia ady : this.getAdyacentes()) {
                IVertice destino = ady.getDestino();
                if (!visitados.contains(destino.getEtiqueta())) {
                    ((TVertice) destino).ordenTopologico(resultado, visitados);
                }
            }

            resultado.addFirst(this.getEtiqueta());
        }
    }

    public void bpfConOrdenFinalizacion(Stack<Comparable> ordenFinalizacion) {
        this.setVisitado(true);

        for (IAdyacencia adyacencia : this.getAdyacentes()) {
            IVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                ((TVertice) destino).bpfConOrdenFinalizacion(ordenFinalizacion);
            }
        }

        ordenFinalizacion.push(this.getEtiqueta());
    }

    public void ordenParcialRec(LinkedList<Comparable> orden) {
        if (!visitado) {
            visitado = true;
            for (IAdyacencia adyacencia : adyacentes) {
                IVertice destino = adyacencia.getDestino();
                ((TVertice) destino).ordenParcialRec(orden);
            }
            orden.addFirst(etiqueta);
        }
    }

    public T getDatos() {
        return datos;
    }
}