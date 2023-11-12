package linea;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
public class LineaTest {
    private Linea game;
    @BeforeEach
    public void setup(){
        game = new Linea(1,1,'C');
    }

    @Test public void test00CrearTablero(){
        assertEquals("""
                |-|
                >1<
                Turno de Rojas""", game.show());
    }

    @Test public void testErrorCrearMalElTablero(){
        assertThrowsLike(() -> new Linea(0,0,'C'), Linea.TAMANO_INVALIDO);
    }

    @Test public void test01TableroConMultiplesFilasYColumnas(){
        Linea game = new Linea(5,5,'C');
        assertEquals("""
                |-----|
                |-----|
                |-----|
                |-----|
                |-----|
                >12345<
                Turno de Rojas""", game.show());
    }

    @Test public void test02PonerFichaEnLugarAdecuado(){
        game.playRedkAt(1);
        assertEquals("""
                |X|
                >1<
                Empate!""", game.show());
    }

    @Test public void test02PonerFichaAzulEnLugarAdecuado(){
        Linea game = new Linea(2,2,'C');
        game.playRedkAt(1);
        game.playBlueAt(1);
        assertEquals("""
                |O-|
                |X-|
                >12<
                Turno de Rojas""", game.show());
    }

    @Test public void test03PonerFichaEnTableroGrande(){
        Linea game = new Linea(5,5,'C');
        game.playRedkAt(1);
        assertEquals("""
                |-----|
                |-----|
                |-----|
                |-----|
                |X----|
                >12345<
                Turno de Azules""", game.show());
    }

    @Test public void test04PonerFichaFueraDelTablero(){
        assertThrowsLike(() -> game.playRedkAt(2), Linea.FUERA_DE_TABLERO);
    }

    @Test public void test05PonerFichaEnColumnaLlena(){
        Linea game = new Linea(2,2,'C');
        game.playRedkAt(1);
        game.playBlueAt(1);
        assertThrowsLike(() -> game.playRedkAt(1), Linea.COLUMNA_LLENA);
    }

    @Test public void test06EmpateJuegoTerminadoConTableroLleno(){
        game.playRedkAt(1);
        assertTrue(game.finished());
    }

    @Test public void test07AzulNoPuedeComenzar(){
        assertThrowsLike(() -> game.playBlueAt(1), Linea.TURNO_ROJAS);
    }

    @Test public void test08RojoNoPuedeJugarDosVecesSeguidas(){
        Linea game = new Linea(2,2,'C');
        game.playRedkAt(1);
        assertThrowsLike(() -> game.playRedkAt(2), Linea.TURNO_AZULES);
    }

    @Test public void test09AzulNoPuedeJugarDosVecesSeguidas(){
        Linea game = new Linea(2,2,'C');
        game.playRedkAt(1);
        game.playBlueAt(2);
        assertThrowsLike(() -> game.playBlueAt(2), Linea.TURNO_ROJAS);
    }

    @Test public void tes10VerificarCorrectamenteElModoDeJuego(){
        assertThrowsLike(() -> new Linea(1,1,'D'), Linea.ESTRATEGIA_INVALIDA);
    }

    @Test public void testTerminarJuego(){
        game.playRedkAt(1);
        assertTrue(game.finished());
    }
    @Test public void testNoPuedoPonerFichaDespuesDeTerminado(){
        game.playRedkAt(1);
        assertTrue(game.finished());
        assertThrowsLike(() -> game.playBlueAt(1), Linea.EMPATE);
    }
    @Test public void testVictoriaRojo(){
        Linea game = new Linea(4,4,'A');
        game.playRedkAt(1);
        game.playBlueAt(1);
        game.playRedkAt(2);
        game.playBlueAt(2);
        game.playRedkAt(3);
        game.playBlueAt(3);
        game.playRedkAt(4);
        checkGameFinished(game, game.getRedChar());
        assertThrowsLike(() -> game.playBlueAt(4), Linea.JUEGO_TERMINADO);
    }
    @Test public void testVictoriaAzul(){
        Linea game = new Linea(4,4,'A');
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(3);
        game.playBlueAt(2);
        checkGameFinished(game, game.getBlueChar());
        assertThrowsLike(() -> game.playRedkAt(1), Linea.JUEGO_TERMINADO);
    }
    @Test public void testEmpate(){
        game.playRedkAt(1);
        assertTrue(game.finished());
        assertThrowsLike(() -> game.playBlueAt(1), Linea.EMPATE);
    }
    @Test public void test10VictoriaRojoHorizontalEnA(){
        Linea game = new Linea(4,4,'A');
        game.playRedkAt(1);
        game.playBlueAt(1);
        game.playRedkAt(2);
        game.playBlueAt(2);
        game.playRedkAt(3);
        game.playBlueAt(3);
        game.playRedkAt(4);
        checkGameFinished(game, game.getRedChar());
    }

