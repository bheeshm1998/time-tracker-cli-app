package org.anand.timeTracker.utils;

import org.anand.timeTracker.model.Args;
import org.anand.timeTracker.model.Command;
import org.anand.timeTracker.model.TaskCategory;

public class ArgsUtil {

    public static Args parseArgs(String[] args){
        Args arguments = new Args();
        if(validateArgs(args)){
            Command command = switch (args[0]){
                case "start" -> Command.START_TASK;
                case "stop" -> Command.STOP_TASK;
                case "report" -> args[1].equals("task") ? Command.REPORT_TASK : args[1].equals("category") ? Command.REPORT_CATEGORY : null;
                case "restart" -> Command.RESTART;
                case "exit" -> Command.EXIT;
                default -> throw new RuntimeException(("Invalid Argument"));
            };
            arguments.setCommand(command);
            if(command.equals(Command.START_TASK) || command.equals(Command.STOP_TASK)){
                arguments.setTaskName(args[1]);
                arguments.setCategoryName(args.length > 2 ? args[2] : TaskCategory.NONE);
            }
            return arguments;

        } else{
            throw new RuntimeException(("Error! Insufficient Arguments"));
        }
    }

    private static boolean validateArgs(String[] args){
        if(args.length < 1){
            return false;
        }
        if(args.length < 2){
            if(args[0].equals("restart") || args[0].equals("exit")){
                return true;
            } else{
                return false;
            }
        }
        return true;
    }
}
