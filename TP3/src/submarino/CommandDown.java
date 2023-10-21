package submarino;

public class CommandDown extends Comando{
    public boolean canHandle(char command) {
        return command == 'd';
    }
    public void execute(Submarino submarino) {
        submarino.down();
    }
}
