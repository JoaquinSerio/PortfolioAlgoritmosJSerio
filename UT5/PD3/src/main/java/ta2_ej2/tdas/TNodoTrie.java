package main.java.ta2_ej2.tdas;

import main.java.ta2_ej2.interfaces.INodoTrie;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TNodoTrie implements INodoTrie {

    private static final int CANT_CHR_ABECEDARIO = 26;
    private TNodoTrie[] hijos;
    private boolean esPalabra;
    private List<Integer> paginas;

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        esPalabra = false;
        paginas = new ArrayList<>();
    }

    @Override
    public void insertar(String unaPalabra) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            char caracter = unaPalabra.charAt(c);
            if (caracter < 'a' || caracter > 'z') {
                return;
            }
            int indice = caracter - 'a';
            if (nodo.hijos[indice] == null) {
                nodo.hijos[indice] = new TNodoTrie();
            }
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;
    }

    public void agregarPagina(String palabra, int indice, int pagina) {
        if (indice == palabra.length()) {
            if (esPalabra && !paginas.contains(pagina)) {
                paginas.add(pagina);
            }
            return;
        }
        char caracter = palabra.charAt(indice);
        if (caracter < 'a' || caracter > 'z') {
            return;
        }
        int pos = caracter - 'a';
        if (hijos[pos] != null) {
            hijos[pos].agregarPagina(palabra, indice + 1, pagina);
        }
    }

    public void imprimirIndice(String prefijo) {
        if (esPalabra && !paginas.isEmpty()) {
            System.out.print(prefijo + ": ");
            for (Integer pag : paginas) {
                System.out.print(pag + " ");
            }
            System.out.println();
        }
        for (int i = 0; i < 26; i++) {
            if (hijos[i] != null) {
                hijos[i].imprimirIndice(prefijo + (char) (i + 'a'));
            }
        }
    }

    private void imprimir(String s, TNodoTrie nodo) {
        if (nodo != null) {
            if (nodo.esPalabra) {
                System.out.println(s);
            }
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) {
                    imprimir(s+(char)(c + 'a'), nodo.hijos[c]);
                }
            }
        }
    }

    @Override
    public void imprimir() {
        imprimir("", this);
    }

    private TNodoTrie buscarNodo(String s) {
        TNodoTrie nodoActual = this;
        for(int c = 0; c < s.length(); c++){
            char caracter = s.charAt(c);
            if (caracter < 'a' || caracter > 'z') {
                return null;
            }
            int indice = caracter - 'a';
            TNodoTrie unHijo = nodoActual.hijos[indice];
            if(unHijo == null){
                return null;
            }else {
                nodoActual = unHijo;
            }
        }
        return nodoActual;
    }

    @Override
    public int buscar(String s) {
        int contador = 0;
        TNodoTrie nodoActual = this;

        for(int c = 0; c < s.length(); c++){
            char caracter = s.charAt(c);
            if (caracter < 'a' || caracter > 'z') {
                System.out.println("La palabra contiene caracteres no válidos.");
                return 0;
            }
            int indice = caracter - 'a';
            contador++;

            if(nodoActual.hijos[indice] == null){
                System.out.println("No existe - Comparaciones: " + contador);
                return contador;
            }
            nodoActual = nodoActual.hijos[indice];
        }

        if(nodoActual.esPalabra){
            System.out.print("Existe - Comparaciones: " + contador + " - Páginas: ");
            for(Integer pag : nodoActual.paginas){
                System.out.print(pag + " ");
            }
            System.out.println();
        } else {
            System.out.println("No existe - Comparaciones: " + contador);
        }
        return contador;
    }

    @Override
    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrie nodo = buscarNodo(prefijo);
        if (nodo != null) {
            predecirRecursivo(nodo, prefijo, palabras);
        }
    }

    private void predecirRecursivo(TNodoTrie nodo, String palabraActual, LinkedList<String> palabras) {
        if (nodo.esPalabra) {
            palabras.add(palabraActual);
        }

        for (int i = 0; i < CANT_CHR_ABECEDARIO; i++) {
            if (nodo.hijos[i] != null) {
                predecirRecursivo(nodo.hijos[i], palabraActual + (char)(i + 'a'), palabras);
            }
        }
    }
}