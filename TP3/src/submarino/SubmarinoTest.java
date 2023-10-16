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
    private Coord inicio;

    @BeforeEach public void setUp() {
        norte = new DireccionN();
        inicio = new Coord(0,0);
        submarino = new Submarino(norte,inicio);
    }

    @Test public void test00NewSubmarinoCondicionesIniciales(){
        assertEquals(new Coord(0,0), submarino.coord);
    }
    @Test public void test01GirarSinMoverse(){
        assertEquals( submarino.direccion, norte);
        submarino.comando("l");
        assertEquals( submarino.direccion, new DireccionO());
        submarino.comando("r");
        assertEquals( submarino.direccion, norte);
    }

    @Test public void test02Descender(){
        submarino.comando("d");
        assertEquals( submarino.profundidad, -1);
    }

    @Test public void test03Ascender(){
        submarino.comando("d");
        submarino.comando("u");
        assertEquals( submarino.profundidad, 0);
    }

    @Test public void test04Avanzar(){
        submarino.comando("f");
        assertEquals( new Coord(0,1),submarino.coord);
    }

    @Test public void test05GirarYAvanzar(){
        submarino.comando("l");
        submarino.comando("f");
        assertEquals( new Coord(-1,0),submarino.coord);
    }

    @Test public void test06VariasAcciones(){
        submarino.comando("f");
        submarino.comando("l");
        submarino.comando("f");
        submarino.comando("l");
        submarino.comando("u");
        submarino.comando("f");
        submarino.comando("l");
        submarino.comando("r");
        submarino.comando("f");
        submarino.comando("f");
        submarino.comando("d");
        assertEquals( new Coord(-1,-2),submarino.coord);
        assertEquals( submarino.profundidad, -1);
    }

    @Test public void test07AscenderDesdeLaSuperficie(){
        assertEquals( submarino.profundidad,0);
        submarino.ascender();
        assertEquals( submarino.profundidad,0);
    }

    @Test public void test08LiberarLaCapsulaEnProfundidad(){
        submarino.comando("d");
        submarino.comando("d");
        Exception e = assertThrows( RuntimeException.class, () -> submarino.comando("m") );
        assertEquals( "Se destruyó el submarino por exceso de chocolate", e.getMessage() );
    }
}
