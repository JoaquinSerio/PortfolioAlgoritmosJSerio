package tdas;

import interfaces.IArbolTrie;
import java.util.LinkedList;

public class TArbolTrieHashMap implements IArbolTrie {

    private TNodoTrieHashMap raiz;

    @Override
    public void insertar(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrieHashMap();
        }
        raiz.insertar(palabra);
    }

    @Override
    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    @Override
    public int buscar(String palabra) {
        if (raiz != null) {
            return raiz.buscar(palabra);
        }
        return -1;
    }

    @Override
    public LinkedList<String> predecir(String prefijo) {
        LinkedList<String> palabras = new LinkedList<>();
        if (raiz != null) {
            raiz.predecir(prefijo, palabras);
        }
        return palabras;
    }
}