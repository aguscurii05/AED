package aed;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Disabled; //Esta biblioteca fue usada para el Testing del TP
import org.junit.jupiter.api.Test;

public class BestEffortTests {

    int cantCiudades;
    int extraCiud;
    Traslado[] listaTraslados;
    Traslado[] extraTras;
    ArrayList<Integer> actual;


    @BeforeEach
    void init(){
        //Reiniciamos los valores de las ciudades y traslados antes de cada test
        cantCiudades = 7;
        listaTraslados = new Traslado[] {
                                            new Traslado(1, 0, 1, 100, 10),
                                            new Traslado(2, 0, 1, 400, 20),
                                            new Traslado(3, 3, 4, 500, 50),
                                            new Traslado(4, 4, 3, 500, 11),
                                            new Traslado(5, 1, 0, 1000, 40),
                                            new Traslado(6, 1, 0, 1000, 41),
                                            new Traslado(7, 6, 3, 2000, 42)
                                        };
        extraCiud = 15;
        extraTras = new Traslado[]{
            new Traslado(1,0,12,1500,12),
            new Traslado(2,3,14,500,123),
            new Traslado(3,7,9,2500,1),
            new Traslado(4,8,4,1000,15),
            new Traslado(5,13,2,100,9),
            new Traslado(6,14,11,10,5),
            new Traslado(7,11,2,1600,66),
            new Traslado(8,4,1,8000,22),
            new Traslado(9,6,10,15000,555),
            new Traslado(10,1,0,15500,6),
        };
    }

    void assertSetEquals(ArrayList<Integer> s1, ArrayList<Integer> s2) {
        assertEquals(s1.size(), s2.size());
        for (int e1 : s1) {
            boolean encontrado = false;
            for (int e2 : s2) {
                if (e1 == e2) encontrado = true;
            }
            assertTrue(encontrado, "No se encontró el elemento " +  e1 + " en el arreglo " + s2.toString());
        }
    }

    @Test
    void despachar_con_mas_ganancia_de_a_uno(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(1);
        
        assertSetEquals(new ArrayList<>(Arrays.asList(6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());

        sis.despacharMasRedituables(1);
        sis.despacharMasRedituables(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(1, 6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 3)), sis.ciudadesConMayorPerdida());
    }
    
    @Test
    void despachar_con_mas_ganancia_de_a_varios(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(3);

        assertSetEquals(new ArrayList<>(Arrays.asList(1, 6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 3)), sis.ciudadesConMayorPerdida());

        sis.despacharMasRedituables(3);

        assertSetEquals(new ArrayList<>(Arrays.asList(1, 6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());

    }
    
    @Test
    void despachar_mas_viejo_de_a_uno(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);
        
        sis.despacharMasAntiguos(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorPerdida());

        sis.despacharMasAntiguos(1);
        assertSetEquals(new ArrayList<>(Arrays.asList(4)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());

        sis.despacharMasAntiguos(1);
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 4)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(1, 3)), sis.ciudadesConMayorPerdida());
    }
    
    @Test
    void despachar_mas_viejo_de_a_varios(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);
        
        sis.despacharMasAntiguos(3);
        assertSetEquals(new ArrayList<>(Arrays.asList(0, 4)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(1, 3)), sis.ciudadesConMayorPerdida());

        sis.despacharMasAntiguos(3);
        assertSetEquals(new ArrayList<>(Arrays.asList(1, 6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());
        
    }
    
    @Test
    void despachar_mixtos(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasRedituables(3);
        sis.despacharMasAntiguos(3);
        assertSetEquals(new ArrayList<>(Arrays.asList(1, 6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());

        sis.despacharMasAntiguos(1);
        assertSetEquals(new ArrayList<>(Arrays.asList(1, 6)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(3)), sis.ciudadesConMayorPerdida());
        
    }
    
    @Test
    void agregar_traslados(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        Traslado[] nuevos = new Traslado[] {
            new Traslado(8, 0, 1, 10001, 5),
            new Traslado(9, 0, 1, 40000, 2),
            new Traslado(10, 0, 1, 50000, 3),
            new Traslado(11, 0, 1, 50000, 4),
            new Traslado(12, 1, 0, 150000, 1)
        };

        sis.registrarTraslados(nuevos);

        sis.despacharMasAntiguos(4);
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorPerdida());

        sis.despacharMasRedituables(1);
        assertSetEquals(new ArrayList<>(Arrays.asList(0)), sis.ciudadesConMayorGanancia());
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), sis.ciudadesConMayorPerdida());

    }
    