    @Test public void test11VictoriaRojoVerticalEnA(){
        Linea game = new Linea(4,4,'A');
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(1);
        checkGameFinished(game, game.getRedChar());
    }

    @Test public void test12VictoriaRojoDiagonalDerechaEnB(){
        Linea game = new Linea(4,4,'B');
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(2);
        game.playBlueAt(3);
        game.playRedkAt(3);
        game.playBlueAt(4);
        game.playRedkAt(4);
        game.playBlueAt(4);
        game.playRedkAt(3);
        game.playBlueAt(1);
        game.playRedkAt(4);
        checkGameFinished(game, game.getRedChar());
    }

    @Test public void test13VictoriaRojoDiagonalIzquierdaEnC(){
        Linea game = new Linea(5,4,'C');
        game.playRedkAt(4);
        game.playBlueAt(3);
        game.playRedkAt(3);
        game.playBlueAt(2);
        game.playRedkAt(2);
        game.playBlueAt(1);
        game.playRedkAt(2);
        game.playBlueAt(1);
        game.playRedkAt(1);
        game.playBlueAt(5);
        game.playRedkAt(1);
        checkGameFinished(game, game.getRedChar());
    }

    @Test public void test14VictoriaAzulHorizontalEnA(){
        Linea game = new Linea(5,4,'A');
        game.playRedkAt(5);
        game.playBlueAt(1);
        game.playRedkAt(5);
        game.playBlueAt(2);
        game.playRedkAt(5);
        game.playBlueAt(3);
        game.playRedkAt(1);
        game.playBlueAt(4);
        checkGameFinished(game, game.getBlueChar());
    }

    @Test public void test15VictoriaAzulVerticalEnA(){
        Linea game = new Linea(4,4,'A');
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(3);
        game.playBlueAt(2);
        checkGameFinished(game, game.getBlueChar());
    }

    @Test public void test16VictoriaAzulDiagonalDerechaEnB(){
        Linea game = new Linea(4,4,'B');
        game.playRedkAt(2);
        game.playBlueAt(1);
        game.playRedkAt(3);
        game.playBlueAt(2);
        game.playRedkAt(3);
        game.playBlueAt(3);
        game.playRedkAt(4);
        game.playBlueAt(4);
        game.playRedkAt(4);
        game.playBlueAt(4);
        checkGameFinished(game, game.getBlueChar());
    }

    @Test public void test17VictoriaAzulDiagonalIzquierdaEnC(){
        Linea game = new Linea(4,4,'C');
        game.playRedkAt(1);
        game.playBlueAt(4);
        game.playRedkAt(3);
        game.playBlueAt(3);
        game.playRedkAt(2);
        game.playBlueAt(2);
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(1);
        game.playBlueAt(1);
        checkGameFinished(game, game.getBlueChar());
    }

    @Test public void test18NOVictoriaHorizontalEnB(){
        Linea game = new Linea(4,4,'B');
        game.playRedkAt(1);
        game.playBlueAt(1);
        game.playRedkAt(2);
        game.playBlueAt(2);
        game.playRedkAt(3);
        game.playBlueAt(3);
        game.playRedkAt(4);
        assertFalse(game.finished());
    }

    @Test public void test19NOVictoriaVerticalEnB(){
        Linea game = new Linea(4,4,'B');
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(1);
        assertFalse(game.finished());
    }

    @Test public void test20NOVictoriaDiagonalEnA(){
        Linea game = new Linea(4,4,'A');
        game.playRedkAt(1);
        game.playBlueAt(2);
        game.playRedkAt(2);
        game.playBlueAt(3);
        game.playRedkAt(3);
        game.playBlueAt(4);
        game.playRedkAt(4);
        game.playBlueAt(4);
        game.playRedkAt(3);
        game.playBlueAt(1);
        game.playRedkAt(4);
        assertFalse(game.finished());
    }

    private void assertThrowsLike(Executable runnable, String message) {
        String ErrorMessage = assertThrows(RuntimeException.class, runnable, "Expected error was not thrown.").getMessage();
        assertEquals(message, ErrorMessage);
    }
    private static void checkGameFinished(Linea game, char color) {
        assertTrue(game.finished());
        assertEquals(Linea.ganador, color);
    }
}
