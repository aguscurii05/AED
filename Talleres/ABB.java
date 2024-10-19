package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2


public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo raiz;
    private int altura;
    private int cardinal;

    private class Nodo {
        private T val;
        private Nodo izq;
        private Nodo der;
        private Nodo padre;

        public Nodo(T value){
            val=value;
            izq=null;
            der=null;
            padre=null;

        }
        
        

    }

    public ABB() {
        raiz=null;
        altura=0;
        cardinal=0;
    }

    public int cardinal() {
        return cardinal;
    }

    public T minimo(){
        Nodo res=raiz;
        while(raiz.izq!=null){
            res=res.izq;
        }
        return res.val;
    }

    public T maximo(){
        Nodo res=raiz;
        while(raiz.der!=null){
            res=res.der;
        }
        return res.val;
    }

    public void insertar(T elem){
        Nodo res= new Nodo(elem);
        res.izq=null;
        res.der=null;
        Nodo puntero = raiz;
        if (puntero!=null){
            while ((puntero.izq!=null && puntero.val.compareTo(elem)>0) || (puntero.der!=null && puntero.val.compareTo(elem)<0)){
                if (puntero.izq!=null && puntero.val.compareTo(elem)>0){
                    puntero=puntero.izq;
                }
                else if (puntero.der!=null && puntero.val.compareTo(elem)<0){
                    puntero=puntero.der;
                }
            }
            if (puntero.val.compareTo(elem)>0){
                puntero.izq=res;
                cardinal++;
                altura++;
            }
            if (puntero.val.compareTo(elem)<0){
                puntero.der=res;
                cardinal++;
                altura++;
            }
            
        }
        else{
            raiz=res;
            cardinal++;
            altura++;
        }
        
    }
    public boolean pertenece(T elem){
        boolean res=false;
        Nodo puntero= raiz;
        if (puntero!=null){
            while (puntero.val!=elem &&(puntero.izq!=null || puntero.der!=null)){
                if (puntero.izq!=null && puntero.val.compareTo(elem)>0){
                    puntero=puntero.izq;
                }
                else if (puntero.der!=null && puntero.val.compareTo(elem)<0){
                    puntero=puntero.der;
                }
            }
            res =puntero.val==elem;
        }
        return res;
    }

    public void eliminar(T elem){
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString(){
        throw new UnsupportedOperationException("No implementada aun");
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        public boolean haySiguiente() {            
            throw new UnsupportedOperationException("No implementada aun");
        }
    
        public T siguiente() {
            throw new UnsupportedOperationException("No implementada aun");
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