    @Test
    void promedio_por_traslado(){
        BestEffort sis = new BestEffort(this.cantCiudades, this.listaTraslados);

        sis.despacharMasAntiguos(3);
        assertEquals(333, sis.gananciaPromedioPorTraslado());

        sis.despacharMasRedituables(3);
        assertEquals(833, sis.gananciaPromedioPorTraslado());

        Traslado[] nuevos = new Traslado[] {
            new Traslado(8, 1, 2, 1452, 5),
            new Traslado(9, 1, 2, 334, 2),
            new Traslado(10, 1, 2, 24, 3),
            new Traslado(11, 1, 2, 333, 4),
            new Traslado(12, 2, 1, 9000, 1)
        };

        sis.registrarTraslados(nuevos);
        sis.despacharMasRedituables(6);

        assertEquals(1386, sis.gananciaPromedioPorTraslado());
        

    }

    @Test
    void mayor_superavit(){
        Traslado[] nuevos = new Traslado[] {
            new Traslado(1, 3, 4, 1, 7),
            new Traslado(7, 6, 5, 40, 6),
            new Traslado(6, 5, 6, 3, 5),
            new Traslado(2, 2, 1, 41, 4),
            new Traslado(3, 3, 4, 100, 3),
            new Traslado(4, 1, 2, 30, 2),
            new Traslado(5, 2, 1, 90, 1)
        };
        BestEffort sis = new BestEffort(this.cantCiudades, nuevos);

        sis.despacharMasAntiguos(1);
        assertEquals(2, sis.ciudadConMayorSuperavit());

        sis.despacharMasAntiguos(2);
        assertEquals(3, sis.ciudadConMayorSuperavit());

        sis.despacharMasAntiguos(3);
        assertEquals(2, sis.ciudadConMayorSuperavit());

        sis.despacharMasAntiguos(1);
        assertEquals(2, sis.ciudadConMayorSuperavit());

    }



    // (desde acá hacia abajo los tests los armamos nosotros)

    // Tests sobre el registro de traslados:
    @Test
    void traslado_raiz(){ 
        BestEffort system = new BestEffort(extraCiud, extraTras);

        Traslado[] nuevo = new Traslado[]{new Traslado (11,12,13,20000,0)}; //Es raíz en ambos
        system.registrarTraslados(nuevo);
        
        ArrayList<Integer>  despacho = system.despacharMasRedituables(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(11)), despacho);

        system.registrarTraslados(nuevo);

