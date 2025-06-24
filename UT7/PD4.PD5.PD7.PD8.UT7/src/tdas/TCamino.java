package tdas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class TCamino implements ICamino {

    private final IVertice origen;
    private LinkedList<Comparable> otrosVertices;
    private Double costoTotal = 0.0d;

    public void imprimirEtiquetasConsola() {
        System.out.println(imprimirEtiquetas());
    }

    public TCamino(IVertice v) {
        this.origen = v;
        this.otrosVertices = new LinkedList<>();
    }

    @Override
    public boolean agregarAdyacencia(IAdyacencia adyacenciaActual) {
        if (adyacenciaActual.getDestino() != null) {
            costoTotal = costoTotal + adyacenciaActual.getCosto();
            return getOtrosVertices().add(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(IAdyacencia adyacenciaActual) {
        if (getOtrosVertices().contains(adyacenciaActual.getDestino().getEtiqueta())) {
            costoTotal = costoTotal - adyacenciaActual.getCosto();
            return getOtrosVertices().remove(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    public IVertice getOrigen() {
        return origen;
    }

    public LinkedList<Comparable> getOtrosVertices() {
        return otrosVertices;
    }

    public void setOtrosVertices(LinkedList<Comparable> otrosVertices) {
        this.otrosVertices = otrosVertices;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    @Override
    public TCamino copiar() {
        IVertice origen = new TVertice(this.getOrigen().getEtiqueta());
        TCamino copia = new TCamino(origen);
        origen.getAdyacentes().addAll(this.getOrigen().getAdyacentes());
        copia.getOtrosVertices().addAll(this.getOtrosVertices());
        copia.setCostoTotal(this.costoTotal);
        return copia;
    }

    @Override
    public String imprimirEtiquetas() {
        StringBuilder etiquetas = new StringBuilder();
        etiquetas.append(origen.getEtiqueta());
        for(Comparable vertice: this.otrosVertices){
            etiquetas.append("->").append(vertice.toString());
        }
        etiquetas.append(" (Costo: ").append(costoTotal).append(")");
        return etiquetas.toString();
    }
}