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

    private void ejecutarComandos(String comand) {
        submarino.ejecutarComandos(comand);
    }
    private int getSubmarineZ() {
        return submarino.profundidad.getZ();
    }


    @Test public void test00NewSubmarinoCondicionesIniciales(){
        assertEquals(new Coord(0,0), submarino.coord);
    }
    @Test public void test01GirarSinMoverse(){
        assertEquals( submarino.direccion, norte);
        ejecutarComandos("l");
        assertEquals( submarino.direccion, new DireccionO());
        ejecutarComandos("r");
        assertEquals( submarino.direccion, norte);
    }

    @Test public void test02Descender(){
        ejecutarComandos("d");
        assertEquals(getSubmarineZ(), -1);
    }

    @Test public void test03Ascender(){
        ejecutarComandos("du");
        assertEquals(getSubmarineZ(), 0);
    }

    @Test public void test04Avanzar(){
        ejecutarComandos("f");
        assertEquals( new Coord(0,1),submarino.coord);
    }

    @Test public void test05AvanzarYDescender(){
        ejecutarComandos("df");
        assertEquals( new Coord(0,1),submarino.coord);
        assertEquals(getSubmarineZ(), -1);
    }

    @Test public void test06AvanzarYAscender(){
        ejecutarComandos("duf");
        assertEquals( new Coord(0,1),submarino.coord);
        assertEquals(getSubmarineZ(), 0);
    }

    @Test public void test07GirarYAvanzar(){
        ejecutarComandos("lf");
        assertEquals( new Coord(-1,0),submarino.coord);
    }

    @Test public void test08VariasAcciones(){
        submarino.ejecutarComandos("rflddlufffrrlf");
        assertEquals( new Coord(-2,1),submarino.coord);
        assertEquals(getSubmarineZ(), -1);
    }

    @Test public void test09AscenderDesdeLaSuperficie(){
        assertEquals(getSubmarineZ(),0);
        submarino.ejecutarComandos("u");
        assertEquals(getSubmarineZ(),0);
    }



    @Test public void test10DescencerProfundo(){
        ejecutarComandos("ddddd");
        assertEquals(getSubmarineZ(), -5);
    }



    @Test public void test11AscenderDesdeProfundidad(){
        ejecutarComandos("ddddd");
        assertEquals(getSubmarineZ(), -5);
        ejecutarComandos("uuuuu");
        assertEquals(getSubmarineZ(), 0);
    }

    @Test public void test12LiberarLaCapsulaEnProfundidad(){
        ejecutarComandos("dd");
        Exception e = assertThrows( RuntimeException.class, () -> ejecutarComandos("m") );
        assertEquals( "Se destruyó el submarino por exceso de chocolate", e.getMessage() );
    }

    @Test public void test13InsistirConEmerger(){
        ejecutarComandos("uuuuuuuuu");
        assertEquals(getSubmarineZ(),0);
    }

   @Test public void test14IntentarLiberarAMayorProfundidad(){
       Exception e = assertThrows( RuntimeException.class, () -> ejecutarComandos("ddddddddm") );
       assertEquals( "Se destruyó el submarino por exceso de chocolate", e.getMessage() );
    }
     @Test public void test15LiberarEnZonaSeguraSinErrores(){
         ejecutarComandos("m"); // Superficie
         ejecutarComandos("dm"); // Primer Nivel
    }

    @Test public void test16BajarSubirYLiberarConExito(){
        ejecutarComandos("ddduuum");
    }






}
