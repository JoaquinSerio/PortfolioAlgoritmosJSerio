
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoRedElectrica   {
protected TAristas lasAristas = new TAristas() ;
       /**
     *
     * @param vertices
     * @param aristas
     */
    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
       super(vertices, aristas);     
      lasAristas.insertarAmbosSentidos(aristas);
       
    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }
public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TAristas mejorRedElectrica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public boolean conectados(TVertice v, TVertice w) {
        if (v == null || w == null) {
            return false;
        }

        if (v.getEtiqueta().equals(w.getEtiqueta())) {
            return true;
        }

        desvisitarVertices();
        return dfs(v, w);
    }
    private boolean dfs(TVertice actual, TVertice objetivo) {
        actual.setVisitado(true);

        if (actual.getEtiqueta().equals(objetivo.getEtiqueta())) {
            return true;
        }

        for (Object Objady : actual.getAdyacentes()) {
            TAdyacencia ady = (TAdyacencia) Objady;
            TVertice vecino = ady.getDestino();
            if (!vecino.getVisitado()) {
                if (dfs(vecino, objetivo)) {
                    return true;
                }
            }
        }

        return false;
    }


}
