
import java.util.List;

public class TTrieHashMap {

    private TNodoTrieHashMap raiz;

    public TTrieHashMap() {
        this.raiz = new TNodoTrieHashMap();
    }

    public void insertar(String palabra) {
        if (palabra != null && !palabra.isEmpty()) {
            raiz.insertar(palabra.toLowerCase());
        }
    }

    public boolean buscar(String palabra) {
        if (palabra == null || palabra.isEmpty()) {
            return false;
        }
        return raiz.buscar(palabra.toLowerCase());
    }

    public List<String> predecir(String prefijo) {
        if (prefijo == null || prefijo.isEmpty()) {
            return raiz.predecir("");
        }
        return raiz.predecir(prefijo.toLowerCase());
    }

    public void insertarPatron(String texto, String patron) {
        patron = patron.toLowerCase();
        texto = texto.toLowerCase();

        for (int i = 0; i <= texto.length() - patron.length(); i++) {
            String substring = texto.substring(i, i + patron.length());
            if (substring.equals(patron)) {
                raiz.insertar(patron);
                raiz.agregarPosicion(patron, i);
            }
        }
    }

    public List<Integer> buscarPatron(String patron) {
        if (patron == null || patron.isEmpty()) {
            return List.of();
        }
        return raiz.buscarPatron(patron.toLowerCase());
    }

    public void imprimir() {
        raiz.imprimir();
    }
}