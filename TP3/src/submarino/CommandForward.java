package submarino;

public class CommandForward extends Comando{
    public boolean canHandle(char command) {
        return command == 'f';
    }
    public void execute(Submarino submarino) {
        submarino.forward();
    }
}
