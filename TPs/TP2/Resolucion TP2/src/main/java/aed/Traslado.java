package aed;

// Agregamos algunos métodos a Traslado para que esté bien encapsulado el código y privatizamos los atributos correspondientes.
public class Traslado {
    
    private int id;
    private int origen;
    private int destino;
    private int gananciaNeta;
    private int timestamp;

    public Traslado(int id, int origen, int destino, int gananciaNeta, int timestamp){
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.gananciaNeta = gananciaNeta;
        this.timestamp = timestamp;
    }

    public Traslado(Traslado t){
        id = t.id;
        origen = t.origen;
        destino = t.destino;
        gananciaNeta = t.gananciaNeta;
        timestamp = t.timestamp;

    }
    public int id() {
        return id;
    }

    public int origen() {
        return origen;
    }

    public int destino() {
        return destino;
    }    
    public int timestamp() {
        return timestamp;
    }

    public int ganancia() {
        return gananciaNeta;
    }


    // Las instancias de Comparator requieren métodos de la clase a ordenar, así que armamos estos:
    public int compararPorAntiguedadDescendiente() {
        return -timestamp;
    }

    public int compararPorIDDescendiente() {
        return -id;
    }

}
