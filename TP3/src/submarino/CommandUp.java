package submarino;

public class CommandUp extends Comando{
    public boolean canHandle(char command) {
        return command == 'u';
    }
    public void execute(Submarino submarino) {
        submarino.ascend();
    }
}
