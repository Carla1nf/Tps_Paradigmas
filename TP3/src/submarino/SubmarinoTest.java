package submarino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class SubmarinoTest {
    public final String EXPLODE_MESSAGE = "Se destruyó el submarino por exceso de chocolate";
    private final Direccionalidad norte =  new DireccionN();
    private final Direccionalidad sur =  new DireccionS();
    private final Direccionalidad este =  new DireccionE();
    private final Direccionalidad oeste =  new DireccionO();
    private Submarino submarino;

    @BeforeEach public void setUp() {
        submarino = new Submarino(norte, new Coord(0,0), new ProfundidadSuperficie());
    }


    @Test public void test00NewSubmarinoCondicionesIniciales(){
        SubmarinoInicial();
    }

    private void SubmarinoInicial() {
        assertEquals(new Coord(0,0), submarino.getPosition());
        assertEquals(norte, submarino.getDirection());
        assertEquals(0, submarino.getProfundidad());
    }

    @Test public void test01ComandoVacio(){
        submarino.ejecutarComandos("");
        SubmarinoInicial();
    }

    @Test public void test02GirarSinMoverse(){
        submarino.ejecutarComandos("lr");
        SubmarinoInicial();
    }

    @Test public void test03Descender(){
        assertEquals(submarino.ejecutarComandos("d").getProfundidad(), -1);
    }

    @Test public void test04Ascender(){
        assertEquals(submarino.ejecutarComandos("du").getProfundidad(), 0);
    }

    @Test public void test05AvanzarAlNorte(){
        assertEquals(submarino.ejecutarComandos("f").getPosition(),new Coord(0,1));
    }

    @Test public void test06GirarNorteIzquierda(){
        assertEquals(submarino.ejecutarComandos("l").getDirection(), oeste);
    }

    @Test public void test07GirarNorteDerecha(){
        assertEquals(submarino.ejecutarComandos("r").getDirection(), este);
    }

    @Test public void test08GirarOesteIzquierda(){
        assertEquals(submarino.ejecutarComandos("ll").getDirection(), sur);
    }

    @Test public void test09GirarOesteDerecha(){
        assertEquals(submarino.ejecutarComandos("lr").getDirection(), norte);
    }

    @Test public void test10GirarSurIzquierda(){
        assertEquals(submarino.ejecutarComandos("lll").getDirection(), este);
    }

    @Test public void test11GirarSurDerecha(){
        assertEquals(submarino.ejecutarComandos("llr").getDirection(), oeste);
    }

    @Test public void test12GirarEsteIzquierda(){
        assertEquals(submarino.ejecutarComandos("rl").getDirection(), norte);
    }

    @Test public void test13GirarEsteDerecha(){
        assertEquals(submarino.ejecutarComandos("rr").getDirection(), sur);
    }

    @Test public void test14AvanzarAlOeste(){
        assertEquals(submarino.ejecutarComandos("lf").getPosition(),new Coord(-1,0));
    }

    @Test public void test15AvanzarAlEste(){
        assertEquals(submarino.ejecutarComandos("rf").getPosition(),new Coord(1,0));
    }

    @Test public void test16AvanzarAlSur(){
        assertEquals(submarino.ejecutarComandos("rrf").getPosition(),new Coord(0,-1));
    }

    @Test public void test17AvanzarYDescender(){
        assertEquals(submarino.ejecutarComandos("fd").getPosition(),new Coord(0,1));
        assertEquals(submarino.getProfundidad(), -1);
    }

    @Test public void test18AvanzarYAscender(){
        assertEquals(submarino.ejecutarComandos("duf").getPosition(),new Coord(0,1));
        assertEquals(submarino.getProfundidad(), 0);
    }



    @Test public void test19AscenderDesdeLaSuperficie(){
        assertEquals(submarino.ejecutarComandos("u").getProfundidad(),0);
    }

    @Test public void test20DescencerProfundo(){
        assertEquals(submarino.ejecutarComandos("ddd").getProfundidad(), -3);
    }



    @Test public void test21AscenderDesdeProfundidad(){
        assertEquals(submarino.ejecutarComandos("ddd").getProfundidad(), -3);
        assertEquals(submarino.ejecutarComandos("uuu").getProfundidad(), 0);
    }

    @Test public void test22LiberarCapsulaEnSuperficieYenPrimerNivel(){
        submarino.ejecutarComandos("mdmum");
        SubmarinoInicial();
    }

    @Test public void test23LiberarLaCapsulaEnProfundidad(){
        submarino.ejecutarComandos("ddd");
        Exception e = assertThrows( RuntimeException.class, () -> submarino.ejecutarComandos("m") );
        assertEquals(EXPLODE_MESSAGE, e.getMessage() );
    }

    @Test public void test24VariasAcciones(){
        assertEquals(submarino.ejecutarComandos("rflmddlufffrrlf").getPosition(),new Coord(-2,1));
        assertEquals(submarino.getProfundidad(), -1);
    }
}
