package aed;
import java.util.ArrayList;
import java.util.Comparator;

public class BestEffort {

    private ArrayHeap<Traslado> traslados_por_redito;
    private ArrayHeap<Traslado> traslados_por_timestamp;
    private ArrayHeap<Ciudad> ciudades_por_superavit;

    private ArrayList<Integer> mayores_ganancias;
    private ArrayList<Integer> mayores_perdidas;
    private ArrayList<NodoPosiciones<Ciudad>> stats;

    private int ganancias_totales;
    private int cdad_traslados_despachados;

    public BestEffort(int cantCiudades, Traslado[] traslados) {

        // Iniciamos los comparadores
        Comparator<Traslado> compararTrasladoPorRedito = Comparator.comparing(Traslado::ganancia).thenComparing(Traslado::compararPorIDDescendiente);
        Comparator<Traslado> compararTrasladoPorAntiguedad = Comparator.comparing(Traslado::compararPorAntiguedadDescendiente);
        Comparator<Ciudad> compararCiudadPorSuperavit = Comparator.comparing(Ciudad::superavit).thenComparing(Ciudad::compararPorIDDescendiente);

        
        // Creamos un arrayList de NodoPosiciones --> O(T)
        int i = 0;
        ArrayList<NodoPosiciones<Traslado>> newTraslados = new ArrayList<NodoPosiciones<Traslado>>();
        while (i < traslados.length) {
            NodoPosiciones<Traslado> elem = new NodoPosiciones<Traslado>(2, traslados[i]);
            newTraslados.add(elem);
            i++;
        }
        
        // Iniciamos los heaps de traslados con el constructor de ArrayHeap, el cual implementa el algoritmo de Floyd --> O(T)
        traslados_por_redito = new ArrayHeap<Traslado>(newTraslados, compararTrasladoPorRedito,2,0);
        traslados_por_timestamp = new ArrayHeap<Traslado>(newTraslados, compararTrasladoPorAntiguedad,2,1);

        mayores_ganancias = new ArrayList<Integer>();
        mayores_perdidas = new ArrayList<Integer>();
        stats = new ArrayList<NodoPosiciones<Ciudad>>();

        // Este ciclo realiza una serie de operaciones elementales por cada ciudad --> O(C)
        for (int j = 0; j < cantCiudades; j++) {
            Ciudad actual = new Ciudad(j);
            NodoPosiciones<Ciudad> actualPosInHeap = new NodoPosiciones<Ciudad>(1, actual);
            mayores_ganancias.add(j);       // Como todas tienen iguales ganancias y pérdidas al principio (0) y no se requiere tener 
            mayores_perdidas.add(j);        // traslados despachados para consultarlas, agregamos todas las ciudades a la lista al principio.
            stats.add(actualPosInHeap);     
        }

        // Iniciamos el heap de superávit --> O(C)
        ciudades_por_superavit = new ArrayHeap<Ciudad>(stats, compararCiudadPorSuperavit, 1, 0);                            

        // Iniciamos los atributos de ganancias y traslados despachados --> O(1)
        ganancias_totales = 0;
        cdad_traslados_despachados = 0;
    }
    /* ORDEN DE COMPLEJIDAD:
     * Todos los algoritmos que corremos acá son lineales: primero pasamos los traslados y ciudades a NodoPosiciones iterando simplemente y corremos heapify sobre ellos.
     * En caso de que se quiera consultar sin registrar traslados, también agregamos todas las ciudades a las listas de mayores y menores ganancias.
     * Iniciar parámetros por fuera de esto toma O(1), por lo que la complejidad final depende del tamaño de los datos de entrada, es decir, es O(T + C). */


    public void registrarTraslados(Traslado[] traslados) {
        int i = 0;
        while(i < traslados.length) { 
            Traslado actual = traslados[i];
            NodoPosiciones<Traslado> elem = new NodoPosiciones<>(2, actual);
            traslados_por_timestamp.agregar(elem);
            traslados_por_redito.agregar(elem);
            i++;
        }
    }
    /* ORDEN DE COMPLEJIDAD:
     * En cada paso, agregamos a ambos heaps y actualizamos la posición de todos los elementos que fueron movidos.
     * Este paso es O(log(T)), pues acomodamos las posiciones a medida que vamos sifteando el elemento.
     * Iteramos sobre la longitud de traslados, por lo que tenemos una complejidad de O(|traslados|log(T)). */


