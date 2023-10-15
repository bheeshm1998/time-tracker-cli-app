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
                case "report" -> args[1].equals("tasks") ? Command.REPORT_TASK : args[1].equals("categories") ? Command.REPORT_CATEGORY : null;
                case "restart" -> Command.RESTART;
                case "exit" -> Command.EXIT;
                default -> throw new RuntimeException("Invalid Argument");
            };
            arguments.setCommand(command);
            if(command!= null && (command.equals(Command.START_TASK) || command.equals(Command.STOP_TASK))){
                arguments.setTaskName(args[1]);
                arguments.setCategoryName(args.length > 2 ? args[2] : TaskCategory.NONE);
            }
        }
        return arguments;
    }

    private static boolean validateArgs(String[] args){
        String comm = args[0];
        if(comm.equals("start") || comm.equals("stop") || comm.equals("report") || comm.equals("restart")){
            if(args.length < 2){
                if(args[0].equals("restart") || args[0].equals("exit")){
                    return true;
                } else{
                    System.out.println("Insufficient arguments");
                    return false;
                }
            }
            return true;
        } else{
            System.out.println("Invalid command");
            return false;
        }
    }
}
