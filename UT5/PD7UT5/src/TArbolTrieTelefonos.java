import java.util.Collections;
import java.util.LinkedList;

public class TArbolTrieTelefonos implements IArbolTrieTelefonos {

    private TNodoTrieTelefonos raiz;

    public TArbolTrieTelefonos() {
        raiz = new TNodoTrieTelefonos();
    }

    @Override
    public LinkedList<TAbonado> buscarTelefonos(String pais, String area) {
        LinkedList<TAbonado> abonados = new LinkedList<>();
        if (raiz != null) {
            String prefijo = pais + area;
            raiz.buscarTelefonos(prefijo, abonados);
            Collections.sort(abonados);
        }
        return abonados;
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        if (raiz != null) {
            raiz.insertar(unAbonado);
        }
    }
}