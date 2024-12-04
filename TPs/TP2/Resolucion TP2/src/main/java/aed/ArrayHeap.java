package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class ArrayHeap<T> {

    ArrayList<NodoPosiciones<T>> datos;
    int posAUsar;
    int cantPos;
    int tamaño;
    Comparator<T> c;


    // Inicia un heap vacío. Considerando que el tiempo de iniciar un ArrayList es constante, el constructor también es O(1).
    public ArrayHeap(Comparator<T> comparador, int cantPos,int posAUsar) {
        datos = new ArrayList<NodoPosiciones<T>>(); 
        tamaño = 0;
        this.cantPos = cantPos;
        this.posAUsar = posAUsar;
        c = comparador;
    }

    /* Inicia un heap a partir de un arreglo pasado por parámetro.
     * Primero ordena el arreglo con el algoritmo de Floyd y luego asigna a cada elemento su posición iterativamente (para las hojas),
     * por lo que es O(n + n) = O(n). */
    public ArrayHeap(ArrayList<NodoPosiciones<T>> arreglo, Comparator<T> comparador,int cantPos,int posAUsar) {
        c = comparador;
        tamaño = arreglo.size();
        datos = new ArrayList<>(arreglo);

        this.cantPos = cantPos;
        this.posAUsar = posAUsar;
        int startPos = (tamaño - 2) /2;
        if (startPos >= 0) {
            heapifyDatos((tamaño - 2) / 2); // O(n)
        }
        int i = 0;                      
        while (i < tamaño) { // O(1), n iteraciones = O(n)
            datos.get(i).modificarPos(posAUsar, i);
            i++;
        }
    }


    /* agregar añade un elemento al heap y lo siftea hacia arriba.
     * El peor caso ocurre cuando el elemento tiene la mayor prioridad, y se debe hacer un siftUp por completo (en O(log(n))) */
    public void agregar(NodoPosiciones<T> elem) {
        datos.add(elem);
        elem.modificarPos(posAUsar, tamaño);
        tamaño++;
        siftUp(tamaño - 1);
    }


    // consultarTope devuelve el valor asociado al tope del heap en O(1).
    public T consultarTope() {
        return datos.get(0).obtener();
    }


    /* eliminarTope reemplaza la raíz por el último elemento y lo siftea hacia abajo.
     * Al ser hoja, se debe siftear por completo, por lo que cae en el peor caso de siftDown y es O(log(n)). */
    public NodoPosiciones<T> eliminarTope() {
        return eliminarDesdePosicion(0);
    }
    
    public NodoPosiciones<T> eliminarDesdePosicion(int index) {
        NodoPosiciones<T> ultimo = datos.get(tamaño - 1);
        NodoPosiciones<T> res = datos.set(index, ultimo);
        ultimo.modificarPos(posAUsar, index);
        datos.remove(tamaño - 1); // O(1), porque no hay que hacer corrimiento e ignoramos la complejidad del redimensionado
        tamaño--;

        int pos_hijo = posHijoMayor(index);
        siftDown(index, pos_hijo);
        
        return res;
    }

    /* modificarPrioridad reemplaza al valor de un elemento en una posición y luego lo sube o baja según sea necesario.
     * En el peor caso, sube una hoja hasta la raíz o viceversa, por lo que depende de la altura, y es O(log(n)).
     * Como solo usamos este método para el heap de superávits, asumimos que cantPos = 1 y no tenemos que estar al tanto de otras posiciones. */
    public void modificarPrioridad(int posicion, T nuevo_valor) {
        NodoPosiciones<T> nodo_en_posicion = datos.get(posicion);
        T valor_previo = nodo_en_posicion.obtener();
        nodo_en_posicion.modificarVal(nuevo_valor);    
        if (c.compare(nuevo_valor, valor_previo) >= 0) {
            siftUp(posicion);
        } else {
            int pos_hijo = posHijoMayor(posicion);
            siftDown(posicion, pos_hijo);
        }
    }

    // Retorna la posición del hijo con mayor prioridad dentro del heap en O(1). Si la posición es hoja, se devuelve a sí misma.
    private int posHijoMayor(int pos) {
        int pos_hijo_izq = 2*pos + 1;
        int pos_hijo_der = 2*pos + 2;
        int res = pos_hijo_izq; // Sólo hacemos compare si el nodo tiene dos hijos; chequeamos que no sea hoja en otros métodos (es decir, pos_hijo_izq < tamaño).

        if (tamaño > pos_hijo_der) {
            T val_izq = datos.get(pos_hijo_izq).obtener();
            T val_der = datos.get(pos_hijo_der).obtener();
            if (c.compare(val_izq, val_der) >= 0) {
                res = pos_hijo_izq;
            } else {
                res = pos_hijo_der;
            }
        }
        return res;
    }

    /* siftUp "sube" un elemento con swaps (lo implementamos con iteración).
     * En el peor de los casos sube una hoja hasta la raiz, por lo que depende de la altura, y es O(log(n)). */
    private void siftUp(int posicion) {
        int posActual = posicion;
        int posPadre = (posicion - 1) / 2;
        while (posPadre >= 0 && c.compare(datos.get(posActual).obtener(), datos.get(posPadre).obtener()) > 0) {
            NodoPosiciones<T> valorASiftear = datos.get(posActual);
            NodoPosiciones<T> padre = datos.get(posPadre);

            datos.set(posActual, padre); 
            padre.modificarPos(posAUsar, posActual);
            datos.set(posPadre, valorASiftear); 
            valorASiftear.modificarPos(posAUsar, posPadre);

            posActual = posPadre;
            posPadre = (posActual - 1) / 2;
        }
    }


    /* siftDown "baja" un elemento con swaps (lo implementamos con iteración).
     * En el peor de los casos baja la raíz hasta que sea hoja, por lo que depende de la altura, y es O(log(n)). */
    private void siftDown(int posicion, int posicionHijo) {

        int pos_actual = posicion;
        int pos_hijo = posicionHijo;

        while (pos_hijo < tamaño) {
            NodoPosiciones<T> padre = datos.get(pos_actual);
            NodoPosiciones<T> hijo = datos.get(pos_hijo);

            if (c.compare(hijo.obtener(), padre.obtener()) > 0) {
                datos.set(pos_actual, hijo);
                hijo.modificarPos(posAUsar, pos_actual);
                datos.set(pos_hijo, padre);
                padre.modificarPos(posAUsar, pos_hijo);
            }

            pos_actual = pos_hijo;
            pos_hijo = posHijoMayor(pos_hijo);
        }
    }


    /* Esta función implementa el algoritmo de Floyd. Para esto, toma al padre del ultimo elemento y hace los swaps necesarios.
     * Luego va una posición atrás y realiza lo mismo con el nuevo elemento. Al estar los subárboles inferiores ordenados a medida
     * que se sube, se disminuye la complejidad. Siguiendo de la demostración vista en la teórica, aseguramos que heapifyDatos sea O(n). */
    private void heapifyDatos(int startpos) { 
        int mayorPos = posHijoMayor(startpos);
        siftDown(startpos, mayorPos);
        startpos--;

        if (startpos >= 0) {
            heapifyDatos(startpos);
        }
    }


    // Esta función no se llama en BestEffort; solo la usamos para debuggear.
    @Override
    public String toString() {
        int i = 0;
        String res = "[";
        while (i < tamaño - 1) {
            res += datos.get(i).obtener() + ", ";
            i++;
        }
        return res + datos.get(i).obtener() + "]";
    }
}