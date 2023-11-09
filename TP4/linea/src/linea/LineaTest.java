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

    @Test public void test03PonerFichaEnTableroGrande(){
        Linea game = new Linea(5,5,'C');
        game.playRedkAt(0);
        assertEquals("|-----|\n|-----|\n|-----|\n|-----|\n|X----|\n", game.show());
    }

    @Test public void test04PonerFichaFueraDelTablero(){
        Linea game = new Linea(1,1,'C');
        assertThrows(RuntimeException.class, () -> game.playRedkAt(1));
    }

    @Test public void test05PonerFichaEnColumnaLlena(){
        Linea game = new Linea(2,2,'C');
        game.playRedkAt(0);
        game.playBlueAt(0);
        assertThrows(RuntimeException.class, () -> game.playRedkAt(0));
    }

    @Test public void test06JuegoTerminadoConTableroLleno(){
        Linea game = new Linea(1,1,'C');
        game.playRedkAt(0);
        assertTrue(game.finished());
    }

    @Test public void test07AzulNoPuedeComenzar(){
        Linea game = new Linea(1,1,'C');
        assertThrows(RuntimeException.class, () -> game.playBlueAt(0));
    }

    @Test public void test08RojoNoPuedeJugarDosVecesSeguidas(){
        Linea game = new Linea(2,2,'C');
        game.playRedkAt(0);
        assertThrows(RuntimeException.class, () -> game.playRedkAt(0));
    }

    @Test public void test09AzulNoPuedeJugarDosVecesSeguidas(){
        Linea game = new Linea(2,2,'C');
        game.playRedkAt(0);
        game.playBlueAt(1);
        assertThrows(RuntimeException.class, () -> game.playBlueAt(0));
    }
    @Test public void test10VictoriaRojoHorizontalEnA(){
        Linea game = new Linea(4,4,'A');
        game.playRedkAt(0);
        game.playBlueAt(0);
        game.playRedkAt(1);
        game.playBlueAt(1);
        game.playRedkAt(2);
        game.playBlueAt(2);
        game.playRedkAt(3);
        assertTrue(game.finished());
    }

    @Test public void test11VictoriaRojoVerticalEnA(){
        Linea game = new Linea(4,4,'A');
        game.playRedkAt(0);
        game.playBlueAt(1);
        game.playRedkAt(0);
        game.playBlueAt(1);
        game.playRedkAt(0);
        game.playBlueAt(1);
        game.playRedkAt(0);
        assertTrue(game.finished());
    }

    @Test public void test12VictoriaRojoDiagonalDerechaEnB(){
        Linea game = new Linea(4,4,'B');
        game.playRedkAt(0);
        game.playBlueAt(1);
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(2);
        game.playBlueAt(3);
        game.playRedkAt(3);
        game.playBlueAt(3);
        game.playRedkAt(2);
        game.playBlueAt(0);
        game.playRedkAt(3);
        assertTrue(game.finished());
    }

    @Test public void test13VictoriaRojoDiagonalIzquierdaEnC(){
        Linea game = new Linea(5,4,'C');
        game.playRedkAt(3);
        game.playBlueAt(2);
        game.playRedkAt(2);
        game.playBlueAt(1);
        game.playRedkAt(1);
        game.playBlueAt(0);
        game.playRedkAt(1);
        game.playBlueAt(0);
        game.playRedkAt(0);
        game.playBlueAt(4);
        game.playRedkAt(0);
        assertTrue(game.finished());
    }

    @Test public void test14VictoriaAzulHorizontalEnA(){
        Linea game = new Linea(5,4,'A');
        game.playRedkAt(4);
        game.playBlueAt(0);
        game.playRedkAt(4);
        game.playBlueAt(1);
        game.playRedkAt(4);
        game.playBlueAt(2);
        game.playRedkAt(0);
        game.playBlueAt(3);
        assertTrue(game.finished());
    }

    @Test public void test15VictoriaAzulVerticalEnA(){
        Linea game = new Linea(4,4,'A');
        game.playRedkAt(0);
        game.playBlueAt(1);
        game.playRedkAt(0);
        game.playBlueAt(1);
        game.playRedkAt(0);
        game.playBlueAt(1);
        game.playRedkAt(0);
        assertTrue(game.finished());
    }

    @Test public void test16VictoriaAzulDiagonalDerechaEnB(){
        Linea game = new Linea(4,4,'B');
        game.playRedkAt(1);
        game.playBlueAt(0);
        game.playRedkAt(2);
        game.playBlueAt(1);
        game.playRedkAt(2);
        game.playBlueAt(2);
        game.playRedkAt(3);
        game.playBlueAt(3);
        game.playRedkAt(3);
        game.playBlueAt(3);
        assertTrue(game.finished());
    }

    @Test public void test17VictoriaAzulDiagonalIzquierdaEnC(){
        Linea game = new Linea(4,4,'C');
        game.playRedkAt(0);
        game.playBlueAt(3);
        game.playRedkAt(2);
        game.playBlueAt(2);
        game.playRedkAt(1);
        game.playBlueAt(1);
        game.playRedkAt(0);
        game.playBlueAt(1);
        game.playRedkAt(0);
        game.playBlueAt(0);
        assertTrue(game.finished());
    }

    @Test public void test18NOVictoriaHorizontalEnB(){
        Linea game = new Linea(4,4,'B');
        game.playRedkAt(0);
        game.playBlueAt(0);
        game.playRedkAt(1);
        game.playBlueAt(1);
        game.playRedkAt(2);
        game.playBlueAt(2);
        game.playRedkAt(3);
        assertFalse(game.finished());
    }

    @Test public void test19NOVictoriaVerticalEnB(){
        Linea game = new Linea(4,4,'B');
        game.playRedkAt(0);
        game.playBlueAt(1);
        game.playRedkAt(0);
        game.playBlueAt(1);
        game.playRedkAt(0);
        game.playBlueAt(1);
        game.playRedkAt(0);
        assertFalse(game.finished());
    }

    @Test public void test20NOVictoriaDiagonalEnA(){
        Linea game = new Linea(4,4,'A');
        game.playRedkAt(0);
        game.playBlueAt(1);
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(2);
        game.playBlueAt(3);
        game.playRedkAt(3);
        game.playBlueAt(3);
        game.playRedkAt(2);
        game.playBlueAt(0);
        game.playRedkAt(3);
        assertFalse(game.finished());
    }
}