        ArrayList<Integer>  despacho2 = system.despacharMasAntiguos(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(11)), despacho2);
        
    }


    @Test
    void agregar_varios_elementos(){ // El caso estándar
    BestEffort system = new BestEffort(extraCiud, extraTras);

    Traslado[] nuevos = new Traslado[]{
        new Traslado(11, 2, 1, 20004, 1),
        new Traslado(12, 2, 1, 20003, 2),
        new Traslado(13, 2, 1, 20002, 3),
        new Traslado(14, 2, 1, 20001, 4),
        new Traslado(15, 2, 1, 20000, 5),
    };
    system.registrarTraslados(nuevos);
    int i =0;
    while(i<5){
        int actual = system.despacharMasRedituables(1).get(0);
        assertEquals(actual, nuevos[i].id());
        i++;
    }
    }


    @Test
    void agregar_a_sistema_vacio(){ // agregar a un best effort sin traslados registrados (puede pasar cuando se hayan despachado todoso simplemente se cargo así)
        BestEffort system = new BestEffort(extraCiud, new Traslado[0]);

        Traslado[] nuevo = new Traslado[]{new Traslado(1,0,7,200,2)};

        system.registrarTraslados(nuevo);

        ArrayList<Integer> despacho = system.despacharMasAntiguos(1);
        
        assertSetEquals(new ArrayList<>(Arrays.asList(1)), despacho);
    }

    // Tests sobre el despache de traslados (son análogos para despacharMasRedituables y despacharMasAntiguos):
    @Test
    void despacho_comun(){ //para chequear que las posiciones se actualicen correctamente (no se como hacerlo sin debugger)
        BestEffort system = new BestEffort(extraCiud, extraTras);

        ArrayList<Integer> despacho = system.despacharMasAntiguos(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(3)), despacho);

        ArrayList<Integer> despacho2 = system.despacharMasRedituables(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(10)), despacho2);

    }


    @Test
    void despachar_todos(){ //Despacha todo lo que haya
        BestEffort system = new BestEffort(extraCiud, extraTras);
        
        ArrayList<Integer> despacho_algunos = system.despacharMasAntiguos(5);
        ArrayList<Integer> despacho_el_resto = system.despacharMasRedituables(5);
        for (int i = 0; i < despacho_el_resto.size(); i++) {
            Integer actual = despacho_el_resto.get(i);
            despacho_algunos.add(actual);
        }

        assertSetEquals(new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10)), despacho_algunos);
    }


    @Test
    void despacho_mayor_a_cdad_traslados(){ // Pido despachar más de lo que hay (o sea despacho todo)
        BestEffort system = new BestEffort(extraCiud, extraTras);
        
        ArrayList<Integer> despachoBFD = system.despacharMasAntiguos(15);

        assertSetEquals(new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10)), despachoBFD);
    }


    @Test
    void el_mas_antiguo_tambien_es_el_mas_redituable(){ //despacho un elem que sea raíz en ambos heap
        BestEffort system = new BestEffort(extraCiud, extraTras);

        Traslado[] nuevo = new Traslado[]{new Traslado(11,5,12,100000,0)}; //Es raíz en ambos heap

        system.registrarTraslados(nuevo);

        ArrayList<Integer> despacho = system.despacharMasRedituables(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(11)), despacho);
    }


    @Test 
    void despacho_con_desempate_por_id(){ 
        BestEffort system = new BestEffort(extraCiud, extraTras);

        Traslado[] nuevo = new Traslado[]{new Traslado(11,5,12,15500,12)}; //Es raíz en ambos heap

        system.registrarTraslados(nuevo);

        ArrayList<Integer> despacho = system.despacharMasRedituables(1);

        assertSetEquals(new ArrayList<>(Arrays.asList(10)), despacho);
    }


    // Tests sobre ciudadConMayorSuperavit:
    @Test
    void mayor_superavit_unico(){
        BestEffort system = new BestEffort(extraCiud,extraTras);

        system.despacharMasAntiguos(1); //despacha el 3 de extraTras, origen 7 destino 9

        assertEquals(system.ciudadConMayorSuperavit(), 7);
    }


    @Test
    void mayor_superavit_con_desempate_por_id(){
        BestEffort system = new BestEffort(extraCiud,extraTras);

        Traslado[] nuevo = new Traslado[]{new Traslado(11,6,3,2500,2)};
        system.registrarTraslados(nuevo);
        system.despacharMasAntiguos(2); 
        //despacha el 3 de extraTras, origen 7 destino 9
        //despacha el 11 de extraTras, origen 6 destino 3
        //ganaria 6

        assertEquals(6, system.ciudadConMayorSuperavit());
    }


    @Test
    void todas_las_ciudades_tienen_igual_superavit(){ // no hubo ningún traslado, y todas las ciudades tienen superávit 0
        BestEffort system = new BestEffort(extraCiud,extraTras);

        assertEquals(0, system.ciudadConMayorSuperavit());
    }


    @Test
    void otra_ciudad_supera_en_superavit(){ // un traslado pasa a otro en superávit, por lo que se debe transicionar correctamente
        BestEffort system = new BestEffort(extraCiud,extraTras);

        Traslado[] nuevo = new Traslado[]{new Traslado(11,6,3,2500000,2)};
        system.registrarTraslados(nuevo);

        system.despacharMasAntiguos(2);

        assertEquals(6, system.ciudadConMayorSuperavit());
    }


    // Tests sobre mayorGanancia y mayorPerdida:
    @Test
    void mayor_ganancia_unica(){ // Ganador incontesteado 
        BestEffort system = new BestEffort(extraCiud,extraTras);

        Traslado[] nuevo = new Traslado[]{new Traslado(11,6,3,2500000,2)};
        system.registrarTraslados(nuevo);

        system.despacharMasAntiguos(5);

        assertSetEquals(new ArrayList<>(Arrays.asList(6)), system.ciudadesConMayorGanancia());
    }


    @Test
    void mayor_ganancia_multiple(){ //Empate en ganancia, devuelve todos los que tengan ese valor
        BestEffort system = new BestEffort(extraCiud,extraTras);
        Traslado[] nuevo = new Traslado[]{
            new Traslado(11,0,8,20000,2000),
            new Traslado(12,7,9,19000,2001),
            new Traslado(13,11,4,19900,2002)};

            system.registrarTraslados(nuevo);
            system.despacharMasAntiguos(13);
            //Deberia devolverme [0,7,11]
            assertEquals(new ArrayList<>(Arrays.asList(0,7,11)), system.ciudadesConMayorGanancia());
            assertEquals(7, system.ciudadConMayorSuperavit());
    }


    @Test
    void todas_las_ciudades_tienen_igual_ganancia(){ //Todas las ciudades son las mayores
        // creo un arreglo y asigno igual ganancia a todos de tal manera que todas las ciudades aparezcan una vez como origen
        Traslado[] nuevo = new Traslado[] { new Traslado(1, 0, 1, 3, 400),
                                            new Traslado(2, 1, 1, 3, 401),
                                            new Traslado(3, 2, 1, 3, 403),
                                            new Traslado(6, 3, 1, 3, 404),
                                            new Traslado(14, 4, 1, 3, 501),
                                            new Traslado(4, 5, 1, 3, 502),
        };

        BestEffort system = new BestEffort(6, nuevo);
        system.despacharMasRedituables(3);
        system.despacharMasAntiguos(4); // a propósito para que se pase 
        assertSetEquals(system.ciudadesConMayorGanancia(), new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5)));
        assertEquals(system.ciudadesConMayorPerdida(), new ArrayList<>(Arrays.asList(1)));
    }


    @Test
    void otra_ciudad_supera_en_ganancias(){ //Varios elem tenian el top y uno nuevo los pasa
        int cantCiudades= 8;
        Traslado[] nuevo = new Traslado[]{
            new Traslado(1,0,5,1000,1),
            new Traslado(2,1,5,1000,2),
            new Traslado(3,2,5,1000,3),
            new Traslado(4,3,5,1000,4),
            new Traslado(5,4,5,1000,5)};
            BestEffort system = new BestEffort(cantCiudades,nuevo);
            system.despacharMasAntiguos(5);

            assertEquals(new ArrayList<>(Arrays.asList(0,1,2,3,4)), system.ciudadesConMayorGanancia());
            assertEquals(new ArrayList<>(Arrays.asList(5)), system.ciudadesConMayorPerdida());

            Traslado t = new Traslado(6,6,7,10000,6);
            Traslado[] monoList = new Traslado[]{t};
            system.registrarTraslados(monoList);
            system.despacharMasAntiguos(1);

            assertEquals(new ArrayList<>(Arrays.asList(6)), system.ciudadesConMayorGanancia());
            assertEquals(6, system.ciudadConMayorSuperavit());
            assertEquals(new ArrayList<>(Arrays.asList(7)), system.ciudadesConMayorPerdida());
    }


    @Test
    void stress_ganancia(){
        int i = 0;
        int cantCiudades = 1000;
        Traslado[] lista = new Traslado[cantCiudades-1];
        BestEffort system = new BestEffort(cantCiudades,new Traslado[0]);
        ArrayList<Integer> perdidas = new ArrayList<Integer>();
        while (i < cantCiudades - 1){
            lista[i] = new Traslado(i, cantCiudades - 1, i, 100, i);
            perdidas.add(i);
            i++;
        }
        system.registrarTraslados(lista);
        system.despacharMasAntiguos(cantCiudades - 1);
        ArrayList<Integer> mayoresPerdidas = system.ciudadesConMayorPerdida();
        assertSetEquals(perdidas, mayoresPerdidas);
        //assertTrue(res);
        assertEquals(system.ciudadesConMayorGanancia(),new ArrayList<>(Arrays.asList(cantCiudades-1)));
        assertEquals(system.ciudadConMayorSuperavit(), cantCiudades - 1);
    }
    

    @Test
    void otra_ciudad_supera_en_perdidas(){ //Varios elem tenian el top y uno nuevo los pasa
        int cantCiudades = 8;
        Traslado[] nuevo = new Traslado[] {
            new Traslado(1,5,0,1000,1),
            new Traslado(2,5,1,1000,2),
            new Traslado(3,5,2,1000,3),
            new Traslado(4,5,3,1000,4),
            new Traslado(5,5,4,1000,5)};
            
            BestEffort system = new BestEffort(cantCiudades,nuevo);
            system.despacharMasAntiguos(5);

            assertEquals(new ArrayList<>(Arrays.asList(0,1,2,3,4)), system.ciudadesConMayorPerdida());
            assertEquals(new ArrayList<>(Arrays.asList(5)), system.ciudadesConMayorGanancia());
            assertEquals(5, system.ciudadConMayorSuperavit());

            Traslado t = new Traslado(6,6,7,100000,6);
            Traslado[] monoList = new Traslado[]{t};
            system.registrarTraslados(monoList);
            system.despacharMasAntiguos(1);
            
            assertEquals(new ArrayList<>(Arrays.asList(6)), system.ciudadesConMayorGanancia());
            assertEquals(6, system.ciudadConMayorSuperavit());
            assertEquals(new ArrayList<>(Arrays.asList(7)), system.ciudadesConMayorPerdida());
    }


    @Test // este test chequea que hay más de una ciudad con ganancia y pérdida igual. se podría separar
    void mayor_ganancia_y_perdida_multiples(){ //Empate en ganancia, devuelve todos los que tengan ese valor
        Traslado[] nuevo = new Traslado[] { new Traslado(1, 2, 5, 3, 400),
                                            new Traslado(2, 2, 1, 3, 401),
                                            new Traslado(3, 5, 2, 3, 403),
                                            new Traslado(6, 5, 3, 3, 404),
                                            new Traslado(14, 1, 2, 3, 501),
                                            new Traslado(4, 4, 5, 3, 502),
        };

        BestEffort dos_ganadores = new BestEffort(6, nuevo);
        dos_ganadores.despacharMasAntiguos(3);
        dos_ganadores.despacharMasRedituables(515); // a propósito para que se pase 
        
        assertSetEquals(dos_ganadores.ciudadesConMayorPerdida(), new ArrayList<>(Arrays.asList(2, 5)));
        assertEquals(dos_ganadores.ciudadesConMayorGanancia(), new ArrayList<>(Arrays.asList(2, 5)));
    }


    @Test
    void todas_las_ciudades_tienen_igual_perdida() { //Todas las ciudades son las mayores
        // creo un arreglo y asigno igual ganancia a todos de tal manera que todas las ciudades aparezcan una vez como destino
        Traslado[] nuevo = new Traslado[] { new Traslado(1, 2, 0, 3, 400),
                                            new Traslado(2, 0, 1, 3, 401),
                                            new Traslado(3, 5, 2, 3, 403),
                                            new Traslado(6, 2, 3, 3, 404),
                                            new Traslado(14, 1, 4, 3, 501),
                                            new Traslado(4, 4, 5, 3, 502),
        };

        BestEffort system = new BestEffort(6, nuevo);
        system.despacharMasAntiguos(3);
        system.despacharMasRedituables(515); // a propósito para que se pase 

        assertSetEquals(system.ciudadesConMayorPerdida(), new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5)));
        assertEquals(system.ciudadesConMayorGanancia(), new ArrayList<>(Arrays.asList(2)));
    }


    @Test
    void stress_perdida(){
        int i=0;
        int cantCiudades = 1000;
        Traslado[] lista = new Traslado[cantCiudades-1];
        BestEffort system = new BestEffort(cantCiudades,new Traslado[0]);
        ArrayList<Integer> ganancias = new ArrayList<Integer>();
        while (i<cantCiudades-1){
            lista[i] = new Traslado(i, i, cantCiudades-1, 100, i);
            ganancias.add(i);
            i++;
        }
        system.registrarTraslados(lista);
        system.despacharMasAntiguos(cantCiudades-1);
        ArrayList<Integer> mayoresGanancias = system.ciudadesConMayorGanancia();
        assertSetEquals(ganancias, mayoresGanancias);
        assertEquals(system.ciudadesConMayorPerdida(),new ArrayList<>(Arrays.asList(cantCiudades-1)));
        assertEquals(system.ciudadConMayorSuperavit(), 0);
        
    }

    int size = 10000;
    private Integer clave(Integer i) {
        return size * ((i * (i + 100) * i) % size) + i;
    }
    @Test
    void stressTestEffort(){
        int cant_traslados = 10000;
        Traslado[] lista_traslados = new Traslado[cant_traslados];
        int i = 0;
        while (i <cant_traslados){
            Traslado actual = new Traslado(i,i,size-1-i,clave(i),i+1);
            lista_traslados[i]=actual;
            i++;
        }
        BestEffort system = new BestEffort(size, lista_traslados);
        system.despacharMasAntiguos(cant_traslados);
        Traslado biggerTraslado = new Traslado(cant_traslados+1,0,1,1000000000,1);
        Traslado[] nuevo = new Traslado[1];
        nuevo[0]=biggerTraslado;
        system.registrarTraslados(nuevo);
        system.despacharMasAntiguos(1);
        assertEquals(biggerTraslado.origen(),system.ciudadConMayorSuperavit());
        assertEquals(new ArrayList<>(Arrays.asList(biggerTraslado.origen())),system.ciudadesConMayorGanancia());
        assertEquals(new ArrayList<>(Arrays.asList(biggerTraslado.destino())),system.ciudadesConMayorPerdida());

    }
}
