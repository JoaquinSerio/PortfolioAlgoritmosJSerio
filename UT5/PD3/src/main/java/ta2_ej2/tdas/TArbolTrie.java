package main.java.ta2_ej2.tdas;

import main.java.ta2_ej2.interfaces.IArbolTrie;

import java.util.LinkedList;

public class TArbolTrie implements IArbolTrie {

    private TNodoTrie raiz;

    public TArbolTrie() {
        this.raiz = new TNodoTrie();
    }

    @Override
    public void insertar(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        String palabraLimpia = limpiarPalabra(palabra);
        if (!palabraLimpia.isEmpty()) {
            raiz.insertar(palabraLimpia);
        }
    }

    public void agregarPagina(String palabra, int pagina) {
        String palabraLimpia = limpiarPalabra(palabra);
        if (!palabraLimpia.isEmpty()) {
            raiz.agregarPagina(palabraLimpia, 0, pagina);
        }
    }

    public void imprimirIndice() {
        raiz.imprimirIndice("");
    }

    @Override
    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    @Override
    public int buscar(String palabra) {
        if(raiz == null){
            return 0;
        }
        else {
            String palabraLimpia = limpiarPalabra(palabra);
            return raiz.buscar(palabraLimpia);
        }
    }

    @Override
    public LinkedList<String> predecir(String prefijo) {
        LinkedList<String> unaLista = new LinkedList<>();
        if(raiz != null){
            String prefijoLimpio = limpiarPalabra(prefijo);
            raiz.predecir(prefijoLimpio, unaLista);
        }
        return unaLista;
    }

    private String limpiarPalabra(String palabra) {
        return palabra.replaceAll("[^a-zA-Z]", "").toLowerCase();
    }
}