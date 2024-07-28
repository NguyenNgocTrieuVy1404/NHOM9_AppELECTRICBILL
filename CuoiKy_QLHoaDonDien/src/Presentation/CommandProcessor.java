package Presentation;

import java.sql.SQLException;

public class CommandProcessor {
    //SingleTon
    private static CommandProcessor commandProcessorRemote = null;
    
    CommandProcessor(){}

    public static CommandProcessor makeCommandProcessor(){
        if(commandProcessorRemote == null){
            commandProcessorRemote = new CommandProcessor();
        }
        return commandProcessorRemote;
    }
    public void execute(Command commandRemote) throws ClassNotFoundException, SQLException {
        commandRemote.execute();
    }
}
