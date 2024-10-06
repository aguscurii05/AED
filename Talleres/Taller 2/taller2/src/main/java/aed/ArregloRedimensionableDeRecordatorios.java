package aed;

class ArregloRedimensionableDeRecordatorios {
    
    private Recordatorio[] array;

    public ArregloRedimensionableDeRecordatorios() {
        array = new Recordatorio[0];
        
    }

    public int longitud() {
        return array.length;
    }

    public void agregarAtras(Recordatorio i) {
        int largo = array.length;
        Recordatorio[] nuevoArray = new Recordatorio[largo+1];
        for (int j=0;j<largo;j++){
            nuevoArray[j]=array[j];
        }
        nuevoArray[largo]=i;
        array = new Recordatorio[largo+1];
        for (int j=0;j<largo+1;j++){
            array[j]=nuevoArray[j];
        }
    }

    public Recordatorio obtener(int i) {
        Recordatorio res = array[i];
        return res;
        
    }

    public void quitarAtras() {
        int largo = array.length-1;
        Recordatorio[] nuevoArray = new Recordatorio[largo];
        for (int j=0;j<largo;j++){
            nuevoArray[j]=array[j];
        }
        array = new Recordatorio[largo];
        for (int j=0;j<largo;j++){
            array[j]=nuevoArray[j];
        }
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        array[indice]=valor;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        array = new Recordatorio[vector.longitud()];
        for (int i=0; i<vector.longitud();i++){
            array[i]=vector.obtener(i);
        }
    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios res = new ArregloRedimensionableDeRecordatorios(this);
        return res;
    }
}
