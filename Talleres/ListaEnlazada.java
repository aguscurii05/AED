package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private int len ;
    private Nodo primero;
    private Nodo ultimo;

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;
        Nodo(T v){valor=v;}
    }

    public ListaEnlazada() {
        len = 0;
        primero = null;
        ultimo = null;
    }

    public int longitud() {
        return len;
    }

    public void agregarAdelante(T elem) {
        if (primero== null) {
            Nodo nuevo = new Nodo(elem);
            nuevo.ant= null;
            nuevo.sig = null;
            primero = nuevo;
            ultimo = nuevo;
        }
        else{
            Nodo nuevo = new Nodo(elem);
            nuevo.ant= null;
            nuevo.sig= primero;
            primero =nuevo;
        }
        len++;
    }

    public void agregarAtras(T elem) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public T obtener(int i) {
        Nodo puntero = primero;
        for (int j=0;j<i;j++){
            puntero=puntero.sig;
        }
        return puntero.valor;
        }

    public void eliminar(int i) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void modificarPosicion(int indice, T elem) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        throw new UnsupportedOperationException("No implementada aun");
    }
    
    @Override
    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ListaIterador implements Iterador<T> {
    	// Completar atributos privados

        public boolean haySiguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        
        public boolean hayAnterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }

        public T siguiente() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
        

        public T anterior() {
	        throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
	    throw new UnsupportedOperationException("No implementada aun");
    }

}
