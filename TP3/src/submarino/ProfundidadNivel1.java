package submarino;

public class ProfundidadNivel1 extends Profundidad{
    ProfundidadNivel1() {
        z = -1;
    }
    public Profundidad descender() {
        return new ProfundidadNivelOndo(-2,this);
    }

    public Profundidad ascender() {
        return new ProfundidadSuperficie();
    }

    public void liberarCapsula() {}

}
