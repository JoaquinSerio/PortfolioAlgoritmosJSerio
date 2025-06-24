
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TNodoTrieHashMap {

    private HashMap<Character, TNodoTrieHashMap> hijos;
    private boolean esPalabra;
    private List<Integer> posiciones;

    public TNodoTrieHashMap() {
        hijos = new HashMap<>();
        esPalabra = false;
        posiciones = new ArrayList<>();
    }

    public void insertar(String palabra) {
        TNodoTrieHashMap nodo = this;
        for (char c : palabra.toCharArray()) {
            if (!nodo.hijos.containsKey(c)) {
                nodo.hijos.put(c, new TNodoTrieHashMap());
            }
            nodo = nodo.hijos.get(c);
        }
        nodo.esPalabra = true;
    }

    public void agregarPosicion(String palabra, int posicion) {
        TNodoTrieHashMap nodo = buscarNodo(palabra);
        if (nodo != null && nodo.esPalabra) {
            if (!nodo.posiciones.contains(posicion)) {
                nodo.posiciones.add(posicion);
            }
        }
    }

    public TNodoTrieHashMap buscarNodo(String s) {
        TNodoTrieHashMap nodo = this;
        for (char c : s.toCharArray()) {
            if (!nodo.hijos.containsKey(c)) {
                return null;
            }
            nodo = nodo.hijos.get(c);
        }
        return nodo;
    }

    public boolean buscar(String palabra) {
        TNodoTrieHashMap nodo = buscarNodo(palabra);
        return nodo != null && nodo.esPalabra;
    }

    public List<String> predecir(String prefijo) {
        List<String> resultado = new ArrayList<>();
        TNodoTrieHashMap nodo = buscarNodo(prefijo);
        if (nodo != null) {
            predecirRecursivo(nodo, prefijo, resultado);
        }
        return resultado;
    }

    private void predecirRecursivo(TNodoTrieHashMap nodo, String palabraActual, List<String> resultado) {
        if (nodo.esPalabra) {
            resultado.add(palabraActual);
        }
        for (char c : nodo.hijos.keySet()) {
            predecirRecursivo(nodo.hijos.get(c), palabraActual + c, resultado);
        }
    }

    public List<Integer> buscarPatron(String patron) {
        TNodoTrieHashMap nodo = buscarNodo(patron);
        if (nodo != null && nodo.esPalabra) {
            return new ArrayList<>(nodo.posiciones);
        }
        return new ArrayList<>();
    }

    public void imprimir() {
        imprimirRecursivo("", this);
    }

    private void imprimirRecursivo(String prefijo, TNodoTrieHashMap nodo) {
        if (nodo.esPalabra) {
            System.out.print(prefijo);
            if (!nodo.posiciones.isEmpty()) {
                System.out.print(" (posiciones: " + nodo.posiciones + ")");
            }
            System.out.println();
        }
        for (char c : nodo.hijos.keySet()) {
            imprimirRecursivo(prefijo + c, nodo.hijos.get(c));
        }
    }
}