    public ArrayList<Integer> despacharMasRedituables(int n) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int i = 0;
        while (i < n && traslados_por_redito.tamaño > 0) {
            NodoPosiciones<Traslado> actual = traslados_por_redito.eliminarTope();

            int posicionEnTimestamp = actual.posicion(1);
            traslados_por_timestamp.eliminarDesdePosicion(posicionEnTimestamp); // O(log(T)), porque no lo tenemos que buscar
            
            Traslado traslado = actual.obtener();
            this.actualizarPerdidasYGanancias(traslado.origen(), traslado.destino(), traslado.ganancia()); // O(log(C))

            res.add(traslado.id());
            ganancias_totales += traslado.ganancia(); 
            cdad_traslados_despachados++;
            i++;
        }
        return res;
    }
    /* ORDEN DE COMPLEJIDAD:
     * Borramos el tope de un heap y el mismo elemento del otro sabiendo su posición en O(log(T)), pues debemos siftear y modificar las posiciones de los demás elementos;
     * luego, actualizamos las mayores ganancias y pérdidas (O(1)) y el heap de superávits en O(log(C)), usando operaciones similares.
     * iteramos n veces, por lo que nos queda una complejidad de O(n(log(T) + log(C))). */


    public ArrayList<Integer> despacharMasAntiguos(int n) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        int i = 0;
        while (i < n && traslados_por_timestamp.tamaño > 0) {
            NodoPosiciones<Traslado> actual = traslados_por_timestamp.eliminarTope();
            
            int posicionEnRedito = actual.posicion(0);
            traslados_por_redito.eliminarDesdePosicion(posicionEnRedito); // O(log(T)), por siftDown
            
            Traslado traslado = actual.obtener();
            this.actualizarPerdidasYGanancias(traslado.origen(), traslado.destino(), traslado.ganancia());

            res.add(traslado.id());
            ganancias_totales += traslado.ganancia(); 
            cdad_traslados_despachados++;
            i++;
        }
        return res;
    }
    // ORDEN DE COMPLEJIDAD: O(n(log(T) + log(C))); justificación análoga al método anterior.


    public int ciudadConMayorSuperavit() {
        return ciudades_por_superavit.consultarTope().id();
    }
    // ORDEN DE COMPLEJIDAD: acá consultamos el ID de la ciudad tope del heap, cuyo acceso es O(1).


    public ArrayList<Integer> ciudadesConMayorGanancia() {
        return mayores_ganancias;
    }

    public ArrayList<Integer> ciudadesConMayorPerdida() {
        return mayores_perdidas;
    }

    public int gananciaPromedioPorTraslado() {
        return ganancias_totales / cdad_traslados_despachados;
    }
    // ORDEN DE COMPLEJIDAD: estos tres últimos métodos constituyen operaciones simples, por lo que son O(1).


    private void actualizarPerdidasYGanancias(int origen, int destino, int ganancia) {
        NodoPosiciones<Ciudad> nodo_ciudad1 = stats.get(origen);
        NodoPosiciones<Ciudad> nodo_ciudad2 = stats.get(destino);

        Ciudad ciudad1 = nodo_ciudad1.obtener();
        Ciudad ciudad2 = nodo_ciudad2.obtener();

        // Buscamos las ganancias y pérdidas correspondientes a los primeros elementos de mayorGanancia y mayorPérdida, como representativos.
        int mayorGanancia = stats.get(mayores_ganancias.get(0)).obtener().ganancias(); // Por cómo iniciamos BestEffort,
        int mayorPerdida = stats.get(mayores_perdidas.get(0)).obtener().perdidas();    // estas listas siempre tienen al menos un elemento.
        
        ciudad1.aumentarGanancias(ganancia);           
        ciudad2.aumentarPerdidas(ganancia); 
        
        int pos_ciudad1 = nodo_ciudad1.posicion(0); // hay un solo heap de ciudades, así que cantPos = 1
        ciudades_por_superavit.modificarPrioridad(pos_ciudad1, ciudad1);

        int pos_ciudad2 = nodo_ciudad2.posicion(0);
        ciudades_por_superavit.modificarPrioridad(pos_ciudad2, ciudad2);

        if (ciudad1.ganancias() >= mayorGanancia) {
            if (ciudad1.ganancias() > mayorGanancia) {
                mayores_ganancias.clear();
            }
            mayores_ganancias.add(ciudad1.id());
        }

        if (ciudad2.perdidas() >= mayorPerdida) {
            if (ciudad2.perdidas() > mayorPerdida) {
                mayores_perdidas.clear();
            }
            mayores_perdidas.add(ciudad2.id());
        }
    }
}