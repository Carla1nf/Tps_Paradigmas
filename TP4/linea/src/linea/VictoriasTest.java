package linea;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VictoriasTest {

    @Test public void test09VictoriaRojoHorizontalEnA(){
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

    @Test public void test10VictoriaRojoVerticalEnA(){
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

    @Test public void test11VictoriaRojoDiagonalDerechaEnB(){
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

    @Test public void test12VictoriaRojoDiagonalIzquierdaEnC(){
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

    @Test public void test13VictoriaAzulHorizontalEnA(){
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

    @Test public void test14VictoriaAzulVerticalEnA(){
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

    @Test public void test15VictoriaAzulDiagonalDerechaEnB(){
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

    @Test public void test16VictoriaAzulDiagonalIzquierdaEnC(){
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

    @Test public void test17NOVictoriaHorizontalEnB(){
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

    @Test public void test18NOVictoriaVerticalEnB(){
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

    @Test public void test19NOVictoriaDiagonalEnA(){
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
