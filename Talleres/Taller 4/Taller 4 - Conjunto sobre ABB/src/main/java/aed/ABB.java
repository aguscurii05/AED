package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2


public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    private Nodo raiz;
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
        cardinal=0;
    }

    public int cardinal() {
        return cardinal;
    }

    public T minimo(){
        Nodo res=raiz;
        while(res.izq!=null){
            res=res.izq;
        }
        return res.val;
    }

    public T maximo(){
        Nodo res=raiz;
        while(res.der!=null){
            res=res.der;
        }
        return res.val;
    }

    public void insertar(T elem){
        Nodo res= new Nodo(elem);
        res.izq=null;
        res.der=null;
        res.padre=null;
        Nodo puntero = raiz;
        if (puntero!=null){
            while ((puntero.izq!=null && puntero.val.compareTo(elem)>0) || (puntero.der!=null && puntero.val.compareTo(elem)<0)){
                if (puntero.val.compareTo(elem)>0){
                    puntero=puntero.izq;
                }
                else if (puntero.val.compareTo(elem)<0){
                    puntero=puntero.der;
                }
            }
            if (puntero.val.compareTo(elem)>0){
                puntero.izq=res;
                res.padre=puntero;
                cardinal++;
            }
            if (puntero.val.compareTo(elem)<0){
                puntero.der=res;
                res.padre=puntero;
                cardinal++;
            }
            
        }
        else{
            raiz=res;
            cardinal++;
        }
        
    }
    public boolean pertenece(T elem){
        boolean res=false;
        Nodo puntero= raiz;
        if (puntero!=null){
            while (!res){
                if (puntero.izq!=null && puntero.val.compareTo(elem)>0){
                    puntero=puntero.izq;
                }
                else if (puntero.der!=null && puntero.val.compareTo(elem)<0){
                    puntero=puntero.der;
                }
                else{res=true;}
            }
            if (puntero.val.compareTo(elem)==0){
                res=true;
            }
            else{
                res=false;
            }
        }
        return res;
    }
    public Nodo buscar(T elem){
        Nodo puntero = raiz;
        while(elem.compareTo(puntero.val)!=0){
            if (elem.compareTo(puntero.val)>0){
                puntero=puntero.der;
            }
            else{
                puntero=puntero.izq;
            }
        }
        return puntero;
    }

    public Nodo sucesor(Nodo nodo){
        Nodo res;
        if (nodo.der != null){
            res = nodo.der;
            while (res.izq != null){
                res = res.izq;
            }
        }
        else {
            res = nodo.padre;
            Nodo hijo = nodo;
            while (res.der.val == nodo.val) {
                hijo = res;
                res = res.padre;
            }
        }
        return res;
    }
    public void eliminar(T elem){
        if (this.pertenece(elem)){
            
            Nodo puntero = this.buscar(elem);
            Nodo papa=puntero.padre;
            if(puntero.der==null &&puntero.izq==null){
                if(papa==null){
                    raiz=null;
                }
                else if(papa.val.compareTo(elem)>0){
                    papa.izq=null;
                }
                else if(papa.val.compareTo(elem)<0){
                    papa.der=null;
                }
            }
            else if (puntero.izq!=null && puntero.der==null) {
                if (puntero.padre==null){
                    raiz=puntero.izq;
                    
                }
                else if (papa.val.compareTo(elem)>0){
                    papa.izq=puntero.izq;
                    puntero.izq.padre=papa;
                }
                else if (papa.val.compareTo(elem)<0){
                    papa.der=puntero.izq;
                    puntero.izq.padre=papa;
                }
            }
            else if (puntero.izq==null && puntero.der!=null) {
                if (puntero.padre==null){
                    raiz=puntero.der;
                }
                else if (papa.val.compareTo(elem)>0){
                    papa.izq=puntero.der;
                    puntero.der.padre=papa;
                }
                else if (papa.val.compareTo(elem)<0){
                    papa.der=puntero.der;
                    puntero.der.padre=papa;
                }
            }
            else if (elem.compareTo(raiz.val)==0){
                
                Nodo sucesor= this.sucesor(puntero);
                T nuevoValor= sucesor.val;
                this.eliminar(sucesor.val);
                cardinal++;
                raiz.val=nuevoValor;

            }
            else{
                Nodo sucesor= this.sucesor(puntero);
                T nuevoValor= sucesor.val;
                this.eliminar(sucesor.val);
                cardinal++;
                puntero.val=nuevoValor;
            }
            cardinal=cardinal-1;
            if (raiz!=null &&raiz.padre!=null){
                raiz.padre=null;
            }
            
        }


        
    }

    public String toString(){
        String res = "{";
        if(cardinal!=0){
            Iterador<T> iterador = this.iterador();
            res=res+iterador.siguiente();
            int i=1;
            while (i<cardinal){
                res = res+","+iterador.siguiente();
                i++;
            }
        }
        return res+"}";
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo puntero;
        private Nodo maximo;
        //private Nodo actual;

        public ABB_Iterador(){
            Nodo res=raiz;
            while(res.izq!=null){
                res=res.izq;
            }
            puntero = res;
            res=raiz;
            while(res.der!=null){
                res=res.der;
            }
            maximo=res;
            
        }

        public boolean haySiguiente() {
            return puntero.val.compareTo(maximo.val)!=0;
        }
    
        public T siguiente() {
            T res = puntero.val;
            if (this.haySiguiente()){
                Nodo actual = new Nodo(puntero.val);
                actual.izq=puntero.izq;
                actual.der=puntero.der;
                actual.padre=puntero.padre;
                while (maximo.val.compareTo(puntero.val)!=0 && puntero.val.compareTo(actual.val)>=0 || (actual.izq!=null && actual.izq.val.compareTo(puntero.val)>0)){
                    if (actual.izq!= null && puntero.val.compareTo(actual.izq.val)<0){
                        actual=actual.izq;
                    }
                    else if (actual.der!=null && actual.der.val.compareTo(puntero.val)>0){
                        actual=actual.der;
                    }
                    else if (actual.padre!=null){
                        actual=actual.padre;
                    }
                }
                if (puntero.val.compareTo(actual.val)<=0){
                    puntero=actual;
                }

            }
            return res;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
