package tdas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ernesto
 */
public interface IArista {

    Double getCosto();

    Comparable getEtiquetaDestino();

    Comparable getEtiquetaOrigen();


    void setCosto(Double costo);

    void setEtiquetaDestino(Comparable etiquetaDestino);

    void setEtiquetaOrigen(Comparable etiquetaOrigen);
    
}
