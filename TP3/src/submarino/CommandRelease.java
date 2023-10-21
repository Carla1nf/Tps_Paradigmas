package submarino;

public class CommandRelease extends Comando{
    public boolean canHandle(char command) {
        return command == 'm';
    }
    public void execute(Submarino submarino) {
        submarino.release();
    }
}
