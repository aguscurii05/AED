package aed;

/* El NodoPosiciones permite crear un nodo que almacena algún objeto y una secuencia de posiciones para dicho objeto.
 * Permite acceder en O(1) a las posiciones de dicho objeto en alguna otra estructura.
 * En BestEffort, es usado como "handle" para las posiciones de ciudades y traslados en los heaps correspondientes.
 * El constructor es O(cantPos), pero acá sabemos que cantPos va a ser 2 o menos, por lo que lo consideramos constante. */
class NodoPosiciones<T>{
    private T val;
    private Integer[] posiciones;
    // private int cantidad_de_posiciones;
    
    public NodoPosiciones(int cantPos, T val) {
        this.val = val;
        // this.cantidad_de_posiciones = cantPos;
        Integer[] pos = new Integer[cantPos];
        this.posiciones = pos;
    }

    // Devuelve el valor del nodo.
    public T obtener() {
        return val;
    }

    // Devuelve una de las posiciones del Nodo.
    public int posicion(int index) {
        return posiciones[index];
    }

    // Modifica un valor.
    public void modificarVal(T nuevo_valor) {
        val = nuevo_valor;
    }

    // Modifica una posición.
    public void modificarPos(int index, int nueva_posicion) {
        posiciones[index] = nueva_posicion; 
    }

}