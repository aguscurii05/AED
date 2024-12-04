package aed;

// La clase Ciudad permite almacenar datos sobre una ciudad en particular. Todos los métodos acá son O(1).
public class Ciudad {

    private int id;
    private int ganancias;
    private int perdidas;

    public Ciudad(int id) {
        this.id = id;
        this.ganancias = 0;
        this.perdidas = 0;
    }

    public void aumentarGanancias(int monto) {
        this.ganancias += monto;
    }

    public void aumentarPerdidas(int monto) {
        this.perdidas += monto;
    }

    public int id() {
        return this.id;
    }

    public int ganancias() {
        return this.ganancias;
    }

    public int perdidas() {
        return this.perdidas;
    }


    // Las instancias de Comparator requieren métodos de la clase a ordenar, así que armamos estos:
    public int superavit() {
        return this.ganancias - this.perdidas;
    }

    public int compararPorIDDescendiente() {
        return -id; 
    }
}
