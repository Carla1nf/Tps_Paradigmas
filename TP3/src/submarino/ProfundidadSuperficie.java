package submarino;

public class ProfundidadSuperficie extends Profundidad {
    ProfundidadSuperficie() {
        z = 0;
    }
    public Profundidad descender() {
        return new ProfundidadNivel1();
    }

    public Profundidad ascender() {
        return new ProfundidadSuperficie();
    }

    public void liberarCapsula() {}

}
