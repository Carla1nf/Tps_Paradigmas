package submarino;

public class CommandRight extends Comando{
    public boolean canHandle(char command) {
        return command == 'r';
    }
    public void execute(Submarino submarino) {
        submarino.turnRight();
    }
}
