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
            primero.ant = nuevo;
            primero =nuevo;
        }
        len++;
    }

    public void agregarAtras(T elem) {
        if (primero==null){
            this.agregarAdelante(elem);
        }
        else{
            Nodo nuevo = new Nodo(elem);
            nuevo.ant = ultimo;
            nuevo.sig = null;
            ultimo.sig = nuevo;
            ultimo = nuevo;
            len++;
        }
        
    }

    public T obtener(int i) {
        Nodo puntero = primero;
        for (int j=0;j<i;j++){
            puntero=puntero.sig;
        }
        return puntero.valor;
        }

    public void eliminar(int i) {
        if (len==1 && i==0) {
            primero =null;
            ultimo =null;

        }
        else if (this.obtener(i)==this.primero.valor){
            Nodo newPrimero = primero.sig;
            newPrimero.ant = null;
            primero = newPrimero;
        }
        else if (this.obtener(i)==this.ultimo.valor){
            Nodo newUltimo = ultimo.ant;
            newUltimo.sig = null;
            ultimo = newUltimo;
        }
        else{
            Nodo puntero = primero;
            for (int j=0;j<i;j++){
                puntero= puntero.sig;
            }
            Nodo anterior = puntero.ant;
            Nodo siguiente = puntero.sig;
            anterior.sig=siguiente;
            siguiente.ant=anterior;
        }
        len--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo puntero = primero;
        for (int j=0;j<indice;j++){
            puntero= puntero.sig;
        }
        puntero.valor= elem;

    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        T valorPrimero = lista.primero.valor;
        Nodo copiaPrimero = new Nodo(valorPrimero);
        copiaPrimero.ant=null;
        copiaPrimero.sig=null;
        primero = copiaPrimero;
        ultimo = copiaPrimero;
        len++;
        Nodo puntero = lista.primero;
        for(int j=1;j<lista.len;j++){
            puntero = puntero.sig;
            T valAct = puntero.valor;
            this.agregarAtras(valAct);
            
        }
    }
    
    @Override
    public String toString() {
        Nodo puntero = this.primero;
        String res = "["+puntero.valor;
        for (int j=1;j<this.len;j++){
            puntero=puntero.sig;
            res=res+", "+puntero.valor;
        }
        return res+"]";
    }

    private class ListaIterador implements Iterador<T> {
        private int pos;
        private int largo;

        public ListaIterador() {
            pos = 0;
            largo = len;             
        }

        public boolean haySiguiente() {
            return pos<largo;
        }
        
        public boolean hayAnterior() {
	        return pos>0;
        }

        public T siguiente() {
	        Nodo puntero = primero;
            for (int j=0;j<pos;j++){
                puntero= puntero.sig;
            }
            pos++;
            return puntero.valor;
        }
        

        public T anterior() {
	        Nodo puntero = primero;
            for (int j=0;j<pos-1;j++){
                puntero= puntero.sig;
            }
            pos--;
            return puntero.valor;
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }

}
