package submarino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubmarinoTest {
    private Direccionalidad norte;
    private Submarino submarino;

    @BeforeEach public void setUp() {
        norte = new DireccionN();
        submarino = new Submarino(norte, new Coord(0,0), new ProfundidadSuperficie());
    }

    @Test public void test00NewSubmarinoCondicionesIniciales(){
        assertEquals(new Coord(0,0), submarino.coord);
    }
    @Test public void test01GirarSinMoverse(){
        assertEquals( submarino.direccion, norte);
        submarino.ejecutarComandos("l");
        assertEquals( submarino.direccion, new DireccionO());
        submarino.ejecutarComandos("r");
        assertEquals( submarino.direccion, norte);
    }

    @Test public void test02Descender(){
        submarino.ejecutarComandos("d");
        assertEquals( submarino.profundidad.getZ(), -1);
    }

    @Test public void test03Ascender(){
        submarino.ejecutarComandos("du");
        assertEquals( submarino.profundidad.getZ(), 0);
    }

    @Test public void test04Avanzar(){
        submarino.ejecutarComandos("f");
        assertEquals( new Coord(0,1),submarino.coord);
    }

    @Test public void test05AvanzarYDescender(){
        submarino.ejecutarComandos("df");
        assertEquals( new Coord(0,1),submarino.coord);
        assertEquals( submarino.profundidad.getZ(), -1);
    }

    @Test public void test06AvanzarYAscender(){
        submarino.ejecutarComandos("duf");
        assertEquals( new Coord(0,1),submarino.coord);
        assertEquals( submarino.profundidad.getZ(), 0);
    }

    @Test public void test07GirarYAvanzar(){
        submarino.ejecutarComandos("lf");
        assertEquals( new Coord(-1,0),submarino.coord);
    }

    @Test public void test08VariasAcciones(){
        submarino.ejecutarComandos("rflddlufffrrlf");
        assertEquals( new Coord(-2,1),submarino.coord);
        assertEquals( submarino.profundidad.getZ(), -1);
    }

    @Test public void test09AscenderDesdeLaSuperficie(){
        assertEquals( submarino.profundidad.getZ(),0);
        submarino.ejecutarComandos("u");
        assertEquals( submarino.profundidad.getZ(),0);
    }

    @Test public void test10DescencerProfundo(){
        submarino.ejecutarComandos("ddddd");
        assertEquals( submarino.profundidad.getZ(), -5);
    }

    @Test public void test11AscenderDesdeProfundidad(){
        submarino.ejecutarComandos("ddddd");
        assertEquals( submarino.profundidad.getZ(), -5);
        submarino.ejecutarComandos("uuuuu");
        assertEquals( submarino.profundidad.getZ(), 0);
    }

    @Test public void test12LiberarLaCapsulaEnProfundidad(){
        submarino.ejecutarComandos("dd");
        Exception e = assertThrows( RuntimeException.class, () -> submarino.ejecutarComandos("m") );
        assertEquals( "Se destruyó el submarino por exceso de chocolate", e.getMessage() );
    }
}
