package aed;

public class Fecha {
    private int dia;
    private int mes;

    public Fecha(int dia_inicial, int mes_inicial) {
        dia = dia_inicial;
        mes = mes_inicial;        
    }

    public Fecha(Fecha fecha) {
        //Fecha newFecha= new Fecha(fecha.dia(),fecha.mes());
        dia = fecha.dia();
        mes = fecha.mes();
    }

    public Integer dia() {
        return dia;
        
    }

    public Integer mes() {
        return mes;
    }

    @Override
    public String toString() {
        return dia+"/"+mes;
    }

    @Override
    public boolean equals(Object otra) {
        boolean distintoTipo = otra.getClass() != this.getClass();
        boolean esNull = (otra==null);
        if (distintoTipo || esNull){
            return false;
        }
        else{
            Fecha castOtra = (Fecha) otra;
            return dia==castOtra.dia() && mes==castOtra.mes();
        }
    }

    public void incrementarDia() {
        if (dia==diasEnMes(mes)){
            dia=1;
            if (mes==12){
            mes=1;
           }
           else{
            mes+=1;
           }  
        }
        else{
            dia+=1;
        }
        
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
