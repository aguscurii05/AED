package aed;

public class Agenda {
    private Fecha date;
    private ArregloRedimensionableDeRecordatorios rec;

    public Agenda(Fecha fechaActual) {
        date=fechaActual;
        rec = new ArregloRedimensionableDeRecordatorios();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        rec.agregarAtras(recordatorio);
    }

    @Override
    public String toString() {
        String res = this.fechaActual()+"\n=====\n";
        for(int i=0;i<rec.longitud();i++){
            if(this.fechaActual().equals(rec.obtener(i).fecha())){
                res= res+rec.obtener(i).toString()+"\n";
            }
        }
        
        return res;


        
    }

    public void incrementarDia() {
        Fecha copiaFecha = new Fecha(date);
        copiaFecha.incrementarDia();
        date = copiaFecha;
    }

    public Fecha fechaActual() {
        return date;
    }

}
