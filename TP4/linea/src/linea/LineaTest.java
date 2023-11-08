package linea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LineaTest {

    @Test public void test00CrearTablero(){
        Linea game = new Linea(1,1,'C');
        assertEquals("|-|\n", game.show());
    }

    @Test public void test01TableroConMultiplesFilasYColumnas(){
        Linea game = new Linea(5,5,'C');
        assertEquals("|-----|\n|-----|\n|-----|\n|-----|\n|-----|\n", game.show());
    }

    @Test public void test02PonerFichaEnLugarAdecuado(){
        Linea game = new Linea(1,1,'C');
        game.playRedkAt(0);
        assertEquals("|X|\n", game.show());
    }

    @Test public void test03PonerFichaFueraDelTablero(){
        Linea game = new Linea(1,1,'C');
        assertThrows(RuntimeException.class, () -> game.playRedkAt(1));
    }

    @Test public void test04PonerFichaEnColumnaLlena(){
        Linea game = new Linea(2,2,'C');
        game.playRedkAt(0);
        game.playBlueAt(0);
        assertThrows(RuntimeException.class, () -> game.playRedkAt(0));
    }

    @Test public void test05JuegoTerminadoConTableroLleno(){
        Linea game = new Linea(1,1,'C');
        game.playRedkAt(0);
        assertTrue(game.finished());
    }

    @Test public void test06AzulNoPuedeComenzar(){
        Linea game = new Linea(1,1,'C');
        assertThrows(RuntimeException.class, () -> game.playBlueAt(0));
    }

    @Test public void test07RojoNoPuedeJugarDosVecesSeguidas(){
        Linea game = new Linea(2,2,'C');
        game.playRedkAt(0);
        assertThrows(RuntimeException.class, () -> game.playRedkAt(0));
    }

    @Test public void test08AzulNoPuedeJugarDosVecesSeguidas(){
        Linea game = new Linea(2,2,'C');
        game.playRedkAt(0);
        game.playBlueAt(1);
        assertThrows(RuntimeException.class, () -> game.playBlueAt(0));
    }

}
