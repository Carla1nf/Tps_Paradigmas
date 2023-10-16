package submarino;

public class ProfundidadNivelOndo extends Profundidad{
    private final Profundidad anteriorProfundidad;

    ProfundidadNivelOndo(int z, Profundidad anteriorProfundidad) {
        this.z = z;
        this.anteriorProfundidad = anteriorProfundidad;
    }
    public Profundidad descender() {
        return new ProfundidadNivelOndo(z-1,this);
    }

    public Profundidad ascender() {
        return anteriorProfundidad;
    }

    public void liberarCapsula() {
        throw new UnsupportedOperationException("Se destruyó el submarino por exceso de chocolate");
    }

}
