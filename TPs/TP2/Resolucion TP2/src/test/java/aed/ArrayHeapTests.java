package aed;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class ArrayHeapTests {

    Integer[] arreglo;
    Comparator<Integer> intCompare;

    @BeforeEach
    void init() {
        Integer[] arreglo = {195, 7, 6, 5, 5, 4, 3, 2, 1};
        this.arreglo = arreglo;
        intCompare = Comparator.comparing(Integer::intValue);
    }

    @Test
    void agregar_y_borrar_a_heap_nuevo() {
        ArrayHeap<Integer> test = new ArrayHeap<Integer>(intCompare,1,0);
        NodoPosiciones<Integer> elem2 = new NodoPosiciones<Integer>(1, 3);
        NodoPosiciones<Integer> elem0 = new NodoPosiciones<Integer>(1, 6);
        NodoPosiciones<Integer> elem1 = new NodoPosiciones<Integer>(1, 4);
        NodoPosiciones<Integer> elem3 = new NodoPosiciones<Integer>(1, 2);
        test.agregar(elem2);
        test.agregar(elem0);
        test.agregar(elem1);
        test.agregar(elem3);
        // test.datos = {6, 3, 4, 2}

        assertEquals(4, test.tamaño);
        assertTrue(lasPosicionesSonCorrectas(test, test.posAUsar));
        assertTrue(esHeap(test, 0));
        
        NodoPosiciones<Integer> elem = test.eliminarTope();
        assertEquals(6, elem.obtener());
        assertEquals(0, elem.posicion(0));
        assertEquals(4, test.eliminarTope().obtener());
        assertEquals(3, test.eliminarTope().obtener());
        assertEquals(2, test.eliminarTope().obtener());
    }
    
    @Test
    void heapify_desde_arreglo_ordenado() {
        ArrayList<NodoPosiciones<Integer>> arreglo_con_posiciones = new ArrayList<NodoPosiciones<Integer>>();
        for (int i = 0; i < arreglo.length; i++) {
            NodoPosiciones<Integer> elem = new NodoPosiciones<Integer>(1, arreglo[i]);
            arreglo_con_posiciones.add(elem);
        }

        ArrayHeap<Integer> test = new ArrayHeap<Integer>(arreglo_con_posiciones, intCompare,1,0); // construye el heap y lo ordena
        // test.datos = {195, 7, 6, 5, 5, 4, 3, 2, 1}

        assertEquals(9, test.tamaño);
        assertTrue(lasPosicionesSonCorrectas(test, test.posAUsar));
        assertTrue(esHeap(test, 0));

        assertEquals(195, test.eliminarTope().obtener());
        assertEquals(7, test.eliminarTope().obtener());
        assertEquals(6, test.eliminarTope().obtener());
        assertEquals(5, test.eliminarTope().obtener());
        assertEquals(5, test.eliminarTope().obtener());

        assertEquals(4, test.tamaño);
        assertTrue(lasPosicionesSonCorrectas(test, test.posAUsar));
        assertTrue(esHeap(test, 0));

        assertEquals(4, test.eliminarTope().obtener());
        assertEquals(3, test.eliminarTope().obtener());
        assertEquals(2, test.eliminarTope().obtener());
        assertEquals(1, test.eliminarTope().obtener());

        assertEquals(0, test.tamaño);
    }

    @Test
    void heapify_desde_arreglo_con_orden_inverso() {
        ArrayList<NodoPosiciones<Integer>> arreglo_con_posiciones = new ArrayList<NodoPosiciones<Integer>>();
        for (int i = arreglo.length - 1; i >= 0; i--) { // armo la lista al revés, teniendo que siftear siempre
            NodoPosiciones<Integer> elem = new NodoPosiciones<Integer>(1, arreglo[i]);
            arreglo_con_posiciones.add(elem);
        }

        ArrayHeap<Integer> test = new ArrayHeap<Integer>(arreglo_con_posiciones, intCompare,1,0);
        // test.datos = {195, 7, 6, 5, 5, 4, 3, 2, 1}
        
        assertEquals(9, test.tamaño);
        assertTrue(lasPosicionesSonCorrectas(test, test.posAUsar));
        assertTrue(esHeap(test, 0));
        
        assertEquals(195, test.eliminarTope().obtener());
        assertEquals(7, test.eliminarTope().obtener());
        assertEquals(6, test.eliminarTope().obtener());
        assertEquals(5, test.eliminarTope().obtener());
        assertEquals(5, test.eliminarTope().obtener());

        assertEquals(4, test.tamaño);
        assertTrue(lasPosicionesSonCorrectas(test, test.posAUsar));
        assertTrue(esHeap(test, 0));

        assertEquals(4, test.eliminarTope().obtener());
        assertEquals(3, test.eliminarTope().obtener());
        assertEquals(2, test.eliminarTope().obtener());
        assertEquals(1, test.eliminarTope().obtener());

        assertEquals(0, test.tamaño);
        assertTrue(esHeap(test, 0));
    }

    @Test 
    void modificacion_de_prioridad() {
        ArrayList<NodoPosiciones<Integer>> arreglo_con_posiciones = new ArrayList<NodoPosiciones<Integer>>();
        int i = 0;
        while(i<100){
            arreglo_con_posiciones.add(new NodoPosiciones<Integer>(1,i));
            i++;
        }
        ArrayHeap<Integer> arr = new ArrayHeap<>(arreglo_con_posiciones, intCompare, 1, 0);
        arr.modificarPrioridad(70, 1000);
        arr.modificarPrioridad(30, 2000);
        assertEquals(2000, arr.eliminarTope().obtener());
        assertEquals(1000, arr.eliminarTope().obtener());
        assertEquals(99, arr.eliminarTope().obtener());
    }

    @Test
    void stress_desencolar(){
        ArrayList<NodoPosiciones<Integer>> secuencia = new ArrayList<NodoPosiciones<Integer>>();
        for (int i = 0; i <= 25000; i++) {
            NodoPosiciones<Integer> elem = new NodoPosiciones<Integer>(1, i);
            secuencia.add(elem);
        }

        ArrayHeap<Integer> test = new ArrayHeap<Integer>(secuencia, intCompare, 1, 0);
        for (int j = 25000; j >= 0; j--) {
            int actual = test.eliminarTope().obtener();
            assertEquals(j, actual);
            assertTrue(lasPosicionesSonCorrectas(test, test.posAUsar));
            assertTrue(esHeap(test,0));
        }
    }


    int size = 25000;
    private Integer clave(Integer i) {
        return size * ((i * (i - 100) * i) % size) + i;
    }

    @Test
    void stress_se_preserva_la_propiedad_de_heap() {
        int cdad_elems = 25000;
        ArrayList<NodoPosiciones<Integer>> lista = new ArrayList<NodoPosiciones<Integer>>();
        for (int i = 0; i < cdad_elems; i++) {
            NodoPosiciones<Integer> actual = new NodoPosiciones<Integer>(1, clave(i));
            lista.add(actual);
        }
        ArrayHeap<Integer> test = new ArrayHeap<Integer>(lista, intCompare, 1, 0);

        while (test.tamaño > 0) {
            assertTrue(esHeap(test,0));
            assertTrue(lasPosicionesSonCorrectas(test, 0));
            test.eliminarTope();
        }
    }
    
    @Test
    void heaps_sincronizados_con_criterios_distintos() {
        ArrayList<NodoPosiciones<Integer>> arreglo_pos = new ArrayList<NodoPosiciones<Integer>>();

        for (int i = 0; i < arreglo.length; i++) {
            NodoPosiciones<Integer> elem = new NodoPosiciones<Integer>(2, arreglo[i]);
            arreglo_pos.add(elem);
        }

        ArrayHeap<Integer> test_0 = new ArrayHeap<Integer>(arreglo_pos, intCompare,2, 0);
        ArrayHeap<Integer> test_1 = new ArrayHeap<Integer>(arreglo_pos, intCompare.reversed(),2, 1);

        // Alternamos en qué posicion vamos desencolando, para ver que sigan sincronizados los heaps:
        while (test_0.tamaño > 0) {
            assertTrue(esHeap(test_0, 0));
            assertTrue(esHeap(test_1, 0));

            assertTrue(lasPosicionesSonCorrectas(test_0, test_0.posAUsar));
            assertTrue(lasPosicionesSonCorrectas(test_1, test_1.posAUsar));
            assertSetEquals(test_0.datos, test_1.datos);

            assertEquals(test_0.tamaño, test_1.tamaño);

            if (test_0.tamaño % 2 == 0) {
                NodoPosiciones<Integer> actual = test_0.eliminarTope();
                int pos_en_1 = actual.posicion(1);
                test_1.eliminarDesdePosicion(pos_en_1);
            } else {
                NodoPosiciones<Integer> actual = test_1.eliminarTope();
                int pos_en_0 = actual.posicion(0);
                test_0.eliminarDesdePosicion(pos_en_0);
            }            
        }

        assertEquals(0, test_0.tamaño);
        assertEquals(0, test_1.tamaño);

    }


    private boolean esHeap(ArrayHeap<Integer> heap, int actual) {
        boolean res = true;
        if (heap.tamaño - actual > 0) {
            if (2*actual + 1 < heap.tamaño - 1){
                int izq = 2*actual + 1;
                int der = 2*actual + 2;
                int padre = heap.datos.get(actual).obtener();
                int hijoIzq = heap.datos.get(izq).obtener();
                int hijoDer = heap.datos.get(der).obtener();
                res = (heap.c.compare(padre, hijoIzq) >= 0 && heap.c.compare(padre, hijoDer) >= 0 && esHeap(heap, izq) && esHeap(heap, der));
            } else if (2*actual + 1 < heap.tamaño) {
                int hijo = 2*actual + 1;
                int padre = heap.datos.get(actual).obtener();
                int hijoIzq = heap.datos.get(hijo).obtener();
                res = (heap.c.compare(padre, hijoIzq) >= 0 && esHeap(heap, hijo));
            }
        }
        return res;
    }
    

    private boolean lasPosicionesSonCorrectas(ArrayHeap<Integer> heap, int coordenada) {
        boolean res = true;
        for (int i = 0; i < heap.tamaño; i++) {
            NodoPosiciones<Integer> actual = heap.datos.get(i);
            res &= (actual.posicion(coordenada)) == i;
        }
        return res;
    }


    // Usamos una variación sobre assertSetEquals porque nos permite predicar sobre el conjunto de datos de los heaps.
    void assertSetEquals(ArrayList<NodoPosiciones<Integer>> s1, ArrayList<NodoPosiciones<Integer>> s2) {
        assertEquals(s1.size(), s2.size());

        ArrayList<Integer> arreglo_aux_0 = new ArrayList<Integer>();
        ArrayList<Integer> arreglo_aux_1 = new ArrayList<Integer>();
        for (int i = 0; i < s1.size(); i++) {
            arreglo_aux_0.add(s1.get(i).obtener());
            arreglo_aux_1.add(s2.get(i).obtener());
        }

        for (int e1 : arreglo_aux_0) {
            boolean encontrado = false;
            for (int e2 : arreglo_aux_1) {
                if (e1 == e2) encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " +  e1 + " en el arreglo " + s2.toString());
        }
    }
}