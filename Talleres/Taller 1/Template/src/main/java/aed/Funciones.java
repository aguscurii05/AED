package aed;

class Funciones {
//EJERCICIO 1
    int cuadrado(int x) {
        int res;
        res = x*x;
        return res;
    }
//EJERCICIO 2
    double cuadrado2(double x) {
        double res;
        res = x*x;
        return res;
    }
    double distancia(double x, double y) {
        double res;
        double subres= cuadrado2(x)+cuadrado2(y);
        res =Math.sqrt(subres);
        return res;
    }
//EJERCICIO 3
    boolean divideA(int x,int y){
        return y%x==0;
    }
    boolean esPar(int n) {
        return divideA(2, n);
    }
//EJERCICIO 4
    boolean esBisiesto(int n) {
        return (divideA(4, n) && !(divideA(100, n))) || divideA(400,n);
    }
//EJERCICIO 5
    int factorialIterativo(int n) {
        int res = 1;
        int i= n;
        while (i>0){
            res = res*i;
            i=i-1;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        if (n==0){
            return 1;
        }
        else{
            return n*factorialRecursivo(n-1);
        }
    }
//EJERCICIO 6
    boolean esPrimo(int n) {
        int i=n;
        int cantDiv=0;
        while (i>0){
           if (divideA(i, n) ){
            cantDiv+=1;
           }
           i=i-1;
        }
        if (cantDiv==2){
            return true;
        }
       else{
            return false;
       }}

//EJERCICIO 7

    int sumatoria(int[] numeros) {
        int res=0;
       for (int num:numeros){
        res=res+num;
       }
       return res;
    }

//EJERCICIO 8
    int busqueda(int[] numeros, int buscado) {
        int n=0;
        while (n<numeros.length){
            if (buscado==numeros[n]){
                return n;
            }
            else{
                n+=1;
            }
        }
        return 0;
    }
//EJERCICIO 9

    boolean tienePrimo(int[] numeros) {
        boolean res =false;
        for (int num:numeros){
            if (esPrimo(num)){
                res= true;
            }
        }
        return res;
    }
//EJERCICIO 10

    boolean todosPares(int[] numeros) {
        boolean res=true;
        for (int num:numeros){
            res=res&&divideA(2, num);
        }
        return res;
    }
//EJERCICIO 11

    boolean esPrefijo(String s1, String s2) {
        boolean res=true;
        int calc=s2.length()-s1.length();
        if (calc<0){
            res =false;
        }
        else{
            for (int i=0;i<s1.length();i++){
                if (s1.charAt(i)==s2.charAt(i)){
                    res = res && true;
                }
                else{
                    res=false;
                }
            } 
        }
        return res;
    }
//EJERCICIO 12

    boolean esSufijo(String s1, String s2) {
        boolean res=true;
        int calc=s2.length()-s1.length();
        if (calc<0){
            res =false;
        }
        else{
            for (int i=0;i<s1.length();i++){
                if (s1.charAt(i)==s2.charAt(calc+i)){
                    res = res && true;
                }
                else{
                    res=false;
                }
            } 
        }
        return res;
    }
}
