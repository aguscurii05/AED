package aed;

public class Horario {
    private int hora;
    private int min;
    public Horario(int initHora, int initMin) {
        hora = initHora;
        min = initMin;
    }

    public int hora() {
        return hora;
    }

    public int minutos() {
        return min;
    }

    @Override
    public String toString() {
        return hora+":"+min;
    }

    @Override
    public boolean equals(Object otro) {
        boolean distintoTipo = this.getClass() != otro.getClass();
        boolean esNull = otro==null;
        //casteo
        if (esNull || distintoTipo){
            return false;
        }
        else{
            Horario castOtro = (Horario) otro;
           return hora==castOtro.hora() && min==castOtro.minutos();
        }
        
    }

}
