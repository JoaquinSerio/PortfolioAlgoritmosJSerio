package tdas;

import java.util.Objects;

public class THash<K, V> implements IHash<K, V> {
    private int tamanio;
    private Object[] claves;
    private Object[] valores;
    private boolean[] ocupado;
    private int elementos;

    public THash(int tamanioArray){
        this.tamanio = tamanioArray;
        this.claves = new Object[tamanioArray];
        this.valores = new Object[tamanioArray];
        this.ocupado = new boolean[tamanioArray];
        this.elementos = 0;
    }

    @Override
    public int buscar(K unaClave) {
        int i = 0;
        int comp = 0;
        int j;

        while (i < tamanio) {
            j = (funcionHashing(unaClave) + i) % tamanio;
            comp++;

            if (!ocupado[j]) {
                return comp;
            }

            if (Objects.equals(claves[j], unaClave)) {
                return comp;
            }

            i++;
        }

        return comp;
    }

    @Override
    public int insertar(K clave, V unValor) {
        if (elementos >= tamanio) {
            return -1;
        }

        int i = 0;
        int comp = 0;
        int j;

        do {
            j = (funcionHashing(clave) + i) % tamanio;
            comp++;

            if (!ocupado[j]) {
                claves[j] = clave;
                valores[j] = unValor;
                ocupado[j] = true;
                elementos++;
                return comp;
            }

            if (Objects.equals(claves[j], clave)) {
                valores[j] = unValor;
                return comp;
            }

            i++;
        } while (i < tamanio);

        return comp;
    }

    protected int funcionHashing(K unaClave) {
        return Math.abs(unaClave.hashCode()) % tamanio;
    }

    public double getFactorCarga() {
        return (double) elementos / tamanio;
    }

    public int getElementos() {
        return elementos;
    }

    public int getTamanio() {
        return tamanio;
    }

    public boolean contiene(K clave) {
        int i = 0;
        int j;

        while (i < tamanio) {
            j = (funcionHashing(clave) + i) % tamanio;

            if (!ocupado[j]) {
                return false;
            }

            if (Objects.equals(claves[j], clave)) {
                return true;
            }

            i++;
        }

        return false;
    }
}