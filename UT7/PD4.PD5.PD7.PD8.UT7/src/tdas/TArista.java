package tdas;

public class TArista implements IArista {
    private Comparable etiquetaOrigen;
    private Comparable etiquetaDestino;
    private Double costo;

    public TArista(Comparable etiquetaOrigen, Comparable etiquetaDestino, Double costo) {
        this.etiquetaOrigen = etiquetaOrigen;
        this.etiquetaDestino = etiquetaDestino;
        this.costo = costo;
    }

    @Override
    public Comparable getEtiquetaOrigen() {
        return etiquetaOrigen;
    }

    @Override
    public void setCosto(Double costo) {

    }

    @Override
    public void setEtiquetaDestino(Comparable etiquetaDestino) {

    }

    @Override
    public void setEtiquetaOrigen(Comparable etiquetaOrigen) {

    }

    @Override
    public Comparable getEtiquetaDestino() {
        return etiquetaDestino;
    }

    @Override
    public Double getCosto() {
        return costo;
    }

    @Override
    public String toString() {
        return etiquetaOrigen + " -> " + etiquetaDestino + " (" + costo + ")";
    }
}