package tdas;

import interfaces.INodoTrie;

import java.util.HashMap;
import java.util.LinkedList;

public class TNodoTrieHashMap implements INodoTrie {

    private HashMap<Character, TNodoTrieHashMap> hijos;
    private boolean esPalabra;
    private int comparaciones = 0;

    public TNodoTrieHashMap() {
        hijos = new HashMap<>();
        esPalabra = false;
    }

    @Override
    public void insertar(String unaPalabra) {
        TNodoTrieHashMap nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            char caracter = unaPalabra.charAt(c);
            if (!nodo.hijos.containsKey(caracter)) {
                nodo.hijos.put(caracter, new TNodoTrieHashMap());
            }
            nodo = nodo.hijos.get(caracter);
        }
        nodo.esPalabra = true;
    }

    private void imprimir(String s, TNodoTrieHashMap nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
            }
            for (Character c : nodo.hijos.keySet()) {
                imprimir(s + c, nodo.hijos.get(c));
            }
        }
    }

    @Override
    public void imprimir() {
        imprimir("", this);
    }

    private TNodoTrieHashMap buscarNodoTrie(String s) {
        TNodoTrieHashMap nodo = this;
        comparaciones = 0;
        for (int c = 0; c < s.length(); c++) {
            char caracter = s.charAt(c);
            comparaciones++;
            if (!nodo.hijos.containsKey(caracter)) {
                return null;
            }
            nodo = nodo.hijos.get(caracter);
        }
        return nodo;
    }

    private void predecir(String s, LinkedList<String> palabras, TNodoTrieHashMap nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                palabras.add(s);
            }
            for (Character c : nodo.hijos.keySet()) {
                predecir(s + c, palabras, nodo.hijos.get(c));
            }
        }
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrieHashMap nodo = buscarNodoTrie(prefijo);
        if (nodo != null) {
            predecir(prefijo, palabras, nodo);
        }
    }

    @Override
    public int buscar(String s) {
        TNodoTrieHashMap nodo = buscarNodoTrie(s);
        if (nodo != null && nodo.esPalabra) {
            return comparaciones;
        }
        return -1;
    }
}