package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados

    private class Nodo {
        // Completar
    }

    public ListaEnlazada() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public int longitud() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void agregarAdelante(T elem) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void agregarAtras(T elem) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public T obtener(int i) {
        throw new UnsupportedOperationException("No implementada aun");
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
