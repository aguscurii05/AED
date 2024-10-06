package aed;

public class Recordatorio {
    private String msj;
    private Fecha date;
    private Horario time;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        Fecha newFecha= new Fecha(fecha);
        msj = mensaje;
        date = newFecha;
        time = horario;
    }

    public Horario horario() {
        return time;
    }

    public Fecha fecha() {
        Fecha newFecha= new Fecha(date);
        return newFecha;
    }

    public String mensaje() {
        return msj;
    }

    @Override
    public String toString() {
        return msj+" @ "+date.toString()+" "+time.toString();
    }

    @Override
    public boolean equals(Object otro) {
        boolean distintoTipo = this.getClass() != otro.getClass();
        boolean esNull = otro==null;
        if (distintoTipo || esNull){
            return false;
        }
        else{
            Recordatorio castOtro = (Recordatorio) otro;
            return msj==castOtro.mensaje() && date.equals(castOtro.fecha()) && time.equals(castOtro.horario());
        }
    }

}
