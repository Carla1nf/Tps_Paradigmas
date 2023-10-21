package submarino;

public class CommandLeft extends Comando{
    public boolean canHandle(char command) {
        return command == 'l';
    }
    public void execute(Submarino submarino) {
        submarino.turnLeft();
    }


}
