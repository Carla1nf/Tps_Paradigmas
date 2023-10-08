package submarino;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubmarinoTest {
    @BeforeEach public void setUp() {}

    @Test public void test00NewSubmarinoCondicionesIniciales(){
        Submarino submarino = new Submarino();
        initialConditions(submarino);
        assertEquals( submarino.direccion, 0);
    }
    @Test public void test01GirarSinMoverse(){
        Submarino submarino = new Submarino();
        submarino.comando("l");
        initialConditions(submarino);
        assertEquals( Math.PI/2, submarino.direccion);
        submarino.comando("r");
        initialConditions(submarino);
        assertEquals(  0,submarino.direccion);
    }

    @Test public void test02Descender(){
        Submarino submarino = new Submarino();
        submarino.comando("d");
        assertEquals( submarino.posicionZ, -1);
    }

    @Test public void test03Ascender(){
        Submarino submarino = new Submarino();
        submarino.comando("d");
        submarino.comando("u");
        assertEquals( submarino.posicionZ, 0);
    }

    @Test public void test04Avanzar(){
        Submarino submarino = new Submarino();
        submarino.comando("f");
        assertEquals( submarino.posicionX, 1);
        assertEquals( submarino.posicionY, 0);
    }

    @Test public void test05GirarYAvanzar(){
        Submarino submarino = new Submarino();
        submarino.comando("l");
        submarino.comando("f");
        assertEquals( submarino.posicionX, 0);
        assertEquals( submarino.posicionY, 1);
    }

    @Test public void test06VariasAcciones(){
        Submarino submarino = new Submarino();
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
        assertEquals( submarino.posicionX, -2);
        assertEquals( submarino.posicionY, 1);
        assertEquals( submarino.posicionZ, -1);
    }

    @Test public void test07AscenderDesdeLaSuperficie(){
        Submarino submarino = new Submarino();
        assertEquals( submarino.posicionZ,0);
        submarino.ascender();
        assertEquals( submarino.posicionZ,0);
    }

    @Test public void test08LiberarLaCapsulaEnProfundidad(){
        Submarino submarino = new Submarino();
        submarino.comando("d");
        submarino.comando("d");
        Exception e = assertThrows( RuntimeException.class, () -> submarino.comando("m") );
        assertEquals( "Se destruyó el submarino por exceso de chocolate", e.getMessage() );
    }


    private static void initialConditions(Submarino submarino) {
        assertTrue( submarino.estaEnLaSuperficie());
        assertEquals( submarino.posicionX, 0);
        assertEquals( submarino.posicionY, 0);
        assertEquals( submarino.posicionZ, 0);
    }


}
